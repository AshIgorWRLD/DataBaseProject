INSERT INTO visit_schedule (client_id, visit_date, timing, length_of_visit, type)
VALUES ((SELECT clients.id
         FROM clients
                  INNER JOIN users ON (clients.user_id = users.id)
         WHERE (users.name = 'Ye')), '13/04/2022', '12:00:00', 6, 'Work on album'),
       ((SELECT clients.id
         FROM clients
                  INNER JOIN users ON (clients.user_id = users.id)
         WHERE (users.name = 'Ye')), '14/04/2022', '12:00:00', 6, 'Work on album'),
       ((SELECT clients.id
         FROM clients
                  INNER JOIN users ON (clients.user_id = users.id)
         WHERE (users.name = 'Ye')), '15/04/2022', '12:00:00', 4, 'Work on album'),
       ((SELECT clients.id
         FROM clients
                  INNER JOIN users ON (clients.user_id = users.id)
         WHERE (users.name = 'Jared')), '11/04/2022', '12:00:00', 6, 'Session'),
       ((SELECT clients.id
         FROM clients
                  INNER JOIN users ON (clients.user_id = users.id)
         WHERE (users.name = 'Jared')), '12/04/2022', '12:00:00', 6, 'Session'),
       ((SELECT clients.id
         FROM clients
                  INNER JOIN users ON (clients.user_id = users.id)
         WHERE (users.name = 'Quavious')), '18/04/2022', '12:00:00', 6, 'Session'),
       ((SELECT clients.id
         FROM clients
                  INNER JOIN users ON (clients.user_id = users.id)
         WHERE (users.name = 'Kiari')), '18/04/2022', '12:00:00', 6, 'Session'),
       ((SELECT clients.id
         FROM clients
                  INNER JOIN users ON (clients.user_id = users.id)
         WHERE (users.name = 'Kirshnick')), '18/04/2022', '12:00:00', 6, 'Session'),
       ((SELECT clients.id
         FROM clients
                  INNER JOIN users ON (clients.user_id = users.id)
         WHERE (users.name = 'Quavious')), '19/04/2022', '12:00:00', 6, 'Session'),
       ((SELECT clients.id
         FROM clients
                  INNER JOIN users ON (clients.user_id = users.id)
         WHERE (users.name = 'Kiari')), '19/04/2022', '12:00:00', 6, 'Session'),
       ((SELECT clients.id
         FROM clients
                  INNER JOIN users ON (clients.user_id = users.id)
         WHERE (users.name = 'Kirshnick')), '19/04/2022', '12:00:00', 6, 'Session'),
       ((SELECT clients.id
         FROM clients
                  INNER JOIN users ON (clients.user_id = users.id)
         WHERE (users.name = 'Quavious')), '20/04/2022', '12:00:00', 6, 'Session'),
       ((SELECT clients.id
         FROM clients
                  INNER JOIN users ON (clients.user_id = users.id)
         WHERE (users.name = 'Kiari')), '20/04/2022', '12:00:00', 6, 'Session'),
       ((SELECT clients.id
         FROM clients
                  INNER JOIN users ON (clients.user_id = users.id)
         WHERE (users.name = 'Kirshnick')), '20/04/2022', '12:00:00', 6, 'Session'),
       ((SELECT clients.id
         FROM clients
                  INNER JOIN users ON (clients.user_id = users.id)
         WHERE (users.name = 'Alexander')), '21/04/2022', '12:00:00', 2, 'Do his new track beat'),
       ((SELECT clients.id
         FROM clients
                  INNER JOIN users ON (clients.user_id = users.id)
         WHERE (users.name = 'Alexander')), '22/04/2022', '12:00:00', 3, 'Record voice');