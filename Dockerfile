FROM openjdk:11

COPY ["./target/activeWithdraw-0.0.1-SNAPSHOT.jar", "/usr/app/"]

CMD ["java", "-jar", "/usr/app/activeWithdraw-0.0.1-SNAPSHOT.jar"]

EXPOSE 8085