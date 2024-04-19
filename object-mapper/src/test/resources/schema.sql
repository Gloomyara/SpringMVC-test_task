DROP TABLE IF EXISTS order_products;
DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS users;

CREATE TABLE IF NOT EXISTS customers
(
    id           UUID DEFAULT random_uuid(),
    name         VARCHAR(64)  NOT NULL,
    email        VARCHAR(64)  UNIQUE NOT NULL,
    address      VARCHAR(128) NOT NULL,
    CONSTRAINT   pk_user PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS products
(
    id           UUID DEFAULT random_uuid(),
    name         VARCHAR(64)  NOT NULL,
    description  VARCHAR(255) NOT NULL,
    price        NUMERIC      NOT NULL,
    CONSTRAINT   pk_products PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS orders
(
    id           UUID DEFAULT random_uuid(),
    description  VARCHAR(255) NOT NULL,
    paid         BOOLEAN      NOT NULL,
    amount       NUMERIC      NOT NULL,
    customer_id  UUID         NOT NULL,
    CONSTRAINT   pk_orders PRIMARY KEY (id),
    CONSTRAINT   fk_users  FOREIGN KEY (customer_id) REFERENCES customers (id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS order_products
(
    order_id     UUID,
    product_id   UUID,
    quantity     INTEGER DEFAULT 1,
    CONSTRAINT   fk_orders   FOREIGN KEY (order_id)   REFERENCES orders   (id) ON DELETE CASCADE,
    CONSTRAINT   fk_products FOREIGN KEY (product_id) REFERENCES products (id) ON DELETE CASCADE,
    CONSTRAINT   pk_order_products PRIMARY KEY (order_id, product_id)
);
