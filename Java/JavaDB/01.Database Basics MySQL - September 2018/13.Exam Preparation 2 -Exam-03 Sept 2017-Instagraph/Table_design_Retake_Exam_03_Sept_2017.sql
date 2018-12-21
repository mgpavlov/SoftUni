CREATE DATABASE instagraph_db;
USE instagraph_db;

-- 01.	Table Design ---------------------------------------------------------------

CREATE TABLE pictures (
    id INT(11) AUTO_INCREMENT PRIMARY KEY NOT NULL,
    path VARCHAR(255) NOT NULL,
    size DECIMAL(10,2) NOT NULL
);

CREATE TABLE users (
    id INT(11) AUTO_INCREMENT PRIMARY KEY NOT NULL,
    username VARCHAR(30) UNIQUE NOT NULL,
    password VARCHAR(30) NOT NULL,
    profile_picture_id INT(11),
    
    CONSTRAINT `fk_users_pictures`
    FOREIGN KEY (profile_picture_id)
    REFERENCES pictures(id)
);

CREATE TABLE posts (
    id INT(11) AUTO_INCREMENT PRIMARY KEY NOT NULL,
    caption VARCHAR(255) NOT NULL,
    user_id INT(11) NOT NULL,
    picture_id INT(11) NOT NULL,
    
    CONSTRAINT `fk_posts_users`
    FOREIGN KEY (user_id)
    REFERENCES users(id),
    
    CONSTRAINT `fk_posts_pictures`
    FOREIGN KEY (picture_id)
    REFERENCES pictures(id)
);

CREATE TABLE comments (
    id INT(11) AUTO_INCREMENT PRIMARY KEY NOT NULL,
    content VARCHAR(255) NOT NULL,
    user_id INT(11) NOT NULL,
    post_id INT(11) NOT NULL,
    
    CONSTRAINT `fk_comments_users`
    FOREIGN KEY (user_id)
    REFERENCES users(id),
    
    CONSTRAINT `fk_comments_posts`
    FOREIGN KEY (post_id)
    REFERENCES posts(id)
);

CREATE TABLE users_followers (
    user_id INT(11),
    follower_id INT(11),
    
    CONSTRAINT `fk_users_followers_users`
    FOREIGN KEY (user_id)
    REFERENCES users(id),
    
    CONSTRAINT `fk_users_followers_users_followers`
    FOREIGN KEY (follower_id)
    REFERENCES users(id)
);

