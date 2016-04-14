# CART.JS
Electronic Shopping Cart System

Move 02.jpg, 03.jpg, 05.jpg and 06.jpg image files in to the 'ShoppingCart/src/main/resources/static/images' path in order to work with this system properly.

Technologies used:

	Spring Tool Suite - 3.7.3.RELEASE
	JDK - 1.8
	Node.js - 5.10.1
	npm - 3.8.3
	Angularjs - 1.5.3
	HTML - 5
	MySQL - 14.14

1) System has two main interfaces (pages). One for a customer who is willing to 
purchase a product and other one is for adiministration purposes.

2) You can navigate between these two clicking a button at the top-right corner
as "GO TO ADMINISTRATION PANEL" in storefront and "GO TO STOREFRONT" in admin
page. When you navigate to the admin page, you will be asked to provide the 
admin credential as given below.
	
	Username: zaizi
	Password: zaizi@123

3) The system uses MySQL database for its extensive data storage and retrieval and
hence you will have to have MySQL database installed in your system.

4) In MySQL CLI, provide the following to create a database that integrates to
this system.

	Create database springbootdb;
	USE springbootdb;
	Create table products(
		id INT8 NOT NULL AUTO_INCREMENT,
		name VARCHAR(100),
		price VARCHAR(100),
		stock VARCHAR(100),
		PRIMARY KEY(id)
	);

Or just source the products.sql file attached herewith. The columns descriptions
are given below.
	id - the handler to retrieve and update products specifications
	name - name of the product
	price - price at the current market place
	stock - number of items available on a given product

The MySQL database admin credentials are specified in the 'application.properties'
file in the 'static' resource folder as follows.

	spring.datasource.url=jdbc:mysql://localhost:3306/springbootdb?autoReconnect=true&useSSL=false
	spring.datasource.username=root
	spring.datasource.password=zaizi

Therefore, please feel free to change the password according to your preferences
if you are using this code in production. Otherwise,

	set your root password of MySQL as 'zaizi'

An example of products table is given below.

	mysql> use springbootdb
	Database changed
	mysql> select * from products;
	+----+-------------------+--------+-------+
	| id | name              | price  | stock |
	+----+-------------------+--------+-------+
	|  1 | Dell Laptop PCI3  | 100000 | 7     |
	|  2 | Toshiba 12KK2     | 70000  | 4     |
	|  4 | HP Lint           | 200000 | 2     |
	|  5 | HP Lint 2 series  | 250000 | 0     |
	|  8 | Motorola N series | 150000 | 8     |
	|  9 | Alcatel           | 90000  | 5     |
	| 10 | Lenova N20        | 85000  | 3     |
	| 11 | Volto DS          | 56000  | 6     |
	| 12 | Toshiba N677      | 89000  | 16    |
	+----+-------------------+--------+-------+

5) An admin can add products, search for details of products, update stocks or
name or price of the products and delete products from the database using admin
interface. Please note that the removal of product will not happen as soon as
the stock is finished and only will disallow from being picked by a customer.
An admin can thus remove/delete the product using it's id or update the stock
as soon as the store receive new set of stock.

6) A customer can view (in storefront) all the elements loaded in the database
and can select products he needs using checklist under 'Add to cart?' column.

7) The checked products will then be automatically loaded into the cart and 
will be displayed and will allow the users to specify the number of items he
needs. According to the users' specifications, the total price will be calculated.

8) The user can then click the 'PURCHASE' button in order to complete the shopping
where he will be directed to a new page confirming the transaction. (payment
is not added though as per the requirement specification)

9) There is another button named 'UPDATE' just to update the table after 
modifications done in the admin page. Note that, in order to reduce the ambiguity
between this table and the Cart table, the 'UPDATE' will erase the data in the
Cart table.
