# Pixel Tactics - Users Microservice
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![RabbitMQ](https://img.shields.io/badge/Rabbitmq-FF6600?style=for-the-badge&logo=rabbitmq&logoColor=white)

This is the users microservice of Pixel Tactics. This service focuses on handling user data such as authentication, user statistics, etc.

## Installing
First, you need to setup the environment variables. You can create `.env` file in the root directory to do this step. Below are the required envs:
- `JDBC_DATABASE_URL`: URL for PostgreSQL database. Example: `jdbc:postgresql://localhost:5432/pixel_tactics`.
- `JDBC_DATABASE_USERNAME`: Username for the database. Example: `postgres`.
- `JDBC_DATABASE_PASSWORD`: Password for teh database.
- `JWT_SECRET_KEY`: Secret key for internal JWT encryption process.
- `JWT_EXPIRATION_TIME`: JWT Token expiration time in milliseconds.
- `MICROSERVICE_SECRET_KEY`: Secret key to protect the microservice interaction endpoints. Include this key in `X-SERVICE-SECRET` HTTP header for any service that needs to interact with this service via endpoints.
- `RABBITMQ_HOST`: Hostname for RabbitMQ server.
- `RABBITMQ_PORT`: Port for RabbitMQ server. It is usually `5672`.
- `RABBITMQ_USERNAME`: Username for RabbitMQ server.
- `RABBITMQ_PASSWORD`: Password for RabbitMQ server.

After setting up the environment variables, you need to install dependencies.
```
mvn install
```

After installing the required dependencies, ensure that the Match Microservice and RabbitMQ server is active. Then, to run this service, run:
```
mvn spring-boot:run
```

## Developers
- Meervix (Emyr298)
