ALTER TABLE investors ADD CONSTRAINT invested_money CHECK (invested_money > 100 AND invested_money < 99999999);
ALTER TABLE investors ADD CONSTRAINT business_part CHECK (business_part > 0 AND business_part < 1);

