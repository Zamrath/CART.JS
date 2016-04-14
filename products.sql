Create database springbootdb;
USE springbootdb;
Create table products(
	id INT8 NOT NULL AUTO_INCREMENT,
	name VARCHAR(100),
	price VARCHAR(100),
	stock VARCHAR(100),
	PRIMARY KEY(id)
);