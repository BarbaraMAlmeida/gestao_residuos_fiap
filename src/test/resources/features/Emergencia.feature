# language: pt
Funcionalidade: Cadastro de nova emergencia
  Como usuário da API
  Quero cadastrar uma nova emergencia
  Para que o registro seja salvo corretamente no sistema
  @padrão
  Cenário: Cadastro bem-sucedido de emergencia
    Dado que eu tenha os seguintes dados da emergencia:
      | campo          | valor        |
      | dtEmergencia   | 2023-03-01   |
      | status         | COLETADA     |
      | descricao      | teste        |
      | recipiente     | 1            |
      | caminhao       | 2            |
    Quando eu enviar a requisição para o endpoint "/emergencia" de cadastro de emergencias
    Entao o status code da resposta de emergencia deve ser 201
    E que o arquivo de contrato de emergencia esperado é o "Cadastro bem-sucedido da Emergencia"
    Então a resposta da requisição de emergencia deve estar em conformidade com o contrato selecionado
  @padrão
    Cenário: Cadastro de emergencia com dados inválidos
    Dado que eu tenha os seguintes dados da emergencia:
      | campo          | valor        |
      | dtEmergencia   | 2023-03-01   |
      | status         | INVALIDO     |
      | descricao      | teste        |
      | recipiente     | 1            |
      | caminhao       | 2            |
    Quando eu enviar a requisição para o endpoint "/emergencia" de cadastro de emergencias
    Entao o status code da resposta de emergencia deve ser 400
    E o corpo da reposta de erro da api deve retornar a mensagem "Preencha todos os campos obrigatórios."

  @padrão
  Cenário: Listar todos as emergencias
    Quando eu enviar uma requisição GET para o endpoint de emergencia "/emergencia"
    Então o status code da resposta de emergencia deve ser 200
    E a resposta deve conter uma lista de emergencias
