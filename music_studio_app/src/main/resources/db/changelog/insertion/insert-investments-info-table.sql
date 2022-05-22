INSERT INTO investments_info (investment_id, recipient_id)
VALUES ((SELECT investments.id
         FROM investments
                  INNER JOIN artist_pages ON (artist_pages.id = investments.recipient_id)
                  INNER JOIN artists ON (artists.id = artist_pages.solo_artist_id)
                  INNER JOIN investors ON (investors.id = investments.investor_id)
                  INNER JOIN users ON (users.id = investors.user_id)
         WHERE (users.name = 'Bill' AND investments.money_amount = 20000 AND artists.stage_name = 'Kanye West')),
        (SELECT artist_pages.id
         FROM artist_pages
                  INNER JOIN artists ON (artists.id = artist_pages.solo_artist_id)
                  INNER JOIN clients ON (clients.id = artists.client_id)
                  INNER JOIN users ON (users.id = clients.user_id)
         WHERE (users.name = 'Ye'))),
       ((SELECT investments.id
         FROM investments
                  INNER JOIN artist_pages ON (artist_pages.id = investments.recipient_id)
                  INNER JOIN artists ON (artists.id = artist_pages.solo_artist_id)
                  INNER JOIN investors ON (investors.id = investments.investor_id)
                  INNER JOIN users ON (users.id = investors.user_id)
         WHERE (users.name = 'Elon' AND investments.money_amount = 25000 AND artists.stage_name = 'Juice WRLD')),
        (SELECT artist_pages.id
         FROM artist_pages
                  INNER JOIN artists ON (artists.id = artist_pages.solo_artist_id)
                  INNER JOIN clients ON (clients.id = artists.client_id)
                  INNER JOIN users ON (users.id = clients.user_id)
         WHERE (users.name = 'Jared'))),
       ((SELECT investments.id
         FROM investments
                  INNER JOIN artist_pages ON (artist_pages.id = investments.recipient_id)
                  INNER JOIN groups ON (groups.id = artist_pages.group_id)
                  INNER JOIN investors ON (investors.id = investments.investor_id)
                  INNER JOIN users ON (users.id = investors.user_id)
         WHERE (users.name = 'Elon' AND investments.money_amount = 20000 AND groups.name = 'Migos')),
        (SELECT artist_pages.id
         FROM artist_pages
                  INNER JOIN groups ON (groups.id = artist_pages.group_id)
         WHERE (groups.name = 'Migos')));