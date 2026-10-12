# ---------- STAGE 1: BUILD ----------
FROM eclipse-temurin:8-jdk AS build

WORKDIR /opt

RUN apt-get update && \
    apt-get install -y git && \
    apt-get clean

# Clonar Electrum2 con submódulos (CRÍTICO)
RUN git clone --recursive https://github.com/haslab/Electrum2.git electrum2

WORKDIR /opt/electrum2

# Desactivar daemon y file watching (Docker fix)
ENV GRADLE_OPTS="-Dorg.gradle.daemon=false -Dorg.gradle.vfs.watch=false"

# Build del artefacto sin ejecutar las pruebas upstream, que requieren solvers externos.
RUN ./gradlew build -x test

# ---------- STAGE 2: RUNTIME ----------
FROM eclipse-temurin:8-jre

WORKDIR /app

# Copiar el JAR ejecutable final
COPY --from=build /opt/electrum2/org.alloytools.alloy.dist/target/org.alloytools.alloy.dist.jar /app/electrum.jar

# Script de ejecución
RUN echo '#!/bin/sh\njava -jar /app/electrum.jar -q "$@"' > /usr/bin/electrum && \
    chmod +x /usr/bin/electrum

ENTRYPOINT ["electrum"]
