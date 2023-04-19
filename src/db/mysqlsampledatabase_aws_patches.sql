/*
Fixes for AWS RDS Databases.
AWS RDS Databases run on Unix, where MySQL uses case-sensitive tablenames per default.
*/
RENAME TABLE classicmodels.customers TO classicmodels.Customers;
RENAME TABLE classicmodels.employees  TO classicmodels.Employees;
RENAME TABLE classicmodels.offices  TO classicmodels.Offices;
RENAME TABLE classicmodels.orderdetails  TO classicmodels.OrderDetails;
RENAME TABLE classicmodels.orders  TO classicmodels.Orders;
RENAME TABLE classicmodels.payments  TO classicmodels.Payments;
RENAME TABLE classicmodels.productlines  TO classicmodels.ProductLines;
RENAME TABLE classicmodels.products  TO classicmodels.Products;
