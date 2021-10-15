INSERT INTO app_role (role_id, role_name) VALUES
(1,'ADMIN'),
(2,'USER');

INSERT INTO app_user (user_id, enable_user, encrypted_password, username) VALUES
 (1,1,'$2a$10$TSzNMqK9KIzYa8kTbFqBPOaMOdho7c9/TW501b.uEeHD195FSq7ne', 'dbadmin'),
 (2,1,'$2a$10$TSzNMqK9KIzYa8kTbFqBPOaMOdho7c9/TW501b.uEeHD195FSq7ne', 'user');

INSERT INTO user_role (user_role_id, role_id, user_id) values
 (1,1,1),
 (2,2,1),
 (3,2,2);

