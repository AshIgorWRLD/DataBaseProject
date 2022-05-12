INSERT INTO artist_distribution_cards (artist_or_group_id, distribution_service_id, listen_watch_amount,
                                       monthly_listeners)
VALUES ((SELECT artist_pages.id
         FROM artist_pages
                  INNER JOIN artists ON (artist_pages.solo_artist_id = artists.id)
                  INNER JOIN clients ON (artists.client_id = clients.id)
                  INNER JOIN users ON (clients.user_id = users.id)
         WHERE (users.name = 'Ye')),
        (SELECT distribution_services.id
         FROM distribution_services
         WHERE (distribution_services.name = 'Spotify')), 1000000000, 37000000),
       ((SELECT artist_pages.id
         FROM artist_pages
                  INNER JOIN artists ON (artist_pages.solo_artist_id = artists.id)
                  INNER JOIN clients ON (artists.client_id = clients.id)
                  INNER JOIN users ON (clients.user_id = users.id)
         WHERE (users.name = 'Ye')),
        (SELECT distribution_services.id
         FROM distribution_services
         WHERE (distribution_services.name = 'Yandex music')), 23000000, 1000000),
       ((SELECT artist_pages.id
         FROM artist_pages
                  INNER JOIN artists ON (artist_pages.solo_artist_id = artists.id)
                  INNER JOIN clients ON (artists.client_id = clients.id)
                  INNER JOIN users ON (clients.user_id = users.id)
         WHERE (users.name = 'Ye')),
        (SELECT distribution_services.id
         FROM distribution_services
         WHERE (distribution_services.name = 'Apple music')), 980000000, 34000000),
       ((SELECT artist_pages.id
         FROM artist_pages
                  INNER JOIN artists ON (artist_pages.solo_artist_id = artists.id)
                  INNER JOIN clients ON (artists.client_id = clients.id)
                  INNER JOIN users ON (clients.user_id = users.id)
         WHERE (users.name = 'Ye')),
        (SELECT distribution_services.id
         FROM distribution_services
         WHERE (distribution_services.name = 'Youtube')), 66000000, 8260000),
       ((SELECT artist_pages.id
         FROM artist_pages
                  INNER JOIN artists ON (artist_pages.solo_artist_id = artists.id)
                  INNER JOIN clients ON (artists.client_id = clients.id)
                  INNER JOIN users ON (clients.user_id = users.id)
         WHERE (users.name = 'Ye')),
        (SELECT distribution_services.id
         FROM distribution_services
         WHERE (distribution_services.name = 'Youtube music')), 30000000, 3000000),
       ((SELECT artist_pages.id
         FROM artist_pages
                  INNER JOIN artists ON (artist_pages.solo_artist_id = artists.id)
                  INNER JOIN clients ON (artists.client_id = clients.id)
                  INNER JOIN users ON (clients.user_id = users.id)
         WHERE (users.name = 'Ye')),
        (SELECT distribution_services.id
         FROM distribution_services
         WHERE (distribution_services.name = 'Boom')), 22000000, 1000000),
       ((SELECT artist_pages.id
         FROM artist_pages
                  INNER JOIN artists ON (artist_pages.solo_artist_id = artists.id)
                  INNER JOIN clients ON (artists.client_id = clients.id)
                  INNER JOIN users ON (clients.user_id = users.id)
         WHERE (users.name = 'Ye')),
        (SELECT distribution_services.id
         FROM distribution_services
         WHERE (distribution_services.name = 'Deezer')), 24000000, 1000000),
       ((SELECT artist_pages.id
         FROM artist_pages
                  INNER JOIN artists ON (artist_pages.solo_artist_id = artists.id)
                  INNER JOIN clients ON (artists.client_id = clients.id)
                  INNER JOIN users ON (clients.user_id = users.id)
         WHERE (users.name = 'Jared')),
        (SELECT distribution_services.id
         FROM distribution_services
         WHERE (distribution_services.name = 'Spotify')), 810000000, 32000000),
       ((SELECT artist_pages.id
         FROM artist_pages
                  INNER JOIN artists ON (artist_pages.solo_artist_id = artists.id)
                  INNER JOIN clients ON (artists.client_id = clients.id)
                  INNER JOIN users ON (clients.user_id = users.id)
         WHERE (users.name = 'Jared')),
        (SELECT distribution_services.id
         FROM distribution_services
         WHERE (distribution_services.name = 'Yandex music')), 8000000, 372000),
       ((SELECT artist_pages.id
         FROM artist_pages
                  INNER JOIN artists ON (artist_pages.solo_artist_id = artists.id)
                  INNER JOIN clients ON (artists.client_id = clients.id)
                  INNER JOIN users ON (clients.user_id = users.id)
         WHERE (users.name = 'Jared')),
        (SELECT distribution_services.id
         FROM distribution_services
         WHERE (distribution_services.name = 'Apple music')), 720000000, 28000000),
       ((SELECT artist_pages.id
         FROM artist_pages
                  INNER JOIN artists ON (artist_pages.solo_artist_id = artists.id)
                  INNER JOIN clients ON (artists.client_id = clients.id)
                  INNER JOIN users ON (clients.user_id = users.id)
         WHERE (users.name = 'Jared')),
        (SELECT distribution_services.id
         FROM distribution_services
         WHERE (distribution_services.name = 'Youtube')), 46000000, 5600000),
       ((SELECT artist_pages.id
         FROM artist_pages
                  INNER JOIN artists ON (artist_pages.solo_artist_id = artists.id)
                  INNER JOIN clients ON (artists.client_id = clients.id)
                  INNER JOIN users ON (clients.user_id = users.id)
         WHERE (users.name = 'Jared')),
        (SELECT distribution_services.id
         FROM distribution_services
         WHERE (distribution_services.name = 'Youtube music')), 24000000, 2300000),
       ((SELECT artist_pages.id
         FROM artist_pages
                  INNER JOIN artists ON (artist_pages.solo_artist_id = artists.id)
                  INNER JOIN clients ON (artists.client_id = clients.id)
                  INNER JOIN users ON (clients.user_id = users.id)
         WHERE (users.name = 'Jared')),
        (SELECT distribution_services.id
         FROM distribution_services
         WHERE (distribution_services.name = 'Boom')), 15000000, 850000),
       ((SELECT artist_pages.id
         FROM artist_pages
                  INNER JOIN artists ON (artist_pages.solo_artist_id = artists.id)
                  INNER JOIN clients ON (artists.client_id = clients.id)
                  INNER JOIN users ON (clients.user_id = users.id)
         WHERE (users.name = 'Jared')),
        (SELECT distribution_services.id
         FROM distribution_services
         WHERE (distribution_services.name = 'Deezer')), 14000000, 850000),
       ((SELECT artist_pages.id
         FROM artist_pages
                  INNER JOIN groups ON (artist_pages.group_id = groups.id)
         WHERE (groups.name = 'Migos')),
        (SELECT distribution_services.id
         FROM distribution_services
         WHERE (distribution_services.name = 'Spotify')), 650000000, 22000000),
       ((SELECT artist_pages.id
         FROM artist_pages
                  INNER JOIN groups ON (artist_pages.group_id = groups.id)
         WHERE (groups.name = 'Migos')),
        (SELECT distribution_services.id
         FROM distribution_services
         WHERE (distribution_services.name = 'Yandex music')), 2500000, 137623),
       ((SELECT artist_pages.id
         FROM artist_pages
                  INNER JOIN groups ON (artist_pages.group_id = groups.id)
         WHERE (groups.name = 'Migos')),
        (SELECT distribution_services.id
         FROM distribution_services
         WHERE (distribution_services.name = 'Apple music')), 450000000, 18000000),
       ((SELECT artist_pages.id
         FROM artist_pages
                  INNER JOIN groups ON (artist_pages.group_id = groups.id)
         WHERE (groups.name = 'Migos')),
        (SELECT distribution_services.id
         FROM distribution_services
         WHERE (distribution_services.name = 'Youtube')), 31000000, 3200000),
       ((SELECT artist_pages.id
         FROM artist_pages
                  INNER JOIN groups ON (artist_pages.group_id = groups.id)
         WHERE (groups.name = 'Migos')),
        (SELECT distribution_services.id
         FROM distribution_services
         WHERE (distribution_services.name = 'Youtube music')), 13000000, 1500000),
       ((SELECT artist_pages.id
         FROM artist_pages
                  INNER JOIN groups ON (artist_pages.group_id = groups.id)
         WHERE (groups.name = 'Migos')),
        (SELECT distribution_services.id
         FROM distribution_services
         WHERE (distribution_services.name = 'Boom')), 800000, 100000),
       ((SELECT artist_pages.id
         FROM artist_pages
                  INNER JOIN groups ON (artist_pages.group_id = groups.id)
         WHERE (groups.name = 'Migos')),
        (SELECT distribution_services.id
         FROM distribution_services
         WHERE (distribution_services.name = 'Deezer')), 780000, 100000),
       ((SELECT artist_pages.id
         FROM artist_pages
                  INNER JOIN artists ON (artist_pages.solo_artist_id = artists.id)
                  INNER JOIN clients ON (artists.client_id = clients.id)
                  INNER JOIN users ON (clients.user_id = users.id)
         WHERE (users.name = 'Quavious')),
        (SELECT distribution_services.id
         FROM distribution_services
         WHERE (distribution_services.name = 'Spotify')), 120000000, 9000000),
       ((SELECT artist_pages.id
         FROM artist_pages
                  INNER JOIN artists ON (artist_pages.solo_artist_id = artists.id)
                  INNER JOIN clients ON (artists.client_id = clients.id)
                  INNER JOIN users ON (clients.user_id = users.id)
         WHERE (users.name = 'Quavious')),
        (SELECT distribution_services.id
         FROM distribution_services
         WHERE (distribution_services.name = 'Yandex music')), 3400000, 119000),
       ((SELECT artist_pages.id
         FROM artist_pages
                  INNER JOIN artists ON (artist_pages.solo_artist_id = artists.id)
                  INNER JOIN clients ON (artists.client_id = clients.id)
                  INNER JOIN users ON (clients.user_id = users.id)
         WHERE (users.name = 'Quavious')),
        (SELECT distribution_services.id
         FROM distribution_services
         WHERE (distribution_services.name = 'Apple music')), 100000000, 7800000),
       ((SELECT artist_pages.id
         FROM artist_pages
                  INNER JOIN artists ON (artist_pages.solo_artist_id = artists.id)
                  INNER JOIN clients ON (artists.client_id = clients.id)
                  INNER JOIN users ON (clients.user_id = users.id)
         WHERE (users.name = 'Quavious')),
        (SELECT distribution_services.id
         FROM distribution_services
         WHERE (distribution_services.name = 'Youtube')), 24000000, 2100000),
       ((SELECT artist_pages.id
         FROM artist_pages
                  INNER JOIN artists ON (artist_pages.solo_artist_id = artists.id)
                  INNER JOIN clients ON (artists.client_id = clients.id)
                  INNER JOIN users ON (clients.user_id = users.id)
         WHERE (users.name = 'Quavious')),
        (SELECT distribution_services.id
         FROM distribution_services
         WHERE (distribution_services.name = 'Youtube music')), 7000000, 900000),
       ((SELECT artist_pages.id
         FROM artist_pages
                  INNER JOIN artists ON (artist_pages.solo_artist_id = artists.id)
                  INNER JOIN clients ON (artists.client_id = clients.id)
                  INNER JOIN users ON (clients.user_id = users.id)
         WHERE (users.name = 'Quavious')),
        (SELECT distribution_services.id
         FROM distribution_services
         WHERE (distribution_services.name = 'Boom')), 200000, 70000),
       ((SELECT artist_pages.id
         FROM artist_pages
                  INNER JOIN artists ON (artist_pages.solo_artist_id = artists.id)
                  INNER JOIN clients ON (artists.client_id = clients.id)
                  INNER JOIN users ON (clients.user_id = users.id)
         WHERE (users.name = 'Quavious')),
        (SELECT distribution_services.id
         FROM distribution_services
         WHERE (distribution_services.name = 'Deezer')), 200000, 70000),
       ((SELECT artist_pages.id
         FROM artist_pages
                  INNER JOIN artists ON (artist_pages.solo_artist_id = artists.id)
                  INNER JOIN clients ON (artists.client_id = clients.id)
                  INNER JOIN users ON (clients.user_id = users.id)
         WHERE (users.name = 'Kiari')),
        (SELECT distribution_services.id
         FROM distribution_services
         WHERE (distribution_services.name = 'Spotify')), 170000000, 14000000),
       ((SELECT artist_pages.id
         FROM artist_pages
                  INNER JOIN artists ON (artist_pages.solo_artist_id = artists.id)
                  INNER JOIN clients ON (artists.client_id = clients.id)
                  INNER JOIN users ON (clients.user_id = users.id)
         WHERE (users.name = 'Kiari')),
        (SELECT distribution_services.id
         FROM distribution_services
         WHERE (distribution_services.name = 'Yandex music')), 6900000, 277500),
       ((SELECT artist_pages.id
         FROM artist_pages
                  INNER JOIN artists ON (artist_pages.solo_artist_id = artists.id)
                  INNER JOIN clients ON (artists.client_id = clients.id)
                  INNER JOIN users ON (clients.user_id = users.id)
         WHERE (users.name = 'Kiari')),
        (SELECT distribution_services.id
         FROM distribution_services
         WHERE (distribution_services.name = 'Apple music')), 200000000, 10000000),
       ((SELECT artist_pages.id
         FROM artist_pages
                  INNER JOIN artists ON (artist_pages.solo_artist_id = artists.id)
                  INNER JOIN clients ON (artists.client_id = clients.id)
                  INNER JOIN users ON (clients.user_id = users.id)
         WHERE (users.name = 'Kiari')),
        (SELECT distribution_services.id
         FROM distribution_services
         WHERE (distribution_services.name = 'Youtube')), 44000000, 4100000),
       ((SELECT artist_pages.id
         FROM artist_pages
                  INNER JOIN artists ON (artist_pages.solo_artist_id = artists.id)
                  INNER JOIN clients ON (artists.client_id = clients.id)
                  INNER JOIN users ON (clients.user_id = users.id)
         WHERE (users.name = 'Kiari')),
        (SELECT distribution_services.id
         FROM distribution_services
         WHERE (distribution_services.name = 'Youtube music')), 14000000, 1300000),
       ((SELECT artist_pages.id
         FROM artist_pages
                  INNER JOIN artists ON (artist_pages.solo_artist_id = artists.id)
                  INNER JOIN clients ON (artists.client_id = clients.id)
                  INNER JOIN users ON (clients.user_id = users.id)
         WHERE (users.name = 'Kiari')),
        (SELECT distribution_services.id
         FROM distribution_services
         WHERE (distribution_services.name = 'Boom')), 400000, 90000),
       ((SELECT artist_pages.id
         FROM artist_pages
                  INNER JOIN artists ON (artist_pages.solo_artist_id = artists.id)
                  INNER JOIN clients ON (artists.client_id = clients.id)
                  INNER JOIN users ON (clients.user_id = users.id)
         WHERE (users.name = 'Kiari')),
        (SELECT distribution_services.id
         FROM distribution_services
         WHERE (distribution_services.name = 'Deezer')), 420000, 90000),
       ((SELECT artist_pages.id
         FROM artist_pages
                  INNER JOIN artists ON (artist_pages.solo_artist_id = artists.id)
                  INNER JOIN clients ON (artists.client_id = clients.id)
                  INNER JOIN users ON (clients.user_id = users.id)
         WHERE (users.name = 'Kirshnick')),
        (SELECT distribution_services.id
         FROM distribution_services
         WHERE (distribution_services.name = 'Spotify')), 60000000, 4500000),
       ((SELECT artist_pages.id
         FROM artist_pages
                  INNER JOIN artists ON (artist_pages.solo_artist_id = artists.id)
                  INNER JOIN clients ON (artists.client_id = clients.id)
                  INNER JOIN users ON (clients.user_id = users.id)
         WHERE (users.name = 'Kirshnick')),
        (SELECT distribution_services.id
         FROM distribution_services
         WHERE (distribution_services.name = 'Yandex music')), 1000000, 8800),
       ((SELECT artist_pages.id
         FROM artist_pages
                  INNER JOIN artists ON (artist_pages.solo_artist_id = artists.id)
                  INNER JOIN clients ON (artists.client_id = clients.id)
                  INNER JOIN users ON (clients.user_id = users.id)
         WHERE (users.name = 'Kirshnick')),
        (SELECT distribution_services.id
         FROM distribution_services
         WHERE (distribution_services.name = 'Apple music')), 10000000, 780000),
       ((SELECT artist_pages.id
         FROM artist_pages
                  INNER JOIN artists ON (artist_pages.solo_artist_id = artists.id)
                  INNER JOIN clients ON (artists.client_id = clients.id)
                  INNER JOIN users ON (clients.user_id = users.id)
         WHERE (users.name = 'Kirshnick')),
        (SELECT distribution_services.id
         FROM distribution_services
         WHERE (distribution_services.name = 'Youtube')), 2400000, 210000),
       ((SELECT artist_pages.id
         FROM artist_pages
                  INNER JOIN artists ON (artist_pages.solo_artist_id = artists.id)
                  INNER JOIN clients ON (artists.client_id = clients.id)
                  INNER JOIN users ON (clients.user_id = users.id)
         WHERE (users.name = 'Kirshnick')),
        (SELECT distribution_services.id
         FROM distribution_services
         WHERE (distribution_services.name = 'Youtube music')), 700000, 90000),
       ((SELECT artist_pages.id
         FROM artist_pages
                  INNER JOIN artists ON (artist_pages.solo_artist_id = artists.id)
                  INNER JOIN clients ON (artists.client_id = clients.id)
                  INNER JOIN users ON (clients.user_id = users.id)
         WHERE (users.name = 'Kirshnick')),
        (SELECT distribution_services.id
         FROM distribution_services
         WHERE (distribution_services.name = 'Boom')), 20000, 7000),
       ((SELECT artist_pages.id
         FROM artist_pages
                  INNER JOIN artists ON (artist_pages.solo_artist_id = artists.id)
                  INNER JOIN clients ON (artists.client_id = clients.id)
                  INNER JOIN users ON (clients.user_id = users.id)
         WHERE (users.name = 'Kirshnick')),
        (SELECT distribution_services.id
         FROM distribution_services
         WHERE (distribution_services.name = 'Deezer')), 20000, 7000);