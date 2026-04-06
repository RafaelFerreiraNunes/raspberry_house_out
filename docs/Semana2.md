# 🟡 Semana 2: Confiabilidade e Event-Driven Programming

Nesta semana, a aplicação foi aprimorada para lidar com eventos de controle via REST e melhorar a observabilidade através de logs estruturados.

## 🛠️ Código e Configurações Envolvidas

### 1. Controlador de LEDs (`LedController.kt`)
Exposição de endpoints REST para acionamento individual de LEDs.
- **Local:** `src/main/kotlin/com/raspberry/house_raspberry_out/controller/LedController.kt`
- **Endpoints:** `@GetMapping("/led/{led}")`.
- **Lógica:** Conversão de parâmetros de URL para índices de pinos.

### 2. Configuração de Logs (`logback-spring.xml`)
Adição de persistência e suporte a múltiplos ambientes (IDE e Docker).
- **Local:** `src/main/resources/logback-spring.xml`
- **Propriedades:** `${LOG_PATH:-logs}` - Permite rodar localmente sem erro de diretório.
- **Appenders:**
    - `STDOUT` (com cores para o console).
    - `FILE` (texto puro para persistência).

### 3. Gerenciamento de Estado (`LedService.kt`)
Mudança na forma como o estado é alterado.
- **Local:** `src/main/kotlin/com/raspberry/house_raspberry_out/service/LedService.kt`
- **Função:** `changeStatus(ledNumber: Int)` - Uso de `led.toggle()` para inverter o estado do pino.

### 4. Application Properties (`application.yaml`)
Configuração da porta padrão e variáveis de ambiente para gRPC.
- **Local:** `src/main/resources/application.yaml`
- **Configurações:** `server.port`, `grpc.server.port`.

## 📖 Relação com o Plano de Estudo
- **H3 (Refatoração):** Início da transição para serviços reativos e assíncronos.
- **H4 (Integração Local):** Conexão bem-sucedida entre o controlador REST e o serviço de hardware.
