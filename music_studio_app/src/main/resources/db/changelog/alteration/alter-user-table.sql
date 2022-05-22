ALTER TABLE users ADD CONSTRAINT name CHECK (length(name) < 20 AND length(name) >= 2);
ALTER TABLE users ADD CONSTRAINT login CHECK (length(login) < 30 AND length(login) > 5);
ALTER TABLE users ADD CONSTRAINT password CHECK (length(password) < 30 AND length(password) > 5);

