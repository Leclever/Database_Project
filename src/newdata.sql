//customer 
INSERT INTO Customer VALUES (1, 'John Doe', 12345, '123 Main St, Anytown', 'Credit Card');
INSERT INTO Customer VALUES (2, 'Jane Smith', 67890, '456 Oak Dr, Somecity', 'Debit Card');
INSERT INTO Customer VALUES (3, 'Robert Johnson', 11223, '789 Pine Rd, Othertown', 'Paypal');
INSERT INTO Customer VALUES (4, 'Alice Williams', 44556, '111 Tree St, Cityville', 'Credit Card');
INSERT INTO Customer VALUES (5, 'Bob Brown', 77889, '222 River Rd, Townville', 'Debit Card');
INSERT INTO Customer VALUES (6, 'Charlie Davis', 33221, '333 Hill Ln, Villecity', 'Paypal');
INSERT INTO Customer VALUES (7, 'David Miller', 99331, '444 Peak Pl, Villeton', 'Credit Card');
INSERT INTO Customer VALUES (8, 'Eva Garcia', 66332, '555 Beach Blvd, Coastown', 'Debit Card');
INSERT INTO Customer VALUES (9, 'Frank Wilson', 33445, '666 Lake Ln, Laketon', 'Paypal');
INSERT INTO Customer VALUES (10, 'Grace Martinez', 99884, '777 Star St, Skyville', 'Credit Card');

// merchant
INSERT INTO Merchant VALUES (1, 'Apple', 101);
INSERT INTO Merchant VALUES (2, 'Apple', 102);
INSERT INTO Merchant VALUES (3, 'XIAOMI', 103);
INSERT INTO Merchant VALUES (4, 'XIOAMI', 104);
INSERT INTO Merchant VALUES (5, 'HUAWEI', 105);
INSERT INTO Merchant VALUES (6, 'Apple', 106);
INSERT INTO Merchant VALUES (7, 'HUAWEI', 107);
INSERT INTO Merchant VALUES (8, 'XIAOMI', 108);
INSERT INTO Merchant VALUES (9, 'Apple', 109);
INSERT INTO Merchant VALUES (10, 'HUAWEI', 110);

