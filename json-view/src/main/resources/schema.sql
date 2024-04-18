DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS users;

CREATE TABLE IF NOT EXISTS users
(
    id          UUID DEFAULT random_uuid(),
    name        VARCHAR(64) NOT NULL,
    email       VARCHAR(64) UNIQUE NOT NULL,
    CONSTRAINT  pk_user PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS orders
(
    id           UUID DEFAULT random_uuid(),
    product_name VARCHAR(64)  NOT NULL,
    description  VARCHAR(255) NOT NULL,
    paid         BOOLEAN      NOT NULL,
    customer_id  UUID         NOT NULL,
    CONSTRAINT   pk_orders PRIMARY KEY (id),
    CONSTRAINT   fk_users FOREIGN KEY (customer_id) REFERENCES users (id) ON DELETE CASCADE
);
