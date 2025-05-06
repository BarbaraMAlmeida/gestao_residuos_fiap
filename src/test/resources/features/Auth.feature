# language: pt

Funcionalidade: Cadastro e autenticação de usuários
  Como usuário da API
  Quero me cadastrar como usuário
  Para ter acesso as funcionalidades
  @padrão
  Cenário: Cadastro bem-sucedido do usuário
    Dado que eu tenha os seguintes dados para cadastro:
      | campo          | valor                 |
      | nome           | Fernandaaaa Almeida   |
      | email          | feraaa@teste.com      |
      | senha          | 12345678              |
      | role           | USER                  |
    Quando eu enviar a requisição para o endpoint "/auth/register" de cadastro de usuarios
    Então o status code da resposta deve ser 201
    E que o arquivo de contrato esperado é o "Cadastro bem-sucedido do usuario"
    Então a resposta da requisição deve estar em conformidade com o contrato selecionado


  @padrão
  Cenário: Cadastro de usuário sem sucesso ao passar o campos inválidos
    Dado que eu tenha os seguintes dados para cadastro:
      | campo          | valor              |
      | nome           | AAAAAAA Nicolle    |
      | email          | AAAAAAAteste.com   |
      | senha          | 123                |
      | role           | TESTE              |
    Quando eu enviar a requisição para o endpoint "/auth/register" de cadastro de usuarios
    Então o status code da resposta deve ser 400
    E o corpo da resposta deve conter a mensagem "Preencha todos os campos obrigatórios."


  @padrão
  Cenário: Login do usuário
    Dado que eu tenha os seguintes dados para login:
      | campo  | valor             |
      | email  | andre@teste.com   |
      | senha  | 12345678          |
    Quando eu enviar a requisição para o endpoint "/auth/login" de login de usuarios
    Então o status code da resposta deve ser 200
    E que o arquivo de contrato esperado é o "Login bem-sucedido do usuario"
    Então a resposta da requisição deve estar em conformidade com o contrato selecionado


  Cenário: Login com credenciais inválidas
    Dado que eu tenha os seguintes dados para login:
      | campo  | valor             |
      | email  | andre@teste.com   |
      | senha  | senhaerrada       |
    Quando eu enviar a requisição para o endpoint "/auth/login" de login de usuarios
    Então o status code da resposta deve ser 401
