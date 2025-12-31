BEGIN TRANSACTION;

INSERT INTO users (username, password_hash, role) VALUES
    ('user1', 'user1', 'ROLE_USER'),
    ('user2', 'user2', 'ROLE_USER'),
    ('user3', 'user3', 'ROLE_USER'),
    ('admin', 'admin', 'ROLE_ADMIN');

INSERT INTO artist (name, uploader_id) VALUES
    ('Nightmargin', 4),
    ('Toby Fox', 4),
    ('Leef 6010', 4)
ON CONFLICT DO NOTHING;

INSERT INTO source (name, uploader_id) VALUES
    ('OneShot Original Soundtrack', 4),
    ('UNDERTALE Soundtrack', 4),
    ('Paper Lily OST', 4)
ON CONFLICT DO NOTHING;

INSERT INTO track (name, source_id, uploader_id) VALUES
    ('My Burden Is Light', (SELECT id FROM source WHERE name = 'OneShot Original Soundtrack'), 4),
    ('Someplace I Know', (SELECT id FROM source WHERE name = 'OneShot Original Soundtrack'), 4),
    ('Puzzle Solved', (SELECT id FROM source WHERE name = 'OneShot Original Soundtrack'), 4),
    ('Phosphor', (SELECT id FROM source WHERE name = 'OneShot Original Soundtrack'), 4),
    ('The Prophecy', (SELECT id FROM source WHERE name = 'OneShot Original Soundtrack'), 4),
    ('Abandoned Factory', (SELECT id FROM source WHERE name = 'OneShot Original Soundtrack'), 4),
    ('Silverpoint', (SELECT id FROM source WHERE name = 'OneShot Original Soundtrack'), 4),
    ('A God''s Machine', (SELECT id FROM source WHERE name = 'OneShot Original Soundtrack'), 4),
    ('Rowbot', (SELECT id FROM source WHERE name = 'OneShot Original Soundtrack'), 4),
    ('Geothermal', (SELECT id FROM source WHERE name = 'OneShot Original Soundtrack'), 4),
    ('Distant', (SELECT id FROM source WHERE name = 'OneShot Original Soundtrack'), 4),
    ('Into The Light', (SELECT id FROM source WHERE name = 'OneShot Original Soundtrack'), 4),
    ('Self Contained Universe (Reprise)', (SELECT id FROM source WHERE name = 'OneShot Original Soundtrack'), 4),
    ('Navigate', (SELECT id FROM source WHERE name = 'OneShot Original Soundtrack'), 4),
    ('To Sleep', (SELECT id FROM source WHERE name = 'OneShot Original Soundtrack'), 4),
    ('To Dream', (SELECT id FROM source WHERE name = 'OneShot Original Soundtrack'), 4),
    ('Flooded Ruins', (SELECT id FROM source WHERE name = 'OneShot Original Soundtrack'), 4),
    ('Alula', (SELECT id FROM source WHERE name = 'OneShot Original Soundtrack'), 4),
    ('Children of the Ruins', (SELECT id FROM source WHERE name = 'OneShot Original Soundtrack'), 4),
    ('Ram', (SELECT id FROM source WHERE name = 'OneShot Original Soundtrack'), 4),
    ('Pretty Bad', (SELECT id FROM source WHERE name = 'OneShot Original Soundtrack'), 4),
    ('On Little Cat Feet', (SELECT id FROM source WHERE name = 'OneShot Original Soundtrack'), 4),
    ('Indoors', (SELECT id FROM source WHERE name = 'OneShot Original Soundtrack'), 4),
    ('Dark Stairwell', (SELECT id FROM source WHERE name = 'OneShot Original Soundtrack'), 4),
    ('Sonder', (SELECT id FROM source WHERE name = 'OneShot Original Soundtrack'), 4),
    ('Pretty nice day, huh...', (SELECT id FROM source WHERE name = 'OneShot Original Soundtrack'), 4),
    ('On Little Cat Feet (ground)', (SELECT id FROM source WHERE name = 'OneShot Original Soundtrack'), 4),
    ('Library Stroll', (SELECT id FROM source WHERE name = 'OneShot Original Soundtrack'), 4),
    ('Simple Secrets', (SELECT id FROM source WHERE name = 'OneShot Original Soundtrack'), 4),
    ('Factory', (SELECT id FROM source WHERE name = 'OneShot Original Soundtrack'), 4),
    ('Library Nap', (SELECT id FROM source WHERE name = 'OneShot Original Soundtrack'), 4),
    ('The Tower', (SELECT id FROM source WHERE name = 'OneShot Original Soundtrack'), 4),
    ('Distant water', (SELECT id FROM source WHERE name = 'OneShot Original Soundtrack'), 4),
    ('Niko and the World Machine', (SELECT id FROM source WHERE name = 'OneShot Original Soundtrack'), 4),
    ('I''m Here', (SELECT id FROM source WHERE name = 'OneShot Original Soundtrack'), 4),
    ('Pretty', (SELECT id FROM source WHERE name = 'OneShot Original Soundtrack'), 4),
    ('Sun', (SELECT id FROM source WHERE name = 'OneShot Original Soundtrack'), 4),
    ('Self Contained Universe', (SELECT id FROM source WHERE name = 'OneShot Original Soundtrack'), 4),
    ('Thanks For Everything', (SELECT id FROM source WHERE name = 'OneShot Original Soundtrack'), 4),
    ('OneShot Trailer', (SELECT id FROM source WHERE name = 'OneShot Original Soundtrack'), 4),
    ('Countdown', (SELECT id FROM source WHERE name = 'OneShot Original Soundtrack'), 4),
    ('IT''S TIME TO FIGHT CRIME', (SELECT id FROM source WHERE name = 'OneShot Original Soundtrack'), 4),

    ('Once Upon a Time', (SELECT id FROM source WHERE name = 'UNDERTALE Soundtrack'), 4),
    ('Start Menu', (SELECT id FROM source WHERE name = 'UNDERTALE Soundtrack'), 4),
    ('Your Best Friend', (SELECT id FROM source WHERE name = 'UNDERTALE Soundtrack'), 4),
    ('Fallen Down', (SELECT id FROM source WHERE name = 'UNDERTALE Soundtrack'), 4),
    ('Ruins', (SELECT id FROM source WHERE name = 'UNDERTALE Soundtrack'), 4),
    ('Uwa!! So Temperate♫', (SELECT id FROM source WHERE name = 'UNDERTALE Soundtrack'), 4),
    ('Anticipation', (SELECT id FROM source WHERE name = 'UNDERTALE Soundtrack'), 4),
    ('Unnecessary Tension', (SELECT id FROM source WHERE name = 'UNDERTALE Soundtrack'), 4),
    ('Enemy Approaching', (SELECT id FROM source WHERE name = 'UNDERTALE Soundtrack'), 4),
    ('Ghost Fight', (SELECT id FROM source WHERE name = 'UNDERTALE Soundtrack'), 4),
    ('Determination', (SELECT id FROM source WHERE name = 'UNDERTALE Soundtrack'), 4),
    ('Home', (SELECT id FROM source WHERE name = 'UNDERTALE Soundtrack'), 4),
    ('Home (Music Box)', (SELECT id FROM source WHERE name = 'UNDERTALE Soundtrack'), 4),
    ('Heartache', (SELECT id FROM source WHERE name = 'UNDERTALE Soundtrack'), 4),
    ('sans.', (SELECT id FROM source WHERE name = 'UNDERTALE Soundtrack'), 4),
    ('Nyeh Heh Heh!', (SELECT id FROM source WHERE name = 'UNDERTALE Soundtrack'), 4),
    ('Snowy', (SELECT id FROM source WHERE name = 'UNDERTALE Soundtrack'), 4),
    ('Uwa!! So Holiday♫', (SELECT id FROM source WHERE name = 'UNDERTALE Soundtrack'), 4),
    ('Dogbass', (SELECT id FROM source WHERE name = 'UNDERTALE Soundtrack'), 4),
    ('Mysterious Place', (SELECT id FROM source WHERE name = 'UNDERTALE Soundtrack'), 4),
    ('Dogsong', (SELECT id FROM source WHERE name = 'UNDERTALE Soundtrack'), 4),
    ('Snowdin Town', (SELECT id FROM source WHERE name = 'UNDERTALE Soundtrack'), 4),
    ('Shop', (SELECT id FROM source WHERE name = 'UNDERTALE Soundtrack'), 4),
    ('Bonetrousle', (SELECT id FROM source WHERE name = 'UNDERTALE Soundtrack'), 4),
    ('Dating Start!', (SELECT id FROM source WHERE name = 'UNDERTALE Soundtrack'), 4),
    ('Dating Tense!', (SELECT id FROM source WHERE name = 'UNDERTALE Soundtrack'), 4),
    ('Dating Fight!', (SELECT id FROM source WHERE name = 'UNDERTALE Soundtrack'), 4),
    ('Premonition', (SELECT id FROM source WHERE name = 'UNDERTALE Soundtrack'), 4),
    ('Danger Mystery', (SELECT id FROM source WHERE name = 'UNDERTALE Soundtrack'), 4),
    ('Undyne', (SELECT id FROM source WHERE name = 'UNDERTALE Soundtrack'), 4),
    ('Waterfall', (SELECT id FROM source WHERE name = 'UNDERTALE Soundtrack'), 4),
    ('Run!', (SELECT id FROM source WHERE name = 'UNDERTALE Soundtrack'), 4),
    ('Quiet Water', (SELECT id FROM source WHERE name = 'UNDERTALE Soundtrack'), 4),
    ('Memory', (SELECT id FROM source WHERE name = 'UNDERTALE Soundtrack'), 4),
    ('Bird That Carries You Over A Disproportionately Small Gap', (SELECT id FROM source WHERE name = 'UNDERTALE Soundtrack'), 4),
    ('Dummy!', (SELECT id FROM source WHERE name = 'UNDERTALE Soundtrack'), 4),
    ('Pathetic House', (SELECT id FROM source WHERE name = 'UNDERTALE Soundtrack'), 4),
    ('Spooktune', (SELECT id FROM source WHERE name = 'UNDERTALE Soundtrack'), 4),
    ('Spookwave', (SELECT id FROM source WHERE name = 'UNDERTALE Soundtrack'), 4),
    ('Ghouliday', (SELECT id FROM source WHERE name = 'UNDERTALE Soundtrack'), 4),
    ('Chill', (SELECT id FROM source WHERE name = 'UNDERTALE Soundtrack'), 4),
    ('Thundersnail', (SELECT id FROM source WHERE name = 'UNDERTALE Soundtrack'), 4),
    ('Temmie Village', (SELECT id FROM source WHERE name = 'UNDERTALE Soundtrack'), 4),
    ('Tem Shop', (SELECT id FROM source WHERE name = 'UNDERTALE Soundtrack'), 4),
    ('NGAHHH!!', (SELECT id FROM source WHERE name = 'UNDERTALE Soundtrack'), 4),
    ('Spear of Justice', (SELECT id FROM source WHERE name = 'UNDERTALE Soundtrack'), 4),
    ('Ooo', (SELECT id FROM source WHERE name = 'UNDERTALE Soundtrack'), 4),
    ('Alphys', (SELECT id FROM source WHERE name = 'UNDERTALE Soundtrack'), 4),
    ('It''s Showtime!', (SELECT id FROM source WHERE name = 'UNDERTALE Soundtrack'), 4),
    ('Metal Crusher', (SELECT id FROM source WHERE name = 'UNDERTALE Soundtrack'), 4),
    ('Another Medium', (SELECT id FROM source WHERE name = 'UNDERTALE Soundtrack'), 4),
    ('Uwa!! So HEATS!!♫', (SELECT id FROM source WHERE name = 'UNDERTALE Soundtrack'), 4),
    ('Stronger Monsters', (SELECT id FROM source WHERE name = 'UNDERTALE Soundtrack'), 4),
    ('Hotel', (SELECT id FROM source WHERE name = 'UNDERTALE Soundtrack'), 4),
    ('Can You Really Call This A Hotel, I Didn''t Receive A Mint On My Pillow Or Anything', (SELECT id FROM source WHERE name = 'UNDERTALE Soundtrack'), 4),
    ('Confession', (SELECT id FROM source WHERE name = 'UNDERTALE Soundtrack'), 4),
    ('Live Report', (SELECT id FROM source WHERE name = 'UNDERTALE Soundtrack'), 4),
    ('Death Report', (SELECT id FROM source WHERE name = 'UNDERTALE Soundtrack'), 4),
    ('Spider Dance', (SELECT id FROM source WHERE name = 'UNDERTALE Soundtrack'), 4),
    ('Wrong Enemy !?', (SELECT id FROM source WHERE name = 'UNDERTALE Soundtrack'), 4),
    ('Oh! One True Love', (SELECT id FROM source WHERE name = 'UNDERTALE Soundtrack'), 4),
    ('Oh! Dungeon', (SELECT id FROM source WHERE name = 'UNDERTALE Soundtrack'), 4),
    ('It''s Raining Somewhere Else', (SELECT id FROM source WHERE name = 'UNDERTALE Soundtrack'), 4),
    ('CORE Approach', (SELECT id FROM source WHERE name = 'UNDERTALE Soundtrack'), 4),
    ('CORE', (SELECT id FROM source WHERE name = 'UNDERTALE Soundtrack'), 4),
    ('Last Episode!', (SELECT id FROM source WHERE name = 'UNDERTALE Soundtrack'), 4),
    ('Oh My...', (SELECT id FROM source WHERE name = 'UNDERTALE Soundtrack'), 4),
    ('Death By Glamour', (SELECT id FROM source WHERE name = 'UNDERTALE Soundtrack'), 4),
    ('For The Fans', (SELECT id FROM source WHERE name = 'UNDERTALE Soundtrack'), 4),
    ('Long Elevator', (SELECT id FROM source WHERE name = 'UNDERTALE Soundtrack'), 4),
    ('Undertale', (SELECT id FROM source WHERE name = 'UNDERTALE Soundtrack'), 4),
    ('Song That Might Play When You Fight Sans', (SELECT id FROM source WHERE name = 'UNDERTALE Soundtrack'), 4),
    ('The Choice', (SELECT id FROM source WHERE name = 'UNDERTALE Soundtrack'), 4),
    ('Small Shock', (SELECT id FROM source WHERE name = 'UNDERTALE Soundtrack'), 4),
    ('Barrier', (SELECT id FROM source WHERE name = 'UNDERTALE Soundtrack'), 4),
    ('Bergentrückung', (SELECT id FROM source WHERE name = 'UNDERTALE Soundtrack'), 4),
    ('ASGORE', (SELECT id FROM source WHERE name = 'UNDERTALE Soundtrack'), 4),
    ('You Idiot', (SELECT id FROM source WHERE name = 'UNDERTALE Soundtrack'), 4),
    ('Your Best Nightmare', (SELECT id FROM source WHERE name = 'UNDERTALE Soundtrack'), 4),
    ('Finale', (SELECT id FROM source WHERE name = 'UNDERTALE Soundtrack'), 4),
    ('An Ending', (SELECT id FROM source WHERE name = 'UNDERTALE Soundtrack'), 4),
    ('She''s Playing Piano', (SELECT id FROM source WHERE name = 'UNDERTALE Soundtrack'), 4),
    ('Here We Are', (SELECT id FROM source WHERE name = 'UNDERTALE Soundtrack'), 4),
    ('Amalgam', (SELECT id FROM source WHERE name = 'UNDERTALE Soundtrack'), 4),
    ('Fallen Down (Reprise)', (SELECT id FROM source WHERE name = 'UNDERTALE Soundtrack'), 4),
    ('Don''t Give Up', (SELECT id FROM source WHERE name = 'UNDERTALE Soundtrack'), 4),
    ('Hopes And Dreams', (SELECT id FROM source WHERE name = 'UNDERTALE Soundtrack'), 4),
    ('Burn In Despair!', (SELECT id FROM source WHERE name = 'UNDERTALE Soundtrack'), 4),
    ('SAVE The World', (SELECT id FROM source WHERE name = 'UNDERTALE Soundtrack'), 4),
    ('His Theme', (SELECT id FROM source WHERE name = 'UNDERTALE Soundtrack'), 4),
    ('Final Power', (SELECT id FROM source WHERE name = 'UNDERTALE Soundtrack'), 4),
    ('Reunited', (SELECT id FROM source WHERE name = 'UNDERTALE Soundtrack'), 4),
    ('Menu (Full)', (SELECT id FROM source WHERE name = 'UNDERTALE Soundtrack'), 4),
    ('Respite', (SELECT id FROM source WHERE name = 'UNDERTALE Soundtrack'), 4),
    ('Bring It In, Guys!', (SELECT id FROM source WHERE name = 'UNDERTALE Soundtrack'), 4),
    ('Last Goodbye', (SELECT id FROM source WHERE name = 'UNDERTALE Soundtrack'), 4),
    ('But The Earth Refused To Die', (SELECT id FROM source WHERE name = 'UNDERTALE Soundtrack'), 4),
    ('Battle Against A True Hero', (SELECT id FROM source WHERE name = 'UNDERTALE Soundtrack'), 4),
    ('Power Of "NEO"', (SELECT id FROM source WHERE name = 'UNDERTALE Soundtrack'), 4),
    ('MEGALOVANIA', (SELECT id FROM source WHERE name = 'UNDERTALE Soundtrack'), 4),
    ('Good Night', (SELECT id FROM source WHERE name = 'UNDERTALE Soundtrack'), 4),

    ('A Witch''s Wail (Trailer ver.)', (SELECT id FROM source WHERE name = 'Paper Lily OST'), 4),
    ('Lacie''s Lullaby', (SELECT id FROM source WHERE name = 'Paper Lily OST'), 4),
    ('Every Day Lacie', (SELECT id FROM source WHERE name = 'Paper Lily OST'), 4),
    ('Anxiety', (SELECT id FROM source WHERE name = 'Paper Lily OST'), 4),
    ('Wistful - Sunset', (SELECT id FROM source WHERE name = 'Paper Lily OST'), 4),
    ('Theme of Brother', (SELECT id FROM source WHERE name = 'Paper Lily OST'), 4),
    ('The Way Forward', (SELECT id FROM source WHERE name = 'Paper Lily OST'), 4),
    ('One For Sorrow', (SELECT id FROM source WHERE name = 'Paper Lily OST'), 4),
    ('The Last Thing You See', (SELECT id FROM source WHERE name = 'Paper Lily OST'), 4),
    ('Rude Awakening', (SELECT id FROM source WHERE name = 'Paper Lily OST'), 4),
    ('Voice of Trees - Midnight', (SELECT id FROM source WHERE name = 'Paper Lily OST'), 4),
    ('Unexpected Danger', (SELECT id FROM source WHERE name = 'Paper Lily OST'), 4),
    ('Theme of Rune', (SELECT id FROM source WHERE name = 'Paper Lily OST'), 4),
    ('Lost Between the Lines', (SELECT id FROM source WHERE name = 'Paper Lily OST'), 4),
    ('Voice of Trees - Daylight', (SELECT id FROM source WHERE name = 'Paper Lily OST'), 4),
    ('Locked Horrors', (SELECT id FROM source WHERE name = 'Paper Lily OST'), 4),
    ('Respite', (SELECT id FROM source WHERE name = 'Paper Lily OST'), 4),
    ('Awkward Exchange', (SELECT id FROM source WHERE name = 'Paper Lily OST'), 4),
    ('Shop Between Worlds - Closed', (SELECT id FROM source WHERE name = 'Paper Lily OST'), 4),
    ('The Facility', (SELECT id FROM source WHERE name = 'Paper Lily OST'), 4),
    ('Theme of Sai', (SELECT id FROM source WHERE name = 'Paper Lily OST'), 4),
    ('A Single White Lily', (SELECT id FROM source WHERE name = 'Paper Lily OST'), 4),
    ('Burning Past', (SELECT id FROM source WHERE name = 'Paper Lily OST'), 4),
    ('Theme of Lacie', (SELECT id FROM source WHERE name = 'Paper Lily OST'), 4),
    ('Excuse Me!', (SELECT id FROM source WHERE name = 'Paper Lily OST'), 4),
    ('Burning Resolve', (SELECT id FROM source WHERE name = 'Paper Lily OST'), 4),
    ('With All Your Heart', (SELECT id FROM source WHERE name = 'Paper Lily OST'), 4),
    ('A Witch''s Wail', (SELECT id FROM source WHERE name = 'Paper Lily OST'), 4),
    ('A Witch''s Lament', (SELECT id FROM source WHERE name = 'Paper Lily OST'), 4),
    ('Against All Odds', (SELECT id FROM source WHERE name = 'Paper Lily OST'), 4)
