INSERT INTO goals (artist_or_group_id, progress_id, progress_percentage, statement, type, deadline, resources)
VALUES ((SELECT artist_pages.id
         FROM artist_pages
                  INNER JOIN artists ON (artist_pages.solo_artist_id = artists.id)
                  INNER JOIN clients ON (artists.client_id = clients.id)
                  INNER JOIN users ON (clients.user_id = users.id)
         WHERE (users.name = 'Ye')),
        (SELECT progress.id
         FROM progress
                  INNER JOIN artist_pages ON (progress.artist_or_group_id = artist_pages.id)
                  INNER JOIN artists ON (artist_pages.solo_artist_id = artists.id)
                  INNER JOIN clients ON (artists.client_id = clients.id)
                  INNER JOIN users ON (clients.user_id = users.id)
         WHERE (users.name = 'Ye')), 10,
        'Do the grammy nominated album of the year', 'music stream goal', '01/01/2024',
        '3 album presentations'),
       ((SELECT artist_pages.id
         FROM artist_pages
                  INNER JOIN artists ON (artist_pages.solo_artist_id = artists.id)
                  INNER JOIN clients ON (artists.client_id = clients.id)
                  INNER JOIN users ON (clients.user_id = users.id)
         WHERE (users.name = 'Jared')),
        (SELECT progress.id
         FROM progress
                  INNER JOIN artist_pages ON (progress.artist_or_group_id = artist_pages.id)
                  INNER JOIN artists ON (artist_pages.solo_artist_id = artists.id)
                  INNER JOIN clients ON (artists.client_id = clients.id)
                  INNER JOIN users ON (clients.user_id = users.id)
         WHERE (users.name = 'Jared')), 10,
        'Be in top 10 most streamed artists of the year', 'music stream goal', '01/01/2024',
        'some loud features before album'),
       ((SELECT artist_pages.id
         FROM artist_pages
                  INNER JOIN artists ON (artist_pages.solo_artist_id = artists.id)
                  INNER JOIN clients ON (artists.client_id = clients.id)
                  INNER JOIN users ON (clients.user_id = users.id)
         WHERE (users.name = 'Quavious')),
        (SELECT progress.id
         FROM progress
                  INNER JOIN artist_pages ON (progress.artist_or_group_id = artist_pages.id)
                  INNER JOIN artists ON (artist_pages.solo_artist_id = artists.id)
                  INNER JOIN clients ON (artists.client_id = clients.id)
                  INNER JOIN users ON (clients.user_id = users.id)
         WHERE (users.name = 'Quavious')), 10,
        'Become the Rap face of NBA', 'media life goal', '01/01/2024',
        'participating in some NBA shows'),
       ((SELECT artist_pages.id
         FROM artist_pages
                  INNER JOIN artists ON (artist_pages.solo_artist_id = artists.id)
                  INNER JOIN clients ON (artists.client_id = clients.id)
                  INNER JOIN users ON (clients.user_id = users.id)
         WHERE (users.name = 'Kiari')),
        (SELECT progress.id
         FROM progress
                  INNER JOIN artist_pages ON (progress.artist_or_group_id = artist_pages.id)
                  INNER JOIN artists ON (artist_pages.solo_artist_id = artists.id)
                  INNER JOIN clients ON (artists.client_id = clients.id)
                  INNER JOIN users ON (clients.user_id = users.id)
         WHERE (users.name = 'Kiari')), 10,
        'Do single that will reach top 3 billboard', 'music stream goal', '01/01/2024',
        'cooperation with Metroboomin'),
       ((SELECT artist_pages.id
         FROM artist_pages
                  INNER JOIN artists ON (artist_pages.solo_artist_id = artists.id)
                  INNER JOIN clients ON (artists.client_id = clients.id)
                  INNER JOIN users ON (clients.user_id = users.id)
         WHERE (users.name = 'Kirshnick')),
        (SELECT progress.id
         FROM progress
                  INNER JOIN artist_pages ON (progress.artist_or_group_id = artist_pages.id)
                  INNER JOIN artists ON (artist_pages.solo_artist_id = artists.id)
                  INNER JOIN clients ON (artists.client_id = clients.id)
                  INNER JOIN users ON (clients.user_id = users.id)
         WHERE (users.name = 'Kirshnick')), 10,
        'Reach 100000 monthly listeners on spotify ', 'music stream goal', '01/01/2024',
        'new album and loud features'),
       ((SELECT artist_pages.id
         FROM artist_pages
                  INNER JOIN groups ON (artist_pages.group_id = groups.id)
         WHERE (groups.name = 'Migos')),
        (SELECT progress.id
         FROM progress
                  INNER JOIN artist_pages ON (progress.artist_or_group_id = artist_pages.id)
                  INNER JOIN groups ON (artist_pages.group_id = groups.id)
         WHERE (groups.name = 'Migos')), 10,
        'Reach top 1 of billboard albums', 'music stream goal', '01/01/2024',
        'massive hits album');