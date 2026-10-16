# Limp Ecologic

## 1. Nome do projeto e do grupo

**Projeto:** Limp Ecologic  
**Integrante:** Gabriel de Castro Silva  
**Instituição:** Fatec Itaquera — Desenvolvimento de Software Multiplataforma.

## 2. Link do repositório Git

https://github.com/CastroGitHub/limp-ecologic-kotlin

## 3. Integrante e responsabilidades

**Gabriel de Castro Silva:** responsável pelo desenvolvimento do projeto, incluindo backend, frontend multiplataforma e banco de dados.

**Entidades:** Clientes e Agendamentos.

## 4. Descrição da aplicação

O Limp Ecologic é um sistema multiplataforma para gerenciamento de clientes e agendamentos de uma empresa de higienização de estofados.

A aplicação busca centralizar informações, facilitar a organização dos atendimentos e reduzir problemas causados por anotações manuais e planilhas dispersas. O sistema será desenvolvido para dispositivos móveis, web e desktop.

## 5. Tecnologias utilizadas

- Kotlin
- Spring Boot
- Kotlin Multiplatform
- Compose Multiplatform
- MySQL
- Gradle
- IntelliJ IDEA
- Git e GitHub

## 6. Camadas da arquitetura e pastas

### Backend

Pasta: `backend/`

- **Controller:** recebe as requisições.
- **Service:** contém as regras de negócio.
- **Repository:** realiza o acesso aos dados.
- **Model/Entity:** representa as entidades do sistema.
- **Resources:** contém configurações em `src/main/resources/`.

Pacote principal: `com.lekotlin.backend`

### Frontend

Pasta: `frontend/`

- `composeApp/`: aplicação compartilhada e interfaces.
- `androidApp/`: estrutura para Android.
- `iosApp/`: estrutura para iOS.
- `desktopApp/`: estrutura para desktop.
- `webApp/`: estrutura para web.

### Banco de dados

**MySQL:** armazena os dados de clientes e agendamentos. A comunicação com o banco é realizada pelo backend.

## 7. Instalação, execução e acesso

### Pré-requisitos

- IntelliJ IDEA
- JDK 17
- MySQL Server
- Git

### Clonar o repositório

```bash
git clone https://github.com/CastroGitHub/limp-ecologic-kotlin.git
cd limp-ecologic-kotlin
```

### Configurar o banco de dados

1. Iniciar o MySQL.
2. Criar o banco de dados `le_kotlin`.
3. Configurar a variável de ambiente `DB_PASSWORD` com a senha do MySQL.

### Executar o backend

1. Abrir o projeto no IntelliJ IDEA.
2. Configurar a variável `DB_PASSWORD` na execução do backend.
3. Executar a classe `BackendApplication`.

Endereço local: `http://localhost:8080`

### Executar o frontend

1. Abrir o módulo `frontend` no IntelliJ IDEA.
2. Aguardar a sincronização do Gradle.
3. Selecionar a configuração da plataforma desejada e executar.

O projeto é organizado para atender às plataformas mobile, web e desktop.
