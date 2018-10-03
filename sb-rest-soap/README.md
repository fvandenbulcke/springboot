SpringBoot Rest Soap Project!
-------------------

## Description

This project aims to present how use springboot to expose rest and soap web services to manage respectively books and students. The project contains two modules:
* sb-rest-soap-dto for rest dto and saop stubs generated from a WSDL by jaxb2-maven-plugin
* sb-rest-soap-api for endpoints and business services

### Soap services (Students)

Soap services aims to manage students. When the application is started, the list is initialized as follow :

|<span/>	|id | Name 	 | PassportNumber |
|-----------|---|--------|----------------|
| Student 1 | 1 | Marcel | 123456 		  |
| Student 2 | 2 | James  | 456789 		  |
| Student 3 | 3 | Miguel | 789123 		  |

Further are presented the available operations with a sample of request and a sample of response. The responses are given as though operations have been executed in this order.

##### getStudentById

Sample of request to get student by id:

```xml
<Envelope xmlns="http://schemas.xmlsoap.org/soap/envelope/">
    <Body>
        <GetStudentByIdRequest xmlns="http://webservice.api.sb/soap">
            <id>1</id>
        </GetStudentByIdRequest>
    </Body>
</Envelope>
```

Sample of response to get student by id:

```xml
<SOAP-ENV:Envelope xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/">
    <SOAP-ENV:Header/>
    <SOAP-ENV:Body>
        <ns2:GetStudentByIdResponse xmlns:ns2="http://webservice.api.sb/soap">
            <ns2:version>v1</ns2:version>
            <ns2:StudentDetails>
                <ns2:id>1</ns2:id>
                <ns2:name>Marcel</ns2:name>
                <ns2:passportNumber>123456</ns2:passportNumber>
            </ns2:StudentDetails>
        </ns2:GetStudentByIdResponse>
    </SOAP-ENV:Body>
</SOAP-ENV:Envelope>
```

##### search

Sample of request to search student by partial passport number:

```xml
<Envelope xmlns="http://schemas.xmlsoap.org/soap/envelope/">
    <Body>
        <SearchStudentsRequest xmlns="http://webservice.api.sb/soap">
            <passportNumber>123</passportNumber>
        </SearchStudentsRequest>
    </Body>
</Envelope>
```

Sample of response to search student by partial passport number:

```xml
<SOAP-ENV:Envelope xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/">
    <SOAP-ENV:Header/>
    <SOAP-ENV:Body>
        <ns2:SearchStudentsResponse xmlns:ns2="http://webservice.api.sb/soap">
            <ns2:StudentDetailList>
                <ns2:id>1</ns2:id>
                <ns2:name>Marcel</ns2:name>
                <ns2:passportNumber>123456</ns2:passportNumber>
            </ns2:StudentDetailList>
            <ns2:StudentDetailList>
                <ns2:id>3</ns2:id>
                <ns2:name>Miguel</ns2:name>
                <ns2:passportNumber>789123</ns2:passportNumber>
            </ns2:StudentDetailList>
        </ns2:SearchStudentsResponse>
    </SOAP-ENV:Body>
</SOAP-ENV:Envelope>
```


##### create

Sample of request to create student:

```xml
<Envelope xmlns="http://schemas.xmlsoap.org/soap/envelope/">
    <Body>
        <CreateStudentRequest xmlns="http://webservice.api.sb/soap">
            <name>loic</name>
            <passportNumber>987654</passportNumber>
        </CreateStudentRequest>
    </Body>
</Envelope>
```

Sample of response to create student:

```xml
<SOAP-ENV:Envelope xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/">
    <SOAP-ENV:Header/>
    <SOAP-ENV:Body>
        <ns2:CreateStudentResponse xmlns:ns2="http://webservice.api.sb/soap">
            <ns2:StudentDetailList>
                <ns2:id>1</ns2:id>
                <ns2:name>Marcel</ns2:name>
                <ns2:passportNumber>123456</ns2:passportNumber>
            </ns2:StudentDetailList>
            <ns2:StudentDetailList>
                <ns2:id>2</ns2:id>
                <ns2:name>James</ns2:name>
                <ns2:passportNumber>456789</ns2:passportNumber>
            </ns2:StudentDetailList>
            <ns2:StudentDetailList>
                <ns2:id>3</ns2:id>
                <ns2:name>Miguel</ns2:name>
                <ns2:passportNumber>789123</ns2:passportNumber>
            </ns2:StudentDetailList>
            <ns2:StudentDetailList>
                <ns2:id>4</ns2:id>
                <ns2:name>loic</ns2:name>
                <ns2:passportNumber>987654</ns2:passportNumber>
            </ns2:StudentDetailList>
        </ns2:CreateStudentResponse>
    </SOAP-ENV:Body>
</SOAP-ENV:Envelope>
```


##### updateName

Sample of request to update student name:

```xml
<Envelope xmlns="http://schemas.xmlsoap.org/soap/envelope/">
    <Body>
        <UpdateStudentNameRequest xmlns="http://webservice.api.sb/soap">
            <id>1</id>
            <name>Paul</name>
        </UpdateStudentNameRequest>
    </Body>
</Envelope>
```

Sample of response to update student name:

