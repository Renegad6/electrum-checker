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

# NuSMV is the external backend required by Electrod for unbounded checking.
RUN apt-get update && \
    apt-get install -y --no-install-recommends ca-certificates curl xz-utils libxml2-16 libedit2 && \
    curl -fsSL https://nusmv.fbk.eu/distrib/2.7.1/NuSMV-2.7.1-linux64.tar.xz \
        -o /tmp/NuSMV-2.7.1-linux64.tar.xz && \
    echo '894dc71f0997683f28d4dc3512e3a6b608e02b9700e5e813017931df2d11883c  /tmp/NuSMV-2.7.1-linux64.tar.xz' | sha256sum --check - && \
    tar -xJf /tmp/NuSMV-2.7.1-linux64.tar.xz -C /opt && \
    ln -s /opt/NuSMV-2.7.1-linux64/bin/NuSMV /usr/local/bin/NuSMV && \
    ln -s /usr/lib/x86_64-linux-gnu/libxml2.so.16 /usr/lib/x86_64-linux-gnu/libxml2.so.2 && \
    ln -s /usr/lib/x86_64-linux-gnu/libedit.so.2 /usr/lib/x86_64-linux-gnu/libedit.so.0 && \
    rm /tmp/NuSMV-2.7.1-linux64.tar.xz && \
    rm -rf /var/lib/apt/lists/*

# Copiar el JAR ejecutable final
COPY --from=build /opt/electrum2/org.alloytools.alloy.dist/target/org.alloytools.alloy.dist.jar /app/electrum.jar

# Script de ejecución
RUN echo '#!/bin/sh\nexec java -jar /app/electrum.jar --cli "$@"' > /usr/bin/electrum && \
    chmod +x /usr/bin/electrum

ENTRYPOINT ["electrum"]
