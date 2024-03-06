## Overview
This is an example on how spring-security can be used to offer a secure way to manage secexample and functions rights. 
This is done of course with Java and the Spring Security Framework, instead of using IAM services such as keycloak or similar.


## Situation
Mynotexistingcompany LTD decided that we must provide a new REST API service that allow company employees to request holidays through it.  
This service must provide a security level based on the given roles. 
The API must provide a whole set of endpoints and implement the given business logic.


### Roles - (RBCA)
Access to endpoints is restricted based on the following roles.

- `USER`: Can view their own profile info and request holidays.
- `HR`: Can view and update holidays information for all employees.
- `ADMINISTRATOR`: Has all permissions.
- `AUDITOR`: Can view security records for compliance audit purposes.

### Business Logic
The core business logic includes:

- Allow new users to sign up for the platform.
- Control users' access and authorities.
- Insert or Fetch holidays requests data from the database.
- Processing and formatting holidays requests for fe.
- Ensuring that only authorized users can access requests calendar information.
- 
## Security Concerning
### Authentication
**Basic Authentication**: Username and password mandatory for each endpoint.
### Authorization
**RBCA**: Default by Spring
### Password Hashing
Default Spring `BCrypt` hashing 14 rounds.
### Auditing
All security events will be logged into the database.


## Libraries
- Java 17
- Spring data JPA
- Mapstruct
- Flyway
- Spring Security
- H2 DB
