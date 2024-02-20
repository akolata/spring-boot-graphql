INSERT INTO author(id, version, created_at, updated_at, first_name, last_name)
VALUES ('63f54263-70ce-4280-9491-829007c14fcc', 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Martin', 'Fowler'),
       ('9fd28787-a605-4b3e-b2fd-be29d26758fb', 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Mark', 'Richards'),
       ('e5b4fb6b-4221-44e7-a9ae-15f616020c32', 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Neal', 'Ford'),
       ('5a681dad-ec98-4211-940a-3e837f3c62ed', 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'John', 'Doe');

INSERT INTO book(id, version, created_at, updated_at, title)
VALUES ('abaf26a0-2acf-43cb-8035-15fb378a43e5', 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,
        'Patterns of Enterprise Application Architecture'),
       ('00262dfb-6515-4134-8580-60c61cc33141', 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,
        'Fundamentals of Software Architecture');

INSERT INTO book_author(book_id, author_id)
VALUES ('abaf26a0-2acf-43cb-8035-15fb378a43e5', '63f54263-70ce-4280-9491-829007c14fcc'),
       ('00262dfb-6515-4134-8580-60c61cc33141', '9fd28787-a605-4b3e-b2fd-be29d26758fb'),
       ('00262dfb-6515-4134-8580-60c61cc33141', 'e5b4fb6b-4221-44e7-a9ae-15f616020c32');

INSERT INTO review(id, version, created_at, updated_at, review, book_id)
VALUES ('a2165d1a-b674-4f15-b707-91393b9104dc', 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'good quality',
        'abaf26a0-2acf-43cb-8035-15fb378a43e5'),
       ('971c74ab-d34b-49cb-a378-a14c8d47771f', 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,
        'Many of them may seem to be old and outdated but the fundamental concepts still apply today with modern technologies',
        'abaf26a0-2acf-43cb-8035-15fb378a43e5'),
       ('962bb359-26a9-452d-b834-bacc8981a391', 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,
        'Great one!', '00262dfb-6515-4134-8580-60c61cc33141');