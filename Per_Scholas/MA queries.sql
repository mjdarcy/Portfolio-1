#Select the most popular techniques:
/*
SELECT *, MAX(fav_count) as max FROM
(
	SELECT Technique.*, COUNT(Favorite.techId) AS fav_count
	FROM Favorite
	JOIN Techniquemessage
	ON Favorite.techId = Technique.id
	GROUP BY Technique.id
) AS tbl
WHERE fav_count > 0
GROUP BY NAME;
*/

#Select user favorites
SELECT technique.* FROM technique
JOIN favorite
ON favorite.techId = Technique.id
WHERE favorite.techusername = "Joe44";