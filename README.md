# Spring AI Demo App
A demo app for the [Get Started with the Spring AI Integration](https://www.mongodb.com/docs/atlas/atlas-vector-search/ai-integrations/spring-ai/)
tutorial in the Atlas documentation.

This application demonstrates how to add Spring beans to set up Spring AI, then creates endpoints which you can access to add vector embeddings to MongoDB
Atlas and perform semantic (similarity) searches on this data.

## Run the Application
You can clone this repository, then replace the placeholders in the `application.properties` file with your values. You can run the application by
using the build/run tools in your IDE, or see the following section for shell commands.

### Shell Commands
Build the project
```sh
mvn clean install
```
Run the application
```sh
mvn spring-boot:run
```
The application should now be running on http://localhost:8080.
