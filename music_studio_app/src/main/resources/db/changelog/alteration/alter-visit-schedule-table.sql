ALTER TABLE visit_schedule ADD CONSTRAINT length_of_visit CHECK (length_of_visit > 0 AND length_of_visit < 481);