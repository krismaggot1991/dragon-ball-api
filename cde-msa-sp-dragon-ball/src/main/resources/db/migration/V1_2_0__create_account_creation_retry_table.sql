CREATE TABLE dragonball.user
(
    id         BIGSERIAL PRIMARY KEY,
    user_name  VARCHAR(50) NOT NULL,
    email      VARCHAR(50) NOT NULL,
    password   VARCHAR(50) NOT NULL,
    created_on TIMESTAMP WITH time zone NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_on TIMESTAMP WITH time zone
);

CREATE OR REPLACE FUNCTION update_updated_at_column() RETURNS TRIGGER AS $$
BEGIN
    NEW.updated_on = CURRENT_TIMESTAMP;
RETURN NEW;
END;
$$ LANGUAGE 'plpgsql';

CREATE TRIGGER update_register_updated_at
    BEFORE
        UPDATE ON dragonball.user
    FOR EACH ROW EXECUTE PROCEDURE update_updated_at_column();