INSERT INTO clients (user_id, type)
VALUES ((SELECT users.id
         FROM users
         WHERE users.name = 'Alexander'), 'visitor'),
       ((SELECT users.id
         FROM users
         WHERE users.name = 'Ye'), 'artist'),
       ((SELECT users.id
         FROM users
         WHERE users.name = 'Jared'), 'artist'),
       ((SELECT users.id
         FROM users
         WHERE users.name = 'Quavious'), 'artist'),
       ((SELECT users.id
         FROM users
         WHERE users.name = 'Kiari'), 'artist'),
       ((SELECT users.id
         FROM users
         WHERE users.name = 'Kirshnick'), 'artist');
