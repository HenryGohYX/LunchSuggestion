API Doc: http://localhost:8080/swagger-ui/index.html

Prerequisite:
    1. mvn clean install
    2. cd my-app
        -> npm install
        -> npm install react-cookie

To run:
    1. mvn spring-boot:run (backend)
    2. npm start (frontend)

Test coverage ->
    mvn clean test -> target/site/jacoco/index.html (open in preferred browser)