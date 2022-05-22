ALTER TABLE social_media_statistic ADD CONSTRAINT subscribers_amount CHECK (subscribers_amount >= 0);
ALTER TABLE social_media_statistic ADD CONSTRAINT live_subscribers CHECK (live_subscribers >= 0);