# Use an official Java runtime as the base image
FROM openjdk:17-jdk-slim as build

# Set the working directory in the container
WORKDIR /app

# Copy the Maven or Gradle build files into the container
# (assuming you're using Maven and your build file is 'pom.xml')
COPY pom.xml .

# Download the dependencies (this will be cached if the dependencies haven't changed)
RUN ./mvnw dependency:go-offline

# Copy the entire source code into the container
COPY src /app/src

# Build the Spring Boot application (this will create a jar file)
RUN ./mvnw clean package -DskipTests

# Create a new stage for running the application
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file from the build stage into the final image
COPY --from=build /app/target/*.jar app.jar

# Expose the port the application will run on
EXPOSE 8080

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]
