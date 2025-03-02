<h2>invalid-tweets Notes</h2><hr># Write your MySQL query statement below
SELECT tweet_id
FROM Tweets
WHERE CHAR_LENGTH(content) > 15;