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