;

INSERT INTO track_artist (track_id, artist_id)
SELECT t.id, a.id
FROM track t JOIN artist a ON a.name = 'Nightmargin' JOIN source s ON s.id = t.source_id
WHERE s.name = 'OneShot Original Soundtrack'
ON CONFLICT DO NOTHING;

INSERT INTO track_artist (track_id, artist_id)
SELECT t.id, a.id
FROM track t JOIN artist a ON a.name = 'Toby Fox' JOIN source s ON s.id = t.source_id
WHERE s.name = 'UNDERTALE Soundtrack'
ON CONFLICT DO NOTHING;

INSERT INTO track_artist (track_id, artist_id)
SELECT t.id, a.id
FROM track t JOIN artist a ON a.name = 'Leef 6010' JOIN source s ON s.id = t.source_id
WHERE s.name = 'Paper Lily OST'
ON CONFLICT DO NOTHING;


INSERT INTO artist_source (artist_id, source_id)
SELECT a.id, s.id
FROM artist a
         JOIN source s ON s.name = 'OneShot Original Soundtrack'
WHERE a.name = 'Nightmargin'
    ON CONFLICT DO NOTHING;

INSERT INTO artist_source (artist_id, source_id)
SELECT a.id, s.id
FROM artist a
         JOIN source s ON s.name = 'UNDERTALE Soundtrack'
