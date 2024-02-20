CREATE TABLE author
(
    id         CHAR(36)                 NOT NULL PRIMARY KEY,
    version    INTEGER                  NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL,
    first_name VARCHAR                  NOT NULL,
    last_name  VARCHAR                  NOT NULL
);

CREATE TABLE book
(
    id         CHAR(36)                 NOT NULL PRIMARY KEY,
    version    INTEGER                  NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL,
    title      VARCHAR                  NOT NULL
);

CREATE TABLE review
(
    id         CHAR(36)                 NOT NULL PRIMARY KEY,
    version    INTEGER                  NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL,
    review     VARCHAR                  NOT NULL,
    book_id    CHAR(36)                 NOT NULL
);

CREATE TABLE book_author
(
    book_id   CHAR(36) NOT NULL,
    author_id CHAR(36) NOT NULL,
    PRIMARY KEY (book_id, author_id)
);