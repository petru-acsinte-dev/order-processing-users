# Order Processing: Users Microservice

This microservice is part of the [Order Processing](https://github.com/petru-acsinte-dev) 
portfolio project, extracted from the 
[monolith](https://github.com/petru-acsinte-dev/order-processing-monolith).

It handles user registration, authentication, and profile management. 
Authentication is JWT-based: a token issued here is validated by all other 
services in the system, with user roles and identity embedded directly in the token.

**REST API** — explore and test via Swagger UI at `http://localhost:8080/swagger-ui/index.html`

### Responsibilities
- User registration and profile management
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
- [Development journal](docs/journal/daily-journal.md)
