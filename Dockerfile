ARG DOCKER_REPO
FROM ${DOCKER_REPO}/dockerhub-proxy-cache/adoptopenjdk/openjdk11
WORKDIR /repo
COPY . .
RUN ./gradlew bootJar -i

FROM ${DOCKER_REPO}/appops_release/alpine
RUN apk add openjdk11-jre
WORKDIR /app
COPY --from=0 /repo/build/libs/redis-0.0.1-SNAPSHOT.jar ./app.jar
ENTRYPOINT [ "java", "-jar", "app.jar" ]
