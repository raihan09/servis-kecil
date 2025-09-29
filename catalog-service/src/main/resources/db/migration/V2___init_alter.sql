create table products
(
    price numeric(12, 2) not null,
    stock integer        not null,
    id    uuid           not null
        primary key,
    sku   varchar(64)    not null
        unique,
    name  varchar(200)   not null
);
CREATE INDEX idx_products_sku ON products(sku);