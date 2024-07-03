ALTER TABLE models ADD COLUMN height INT DEFAULT 0;
UPDATE models SET height = 0 WHERE height IS NULL;