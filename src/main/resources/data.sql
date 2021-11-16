INSERT INTO app_role (role_name) VALUES
('ADMIN'),
('USER');
INSERT INTO app_user (enable_user, encrypted_password, username) VALUES
(1,'$2a$10$jVeQwMRXJIdrHwEdn9bUwO/CWkPTBxoSBSnczbQrxjoq4aSKBss0O', 'dbadmin'),
(1,'$2a$10$jVeQwMRXJIdrHwEdn9bUwO/CWkPTBxoSBSnczbQrxjoq4aSKBss0O', 'user');

INSERT INTO user_role (role_id, user_id) values
(1,1),
(2,1),
(2,2);
INSERT INTO position_worker (position_name) values
('Engineer'),
('Manager');
INSERT INTO department (name) values
('Service department'),
('Managment department'),
('depart 3'),
('depart 4'),
('depart 5'),
('depart 6');
INSERT INTO worker (first_name, last_name, patronymic,
phone, email, date_accept, date_fired, position_worker_id, department_id) values
('Ivanov', 'Ivan', 'Ivanovich', 3654643, null, null, null, 1, 1),
('Petrov', 'Petr', 'Petrovich', 1234567, null, null, null, 2, 1);
INSERT INTO equipment (name, description, location) values
('sert', ' frez', '37'),
('kotr', ' frez', '45');
INSERT INTO report (name, start_date, end_date, description) values
('first report', null, null, 'Descript First Report'),
('second report', null, null, 'Descript second Report');
INSERT INTO status_ticket (status_name) values
('Stopped'),
('In work');
INSERT INTO priority (priority_name) values
('High'),
('Medium');
INSERT INTO ticket (status_ticket_id, priority_id, description,
department_id, equipment_id, start_date, end_date) values
(1, 2, 'Fault in air', 2, 2, '2021-10-14', '2021-10-18'),
(1, 2, 'Exception', 2, 2, '2021-09-12', '2021-10-01');
INSERT INTO worker_ticket (worker_id, ticket_id) values
(1,1);