SpringBoot messaging-rabbitmq!
-------------------

## Description

This project aims to present how use springboot to consume rabbitmq message.
It contains:
* a REST endpoint to send message as String
* a producer service to send message to rabbitMq
* a rabbitMq listener

To deploy rabbitmq broker, run this [docker-compose](https://github.com/fvandenbulcke/docker/blob/master/dockercompose/rabbitmq_broker).

### Message produce endpoint

Sample of request to produce endpoint:

```bash
curl -X POST http://localhost:8080/message/produce/MY_RABBIT_MESSAGE
```

Webservice send a response with a 201 http status.


### Message consumer

Each consumed message is logged as follow:

```xml
Received <MY_RABBIT_MESSAGE>
```
