ALTER TABLE artists_and_groups_on_event ADD CONSTRAINT income CHECK (income > 100 AND income < 99999999);
