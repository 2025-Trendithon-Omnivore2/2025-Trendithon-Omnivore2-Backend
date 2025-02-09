FROM openjdk:17-jdk
#CMD ["./gradlew", "clean", "build", "-x", "test"]
ARG JAR_FILE_PATH=build/libs/Omnivore2Trendithon2025-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE_PATH} hear-me-out.jar
ENTRYPOINT ["java", "-jar", "hear-me-out.jar"]
RUN ln -snf /usr/share/zoneinfo/Asia/Seoul /etc/localtime