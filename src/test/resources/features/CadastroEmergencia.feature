# language: pt
Funcionalidade: Cadastro de nova emergencia
  Como usuário da API
  Quero cadastrar uma nova emergencia
  Para que o registro seja salvo corretamente no sistema

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


#  Cenario: listar todas as emergencias
#    Quando eu enviar a requisição para o endpoint "/emergencia"
#    Entao o status code da resposta deve ser 200
#    E o corpo da reposta de erro da api deve retornar a mensagem "Lista de emergencias"