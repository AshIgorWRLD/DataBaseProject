ALTER TABLE distribution_services ADD CONSTRAINT name CHECK (length(name) > 0);
ALTER TABLE distribution_services ADD CONSTRAINT listen_watch_cost CHECK (listen_watch_cost > 0);