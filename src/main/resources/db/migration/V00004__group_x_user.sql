CREATE TABLE IF NOT EXISTS group_x_user(
    group_sub_id INT NOT NULL,
    user_id VARCHAR(100) NOT NULL,
    FOREIGN KEY (group_sub_id) REFERENCES group_sub(id),
    FOREIGN KEY (user_id) REFERENCES tg_user(chat_id),
    UNIQUE(user_id, group_sub_id)
);

COMMENT ON TABLE group_x_user IS 'many-to-many for user and group';