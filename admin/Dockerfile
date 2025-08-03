# 使用官方 OpenJDK 镜像作为基础镜像
FROM openjdk:11-jre-slim

# 设置工作目录
WORKDIR /app

# 从构建上下文复制 jar 包到容器中
COPY target/*.jar admin.jar

# 暴露端口（根据您的应用端口进行调整）
EXPOSE 8080

# 设置容器启动时执行的命令
ENTRYPOINT ["java", "-jar", "admin.jar"]
