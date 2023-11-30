--Administrators inventory management:
--View the information of all product inventories
SELECT product_NO, product_name, category, Price, brand, stock_level FROM Product;
--View the inventory of the specific product.
SELECT product_NO, product_name, category, Price, brand, stock_level FROM Product WHERE product_NO = &ID;

-- Add products
INSERT INTO Product VALUES (NULL, '&product_name', &unit_price, '&category', &merchantID,'&brand', &stock_level ,&sales);
--delete products
DELETE Product WHERE product_name = '&product_name';
DELETE Product WHERE product_NO = &ID ;

--Update products
--Modify the name of the specific product
UPDATE Product SET product_name = '&product_name' WHERE product_NO = &ID;
--Modify the price of the specific product
UPDATE Product SET unit_price = &unit_price WHERE product_NO = &ID;
--Modify the category of the specific product
UPDATE Product SET category = '&category' WHERE product_NO = &ID;
--Modify the stock level of specific product.
UPDATE Product SET stock_level = &stock_level WHERE product_NO = &ID;

--Administrators analysis report:
--view all information:
SELECT * FROM Order ORDER BY order_date;

--User behavior:
--Specific customer:
--Order info:
SELECT O.order_NO, O.order_date, C.customer_name, P.product_name, O.quantity, O.total_price, O.payment, O.address
FROM Order O JOIN Product P ON O.product_NO = P.product_NO JOIN Customer C ON O.customer_ID = C.customer_ID
WHERE C.customer_ID = '&customer_ID'
ORDER BY C.customer_name, O.order_date;
--Product preferences:
SELECT C.customer_name, P.product_name, P.category, SUM(O.quantity) AS total_quantity
FROM Customer C JOIN Order O ON C.customer_ID = O.customer_ID JOIN Product P ON O.product_NO = P.product_NO
WHERE C.customer_ID = '&customer_ID'
GROUP BY P.product_NO, C.customer_ID
ORDER BY C.customer_ID, total_quantity DESC;
--Category preferences:
SELECT C.customer_name, P.category, SUM(O.quantity) AS total_quantity
FROM Customer C JOIN Order O ON C.customer_ID = O.customer_ID JOIN Product P ON O.product_NO = P.product_NO
WHERE C.customer_ID = '&customer_ID'
GROUP BY P.category, C.customer_ID
ORDER BY C.customer_ID, total_quantity DESC;
--Merchant preferences:
SELECT C.customer_name, M.merchant_name, SUM(O.quantity) AS total_quantity
FROM Customer C JOIN Order O ON C.customer_ID = O.customer_ID JOIN Product P ON O.product_NO = P.product_NO JOIN Merchant M on M.merchant_ID = P.merchant_ID
WHERE C.customer_ID = '&customer_ID'
GROUP BY M.merchant_ID, C.customer_ID
ORDER BY C.customer_ID, total_quantity DESC;

--All customers:
--Order info:
SELECT O.order_NO, O.order_date, C.customer_name, P.product_name, O.quantity, O.total_price, O.payment, O.address
FROM Order O JOIN Product P ON O.product_NO = P.product_NO JOIN Customer C ON O.customer_ID = C.customer_ID
ORDER BY C.customer_name, O.order_date;
--Product preferences:
SELECT C.customer_name, P.product_name, P.category, SUM(O.quantity) AS total_quantity
FROM Customer C JOIN Order O ON C.customer_ID = O.customer_ID JOIN Product P ON O.product_NO = P.product_NO
GROUP BY P.product_NO, C.customer_ID
ORDER BY C.customer_ID, total_quantity DESC;
--Category preferences:
SELECT C.customer_name, P.category, SUM(O.quantity) AS total_quantity
FROM Customer C JOIN Order O ON C.customer_ID = O.customer_ID JOIN Product P ON O.product_NO = P.product_NO
GROUP BY P.category, C.customer_ID
ORDER BY C.customer_ID, total_quantity DESC;
--Merchant preferences:
SELECT C.customer_name, M.merchant_name, SUM(O.quantity) AS total_quantity
FROM Customer C JOIN Order O ON C.customer_ID = O.customer_ID JOIN Product P ON O.product_NO = P.product_NO JOIN Merchant M on M.merchant_ID = P.merchant_ID
GROUP BY  M.merchant_ID, C.customer_ID
ORDER BY C.customer_ID, total_quantity DESC;


--Product sales data:
--Products sales ranking
SELECT product_NO, product_name, category, sales FROM Product ORDER BY sales DESC;
--Products sales ranking under a specific category
SELECT product_NO, product_name, category, sales FROM Product WHERE category = '&category' ORDER BY sales DESC;
--Products sales ranking under a specific merchant
SELECT product_NO, product_name, category, sales FROM Product JOIN Merchant ON Product.merchant_ID = Merchant.merchant_ID
WHERE Merchant.merchant_name = '&merchant_name' ORDER BY sales DESC;
--Merchant sales ranking
SELECT Product.merchant_ID, Merchant.merchant_name, SUM(Product.sales) AS total_sales
FROM Product JOIN Merchant ON Product.merchant_ID = Merchant.merchant_ID
GROUP BY Product.merchant_ID, Merchant.merchant_name
ORDER BY total_sales DESC;

