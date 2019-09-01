DROP TABLE IF EXISTS authorities;
DROP TABLE IF EXISTS articles;
DROP TABLE IF EXISTS comments;

CREATE TABLE authorities (id bigint auto_increment not null, username varchar_ignorecase(50) not null, authority varchar_ignorecase(50) not null, constraint fk_authorities_users foreign key(username) references users(username));
CREATE TABLE articles (id bigint auto_increment not null, title varchar(50) not null, body varchar(1000) not null, author bigint not null, constraint fk_articles_users foreign key(author) references users(id));
CREATE TABLE comments (id bigint auto_increment not null, article bigint not null, body varchar(1000) not null, author bigint not null, constraint fk_comments_users foreign key(author) references users(id), constraint fk_comments_articles foreign key(article) references articles(id));

INSERT INTO users (id, username, password,enabled) VALUES (1, 'root', 'root', true), (2, 'user', 'user', true), (3, 'Raoul Duke', '1234', true);
INSERT INTO authorities (id, username, authority) VALUES (1, 'root', 'ROLE_user'), (2, 'root', 'ROLE_admin'), (3, 'user', 'ROLE_user'), (4, 'Raoul Duke', 'ROLE_user');
INSERT INTO articles (id, title, body, author) VALUES (1, 'Mint 400', 'It was boring...', 3), (2, 'D.A. convention', 'It was boring...', 3), (3, 'Fear and Loathing in Scala and Kotlin interop', 'This is awesome', 3);
INSERT INTO comments (id, article, body, author) VALUES (1, 1, 'It was boring...', 1), (2, 2, 'It was boring...', 2), (3, 3, 'This is awesome', 3);
