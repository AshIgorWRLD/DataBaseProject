INSERT INTO artists (client_id, group_id, stage_name, genre, creation_date)
VALUES ((SELECT clients.id
         FROM clients
                  INNER JOIN users ON (users.id = clients.user_id)
         WHERE users.name = 'Ye'), null, 'Kanye West', 'Hip-hop', '14/04/2001'),
       ((SELECT clients.id
         FROM clients
                  INNER JOIN users ON (users.id = clients.user_id)
         WHERE users.name = 'Jared'), null, 'Juice WRLD', 'Emo-Rap', '14/04/2018'),
       ((SELECT clients.id
         FROM clients
                  INNER JOIN users ON (users.id = clients.user_id)
         WHERE users.name = 'Quavious'),
        (SELECT groups.id
         FROM groups
         WHERE (groups.name = 'Migos')), 'Quavo', 'Trap', '14/04/2008'),
       ((SELECT clients.id
         FROM clients
                  INNER JOIN users ON (users.id = clients.user_id)
         WHERE users.name = 'Kiari'),
        (SELECT groups.id
         FROM groups
         WHERE (groups.name = 'Migos')), 'Offset', 'Trap', '14/04/2008'),
       ((SELECT clients.id
         FROM clients
                  INNER JOIN users ON (users.id = clients.user_id)
         WHERE users.name = 'Kirshnick'),
        (SELECT groups.id
         FROM groups
         WHERE (groups.name = 'Migos')), 'Takeoff', 'Trap', '14/04/2008');