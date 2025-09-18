CREATE TABLE products(
                         id BIGSERIAL PRIMARY KEY,
                         sku VARCHAR(64) UNIQUE NOT NULL,
                         name VARCHAR(200) NOT NULL,
                         price NUMERIC(12,2) NOT NULL,
                         stock INT NOT NULL DEFAULT 0
);
CREATE INDEX idx_products_sku ON products(sku);