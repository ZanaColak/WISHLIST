# Set the base image to adoptopenjdk
FROM adoptopenjdk/openjdk17:alpine-slim

# Add the application jar to the container
ADD target/WISHLIST-0.0.1-SNAPSHOT.jar app.jar

# Expose the port that the application will run on
EXPOSE 8081

# Set the entry point to the application
ENTRYPOINT ["java","-jar","/app.jar", "-b"]