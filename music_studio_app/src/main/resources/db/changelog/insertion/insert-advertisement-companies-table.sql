INSERT INTO advertisement_companies (artist_or_group_id, advertisement_company_name, start_date, end_date,
                                     auditory_type, advertisement_type, expenses)
VALUES ((SELECT artist_pages.id
         FROM artist_pages
                  INNER JOIN artists ON (artist_pages.solo_artist_id = artists.id)
                  INNER JOIN clients ON (artists.client_id = clients.id)
                  INNER JOIN users ON (clients.user_id = users.id)
         WHERE (users.name = 'Ye')), 'MacDonalds Super Bowl', '17/02/2022', '18/02/2023',
        '12+', 'video, billboard', 40000000),
       ((SELECT artist_pages.id
         FROM artist_pages
                  INNER JOIN artists ON (artist_pages.solo_artist_id = artists.id)
                  INNER JOIN clients ON (artists.client_id = clients.id)
                  INNER JOIN users ON (clients.user_id = users.id)
         WHERE (users.name = 'Jared')), 'Fortnite summer event', '15/07/2021', '14/07/2022',
        '12+', 'gameplay, video', 30000000),
       ((SELECT artist_pages.id
         FROM artist_pages
                  INNER JOIN groups ON (artist_pages.group_id = groups.id)
         WHERE (groups.name = 'Migos')), 'Kfc season menu', '14/09/2021', '14/07/2022',
        '12+', 'video, billboard', 35000000),
       ((SELECT artist_pages.id
         FROM artist_pages
                  INNER JOIN artists ON (artist_pages.solo_artist_id = artists.id)
                  INNER JOIN clients ON (artists.client_id = clients.id)
                  INNER JOIN users ON (clients.user_id = users.id)
         WHERE (users.name = 'Kiari')), 'Versache collection', '20/08/2021', '20/09/2023',
        '16+', 'magazines, billboard', 25000000),
       ((SELECT artist_pages.id
         FROM artist_pages
                  INNER JOIN artists ON (artist_pages.solo_artist_id = artists.id)
                  INNER JOIN clients ON (artists.client_id = clients.id)
                  INNER JOIN users ON (clients.user_id = users.id)
         WHERE (users.name = 'Quavious')), 'NBA season', '20/07/2021', '19/07/2022',
        '16+', 'video, billboard', 30000000);