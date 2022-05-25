SELECT productName AS "Name",
productLine AS "Product Line",
buyPrice AS "Buy Price"
FROM products
ORDER BY buyPrice DESC;

SELECT contactFirstName AS "First Name", 
contactLastName AS "Last Name", 
city AS "City"
FROM customers
ORDER BY contactLastName ASC;

SELECT DISTINCT STATUS
FROM orders
ORDER BY STATUS DESC;

SELECT * FROM payments
WHERE paymentDate >= '2005-01-01'
ORDER BY paymentDate ASC;

SELECT lastName, firstName, email, jobTitle
FROM employees
WHERE officeCode
IN (SELECT officeCode FROM offices WHERE city = 'San Francisco')
ORDER BY lastName;

SELECT productName, productLine, productScale, productVendor
FROM products
WHERE productLine = 'Classic Cars' OR productLine = 'Vintage Cars'
ORDER BY productLine DESC, productName;