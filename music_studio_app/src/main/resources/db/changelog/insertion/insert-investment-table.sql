INSERT INTO investments (investor_id, recipient_id, money_amount)
VALUES ((SELECT investors.id
         FROM investors
                  INNER JOIN users ON (users.id = investors.user_id)
         WHERE (users.name = 'Bill')),
        (SELECT artist_pages.id
         FROM artist_pages
                  INNER JOIN artists ON (artist_pages.solo_artist_id = artists.id)
                  INNER JOIN clients ON (artists.client_id = clients.id)
                  INNER JOIN users ON (users.id = clients.user_id)
         WHERE (users.name = 'Ye')), 20000),
       ((SELECT investors.id
         FROM investors
                  INNER JOIN users ON (users.id = investors.user_id)
         WHERE (users.name = 'Elon')),
        (SELECT artist_pages.id
         FROM artist_pages
                  INNER JOIN artists ON (artist_pages.solo_artist_id = artists.id)
                  INNER JOIN clients ON (artists.client_id = clients.id)
                  INNER JOIN users ON (users.id = clients.user_id)
         WHERE (users.name = 'Jared')), 25000),
       ((SELECT investors.id
         FROM investors
                  INNER JOIN users ON (users.id = investors.user_id)
         WHERE (users.name = 'Elon')),
        (SELECT artist_pages.id
         FROM artist_pages
                  INNER JOIN groups ON (artist_pages.group_id = groups.id)
         WHERE (groups.name = 'Migos')), 20000);