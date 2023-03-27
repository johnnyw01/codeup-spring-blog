CREATE DATABASE IF NOT EXISTS blog_db;

CREATE USER blog_user@localhost IDENTIFIED BY 'password';
GRANT ALL ON blog_db.* TO blog_user@localhost;
SHOW GRANTS FOR blog_user@localhost;

DROP USER test_user@localhost;

