# Shopping Cart Application

This project is a simple shopping cart application with a collection of endpoints that represents de crud of products, orders and a payment process. Also implement authentication using JSON Web Tokens (JWT) in Spring Boot.

## Project Structure

- `src/main/java/org/example/Application.java`: Main class to run the Spring Boot application.
- `src/main/java/org/example/config/JwtUtil.java`: Utility class for generating and validating JWT tokens.
- `src/main/java/org/example/config/SecurityConfig.java`: Security configuration class to set up HTTP security.
- `src/main/java/org/example/controller/AuthController.java`: Controller class to handle authentication-related endpoints.
- `src/main/java/org/example/controller/ProductController.java`: Controller class for product management.
- `src/main/java/org/example/controller/OrderController.java`: Controller class for order management.
- `src/main/java/org/example/controller/PaymentController.java`: Controller class for payment processing.
- `src/main/java/org/example/model/AuthRequest.java`: Model class for authentication requests.
- `src/main/java/org/example/model/AuthResponse.java`: Model class for authentication responses.
- `src/main/java/org/example/model/User.java`: Model class for user details.
- `src/main/java/org/example/model/Product.java`: Model class for products.
- `src/main/java/org/example/model/Order.java`: Model class for orders.
- `src/main/java/org/example/model/Payment.java`: Model class for payments.
- `src/main/java/org/example/service/AuthService.java`: Service class to handle authentication logic.
- `src/main/java/org/example/service/ProductService.java`: Service class for product management.
- `src/main/java/org/example/service/OrderService.java`: Service class for order management.
- `src/main/java/org/example/service/PaymentService.java`: Service class for payment processing.

## Endpoints

### Authentication

#### Register

- **URL**: `/auth/register`
- **Method**: `POST`
- **Request Body**:
  ```json
  {
    "username": "your_username",
    "password": "your_password"
  }
  ```
- **Response**:
  ```json
  {
    "token": "generated_jwt_token"
  }
  ```

#### Login

- **URL**: `/auth/login`
- **Method**: `POST`
- **Request Body**:
  ```json
  {
    "username": "your_username",
    "password": "your_password"
  }
  ```
- **Response**:
  ```json
  {
    "token": "generated_jwt_token"
  }
  ```

#### Validate Token

- **URL**: `/auth/validate`
- **Method**: `GET`
- **Query Parameter**: `token`
- **Response**:
  ```json
  "true (if the token is valid), false (otherwise)"
  ```

### Products

#### Get All Products

- **URL**: `/products`
- **Method**: `GET`
- **Response**:
  ```json
  [
    {
      "id": 1,
      "name": "Product 1",
      "price": 100.0
    },
    {
      "id": 2,
      "name": "Product 2",
      "price": 150.0
    }
  ]
  ```

#### Get Product by ID

- **URL**: `/products/{id}`
- **Method**: `GET`
- **Response**:
  ```json
  {
    "id": 1,
    "name": "Product 1",
    "price": 100.0
  }
  ```

### Orders

#### Create Order

- **URL**: `/orders`
- **Method**: `POST`
- **Request Body**:
  ```json
  {
    "productId": 1,
    "quantity": 2
  }
  ```
- **Response**:
  ```json
  {
    "orderId": 123,
    "productId": 1,
    "quantity": 2,
    "status": "CREATED"
  }
  ```

#### Get Order by ID

- **URL**: `/orders/{id}`
- **Method**: `GET`
- **Response**:
  ```json
  {
    "orderId": 123,
    "productId": 1,
    "quantity": 2,
    "status": "CREATED"
  }
  ```

### Payments

#### Process Payment

- **URL**: `/payments`
- **Method**: `POST`
- **Request Body**:
  ```json
  {
    "orderId": 123,
    "amount": 200.0
  }
  ```
- **Response**:
  ```json
  {
    "paymentId": 456,
    "orderId": 123,
    "amount": 200.0,
    "status": "COMPLETED"
  }
  ```

#### Get Payment by ID

- **URL**: `/payments/{id}`
- **Method**: `GET`
- **Response**:
  ```json
  {
    "paymentId": 456,
    "orderId": 123,
    "amount": 200.0,
    "status": "COMPLETED"
  }
  ```

## JWT Token

The JWT token is generated with the following properties:

- **Subject**: Username of the user
- **Issued At**: Current date and time
- **Expiration**: 10 hours from the issued time
- **Signature Algorithm**: HS256

## Security Configuration

The security configuration allows unauthenticated access to the following endpoints:

- `/auth/login`
- `/auth/register`
- `/auth/validate`
- `/products`
- `/orders`
- `/payments`

All other endpoints require authentication.

## Running the Application

1. Clone the repository.
2. Navigate to the project directory.
3. Run the application using the following command:
   ```sh
   ./gradlew bootRun
   ```
4. The application will start on `http://localhost:8080`.

## Dependencies

- Spring Boot
- Spring Security
- JSON Web Token (JWT)
- Gradle

## License

This project is licensed under the MIT License.

