CREATE TABLE Customer (
    customer_ID INTEGER PRIMARY KEY, 
    customer_name VARCHAR(255),
    password INTEGER,
    address VARCHAR(255),
    payment VARCHAR(255)
);

CREATE TABLE Merchant(
    merchant_ID INTEGER PRIMARY KEY,
    merchant_name VARCHAR(255),
    FOREIGN KEY(product_NO) REFERENCES Product(product_NO)
);

CREATE TABLE Product(
    product_NO INTEGER PRIMARY KEY,
    product_name VARCHAR(255),
    unit_price FLOAT,
    category VARCHAR(255) ,
    merchant_ID INTEGER,
    brand VARCHAR(255),
    stock_level INTEGER,
    FOREIGN KEY(merchant_ID) REFERENCES Merchant(merchant_ID),
    sales INTEGER
);

CREATE TABLE shopping_cart(
    customer_ID INTEGER,
    product_NO INTEGER,
    product_name VARCHAR(255),
    unit_price FLOAT,
    quantity INTEGER,
    total_price FLOAT,
    FOREIGN KEY(customer_ID) REFERENCES Customer(customer_ID),
    FOREIGN KEY(product_NO) REFERENCES Product(product_NO),
    FOREIGN KEY(product_name) REFERENCES Product(product_name),
    FOREIGN KEY(unit_price) REFERENCES Product(unit_price)
);

CREATE TABLE "Order" (
    order_NO INTEGER PRIMARY KEY,
    customer_ID INTEGER,
    order_date DATE,
    product_NO INTEGER,
    quantity INTEGER,
    total_price FLOAT,
    payment VARCHAR(255),
    address VARCHAR(255),
    FOREIGN KEY(customer_ID) REFERENCES Customer(customer_ID),
    FOREIGN KEY(product_NO) REFERENCES Product(product_NO),
    FOREIGN KEY(product_name) REFERENCES Product(product_name),
    FOREIGN KEY(payment) REFERENCES Customer(payment),
    FOREIGN KEY(customer_name) REFERENCES Customer(customer_name),
    FOREIGN KEY(address) REFERENCES Customer(address)
);

CREATE TABLE report(
    date_of_report DATE PRIMARY KEY,
    customer_ID INTEGER,
    merchant_ID INTEGER,
    merchant_name VARCHAR(255),
    product_NO INTEGER,
    unit_price FLOAT,
    brand VARCHAR(255),
    inventory INTEGER,
    FOREIGN KEY(customer_ID) REFERENCES Customer(customer_ID),
    FOREIGN KEY(merchant_ID) REFERENCES Merchant(merchant_ID),
    FOREIGN KEY(merchant_name) REFERENCES Merchant(merchant_name),
    FOREIGN KEY(product_NO) REFERENCES Product(product_NO),
    FOREIGN KEY(unit_price) REFERENCES Product(unit_price),
    FOREIGN KEY(brand) REFERENCES Product(brand),
    FOREIGN KEY(inventory) REFERENCES Product(inventory),
    FOREIGN KEY(sales) REFERENCES Product(sales)

);

CREATE TABLE Contain(
    product_NO INTEGER,
    customer_ID INTEGER,
    FOREIGN KEY(product_NO) REFERENCES Product(product_NO),
    FOREIGN KEY(customer_ID) REFERENCES Customer(customer_ID)
);

CREATE TABLE Provide(
    product_NO INTEGER,
    customer_ID INTEGER,
    FOREIGN KEY(product_NO) REFERENCES Product(product_NO),
    FOREIGN KEY(customer_ID) REFERENCES Customer(customer_ID)
);

CREATE TABLE Purchase(
    product_NO INTEGER,
    merchant_ID INTEGER,
    FOREIGN KEY(product_NO) REFERENCES Product(product_NO),
    FOREIGN KEY(merchant_ID) REFERENCES Merchant(merchant_ID)
);


