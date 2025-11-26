-- database music-tagger
BEGIN TRANSACTION;

-- *************************************************************************************************
-- Drop all db objects in the proper order
-- *************************************************************************************************
DROP TABLE IF EXISTS source_merge_log CASCADE;
DROP TABLE IF EXISTS source_merge_vote CASCADE;
DROP TABLE IF EXISTS artist_merge_log CASCADE;
DROP TABLE IF EXISTS artist_merge_vote CASCADE;
DROP TABLE IF EXISTS track_merge_log CASCADE;
DROP TABLE IF EXISTS track_merge_vote CASCADE;
DROP TABLE IF EXISTS track_tag_counter CASCADE;
DROP TABLE IF EXISTS track_tag_downvote CASCADE;
DROP TABLE IF EXISTS track_tag CASCADE;
DROP TABLE IF EXISTS track_alias CASCADE;
DROP TABLE IF EXISTS track_artist CASCADE;
DROP TABLE IF EXISTS link_downvote CASCADE;
DROP TABLE IF EXISTS link CASCADE;
DROP TABLE IF EXISTS tag CASCADE;
DROP TABLE IF EXISTS track CASCADE;
DROP TABLE IF EXISTS source CASCADE;
DROP TABLE IF EXISTS artist CASCADE;
DROP TABLE IF EXISTS users CASCADE;

-- *************************************************************************************************
-- Create the tables and constraints
-- *************************************************************************************************

--users (name is pluralized because 'user' is a SQL keyword)
CREATE TABLE users (
	id SERIAL PRIMARY KEY,
	username TEXT NOT NULL UNIQUE,
	password_hash TEXT NOT NULL,
	role TEXT NOT NULL
);

CREATE TABLE artist (
    id SERIAL PRIMARY KEY,
    name TEXT NOT NULL,
    uploaded_by INTEGER REFERENCES users(id)
);

CREATE TABLE source (
    id SERIAL PRIMARY KEY,
    name TEXT NOT NULL,
    uploaded_by INTEGER REFERENCES users(id)
);

CREATE TABLE track (
    id SERIAL PRIMARY KEY,
    name TEXT NOT NULL,
	source_id INTEGER REFERENCES source(id),
    uploaded_by INTEGER REFERENCES users(id)
);

CREATE TABLE tag (
    id SERIAL PRIMARY KEY,
    name TEXT NOT NULL UNIQUE
);

CREATE TABLE link (
    id SERIAL PRIMARY KEY,
    origin_type TEXT NOT NULL CHECK (origin_type IN ('track','artist','source')),
    origin_id INTEGER NOT NULL,
    url TEXT NOT NULL,
    uploaded_by INTEGER REFERENCES users(id),
    UNIQUE(origin_type, origin_id, url)
);

CREATE TABLE link_downvote (
    id SERIAL PRIMARY KEY,
    link_id INTEGER NOT NULL REFERENCES link(id) ON DELETE CASCADE,
    user_id INTEGER NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    UNIQUE(link_id, user_id)
);

CREATE TABLE track_artist (
    track_id INTEGER NOT NULL REFERENCES track(id) ON DELETE CASCADE,
    artist_id INTEGER NOT NULL REFERENCES artist(id) ON DELETE CASCADE,
    PRIMARY KEY(track_id, artist_id)
);

CREATE TABLE track_alias (
    id SERIAL PRIMARY KEY,
    track_id INTEGER NOT NULL REFERENCES track(id) ON DELETE CASCADE,
    alias TEXT NOT NULL,
    uploaded_by INTEGER REFERENCES users(id),
    UNIQUE(track_id, alias)
);

CREATE TABLE track_tag (
    id SERIAL PRIMARY KEY,
    track_id INTEGER NOT NULL REFERENCES track(id) ON DELETE CASCADE,
    tag_id INTEGER NOT NULL REFERENCES tag(id) ON DELETE CASCADE,
    user_id INTEGER NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    UNIQUE(track_id, tag_id, user_id)
);

CREATE TABLE track_tag_downvote (
    id SERIAL PRIMARY KEY,
    track_id INTEGER NOT NULL REFERENCES track(id) ON DELETE CASCADE,
    tag_id INTEGER NOT NULL REFERENCES tag(id) ON DELETE CASCADE,
    user_id INTEGER NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    UNIQUE(track_id, tag_id, user_id)
);

