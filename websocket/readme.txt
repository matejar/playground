The deliverable is two part:
1. Access to a github repository containing your solution for us to review.
2. Either a fully running solution on an AWS image(Ubuntu) or a Docker setup and configuration for running the solution locally.

The task is to implement a data processing pipeline in the cloud.
Set up a running environment aligned with the technologies mentioned below
A Readme file containing information you deem useful for someone getting to know your code and want to try the system out

Develop the application in Java 8, using either DropWizard or Spring Boot as the foundation
A REST endpoint is taking a dummy JSON input, and the server puts the REST payload on Redis or another tool you think is well suited for the task
A Consumer is running in the application, taking the freshly received message and persists it in a database of your choice
A REST endpoint is implemented for retrieving all the messages persisted in JSON format from the database
The message should also be pushed through Websockets for listening browser clients at the time the message was received on the REST endpoint
A simple HTML page is implemented to show the real time message delivery
Please setup a github repository to host the code and share it with your final note for review

We're looking for that:
All tasks are solved in the solution
The application has a solid commit history
The application is built with scalability and maintainability in mind.
The application is built for testability, demonstrated by actual tests
Your solution reflects a sense of quality you would be confident in releasing to production
Documentation is applied to code/ repository describing intent and purpose, as well as complicated/ non obvious choices in the implementation
Feel free to ask any questions regarding the test. 