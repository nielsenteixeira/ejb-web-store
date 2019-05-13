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
        quantidade: 1,
        codigo: "d78d8653",
        nome: "Geladeira Brastemp",
        descricao: "Geladeira Brastemp 450L",
        preco: 2500
    },
    {
        quantidade: 6,
        codigo: "9b456dcc",
        nome: "Freezer",
        descricao: "Freezer 300L",
        preco: 1600
    },
    {
        quantidade: 7,
        codigo: "9599fafa",
        nome: "TV 50'",
        descricao: "TV 50' 4K",
        preco: 2400
    }
]
```

The parameter 'formato' can be: _html_ or _json_

To list cart access this url:

```
http://localhost:8080/apl.web/CarrinhoServlet?acao=listar
```

To add an item into cart access this url:

```
http://localhost:8080/apl.web/CarrinhoServlet?acao=adicionar&produto=d78d8653
```

The parameter _produto_ refers to property _codigo_ from list stock products

To finalize the purchase:

```
http://localhost:8080/apl.web/CarrinhoServlet?acao=finalizar
```

At the end of the purchase the system sends a request to the replenishment queue where the algorithm is: minimum quantity required for product in stock + 2

After that, just check the quantity in stock again

```
http://localhost:8080/apl.web/EstoqueServlet?acao=listar
```