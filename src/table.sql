CREATE TABLE Customer (
    customer_ID INTEGER, 
    customer_name VARCHAR(255),
    password INTEGER,
    address VARCHAR(255),
    payment VARCHAR(255)
);

CREATE TABLE Merchant(
    merchant_ID INTEGER,
    merchant_name VARCHAR(255),
    product_NO INTEGER
    );

CREATE TABLE Product(
    product_NO INTEGER,
    product_name VARCHAR(255),
    unit_price FLOAT,
    category VARCHAR(255) ,
    merchant_ID INTEGER,
    brand VARCHAR(255),
    stock_level INTEGER,
    sales INTEGER
);

CREATE TABLE shopping_cart(
    customer_ID INTEGER,
    product_NO INTEGER,
    product_name VARCHAR(255),
    unit_price FLOAT,
    quantity INTEGER,
    total_price FLOAT
);

CREATE TABLE "Order" (
    order_NO INTEGER,
    customer_ID INTEGER,
    order_date DATE,
    product_NO INTEGER,
    quantity INTEGER,
    total_price FLOAT,
    payment VARCHAR(255),
    address VARCHAR(255)
);

CREATE TABLE report(
    date_of_report DATE,
    customer_ID INTEGER,
    merchant_ID INTEGER,
    merchant_name VARCHAR(255),
    product_NO INTEGER,
    unit_price FLOAT,
    brand VARCHAR(255),
    inventory INTEGER
);

CREATE TABLE Contain(
    product_NO INTEGER,
    customer_ID INTEGER
);

CREATE TABLE Provide(
    product_NO INTEGER,
    customer_ID INTEGER
);

CREATE TABLE Purchase(
    product_NO INTEGER,
    merchant_ID INTEGER
);

CREATE TABLE Communicate(
    customer_ID INTEGER,
    merchant_ID INTEGER
);