CREATE TABLE track_tag_counter (
    track_id INTEGER NOT NULL REFERENCES track(id) ON DELETE CASCADE,
    tag_id INTEGER NOT NULL REFERENCES tag(id) ON DELETE CASCADE,
    vote_count INTEGER NOT NULL DEFAULT 0,
    PRIMARY KEY(track_id, tag_id)
);

CREATE TABLE track_merge_vote (
    id SERIAL PRIMARY KEY,
    original_id INTEGER NOT NULL REFERENCES track(id),
    duplicate_id INTEGER NOT NULL REFERENCES track(id),
    user_id INTEGER NOT NULL REFERENCES users(id),
    UNIQUE(original_id, duplicate_id, user_id)
);

CREATE TABLE track_merge_log (
    id SERIAL PRIMARY KEY,
    original_id INTEGER NOT NULL REFERENCES track(id),
    duplicate_id INTEGER NOT NULL REFERENCES track(id),
    created_at TIMESTAMP NOT NULL DEFAULT NOW(),
    admin_id INTEGER REFERENCES users(id)
);

CREATE TABLE artist_merge_vote (
    id SERIAL PRIMARY KEY,
    original_id INTEGER NOT NULL REFERENCES artist(id),
    duplicate_id INTEGER NOT NULL REFERENCES artist(id),
    user_id INTEGER NOT NULL REFERENCES users(id),
    UNIQUE(original_id, duplicate_id, user_id)
);

CREATE TABLE artist_merge_log (
    id SERIAL PRIMARY KEY,
    original_id INTEGER NOT NULL REFERENCES artist(id),
    duplicate_id INTEGER NOT NULL REFERENCES artist(id),
    created_at TIMESTAMP NOT NULL DEFAULT NOW(),
    admin_id INTEGER REFERENCES users(id)
);

CREATE TABLE source_merge_vote (
    id SERIAL PRIMARY KEY,
    original_id INTEGER NOT NULL REFERENCES source(id),
    duplicate_id INTEGER NOT NULL REFERENCES source(id),
    user_id INTEGER NOT NULL REFERENCES users(id),
    UNIQUE(original_id, duplicate_id, user_id)
);

CREATE TABLE source_merge_log (
    id SERIAL PRIMARY KEY,
    original_id INTEGER NOT NULL REFERENCES source(id),
    duplicate_id INTEGER NOT NULL REFERENCES source(id),
    created_at TIMESTAMP NOT NULL DEFAULT NOW(),
    admin_id INTEGER REFERENCES users(id)
);

CREATE INDEX idx_track_tag_track_tag ON track_tag (track_id, tag_id);
CREATE INDEX idx_track_tag_downvote_user_tag_track ON track_tag_downvote (user_id, tag_id, track_id);
CREATE INDEX idx_track_tag_counter_tag_count ON track_tag_counter (tag_id, vote_count DESC);
CREATE INDEX idx_track_artist_artist_track ON track_artist (artist_id, track_id);
CREATE INDEX idx_link_origin ON link (origin_type, origin_id);
CREATE INDEX idx_track_merge_vote_pair ON track_merge_vote (original_id, duplicate_id);
CREATE INDEX idx_track_merge_log_pair ON track_merge_log (original_id, duplicate_id);

-- *************************************************************************************************
-- Insert some sample starting data
-- *************************************************************************************************

-- Users
-- Password for all users is password
INSERT INTO users (username, password_hash, role) VALUES
    ('user1', '$2a$10$tmxuYYg1f5T0eXsTPlq/V.DJUKmRHyFbJ.o.liI1T35TFbjs2xiem', 'ROLE_USER'),
    ('user2', '$2a$10$tmxuYYg1f5T0eXsTPlq/V.DJUKmRHyFbJ.o.liI1T35TFbjs2xiem', 'ROLE_USER'),
    ('user3', '$2a$10$tmxuYYg1f5T0eXsTPlq/V.DJUKmRHyFbJ.o.liI1T35TFbjs2xiem', 'ROLE_USER'),
    ('admin','$2a$10$tmxuYYg1f5T0eXsTPlq/V.DJUKmRHyFbJ.o.liI1T35TFbjs2xiem', 'ROLE_ADMIN');

COMMIT TRANSACTION;
