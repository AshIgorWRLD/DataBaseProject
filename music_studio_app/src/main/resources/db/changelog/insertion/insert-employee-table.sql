INSERT INTO employees (user_id, post)
VALUES ((SELECT users.id
         FROM users
         WHERE users.name = 'Mike'), 'producer'),
       ((SELECT users.id
         FROM users
         WHERE users.name = 'Tom'), 'beat-maker'),
       ((SELECT users.id
         FROM users
         WHERE users.name = 'Tatiana'), 'administrator'),
       ((SELECT users.id
         FROM users
         WHERE users.name = 'Igor'), 'owner');