# REST API Enterprise Project

This is a template project from internship at `Kuehne + Nagel`.
Here was implemented a few tasks for educational purposes.
Overall, the project contains a simple REST API for managing
BusinessUnits and their Employees.

# Requirements
• Java 17 <br>
• Maven <br>
• Spring Boot <br>

# Additional Requirements
• Change `String path` where you want to store contracts failed logs in 
`src/main/java/com/knits/enterprise/service/company/ContractService.java` 
in method `public String generateFileOfReport(Long id)` 
<br>

## Run the app

    mnv clean package
    java -jar target/Enterprise-api-0.0.1-SNAPSHOT.jar

