INSERT INTO app_role (role_id, role_name) VALUES
(1,'ADMIN'),
(2,'USER');
INSERT INTO app_user (user_id, enable_user, encrypted_password, username) VALUES
(1,1,'$2a$10$F0U/2tzYuphX9Bkq40EvY.W3GnborsatUhVK6tm2.IfD7sfhbER8m', 'dbadmin'),
(2,1,'$2a$10$F0U/2tzYuphX9Bkq40EvY.W3GnborsatUhVK6tm2.IfD7sfhbER8m', 'user');

INSERT INTO user_role (user_role_id, role_id, user_id) values
 (1,1,1),
 (2,2,1),
 (3,2,2);

