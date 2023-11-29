
//customer 
INSERT INTO Customer (customer_ID, customer_name, password, address, payment) 
VALUES (1, 'John Doe', 12345, '123 Main St, Anytown', 'Credit Card');

INSERT INTO Customer (customer_ID, customer_name, password, address, payment) 
VALUES (2, 'Jane Smith', 67890, '456 Oak Dr, Somecity', 'Debit Card');

INSERT INTO Customer (customer_ID, customer_name, password, address, payment) 
VALUES (3, 'Robert Johnson', 11223, '789 Pine Rd, Othertown', 'Paypal');

INSERT INTO Customer (customer_ID, customer_name, password, address, payment) 
VALUES (4, 'Alice Williams', 44556, '111 Tree St, Cityville', 'Credit Card');

INSERT INTO Customer (customer_ID, customer_name, password, address, payment) 
VALUES (5, 'Bob Brown', 77889, '222 River Rd, Townville', 'Debit Card');

INSERT INTO Customer (customer_ID, customer_name, password, address, payment) 
VALUES (6, 'Charlie Davis', 33221, '333 Hill Ln, Villecity', 'Paypal');

INSERT INTO Customer (customer_ID, customer_name, password, address, payment) 
VALUES (7, 'David Miller', 99331, '444 Peak Pl, Villeton', 'Credit Card');

INSERT INTO Customer (customer_ID, customer_name, password, address, payment) 
VALUES (8, 'Eva Garcia', 66332, '555 Beach Blvd, Coastown', 'Debit Card');

INSERT INTO Customer (customer_ID, customer_name, password, address, payment) 
VALUES (9, 'Frank Wilson', 33445, '666 Lake Ln, Laketon', 'Paypal');

INSERT INTO Customer (customer_ID, customer_name, password, address, payment) 
VALUES (10, 'Grace Martinez', 99884, '777 Star St, Skyville', 'Credit Card');




// merchant
INSERT INTO Merchant (merchant_ID, merchant_name, product_NO) 
VALUES (1, 'Apple', 101);

INSERT INTO Merchant (merchant_ID, merchant_name, product_NO) 
VALUES (2, 'Apple', 102);

INSERT INTO Merchant (merchant_ID, merchant_name, product_NO) 
VALUES (3, 'XIAOMI', 103);

INSERT INTO Merchant (merchant_ID, merchant_name, product_NO) 
VALUES (4, 'XIOAMI', 104);

INSERT INTO Merchant (merchant_ID, merchant_name, product_NO) 
VALUES (5, 'HUAWEI', 105);

INSERT INTO Merchant (merchant_ID, merchant_name, product_NO) 
VALUES (6, 'Apple', 106);

INSERT INTO Merchant (merchant_ID, merchant_name, product_NO) 
VALUES (7, 'HUAWEI', 107);

INSERT INTO Merchant (merchant_ID, merchant_name, product_NO) 
VALUES (8, 'XIAOMI', 108);

INSERT INTO Merchant (merchant_ID, merchant_name, product_NO) 
VALUES (9, 'Apple', 109);

INSERT INTO Merchant (merchant_ID, merchant_name, product_NO) 
VALUES (10, 'HUAWEI', 110);


//product
INSERT INTO Product (product_NO, product_name, unit_price, category, merchant_ID, brand, inventory, sales) 
VALUES (101, 'phone', 19.99, 1, 1, 'apple', 100, 50);

INSERT INTO Product (product_NO, product_name, unit_price, category, merchant_ID, brand, inventory, sales) 
VALUES (102, 'ipad', 29.99, 2, 1, 'HUWEI', 200, 60);

INSERT INTO Product (product_NO, product_name, unit_price, category, merchant_ID, brand, inventory, sales) 
VALUES (103, 'computer', 39.99, 1, 2, 'XIAOMI', 150, 70);

