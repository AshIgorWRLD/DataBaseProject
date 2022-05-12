INSERT INTO artist_payments (artist_id, artist_bill_id, amount)
VALUES ((SELECT artists.id
         FROM artists
                  INNER JOIN clients ON (clients.id = artists.client_id)
                  INNER JOIN users ON (users.id = clients.user_id)
         WHERE (users.name = 'Ye')),
        (SELECT artist_bills.id
         FROM artist_bills
         WHERE (pay_day = '2010/01/05' AND month_to_work = 3)), 300000),
       ((SELECT artists.id
         FROM artists
                  INNER JOIN clients ON (clients.id = artists.client_id)
                  INNER JOIN users ON (users.id = clients.user_id)
         WHERE (users.name = 'Jared')),
        (SELECT artist_bills.id
         FROM artist_bills
         WHERE (pay_day = '2010/01/05' AND month_to_work = 3)), 250000),
       ((SELECT artists.id
         FROM artists
                  INNER JOIN clients ON (clients.id = artists.client_id)
                  INNER JOIN users ON (users.id = clients.user_id)
         WHERE (users.name = 'Quavious')),
        (SELECT artist_bills.id
         FROM artist_bills
         WHERE (pay_day = '2010/01/05' AND month_to_work = 3)), 200000),
       ((SELECT artists.id
         FROM artists
                  INNER JOIN clients ON (clients.id = artists.client_id)
                  INNER JOIN users ON (users.id = clients.user_id)
         WHERE (users.name = 'Kiari')),
        (SELECT artist_bills.id
         FROM artist_bills
         WHERE (pay_day = '2010/01/05' AND month_to_work = 3)), 200000),
       ((SELECT artists.id
         FROM artists
                  INNER JOIN clients ON (clients.id = artists.client_id)
                  INNER JOIN users ON (users.id = clients.user_id)
         WHERE (users.name = 'Kirshnick')),
        (SELECT artist_bills.id
         FROM artist_bills
         WHERE (pay_day = '2010/01/05' AND month_to_work = 3)), 150000);