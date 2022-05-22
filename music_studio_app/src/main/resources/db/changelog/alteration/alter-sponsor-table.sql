ALTER TABLE sponsors ADD CONSTRAINT name CHECK (length(name) > 3);
ALTER TABLE sponsors ADD CONSTRAINT sponsored_money CHECK (sponsored_money > 5000);