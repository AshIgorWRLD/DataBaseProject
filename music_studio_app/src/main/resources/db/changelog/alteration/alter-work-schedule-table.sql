ALTER TABLE work_schedule ADD CONSTRAINT week_day CHECK (week_day > 0 AND week_day < 8);
ALTER TABLE work_schedule ADD CONSTRAINT work_length CHECK (work_length > 0 AND work_length < 481);