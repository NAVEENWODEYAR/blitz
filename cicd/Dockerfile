	
# Use an official Maven image to build the application
FROM maven:3.8.5-openjdk-17 AS build

# Set the working directory for Maven
WORKDIR /app

# Copy the pom.xml and source code into the container
COPY pom.xml ./
COPY src ./src

# Build the application
RUN mvn clean package

# Use an official OpenJDK runtime as a parent image for the final image
FROM openjdk:17-jre-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file from the build stage into the container
COPY --from=build /app/target/myapp.jar app.jar

# Set an environment variable for the port, defaulting to 8080
ENV SERVER_PORT=8080

# Expose the port the app runs on (defaults to the value of SERVER_PORT)
EXPOSE $SERVER_PORT

# Add a label with the name of the application
LABEL name="DairyApp"

# Run the JAR file, using the environment variable for the server port
CMD ["java", "-jar", "app.jar", "--server.port=${SERVER_PORT}"]
