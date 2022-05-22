INSERT INTO bills_of_employees (employee_payment_id, employee_bill_id)
VALUES ((SELECT employee_payments.id
         FROM employee_payments
                  INNER JOIN employees ON (employees.id = employee_payments.employee_id)
                  INNER JOIN users ON (users.id = employees.user_id)
         WHERE (users.name = 'Mike' AND employee_payments.amount = 60000)),
        (SELECT employee_bills.id
         FROM employee_bills
         WHERE employee_bills.month_to_work = 1)),
       ((SELECT employee_payments.id
         FROM employee_payments
                  INNER JOIN employees ON (employees.id = employee_payments.employee_id)
                  INNER JOIN users ON (users.id = employees.user_id)
         WHERE (users.name = 'Tom' AND employee_payments.amount = 50000)),
        (SELECT employee_bills.id
         FROM employee_bills
         WHERE employee_bills.month_to_work = 1)),
       ((SELECT employee_payments.id
         FROM employee_payments
                  INNER JOIN employees ON (employees.id = employee_payments.employee_id)
                  INNER JOIN users ON (users.id = employees.user_id)
         WHERE (users.name = 'Tatiana' AND employee_payments.amount = 40000)),
        (SELECT employee_bills.id
         FROM employee_bills
         WHERE employee_bills.month_to_work = 1)),
       ((SELECT employee_payments.id
         FROM employee_payments
                  INNER JOIN employees ON (employees.id = employee_payments.employee_id)
                  INNER JOIN users ON (users.id = employees.user_id)
         WHERE (users.name = 'Igor' AND employee_payments.amount = 70000)),
        (SELECT employee_bills.id
         FROM employee_bills
         WHERE employee_bills.month_to_work = 1));