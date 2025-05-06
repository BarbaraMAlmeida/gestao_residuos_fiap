# language: pt
Funcionalidade: Cadastro de novo recipiente
  Como usuário da API
  Quero cadastrar um novo recipiente
  Para que o registro seja salvo corretamente no sistema
  @padrão
  Cenário: Cadastro bem-sucedido de recepiente
    Dado que eu tenha os seguintes dados do recipiente:
      | campo                | valor               |
      | maxCapacidade        | 90                  |
      | atualNivel           | 60                  |
      | status               | CAPACIDADE_ATINGIDA |
      | ultimaAtualizacao    | 2024-10-01          |
      | latitude             | 29                  |
      | longitude            | 22                  |

    Quando eu enviar a requisição para o endpoint "/recipiente" de cadastro de recipientes
    Entao o status code da resposta de recipiente deve ser 201
    E que o arquivo de contrato de recipiente esperado é o "Cadastro bem-sucedido da Recipiente"
    Então a resposta da requisição de recipiente deve estar em conformidade com o contrato selecionado
  @padrão
  Cenário: Cadastro de recipiente com dados inválidos
    Dado que eu tenha os seguintes dados do recipiente:
      | campo                | valor               |
      | maxCapacidade        | 90                  |
      | atualNivel           | 60                  |
      | status               | INVALIDO            |
      | ultimaAtualizacao    | 2024-10-01          |
      | latitude             | 29                  |
      | longitude            | 22                  |
    Quando eu enviar a requisição para o endpoint "/recipiente" de cadastro de recipientes
    Entao o status code da resposta de recipiente deve ser 400
    E o corpo da resposta de erro da api de recipiente deve retornar a mensagem "Preencha todos os campos obrigatórios."
  @padrão
  Cenário: Listar todos os recipientes
    Quando eu enviar uma requisição GET para o endpoint de recipiente "/recipiente"
    Então o status code da resposta de lista de recipiente deve ser 200
    E a resposta deve conter uma lista de recipientes
