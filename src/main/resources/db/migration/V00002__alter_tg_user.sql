ALTER TABLE IF EXISTS tg_user

    ADD PRIMARY KEY (chat_id),

    ADD COLUMN user_name VARCHAR (100),
    ADD COLUMN first_name VARCHAR (50),
    ADD COLUMN last_name VARCHAR (80),
    ADD COLUMN is_bot BOOLEAN NOT NULL DEFAULT FALSE;


COMMENT ON TABLE tg_user is 'user info';