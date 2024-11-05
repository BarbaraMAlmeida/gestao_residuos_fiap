<img width=100% src="https://capsule-render.vercel.app/api?type=waving&color=004357&height=120&section=header"/>

[![Typing SVG](https://readme-typing-svg.herokuapp.com/?color=004357&size=55&center=true&vCenter=true&width=1000&lines=Gestao-Residuos;)](https://git.io/typing-svg)

# Sistema de Gestão de Resíduos
Este projeto foi desenvolvido como parte de uma iniciativa educacional na Faculdade FIAP, com foco na criação de uma solução inovadora para agendamento e 
monitoramento de resíduos de lixo de uma cidade. O objetivo do projeto é focarmos no desenvolvimento de smart cities, trazendo benefícios para a sociedade através da tecnologia. 

## 👀 Visão Geral
Nosso sistema conta com funcionalidades e automações para cadastros e agendamentos de coletas de lixo pela cidade. Todas programadas com um caminhão específico e rotas. 
Além disso, temos a funcionalidade de cadastro de agendamentos de coletas emergênciais. 

## Recomendação de fluxo de teste do sistema

## Visualize nosso diagrama para entender as dependências entre tabelas 
https://www.canva.com/design/DAGUOsXi-48/_h-wh-iOQvesDQcmd7c-LA/edit?utm_content=DAGUOsXi-48&utm_campaign=designshare&utm_medium=link2&utm_source=sharebutton

1) Cadastre-se no sistema -> Você pode fazer login como ADMIN ou USER.
2) Faça login no sistema para receber seu token JWT.
3) Cadastre os Caminhões
4) Cadastre os Recipientes
5) Cadastre as Rotas
6) Cadastre os Agendamentos
7) Cadastre as Emergências

## Permissões dentro do sistema
Os endpoints de login (/auth/login) e registro (/auth/register), são abertos ao público. 
Os endpoints get podem ser acessados por usuários autenticados e autorizados (ADMIN e USER)
Os endpoints post, put e delete só podem ser acessados por usuarios ADMIN autenticados. 

## Dockerização
O sistema foi dockerizado porém não foi publicado. Temos o arquivo Dockerfile no projeto com a referência do .jar
Para criar a imagem e o container siga os passos abaixo:

1) Instale o maven do apache https://maven.apache.org/download.cgi
2) Para criar a imagem rode docker build -t atividade:spring-docker .
3) Agora crie o container com: docker container run -d -p 8080:8080 --name atividade-spring-container atividade:spring-docker
4) Verifique se o container foi inicializado com docker container ls

:tada: :partying_face: CONTAINER INICIALIZADO - OS ENDPOINTS JÁ DEVEM FUNCIONAR

5) Para parar o container docker stop atividade-spring-container
7) Para inicializar novamente o container docker start atividade-spring-container

## 🧑‍💻👩‍💻 Contribuidores
André Mori
Bárbara Almeida
Gabriel Lopez
Jaqueline Otero
Matheus Oliveira




