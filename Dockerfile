FROM adoptopenjdk/openjdk8:ubi

# copy the packaged war file into webapps directory of tomcat
RUN mkdir -p /app
WORKDIR /app
COPY target/*.jar .

# set the startup command to execute the tomcat server
CMD ["java", "-jar", "project-02-0.0.1-SNAPSHOT.jar"]