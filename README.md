# Limp Ecologic

## 1. Nome do projeto e integrante

**Nome do projeto:** Limp Ecologic

**Integrante:** Gabriel de Castro Silva

**Instituição:** Fatec Itaquera — Desenvolvimento de Software Multiplataforma (DSM).

## 2. Repositório Git

[https://github.com/CastroGitHub/limp-ecologic-kotlin](https://github.com/CastroGitHub/limp-ecologic-kotlin)

## 3. Descrição do projeto

O Limp Ecologic é um sistema desenvolvido para auxiliar na organização das atividades de uma empresa de higienização de estofados, com foco no gerenciamento de clientes e agendamentos.

O projeto é dividido em duas partes: um backend desenvolvido com Kotlin e Spring Boot e um frontend multiplataforma desenvolvido com Kotlin Multiplatform e Compose Multiplatform.

O backend é responsável pela lógica de negócio, pelo acesso ao banco de dados, pelo gerenciamento de clientes e agendamentos e pela autenticação. O frontend contém um menu e formulários para cadastro de clientes e agendamentos.

Na versão atual, os formulários do frontend realizam validações básicas e exibem mensagens na interface, mas ainda não persistem os dados no backend.

## 4. Tecnologias utilizadas

### Backend
- Kotlin
- Spring Boot
- Spring Data JPA
- Spring Security
- JWT
- MySQL
- Gradle

### Frontend
- Kotlin Multiplatform
- Compose Multiplatform
- Gradle

### Ferramentas
- IntelliJ IDEA
- JDK 17
- Git e GitHub

## 5. Estrutura do projeto

O repositório está organizado da seguinte forma:

- `backend/`: código-fonte e configurações da aplicação Spring Boot.
- `frontend/`: projeto Kotlin Multiplatform.
- `frontend/shared/`: código compartilhado e interfaces da aplicação.
- `frontend/androidApp/`: módulo da aplicação Android.
- `frontend/iosApp/`: estrutura da aplicação iOS.
- `frontend/desktopApp/`: módulo da aplicação Desktop.
- `frontend/webApp/`: módulo da aplicação Web.

## 6. Pré-requisitos

Para configurar e executar o projeto, são necessários:

- IntelliJ IDEA.
- JDK 17.
- MySQL Server.
- Git.
- Gradle Wrapper, incluído na estrutura do projeto.

A execução de cada plataforma do frontend pode exigir ferramentas adicionais específicas.

## 7. Instalação do projeto

### 7.1. Clonar o repositório

Abra um terminal e execute:

```bash
git clone https://github.com/CastroGitHub/limp-ecologic-kotlin.git
cd limp-ecologic-kotlin
```

Abra a pasta do projeto no IntelliJ IDEA e aguarde a sincronização dos módulos Gradle.

## 8. Configuração e execução do backend

### 8.1. Configurar o banco de dados

1. Inicie o MySQL Server.
2. Crie um banco de dados chamado `le_kotlin`.
3. Configure as credenciais de acesso ao banco conforme as propriedades da aplicação.

Exemplo de criação do banco:

```sql
CREATE DATABASE le_kotlin;
```

Configure a variável de ambiente `DB_PASSWORD` com a senha do usuário MySQL utilizado pela aplicação.

Configure também a variável `JWT_SECRET`, necessária para a assinatura dos tokens de autenticação. Utilize um valor secreto adequado e não publique senhas ou chaves reais no repositório.

### 8.2. Executar o backend

Abra um terminal na pasta `backend`:

```bash
cd backend
```

No Windows, execute a aplicação com o Gradle Wrapper:

```powershell
.\gradlew.bat bootRun
```

Alternativamente, abra o módulo `backend` no IntelliJ IDEA, aguarde a sincronização do Gradle e execute a classe principal da aplicação Spring Boot.

O backend utiliza, por padrão, o endereço local:

`http://localhost:8080`

As rotas da API devem ser acessadas conforme os endpoints implementados no projeto. As rotas protegidas exigem autenticação quando aplicável.

## 9. Configuração e execução do frontend

### 9.1. Abrir o projeto

1. Abra a pasta `frontend` no IntelliJ IDEA ou abra o projeto completo e selecione o módulo correspondente.
2. Aguarde a sincronização do Gradle.
3. Selecione a configuração de execução da plataforma desejada.

### 9.2. Executar a versão Web

No Windows, abra um terminal na pasta `frontend` e execute:

```powershell
.\gradlew.bat :webApp:wasmJsBrowserDevelopmentRun
```

A tarefa inicia a versão Web em modo de desenvolvimento e abre o navegador, conforme a configuração do projeto.

### 9.3. Preparar a versão Android

Para compilar a aplicação Android, execute:

```powershell
.\gradlew.bat :androidApp:assembleDebug
```

Para executar a aplicação, utilize a configuração Android do IntelliJ IDEA com um emulador ou dispositivo compatível.

### 9.4. Desktop e iOS

A execução do Desktop depende da configuração de execução correspondente no IntelliJ IDEA.

Para o iOS, são necessárias as ferramentas compatíveis com o desenvolvimento Apple, incluindo macOS e Xcode para a execução no simulador oficial.

A existência dos módulos no projeto não significa, por si só, que todas as plataformas tenham sido executadas e testadas.

## 10. Funcionalidades implementadas

### Backend
- Gerenciamento de clientes.
- Gerenciamento de agendamentos.
- Persistência de dados com Spring Data JPA e MySQL.
- Busca parcial de registros.
- Cadastro e autenticação de usuários com JWT.
- Proteção de rotas e validação de dados.

### Frontend
- Menu principal.
- Formulário de clientes.
- Formulário de agendamentos.
- Campos para informações de contato, endereço e dados do agendamento.
- Validações básicas e mensagens de retorno na interface.

**Observação:** na versão atual, os formulários do frontend são demonstrativos e não enviam os dados para persistência no backend.

## 11. Considerações finais

O projeto Limp Ecologic foi desenvolvido com o objetivo de aplicar os conceitos de desenvolvimento backend com Kotlin e Spring Boot e de desenvolvimento de interfaces multiplataforma com Kotlin Multiplatform e Compose Multiplatform.

A estrutura foi organizada em módulos separados para facilitar a manutenção e a evolução do sistema.
