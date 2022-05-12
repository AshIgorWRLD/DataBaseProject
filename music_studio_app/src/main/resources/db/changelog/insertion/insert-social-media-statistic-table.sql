INSERT INTO social_media_statistic (artist_or_group_id, social_network, subscribers_amount, live_subscribers)
VALUES ((SELECT artist_pages.id
         FROM artist_pages
                  INNER JOIN artists ON (artist_pages.solo_artist_id = artists.id)
                  INNER JOIN clients ON (artists.client_id = clients.id)
                  INNER JOIN users ON (clients.user_id = users.id)
         WHERE (users.name = 'Ye')), 'Instagram', 16000000, 5700000),
       ((SELECT artist_pages.id
         FROM artist_pages
                  INNER JOIN artists ON (artist_pages.solo_artist_id = artists.id)
                  INNER JOIN clients ON (artists.client_id = clients.id)
                  INNER JOIN users ON (clients.user_id = users.id)
         WHERE (users.name = 'Ye')), 'Twitter', 30800000, 865000),
       ((SELECT artist_pages.id
         FROM artist_pages
                  INNER JOIN artists ON (artist_pages.solo_artist_id = artists.id)
                  INNER JOIN clients ON (artists.client_id = clients.id)
                  INNER JOIN users ON (clients.user_id = users.id)
         WHERE (users.name = 'Jared')), 'Instagram', 12200000, 2530000),
       ((SELECT artist_pages.id
         FROM artist_pages
                  INNER JOIN artists ON (artist_pages.solo_artist_id = artists.id)
                  INNER JOIN clients ON (artists.client_id = clients.id)
                  INNER JOIN users ON (clients.user_id = users.id)
         WHERE (users.name = 'Jared')), 'Twitter', 1800000, 889000),
       ((SELECT artist_pages.id
         FROM artist_pages
                  INNER JOIN artists ON (artist_pages.solo_artist_id = artists.id)
                  INNER JOIN clients ON (artists.client_id = clients.id)
                  INNER JOIN users ON (clients.user_id = users.id)
         WHERE (users.name = 'Quavious')), 'Instagram', 21000000, 200000),
       ((SELECT artist_pages.id
         FROM artist_pages
                  INNER JOIN artists ON (artist_pages.solo_artist_id = artists.id)
                  INNER JOIN clients ON (artists.client_id = clients.id)
                  INNER JOIN users ON (clients.user_id = users.id)
         WHERE (users.name = 'Quavious')), 'Twitter', 3600000, 35000),
       ((SELECT artist_pages.id
         FROM artist_pages
                  INNER JOIN artists ON (artist_pages.solo_artist_id = artists.id)
                  INNER JOIN clients ON (artists.client_id = clients.id)
                  INNER JOIN users ON (clients.user_id = users.id)
         WHERE (users.name = 'Kiari')), 'Instagram', 19700000, 1300000),
       ((SELECT artist_pages.id
         FROM artist_pages
                  INNER JOIN artists ON (artist_pages.solo_artist_id = artists.id)
                  INNER JOIN clients ON (artists.client_id = clients.id)
                  INNER JOIN users ON (clients.user_id = users.id)
         WHERE (users.name = 'Kiari')), 'Twitter', 4000000, 344000),
       ((SELECT artist_pages.id
         FROM artist_pages
                  INNER JOIN artists ON (artist_pages.solo_artist_id = artists.id)
                  INNER JOIN clients ON (artists.client_id = clients.id)
                  INNER JOIN users ON (clients.user_id = users.id)
         WHERE (users.name = 'Kirshnick')), 'Instagram', 6800000, 120000),
       ((SELECT artist_pages.id
         FROM artist_pages
                  INNER JOIN artists ON (artist_pages.solo_artist_id = artists.id)
                  INNER JOIN clients ON (artists.client_id = clients.id)
                  INNER JOIN users ON (clients.user_id = users.id)
         WHERE (users.name = 'Kirshnick')), 'Twitter', 1400000, 204000),
       ((SELECT artist_pages.id
         FROM artist_pages
                  INNER JOIN groups ON (artist_pages.group_id = groups.id)
         WHERE (groups.name = 'Migos')), 'Instagram', 16000000, 5700000),
       ((SELECT artist_pages.id
         FROM artist_pages
                  INNER JOIN groups ON (artist_pages.group_id = groups.id)
         WHERE (groups.name = 'Migos')), 'Twitter', 3500000, 185000);