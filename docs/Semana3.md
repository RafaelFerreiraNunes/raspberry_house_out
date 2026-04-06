# 🔵 Semana 3: Docker Avançado + ARM64 (Portabilidade)

Nesta semana, a aplicação foi preparada para ser executada em containers Docker, resolvendo os desafios de acesso ao hardware e arquitetura ARM64.

## 🛠️ Código e Configurações Envolvidas

### 1. Dockerfile Multi-stage (`Dockerfile`)
Construção otimizada para Pi 5.
- **Local:** `Dockerfile`
- **Build Stage:** `maven:3.9-amazoncorretto-25`.
- **Final Stage:** `amazoncorretto:25-al2023`.
- **Ações:**
    - `RUN yum install -y util-linux` (para utilitários de sistema).
    - `WORKDIR /app`.
    - `ENTRYPOINT ["java", "-jar", "app.jar"]`.

### 2. Implementação gRPC (`LedGrpcHandler.kt`)
Adição de interface de alta performance.
- **Local:** `src/main/kotlin/com/raspberry/house_raspberry_out/grpc/LedGrpcHandler.kt`
- **Serviço:** `@GrpcService`.
- **Função:** `changeStatus(request: LedRequest)` - Implementação assíncrona (`suspend`).

### 3. Configuração de Variáveis (`.env`)
Configuração de portas externas sem alterar o código.
- **Local:** `.env`
- **Variáveis:** `APP_PORT=8090`.

### 4. Permissões de Hardware (`docker-compose.yaml`)
Passagem de dispositivos físicos para o container.
- **Local:** `docker-compose.yaml` (apenas a seção da app-led).
- **Devices:** `"/dev/gpiochip0:/dev/gpiochip0"`.
- **Privileged:** `true` - Garante acesso total ao kernel.

## 📖 Relação com o Plano de Estudo
- **H2 (Dockerfile Sênior):** Implementação do multi-stage build.
- **H4 (Hardware Passthrough):** Mapeamento de `/dev` no Docker Compose.
- **H5 (Deploy Isolado):** Criação da imagem `raspberry-out:1.0`.