INSERT INTO Product (product_NO, product_name, unit_price, category, merchant_ID, brand, inventory, sales) 
VALUES (104, 'earphone', 49.99, 3, 2, 'apple', 180, 80);

INSERT INTO Product (product_NO, product_name, unit_price, category, merchant_ID, brand, inventory, sales) 
VALUES (105, 'mouse', 59.99, 2, 3, 'HUWEI', 170, 90);

INSERT INTO Product (product_NO, product_name, unit_price, category, merchant_ID, brand, inventory, sales) 
VALUES (106, 'phone', 89.99, 3, 4, 'XIAOMI', 120, 30);

INSERT INTO Product (product_NO, product_name, unit_price, category, merchant_ID, brand, inventory, sales) 
VALUES (107, 'ipad', 99.99, 1, 4, 'HUWEI', 130, 40);

INSERT INTO Product (product_NO, product_name, unit_price, category, merchant_ID, brand, inventory, sales) 
VALUES (108, 'mouse', 79.99, 2, 5, 'XIAOMI', 140, 50);

INSERT INTO Product (product_NO, product_name, unit_price, category, merchant_ID, brand, inventory, sales) 
VALUES (109, 'computer', 69.99, 3, 5, 'HUWEI', 150, 60);

INSERT INTO Product (product_NO, product_name, unit_price, category, merchant_ID, brand, inventory, sales) 
VALUES (110, 'ipad', 59.99, 1, 1, 'apple', 160, 70);




//shopping_cart
INSERT INTO shopping_cart (customer_ID, product_NO, product_name, unit_price, quantity, total_price)
VALUES (1, 101, 'Phone', 19.99, 2, 19.99*2);

INSERT INTO shopping_cart (customer_ID, product_NO, product_name, unit_price, quantity, total_price)
VALUES (2, 102, 'HUAWEI', 29.99, 1, 29.99*1);

INSERT INTO shopping_cart (customer_ID, product_NO, product_name, unit_price, quantity, total_price)
VALUES (3, 103, 'ipad', 39.99, 3, 39.99*3);

INSERT INTO shopping_cart (customer_ID, product_NO, product_name, unit_price, quantity, total_price)
VALUES (4, 104, 'HUAWEI', 49.99, 2, 49.99*2);

INSERT INTO shopping_cart (customer_ID, product_NO, product_name, unit_price, quantity, total_price)
VALUES (2, 105, 'ipad', 59.99, 1, 59.99*1);

INSERT INTO shopping_cart (customer_ID, product_NO, product_name, unit_price, quantity, total_price)
VALUES (6, 101, 'Phone', 19.99, 2, 19.99*2);

INSERT INTO shopping_cart (customer_ID, product_NO, product_name, unit_price, quantity, total_price)
VALUES (7, 102, 'HUAWEI', 29.99, 1, 29.99*1);

INSERT INTO shopping_cart (customer_ID, product_NO, product_name, unit_price, quantity, total_price)
VALUES (8, 103, 'ipad', 39.99, 3, 39.99*3);

INSERT INTO shopping_cart (customer_ID, product_NO, product_name, unit_price, quantity, total_price)
VALUES (9, 104, 'HUAWEI', 49.99, 2, 49.99*2);

INSERT INTO shopping_cart (customer_ID, product_NO, product_name, unit_price, quantity, total_price)
VALUES (10, 105, 'ipad', 59.99, 1, 59.99*1);




//Order
INSERT INTO "Order" (order_NO, customer_ID, order_date, product_NO, quantity, total_price, payment, address)
VALUES (1, 1, '2023-11-28', 101, 2, 19.99*2, 'Credit Card', '123 Main St, City');

INSERT INTO "Order" (order_NO, customer_ID, order_date, product_NO, quantity, total_price, payment, address)
VALUES (2, 1, '2023-11-28', 102, 1, 29.99*1, 'Credit Card', '123 Main St, City');

