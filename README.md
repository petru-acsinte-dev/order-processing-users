# Order Processing: Users Microservice

This microservice is part of the [Order Processing](https://github.com/petru-acsinte-dev) 
portfolio project, extracted from the 
[monolith](https://github.com/petru-acsinte-dev/order-processing-monolith).

It handles user registration, authentication, and account management. 
Authentication is JWT-based: a token issued here is validated by all other 
services in the system, with user roles and identity embedded directly in the token.

**REST API** — explore and test via Swagger UI:
- Local: `http://localhost:8080/users/swagger-ui/index.html`
- Live: `http://52.205.87.85/users/swagger-ui/index.html`

### Responsibilities
- User registration and account management
- JWT token issuance (includes roles and external user ID)
- Credential validation

### Key Technologies
- Spring Boot 3.5 · Spring Security · PostgreSQL · Flyway · MapStruct · Testcontainers

### Related
- [order-processing-common](https://github.com/petru-acsinte-dev/order-processing-common) — shared library
- [order-processing-orders](https://github.com/petru-acsinte-dev/order-processing-orders)
- [order-processing-shipments](https://github.com/petru-acsinte-dev/order-processing-shipments)
- [User Story](https://github.com/petru-acsinte-dev/order-processing-monolith/blob/master/OrdersProcessor/docs/UserStory.md)
- [Design Document](https://github.com/petru-acsinte-dev/order-processing-monolith/blob/master/OrdersProcessor/docs/DesignDoc.md)
- [Development journal — monolith phase](https://github.com/petru-acsinte-dev/order-processing-monolith/blob/master/OrdersProcessor/docs/journal/daily-journal.md)
- [Development journal — microservices phase](docs/journal/daily-journal.md)
