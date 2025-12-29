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

COMMIT TRANSACTION;
