## 🛠️ Getting started
Follow this step-by-step guide to set up and launch the application on your local machine. Please complete every step carefully to ensure a successful system boot.

### 🚀 About the Application
This robust Java application streamlines Product and Order management while enforcing strict business logic to guarantee high data quality and integrity.

### 💼 Prerequisites

Please ensure that you computer has the following prerequisites installed before attempting to run the application

  ```sh
  - Java 17 or higher installed
  - Maven 3.8+ installed (or use the included Maven Wrapper ./mvnw)
  ```
### 💻  Installation

Below are instructions on how you can install and set up your app. 

1. Open your terminal and navigate to the project root directory.
2. Build the project and package the JAR file: (in the case that you get an error - delete the 'target' folder before trying again)
   ```sh
   mvn clean package
   ```
2. Run the compiled application:
   ```sh
   java -jar target/OrderManMicroService-0.0.1-SNAPSHOT.jar
   ```
*Alternatively, you can boot it instantly using the Spring Boot plugin: mvn spring-boot:run*
4. Access the running application:
> #### Swagger UI Documentation:
>
> - Open http://localhost:8080/swagger-ui/index.html in your browser.
> #### H2 Database Console:
>
> - Navigate to http://localhost:8080/h2-console (JDBC URL: jdbc:h2:mem:orderdb, Username: sa, Password: password).
5. Authenticate your API calls using Basic Auth:
>
> - Username: admin
> - Password: admin123

### Run the Tests

Run the business logic unit tests using Maven commands, execute the following command in your terminal.

1. To run all tests:
   ```sh
   mvn clean package
   ```
2. To run a specific tests:
   ```sh
    mvn test -Dtest=OrderServiceTest
   ```
3. To generate a test report:
   ```sh
    mvn surefire-report:report
   ```
*The resulting HTML report will be available inside the target/site/surefire-report.html folder.*

<!-- CONTACT -->
## Contact

`Founder` : Matimu Romeo Ngoveni - matimu.romeo@outlook.com

Project Link: [https://github.com/STIMZA/OrderManMicroService](https://github.com/STIMZA/OrderManMicroService)