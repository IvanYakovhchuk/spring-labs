CREATE TABLE IF NOT EXISTS cinema_halls (
    id   BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS movie_screenings (
    id             BIGSERIAL PRIMARY KEY,
    screening_date TIMESTAMP    NOT NULL,
    movie_name     VARCHAR(200) NOT NULL,
    cinema_hall    INTEGER      NOT NULL REFERENCES cinema_halls(id)
);

CREATE TABLE IF NOT EXISTS seats (
    id          BIGSERIAL PRIMARY KEY,
    cinema_hall INTEGER  NOT NULL REFERENCES cinema_halls(id),
    row_number  INTEGER  NOT NULL,
    seat_number INTEGER  NOT NULL,
    is_vip      BOOLEAN  NOT NULL DEFAULT false,

    UNIQUE (cinema_hall, row_number, seat_number)
);

CREATE TABLE IF NOT EXISTS tickets (
    id            BIGSERIAL PRIMARY KEY,
    screening_id  INTEGER NOT NULL REFERENCES screenings(id) ON DELETE CASCADE,
    seat_id       INTEGER NOT NULL REFERENCES seats(id)      ON DELETE CASCADE,
    customer_name VARCHAR(200) NOT NULL,
    price         DECIMAL(10,2) NOT NULL,

    UNIQUE (screening_id, seat_id)
);