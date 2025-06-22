###Para desenvolvimento
# Use uma imagem base do Java (exemplo: OpenJDK)
#FROM maven:3.8.4-openjdk-17-slim AS build

# Diretório de trabalho da aplicação dentro do container
#WORKDIR /app

# Copia o application.properties para o container
#COPY . .

# Porta que a aplicação irá escutar
#EXPOSE 8083

# Comando para rodar a aplicação
#ENTRYPOINT ["sh", "-c", "mvn spring-boot:run -Dspring-boot.run.jvmArguments=\"$JAVA_OPTS\""]



###Para Produção
# Etapa 1: Construção do JAR
FROM maven:3.8.5-openjdk-17-slim AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Etapa 2: Imagem final com a aplicação
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/reserva-sala*.jar /app/reserva-sala.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "reserva-sala.jar"]