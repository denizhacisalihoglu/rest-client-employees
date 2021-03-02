# rest-client-employees project

This project is an API gives Employee information hosted at Heroku, and using MOCK data provided from Mockaroo.

API is using Quarkus framework.

## Technical Specs

Database: heroku.com / Postgres

Data: Mock data from https://www.mockaroo.com/

Entity: Panache

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
./mvnw compile quarkus:dev
```

## API Endpoints

#### `/employees`

Lists all employees from Employees table.   `application/json`

```shell script
curl -GET http://localhost:8080/employees
```

## References
https://quarkus.io/
