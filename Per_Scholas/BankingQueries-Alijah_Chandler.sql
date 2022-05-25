SELECT name AS "Product", product_type_cd AS "Type" FROM product;

SELECT branch.NAME, branch.CITY, employee.LAST_NAME, employee.TITLE
FROM employee
LEFT JOIN branch
ON employee.ASSIGNED_BRANCH_ID = branch.BRANCH_ID;

SELECT DISTINCT(title) FROM employee;

SELECT e1.LAST_NAME, e1.title, e2.LAST_NAME, e2.TITLE
FROM employee e1
left JOIN employee e2
ON e1.SUPERIOR_EMP_ID = e2.EMP_ID;

SELECT product.NAME, account.AVAIL_BALANCE, individual.LAST_NAME
FROM account
join product
ON account.product_cd = product.PRODUCT_CD
join customer
ON account.CUST_ID = customer.CUST_ID
join individual
ON customer.CUST_ID = individual.CUST_ID;

SELECT *
FROM acc_transaction
JOIN account
ON acc_transaction.ACCOUNT_ID = account.ACCOUNT_ID
join customer
ON account.CUST_ID = customer.CUST_ID
join individual
ON customer.CUST_ID = individual.CUST_ID
WHERE individual.LAST_NAME REGEXP('^T.*');