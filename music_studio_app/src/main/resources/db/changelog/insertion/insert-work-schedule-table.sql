INSERT INTO work_schedule (employee_id, week_day, time_to_come, work_length)
VALUES ((SELECT employees.id
         FROM employees
                  INNER JOIN users ON (employees.user_id = users.id)
         WHERE (users.name = 'Mike')), 1, '10:00:00', 8),
       ((SELECT employees.id
         FROM employees
                  INNER JOIN users ON (employees.user_id = users.id)
         WHERE (users.name = 'Mike')), 2, '10:00:00', 8),
       ((SELECT employees.id
         FROM employees
                  INNER JOIN users ON (employees.user_id = users.id)
         WHERE (users.name = 'Mike')), 3, '10:00:00', 8),
       ((SELECT employees.id
         FROM employees
                  INNER JOIN users ON (employees.user_id = users.id)
         WHERE (users.name = 'Mike')), 4, '10:00:00', 8),
       ((SELECT employees.id
         FROM employees
                  INNER JOIN users ON (employees.user_id = users.id)
         WHERE (users.name = 'Mike')), 5, '10:00:00', 6),
       ((SELECT employees.id
         FROM employees
                  INNER JOIN users ON (employees.user_id = users.id)
         WHERE (users.name = 'Tom')), 1, '10:00:00', 8),
       ((SELECT employees.id
         FROM employees
                  INNER JOIN users ON (employees.user_id = users.id)
         WHERE (users.name = 'Tom')), 2, '10:00:00', 8),
       ((SELECT employees.id
         FROM employees
                  INNER JOIN users ON (employees.user_id = users.id)
         WHERE (users.name = 'Tom')), 3, '10:00:00', 8),
       ((SELECT employees.id
         FROM employees
                  INNER JOIN users ON (employees.user_id = users.id)
         WHERE (users.name = 'Tom')), 4, '10:00:00', 8),
       ((SELECT employees.id
         FROM employees
                  INNER JOIN users ON (employees.user_id = users.id)
         WHERE (users.name = 'Tom')), 5, '10:00:00', 6),
       ((SELECT employees.id
         FROM employees
                  INNER JOIN users ON (employees.user_id = users.id)
         WHERE (users.name = 'Tatiana')), 1, '11:00:00', 7),
       ((SELECT employees.id
         FROM employees
                  INNER JOIN users ON (employees.user_id = users.id)
         WHERE (users.name = 'Tatiana')), 2, '11:00:00', 8),
       ((SELECT employees.id
         FROM employees
                  INNER JOIN users ON (employees.user_id = users.id)
         WHERE (users.name = 'Tatiana')), 3, '11:00:00', 7),
       ((SELECT employees.id
         FROM employees
                  INNER JOIN users ON (employees.user_id = users.id)
         WHERE (users.name = 'Tatiana')), 4, '11:00:00', 8),
       ((SELECT employees.id
         FROM employees
                  INNER JOIN users ON (employees.user_id = users.id)
         WHERE (users.name = 'Tatiana')), 5, '11:00:00', 10);