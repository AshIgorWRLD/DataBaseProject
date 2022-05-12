INSERT INTO progress (artist_or_group_id, social_media_coefficient, advertisement_companies_coefficient,
                      distribution_coefficient, incomes_coefficient, supposed_success_date)
VALUES ((SELECT artist_pages.id
         FROM artist_pages
                  INNER JOIN artists ON (artist_pages.solo_artist_id = artists.id)
                  INNER JOIN clients ON (artists.client_id = clients.id)
                  INNER JOIN users ON (clients.user_id = users.id)
         WHERE (users.name = 'Ye')), 0.1, 0.1, 0.1, 0.1, '01/01/2024'),
       ((SELECT artist_pages.id
         FROM artist_pages
                  INNER JOIN artists ON (artist_pages.solo_artist_id = artists.id)
                  INNER JOIN clients ON (artists.client_id = clients.id)
                  INNER JOIN users ON (clients.user_id = users.id)
         WHERE (users.name = 'Jared')), 0.1, 0.1, 0.1, 0.1, '01/01/2024'),
       ((SELECT artist_pages.id
         FROM artist_pages
                  INNER JOIN artists ON (artist_pages.solo_artist_id = artists.id)
                  INNER JOIN clients ON (artists.client_id = clients.id)
                  INNER JOIN users ON (clients.user_id = users.id)
         WHERE (users.name = 'Quavious')), 0.1, 0.1, 0.1, 0.1, '01/01/2024'),
       ((SELECT artist_pages.id
         FROM artist_pages
                  INNER JOIN artists ON (artist_pages.solo_artist_id = artists.id)
                  INNER JOIN clients ON (artists.client_id = clients.id)
                  INNER JOIN users ON (clients.user_id = users.id)
         WHERE (users.name = 'Kiari')), 0.1, 0.1, 0.1, 0.1, '01/01/2024'),
       ((SELECT artist_pages.id
         FROM artist_pages
                  INNER JOIN artists ON (artist_pages.solo_artist_id = artists.id)
                  INNER JOIN clients ON (artists.client_id = clients.id)
                  INNER JOIN users ON (clients.user_id = users.id)
         WHERE (users.name = 'Kirshnick')), 0.1, 0.1, 0.1, 0.1, '01/01/2024'),
       ((SELECT artist_pages.id
         FROM artist_pages
                  INNER JOIN groups ON (artist_pages.group_id = groups.id)
         WHERE (groups.name = 'Migos')), 0.1, 0.1, 0.1, 0.1, '01/01/2024');