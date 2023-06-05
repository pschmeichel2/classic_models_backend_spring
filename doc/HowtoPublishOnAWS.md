# How to publish this web service on AWS (Amazon Web Service)

First draft, just some quick notes.

## Create a free AWS account
* [Create a free AWS account](https://aws.amazon.com/de/free)

## Create a mysql database on Amazon RDS (Amazon Relational Database Service)
* [Go to the Amazon RDS console](https://eu-central-1.console.aws.amazon.com/rds/home?region=eu-central-1#databases:)
    * The link above is for the eu-central-1 (Frankfurt) region.
* Create database - MySQL - free
* Enter a database instance name, user ID and password.
    * If you chose to have AWS create a password, it will appear at the top of the screen after the database is created. 
    You'll have to copy it from there, there's no way to retrieve it later; you can only create a new password 
    (select the database instance in the console and click "Modify").

## Configuring the database
* In Security Group Rules, Inbound, add your local IP address.
* Test access to the database using a local database client (such as [dBeaver](https://dbeaver.io/)).
** Enter the "Endpoint" from the RDS database screen as the "Server-Host" in dBeaver.
* To create the MySQL [sample database](https://www.mysqltutorial.org/mysql-sample-database.aspx), just open it's script and run it from within dBeaver.
* MySQL table names are case sensitive on AWS !!! (and on Unix in general)
    * We want the table names to be camel case, because Spring Boot prefers those, and the table names will be replicated as class names in your Spring Boot application.
    * Change the MySql system variable lower_case_table_names, or: 
----
            RENAME TABLE classicmodels.customers TO classicmodels.Customers;
            RENAME TABLE classicmodels.employees  TO classicmodels.Employees;
            RENAME TABLE classicmodels.offices  TO classicmodels.Offices;
            RENAME TABLE classicmodels.orderdetails  TO classicmodels.OrderDetails;
            RENAME TABLE classicmodels.orders  TO classicmodels.Orders;
            RENAME TABLE classicmodels.payments  TO classicmodels.Payments;
            RENAME TABLE classicmodels.productlines  TO classicmodels.ProductLines;
            RENAME TABLE classicmodels.products  TO classicmodels.Products;
----

## Deploying your spring web service in AWS Elastic Beanstalk
### Create a jar file from your application
* Change the name of the jar file
    * Gradle: set "version" in build.gradle 
* Create the jar file
    * Open a terminal
    * gradle:  ./gradlew bootJar
    * maven: mvn clean install
* Test the jar file
    * Open a terminal
    * Go to the build\libs directory
    * Run: java \-jar myapp.jar

### Elastic Beanstalk Console
* Create Environment
    * Platform: Java 17
    * Version: 0.1
    * Upload code - Local file - Select my jar file		
    * next
	* VPC: choose the same VPC as the database
	* Availability zone: same as the database (Frankfurt=eu\-central\-1a)
	* Database: **DO NOT SELECT THIS**
	* ec2 security group: default
	* Environment variables:
	    * SERVER_PORT : 5000 
		* SPRING_DATASOURCE_URL : jdbc:mysql://....rds.amazonaws.com:3306/classicmodels
		* SPRING_DATASOURCE_USERNAME :  admin
		* SPRING_DATASOURCE_PASSWORD: ...
		* SPRING_JPA_DATABASE_PLATFORM : org.hibernate.dialect.MySQL8Dialect

    * Add the new environment to the ec2 inbound security rule of the database (at the RDS Console).

    ## Test the service
    * Status should be \"ok\"
    * Log files
        * Click on "Protocols", select a protocol from the combo box on the right 

    * Launch the Swagger Frontend    
        * Click on the link under "Domain", this should be something like http://classicmodels-backend-env......eu-central-1.elasticbeanstalk.com/
        * This should produce a "whitelabel" error.
        * Just add "/swagger-ui/index.html" to the "domain" URL. 
        This should display the Swagger frontend and allow you to test the services.
