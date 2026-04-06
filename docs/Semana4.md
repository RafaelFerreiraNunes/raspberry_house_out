# 🟣 Semana 4: Docker Compose & Orquestração

Nesta semana, as duas aplicações foram integradas via Docker Compose, com foco em persistência de dados, redes virtuais e resiliência.

## 🛠️ Código e Configurações Envolvidas

### 1. Orquestração Completa (`docker-compose.yaml`)
Configuração de múltiplos serviços e rede interna.
- **Local:** `docker-compose.yaml`
- **Serviços:** `app-led`, `app-botao`.
- **Rede:** `rpi-network` (driver: `bridge`).
- **Ordem de Inicialização:** `depends_on: app-led (condition: service_healthy)`.

### 2. DNS Interno e Comunicação entre Containers
As aplicações se comunicam via nome de host Docker.
- **Configuração no Compose:** `LED_SERVICE_URL: app-led`, `GRPC_LED_HOST: app-led`.
- **Lógica:** A `app-botao` chama a `app-led` sem precisar de IPs fixos.

### 3. Persistência de Logs (Volumes)
Mapeamento para armazenamento durável fora do ciclo de vida dos containers.
- **Configuração no Compose:** `volumes: - ../log/app-led:/app/logs`.
- **Logback Integration:** A variável `LOG_PATH` é passada para a aplicação.

### 4. Resiliência e Monitoramento (Healthcheck)
Verificação automática da saúde do serviço.
- **Configuração no Compose:** `healthcheck: test: ["CMD", "curl", "-f", "http://localhost:8090/actuator/health"]`.
- **Lógica:** A aplicação `app-led` expõe seu estado para o Docker orquestrar a dependência.

### 5. Configurações de JVM (`JAVA_TOOL_OPTIONS`)
Flags necessárias para rodar gRPC e Pi4J no Java 21+ dentro do container.
- **Flags:** `--enable-native-access=ALL-UNNAMED`, `--add-opens=jdk.unsupported/sun.misc=ALL-UNNAMED`.

## 📖 Relação com o Plano de Estudo
- **H2 (Service Discovery):** Uso do DNS interno do Docker para chamadas REST/gRPC.
- **H3 (Volumes):** Persistência centralizada em `../log`.
- **H4 (Healthchecks):** Implementação via Spring Actuator no Docker Compose.
