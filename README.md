# Sistema de Gestão de Resíduos FIAP

Este projeto foi desenvolvido como parte de uma iniciativa educacional na Faculdade FIAP, com foco na criação de uma solução para agendamento e monitoramento em tempo real da coleta de resíduos em uma cidade.

## Funcionalidades Principais

- Cadastro e autenticação de usuários (roles: ADMIN e USER)
- Cadastro de Caminhões, Recipientes, Rotas, Agendamentos e Emergências
- Endpoints REST protegidos por JWT
- Pipeline CI/CD via GitHub Actions com build, push e deploy para Azure Container Instances (Dev e Prod)

## Pré-requisitos

- Java 17 ou superior
- Maven 3.6 ou superior
- Git
- Docker (opcional, para execução em container)
- Conta no Docker Hub (para push/pull de imagens)
- CLI do Azure (para deploy em ACI)

## 1. Clonar o repositório

```bash
git clone https://github.com/BarbaraMAlmeida/gestao_residuos_fiap.git
cd gestao_residuos_fiap
```

## 2. Configurar variáveis de ambiente

No arquivo `src/main/resources/application.properties`, `Dockerfile` e `docker-compose.yaml` configure suas credenciais de banco de dados:

```properties
spring.datasource.url=jdbc:<tipo-do-seu-banco>://<host>:<porta>/<nome_do_banco>
spring.datasource.username=<seu_usuario>
spring.datasource.password=<sua_senha>
spring.jpa.hibernate.ddl-auto=update
server.port=8080
```

> **Dica:** para ambiente de produção, edite `application-production.properties` com valores adequados.

## 3. Build e execução local

#### Para iniciar delete todas as tabelas, sequences e dados da flayway_schema_history. 

### 3.1 Com Maven

```bash
./mvnw clean package
java -jar target/gestao-residuos-0.0.1-SNAPSHOT.jar
```

### 3.2 Com Docker

```bash
# Subir a aplicação
docker compose up
```

## 4. Endpoints Disponíveis

### Autenticação

- **POST** `/auth/register` – registrar novo usuário
- **POST** `/auth/login` – obter token JWT

### API de Recursos (JWT Bearer)

- **GET**  `/api/v1/caminhoes`

- **POST** `/api/v1/caminhoes`

- **PUT**  `/api/v1/caminhoes/{id}`

- **DELETE** `/api/v1/caminhoes/{id}`

- **GET**  `/api/v1/recipientes`

- **POST** `/api/v1/recipientes`

- **PUT**  `/api/v1/recipientes/{id}`

- **DELETE** `/api/v1/recipientes/{id}`

- **GET**  `/api/v1/rotas`

- **POST** `/api/v1/rotas`

- **PUT**  `/api/v1/rotas/{id}`

- **DELETE** `/api/v1/rotas/{id}`

- **GET**  `/api/v1/agendamentos`

- **POST** `/api/v1/agendamentos`

- **PUT**  `/api/v1/agendamentos/{id}`

- **DELETE** `/api/v1/agendamentos/{id}`

- **GET**  `/api/v1/emergencias`

- **POST** `/api/v1/emergencias`

- **PUT**  `/api/v1/emergencias/{id}`

- **DELETE** `/api/v1/emergencias/{id}`

> Endpoints GET podem ser usados por usuários autenticados (ADMIN e USER). POST, PUT e DELETE são restritos a usuários com role ADMIN.

## 5. Pipeline CI/CD (GitHub Actions)

O workflow está definido em `.github/workflows/ci-cd.yml` e possui as seguintes etapas:

1. **build-and-push:** build da aplicação, construção das imagens Docker (tags `dev-<run_id>` e `prod-<run_id>`) e push para Docker Hub.
2. **deploy-dev:** deploy da imagem `dev-<run_id>` no Azure Container Instances (RG `rg-gestao-dev`).
3. **deploy-prod:** deploy da imagem `prod-<run_id>` no Azure Container Instances (RG `rg-gestao-prod`), disparado em pushes para `main`.

## 6. Testes Rápidos

Para validar localmente, execute:

```bash
# Registrar usuário
curl -X POST http://localhost:8080/auth/register \
  -H "Content-Type: application/json" \
  -d '{"nome":"Teste","cpf":"12345678901","rg":"MG1234567","dataNascimento":"1990-01-01","orgaoExpedicao":"SSP","imagem":{"fileName":"test.jpg","base64":"..."},"telefone":"11900000000"}'

# Login
curl -X POST http://localhost:8080/auth/login \
  -H "Content-Type: application/json" \
  -d '{"cpf":"12345678901","password":"<senha>"}'

# Listar caminhões
curl http://localhost:8080/api/v1/caminhoes \
  -H "Authorization: Bearer <TOKEN_JWT>"
```

## 7. Contribuição

**Autores:** André Mori, Bárbara Almeida, Gabriel Lopez, Jaqueline Otero, Matheus Oliveira


## 8. Link Repositório

**Link:** https://github.com/BarbaraMAlmeida/gestao_residuos_fiap

## 9. Executando os Testes com os Runners

## Pré-requisitos
**Java:** Certifique-se de que o Java JDK está instalado (versão 8 ou superior).
**Maven:** Verifique se o Maven está configurado no ambiente.
**IDE:** Utilize uma IDE como **IntelliJ IDEA** para facilitar a execução dos testes.
Dependências: Certifique-se de que as dependências do Cucumber estão configuradas no **pom.xml.**

**Estrutura dos Runners**
Os Runners estão localizados no pacote **src/test/java/runner** e são responsáveis por executar os testes de cada funcionalidade. Cada Runner está associado a um arquivo .feature específico.

## Runners Disponíveis
**EmergenciaRunner:** Executa os testes do arquivo Emergencia.feature.
**AgendamentoRunner:** Executa os testes do arquivo Agendamento.feature.
**RotasRunner:** Executa os testes do arquivo Rotas.feature.
**AuthRunner:** Executa os testes do arquivo Auth.feature.
**CaminhaoRunner:** Executa os testes do arquivo Caminhao.feature.
**RecipienteRunner:** (se necessário, crie um Runner para Recipiente.feature).

## GUIA: Executando os Testes
1. Via IDE
2. Abra o projeto na IntelliJ IDEA.
3. Navegue até o pacote runner em src/test/java.
4. Clique com o botão direito no Runner desejado (ex.: EmergenciaRunner).
5. Selecione a opção Run 'EmergenciaRunner'.
6. Verifique os resultados no console e nos relatórios gerados.