//product
INSERT INTO Product VALUES (101, 'phone', 19.99, 1, 1, 'apple', 100, 50;
INSERT INTO Product VALUES (102, 'ipad', 29.99, 2, 2, 'HUWEI', 200, 60);
INSERT INTO Product VALUES (103, 'computer', 39.99, 3, 2, 'XIAOMI', 150, 70);
INSERT INTO Product VALUES (104, 'earphone', 49.99, 4, 2, 'apple', 180, 80);
INSERT INTO Product VALUES (105, 'mouse', 59.99, 2, 5, 'HUWEI', 170, 90);
INSERT INTO Product VALUES (106, 'phone', 89.99, 3, 6, 'XIAOMI', 120, 30);
INSERT INTO Product VALUES (107, 'ipad', 99.99, 1, 7, 'HUWEI', 130, 40);
INSERT INTO Product VALUES (108, 'mouse', 79.99, 2, 8, 'XIAOMI', 140, 50);
INSERT INTO Product VALUES (109, 'computer', 69.99, 9, 5, 'HUWEI', 150, 60);
INSERT INTO Product VALUES (110, 'ipad', 59.99, 1, 10, 'apple', 160, 70);

//shopping_cart
INSERT INTO shopping_cart VALUES (1, 101, 'Phone', 19.99, 2, 19.99*2);
INSERT INTO shopping_cart VALUES (2, 102, 'HUAWEI', 29.99, 1, 29.99*1);
INSERT INTO shopping_cart VALUES (3, 103, 'ipad', 39.99, 3, 39.99*3);
INSERT INTO shopping_cart VALUES (4, 104, 'HUAWEI', 49.99, 2, 49.99*2);
INSERT INTO shopping_cart VALUES (2, 107, 'ipad', 59.99, 1, 59.99*1);
INSERT INTO shopping_cart VALUES (6, 101, 'Phone', 19.99, 2, 19.99*2);
INSERT INTO shopping_cart VALUES (7, 102, 'HUAWEI', 29.99, 1, 29.99*1);
INSERT INTO shopping_cart VALUES (8, 103, 'ipad', 39.99, 3, 39.99*3);
INSERT INTO shopping_cart VALUES (6, 104, 'HUAWEI', 49.99, 2, 49.99*2);
INSERT INTO shopping_cart VALUES (10, 109, 'ipad', 59.99, 1, 59.99*1);

//orders
INSERT INTO "Order" VALUES (1, 1, TO_DATE('2023-11-01', 'YYYY-MM-DD'), 101, 2, 19.99*2, 'Credit Card', '123 Main St, City');
INSERT INTO "Order" VALUES (2, 1, TO_DATE('2023-10-05', 'YYYY-MM-DD'), 101, 2, 19.99*2, 'Credit Card', '123 Main St, City');
INSERT INTO "Order" VALUES (3, 1, TO_DATE('2023-06-03', 'YYYY-MM-DD'), 102, 1, 29.99*1, 'Credit Card', '123 Main St, City');
INSERT INTO "Order" VALUES (4, 2, TO_DATE('2023-08-01', 'YYYY-MM-DD'), 103, 3, 39.99*3, 'PayPal', '456 Maple Dr, City');
INSERT INTO "Order" VALUES (5, 2, TO_DATE('2023-04-10', 'YYYY-MM-DD'), 104, 2, 49.99*2, 'PayPal', '456 Maple Dr, City');
INSERT INTO "Order" VALUES (6, 9, TO_DATE('2023-03-01', 'YYYY-MM-DD'), 105, 1, 59.99*1, 'PayPal', '456 Maple Dr, City');
INSERT INTO "Order" VALUES (7, 3, TO_DATE('2023-08-26', 'YYYY-MM-DD'), 106, 2, 89.99*2, 'Debit Card', '789 Oak St, Cityj');
INSERT INTO "Order" VALUES (8, 3, TO_DATE('2023-01-01', 'YYYY-MM-DD'), 107, 3, 99.99*3, 'Debit Card', '789 Oak St, City');
INSERT INTO "Order" VALUES (9, 4, TO_DATE('2023-09-26', 'YYYY-MM-DD'), 108, 1, 79.99*1, 'PayPal', '321 Pine Dr, City');
INSERT INTO "Order" VALUES (10, 4, TO_DATE('2023-03-01', 'YYYY-MM-DD'), 109, 2, 69.99*2, 'PayPal', '321 Pine Dr, City');

//report
INSERT INTO report VALUES (DATE '2023-11-29', 1, 1, 'Merchant 1', 101, 19.99, 'Apple', 100);
INSERT INTO report VALUES (DATE '2023-01-01', 2, 2, 'Merchant 2', 102, 29.99, 'HUWEI', 200);
INSERT INTO report VALUES (DATE '2023-12-01', 3, 3, 'Merchant 3', 103, 39.99, 'XIAOMI', 300);
INSERT INTO report VALUES (DATE '2023-03-01', 4, 4, 'Merchant 4', 104, 49.99, 'HUWEI', 400);
INSERT INTO report VALUES (DATE '2023-04-01', 5, 5, 'Merchant 5', 105, 59.99, 'Apple', 500);
INSERT INTO report VALUES (DATE '2023-05-01', 6, 1, 'Merchant 6', 106, 69.99, 'XIAOMI', 600);
INSERT INTO report VALUES (DATE '2023-07-01', 7, 2, 'Merchant 7', 107, 79.99, 'HUWEI', 700);
INSERT INTO report VALUES (DATE '2023-06-01', 8, 3, 'Merchant 8', 108, 89.99, 'Apple', 800);
INSERT INTO report VALUES (DATE '2023-09-01', 9, 4, 'Merchant 9', 109, 99.99, 'XIAOMI', 900);
INSERT INTO report VALUES (DATE '2023-08-01', 10, 5, 'Merchant 10', 110, 109.99, 'HUWEI', 1000);

// Contain 
INSERT INTO Contain VALUES (101, 1);
INSERT INTO Contain VALUES (102, 2);
INSERT INTO Contain VALUES (103, 3);
INSERT INTO Contain VALUES (104, 4);
INSERT INTO Contain VALUES (105, 5);
INSERT INTO Contain VALUES (106, 6);
INSERT INTO Contain VALUES (107, 7);
INSERT INTO Contain VALUES (108, 8);
INSERT INTO Contain VALUES (109, 9);
INSERT INTO Contain VALUES (110, 10);

//proivde
INSERT INTO Provide VALUES (101, 1);
INSERT INTO Provide VALUES (102, 2);
INSERT INTO Provide VALUES (103, 3);
INSERT INTO Provide VALUES (104, 4);
INSERT INTO Provide VALUES (105, 5);
INSERT INTO Provide VALUES (106, 6);
INSERT INTO Provide VALUES (107, 7);
INSERT INTO Provide VALUES (108, 8);
INSERT INTO Provide VALUES (109, 9);
INSERT INTO Provide VALUES (110, 10);

//Purchase
INSERT INTO Purchase VALUES (101, 1);
INSERT INTO Purchase VALUES (102, 2);
INSERT INTO Purchase VALUES (103, 3);
INSERT INTO Purchase VALUES (104, 4);
INSERT INTO Purchase VALUES (105, 5);
INSERT INTO Purchase VALUES (106, 6);
INSERT INTO Purchase VALUES (107, 7);
INSERT INTO Purchase VALUES (108, 8);
INSERT INTO Purchase VALUES (109, 9);
INSERT INTO Purchase VALUES (110, 10);

//Communicate
INSERT INTO Communicate VALUES (101, 1);
INSERT INTO Communicate VALUES (102, 2);
INSERT INTO Communicate VALUES (103, 3);
INSERT INTO Communicate VALUES (104, 4);
INSERT INTO Communicate VALUES (105, 5);
INSERT INTO Communicate VALUES (106, 6);
INSERT INTO Communicate VALUES (107, 7);
INSERT INTO Communicate VALUES (108, 8);
INSERT INTO Communicate VALUES (109, 9);
INSERT INTO Communicate VALUES (110, 10);
