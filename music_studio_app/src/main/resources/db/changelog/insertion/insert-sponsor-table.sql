INSERT INTO sponsors (event_id, name, business_type, sponsored_money)
VALUES ((SELECT events.id
         FROM events
         WHERE (events.event_name = 'ASTROFEST')), 'Redbull', 'law firm', 100000000),
       ((SELECT events.id
         FROM events
         WHERE (events.event_name = 'ASTROFEST')), 'Audi', 'law firm', 100000000),
       ((SELECT events.id
         FROM events
         WHERE (events.event_name = 'NBA open season')), 'Michael Jordan',
        'individual entrepreneur', 50000000);
