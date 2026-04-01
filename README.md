# House Raspberry Out

## Descrição

Esta é uma aplicação Spring Boot desenvolvida em Kotlin para controlar LEDs conectados a uma Raspberry Pi. A aplicação expõe interfaces REST e gRPC para controlar o estado dos LEDs via GPIO. O projeto conta com suporte a Docker e Docker Compose para facilitar o deploy e execução.

## Funcionalidades e Alterações Recentes

*   **Controle de GPIO**: Integração com `/dev/gpiochip0` para manipulação direta de hardware.
*   **Interface gRPC**: Adicionado suporte para chamadas gRPC de alta performance.
*   **Containerização**: Adicionado `Dockerfile` e `docker-compose.yaml` para orquestração de containers.
*   **Configuração via Variáveis de Ambiente**: Porta da aplicação e caminhos de log configuráveis.
*   **Logs Persistentes**: Volume mapeado para persistência de logs fora do container, compatível com execução local e Docker.

## Tecnologias Utilizadas

*   **Java 21**
*   **Kotlin**
*   **Spring Boot**: Framework para criação de aplicações Java.
*   **gRPC**: Protocolo de comunicação de alta performance.
*   **Maven**: Ferramenta de automação de compilação.
*   **Diozero**: Biblioteca para controle de GPIO em dispositivos como a Raspberry Pi.
*   **Docker & Docker Compose**: Para containerização e orquestração.

## Estrutura do Projeto

O projeto segue a estrutura padrão de uma aplicação Spring Boot com Maven:

```
.
├── .mvn
├── src
│   ├── main
│   │   ├── kotlin
│   │   │   └── com
│   │   │       └── raspberry
│   │   │           └── house_raspberry_out
│   │   │               ├── controller
│   │   │               ├── grpc
│   │   │               ├── service
│   │   │               └── HouseRaspberryOutApplication.kt
│   │   └── resources
│   │       ├── application.yaml
│   │       └── logback-spring.xml
│   └── test
├── docker-compose.yaml
├── Dockerfile
├── .env
├── .gitattributes
├── .gitignore
├── mvnw
├── mvnw.cmd
└── pom.xml
```

## Interfaces de Comunicação

A aplicação expõe as seguintes portas:
*   **REST**: 8090 (configurável via `.env`)
*   **gRPC**: 9090

### 1. API REST (Ligar/Desligar um LED)

*   **URL**: `/led/{led}`
*   **Método**: `GET`
*   **Exemplo de Requisição**:
    ```bash
    curl http://localhost:8090/led/1
    ```

### 2. Interface gRPC (Alterar Status do LED)

*   **Serviço**: `LedService`
*   **Método**: `ChangeStatus`
*   **Exemplo de Requisição (via grpcurl)**:
    ```bash
    grpcurl -plaintext -d '{"ledId": "1"}' 127.0.0.1:9090 LedService/ChangeStatus
    ```

## Como Executar com Docker Compose

Esta é a forma recomendada de execução, pois configura o acesso ao GPIO e as portas corretamente.

### Passos

1.  **Subir a aplicação**:
    ```bash
    sudo docker compose up -d --build
    ```

2.  **Verificar Logs**:
    ```bash
    sudo docker compose logs -f
    ```

3.  **Remover containers**:
    ```bash
    sudo docker compose down
    ```

## Como Executar Localmente (IDE)

Para que o Logback funcione corretamente tanto no Docker quanto na IDE, a variável `LOG_PATH` deve ser configurada.

1.  **Configuração de Logs**:
    Ao rodar na IDE, os logs serão criados na pasta `./logs` da raiz do projeto por padrão.
2.  **Execução**:
    ```bash
    ./mvnw clean install
    java -jar target/house_raspberry_out-0.0.1-SNAPSHOT.jar
    ```

---
*Nota: Para controle de hardware (GPIO), a aplicação deve ser executada em um ambiente com os drivers necessários (como uma Raspberry Pi).*
