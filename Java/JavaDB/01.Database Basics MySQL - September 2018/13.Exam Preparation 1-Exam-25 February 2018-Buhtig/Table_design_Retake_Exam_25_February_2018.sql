CREATE DATABASE buhtig;
USE buhtig;

-- 01.	Table Design ---------------------------------------------------------------

CREATE TABLE users (
    id INT(11) AUTO_INCREMENT PRIMARY KEY NOT NULL,
    username VARCHAR(30) UNIQUE NOT NULL,
    password VARCHAR(30) NOT NULL,
    email VARCHAR(50) NOT NULL
);

CREATE TABLE repositories  (
    id INT(11) AUTO_INCREMENT PRIMARY KEY NOT NULL,
    name VARCHAR(50) UNIQUE NOT NULL
);

CREATE TABLE repositories_contributors (
    repository_id INT(11) NOT NULL,
    contributor_id INT(11) NOT NULL,
    CONSTRAINT `fk_repositories_contributors_repository`
    FOREIGN KEY (repository_id)
    REFERENCES repositories(id),
    
    CONSTRAINT `fk_repositories_contributors_users`
    FOREIGN KEY (contributor_id)
    REFERENCES users(id)
);

CREATE TABLE issues (
    id INT(11) AUTO_INCREMENT PRIMARY KEY NOT NULL,
    title VARCHAR(255) NOT NULL,
    issue_status VARCHAR(6) NOT NULL,
    repository_id INT(11) NOT NULL,
    assignee_id INT(11) NOT NULL,
    
    CONSTRAINT `fk_issues_repository`
    FOREIGN KEY (repository_id)
    REFERENCES repositories(id),
    
    CONSTRAINT `fk_issues_users`
    FOREIGN KEY (assignee_id)
    REFERENCES users(id)
);

CREATE TABLE commits (
    id INT(11) AUTO_INCREMENT PRIMARY KEY NOT NULL,
    message VARCHAR(255) NOT NULL,
    issue_id INT(11) ,
    repository_id INT(11)  NOT NULL,
    contributor_id INT(11)  NOT NULL,
    
    CONSTRAINT `fk_commits_issues`
    FOREIGN KEY (issue_id)
    REFERENCES issues(id),
    
    CONSTRAINT `fk_commits_repository`
    FOREIGN KEY (repository_id)
    REFERENCES repositories(id),
    
    CONSTRAINT `fk_commits_users`
    FOREIGN KEY (contributor_id)
    REFERENCES users(id)
);


CREATE TABLE files (
    id INT(11)  AUTO_INCREMENT PRIMARY KEY NOT NULL,
    name VARCHAR(100) NOT NULL,
    size DECIMAL(10,2)  NOT NULL,
    parent_id INT(11),
    commit_id INT(11)  NOT NULL,
    
    CONSTRAINT `fk_files_files`
    FOREIGN KEY (parent_id)
    REFERENCES files(id),
    
    CONSTRAINT `fk_files_commits`
    FOREIGN KEY (commit_id)
    REFERENCES commits(id)
);


