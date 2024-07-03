ALTER TABLE models ADD COLUMN weight INT DEFAULT 0;
UPDATE models SET weight = 0 WHERE weight IS NULL;