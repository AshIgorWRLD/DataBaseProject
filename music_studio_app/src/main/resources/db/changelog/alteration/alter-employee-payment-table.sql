ALTER TABLE employee_payments ADD CONSTRAINT amount CHECK (amount > 0);
