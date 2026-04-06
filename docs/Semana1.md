# 🟢 Semana 1: Fundamentos e Hardware

Nesta semana, o foco foi estabelecer a comunicação base entre o código Kotlin e os pinos físicos da Raspberry Pi.

## 🛠️ Código e Configurações Envolvidas

### 1. Dependências de Hardware (`pom.xml`)
Para acessar o GPIO, utilizamos a biblioteca **Pi4J V2** (e anteriormente referências a DioZero).
- **Local:** `pom.xml`
- **Libs principais:**
    - `com.pi4j:pi4j-core`
    - `com.pi4j:pi4j-plugin-raspberrypi`
    - `com.pi4j:pi4j-plugin-pigpio`

### 2. Definição dos Pinos (`LedService.kt`)
Configuração dos endereços físicos (BCM) dos LEDs.
- **Local:** `src/main/kotlin/com/raspberry/house_raspberry_out/service/LedService.kt`
- **Pinos configurados:** `18, 23, 24, 25, 8, 7`.
- **Lógica:** Inicialização de uma lista de `DigitalOutput` no `init` da classe Service.

### 3. Factory de Hardware (`LedFactory.kt`)
Abstração da criação dos pinos para facilitar testes e isolar a biblioteca de hardware.
- **Local:** `src/main/kotlin/com/raspberry/house_raspberry_out/factory/LedFactory.kt`
- **Função:** `createLed(pin: Int)` - Configura o pino como `LOW` por padrão no startup.

### 4. Aplicação Principal (`HouseRaspberryOutApplication.kt`)
O ponto de entrada que sobe o contexto do Spring Boot.
- **Local:** `src/main/kotlin/com/raspberry/house_raspberry_out/HouseRaspberryOutApplication.kt`

## 📖 Relação com o Plano de Estudo
- **H4 (Configuração do Projeto):** Realizada via `pom.xml` e estrutura de pacotes Spring.
- **H5 (Código Inicial):** Implementado no `LedService` com logs de inicialização de cada pino.
