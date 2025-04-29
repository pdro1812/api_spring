# 🚀 Desafio Técnico: Performance e Análise de Dados via API

## Desafio original: 
https://github.com/codecon-dev/desafio-1-1s-vs-3j/blob/main/README.md

### 📌 Objetivo
Criar uma API em Java com Spring Boot que receba um arquivo JSON contendo 100.000 usuários e forneça endpoints performáticos e bem estruturados para análise de dados.

### ⚙️ Tecnologias Utilizadas
Java 17+
Spring Boot
Maven
RESTful API
JSON
Swagger

### 📥 Endpoints

POST	/users	Recebe e armazena os usuários na memória (simula um banco de dados em memória).

GET	/users/data	Retorna todos os usuários recebidos.

GET	/superusers	Retorna os usuários com score >= 900 e active = true, além do tempo de execução.

GET	/top-countries	Retorna os 5 países com maior número de superusuários.

GET	/team-insights	Agrupa por equipe e retorna: total de membros, líderes, projetos concluídos e porcentagem de conclusão.

GET	/	Health check da aplicação.

### 🧪 Como executar o projeto localmente
Certifique-se de ter o Java 17+ e Maven instalados.

Clone o repositório
```
git clone https://github.com/ 
cd seu-repositorio

Compile o projeto
mvn clean install

Rode a aplicação
mvn spring-boot:run
```
### 📄 Estrutura esperada do JSON
O JSON deve conter uma lista com a seguinte estrutura para cada usuário:
[
  {
    "name": "João Silva",
    "score": 950,
    "active": true,
    "country": "Brazil",
    "equipe": {
      "nome": "Time A",
      "lider": true,
      "projetos": [
        {
          "nome": "Projeto X",
          "concluido": true
        }
      ]
    }
  }
]

### 📊 Retornos com Métricas
As rotas /superusers, /top-countries e /team-insights retornam informações adicionais como:

timestamp: Data e hora da requisição.

execution_time_ms: Tempo de execução da lógica em milissegundos.
