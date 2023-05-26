INSERT INTO account (username, password) VALUES('admin', 'admin');

INSERT INTO room_status (id, name) VALUES (0, 'Kh�ng cho thu�');
INSERT INTO room_status (id, name) VALUES (1, 'Ph�ng kh? d?ng');

INSERT INTO room_type (id, name) VALUES (0, 'C? b?n');
INSERT INTO room_type (id, name) VALUES (1, 'Cao c?p');

INSERT INTO room (name, capacity, rental_price, type_id, area, status_id)
VALUES ( 'Ph�ng 1', 4, 1600, 0, 60, 1);
INSERT INTO room (name, capacity, rental_price, type_id, area, status_id)
VALUES ( 'Ph�ng 2', 2, 1000, 1, 80, 1);

INSERT INTO tenant_status (id, name) VALUES (1, 'Ho?t ??ng');
INSERT INTO tenant_status (id, name) VALUES (0, 'Kh�ng ho?t ??ng');

INSERT INTO tenant ( name, home_town, phone_number, email, dob, id_number, status_id)
VALUES ( 'Nguy?n V?n A' , 'Ph� Y�n', '0987654321', 'abc@xyz.com', TO_DATE('2003/07/09', 'yyyy/mm/dd'), '9876543210', 1);
INSERT INTO tenant ( name, home_town, phone_number, email, dob, id_number, status_id)
VALUES ( 'Nguy?n V?n A' , 'B�nh ??nh', '0123456789', 'xyz@abc.com', TO_DATE('2003/01/01', 'yyyy/mm/dd'), '0123456789', 1);
INSERT INTO tenant ( name, home_town, phone_number, email, dob, id_number, status_id)
VALUES ( 'Nguy?n V?n B' , 'B�nh ??nh', '0846116513', 'xyz@abc.com', TO_DATE('2003/01/01', 'yyyy/mm/dd'), '0846116513', 1);
INSERT INTO tenant ( name, home_town, phone_number, email, dob, id_number, status_id)
VALUES ( 'Nguy?n V?n C' , 'B�nh ??nh', '0897985643', 'xyz@abc.com', TO_DATE('2003/01/01', 'yyyy/mm/dd'), '0897985643', 1);

INSERT INTO support_ticket_status (id, name) VALUES (0, 'Ch? x? l�');
INSERT INTO support_ticket_status (id, name) VALUES (1, 'Ho�n t?t');

INSERT INTO support_ticket (incident_time, receive_time, room_id, tenant_id, description, status_id)
VALUES (SYSDATE, SYSDATE, 'P0001', 'KH001', 'S?p nh�', 0);
INSERT INTO support_ticket (incident_time, receive_time, room_id, tenant_id, description, status_id)
VALUES (SYSDATE, SYSDATE, 'P0002', 'KH002', 'ph�ng b�n ?�nh v?n g�y ?n', 1);

INSERT INTO contract_status (id, name) VALUES (1, 'Hi?u l?c');
INSERT INTO contract_status (id, name) VALUES (0, 'Kh�ng c� hi?u l?c');
INSERT INTO contract_status (id, name) VALUES (2, 'K?t th�c ?�ng h?n');
INSERT INTO contract_status (id, name) VALUES (3, 'T?t to�n s?m');

INSERT INTO contract (start_date, end_date, deposit, tenant_id, room_id, status_id)
VALUES (SYSDATE, ADD_MONTHS(SYSDATE, 6), 5000, 'KH001', 'P0001', 1);

INSERT INTO contract (start_date, end_date, deposit, tenant_id, room_id, status_id)
VALUES (SYSDATE, ADD_MONTHS(SYSDATE, 12), 8000, 'KH002', 'P0002', 1);

INSERT INTO detail_contract_status (id, name) VALUES (1, 'Primary');
INSERT INTO detail_contract_status (id, name) VALUES (2, 'Representative');

INSERT INTO detail_contract (contract_id, tenant_id) VALUES ('HG001', 'KH001');
INSERT INTO detail_contract (contract_id, tenant_id) VALUES ('HG002', 'KH002');
INSERT INTO invoice_status (id, name) VALUES (0, 'Ch? thanh to�n');
INSERT INTO invoice_status (id, name) VALUES (1, '?� thanh to�n');

INSERT INTO invoice (id, room_id, month, year, total_money, status_id)
VALUES ('HN001', 'R001', 5, 2023, 0, 0);
INSERT INTO invoice (id, room_id, month, year, total_money, status_id)
VALUES ('HN002', 'R002', 5, 2023, 2000, 1);

INSERT INTO detail_invoice_type (id, name, unit) VALUES (0, 'Thue phong', 'thang');
INSERT INTO detail_invoice_type (id, name, unit) VALUES (1, '?i?n', 'kWh');
INSERT INTO detail_invoice_type (id, name, unit) VALUES (2, 'N??c', 'm�');

INSERT INTO detail_invoice (invoice_id, type_id, quantity, unit_price, sum_money)
VALUES ('HN001', 1, 100, 5, 500);
INSERT INTO detail_invoice (invoice_id, type_id, quantity, unit_price, sum_money)
VALUES ('HN001', 2, 50, 3, 150);