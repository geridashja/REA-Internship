# TODO Web App using Spring Boot and Postgresql

A simple Todo Web App using Spring Boot with the following options:

* Spring Security for Auth
* Postgresql 
* Thymeleaf for Front-End
* Maven is used for Dependency Management.


### Installing
This app is built using [Maven](https://spring.io/guides/gs/maven/). Since this app is under my Internship Repository, you can download this project along with others by following these steps:

```
git clone https://github.com/geridashja/REA-Internship.git
cd REA-Internship
cd Internship\ Assignment2/
cd todo
```

## Configure Postgresql

1. Create a database named "users" (I named it as a test database) in your Postgresql (psql or gui).
2. Update the application.properties file in the src/main/resources folder with the URL, username and password for your Postgresql instance. The table schemas as well as Relationships for the Todo and todouser objects will be created for you in the database.


### Executing program
In the todo directory:
```
./mvnw package
java -jar target/*.jar
```
