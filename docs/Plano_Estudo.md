# 📅 Plano de Estudos: Raspberry Pi & Desenvolvimento de Sistemas

Este documento detalha o cronograma de estudos e implementação prática para o projeto de automação com Raspberry Pi 5, Kotlin e Docker.

---

## 🟢 Semana 1: Fundamentos e Hardware
**Objetivo:** Estabelecer a base de eletrônica e comunicação inicial com o hardware.

*   **H1: Estudo Teórico - Eletrônica de Entrada**
    *   Entender a diferença entre sinais flutuantes, resistores de Pull-up e Pull-down (internos e externos).
*   **H2: Montagem Física - Circuito de Botão**
    *   Implementar o circuito físico na protoboard usando resistores externos para garantir estabilidade.
*   **H3: Teste de Baixo Nível (Shell)**
    *   Validar a leitura do pino GPIO diretamente via terminal Linux (`gpiod`/`sysfs`) para isolar problemas de hardware do software.
*   **H4: Configuração do Projeto Kotlin (Gradle/DioZero)**
    *   Adicionar dependências de acesso ao GPIO e configurar o ambiente de desenvolvimento remoto no Raspberry.
*   **H5: Código Inicial - Leitura de Estado**
    *   Criar um serviço simples que imprime no log o estado atual do botão (HIGH/LOW) em tempo real.

---

## 🟡 Semana 2: Confiabilidade e Event-Driven Programming
**Objetivo:** Migrar do modelo de "espera ocupada" (polling) para o modelo de "interrupção", garantindo eficiência.

*   **H1: Estudo Teórico - O Fenômeno do "Bouncing"**
    *   [x] **Status: Concluído**
    *   Entendemos por que botões mecânicos geram ruído e como isso afeta sistemas de alta velocidade como o Pi 5.
*   **H2: Implementação - Debounce via Software**
    *   [x] **Status: Concluído**
    *   Configuração do `setDebounceTimeMs(50)` para filtrar ruídos.
*   **H3: Refatoração - De Polling para Interrupções**
    *   [x] **Status: Concluído**
    *   Troca do `while(true)` pelo `addListener`, permitindo que o Kernel do Linux notifique a JVM.
*   **H4: Integração Local - Botão → LED**
    *   [ ] **Status: Próximo Passo**
    *   Unir as peças: o evento de clique aciona a lógica que controla o LED, respeitando a separação de pacotes.
*   **H5: Teste de Stress e Demonstração**
    *   Validar a latência e a precisão sob apertos repetidos, garantindo zero comandos fantasmas.

---

## 🔵 Semana 3: Docker Avançado + ARM64 (Portabilidade)
**Objetivo:** Garantir que a aplicação rode de forma consistente em qualquer ambiente Linux ARM64.

*   **H1: Teoria do Snapshot e Imutabilidade**
    *   Anatomia de uma imagem (Layers).
    *   Diferença entre `CMD` e `ENTRYPOINT` para passagem de parâmetros via CLI.
    *   Docker Host vs. Container Runtime.
*   **H2: Construção do Dockerfile "Sênior" (Multi-stage)**
    *   Criar Dockerfile que compila com Maven em um estágio e gera a imagem final leve (`debian-slim` ou `corretto-al2023`).
*   **H3: Networking e o Conceito de Bridge**
    *   Como o Docker cria a placa de rede virtual (`docker0`).
    *   Diferença entre `EXPOSE` e mapeamento de porta do host (`-p`).
*   **H4: Hardware Passthrough (O Desafio do Pi 5)**
    *   Como o Kernel compartilha `/dev` com o processo isolado.
    *   Mapear privilégios de GPIO e caminhos `/dev/gpiochipX`.
*   **H5: Hands-on - Deploy Isolado da App B (LED)**
    *   Subir o container manualmente e validar via `curl` externo.

---

## 🟣 Semana 4: Docker Compose & Orquestração
**Objetivo:** Orquestrar múltiplas aplicações e configurar comunicação interna via DNS.

*   **H1: Teoria da Orquestração (O Maestro)**
    *   Por que evitar scripts `.sh` e usar YAML. Introdução à sintaxe do Docker Compose.
*   **H2: Service Discovery e DNS Interno**
    *   Resolução de nomes entre containers (ex: `http://app-led:8090`).
    *   Criação de redes dedicadas no Compose.
*   **H3: Persistência e Volumes (Onde os Logs moram)**
    *   Configurar Bind Mounts para persistir logs no SSD da Raspberry Pi.
*   **H4: Gestão de Dependências e Healthchecks**
    *   Configurar `depends_on` e `healthcheck` via Spring Actuator para garantir ordem de subida.
*   **H5: O Grand Finale - Stress Test Containerizado**
    *   Rodar o sistema completo, medir latência gRPC e validar estabilidade na rede virtual.

---

## 🚀 Tópicos Adicionais semana 4 (Resumo)

| H | Tópico | Teoria / Explicação | Prática Sênior |
| :--- | :--- | :--- | :--- |
| **H1** | **Docker Compose & Persistência** | Anatomia do YAML + Bind Mounts vs Volumes. | `restart: always` + Mapeamento de logs para Host SSD. |
| **H2** | **gRPC vs REST & Service Discovery** | Protocol Buffers vs JSON no Pi 5. | Criar `.proto` e chamada via DNS interno. |
| **H3** | **Configuração Dinâmica (.env)** | 12-Factor App: Config vs Código. | Uso de `.env` para Portas e `CHIP_ID`. |
| **H4** | **Hardware Healthcheck & Resiliência** | Liveness vs Readiness + Circuit Breaker. | Actuator + Resilience4j + Retry logic. |
| **H5** | **The Big Green Button** | Análise de gargalos distribuídos. | Deploy total, latência gRPC e persistência. |
