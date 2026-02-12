# Sistema de Login - JavaFX

Um sistema de autenticação de usuários construído com **JavaFX** e **SQLite**, com suporte a diferentes tipos de usuários (ADMIN e USER) e senhas criptografadas com hash e salt.

## 📋 Descrição

Esta aplicação é um sistema de login completo que fornece:

- ✅ **Autenticação de Usuários**: Login seguro com criptografia de senhas
- ✅ **Registro de Usuários**: Cadastro de novos usuários com validação
- ✅ **Sistema de Papéis**: Suporte para usuários ADMIN e USER
- ✅ **Interface Gráfica**: Interface moderna com JavaFX e FXML
- ✅ **Banco de Dados Local**: SQLite para persistência de dados
- ✅ **Segurança**: Hashing de senhas com salt único por usuário
- ✅ **Gerenciamento de Sessão**: Controle de sessão de usuário logado

## 🛠️ Tecnologias e Dependências

### Tecnologias Principais
- **Java 17**: Linguagem de programação
- **JavaFX 21**: Framework para interface gráfica
- **SQLite 3.46.0**: Banco de dados relacional
- **Maven**: Gerenciador de dependências e build
- **SLF4J 2.0.13**: Framework de logging

### Dependências Principais (POM.xml)
- `javafx-controls`: Componentes JavaFX para UI
- `javafx-fxml`: Suporte a arquivos FXML para design de interface
- `sqlite-jdbc`: Driver JDBC para SQLite
- `slf4j-api` e `slf4j-simple`: Logging da aplicação

## 📁 Estrutura do Projeto

```
sistem-login/
├── pom.xml                              # Configuração Maven
├── README.md                            # Este arquivo
├── src/
│   └── main/
│       ├── java/com/seuapp/
│       │   ├── App.java                 # Classe principal - ponto de entrada
│       │   ├── controller/
│       │   │   └── AuthController.java  # Controladora de autenticação
│       │   ├── model/
│       │   │   ├── User.java            # Entidade usuário
│       │   │   └── UserDAO.java         # Data Access Object para usuários
│       │   ├── util/
│       │   │   ├── DatabaseManager.java # Gerenciador de conexões DB
│       │   │   ├── FXMLUtils.java       # Utilitários para carregar FXML
│       │   │   ├── PasswordUtils.java   # Utilitários para criptografia
│       │   │   └── SessionManager.java  # Gerenciador de sessão
│       │   └── view/
│       │       ├── LoginViewController.java       # Controller da tela de login
│       │       ├── MainViewController.java        # Controller da tela principal
│       │       └── RegisterViewController.java    # Controller da tela de registro
│       └── resources/
│           ├── css/                     # Estilos CSS
│           ├── database/
│           │   └── schema.sql           # Schema do banco de dados
│           ├── fxml/
│           │   ├── LoginView.fxml       # Interface de login
│           │   ├── MainView.fxml        # Interface principal
│           │   └── RegisterView.fxml    # Interface de registro
│           └── img/                     # Imagens e recursos
└── target/                              # Arquivos compilados (gerado pelo Maven)
```

## 🚀 Como Instalar e Executar

### Pré-requisitos
- **Java 17** ou superior instalado
- **Maven** 3.6+ instalado
- **Git** (opcional, para clonar o repositório)

### Passos de Instalação

1. **Clone ou acesse o repositório:**
   ```bash
   cd sistem-login
   ```

2. **Compile o projeto com Maven:**
   ```bash
   mvn clean compile
   ```

3. **Empacote a aplicação:**
   ```bash
   mvn package
   ```

4. **Execute a aplicação:**
   ```bash
   mvn javafx:run
   ```

   Ou execute o JAR gerado:
   ```bash
   java -jar target/sistema-login-1.0.0.jar
   ```

## 🔐 Banco de Dados

### Estrutura das Tabelas

A aplicação utiliza uma tabela `usuarios` com a seguinte estrutura:

