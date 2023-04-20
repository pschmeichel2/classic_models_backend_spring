# Howto publish this web service on AWS (Amazon Web Service)

First draft, just some quick notes.

## Create a free AWS Account
* [Create free AWS Account](https://aws.amazon.com/de/free)

## Create a mysql database in Amazon RDS
* [Go to Amazon RDS console](https://eu-central-1.console.aws.amazon.com/rds/home?region=eu-central-1#databases:)
    * The link above is for the region eu-central-1 (Frankfurt).
* Create database - MySQL - free
* Enter a database instance name, user id and password.
    * If you chose to let AWS create a password, it will be displayed at the top of the screen after creation of the database. 
    You'll need to copy it from there, later on there's no way to retrieve it; you can only create a new password 
    (select the database instance from the console and click "Modify").

## Configure the database
* In Security Group Rules, Inbound, add your local IP address.
* Test access to the database with a local database client (e.g. [dBeaver](https://dbeaver.io/)).
* To create the MySQL [Sample Database](https://www.mysqltutorial.org/mysql-sample-database.aspx), just open it's script and run it from dBeaver.
* MySQL databases are case sensitive on AWS !!! (and on Unix in general)
    * change system variable lower_case_table_names, or: 
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

## Deploy your spring web service in AWS Elastic Beanstalk
### Create a jar file for jour application
* Change the name of the jar file
    * Gradle: in build.gradle, set "version"
* Create the jar file
    * Open a terminal
    * gradle:  ./gradlew bootJar
    * maven: mvn clean install
* Test the jar file
    * Open a terminal
    * Go to the build\libs directory
    * Start with java *jar myapp.jar

### Elastic Beanstalk console
* Create environment
    * Platform: Java 17
    * Version: 0.1
    * Upload code - Local file - Select my jar file		
    * next
	* VPC: select the same VPC as the database
	* Availability zone: same as the database (Frankfurt=eu central 1a)
	* Database: **DO NOT SELECT THIS**
	* ec2 security group: default
	* Environment variables:
	    * SERVER_PORT : 5000 
		* SPRING_DATASOURCE_URL : jdbc:mysql://....rds.amazonaws.com:3306/classicmodels
		* SPRING_DATASOURCE_USERNAME :  admin
		* SPRING_DATASOURCE_PASSWORD: ...
		* SPRING_JPA_DATABASE_PLATFORM : org.hibernate.dialect.MySQL8Dialect

    * Add the new environment to the Database's ec2 inbound security rule.

    ## Test the service
    * Status should be "ok"
    * Logfiles
        * Click on "Protocols", select a protocol from the combobox on the right side
    * Start the Swagger Frontend
    
        * Click on the link below "Domain", this should be something like http://classicmodels-backend-env......eu-central-1.elasticbeanstalk.com/
        * This should produce a "whitelabel" error.
        * Just add "/swagger-ui/index.html" to the "domain" URL. This should display the Swagger frontend and enable you to test the services.
