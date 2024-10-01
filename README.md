# bcb-server
This is the backend main application for Big Chat Brasil project.

## Build and Run

### 1. Build Application

To build the application use the maven commands:

` mvn clean install `

### 2. Build docker image

Build the docker image with a tag name

` docker build -t bcb-core-app `

### 3. Run with Docker

Run the app container exposing the 8080 port

` docker run -d --name bcb-sms-simulator -p 8080:8080 bcb-sms-simulator `

## Make a Request

### Send a message endpoint

Sends a SMS message to a given recipient phone number. This operation costs 0.25 from the customer plan credit amount.

` POST /message `

### Backoffice Endpoints

Endpoint to fetch all customers and their respective plans.

` GET /customers `

Endpoint to fetch a specific customer and their plan finding by ID.

` GET /customers/{id} `

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