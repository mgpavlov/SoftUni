-- 02.	Data Insertion 10/10
INSERT INTO comments (content, user_id, post_id)
SELECT 
	concat('Omg!',
		(select username
		from users
		WHERE id = user_id), '!This is so cool!'),
		ceil(p.id*3)/2 as user_id,
		p.id
FROM posts p
WHERE p.id BETWEEN 1 AND 10;

-- 03.	Data Update 10/10
UPDATE users AS u
LEFT JOIN (SELECT count(uf.follower_id) as count_followers, uf. user_id
	FROM users_followers uf
    GROUP BY uf.user_id
) as tb
ON u.id = tb.user_id
SET 
u.profile_picture_id = CASE WHEN tb.count_followers = 0 THEN u.id ELSE tb.count_followers END
WHERE u.profile_picture_id IS NULL;	

-- 04.	Data Deletion 10/10
DELETE u FROM `users` AS u
        LEFT JOIN
    `users_followers` AS uf ON u.id = uf.user_id
WHERE
    uf.user_id IS NULL;

DELETE u FROM `users` AS u
        LEFT JOIN
    `users_followers` AS uf ON u.id = uf.follower_id
WHERE
    uf.follower_id IS NULL;

-- 05.	Users 10/10
SELECT id, username
FROM users
ORDER BY id;

-- 06.	Users 10/10
SELECT u.id, u.username
FROM users u
JOIN
(SELECT user_id, follower_id
FROM users_followers
WHERE user_id = follower_id
ORDER BY user_id) as tb
ON u.id = tb.user_id
ORDER BY u.id;

-- 07.	High Quality Pictures 10/10
SELECT p.id, p.path, p.size
FROM pictures p
WHERE p.size> 50000 AND (p.path LIKE '%jpeg%' OR p.path LIKE '%png%')
ORDER BY p.size DESC;

-- 08.	Comments and Users
SELECT c.id, concat((select username from users WHERE id = c.user_id), ' : ', c.content) AS full_comment
FROM comments c
ORDER BY c.id DESC;

-- 09.	Profile Pictures 10/10
SELECT us.id, us.username, concat((select size FROM pictures where id = us.profile_picture_id), 'KB') as size
FROM 
(SELECT u.id, u.username, u.profile_picture_id, count(u.id) as `count`
FROM users u
GROUP BY u.profile_picture_id
HAVING count > 1) as tb
Left JOIN pictures p 
ON p.id = tb.profile_picture_id
JOIN users us
ON us.profile_picture_id = p.id
ORDER BY us.id;

-- 10.	Spam Posts 10/10
SELECT p.id, p.caption, (CASE WHEN tb.count_comments is NULL THEN 0 ELSE tb.count_comments END) as comments
FROM posts p
LEFT JOIN(
SELECT c.post_id, count(c.post_id) as count_comments
FROM comments c
GROUP BY c.post_id) as tb
ON p.id = tb.post_id
ORDER BY comments DESC, p.id
LIMIT 5;

-- 11.	Most Popular User 10/10
SELECT u.id, u.username, (SELECT count(*) FROM posts WHERE user_id = tb.user_id) as posts, tb.followers as followers
FROM users u
JOIN (select user_id, count(follower_id) as followers from users_followers GROUP BY user_id ORDER BY followers DESC LIMIT 1) as tb
ON u.id = tb.user_id;

# select user_id, count(follower_id) as followers from users_followers GROUP BY user_id ORDER BY followers DESC LIMIT 1;
# SELECT count(*) FROM posts WHERE user_id = tb.user_id; 

-- 12.	Commenting Myself 10/10 
SELECT u.id, u.username, (CASE WHEN  tb.my_comments IS NULL THEN 0 ELSE tb.my_comments END) as my_comments
FROM users u
LEFT JOIN
(SELECT p.id, p.user_id, count(p.id) as my_comments
FROM posts p
JOIN comments c
ON p.id = c.post_id
WHERE p.user_id = c.user_id
GROUP BY c.user_id) as tb

ON u.id = tb.user_id
ORDER BY tb.my_comments DESC, u.id;


-- 13.	User Top Posts 10/10

SELECT u.id, u.username, tb.caption
FROM users u
JOIN 
(SELECT p.id, p.user_id, p.caption, count(c.id) as comments
FROM posts p
LEFT JOIN comments c
ON p.id = c.post_id
GROUP BY p.id
ORDER BY comments DESC, p.id
) as tb
ON u.id = tb.user_id
GROUP BY u.id
ORDER BY u.id;

-- 14.	Posts and Commentators 10/10
SELECT 
psts.id, 

psts.caption, 

COUNT(p.post_id) as users

FROM posts psts
LEFT JOIN
(
SELECT c.post_id, c.user_id, count(c.post_id)
FROM comments c
GROUP BY c.post_id, c.user_id) as p
ON psts.id = p.post_id
GROUP BY psts.id
ORDER BY users DESC, psts.id ASC;



-- 15.	Post 10/10

DROP PROCEDURE IF EXISTS udp_post;

DELIMITER $$
CREATE PROCEDURE udp_post
    (username VARCHAR(30), password VARCHAR(30), caption VARCHAR(255), path VARCHAR(255))
BEGIN
    START TRANSACTION;
    
    IF ((SELECT u.password FROM `users` AS u WHERE u.username = username) <> password) THEN
        SIGNAL SQLSTATE '45000' 
            SET MESSAGE_TEXT = 'Password is incorrect!';
        ROLLBACK;
    ELSEIF ((SELECT COUNT(p.id) FROM `pictures` AS p WHERE p.path = path) = 0) THEN
        SIGNAL SQLSTATE '45000' 
            SET MESSAGE_TEXT = 'The picture does not exist!';
        ROLLBACK;
    ELSE
        INSERT INTO `posts` 
            (`caption`, `user_id`, `picture_id`)
        VALUES
            (caption,
            (SELECT u.id FROM `users` AS u WHERE u.usernam = username),
            (SELECT pic.id FROM `pictures` AS pic WHERE pic.path = path));
        COMMIT;
    END IF;
END $$
DELIMITER ;

-- 16.	Filter 10/10

DROP PROCEDURE IF EXISTS udp_findbyextension;

DELIMITER $$
CREATE PROCEDURE udp_ffilter(hashtag VARCHAR(100))
BEGIN
    SELECT 
        f.id, 
        f.caption, 
        (SELECT username FROM users u WHERE u.id = f.user_id) AS 'user'
    FROM 
        `posts` AS f 
    WHERE 
        f.caption LIKE (CONCAT('%','#', hashtag, '%'))
    ORDER BY f.id;
END $$
DELIMITER ;

CALL udp_findbyextension('html');


-- FINISH -----------------------------------













