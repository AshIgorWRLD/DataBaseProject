ALTER TABLE artists ADD CONSTRAINT stage_name CHECK (length(stage_name) > 0);
