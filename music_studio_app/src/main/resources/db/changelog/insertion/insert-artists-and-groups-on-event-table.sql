INSERT INTO artists_and_groups_on_event (artist_or_group_id, event_id, performance_time, income)
VALUES ((SELECT artist_pages.id
         FROM artist_pages
                  INNER JOIN artists ON (artist_pages.solo_artist_id = artists.id)
                  INNER JOIN clients ON (artists.client_id = clients.id)
                  INNER JOIN users ON (clients.user_id = users.id)
         WHERE (users.name = 'Ye')),
        (SELECT events.id
         FROM events
         WHERE (events.event_name = 'ASTROFEST')), '22:00:00', 2000000),
       ((SELECT artist_pages.id
         FROM artist_pages
                  INNER JOIN artists ON (artist_pages.solo_artist_id = artists.id)
                  INNER JOIN clients ON (artists.client_id = clients.id)
                  INNER JOIN users ON (clients.user_id = users.id)
         WHERE (users.name = 'Jared')),
        (SELECT events.id
         FROM events
         WHERE (events.event_name = 'ASTROFEST')), '21:30:00', 1500000),
       ((SELECT artist_pages.id
         FROM artist_pages
                  INNER JOIN groups ON (artist_pages.group_id = groups.id)
         WHERE (groups.name = 'Migos')),
        (SELECT events.id
         FROM events
         WHERE (events.event_name = 'ASTROFEST')), '21:00:00', 1200000),
       ((SELECT artist_pages.id
         FROM artist_pages
                  INNER JOIN artists ON (artist_pages.solo_artist_id = artists.id)
                  INNER JOIN clients ON (artists.client_id = clients.id)
                  INNER JOIN users ON (clients.user_id = users.id)
         WHERE (users.name = 'Kiari')),
        (SELECT events.id
         FROM events
         WHERE (events.event_name = 'ASTROFEST')), '21:20:00', 800000),
       ((SELECT artist_pages.id
         FROM artist_pages
                  INNER JOIN artists ON (artist_pages.solo_artist_id = artists.id)
                  INNER JOIN clients ON (artists.client_id = clients.id)
                  INNER JOIN users ON (clients.user_id = users.id)
         WHERE (users.name = 'Quavious')),
        (SELECT events.id
         FROM events
         WHERE (events.event_name = 'NBA open season')), '20:30:00', 600000),
       ((SELECT artist_pages.id
         FROM artist_pages
                  INNER JOIN artists ON (artist_pages.solo_artist_id = artists.id)
                  INNER JOIN clients ON (artists.client_id = clients.id)
                  INNER JOIN users ON (clients.user_id = users.id)
         WHERE (users.name = 'Kirshnick')),
        (SELECT events.id
         FROM events
         WHERE (events.event_name = 'NBA open season')), '20:15:00', 300000);