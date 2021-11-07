INSERT INTO app_role (role_id, role_name) VALUES
(1,'ADMIN'),
(2,'USER');
INSERT INTO app_user (user_id, enable_user, encrypted_password, username) VALUES
(1,1,'$2a$10$jVeQwMRXJIdrHwEdn9bUwO/CWkPTBxoSBSnczbQrxjoq4aSKBss0O', 'dbadmin'),
(2,1,'$2a$10$jVeQwMRXJIdrHwEdn9bUwO/CWkPTBxoSBSnczbQrxjoq4aSKBss0O', 'user');

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
phone, email, date_accept, date_fired, position_worker_id, department_id) values
(1, 'Ivanov', 'Ivan', 'Ivanovich', 3654643, null, null, null, 1, 1),
(2, 'Petrov', 'Petr', 'Petrovich', 1234567, null, null, null, 1, 1);
INSERT INTO equipment (id, name, description, location) values
(1, 'sert', ' frez', '37'),
(2, 'kotr', ' frez', '45');
INSERT INTO report (id, name, start_date, end_date, description) values
(1, 'first report', null, null, 'Descript First Report'),
(2, 'second report', null, null, 'Descript second Report');
INSERT INTO status_ticket (id, status_name) values
(1, 'Stopped'),
(2, 'In work');
INSERT INTO priority (id, priority_name) values
(1, 'High'),
(2, 'Medium');
--INSERT INTO ticket (id, status_ticket_id, priority_id, worker_id, description,
--client_department_id, equipment_id, start_date, end_date) values
--(1, 1, 2, 1, 'Fault in air', 2, 2, '2021-10-14', '2021-10-18')