INSERT INTO account (username, password) VALUES('admin', 'admin');

INSERT INTO room_status (id, name) VALUES (1, 'Available');
INSERT INTO room_status (id, name) VALUES (2, 'Occupied');
INSERT INTO room_status (id, name) VALUES (3, 'Under Maintenance');

INSERT INTO room_type (id, name) VALUES (1, 'Single');
INSERT INTO room_type (id, name) VALUES (2, 'Double');
INSERT INTO room_type (id, name) VALUES (3, 'Suite');

INSERT INTO room (id, name, capacity, rental_price, type_id, area, status_id)
VALUES ('R001', 'Room 1', 1, 1000, 1, 200, 1);
INSERT INTO room (id, name, capacity, rental_price, type_id, area, status_id)
VALUES ('R002', 'Room 2', 2, 1500, 2, 250, 1);
INSERT INTO room (id, name, capacity, rental_price, type_id, area, status_id)
VALUES ('R003', 'Room 3', 1, 1200, 1, 180, 1);

INSERT INTO tenant_status (id, name) VALUES (1, 'Active');
INSERT INTO tenant_status (id, name) VALUES (2, 'Inactive');

INSERT INTO tenant (id, name, home_town, phone_number, password, email, dob, id_number, status_id)
VALUES ('KH001', 'John Doe', 'New York', '1234567890', 'password123', 'johndoe@example.com', TO_DATE('1990-01-15', 'YYYY-MM-DD'), '123456789012', 1);
INSERT INTO tenant (id, name, home_town, phone_number, password, email, dob, id_number, status_id)
VALUES ('KH002', 'Jane Smith', 'Los Angeles', '9876543210', 'secret456', 'janesmith@example.com', TO_DATE('1988-05-20', 'YYYY-MM-DD'), '987654321098', 1);

INSERT INTO support_ticket_type (id, name) VALUES (1, 'Maintenance');
INSERT INTO support_ticket_type (id, name) VALUES (2, 'Complaint');
INSERT INTO support_ticket_type (id, name) VALUES (3, 'Request');

INSERT INTO support_ticket_status (id, name) VALUES (1, 'Open');
INSERT INTO support_ticket_status (id, name) VALUES (2, 'In Progress');
INSERT INTO support_ticket_status (id, name) VALUES (3, 'Closed');

INSERT INTO support_ticket (id, type_id, incident_time, receive_time, room_id, tenant_id, description, status_id)
VALUES (1, 1, SYSDATE, SYSDATE, 'R001', 'KH001', 'Broken faucet', 1);
INSERT INTO support_ticket (id, type_id, incident_time, receive_time, room_id, tenant_id, description, status_id)
VALUES (2, 2, SYSDATE, SYSDATE, 'R002', 'KH002', 'Noise issue', 1);

INSERT INTO invoice_status (id, name) VALUES (0, 'Unpaid');
INSERT INTO invoice_status (id, name) VALUES (1, 'Paid');

INSERT INTO invoice (id, room_id, month, year, total_money, status_id)
VALUES ('HN001', 'R001', 5, 2023, 1500, 0);
INSERT INTO invoice (id, room_id, month, year, total_money, status_id)
VALUES ('HN002', 'R002', 5, 2023, 2000, 1);

INSERT INTO detail_invoice_type (id, name, unit) VALUES (1, 'Electricity', 'kWh');
INSERT INTO detail_invoice_type (id, name, unit) VALUES (2, 'Water', 'm³');

INSERT INTO detail_invoice (invoice_id, type_id, quantity, unit_price, sum_money)
VALUES ('HN001', 1, 100, 5, 500);
INSERT INTO detail_invoice (invoice_id, type_id, quantity, unit_price, sum_money)
VALUES ('HN001', 2, 50, 3, 150);

INSERT INTO contract_status (id, name) VALUES (1, 'Active');
INSERT INTO contract_status (id, name) VALUES (2, 'Terminated');

INSERT INTO contract (id, start_date, end_date, price_per_period, deposit, tenant_id, room_id, status_id)
VALUES ('HG001', SYSDATE, ADD_MONTHS(SYSDATE, 6), 2000, 5000, 'KH001', 'R001', 1);
INSERT INTO contract (id, start_date, end_date, price_per_period, deposit, tenant_id, room_id, status_id)
VALUES ('HG002', SYSDATE, ADD_MONTHS(SYSDATE, 12), 3000, 6000, 'KH002', 'R002', 1);

INSERT INTO detail_contract_status (id, name) VALUES (1, 'Primary');
INSERT INTO detail_contract_status (id, name) VALUES (2, 'Representative');

INSERT INTO detail_contract (contract_id, tenant_id) VALUES ('HG001', 'KH001');
INSERT INTO detail_contract (contract_id, tenant_id) VALUES ('HG002', 'KH002');