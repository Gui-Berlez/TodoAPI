# TodoAPI 📝

API REST para gerenciamento de tarefas com autenticação JWT, desenvolvida com Spring Boot.

## 🚀 Deploy

API disponível em: https://todoapi-production-751e.up.railway.app

Swagger: https://todoapi-production-751e.up.railway.app/swagger-ui/index.html

## 🛠️ Tecnologias

- Java 17
- Spring Boot 4.0.3
- Spring Security + JWT
- Spring Data JPA + Hibernate
- MySQL
- BCrypt
- Swagger/OpenAPI
- Docker
- Railway (deploy)

## ⚙️ Funcionalidades

- Cadastro e autenticação de usuários com JWT
- Senhas criptografadas com BCrypt
- CRUD completo de tarefas
- Endpoints protegidos por token
- Validação de campos
- Tratamento de erros padronizado
- Documentação via Swagger

## 📋 Endpoints

### Autenticação
| Método | Endpoint | Descrição |
|--------|----------|-----------|
| POST | /auth/register | Cadastrar usuário |
| POST | /auth/login | Login e geração de token |

### Tarefas (requer token)
| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | /tasks | Listar tarefas |
| GET | /tasks/{id} | Buscar por ID |
| POST | /tasks | Criar tarefa |
| PUT | /tasks/{id} | Atualizar tarefa |
| DELETE | /tasks/{id} | Deletar tarefa |

### Usuários (requer token)
| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | /users | Listar usuários |
| GET | /users/{id} | Buscar por ID |
| PUT | /users/{id} | Atualizar usuário |
| DELETE | /users/{id} | Deletar usuário |

## 🔐 Como autenticar

1. Cadastre um usuário em `POST /auth/register`
2. Faça login em `POST /auth/login` e copie o token
3. Nas requisições protegidas, adicione o header:
```
Authorization: Bearer {seu_token}
```

## 🐳 Rodando localmente com Docker

```bash
# Clone o repositório
git clone https://github.com/Gui-Berlez/TodoAPI.git

# Entre na pasta
cd TodoAPI

# Suba os containers
docker-compose up --build
```

A API estará disponível em `http://localhost:8080`

## 🧪 Testes

```bash
cd todoapi
./mvnw test
```

## 👨‍💻 Autor

**Guilherme Jacob Berlez**

[![LinkedIn](https://img.shields.io/badge/LinkedIn-0077B5?style=flat&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/guilherme-berlez/)
[![GitHub](https://img.shields.io/badge/GitHub-100000?style=flat&logo=github&logoColor=white)](https://github.com/Gui-Berlez)
