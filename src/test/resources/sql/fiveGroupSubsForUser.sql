INSERT INTO tg_user(chat_id, user_name, first_name, last_name, active)
VALUES (1,'testTest', 'firstTest', 'lastTest', true);

INSERT INTO group_sub
VALUES (1, 'g1', 1),
       (2, 'g2', 2),
       (3, 'g3', 3),
       (4, 'g4', 4),
       (5, 'g5', 5);

INSERT INTO group_x_user
VALUES (1, 1),
       (2, 1),
       (3, 1),
       (4, 1),
       (5, 1);