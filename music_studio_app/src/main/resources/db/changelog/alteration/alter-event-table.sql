ALTER TABLE events ADD CONSTRAINT event_name CHECK (length(event_name) > 3);
ALTER TABLE events ADD CONSTRAINT audience_amount CHECK (audience_amount > 10);
ALTER TABLE events ADD CONSTRAINT point CHECK (length(point) > 3);