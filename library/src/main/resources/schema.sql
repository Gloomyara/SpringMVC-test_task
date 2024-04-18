DROP TABLE IF EXISTS books;
DROP TABLE IF EXISTS users;

CREATE TABLE IF NOT EXISTS users
(
    id          UUID DEFAULT random_uuid(),
    name        VARCHAR(64) NOT NULL,
    email       VARCHAR(64) UNIQUE NOT NULL,
    CONSTRAINT  pk_user PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS books
(
    id           UUID DEFAULT random_uuid(),
    title        VARCHAR(64)  NOT NULL,
    description  VARCHAR(255) NOT NULL,
    author_id    UUID         NOT NULL,
    publish_date timestamp without time zone,
    CONSTRAINT   pk_books PRIMARY KEY (id),
    CONSTRAINT   fk_users FOREIGN KEY (author_id) REFERENCES users (id) ON DELETE CASCADE
);
