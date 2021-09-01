FROM openjdk:11
copy ./target/tradingSystem-0.0.1-SNAPSHOT.jar tradingSystem-0.0.1-SNAPSHOT.jar
CMD ["java","-jar","tradingSystem-0.0.1-SNAPSHOT.jar"]