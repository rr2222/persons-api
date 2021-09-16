<h2>Desenvolvendo um sistema de gerenciamento de pessoas em API REST com Spring Boot</h2>

Sistema para o gerenciamento de pessoas de uma empresa através de uma API REST, criada com o Spring Boot.


Após executar o comando acima, basta apenas abrir o seguinte endereço e visualizar a execução do projeto:

```
http://localhost:8080/api/v1/people
```
GET - Retorna todas as pessoas cadastradas
```
http://localhost:8080/api/v1/people
```

GET - Retorna a pessoa com ID especificado.
Ex: http://localhost:8080/api/v1/people/1
```
http://localhost:8080/api/v1/people/{id}
```

POST - Cria uma pessoa. Parametros JSON para requisição abaixo.
```
http://localhost:8080/api/v1/people
```
JSON
```
{
    "firstName": "Dio",
    "lastName": "Brando",
    "cpf": "000.000.000-00",
    "birthDate" : "07-04-1886",
    "phones": [
        {
            "type": "MOBILE",
            "number": "(11)999999999"
        }
    ]
}
```
DELETE - Deleta uma pessoa com o ID especificado
Ex: http://localhost:8080/api/v1/people/1
```
http://localhost:8080/api/v1/people/{id}
```

PUT - Atualiza uma pessoa com o ID especificado. Necessário especificar o ID da pessoa e o ID de seu telefone no JSON
Ex: http://localhost:8080/api/v1/people/1
```
http://localhost:8080/api/v1/people/{id}
```
JSON
```
{
    "id": 1,
    "firstName": "Dio",
    "lastName": "Brando",
    "cpf": "000.000.000-00",
    "birthDate" : "07-04-1886",
    "phones": [
        {
            "id": 1,
            "type": "MOBILE",
            "number": "(11)999999999"
        }
    ]
}

```


* [Site oficial do Spring](https://spring.io/)
* [Site oficial do Spring Initialzr, para setup do projeto](https://start.spring.io/)
* [Site oficial do Heroku](https://www.heroku.com/)
* [Site oficial do GIT](https://git-scm.com/)
* [Site oficial do GitHub](http://github.com/)
* [Documentação oficial do Lombok](https://projectlombok.org/)
* [Documentação oficial do Map Struct](https://mapstruct.org/)
* [Referência para o padrão arquitetural REST](https://restfulapi.net/)