```xml
<SOAP-ENV:Envelope xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/">
    <SOAP-ENV:Header/>
    <SOAP-ENV:Body>
        <ns2:UpdateStudentNameResponse xmlns:ns2="http://webservice.api.sb/soap">
            <ns2:StudentDetailList>
                <ns2:id>1</ns2:id>
                <ns2:name>Paul</ns2:name>
                <ns2:passportNumber>123456</ns2:passportNumber>
            </ns2:StudentDetailList>
            <ns2:StudentDetailList>
                <ns2:id>2</ns2:id>
                <ns2:name>James</ns2:name>
                <ns2:passportNumber>456789</ns2:passportNumber>
            </ns2:StudentDetailList>
            <ns2:StudentDetailList>
                <ns2:id>3</ns2:id>
                <ns2:name>loic</ns2:name>
                <ns2:passportNumber>987654</ns2:passportNumber>
            </ns2:StudentDetailList>
            <ns2:StudentDetailList>
                <ns2:id>4</ns2:id>
                <ns2:name>loic</ns2:name>
                <ns2:passportNumber>987654</ns2:passportNumber>
            </ns2:StudentDetailList>
        </ns2:UpdateStudentNameResponse>
    </SOAP-ENV:Body>
</SOAP-ENV:Envelope>
```
##### delete

Sample of request to delete student:

```xml
<Envelope xmlns="http://schemas.xmlsoap.org/soap/envelope/">
    <Body>
        <DeleteStudentRequest xmlns="http://webservice.api.sb/soap">
            <id>3</id>
        </DeleteStudentRequest>
    </Body>
</Envelope>
```

Sample of response to delete student:

```xml
<SOAP-ENV:Envelope xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/">
    <SOAP-ENV:Header/>
    <SOAP-ENV:Body>
        <ns2:DeleteStudentResponse xmlns:ns2="http://webservice.api.sb/soap">
            <ns2:StudentDetailList>
                <ns2:id>1</ns2:id>
                <ns2:name>Marcel</ns2:name>
                <ns2:passportNumber>123456</ns2:passportNumber>
            </ns2:StudentDetailList>
            <ns2:StudentDetailList>
                <ns2:id>2</ns2:id>
                <ns2:name>James</ns2:name>
                <ns2:passportNumber>456789</ns2:passportNumber>
            </ns2:StudentDetailList>
            <ns2:StudentDetailList>
                <ns2:id>3</ns2:id>
                <ns2:name>loic</ns2:name>
                <ns2:passportNumber>987654</ns2:passportNumber>
            </ns2:StudentDetailList>
        </ns2:DeleteStudentResponse>
    </SOAP-ENV:Body>
</SOAP-ENV:Envelope>
```

### Rest services (Books)

Rest services aims to manage books. When the application is started, the list is initialized as follow :

|<span/> |id | Name 	           | PassportNumber         |
|--------|---|---------------------|------------------------|
| Book 1 | 1 | Marcel Proust       | In Search of Lost Time |
| Book 2 | 2 | James Joyce         | Ulysses                |
| Book 3 | 3 | Miguel de Cervantes | IDon Quixote           |

Further are presented the available operations with a sample of request and a sample of response. The responses are given as though operations have been executed in this order.

##### getAllBooks


Sample of request to get all books:

```bash
curl -X GET http://localhost:8080/api/v1/book
```

Sample of response to get all books:

```json
[
    { "id": 1, "title": "In Search of Lost Time", "author": "Marcel Proust" },
    { "id": 2, "title": "Ulysses", "author": "James Joyce" },
    { "id": 3, "title": "IDon Quixote", "author": "Miguel de Cervantes" }
]
```

##### getBookById

Sample of request to get book by id:

```bash
curl -X GET http://localhost:8080/api/v1/book/1
```

Sample of response to get book by id:

```json
{
	"id": 1,
	"title": "In Search of Lost Time",
	"author": "Marcel Proust"
},
```

##### create

Sample of request to create book:

```bash
curl -H "Content-Type: application/json" \
	  -X POST \
	  -d '{"title":"Germinal","author":"Emile Zola"}' \
	  http://localhost:8080/api/v1/book
```

Sample of response to create book:

```json
[
    { "id": 1, "title": "In Search of Lost Time", "author": "Marcel Proust" },
    { "id": 2, "title": "Ulysses", "author": "James Joyce" },
    { "id": 3, "title": "IDon Quixote", "author": "Miguel de Cervantes" }
    { "id": 4, "title": "Germinal", "author": "Emile Zola" }
]
```


##### updateTitle

Sample of request to update book title:

```bash
curl -H "Content-Type: application/json" \
	  -X PUT \
	  -d 'Les Gens de Dublin' \
	  http://localhost:8080/api/v1/book/2
```

Sample of response to update book title:

```json
[
    { "id": 1, "title": "In Search of Lost Time", "author": "Marcel Proust" },
    { "id": 2, "title": "Les Gens de Dublin", "author": "James Joyce" },
    { "id": 3, "title": "IDon Quixote", "author": "Miguel de Cervantes" }
    { "id": 4, "title": "Germinal", "author": "Emile Zola" }
]
```


##### delete

Sample of request to delete book:

```bash
curl -H "Content-Type: application/json" \
	  -X DELTE \
	  http://localhost:8080/api/v1/book/2
```

Sample of response to delete book:

```json
[
    { "id": 1, "title": "In Search of Lost Time", "author": "Marcel Proust" },
    { "id": 2, "title": "IDon Quixote", "author": "Miguel de Cervantes" }
    { "id": 3, "title": "Germinal", "author": "Emile Zola" }
]
```

