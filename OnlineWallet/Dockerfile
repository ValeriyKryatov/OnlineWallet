FROM amazoncorretto:17.0.10
COPY ./target/OnlineWallet-0.0.1-SNAPSHOT.jar /usr/app/
WORKDIR /usr/app
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "OnlineWallet-0.0.1-SNAPSHOT.jar"]