INSERT INTO bills_of_artists (artist_payment_id, artist_bill_id)
VALUES ((SELECT artist_payments.id
         FROM artist_payments
                  INNER JOIN artists ON (artists.id = artist_payments.artist_id)
                  INNER JOIN clients ON (clients.id = artists.client_id)
                  INNER JOIN users ON (users.id = clients.user_id)
         WHERE (users.name = 'Ye' AND artist_payments.amount = 300000)),
        (SELECT artist_bills.id
         FROM artist_bills
         WHERE artist_bills.month_to_work = 3)),
       ((SELECT artist_payments.id
         FROM artist_payments
                  INNER JOIN artists ON (artists.id = artist_payments.artist_id)
                  INNER JOIN clients ON (clients.id = artists.client_id)
                  INNER JOIN users ON (users.id = clients.user_id)
         WHERE (users.name = 'Jared' AND artist_payments.amount = 250000)),
        (SELECT artist_bills.id
         FROM artist_bills
         WHERE artist_bills.month_to_work = 3)),
       ((SELECT artist_payments.id
         FROM artist_payments
                  INNER JOIN artists ON (artists.id = artist_payments.artist_id)
                  INNER JOIN clients ON (clients.id = artists.client_id)
                  INNER JOIN users ON (users.id = clients.user_id)
         WHERE (users.name = 'Quavious' AND artist_payments.amount = 200000)),
        (SELECT artist_bills.id
         FROM artist_bills
         WHERE artist_bills.month_to_work = 3)),
       ((SELECT artist_payments.id
         FROM artist_payments
                  INNER JOIN artists ON (artists.id = artist_payments.artist_id)
                  INNER JOIN clients ON (clients.id = artists.client_id)
                  INNER JOIN users ON (users.id = clients.user_id)
         WHERE (users.name = 'Kiari' AND artist_payments.amount = 200000)),
        (SELECT artist_bills.id
         FROM artist_bills
         WHERE artist_bills.month_to_work = 3)),
       ((SELECT artist_payments.id
         FROM artist_payments
                  INNER JOIN artists ON (artists.id = artist_payments.artist_id)
                  INNER JOIN clients ON (clients.id = artists.client_id)
                  INNER JOIN users ON (users.id = clients.user_id)
         WHERE (users.name = 'Kirshnick' AND artist_payments.amount = 150000)),
        (SELECT artist_bills.id
         FROM artist_bills
         WHERE artist_bills.month_to_work = 3));