INSERT INTO users.customer_user (external_id, username, email, role_id, status_id, created, address_line1, address_line2, "password")
VALUES
(gen_random_uuid(), 'jdoe', 'jdoe@example.com', 1, 0, now(), '123 Maple Street', 'Apt 4B', '$2a$10$t5FV/DmKq4GbRrckTV7kqe4QSqkrfPAE9sNmF.jKbFsrp0.0dBLx2'),
(gen_random_uuid(), 'asmith', 'asmith@example.com', 1, 0, now(), '456 Oak Avenue', '', '$2a$10$t5FV/DmKq4GbRrckTV7kqe4QSqkrfPAE9sNmF.jKbFsrp0.0dBLx2'),
(gen_random_uuid(), 'mbrown', 'mbrown@example.com', 1, 0, now(), '789 Pine Road', 'Suite 2', '$2a$10$t5FV/DmKq4GbRrckTV7kqe4QSqkrfPAE9sNmF.jKbFsrp0.0dBLx2'),
(gen_random_uuid(), 'tjohnson', 'tjohnson@example.com', 1, 0, now(), '321 Cedar Lane', '', '$2a$10$t5FV/DmKq4GbRrckTV7kqe4QSqkrfPAE9sNmF.jKbFsrp0.0dBLx2'),
(gen_random_uuid(), 'ewilson', 'ewilson@example.com', 1, 0, now(), '654 Birch Blvd', 'Floor 3', '$2a$10$t5FV/DmKq4GbRrckTV7kqe4QSqkrfPAE9sNmF.jKbFsrp0.0dBLx2'),
(gen_random_uuid(), 'dlee', 'dlee@example.com', 1, 0, now(), '987 Spruce Street', '', '$2a$10$t5FV/DmKq4GbRrckTV7kqe4QSqkrfPAE9sNmF.jKbFsrp0.0dBLx2'),
(gen_random_uuid(), 'hwalker', 'hwalker@example.com', 1, 0, now(), '159 Elm Street', 'Unit 12', '$2a$10$t5FV/DmKq4GbRrckTV7kqe4QSqkrfPAE9sNmF.jKbFsrp0.0dBLx2'),
(gen_random_uuid(), 'cwhite', 'cwhite@example.com', 1, 0, now(), '753 Willow Drive', '', '$2a$10$t5FV/DmKq4GbRrckTV7kqe4QSqkrfPAE9sNmF.jKbFsrp0.0dBLx2'),
(gen_random_uuid(), 'kgonzalez', 'kgonzalez@example.com', 1, 0, now(), '852 Aspen Court', '', '$2a$10$t5FV/DmKq4GbRrckTV7kqe4QSqkrfPAE9sNmF.jKbFsrp0.0dBLx2'),
(gen_random_uuid(), 'rbaker', 'rbaker@example.com', 1, 0, now(), '951 Poplar Way', 'Apt 8C', '$2a$10$t5FV/DmKq4GbRrckTV7kqe4QSqkrfPAE9sNmF.jKbFsrp0.0dBLx2'),
(gen_random_uuid(), 'nmartinez', 'nmartinez@example.com', 1, 0, now(), '147 Cypress Street', '', '$2a$10$t5FV/DmKq4GbRrckTV7kqe4QSqkrfPAE9sNmF.jKbFsrp0.0dBLx2'),
(gen_random_uuid(), 'lrodriguez', 'lrodriguez@example.com', 1, 0, now(), '258 Magnolia Ave', 'Suite 5', '$2a$10$t5FV/DmKq4GbRrckTV7kqe4QSqkrfPAE9sNmF.jKbFsrp0.0dBLx2'),
(gen_random_uuid(), 'jhernandez', 'jhernandez@example.com', 1, 0, now(), '369 Palm Street', '', '$2a$10$t5FV/DmKq4GbRrckTV7kqe4QSqkrfPAE9sNmF.jKbFsrp0.0dBLx2'),
(gen_random_uuid(), 'slopez', 'slopez@example.com', 1, 0, now(), '741 Redwood Lane', '', '$2a$10$t5FV/DmKq4GbRrckTV7kqe4QSqkrfPAE9sNmF.jKbFsrp0.0dBLx2'),
(gen_random_uuid(), 'bclark', 'bclark@example.com', 1, 0, now(), '852 Hickory Drive', 'Apt 2A', '$2a$10$t5FV/DmKq4GbRrckTV7kqe4QSqkrfPAE9sNmF.jKbFsrp0.0dBLx2'),
(gen_random_uuid(), 'dlewis', 'dlewis@example.com', 1, 0, now(), '963 Walnut Street', '', '$2a$10$t5FV/DmKq4GbRrckTV7kqe4QSqkrfPAE9sNmF.jKbFsrp0.0dBLx2'),
(gen_random_uuid(), 'pscott', 'pscott@example.com', 1, 0, now(), '111 Cherry Lane', '', '$2a$10$t5FV/DmKq4GbRrckTV7kqe4QSqkrfPAE9sNmF.jKbFsrp0.0dBLx2'),
(gen_random_uuid(), 'kgreen', 'kgreen@example.com', 1, 0, now(), '222 Sycamore Ave', 'Unit 7', '$2a$10$t5FV/DmKq4GbRrckTV7kqe4QSqkrfPAE9sNmF.jKbFsrp0.0dBLx2'),
(gen_random_uuid(), 'ahall', 'ahall@example.com', 1, 0, now(), '333 Dogwood Drive', '', '$2a$10$t5FV/DmKq4GbRrckTV7kqe4QSqkrfPAE9sNmF.jKbFsrp0.0dBLx2'),
(gen_random_uuid(), 'mallen', 'mallen@example.com', 1, 0, now(), '444 Fir Street', 'Suite 10', '$2a$10$t5FV/DmKq4GbRrckTV7kqe4QSqkrfPAE9sNmF.jKbFsrp0.0dBLx2');
COMMIT;
