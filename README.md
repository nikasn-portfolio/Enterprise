# REST API Enterprise Project

This is a template project from internship at `Kuehne + Nagel`.
Here was implemented a few tasks for educational purposes.
Overall, the project contains a simple REST API for managing
BusinessUnits and their Employees.

# Requirements
• Java 17 <br>
• Maven <br>
• Spring Boot <br>

## Run the app

    mnv clean package
    java -jar target/Enterprise-api-0.0.1-SNAPSHOT.jar

# REST API

The REST API to the example is described below.

# BusinessUnit Endpoints

## Get BusinessUnit Paginated

### Request

`GET http://localhost:8080/api/business-units?limit=1&page=1` <br>
Accept following query parameters: <br>
`limit` - the number of items per page (optional) <br>
`page` - the page number (optional) <br>
`sort` - the field to sort by (separated by comma) (optional) <br>
`dir` - the direction of sorting (ASC or DESC) (optional) <br>
`name` - the name of the business unit (optional) <br>

### Response

    {
    "page": 1,
    "size": 1,
    "sortingFields": "id",
    "sortDirection": "ASC",
    "data": [
        {
            "active": true,
            "id": 2,
            "name": "Air Logistics",
            "description": "sollicitudin mi sit amet lobortis sapien sapien non mi integer ac neque duis bibendum",
            "startDate": "28/11/2021 00:00:00",
            "endDate": null,
            "createdBy": null
        }
    ]
    }

## Create a new BusinessUnit

### Request

`POST http://localhost:8080/api/business-unit`

### RequestBody
    {
        "name": "Example Name"
    }
`description` - optional <br>
`startDate` - optional, format : dd/MM/yyyy HH:mm:ss <br>
`endDate` - optional, format : dd/MM/yyyy HH:mm:ss <br>
`createdBy` - auto generated <br>

### Response

    {
    "active": true,
    "id": 10000,
    "name": "Example Name",
    "description": "Example Description",
    "startDate": "19/02/2024 21:08:33",
    "endDate": null,
    "createdBy": null
    }

## Update a BusinessUnit

### Request

`PATCH http://localhost:8080/api/business-units`

### RequestBody
    {
        "id": 10000,
        "name": "Example Name (updated)",
        ...
    }
### Response
    {
    "active": false,
    "id": 10000,
    "name": "Example Name (updated)",
    "description": "Example Description",
    "startDate": "19/02/2024 21:08:33",
    "endDate": null,
    "createdBy": null
    }
**if successful, returns the updated BusinessUnit**
    

## Deactivate a BusinessUnit

### Request

`PUT http://localhost:8080/api/business-units/10000`

### Response
    Status: 204 No content
**if successful, returns no content**

## Hard delete a BusinessUnit

### Request

`DELETE http://localhost:8080/api/business-units/10000`

### Response
    Status: 204 No content
**if successful, returns no content**

## Get list of Things again

### Request

`GET /thing/`

    curl -i -H 'Accept: application/json' http://localhost:7000/thing/

### Response

    HTTP/1.1 200 OK
    Date: Thu, 24 Feb 2011 12:36:31 GMT
    Status: 200 OK
    Connection: close
    Content-Type: application/json
    Content-Length: 74

    [{"id":1,"name":"Foo","status":"new"},{"id":2,"name":"Bar","status":null}]

## Change a Thing's state

### Request

`PUT /thing/:id/status/changed`

    curl -i -H 'Accept: application/json' -X PUT http://localhost:7000/thing/1/status/changed

### Response

    HTTP/1.1 200 OK
    Date: Thu, 24 Feb 2011 12:36:31 GMT
    Status: 200 OK
    Connection: close
    Content-Type: application/json
    Content-Length: 40

    {"id":1,"name":"Foo","status":"changed"}

## Get changed Thing

### Request

`GET /thing/id`

    curl -i -H 'Accept: application/json' http://localhost:7000/thing/1

### Response

    HTTP/1.1 200 OK
    Date: Thu, 24 Feb 2011 12:36:31 GMT
    Status: 200 OK
    Connection: close
    Content-Type: application/json
    Content-Length: 40

    {"id":1,"name":"Foo","status":"changed"}

