----------------- DATA -----------------
------ Room_Status ------
INSERT INTO ROOM_STATUS VALUES(1, 'Con trong');
INSERT INTO ROOM_STATUS VALUES(2, 'Full');
INSERT INTO ROOM_STATUS VALUES(3, 'Dang sua chua');

------ Tenant_Status ------
INSERT INTO TENANT_STATUS VALUES(1, 'Hoat dong');
INSERT INTO TENANT_STATUS VALUES(2, 'Khong hoat dong');
INSERT INTO TENANT_STATUS VALUES(3, 'Vo hieu hoa');

------ Contract_Status ------
INSERT INTO contract_status VALUES (1, 'Hieu luc');
INSERT INTO contract_status VALUES (0, 'Khong co hieu luc');
INSERT INTO contract_status VALUES (2, 'Ket thuc dung han');

------ Detail_Contract_Status ------
INSERT INTO DETAIL_CONTRACT_STATUS VALUES(1, 'Dang tam tru');
INSERT INTO DETAIL_CONTRACT_STATUS VALUES(2, 'Khong con tam tru');

------ Invoice_Status ------
INSERT INTO INVOICE_STATUS VALUES(1, 'Da thanh toan');
INSERT INTO INVOICE_STATUS VALUES(0, 'Chua thanh toan');

------ Support_Ticket_Status ------
INSERT INTO support_ticket_status VALUES (0, 'Cho xu ly');
INSERT INTO support_ticket_status VALUES (1, 'Hoan tat');


------ Room_Type ------
INSERT INTO ROOM_TYPE VALUES(1, 'Co may lanh');
INSERT INTO ROOM_TYPE VALUES(2, 'Khong may lanh');

------ Detail_Invoice_Type ------
INSERT INTO DETAIL_INVOICE_TYPE VALUES(0, 'Tro', 'thang');
INSERT INTO DETAIL_INVOICE_TYPE VALUES(1, 'Dien', 'm3');
INSERT INTO DETAIL_INVOICE_TYPE VALUES(2, 'Nuoc', 'kWh');
INSERT INTO DETAIL_INVOICE_TYPE VALUES(3, 'Rac', 'thang');
INSERT INTO DETAIL_INVOICE_TYPE VALUES(4, 'Internet', 'gio');

------ Room ------
INSERT INTO ROOM(name, capacity, rental_price, type_id, area) VALUES('Phong A', 4, 4000, 1, 20);
INSERT INTO ROOM(name, capacity, rental_price, type_id, area) VALUES('Phong B', 8, 5500, 1, 20);
INSERT INTO ROOM(name, capacity, rental_price, type_id, area) VALUES('Phong C', 6, 3000, 2, 20);
INSERT INTO ROOM(name, capacity, rental_price, type_id, area) VALUES('Phong D', 8, 2000, 2, 20);
INSERT INTO ROOM(name, capacity, rental_price, type_id, area) VALUES('Phong E', 4, 4000, 2, 20);

------ Tenant ------
INSERT INTO TENANT(name, home_town, phone_number, password, email, dob, id_number) VALUES('Phuong A', 'TPHCM', 'xxxx4961', '1234', 'tpa@gmail.com', '05/MAY/2003','678912345');
INSERT INTO TENANT(name, home_town, phone_number, password, email, dob, id_number) VALUES('My C', 'Binh Dinh', 'xxxx1234', '1010','xxx1@gmail.com', '01/MAR/1998', '012346789');
INSERT INTO TENANT(name, home_town, phone_number, password, email, dob, id_number) VALUES('Tuan A', 'TPHCM', 'xxxx5678', '5555','xxx2@gmail.com', '01/JUN/1995', '24681357');
INSERT INTO TENANT(name, home_town, phone_number, password, email, dob, id_number) VALUES('Minh N', 'TPHCM', 'xxxx2468', '2222', 'xxx3@gmail.com', '01/MAR/1996','987654321');
INSERT INTO TENANT(name, home_town, phone_number, password, email, dob, id_number) VALUES('Oracle', 'Thu Duc', 'xxxx3579', '9999', 'xxx4@gmail.com', '01/MAY/2000','135792468');

------ Contract ------
INSERT INTO CONTRACT(START_DATE, END_DATE, PRICE_PER_PERIOD, DEPOSIT, TENANT_ID, ROOM_ID) VALUES(SYSDATE, '31/DEC/2023', 5000, 1000, 'KH001', 'P0001');
INSERT INTO CONTRACT(START_DATE, END_DATE, PRICE_PER_PERIOD, DEPOSIT, TENANT_ID, ROOM_ID) VALUES(SYSDATE, '31/DEC/2025', 8000, 1000, 'KH003', 'P0002');

------ Detail_Contract ------

------ Invoice ------
INSERT INTO INVOICE(Room_ID, Month, Year) VALUES ('P0001', 5, 2023);
INSERT INTO INVOICE(Room_ID, Month, Year) VALUES ('P0002', 4, 2023);
INSERT INTO INVOICE(Room_ID, Month, Year) VALUES ('P0002', 3, 2023);
INSERT INTO INVOICE(Room_ID, Month, Year) VALUES ('P0002', 2, 2023);

------ Detail_Invoice ------
INSERT INTO DETAIL_INVOICE(INVOICE_ID, type_id,quantity,unit_price) VALUES('HN001', 1, 50, 50);
INSERT INTO DETAIL_INVOICE(INVOICE_ID, type_id,quantity,unit_price) VALUES('HN002', 2, 50, 50);
INSERT INTO DETAIL_INVOICE(INVOICE_ID, type_id,quantity,unit_price) VALUES('HN003', 3, 50, 50);
INSERT INTO DETAIL_INVOICE(INVOICE_ID, type_id,quantity,unit_price) VALUES('HN004', 4, 50, 50);

------ Support_Ticket ------
INSERT INTO support_ticket (incident_time, receive_time, room_id, tenant_id, description)
VALUES (SYSDATE, SYSDATE, 'P0001', 'KH001', 'Sap nen nha');
INSERT INTO support_ticket (incident_time, receive_time, room_id, tenant_id, description, status_id)
VALUES (SYSDATE, SYSDATE, 'P0002', 'KH002', 'Phong ben canh gay on nhieu gio', 1);
INSERT INTO support_ticket (incident_time, receive_time, room_id, tenant_id, description, status_id)
VALUES (SYSDATE, SYSDATE, 'P0003', 'KH001', 'Voi nuoc bi ri', 1);
INSERT INTO support_ticket (incident_time, receive_time, room_id, tenant_id, description, status_id)
VALUES (SYSDATE, SYSDATE, 'P0004', 'KH004', 'Voi sen bi gay', 1);
INSERT INTO support_ticket (incident_time, receive_time, room_id, tenant_id, description)
VALUES (SYSDATE, SYSDATE, 'P0001', 'KH003', 'May lanh khong mo duoc');
    