Customer:
INSERT INTO VALUES (1, 'John Doe', 12345, '123 Main St', 'Credit Card');
INSERT INTO VALUES (2, 'Jane Smith', 67890, '456 Elm St', 'PayPal');
INSERT INTO VALUES (3, 'Michael Johnson', 54321, '789 Oak St', 'Credit Card');
INSERT INTO VALUES (4, 'Emily Davis', 98765, '321 Pine St', 'Venmo');
INSERT INTO VALUES (5, 'David Wilson', 13579, '654 Cedar St', 'PayPal');
INSERT INTO VALUES (6, 'John Doe', 12345, '123 Main St', 'Credit Card');
INSERT INTO VALUES (7, 'John Doe', 12345, '123 Main St', 'Credit Card');
INSERT INTO VALUES (8, 'John Doe', 12345, '123 Main St', 'Credit Card');
INSERT INTO VALUES (9, 'John Doe', 12345, '123 Main St', 'Credit Card');
INSERT INTO VALUES (10, 'John Doe', 12345, '123 Main St', 'Credit Card');

Merchant:
INSERT INTO VALUES (101, 'Merchant A', 1001)
INSERT INTO VALUES (102, 'Merchant B', 1002);
INSERT INTO VALUES (103, 'Merchant C', 1003);
INSERT INTO VALUES (104, 'Merchant D', 1004);
INSERT INTO VALUES (105, 'Merchant E', 1005);

Product:
INSERT INTO VALUES (1001, 'Product A', 9.99, 'Electronics', 101, 'Brand X', 50, 100);
INSERT INTO VALUES (1002, 'Product B', 19.99, 'Clothing', 102, 'Brand Y', 30, 50);
INSERT INTO VALUES (1003, 'Product C', 14.99, 'Home Decor', 103, 'Brand Z', 20, 80);
INSERT INTO VALUES (1004, 'Product D', 7.99, 'Electronics', 104, 'Brand X', 15, 120);
INSERT INTO VALUES (1005, 'Product E', 12.99, 'Clothing', 105, 'Brand Y', 10, 70);
INSERT INTO VALUES (1006, 'Product F', 8.99, 'Home Decor', 101, 'Brand Z', 25, 90);
INSERT INTO VALUES (1007, 'Product G', 16.99, 'Electronics', 102, 'Brand X', 40, 60);
INSERT INTO VALUES (1008, 'Product H', 14.99, 'Clothing', 103, 'Brand Y', 30, 110);
INSERT INTO VALUES (1009, 'Product I', 10.99, 'Home Decor', 104, 'Brand Z', 20, 100);
INSERT INTO VALUES (1010, 'Product J', 16.99, 'Electronics', 105, 'Brand X', 15, 80);

shopping_cart:
INSERT INTO VALUES (1, 1001, 'Product A', 9.99, 2, 19.98);
INSERT INTO VALUES (1, 1003, 'Product C', 14.99, 1, 14.99);
INSERT INTO VALUES (2, 1002, 'Product B', 19.99, 3, 59.97);
INSERT INTO VALUES (2, 1005, 'Product E', 12.99, 2, 25.98);
INSERT INTO VALUES (3, 1004, 'Product D', 7.99, 5, 39.95);

Order:
INSERT INTO VALUES (1, 1, TO_DATE('2023-11-01', 'YYYY-MM-DD'), 1001, 2, 19.98, 'Credit Card', '123 Main St');
INSERT INTO VALUES (2, 1, TO_DATE('2023-11-05', 'YYYY-MM-DD'), 1003, 1, 14.99, 'PayPal', '123 Main St');
INSERT INTO VALUES (3, 2, TO_DATE('2023-11-03', 'YYYY-MM-DD'), 1002, 3, 59.97, 'Credit Card', '456 Elm St');
INSERT INTO VALUES (4, 2, TO_DATE('2023-11-06', 'YYYY-MM-DD'), 1005, 2, 25.98, 'Credit Card', '456 Elm St');
INSERT INTO VALUES (5, 3, TO_DATE('2023-11-02', 'YYYY-MM-DD'), 1004, 5, 39.95, 'PayPal', '789 Oak St');

report:
INSERT INTO VALUES (DATE '2023-11-29', 6, 106, 'Merchant F', 1011, 16.99, 'Brand Y', 15);
INSERT INTO VALUES (DATE '2023-11-29', 7, 107, 'Merchant G', 1012, 13.99, 'Brand Z', 25);
INSERT INTO VALUES (DATE '2023-11-30', 8, 108, 'Merchant H', 1013, 9.99, 'Brand X', 35);
INSERT INTO VALUES (DATE '2023-11-30', 9, 109, 'Merchant I', 1014, 12.99, 'Brand Y', 20);
INSERT INTO VALUES (DATE '2023-11-30', 10, 110, 'Merchant J', 1015, 7.99, 'Brand Z', 50);
INSERT INTO VALUES (DATE '2023-11-30', 6, 106, 'Merchant F', 1016, 11.99, 'Brand X', 40);
INSERT INTO VALUES (DATE '2023-11-30', 7, 107, 'Merchant G', 1017, 18.99, 'Brand Y', 30);
INSERT INTO VALUES (DATE '2023-11-30', 8, 108, 'Merchant H', 1018, 14.99, 'Brand Z', 10);
INSERT INTO VALUES (DATE '2023-11-30', 9, 109, 'Merchant I', 1019, 10.99, 'Brand X', 30);
INSERT INTO VALUES (DATE '2023-11-30', 10, 110, 'Merchant J', 1020, 16.99, 'Brand Y', 20);

