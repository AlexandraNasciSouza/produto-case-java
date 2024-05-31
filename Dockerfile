FROM ubuntu:20.04
ENV APP_NAME produto_case_java
COPY ./target/${APP_NAME}.jar /app/${APP_NAME}.jar
WORKDIR /app
CMD /bin/sh
EXPOSE 3000


