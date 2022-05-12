INSERT INTO investors (user_id, invested_money, business_part)
VALUES ((SELECT users.id
         FROM users
         WHERE users.name = 'Bill'), 30000, 0.02),
       ((SELECT users.id
         FROM users
         WHERE users.name = 'Elon'), 50000, 0.03);