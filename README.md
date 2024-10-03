# bcb-server
This is the backend main application for Big Chat Brasil project.

## Build and Run

### 1. Build Application

To build the application use the maven commands:

` mvn clean install `

### 2. Build docker image

You can build a docker image for this app only, or build all the stack with docker-compose following the steps in [BCB-INFRA](https://github.com/DevJamila/bcb-infra) repository.

Build the docker image with a tag 

` docker build -t bcb-core-app `

### 3. Run with Docker

Also you can run all the stack with [docker-compose](https://github.com/DevJamila/bcb-infra/blob/main/docker-compose.yaml) or run just this app container exposing the 8080 port

` docker run -d --name bcb-core-app -p 8080:8080 bcb-core-app `

## Make a Request

### Client Endpoints

Sends a SMS message to a given recipient phone number. This operation costs 0.25 from the customer plan credit amount.

` POST /message `

Get the message history sent by a specified customer.

` GET /message/customer/{id} `

Login into the system.

` POST /auth/login `

### Backoffice Endpoints

Endpoint to fetch all customers and their respective plans.

` GET /customers `

Endpoint to fetch a specific customer and their plan finding by key, the key may be the ID or phone number.

` GET /customers/{key} `

Endpoint to add credit to a customer plan, just prepaid plans allows this operation.

` POST /customers/{id}/credit `

Endpoint to switch the customer plan type, new postpaid plans comes with 4.00 credit amount.

` PUT /customers/{id}/plan `

### API documentation

Access the request body json model on the Swagger UI.

` http://localhost:8080/swagger-ui/index.html `

## Test

Execute the automated tests with the maven command:

` mvn test `