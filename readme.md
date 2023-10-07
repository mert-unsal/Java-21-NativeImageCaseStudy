## [java21Native-java-case-study](https://github.com/mert-unsal/Java-21-NativeImageCaseStudy)
Senior Java Developer java21Native Case Study

You need to run dockerized application container via ``docker-compose up --build`` on the root directory.

### DOCKER CONTAINER
If you need to containerize manually, you can build image via ``Dockerfile`` run this script ``docker build . -t java21Native-case-study-backend``
Then You can run application via `docker-compose up` on the root directory.

### POSTMAN

In the root directory, there exists a postman collection json file.
You may import this file to use on POSTMAN.

On the Postman collection, there are `username` and `password` collection variables.
You need to first ``signUp`` a customer. Therefore, you need to use 
 `Java-21-NativeImageCaseStudy -> Authentication -> SignUp` 
SignUp request body is indicated at below,

#### SIGN UP EXAMPLE BODY
```json
{
  "name": "mert",
  "surname": "unsal",
  "username": "munsal",
  "password": "munsal",
  "email": "a@a.com",
  "address": "Körfez caddesi",
  "phone": "03483396874"
}
```

You do not need to send JWT token on sending any request on postman, 
there is a pre-request script on postman it takes `username` and `password` from collection variables 
and authenticate itself. After you have sign up with a customer.
It also persists JWT token on collection variable `BEARERTOKEN`.

#### You need to initialize collection variable `username` and `password`.
#### Once you did it, you do not need to update it again unless you want to `signIn` with different customer

### SWAGGER
There is swagger page for this application.
You can achieve it here [swagger](http://localhost:8080/swagger-ui/index.html)