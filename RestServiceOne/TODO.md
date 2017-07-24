# TODO

## Coding
### Rest

- JSON/XML annotations on domain objects
- JSON/XML views ( show less or different info from a domain )
`com.example.demo.rest.AccountEndpoint`  

> for Java - JAXB approach see:
- <http://localhost:8080/api/accounts/1>
- <http://localhost:8080/api/accounts/1/sensitive> 

> for Jackson approach (requires `com.fasterxml.jackson.annotation.JsonView` annotations to account and rest method) see:
- <http://localhost:8080/rest/api/accounts/1/jackson>
- <http://localhost:8080/rest/api/accounts/1/jackson/sensitive>

### Discovery
- Service discovery (consul - spring boot)
- Service discovery (consul - wildfly)

### Service
- Bootstrap different Service instances (Spring / EJB ? )
- Bootstrap alternative implementations of converters on the same service (flavouring)

### DDD
- Model to Entity Converters


### Security
- Security with annotations (Spring vs Shiro vs Custom)

### Messaging
- topic / queue communication (Spring)
- topic / queue communication (EE)

## DevOps
- Provide SpringBoot deployment with filtering properties
- Provide Wildfly deployment with filtering properties
- Wildfly CLI 
- flyway configuration (sub module)

## Unit Testing


## Integration testing

