INSERT INTO artist_pages (solo_artist_id, group_id)
SELECT artists.id, null
FROM artists;

INSERT INTO artist_pages (solo_artist_id, group_id)
SELECT null, groups.id
FROM groups;