## About 
Personal WebFlux + Armeria practice
</br></br>

## Goal
Implement CRUD based on WebFlux, Armeria + WebFlux, and Armeria + MVC
</br></br>

## APIs
Check Swagger UI (http://[IP]:[PORT]/webjars/swagger-ui/index.html)

- GET
    - /devices
    - /devices/{id}

- POST
    - /devices

- PUT
    - /devices/{id}

- DELETE
    - /devices/{id}

</br>
All media types are application/json
</br></br>

## ETC
Check Armeria Doc Service UI (http://[IP]:[PORT]/docs/#/)
</br></br>

## References
WebFlux
- https://docs.spring.io/spring-framework/reference/web/webflux/new-framework.html

WebFlux + Armeria
- https://armeria.dev/docs/advanced/spring-webflux-integration/
- https://github.com/line/armeria-examples/tree/main/spring-boot-webflux

MVC + Armeria
- https://armeria.dev/docs/advanced/spring-boot-integration/
- https://github.com/line/armeria-examples/tree/main/spring-boot-tomcat
