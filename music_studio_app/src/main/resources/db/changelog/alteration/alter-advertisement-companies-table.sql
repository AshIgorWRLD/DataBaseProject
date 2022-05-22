ALTER TABLE advertisement_companies ADD CONSTRAINT advertisement_company_name CHECK (length(advertisement_company_name) > 5);
ALTER TABLE advertisement_companies ADD CONSTRAINT auditory_type CHECK (length(auditory_type) > 2);
ALTER TABLE advertisement_companies ADD CONSTRAINT advertisement_type CHECK (length(advertisement_type) > 7);
ALTER TABLE advertisement_companies ADD CONSTRAINT expenses CHECK (expenses >= 0 AND expenses < 99999999);