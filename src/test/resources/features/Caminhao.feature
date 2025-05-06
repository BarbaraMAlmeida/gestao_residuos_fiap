# language: pt

Funcionalidade: Cadastro de caminhao
  Como usuário da API
  Quero cadastrar caminhoes
  Cenário: Cadastro bem-sucedido do caminhao
    Dado que eu tenha os seguintes dados para caminhao:
      | campo        | valor   |
      | placa        | 123456  |
      | capacidade   | 200     |
    Quando eu enviar a requisição para o endpoint "/caminhao" de cadastro de caminhoes
    Então o status code da resposta de caminhao deve ser 201
    E que o arquivo de contrato de caminhao esperado é o "Cadastro bem-sucedido do caminhao"
    Então a resposta da requisição de caminhao deve estar em conformidade com o contrato selecionado


  @padrão
  Cenário: Cadastro de caminhao sem sucesso ao passar o campos inválidos
    Dado que eu tenha os seguintes dados para caminhao:
      | campo        | valor     |
      | placa        | 12555456  |
      | capacidade   | 200       |
    Quando eu enviar a requisição para o endpoint "/caminhao" de cadastro de caminhoes
    Então  o status code da resposta de caminhao deve ser 400
    E o corpo da resposta de caminhao deve conter a mensagem "A placa deve conter no máximo 7 caracteres"


  Cenário: Listar todos os caminhaos
    Quando eu enviar uma requisição GET para o endpoint de caminhao "/caminhao"
    Então o status code da resposta de caminhao deve ser 200
    E a resposta deve conter uma lista de caminhaos
