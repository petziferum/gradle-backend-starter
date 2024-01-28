# Use the official Java 11 runtime as a parent image
FROM openjdk:17-oracle

  # Set the working directory in the container
WORKDIR /app

  # Copy the JAR file into the container at /app
COPY build/libs/gradlebackend-0.0.1-SNAPSHOT.jar /app/gradlebackend-0.0.1-SNAPSHOT.jar

  # Make port available to the world outside this container
EXPOSE 8080

  # Run the JAR file
CMD ["java", "-jar", "/app/gradlebackend-0.0.1-SNAPSHOT.jar"]
