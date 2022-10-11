FROM maven:3.6.3-jdk-15

WORKDIR /springbootrestdemo

COPY . .

RUN mvn clean install

CMD mvn spring-boot:run