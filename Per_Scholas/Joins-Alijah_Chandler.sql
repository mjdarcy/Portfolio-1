/*Write a query to display each customer’s name (as “Customer Name”) 
alongside the name of the employee who is responsible for that customer’s orders.
 The employee name should be in a single “Sales Rep” column formatted as “lastName,
  firstName”. The output should be sorted alphabetically by customer name.
classicmodels*/


SELECT customerName AS "Customer Name", concat(employees.lastName, ' ', employees.firstName) AS "Sales Rep"
FROM customers
JOIN employees
ON salesRepEmployeeNumber = employeeNumber
ORDER BY customerName;


/*
2. Determine which products are most popular with our customers. For each product,
 list the total quantity ordered along with the total sale generated (total quantity
  ordered * priceEach) for that product. The column headers should be “Product Name”, “Total # Ordered” and “Total Sale”. List the products by Total Sale descending.
*/

SELECT productName AS "Product Name", sum(orderdetails.quantityOrdered) AS "Total # Ordered", sum(orderdetails.quantityOrdered * orderdetails.priceEach) AS 'Total Sale'
FROM products
JOIN orderdetails
ON products.productCode = orderdetails.productCode
GROUP BY productName
ORDER BY 3;
/*
3. Write a query that lists order status and the # of orders with that status.
 Column headers should be “Order Status” and “# Orders”. Sort alphabetically by status.
*/

SELECT orders.status AS "Order Status", COUNT(orders.status) AS "# Orders"
FROM orders
GROUP BY orders.status
ORDER BY orders.status;
/*
4. Write a query to list, for each product line, the total # of products sold
 from that product line. The first column should be “Product Line” and the second
  should be “# Sold”. Order by the second column descending.
*/
SELECT productlines.productLine, orderdetails.quantityOrdered * COUNT(productlines.productLine) AS "# Sold"
FROM productlines
JOIN products
ON productlines.productLine = products.productLine
JOIN orderdetails
ON products.productCode = orderdetails.productCode
GROUP BY productlines.productLine
ORDER BY 2 DESC;
/*
5. For each employee who represents customers, output the total # of orders that
 employee’s customers have placed alongside the total sale amount of those orders.
  The employee name should be output as a single column named “Sales Rep” formatted 
  as “lastName, firstName”. The second column should be titled “# Orders” and the
   third should be “Total Sales”. Sort the output by Total Sales descending. Only
	 (and all) employees with the job title ‘Sales Rep’ should be included in the 
	 output, and if the employee made no sales the Total Sales should display as “0.00”.
*/
SELECT CONCAT(employees.lastName, " ", employees.firstName) AS "Sales Rep", COUNT(orders.orderNumber) AS "# Orders", COUNT(orders.orderNumber) * orderdetails.quantityOrdered AS "Total Sales"
FROM employees
JOIN customers
ON employees.employeeNumber = customers.salesRepEmployeeNumber
JOIN orders
ON customers.customerNumber = orders.customerNumber
JOIN orderdetails
ON orders.orderNumber = orderdetails.orderNumber
GROUP BY 1
ORDER BY 3 DESC;
/*
6. Your product team is requesting data to help them create a bar-chart of monthly
 sales since the company’s inception. Write a query to output the month (January, February, etc.),
  4-digit year, and total sales for that month. The first column should be labeled 
  ‘Month’, the second ‘Year’, and the third should be ‘Payments Received’. 
  Values in the third column should be formatted as numbers with two decimals – for example 694,292.68.
*/
SELECT EXTRACT(MONTH FROM orders.orderDate) AS 'Month', extract(year from orders.orderDate) AS 'Year', cast(sum(orderdetails.quantityOrdered) AS DECIMAL(10, 2)) AS 'Payments Received'
FROM orders
JOIN orderdetails
ON orderdetails.orderNumber = orders.orderNumber
GROUP BY 1;