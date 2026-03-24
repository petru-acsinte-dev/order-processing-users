OrderProcessor - Users microservice - Daily journal
=

2026-03-23
-
- created new GitHub repository for users
- created Spring Boot project stub for users with dependencies matching the monolith, plus the new common library 
- introduced CI with tests and JaCoCo coverage

2026-03-24
-
- ported user classes from monolith: dao, entities, repos, service, controllers, configurations, mappers, props, exceptions, constants
- ported security specific classes, including configurations, controller, JWT service, Json filter and custom UserDetailsSecurityService
- ported from monolith and adapted user tests to rely on common test classes
- reword the Flyway scripts into a fresh, clean users initialization script
- removed non user related sample data
- integrated user external id into JWT token (to be used by future orders and shipments services)
