INSERT INTO app_role (role_id, role_name) VALUES
(1,'ADMIN'),
(2,'USER');
INSERT INTO app_user (user_id, enable_user, encrypted_password, username) VALUES
(1,1,'$2a$10$T8J6nlNje6RrUp5XZlIjY.pI9nZ0cWAHsm.PStHJKvM1kybsJGbF.', 'dbadmin'),
(2,1,'$2a$10$T8J6nlNje6RrUp5XZlIjY.pI9nZ0cWAHsm.PStHJKvM1kybsJGbF.', 'user');

INSERT INTO user_role (user_role_id, role_id, user_id) values
 (1,1,1),
 (2,2,1),
 (3,2,2);
INSERT INTO position_worker (id, position_name) values
 (1, 'Engineer'),
 (2, 'Manager');
 INSERT INTO department (id, name) values
 (1, 'Service department'),
 (2, 'Managment department');
INSERT INTO worker (id, first_name, last_name, patronymic,
phone, email, date_accept, date_fired) values
(1, 'Ivanov', 'Ivan', 'Ivanovich', 3654643, null, null, null),
(2, 'Petrov', 'Petr', 'Petrovich', 1234567, null, null, null);
INSERT INTO equipment (id, name, description, location) values
(1, 'sert', ' frez', '37'),
(2, 'kotr', ' frez', '45');
INSERT INTO report (id, name, start_date, end_date, description) values
(1, 'first report', null, null, 'Descript First Report'),
(2, 'second report', null, null, 'Descript second Report');
