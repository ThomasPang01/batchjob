# Getting Started
### Notes
This project created from spring initializr and added below dependency:
* Spring Batch

1. Define the Data Model
com.example.batchjob.model.Transaction.java
Create a Java class to represent the fields in the dataSource.txt file. The file is delimited by |, so you'll map each column to a field in the class.

2. Create a Configuration Class
com.example.batchjob.config.BatchConfig.java
Set up the Spring Batch configuration to read the file, process the data, and write the results.

3. Create Custom Skip Listener for Exception Handling, to prevent stopper.
com.example.batchjob.listener.java
Create a CustomSkipListener class to log skipped records in a package.

4. Main Application 
com.example.batchjob.BatchjobApplication.java

5. Class Diagram
+------------------+      +-----------------+       +------------------+
|   Transaction    |      |   BatchConfig   |       |  BatchJobApplication |
+------------------+      +-----------------+       +------------------+
| - id: Long       |      | - reader()     |       | - main(String[]) |
| - accountNumber  |      | - processor()  |       +------------------+
| - trxAmount      |      | - writer()     |
| - description    |      | - step1()      |
| - trxDate        |      | - job()        |
| - trxTime        |      +-----------------+
| - customerId     |
+------------------+

6. Activity Diagram
Start -> Read File -> Process Record -> Write Record -> End
         |
         +--> Skipped if Exception


7. Design Pattern
Template Method Pattern: to define the skeleton of the batch process (read, process, write). Hence, it enables customization of specific steps (like: custom readers, processors, and writers) without changing the overall process flow.



### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/3.3.6/maven-plugin)
* [Create an OCI image](https://docs.spring.io/spring-boot/3.3.6/maven-plugin/build-image.html)
* [Spring Batch](https://docs.spring.io/spring-boot/3.3.6/how-to/batch.html)
* [Spring Data JPA](https://docs.spring.io/spring-boot/3.3.6/reference/data/sql.html#data.sql.jpa-and-spring-data)
* [Spring Data JDBC](https://docs.spring.io/spring-boot/3.3.6/reference/data/sql.html#data.sql.jdbc)

### Guides
The following guides illustrate how to use some features concretely:

* [Creating a Batch Service](https://spring.io/guides/gs/batch-processing/)

### Maven Parent overrides

Due to Maven's design, elements are inherited from the parent POM to the project POM.
While most of the inheritance is fine, it also inherits unwanted elements like `<license>` and `<developers>` from the parent.
To prevent this, the project POM contains empty overrides for these elements.
If you manually switch to a different parent and actually want the inheritance, you need to remove those overrides.

