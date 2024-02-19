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