```sql
CREATE TABLE usuarios (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    username VARCHAR(50) UNIQUE NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    salt VARCHAR(255) NOT NULL,
    role TEXT CHECK(role IN ('ADMIN', 'USER')) NOT NULL DEFAULT 'USER',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

### Campos da Tabela
- **id**: Identificador único (chave primária)
- **username**: Nome de usuário (único)
- **password_hash**: Hash criptografado da senha
- **salt**: Valor aleatório usado na criptografia (único por usuário)
- **role**: Tipo de usuário (ADMIN ou USER)
- **created_at**: Data e hora de criação da conta

### Usuário Padrão
Ao iniciar a aplicação pela primeira vez, um usuário administrador padrão é criado:
- **Username**: `admin`
- **Password**: `admin`
- **Role**: `ADMIN`

⚠️ **Importante**: Altere a senha do administrador após o primeiro acesso!

## 🔐 Segurança

### Criptografia de Senhas
A aplicação implementa um sistema de segurança robusto:

1. **Salt Aleatório**: Cada usuário recebe um salt único gerado aleatorriamente
2. **Hashing**: As senhas são criptografadas usando algoritmo seguro (SHA-256 ou similar)
3. **Sem Armazenamento em Texto Plano**: Apenas o hash é armazenado no banco de dados

A classe `PasswordUtils` é responsável por:
- Gerar salts aleatórios
- Fazer hash das senhas com salt
- Validar senhas no login

## 📱 Funcionalidades Principais

### 1. Tela de Login (`LoginView.fxml`)
- Login com username e password
- Validação de credenciais contra o banco de dados
- Redirecionamento para tela principal após login bem-sucedido
- Link para tela de registro

### 2. Tela de Registro (`RegisterView.fxml`)
- Cadastro de novo usuário
- Validação de dados (username único, senha mínima, etc.)
- Criptografia de senha com salt
- Redirecionamento para tela de login após sucesso

### 3. Tela Principal (`MainView.fxml`)
- Exibição de informações do usuário logado
- Logout com encerramento de sessão
- Opções diferenciadas por tipo de usuário (ADMIN vs USER)

## 📦 Classes Principais

### `App.java`
Classe principal que:
- Inicializa a aplicação JavaFX
- Conecta ao banco de dados na inicialização
- Carrega a tela de login inicial
- Encerra conexões ao fechar a aplicação

### `User.java`
Modelo de dados que representa um usuário com:
- ID, username, hash de senha, salt
- Tipo de usuário (role)
- Data de criação da conta

### `UserDAO.java`
Data Access Object que gerencia operações com usuários:
- Inserir novo usuário
- Buscar usuário por username
- Validar credenciais
- Atualizar dados do usuário

### `DatabaseManager.java`
Gerenciador centralizado de conexões:
- Mantém pool de conexões
- Inicializa tabelas automaticamente
- Cria usuário admin padrão na primeira execução

### `SessionManager.java`
Controla a sessão do usuário logado:
- Armazena dados do usuário atual
- Fornece métodos para login/logout
- Verifica se usuário está autenticado

### `AuthController.java`
Controladora de autenticação que:
- Processa login de usuários
- Processa registro de novos usuários
- Valida dados de entrada

### Controllers de View
- `LoginViewController`: Controla lógica da tela de login
- `RegisterViewController`: Controla lógica do registro
- `MainViewController`: Controla lógica da tela principal

## 🔧 Configuração

### Alterando o Banco de Dados
Para usar um banco diferente, edite `DatabaseManager.java`:
```java
private static final String DB_URL = "jdbc:sqlite:seu_banco.db";
```

### Modificando Estilos CSS
Os estilos podem ser customizados em `src/main/resources/css/`

### Adicionando Novas Views
1. Crie arquivo `.fxml` em `src/main/resources/fxml/`
2. Crie controller correspondente em `src/main/java/com/seuapp/view/`
3. Use `FXMLUtils` para carregar a nova view

## 🐛 Troubleshooting

### Erro: "Driver SQLite não encontrado"
- Verifique se as dependências Maven estão instaladas
- Execute: `mvn clean install`

### Erro: "Erro ao carregar arquivo FXML"
- Verifique se os arquivos `.fxml` estão em `src/main/resources/fxml/`
- Reconstrua o projeto: `mvn clean compile`

### Aplicação não inicia
- Verifique se Java 17+ está instalado: `java -version`
- Verifique logs de erro no console
- Tente limpar cache: `mvn clean`

## 📝 Logs

A aplicação utiliza **SLF4J** para logging. Os logs são exibidos no console e podem ser configurados em `src/main/resources/logback.xml` (se usar Logback).

## 👤 Autor

Sistema de Login - Desenvolvido como projeto educacional.

## ❓ Dúvidas e Suporte

Para dúvidas sobre o código:
1. Consulte os comentários no código-fonte
2. Verifique os logs da aplicação
3. Revise a estrutura do banco de dados em `schema.sql`

---

**Última atualização**: Fevereiro de 2026
