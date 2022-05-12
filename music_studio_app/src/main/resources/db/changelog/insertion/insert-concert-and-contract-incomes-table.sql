INSERT INTO concert_and_contract_incomes (artist_or_group_id, type, name, money_amount)
VALUES ((SELECT artist_pages.id
         FROM artist_pages
                  INNER JOIN artists ON (artist_pages.solo_artist_id = artists.id)
                  INNER JOIN clients ON (artists.client_id = clients.id)
                  INNER JOIN users ON (clients.user_id = users.id)
         WHERE (users.name = 'Ye')), 'concert', 'Rolling Loud', 100000),
       ((SELECT artist_pages.id
         FROM artist_pages
                  INNER JOIN artists ON (artist_pages.solo_artist_id = artists.id)
                  INNER JOIN clients ON (artists.client_id = clients.id)
                  INNER JOIN users ON (clients.user_id = users.id)
         WHERE (users.name = 'Jared')), 'concert', 'Rolling Loud', 80000),
       ((SELECT artist_pages.id
         FROM artist_pages
                  INNER JOIN groups ON (artist_pages.group_id = groups.id)
         WHERE (groups.name = 'Migos')), 'contract', 'Interscope Records', 500000);