create schema story_map default character set utf8;
CREATE USER story_map@'%' IDENTIFIED BY 'story_mapqwe123456';
CREATE USER story_map@'localhost' IDENTIFIED BY 'story_mapqwe123456';
GRANT ALL PRIVILEGES ON story_map.* TO story_map@'%';
GRANT ALL PRIVILEGES ON story_map.* TO story_map@'localhost';
flush privileges;