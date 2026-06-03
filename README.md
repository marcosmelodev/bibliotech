# 📚 Bibliotech

Sistema de gerenciamento de biblioteca desenvolvido com **Spring Boot**, com interface gráfica web suporte a perfis de acesso: **Bibliotecário**, **Aluno** e **Professor**.

---

## 🖥️ Interface

A interface é servida pelo próprio backend em `http://localhost:8080` e não exige login. O acesso é feito por seleção de perfil:

| Perfil | Permissões |
|---|---|
| 🏛️ **Bibliotecário** | Inserir, buscar, atualizar e deletar livros, alunos, professores, bibliotecários e empréstimos |
| 🎓 **Aluno** | Buscar livros disponíveis, realizar empréstimo (limite: 3 livros) e devolver |
| 👨‍🏫 **Professor** | Buscar livros disponíveis, realizar empréstimo (limite: 5 livros) e devolver |

---

## 🛠️ Tecnologias

- Java 22
- Spring Boot 4
- Spring Data JPA / Hibernate
- H2 (testes / desenvolvimento)
- MySQL (produção / persistência)

---

## ⚙️ Configuração de Banco de Dados

O projeto possui dois perfis configurados em `src/main/resources/`:

### 🧪 Perfil `test` — H2 (em memória)

Usado para desenvolvimento e testes locais. **Não requer instalação de banco de dados.**

```properties
# application-test.properties
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.username=sa
spring.datasource.password=
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
```

Console H2 disponível em: `http://localhost:5000/h2-console`

### 🗄️ Perfil `dev` — MySQL (persistência)

Usado em produção. Requer MySQL instalado e variáveis de ambiente configuradas.

```properties
# application-dev.properties
spring.datasource.url=jdbc:mysql://localhost:3306/bibliotech?createDatabaseIfNotExist=true&serverTimezone=America/Sao_Paulo
spring.datasource.username=${DB_USER}
spring.datasource.password=${DB_PASSWORD}
spring.jpa.hibernate.ddl-auto=update
```

Configure as variáveis de ambiente antes de rodar:

```bash
export DB_USER=seu_usuario
export DB_PASSWORD=sua_senha
```

Para alternar entre os perfis, edite `application.properties`:

```properties
# Testes (H2)
spring.profiles.active=test

# Produção (MySQL)
spring.profiles.active=dev
```

---

## 🚀 Como Rodar Localmente

### Pré-requisitos

- [Java 19+](https://adoptium.net)
- MySQL (apenas se usar perfil `dev`)

### 1. Clone o repositório

```bash
git clone https://github.com/marcosmelodev/bibliotech.git
cd bibliotech
```

### 2. Execute com H2 (sem banco externo)

```bash
# Linux / Mac
./mvnw spring-boot:run

# Windows
mvnw.cmd spring-boot:run
```

### 3. Execute com MySQL

```bash
export DB_USER=root
export DB_PASSWORD=sua_senha

./mvnw spring-boot:run -Dspring-boot.run.profiles=dev
```

### 4. Acesse no navegador

```
http://localhost:5000
```

---

## 📡 Endpoints da API REST

### Livros — `/livros`
| Método | Rota | Descrição |
|---|---|---|
| GET | `/livros` | Listar todos |
| GET | `/livros/{id}` | Buscar por ID |
| GET | `/livros/autor/{autor}` | Buscar por autor |
| GET | `/livros/livros/{titulo}` | Buscar por título |
| POST | `/livros` | Inserir livro |
| PUT | `/livros/{id}` | Atualizar livro |
| DELETE | `/livros/{id}` | Deletar livro |

### Alunos — `/alunos`
| Método | Rota | Descrição |
|---|---|---|
| GET | `/alunos` | Listar todos |
| GET | `/alunos/{id}` | Buscar por ID |
| POST | `/alunos` | Inserir aluno |
| PUT | `/alunos/{id}` | Atualizar aluno |
| DELETE | `/alunos/{id}` | Deletar aluno |

### Professores — `/professores`
| Método | Rota | Descrição |
|---|---|---|
| GET | `/professores` | Listar todos |
| GET | `/professores/{id}` | Buscar por ID |
| POST | `/professores` | Inserir professor |
| PUT | `/professores/{id}` | Atualizar professor |
| DELETE | `/professores/{id}` | Deletar professor |

### Bibliotecários — `/bibliotecarios`
| Método | Rota | Descrição |
|---|---|---|
| GET | `/bibliotecarios` | Listar todos |
| GET | `/bibliotecarios/{id}` | Buscar por ID |
| POST | `/bibliotecarios` | Inserir bibliotecário |
| DELETE | `/bibliotecarios/{id}` | Deletar bibliotecário |

### Empréstimos — `/emprestimos`
| Método | Rota | Descrição |
|---|---|---|
| GET | `/emprestimos` | Listar todos |
| GET | `/emprestimos/{id}` | Buscar por ID |
| GET | `/emprestimos/atrasados` | Listar atrasados |
| POST | `/emprestimos` | Criar empréstimo |
| PUT | `/emprestimos/{id}` | Atualizar empréstimo |
| PUT | `/emprestimos/{id}/devolucao` | Registrar devolução |

---

## 🔄 Funcionalidades Implementadas

- ✅ CRUD completo para todas as entidades
- ✅ Controle de estoque de livros (decrementa ao emprestar, restaura ao devolver)
- ✅ Validação de limite de livros por perfil (aluno: 3, professor: 5)
- ✅ Status automático do livro (`DISPONIVEL` / `EMPRESTADO`)
- ✅ Detecção automática de empréstimos atrasados via job agendado (executa diariamente à meia-noite)
- ✅ Interface gráfica integrada sem necessidade de login

---

## 🗂️ Estrutura do Projeto

```
bibliotech/
├── src/main/java/br/edu/udf/bibliotech/
│   ├── BibliotechApplication.java
│   ├── config/
│   │   └── TestConfig.java          # Dados de seed para perfil test
│   ├── controller/                  # Endpoints REST
│   ├── entities/                    # Entidades JPA
│   │   └── enums/
│   ├── repositories/                # Spring Data JPA
│   └── service/                     # Regras de negócio
│       ├── AgendadorService.java    # Job de detecção de atrasos
│       └── EmprestimoService.java   # Lógica de empréstimo e estoque
├── src/main/resources/
│   ├── application.properties
│   ├── application-dev.properties   # MySQL
│   ├── application-test.properties  # H2
│   └── static/
│       └── index.html               # Frontend
└── pom.xml
```

---

## 👨‍💻 Autor

Desenvolvido por:

**Marcos Melo** — [@marcosmelodev](https://github.com/marcosmelodev)

**Valmisonia Pereira**


> 🎓 Informações Acadêmicas

Projeto acadêmico desenvolvido para a disciplina de Programação Orientada a Objetos
ministrada pelo  **Prof. MSc. Gleidson Porto Batista
Centro Universitário UDF**
