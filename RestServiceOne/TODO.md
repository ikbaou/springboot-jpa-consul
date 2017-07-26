## Intro
------------------------------------------------------------

this is a stripped down version of the whole framework, targeted to cross framework development (EJB/Spring)
single module and dodgy package names for simplicity ...

## Done
------------------------------------------------------------

#### Coding

###### Rest

- JSON/XML annotations on domain objects
- JSON/XML views ( show less or different info from a domain )
`com.example.demo.rest.AccountEndpoint`  

> for Java - JAXB approach see:
- <http://localhost:8080/api/accounts/1>
- <http://localhost:8080/api/accounts/1/sensitive> 

> for Jackson approach see (requires `com.fasterxml.jackson.annotation.JsonView` annotations to account and rest method):
- <http://localhost:8080/rest/api/accounts/1/jackson>
- <http://localhost:8080/rest/api/accounts/1/jackson/sensitive>

> I personally favour the jackson approach.

- javax.validation on domain objects with groups
> see `com.example.demo.domain.Account` , ``

###### DDD
- Model to Entity Converters
> see `com.example.demo.util.AbstractMapper` and `com.example.demo.entity.mapper`

## Todo
------------------------------------------------------------

#### Coding

###### Discovery
- embedded consul ?
- Service discovery (consul - spring boot)
- Service discovery (consul - wildfly)

###### Service
- resolve and use in a uniform manner services based on different technologies sharing the actual business logic (Spring / EJB ? )

###### Security
- Security with annotations (Spring vs Shiro vs Custom)

###### Messaging
- topic / queue communication (Spring - ActiveMQ embedded / remote)
> see `com.example.demo.config.JmsConfiguration` and `com.example.demo.jms.Receiver`
- topic / queue communication (MDB - ActiveMQ embedded / remote)

###### Flavouring
- Provide alternative implementations of domain / entity / converter / service / dao  (flavouring)
- Enable automatic loading of the above in place of the originals thus maintaining AND/OR extending business logic


#### DevOps
- Provide SpringBoot deployment with filtering properties
- Provide Wildfly deployment with filtering properties
- Wildfly CLI 
- flyway configuration (sub module)

#### Unit Testing


#### Integration testing

