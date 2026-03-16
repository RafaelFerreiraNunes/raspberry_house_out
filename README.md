# House Raspberry Out

## Descrição

Esta é uma aplicação Spring Boot desenvolvida em Kotlin para controlar LEDs conectados a uma Raspberry Pi. A aplicação expõe uma API REST para controlar o estado dos LEDs.

## Tecnologias Utilizadas

*   **Java 21**
*   **Kotlin**
*   **Spring Boot**: Framework para criação de aplicações Java.
*   **Maven**: Ferramenta de automação de compilação.
*   **Diozero**: Biblioteca para controle de GPIO em dispositivos como a Raspberry Pi.

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
├── .gitattributes
├── .gitignore
├── mvnw
├── mvnw.cmd
└── pom.xml
```

*   `src/main/kotlin`: Contém o código-fonte da aplicação.
    *   `controller`: Contém os controladores REST da aplicação.
    *   `service`: Contém a lógica de negócio da aplicação.
    *   `factory`: Contém as fábricas da aplicação.
    *   `HouseRaspberryOutApplication.kt`: Classe principal que inicia a aplicação Spring Boot.
*   `pom.xml`: Arquivo de configuração do Maven que define as dependências e o processo de build do projeto.

## API Endpoints

A aplicação expõe a seguinte API para controlar os LEDs:

### Ligar/Desligar um LED

*   **URL**: `/led/{led}`
*   **Método**: `GET`
*   **Descrição**: Altera o estado de um LED específico.
*   **Parâmetros de URL**:
    *   `led`: O número do LED a ser controlado (deve ser um valor numérico).

#### Exemplo de Requisição

```bash
curl http://localhost:8080/led/1
```

## Como Construir e Executar a Aplicação

### Pré-requisitos

*   Java 21 ou superior
*   Maven

### Passos

1.  **Clone o repositório:**

    ```bash
    git clone <url-do-repositorio>
    cd house_raspberry_out
    ```

2.  **Construa o projeto com o Maven:**

    ```bash
    ./mvnw clean install
    ```

3.  **Execute a aplicação:**

    ```bash
    java -jar target/house_raspberry_out-0.0.1-SNAPSHOT.jar
    ```

A aplicação estará disponível em `http://localhost:8080`.
