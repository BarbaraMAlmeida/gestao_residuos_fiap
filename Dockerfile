#FROM eclipse-temurin:21-alpine
#VOLUME /tmp
#EXPOSE 8080
#ARG JAR_FILE=target/atvd-spring-boot-0.0.1-SNAPSHOT.jar
#ADD ${JAR_FILE} app.jar
#ENTRYPOINT ["java","-jar","app.jar"]

# Stage 1: build
FROM maven:3.8.5-openjdk-17-slim AS builder

WORKDIR /app

# Copia somente o pom.xml primeiro (cache)
COPY pom.xml .
# Se você usar settings.xml para credenciais private, copie também aqui:
# COPY settings.xml /root/.m2/settings.xml

RUN mvn dependency:go-offline -B

# Copia código-fonte
COPY src ./src

# Empacota sem rodar testes (ajuste se quiser rodar testes)
RUN mvn package -DskipTests -B

# Stage 2: runtime
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

# Copia o jar gerado
COPY --from=builder /app/target/*.jar app.jar

# Defina variáveis de ambiente padrão para Oracle (pode sobrescrever no compose)
ENV SPRING_DATASOURCE_URL=jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL \
    SPRING_DATASOURCE_USERNAME=RM552825 \
    SPRING_DATASOURCE_PASSWORD=220105 \
    JAVA_OPTS=""

# Exponha a porta do Spring Boot
EXPOSE 8080

# Entrypoint padrão
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -jar app.jar" ]