WHERE a.name = 'Toby Fox'
    ON CONFLICT DO NOTHING;

INSERT INTO artist_source (artist_id, source_id)
SELECT a.id, s.id
FROM artist a
         JOIN source s ON s.name = 'Paper Lily OST'
WHERE a.name = 'Leef 6010'
    ON CONFLICT DO NOTHING;

INSERT INTO link (origin_type, origin_id, url, uploader_id)
VALUES (
           'ARTIST',
           (SELECT id FROM artist WHERE name = 'Nightmargin'),
           'https://www.youtube.com/@NightMargins',
           4
       )
ON CONFLICT DO NOTHING;

INSERT INTO link (origin_type, origin_id, url, uploader_id)
VALUES (
           'ARTIST',
           (SELECT id FROM artist WHERE name = 'Toby Fox'),
           'https://www.youtube.com/channel/UC26hbdeqyPRl7VsnK1UhFPw',
           4
       )
ON CONFLICT DO NOTHING;

INSERT INTO link (origin_type, origin_id, url, uploader_id)
VALUES (
           'ARTIST',
           (SELECT id FROM artist WHERE name = 'Leef 6010'),
           'https://www.youtube.com/@Leef6010',
           4
       )
ON CONFLICT DO NOTHING;

INSERT INTO link (origin_type, origin_id, url, uploader_id)
VALUES (
           'SOURCE',
           (SELECT id FROM source WHERE name = 'OneShot Original Soundtrack'),
           'https://www.youtube.com/playlist?list=PLa73G0dLHZJBI21GOx_jg2btFjEAP9aid',
           4
       )
