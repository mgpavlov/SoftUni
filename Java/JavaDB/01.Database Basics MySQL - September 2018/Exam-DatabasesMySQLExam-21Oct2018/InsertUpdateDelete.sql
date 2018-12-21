-- 02.Insert 
-- Buhtig
# You will have to INSERT records of data into the issues table, based on the files table. For files with id between 46 and 50 (inclusive), insert data in the issues table with the following values:
# •	title – set it to “Critical Problem With {fileName}!”. Where the fileName is the name of the file.
# •	issue_status – set it to “open”.
# •	repository_id – MULTIPLY the id of the file by 2 and DIVIDE it by 3.
# o	ROUND the resulting value UP.
# •	assignee_id – the file’s commit’s contributor’s id.

INSERT INTO issues (title, issue_status, repository_id, assignee_id)
SELECT concat('Critical Problem With ', f.name,'!'), 'open', ceil(f.id*2/3), u.id
FROM files f
JOIN commits c
ON f.commit_id = c.id
JOIN users u
ON u.id = c.contributor_id
WHERE f.id BETWEEN 46 AND 50;

-- -------------------------------------------------------------
-- instagraph

INSERT INTO comments (content, user_id, post_id)
SELECT concat('Omg!', u.username, '!This is so cool!”'),
ceil(p.id*3/2), 
p.id
FROM posts p
JOIN users u
ON p.username_id = u.id
WHERE p.id BETWEEN 1 AND 10;

-- ============================================================

-- 03 UPDATE

# UPDATE all contributors to repositories which have the same id (value) as the repository they contribute to. 
# SET them as a contributor to the repository with the lowest id (by value) which has no contributors. 
# If there aren’t any repositories with no contributors do nothing.

-- заявка за: contributors to repositories which have the same id (value) as the repository they contribute to
# SELECT rc.contributor_id
# FROM repositories_contributors rc
# WHERE rc.contributor_id = rc.repository_id;

-- заявка за намиране на на : repository with the lowest id (by value) which has no contributors. 
# select r.id
# from repositories r
# LEFT join repositories_contributors rc
# ON r.id = rc.repository_id
# WHERE rc.contributor_id IS NULL
# ORDER BY r.id
# limit 1;
 
UPDATE repositories_contributors
	JOIN (
select r.id AS repo
from repositories r
LEFT join repositories_contributors rc
ON r.id = rc.repository_id
WHERE rc.contributor_id IS NULL
ORDER BY r.id
limit 1
) as tb
SET repository_id = tb.repo
WHERE contributor_id = repository_id;

-- --------------------------------------------------------------

-- instagraph
# UPDATE all users which do NOT have a profile picture. 
# Set their profile picture id to the count of followers they have. 
# If they have 0, set it to the user’s id.


UPDATE users us
JOIN(
SELECT if(count(uf.follower_id) = 0, u.id, count(uf.follower_id)) as count
FROM users u
Left join users_followers uf
on u.id = uf.user_id
GROUP BY uf.user_id) tb
SET us.profile_picture_id= tb.count
WHERE us.profile_picture_id is NULL;

-- Another solution
UPDATE users AS u
LEFT JOIN (SELECT count(uf.follower_id) as count_followers, uf. user_id
	FROM users_followers uf
    GROUP BY uf.user_id
) as tb
ON u.id = tb.user_id
SET 
u.profile_picture_id = CASE WHEN tb.count_followers = 0 THEN u.id ELSE tb.count_followers END
WHERE u.profile_picture_id IS NULL;	

-- report_service
use report_service;

UPDATE reports r
SET r.status_id = 2
WHERE r.status_id = 1 AND r.category_id = 4;


 -- =============================================================================
-- 04.Delete buhtig
# Buhtig is all about activity, and activity is expressed in issues. 
#Issues indicate the constant process of development. Naturally, inactive repositories are being treated as abandoned. 
#DELETE all repositories which do NOT have any issues.

-- заявка за: all repositories which do NOT have any issues
# SELECT r.id
# FROM repositories r
# LEFT JOIN issues i
# ON r.id = i.repository_id
# WHERE i.id IS NULL;

DELETE r
FROM repositories r
LEFT JOIN issues i
ON r.id = i.repository_id
WHERE i.id IS NULL;


-- instagraph

DELETE u
FROM users u
LEFT JOIN users_followers uf
ON u.id = uf.user_id
WHERE uf.follower_id is NULL;

DELETE u
FROM users u
LEFT JOIN users_followers uf
ON u.id = uf.follower_id
WHERE uf.user_id is null;

-- report_service
DELETE 
FROM reports
WHERE status_id = 4;
