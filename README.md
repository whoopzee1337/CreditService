# MTSFinalProject

Данный проект - выпускной проект для MTS FINTECH ACADEMY.

## В проекте были реализованы следующие микросервисы:
1. MTSFinalProject - сервис, отвечающий за обработку кредитных заявок.
2. MTSFinalProjectSecurity - сервис, с реализацией SpringSecurity и фронтендом. 
3. MTSFinalProjectApiGateway - сервис, реализующий централизированный вход в микросервисное приложение.
4. MTSFinalProjectEurekaServer - сервис, реализующий сервер для Spring Cloud Eureka.

## Для реализации проекта использовались такие фреймворки и технологии:
1. Spring Boot
2. Spring Security
3. Spring Cloud
4. Spring Data 
5. Liquibase
6. Testcontainers
7. Docker
8. PostgreSQL
9. Lombok
10. Thymeleaf


## Для запуска проекта необходимо:
1. Установить и запустить Docker на вашем компьютере.
2. В терминале перейти в директорию проекта. Запустить контейнер PostgreSQL с помощью команды: docker-compose up -d
3. Запустить каждый из микросервисов

### Ссылки на ключевые endpoint'ы
POST   http://localhost:8765/loan-service/order - добавление новой заявки  

GET    http://localhost:8765/loan-service/getTariffs - получение тарифов  

GET    http://localhost:8765/loan-service/getStatusOrder - получение статуса заявки  

DELETE http://localhost:8765/loan-service/deleteOrder - удаление заявки

GET http://localhost:8765/security-service/auth/login - стартовая страница фронтенда

