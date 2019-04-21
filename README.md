# ejb-web-store
Project to create a simple EJB multimodule application.

#### Prerequisites
- [OpenJDK 1.8](http://jdk.java.net/java-se-ri/8)
- [Maven 3.6+](https://maven.apache.org/install.html)
- [WildFly 16](https://wildfly.org/news/2019/02/27/WildFly16-Final-Released/)

#### Running Localy

```SHELL
mvn clean install
```
```SHELL
sudo systemctl start wildfly
```
```SHELL
 mvn wildfly:deploy
```

#### Running with docker

```SHELL
cd apl-proj
```

```SHELL
docker build -t apl.web .
```

```SHELL
docker run -p 8080:8080 -p 9990:9990 -it apl.web
```

#### Instructions

To list product stock access this url:

```
http://localhost:8080/apl.web/EstoqueServlet?acao=listar
```

This url will return html format as default, but if you prefer **JSON format** just add '&formato=json':

```
http://localhost:8080/apl.web/EstoqueServlet?acao=listar&formato=json
```
JSON return:
```JSON
[
    {
        id: 1,
        codigo: "d78d8653",
        nome: "Geladeira Brastemp",
        descricao: "Geladeira Brastemp 450L",
        preco: 2500
    },
    {
        id: 6,
        codigo: "9b456dcc",
        nome: "Freezer",
        descricao: "Freezer 300L",
        preco: 1600
    },
    {
        id: 7,
        codigo: "9599fafa",
        nome: "TV 50'",
        descricao: "TV 50' 4K",
        preco: 2400
    }
]
```

The parameter 'formato' can be: _html_ or _json_
