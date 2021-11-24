# springboot-rabbitmq

This project implements all of RabbitMQ Exchange Types using Spring Boot as both Producer and Consumer

## RabbitMQ Exchange Types

- Direct Exchange 
- Fanout Exchange
- Topic Exchange
- Header Exchange
- Default Exchange

All of the exchange implementation is located in the spring-rabbitMQ-producer folder.

## Installing RabbitMQ 
- To install with Docker,  you can use the command: 
  `docker run -d --hostname my-rabbit --name <NAME-OF-CONTAINER> -p 5672:5672 -p 15672:15672 rabbitmq:3-management`
- Open rabbitMQ on the web with `http://localhost:15672` or `http://{host-IP}:15672`
- Login with the default Credentials: User: "guest" & password: "guest" to access RabbitMQ Dashboard

You can visit [here](https://www.rabbitmq.com/download.html) to install RabbitMQ without Docker

## Getting Started

These instructions will get you a copy of the project up and running on your local machine.

- Click on the 'Clone or download' button and select 'Download Zip. or use `git clone` option
- Navigate to the `spring-rabbitMQ-producer` folder, install Dependacies and Run
- Make sure you have RabbitMQ running
- Test all the endpoints located in the [MessagePublisher.java](https://github.com/epaitoo/springboot-rabbitmq/tree/main/spring-rabbitmq-producer/src/main/java/com/epaitoo/springmq/controller) file
- Check the RabbitMQ dashboard for the queues in the `Queue` section
- Navigate to the `spring-rabbitMQ-consumer` folder, install Dependacies, Run 
- View all messages

You can also visit the [RabbitMQ Website](https://www.rabbitmq.com/) for more.