## Change a Thing

### Request

`PUT /thing/:id`

    curl -i -H 'Accept: application/json' -X PUT -d 'name=Foo&status=changed2' http://localhost:7000/thing/1

### Response

    HTTP/1.1 200 OK
    Date: Thu, 24 Feb 2011 12:36:31 GMT
    Status: 200 OK
    Connection: close
    Content-Type: application/json
    Content-Length: 41

    {"id":1,"name":"Foo","status":"changed2"}

## Attempt to change a Thing using partial params

### Request

`PUT /thing/:id`

    curl -i -H 'Accept: application/json' -X PUT -d 'status=changed3' http://localhost:7000/thing/1

### Response

    HTTP/1.1 200 OK
    Date: Thu, 24 Feb 2011 12:36:32 GMT
    Status: 200 OK
    Connection: close
    Content-Type: application/json
    Content-Length: 41

    {"id":1,"name":"Foo","status":"changed3"}

## Attempt to change a Thing using invalid params

### Request

`PUT /thing/:id`

    curl -i -H 'Accept: application/json' -X PUT -d 'id=99&status=changed4' http://localhost:7000/thing/1

### Response

    HTTP/1.1 200 OK
    Date: Thu, 24 Feb 2011 12:36:32 GMT
    Status: 200 OK
    Connection: close
    Content-Type: application/json
    Content-Length: 41

    {"id":1,"name":"Foo","status":"changed4"}

## Change a Thing using the _method hack

### Request

`POST /thing/:id?_method=POST`

    curl -i -H 'Accept: application/json' -X POST -d 'name=Baz&_method=PUT' http://localhost:7000/thing/1

### Response

    HTTP/1.1 200 OK
    Date: Thu, 24 Feb 2011 12:36:32 GMT
    Status: 200 OK
    Connection: close
    Content-Type: application/json
    Content-Length: 41

    {"id":1,"name":"Baz","status":"changed4"}

## Change a Thing using the _method hack in the url

### Request

`POST /thing/:id?_method=POST`

    curl -i -H 'Accept: application/json' -X POST -d 'name=Qux' http://localhost:7000/thing/1?_method=PUT

### Response

    HTTP/1.1 404 Not Found
    Date: Thu, 24 Feb 2011 12:36:32 GMT
    Status: 404 Not Found
    Connection: close
    Content-Type: text/html;charset=utf-8
    Content-Length: 35

    {"status":404,"reason":"Not found"}

## Delete a Thing

### Request

`DELETE /thing/id`

    curl -i -H 'Accept: application/json' -X DELETE http://localhost:7000/thing/1/

### Response

    HTTP/1.1 204 No Content
    Date: Thu, 24 Feb 2011 12:36:32 GMT
    Status: 204 No Content
    Connection: close


## Try to delete same Thing again

### Request

`DELETE /thing/id`

    curl -i -H 'Accept: application/json' -X DELETE http://localhost:7000/thing/1/

### Response

    HTTP/1.1 404 Not Found
    Date: Thu, 24 Feb 2011 12:36:32 GMT
    Status: 404 Not Found
    Connection: close
    Content-Type: application/json
    Content-Length: 35

    {"status":404,"reason":"Not found"}

## Get deleted Thing

### Request

`GET /thing/1`

    curl -i -H 'Accept: application/json' http://localhost:7000/thing/1

### Response

    HTTP/1.1 404 Not Found
    Date: Thu, 24 Feb 2011 12:36:33 GMT
    Status: 404 Not Found
    Connection: close
    Content-Type: application/json
    Content-Length: 35

    {"status":404,"reason":"Not found"}

## Delete a Thing using the _method hack

### Request

`DELETE /thing/id`

    curl -i -H 'Accept: application/json' -X POST -d'_method=DELETE' http://localhost:7000/thing/2/

### Response

    HTTP/1.1 204 No Content
    Date: Thu, 24 Feb 2011 12:36:33 GMT
    Status: 204 No Content
    Connection: close