INSERT INTO "Order" (order_NO, customer_ID, order_date, product_NO, quantity, total_price, payment, address)
VALUES (3, 2, '2023-11-28', 103, 3, 39.99*3, 'PayPal', '456 Maple Dr, City');

INSERT INTO "Order" (order_NO, customer_ID, order_date, product_NO, quantity, total_price, payment, address)
VALUES (4, 2, '2023-11-28', 104, 2, 49.99*2, 'PayPal', '456 Maple Dr, City');

INSERT INTO "Order" (order_NO, customer_ID, order_date, product_NO, quantity, total_price, payment, address)
VALUES (5, 2, '2023-11-28', 105, 1, 59.99*1, 'PayPal', '456 Maple Dr, City');

INSERT INTO "Order" (order_NO, customer_ID, order_date, product_NO, quantity, total_price, payment, address)
VALUES (6, 3, '2023-11-28', 106, 2, 89.99*2, 'Debit Card', '789 Oak St, Cityj');

INSERT INTO "Order" (order_NO, customer_ID, order_date, product_NO, quantity, total_price, payment, address)
VALUES (7, 3, '2023-11-28', 107, 3, 99.99*3, 'Debit Card', '789 Oak St, City');

INSERT INTO "Order" (order_NO, customer_ID, order_date, product_NO, quantity, total_price, payment, address)
VALUES (8, 4, '2023-11-28', 108, 1, 79.99*1, 'PayPal', '321 Pine Dr, City');

INSERT INTO "Order" (order_NO, customer_ID, order_date, product_NO, quantity, total_price, payment, address)
VALUES (9, 4, '2023-11-28', 109, 2, 69.99*2, 'PayPal', '321 Pine Dr, City');

INSERT INTO "Order" (order_NO, customer_ID, order_date, product_NO, quantity, total_price, payment, address)
VALUES (10, 5, '2023-11-28', 110, 3, 59.99*3, 'Credit Card', '654 Elm Ln, City');

//report
INSERT INTO report (report_ID, date_of_report, customer_ID, merchant_ID, merchant_name, product_NO, unit_price, brand, inventory, sales)
VALUES (1, '2023-11-28', 1, 101, 'Merchant 1', 201, 19.99, 'Apple', 100, 50);

INSERT INTO report (report_ID, date_of_report, customer_ID, merchant_ID, merchant_name, product_NO, unit_price, brand, inventory, sales)
VALUES (2, '2023-11-28', 2, 102, 'Merchant 2', 202, 29.99, 'HUWEI', 200, 40);

INSERT INTO report (report_ID, date_of_report, customer_ID, merchant_ID, merchant_name, product_NO, unit_price, brand, inventory, sales)
VALUES (3, '2023-11-28', 3, 103, 'Merchant 3', 203, 39.99, 'XIAOMI', 300, 30);

INSERT INTO report (report_ID, date_of_report, customer_ID, merchant_ID, merchant_name, product_NO, unit_price, brand, inventory, sales)
VALUES (4, '2023-11-28', 4, 104, 'Merchant 4', 204, 49.99, 'HUWEI', 400, 20);

INSERT INTO report (report_ID, date_of_report, customer_ID, merchant_ID, merchant_name, product_NO, unit_price, brand, inventory, sales)
VALUES (5, '2023-11-28', 5, 105, 'Merchant 5', 205, 59.99, 'Apple', 500, 10);

INSERT INTO report (report_ID, date_of_report, customer_ID, merchant_ID, merchant_name, product_NO, unit_price, brand, inventory, sales)
VALUES (6, '2023-11-29', 6, 106, 'Merchant 6', 206, 69.99, 'XIAOMI', 600, 60);

INSERT INTO report (report_ID, date_of_report, customer_ID, merchant_ID, merchant_name, product_NO, unit_price, brand, inventory, sales)
VALUES (7, '2023-11-29', 7, 107, 'Merchant 7', 207, 79.99, 'HUWEI', 700, 70);

