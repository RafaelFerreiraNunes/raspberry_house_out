# House Raspberry Out

## Descrição

Esta é uma aplicação Spring Boot desenvolvida em Kotlin para controlar LEDs conectados a uma Raspberry Pi. A aplicação expõe uma API REST para controlar o estado dos LEDs via GPIO. O projeto agora conta com suporte a Docker e Docker Compose para facilitar o deploy e execução.

## Funcionalidades e Alterações Recentes

*   **Controle de GPIO**: Integração com `/dev/gpiochip0` para manipulação direta de hardware.
*   **Containerização**: Adicionado `Dockerfile` e `docker-compose.yaml` para orquestração de containers.
*   **Configuração via Variáveis de Ambiente**: Porta da aplicação configurável via arquivo `.env`.
*   **Logs Persistentes**: Volume mapeado para persistência de logs fora do container.

## Tecnologias Utilizadas

*   **Java 21**
*   **Kotlin**
*   **Spring Boot**: Framework para criação de aplicações Java.
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
│   │   │               │   └── LedController.kt
│   │   │               ├── factory
│   │   │               ├── service
│   │   │               └── HouseRaspberryOutApplication.kt
│   │   └── resources
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

## API Endpoints

A aplicação expõe a seguinte API para controlar os LEDs. A porta padrão configurada no Docker é **8090**.

### Ligar/Desligar um LED

*   **URL**: `/led/{led}`
*   **Método**: `GET`
*   **Descrição**: Altera o estado de um LED específico.
*   **Parâmetros de URL**:
    *   `led`: O número do LED a ser controlado (deve ser um valor numérico).

#### Exemplo de Requisição (com Docker na porta 8090)

```bash
curl http://localhost:8090/led/1
```

## Como Executar com Docker Compose

Esta é a forma recomendada de executar a aplicação, pois já configura o acesso ao GPIO e as portas corretamente.

### Pré-requisitos

*   Docker
*   Docker Compose (plugin `docker compose` ou standalone `docker-compose`)

### Passos

1.  **Verifique o arquivo `.env`**:
    Certifique-se de que o arquivo `.env` existe na raiz e define a porta desejada (padrão 8090).
    ```properties
    APP_PORT=8090
    ```

2.  **Subir a aplicação (Build & Run)**:
    Execute o comando abaixo para construir a imagem e iniciar o container em modo "detached" (segundo plano).

    ```bash
    # Se estiver usando o plugin Docker Compose V2 (recomendado)
    sudo docker compose up -d --build

    # OU se estiver usando a versão legada standalone
    sudo docker-compose up -d --build
    ```

3.  **Verificar se está rodando**:
    ```bash
    sudo docker compose ps
    ```

4.  **Acessar os Logs**:
    Os logs são salvos localmente em `./log/app-led` ou podem ser visualizados via comando:
    ```bash
    sudo docker compose logs -f
    ```

5.  **Parar e remover os containers**:
    Para parar a aplicação e remover os containers criados:
    ```bash
    sudo docker compose down
    ```

## Como Construir e Executar Localmente (Sem Docker)

Caso queira rodar diretamente na JVM sem containers:

1.  **Construa o projeto:**
    ```bash
    ./mvnw clean install
    ```

2.  **Execute o jar:**
    ```bash
    java -jar target/house_raspberry_out-0.0.1-SNAPSHOT.jar
    ```
    *Nota: Ao rodar localmente, certifique-se de ter permissões de acesso ao GPIO da Raspberry Pi.*
