var stompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

function connect() {
	var socket = new SockJS('/websocket/chat');
	stompClient = Stomp.over(socket);
	stompClient.connect({}, function(frame) {
		console.log('Connected: ' + frame);
		setConnected(true);
		stompClient.subscribe('/topic/messages', function(messageOutput) {
			showMessage(JSON.parse(messageOutput.body));
		});
	});
}

function disconnect() {
	if (stompClient != null) {
		stompClient.disconnect();
	}
	setConnected(false);
	console.log("Disconnected");
}

function onLoad() {
	disconnect();
	$("#historyTable").hide();
}

function showMessage(outMessage) {
    $("#messages").prepend("<tr><td>" + outMessage.nickname + "</td><td>" + outMessage.message + "</td></tr>");
}

function showMessageHistory(outMessage) {
	$("#historyMessages").prepend("<tr><td>" + outMessage.nickname + "</td><td>" + outMessage.message + "</td></tr>");
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#sendMessage" ).click(function() {
		var nickname = $("#nickname").val();
		var message = $("#message").val();
		$.ajax({
			type : 'POST',
			url : 'message',
			data : JSON.stringify({
				'nickname' : nickname,
				'message' : message
			}),
			contentType : "application/json"
		});
	});
    $( "#history" ).click(function() {
    	$.ajax({
			type : 'GET',
			url : 'message',
			contentType : "application/json",
			success: function(data) {
				$("#historyTable").find("tbody tr").remove();
				$("#historyTable").show();
				data.forEach(showMessageHistory);
			}
		});
	});  
});