INSERT INTO report (report_ID, date_of_report, customer_ID, merchant_ID, merchant_name, product_NO, unit_price, brand, inventory, sales)
VALUES (8, '2023-11-29', 8, 108, 'Merchant 8', 208, 89.99, 'Apple', 800, 80);

INSERT INTO report (report_ID, date_of_report, customer_ID, merchant_ID, merchant_name, product_NO, unit_price, brand, inventory, sales)
VALUES (9, '2023-11-29', 9, 109, 'Merchant 9', 209, 99.99, 'XIAOMI', 900, 90);

INSERT INTO report (report_ID, date_of_report, customer_ID, merchant_ID, merchant_name, product_NO, unit_price, brand, inventory, sales)
VALUES (10, '2023-11-29', 10, 110, 'Merchant 10', 210, 109.99, 'HUWEI', 1000, 100);



// Contain 
INSERT INTO Contain (order_ID, product_NO, customer_ID)
VALUES (1, 201, 1);

INSERT INTO Contain (order_ID, product_NO, customer_ID)
VALUES (2, 202, 2);

INSERT INTO Contain (order_ID, product_NO, customer_ID)
VALUES (3, 203, 3);

INSERT INTO Contain (order_ID, product_NO, customer_ID)
VALUES (4, 204, 4);

INSERT INTO Contain (order_ID, product_NO, customer_ID)
VALUES (5, 205, 5);

INSERT INTO Contain (order_ID, product_NO, customer_ID)
VALUES (6, 206, 6);

INSERT INTO Contain (order_ID, product_NO, customer_ID)
VALUES (7, 207, 7);

INSERT INTO Contain (order_ID, product_NO, customer_ID)
VALUES (8, 208, 8);

INSERT INTO Contain (order_ID, product_NO, customer_ID)
VALUES (9, 209, 9);

INSERT INTO Contain (order_ID, product_NO, customer_ID)
VALUES (10, 210, 10);


//proivde
INSERT INTO Provide (product_NO, merchant_ID)
VALUES (201, 101);

INSERT INTO Provide (product_NO, merchant_ID)
VALUES (202, 102);

INSERT INTO Provide (product_NO, merchant_ID)
VALUES (203, 103);

INSERT INTO Provide (product_NO, merchant_ID)
VALUES (204, 104);

INSERT INTO Provide (product_NO, merchant_ID)
VALUES (205, 105);

INSERT INTO Provide (product_NO, merchant_ID)
VALUES (206, 106);

INSERT INTO Provide (product_NO, merchant_ID)
VALUES (207, 107);

INSERT INTO Provide (product_NO, merchant_ID)
VALUES (208, 108);

INSERT INTO Provide (product_NO, merchant_ID)
VALUES (209, 109);

INSERT INTO Provide (product_NO, merchant_ID)
VALUES (210, 110);



//Purchase
INSERT INTO Purchase (order_ID, product_NO, merchant_ID)
VALUES (1, 201, 101);

INSERT INTO Purchase (order_ID, product_NO, merchant_ID)
VALUES (2, 202, 102);

INSERT INTO Purchase (order_ID, product_NO, merchant_ID)
VALUES (3, 203, 103);

INSERT INTO Purchase (order_ID, product_NO, merchant_ID)
VALUES (4, 204, 104);

INSERT INTO Purchase (order_ID, product_NO, merchant_ID)
VALUES (5, 205, 105);

INSERT INTO Purchase (order_ID, product_NO, merchant_ID)
VALUES (6, 206, 106);

INSERT INTO Purchase (order_ID, product_NO, merchant_ID)
VALUES (7, 207, 107);

INSERT INTO Purchase (order_ID, product_NO, merchant_ID)
VALUES (8, 208, 108);

INSERT INTO Purchase (order_ID, product_NO, merchant_ID)
VALUES (9, 209, 109);

INSERT INTO Purchase (order_ID, product_NO, merchant_ID)
VALUES (10, 210, 110);
