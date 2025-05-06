# language: pt

Funcionalidade: Cadastro de agendamento
  Como usuário da API
  Quero cadastrar agendamentos
  Cenário: Cadastro bem-sucedido do agendamento
    Dado que eu tenha os seguintes dados para agendamento:
      | campo             | valor         |
      | dtAgendamento     | 2025-05-02    |
      | statusAgendamento | CONCLUIDO     |
      | rota              | 1             |
    Quando eu enviar a requisição para o endpoint "/agendamento" de cadastro de agendamentos
    Então o status code da resposta de agendamento deve ser 201
    E que o arquivo de contrato de agendamento esperado é o "Cadastro bem-sucedido do agendamento"
    Então a resposta da requisição de agendamento deve estar em conformidade com o contrato selecionado


  @padrão
  Cenário: Cadastro de agendamento sem sucesso ao passar o campos inválidos
    Dado que eu tenha os seguintes dados para agendamento:
      | campo             | valor        |
      | dtAgendamento     | 2025-05-02   |
      | statusAgendamento | TESTE        |
      | rota              | 2            |
    Quando eu enviar a requisição para o endpoint "/agendamento" de cadastro de agendamentos
    Então  o status code da resposta de agendamento deve ser 400
    E o corpo da resposta de agendamento deve conter a mensagem "Preencha todos os campos obrigatórios."


  Cenário: Listar todos os agendamentos
    Quando eu enviar uma requisição GET para o endpoint "/agendamento"
    Então o status code da resposta de agendamento deve ser 200
    E a resposta deve conter uma lista de agendamentos

#  Cenário: Atualizar um agendamento existente
#    Dado que eu tenha um agendamento cadastrado com os seguintes dados:
#      | campo | valor              |
#      | nome  | João da Silva      |
#      | email | joao@teste.com     |
#      | senha | 12345678           |
#      | role  | USER               |
#    E que eu tenha os seguintes dados atualizados:
#      | campo | valor                |
#      | nome  | João Atualizado      |
#      | email | joaoatualizado@teste.com |
#      | senha | 87654321             |
#      | role  | ADMIN                |
#    Quando eu enviar uma requisição PUT para o endpoint "/agendamento/{id}" com os dados atualizados
#    Então o status code da resposta deve ser 200
#    E o nome retornado deve ser "João Atualizado"
#
#
#  Cenário: Excluir um agendamento existente
#    Dado que eu tenha um agendamento cadastrado com os seguintes dados:
#      | campo | valor         |
#      | nome  | Maria da Luz  |
#      | email | maria@teste.com |
#      | senha | 12345678      |
#      | role  | USER          |
#    Quando eu enviar uma requisição DELETE para o endpoint "/agendamento/{id}"
#    Então o status code da resposta deve ser 204
#    E o agendamento não deve mais existir na listagem