ON CONFLICT DO NOTHING;

INSERT INTO link (origin_type, origin_id, url, uploader_id)
VALUES (
           'SOURCE',
           (SELECT id FROM source WHERE name = 'UNDERTALE Soundtrack'),
           'https://www.youtube.com/playlist?list=PLKXdyINOQYsb38jn5HGiF6w8X3rph4IIx',
           4
       )
ON CONFLICT DO NOTHING;

INSERT INTO link (origin_type, origin_id, url, uploader_id)
VALUES (
           'SOURCE',
           (SELECT id FROM source WHERE name = 'Paper Lily OST'),
           'https://www.youtube.com/playlist?list=PLDOjCqYj3ys3TEe8HCR7_cYH7X7dU28_B',
           4
       )
ON CONFLICT DO NOTHING;

INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=-wtiB7oXKsw', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'My Burden Is Light' AND s.name = 'OneShot Original Soundtrack' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=ssIobq_j8OQ', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'Someplace I Know' AND s.name = 'OneShot Original Soundtrack' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=rh_I8yEEUbg', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'Puzzle Solved' AND s.name = 'OneShot Original Soundtrack' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=FtMKV6gNbSQ', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'Phosphor' AND s.name = 'OneShot Original Soundtrack' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=Poc-uqGHqIg', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'The Prophecy' AND s.name = 'OneShot Original Soundtrack' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=83DVj6MwxyU', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'Abandoned Factory' AND s.name = 'OneShot Original Soundtrack' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=mmxLcw0oH4w', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'Silverpoint' AND s.name = 'OneShot Original Soundtrack' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=5E0BOS4e2MY', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'A God''s Machine' AND s.name = 'OneShot Original Soundtrack' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=-jjutv1qW3o', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'Rowbot' AND s.name = 'OneShot Original Soundtrack' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=CaLzKVDe5P4', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'Geothermal' AND s.name = 'OneShot Original Soundtrack' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=FsOOZ8Ku8AA', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'Distant' AND s.name = 'OneShot Original Soundtrack' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=4DIMMOoMctc', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'Into The Light' AND s.name = 'OneShot Original Soundtrack' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=UttcxQZ9P34', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'Self Contained Universe (Reprise)' AND s.name = 'OneShot Original Soundtrack' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=CG0nZQRuruY', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'Navigate' AND s.name = 'OneShot Original Soundtrack' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=UO38U3WoTFU', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'To Sleep' AND s.name = 'OneShot Original Soundtrack' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=VJlfcORZUgw', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'To Dream' AND s.name = 'OneShot Original Soundtrack' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=_VixQX5QEHU', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'Flooded Ruins' AND s.name = 'OneShot Original Soundtrack' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=rx4aqT5R1jc', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'Alula' AND s.name = 'OneShot Original Soundtrack' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=ai0BRueCP_E', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'Children of the Ruins' AND s.name = 'OneShot Original Soundtrack' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=jgau10Vh1xY', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'Ram' AND s.name = 'OneShot Original Soundtrack' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=iBPrYmfaaaQ', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'Pretty Bad' AND s.name = 'OneShot Original Soundtrack' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=JcmkEDOAIqk', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'On Little Cat Feet' AND s.name = 'OneShot Original Soundtrack' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=j8WSjqIUzqo', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'Indoors' AND s.name = 'OneShot Original Soundtrack' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=sFIW4LLtqEw', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'Dark Stairwell' AND s.name = 'OneShot Original Soundtrack' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=5ule0YsXsVk', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'Sonder' AND s.name = 'OneShot Original Soundtrack' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=7yFow0Pwkg0', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'Pretty nice day, huh...' AND s.name = 'OneShot Original Soundtrack' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=kManD0msf1c', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'On Little Cat Feet (ground)' AND s.name = 'OneShot Original Soundtrack' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=DfPid8-Z9Yc', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'Library Stroll' AND s.name = 'OneShot Original Soundtrack' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=4mmpk6snhAY', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'Simple Secrets' AND s.name = 'OneShot Original Soundtrack' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=QkX8p59kELk', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'Factory' AND s.name = 'OneShot Original Soundtrack' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=HO2cnnJydH0', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'Library Nap' AND s.name = 'OneShot Original Soundtrack' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=ZRgiLE8b6oY', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'The Tower' AND s.name = 'OneShot Original Soundtrack' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=SckoRZhTKoI', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'Distant water' AND s.name = 'OneShot Original Soundtrack' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=lJ2XqMF36jg', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'Niko and the World Machine' AND s.name = 'OneShot Original Soundtrack' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=I7r4TqS3ViE', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'I''m Here' AND s.name = 'OneShot Original Soundtrack' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=ebF19vgM4VU', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'Pretty' AND s.name = 'OneShot Original Soundtrack' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=44wuroOlE0k', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'Sun' AND s.name = 'OneShot Original Soundtrack' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=Up8ZwW4SRGc', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'Self Contained Universe' AND s.name = 'OneShot Original Soundtrack' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=tc0U-Xu-zHw', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'Thanks For Everything' AND s.name = 'OneShot Original Soundtrack' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=7Tqn_HWh1wk', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'OneShot Trailer' AND s.name = 'OneShot Original Soundtrack' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=qDbC-BT_bIE', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'Countdown' AND s.name = 'OneShot Original Soundtrack' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=02p8rTH3UWA', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'IT''S TIME TO FIGHT CRIME' AND s.name = 'OneShot Original Soundtrack' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=0AQMAth2gio', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'Once Upon a Time' AND s.name = 'UNDERTALE Soundtrack' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=5BrJzFtOnwo', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'Start Menu' AND s.name = 'UNDERTALE Soundtrack' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=MacoenOy9Ss', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'Your Best Friend' AND s.name = 'UNDERTALE Soundtrack' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=3t-6qAQH_bQ', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'Fallen Down' AND s.name = 'UNDERTALE Soundtrack' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=oHZDWwW6iXs', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'Ruins' AND s.name = 'UNDERTALE Soundtrack' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=wvT8KrI1nvo', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'Uwa!! So Temperate♫' AND s.name = 'UNDERTALE Soundtrack' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=kft3ZUYNiwE', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'Anticipation' AND s.name = 'UNDERTALE Soundtrack' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=gEgy7xzAYZY', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'Unnecessary Tension' AND s.name = 'UNDERTALE Soundtrack' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=g6cK5c_59YQ', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'Enemy Approaching' AND s.name = 'UNDERTALE Soundtrack' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=C_5enfN8V8w', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'Ghost Fight' AND s.name = 'UNDERTALE Soundtrack' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=sRLQnlglfrI', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'Determination' AND s.name = 'UNDERTALE Soundtrack' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=0UkRqMFMZic', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'Home' AND s.name = 'UNDERTALE Soundtrack' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=vC1GAZ62NPg', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'Home (Music Box)' AND s.name = 'UNDERTALE Soundtrack' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=mNWArmA5wMw', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'Heartache' AND s.name = 'UNDERTALE Soundtrack' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=9cGrP9w5E7M', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'sans.' AND s.name = 'UNDERTALE Soundtrack' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=T6f1jYcjR_8', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'Nyeh Heh Heh!' AND s.name = 'UNDERTALE Soundtrack' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=QZ1Jp-Wg7b0', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'Snowy' AND s.name = 'UNDERTALE Soundtrack' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=ANpMdkf6q7E', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'Uwa!! So Holiday♫' AND s.name = 'UNDERTALE Soundtrack' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=meUZ2HjwJ0o', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'Dogbass' AND s.name = 'UNDERTALE Soundtrack' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=ewxpqe2btu8', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'Mysterious Place' AND s.name = 'UNDERTALE Soundtrack' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=EWP9Nr7IxUU', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'Dogsong' AND s.name = 'UNDERTALE Soundtrack' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=LETChg7kPJo', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'Snowdin Town' AND s.name = 'UNDERTALE Soundtrack' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=O6H_zN2fezE', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'Shop' AND s.name = 'UNDERTALE Soundtrack' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=mLsWDkF_LIc', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'Bonetrousle' AND s.name = 'UNDERTALE Soundtrack' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=puwOdeKHAKY', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'Dating Start!' AND s.name = 'UNDERTALE Soundtrack' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=j0O3RFCzI_A', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'Dating Tense!' AND s.name = 'UNDERTALE Soundtrack' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=e9YJ1D6HbAU', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'Dating Fight!' AND s.name = 'UNDERTALE Soundtrack' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=Yk8EQW0TJ3U', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'Premonition' AND s.name = 'UNDERTALE Soundtrack' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=aeALZoOiDPs', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'Danger Mystery' AND s.name = 'UNDERTALE Soundtrack' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=rBR_IvHe3Xg', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'Undyne' AND s.name = 'UNDERTALE Soundtrack' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=DVUh7caufKU', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'Waterfall' AND s.name = 'UNDERTALE Soundtrack' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=XLxNMt-2qMU', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'Run!' AND s.name = 'UNDERTALE Soundtrack' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=4LfRKEovz2Y', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'Quiet Water' AND s.name = 'UNDERTALE Soundtrack' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=rtb0N-JQ79k', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'Memory' AND s.name = 'UNDERTALE Soundtrack' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=lV28xl-YKw4', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'Bird That Carries You Over A Disproportionately Small Gap' AND s.name = 'UNDERTALE Soundtrack' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=FbtgN-lqRHA', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'Dummy!' AND s.name = 'UNDERTALE Soundtrack' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=H8OIvglCJp8', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'Pathetic House' AND s.name = 'UNDERTALE Soundtrack' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=lV65zxK61cI', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'Spooktune' AND s.name = 'UNDERTALE Soundtrack' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=DRIOFUDEpLY', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'Spookwave' AND s.name = 'UNDERTALE Soundtrack' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=J-rPVhWEq18', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'Ghouliday' AND s.name = 'UNDERTALE Soundtrack' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=dASRdXkHmq4', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'Chill' AND s.name = 'UNDERTALE Soundtrack' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=vdlFq3d_Ioc', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'Thundersnail' AND s.name = 'UNDERTALE Soundtrack' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=W_KIzOudAlY', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'Temmie Village' AND s.name = 'UNDERTALE Soundtrack' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=OKJYbjxdHpg', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'Tem Shop' AND s.name = 'UNDERTALE Soundtrack' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=IpShDXqs74Q', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'NGAHHH!!' AND s.name = 'UNDERTALE Soundtrack' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=XGzYhJcm-Jw', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'Spear of Justice' AND s.name = 'UNDERTALE Soundtrack' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=kfwEtdUyK_4', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'Ooo' AND s.name = 'UNDERTALE Soundtrack' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=gaCaF-MbkzQ', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'Alphys' AND s.name = 'UNDERTALE Soundtrack' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=EviIkV2CmcM', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'It''s Showtime!' AND s.name = 'UNDERTALE Soundtrack' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=Lt7pB78SGbY', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'Metal Crusher' AND s.name = 'UNDERTALE Soundtrack' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=NP4sPRIyHKM', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'Another Medium' AND s.name = 'UNDERTALE Soundtrack' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=JPXAXHlcXFs', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'Uwa!! So HEATS!!♫' AND s.name = 'UNDERTALE Soundtrack' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=lEbHopRL0OA', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'Stronger Monsters' AND s.name = 'UNDERTALE Soundtrack' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=k0SGqISmlm0', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'Hotel' AND s.name = 'UNDERTALE Soundtrack' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=tgqTM8-SDFA', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'Can You Really Call This A Hotel, I Didn''t Receive A Mint On My Pillow Or Anything' AND s.name = 'UNDERTALE Soundtrack' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=t2t9zqeWrMk', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'Confession' AND s.name = 'UNDERTALE Soundtrack' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=q8euQaH92vE', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'Live Report' AND s.name = 'UNDERTALE Soundtrack' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=JBJQ1FxfydU', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'Death Report' AND s.name = 'UNDERTALE Soundtrack' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=5qZqqUgb1Rw', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'Spider Dance' AND s.name = 'UNDERTALE Soundtrack' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=u78i4ZejBV4', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'Wrong Enemy !?' AND s.name = 'UNDERTALE Soundtrack' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=05hl_0yth-g', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'Oh! One True Love' AND s.name = 'UNDERTALE Soundtrack' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=X4Dhvns8BnU', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'Oh! Dungeon' AND s.name = 'UNDERTALE Soundtrack' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=KtC-pl9P3kE', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'It''s Raining Somewhere Else' AND s.name = 'UNDERTALE Soundtrack' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=jN6xoSmfA8E', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'CORE Approach' AND s.name = 'UNDERTALE Soundtrack' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=SEOYyM9n9mw', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'CORE' AND s.name = 'UNDERTALE Soundtrack' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=1ZeK_DkVh8I', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'Last Episode!' AND s.name = 'UNDERTALE Soundtrack' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=TKIjOMKauTg', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'Oh My...' AND s.name = 'UNDERTALE Soundtrack' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=qeDIZCc6Cyo', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'Death By Glamour' AND s.name = 'UNDERTALE Soundtrack' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=HW8nh_oSCgg', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'For The Fans' AND s.name = 'UNDERTALE Soundtrack' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=k2hc180DxXQ', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'Long Elevator' AND s.name = 'UNDERTALE Soundtrack' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=1HIKNbnV8nw', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'Undertale' AND s.name = 'UNDERTALE Soundtrack' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=x6OUm-HSeFQ', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'Song That Might Play When You Fight Sans' AND s.name = 'UNDERTALE Soundtrack' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=RWl8o-znLck', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'The Choice' AND s.name = 'UNDERTALE Soundtrack' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=I5MW-xF1Kec', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'Small Shock' AND s.name = 'UNDERTALE Soundtrack' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=r4NYGRzIItU', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'Barrier' AND s.name = 'UNDERTALE Soundtrack' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=AhnAielizjs', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'Bergentrückung' AND s.name = 'UNDERTALE Soundtrack' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=42kI2lT0x6U', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'ASGORE' AND s.name = 'UNDERTALE Soundtrack' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=MJE21c--duY', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'You Idiot' AND s.name = 'UNDERTALE Soundtrack' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=DxxLzJDARbo', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'Your Best Nightmare' AND s.name = 'UNDERTALE Soundtrack' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=CANW8s9Lt4g', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'Finale' AND s.name = 'UNDERTALE Soundtrack' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=9g6V67JzAHY', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'An Ending' AND s.name = 'UNDERTALE Soundtrack' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=raVKWF_Ifas', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'She''s Playing Piano' AND s.name = 'UNDERTALE Soundtrack' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=52olsp3GUqY', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'Here We Are' AND s.name = 'UNDERTALE Soundtrack' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=p6qO-ONYEkg', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'Amalgam' AND s.name = 'UNDERTALE Soundtrack' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=qG5ZjezxIck', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'Fallen Down (Reprise)' AND s.name = 'UNDERTALE Soundtrack' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=rpYoFaphxqQ', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'Don''t Give Up' AND s.name = 'UNDERTALE Soundtrack' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=wbO3p7_Mf30', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'Hopes And Dreams' AND s.name = 'UNDERTALE Soundtrack' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=HAnJX4uudqY', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'Burn In Despair!' AND s.name = 'UNDERTALE Soundtrack' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=OEs6SGTkXMo', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'SAVE The World' AND s.name = 'UNDERTALE Soundtrack' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=pMXxxFeZAPg', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'His Theme' AND s.name = 'UNDERTALE Soundtrack' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=6cVdl02AW_U', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'Final Power' AND s.name = 'UNDERTALE Soundtrack' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=VUaeXgFlFFY', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'Reunited' AND s.name = 'UNDERTALE Soundtrack' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=_Rj24hkBkfw', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'Menu (Full)' AND s.name = 'UNDERTALE Soundtrack' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=1Tn1hbCXmD8', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'Respite' AND s.name = 'UNDERTALE Soundtrack' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=qOQz2gfWGaw', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'Bring It In, Guys!' AND s.name = 'UNDERTALE Soundtrack' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=oiaO05_ImsA', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'Last Goodbye' AND s.name = 'UNDERTALE Soundtrack' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=b8ABGEr8oHs', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'But The Earth Refused To Die' AND s.name = 'UNDERTALE Soundtrack' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=xV4pZgTe94E', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'Battle Against A True Hero' AND s.name = 'UNDERTALE Soundtrack' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=ZXG66exDoho', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'Power Of "NEO"' AND s.name = 'UNDERTALE Soundtrack' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=KK3KXAECte4', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'MEGALOVANIA' AND s.name = 'UNDERTALE Soundtrack' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=9ZhG4XIqz9w', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'Good Night' AND s.name = 'UNDERTALE Soundtrack' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=IgeXLLAwNcs', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'A Witch''s Wail (Trailer ver.)' AND s.name = 'Paper Lily OST' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=eFckmP1EW3A', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'Lacie''s Lullaby' AND s.name = 'Paper Lily OST' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=JDjlGKaAakw', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'Every Day Lacie' AND s.name = 'Paper Lily OST' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=zRkoq8bwznQ', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'Anxiety' AND s.name = 'Paper Lily OST' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=bkZQIWa2z3g', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'Wistful - Sunset' AND s.name = 'Paper Lily OST' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=7_UIGbMTVnI', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'Theme of Brother' AND s.name = 'Paper Lily OST' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=CMQJVbyHfZw', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'The Way Forward' AND s.name = 'Paper Lily OST' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=5MmBKW_AX-g', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'One For Sorrow' AND s.name = 'Paper Lily OST' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=sNeJImiJeco', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'The Last Thing You See' AND s.name = 'Paper Lily OST' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=kRfVobstTpU', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'Rude Awakening' AND s.name = 'Paper Lily OST' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=S9m6TXqA038', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'Voice of Trees - Midnight' AND s.name = 'Paper Lily OST' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=m2lwAOzoVHE', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'Unexpected Danger' AND s.name = 'Paper Lily OST' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=LNXYl4WvI7Y', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'Theme of Rune' AND s.name = 'Paper Lily OST' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=MN7tnNA871w', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'Lost Between the Lines' AND s.name = 'Paper Lily OST' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=NgWkPTKDY_k', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'Voice of Trees - Daylight' AND s.name = 'Paper Lily OST' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=ONHUpksRzfA', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'Locked Horrors' AND s.name = 'Paper Lily OST' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=EfqHqItI6Uo', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'Respite' AND s.name = 'Paper Lily OST' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=u-U7Az-KrDI', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'Awkward Exchange' AND s.name = 'Paper Lily OST' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=tQQ9gEfIj1I', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'Shop Between Worlds - Closed' AND s.name = 'Paper Lily OST' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=-NqrF1VOTrk', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'The Facility' AND s.name = 'Paper Lily OST' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=M494Ty2GlOA', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'Theme of Sai' AND s.name = 'Paper Lily OST' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=Zy-V4mgpXps', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'A Single White Lily' AND s.name = 'Paper Lily OST' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=Y2Wmr-tj_ug', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'Burning Past' AND s.name = 'Paper Lily OST' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=W9gV8tQL-5U', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'Theme of Lacie' AND s.name = 'Paper Lily OST' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=kWVJUe7rEzI', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'Excuse Me!' AND s.name = 'Paper Lily OST' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=F9EknkCWt8A', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'Burning Resolve' AND s.name = 'Paper Lily OST' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=1pyh-NafO8A', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'With All Your Heart' AND s.name = 'Paper Lily OST' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=T122ApT2AM4', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'A Witch''s Wail' AND s.name = 'Paper Lily OST' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=ObAi-bfvAU4', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'A Witch''s Lament' AND s.name = 'Paper Lily OST' ON CONFLICT DO NOTHING;
INSERT INTO link (origin_type, origin_id, url, uploader_id) SELECT 'TRACK', t.id, 'https://www.youtube.com/watch?v=DxJLIhc394c', 4 FROM track t JOIN source s ON s.id = t.source_id WHERE t.name = 'Against All Odds' AND s.name = 'Paper Lily OST' ON CONFLICT DO NOTHING;

COMMIT TRANSACTION;
