# language: pt
Funcionalidade: Cadastro de nova rota
  Como usuário da API
  Quero cadastrar uma nova rota
  Para que o registro seja salvo corretamente no sistema

  Cenário: Cadastro bem-sucedido de rota
    Dado que eu tenha os seguintes dados da rota:
      | campo          | valor        |
      | dtRota         | 2024-07-01   |
      | caminhao       | 2            |
      | recipiente     | 1            |

    Quando eu enviar a requisição para o endpoint "/rotas" de cadastro de rotas
    Entao o status code da resposta de rotas deve ser 201
    E que o arquivo de contrato de rota esperado é o "Cadastro bem-sucedido da Rota"
    Então a resposta da requisição da rota deve estar em conformidade com o contrato selecionado

  Cenário: Cadastro de rotas com dados inválidos
    Dado que eu tenha os seguintes dados da rota:
      | campo          | valor        |
      | dtRota         | 2024-07-01   |
      | caminhao       | 10           |
      | recipiente     | 1            |

  Cenário: Deve ser possivel deletar uma rota
    Dado que eu recupere o ID da rota criada no contexto
    Quando eu enviar a requisição com o ID para o endpoint "/rotas" de deleção de rotas
    Entao o status code da resposta de rota deve ser 200
