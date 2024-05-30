FROM scratch
ENV APP_NAME produto_case_java
COPY ./target/${APP_NAME}.jar /app/${APP_NAME}.jar
WORKDIR /app
CMD java -jar ${APP_NAME}.jar
EXPOSE 8080

