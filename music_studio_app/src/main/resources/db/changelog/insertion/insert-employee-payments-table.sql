INSERT INTO employee_payments (employee_id, employee_bill_id, amount)
VALUES ((SELECT employees.id
         FROM employees
                  INNER JOIN users ON (users.id = employees.user_id)
         WHERE (users.name = 'Mike')),
        (SELECT employee_bills.id
         FROM employee_bills
         WHERE (pay_day = '2010/01/05' AND month_to_work = 1)), 60000),
       ((SELECT employees.id
         FROM employees
                  INNER JOIN users ON (users.id = employees.user_id)
         WHERE (users.name = 'Tom')),
        (SELECT employee_bills.id
         FROM employee_bills
         WHERE (pay_day = '2010/01/05' AND month_to_work = 1)), 50000),
       ((SELECT employees.id
         FROM employees
                  INNER JOIN users ON (users.id = employees.user_id)
         WHERE (users.name = 'Tatiana')),
        (SELECT employee_bills.id
         FROM employee_bills
         WHERE (pay_day = '2010/01/05' AND month_to_work = 1)), 40000),
       ((SELECT employees.id
         FROM employees
                  INNER JOIN users ON (users.id = employees.user_id)
         WHERE (users.name = 'Igor')),
        (SELECT employee_bills.id
         FROM employee_bills
         WHERE (pay_day = '2010/01/05' AND month_to_work = 1)), 70000);