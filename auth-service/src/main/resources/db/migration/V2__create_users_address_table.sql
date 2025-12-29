CREATE TABLE IF NOT EXISTS user_address (
    id BINARY(16) NOT NULL PRIMARY KEY,
    fk_user_id BINARY(16) NOT NULL,
    street_number VARCHAR(10) NOT NULL,
    street_name VARCHAR(100) NOT NULL,
    unit_number VARCHAR(20),
    city VARCHAR(50) NOT NULL,
    state CHAR(2) NOT NULL,
    zip_code VARCHAR(10) NOT NULL,
    county VARCHAR(50),
    country VARCHAR(50) NOT NULL DEFAULT 'USA',
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT fk_user FOREIGN KEY (fk_user_id) REFERENCES users(id),
    INDEX idx_user_address_user (fk_user_id),
    INDEX idx_user_address_zip  (zip_code),
    INDEX idx_user_address_city_state (city, state)
);
