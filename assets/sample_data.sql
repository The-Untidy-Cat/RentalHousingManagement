----------------- DATA -----------------
------ Room_Status ------
INSERT INTO ROOM_STATUS VALUES(1, 'Da duoc thue');
INSERT INTO ROOM_STATUS VALUES(2, 'Con trong');
INSERT INTO ROOM_STATUS VALUES(3, 'Dang sua chua');

------ Room_Type ------
INSERT INTO ROOM_TYPE VALUES(1, 'Co may lanh');
INSERT INTO ROOM_TYPE VALUES(2, 'Khong may lanh');

------ Room ------
INSERT INTO ROOM(name, capacity, rental_price, type_id, area, status_id) VALUES('Phong A', 4, 4000, 1, 20, 2);
INSERT INTO ROOM(name, capacity, rental_price, type_id, area, status_id) VALUES('Phong B', 2, 5500, 1, 20, 1);
INSERT INTO ROOM(name, capacity, rental_price, type_id, area, status_id) VALUES('Phong C', 6, 3000, 2, 20, 2);
INSERT INTO ROOM(name, capacity, rental_price, type_id, area, status_id) VALUES('Phong D', 8, 2000, 2, 20, 3);
INSERT INTO ROOM(name, capacity, rental_price, type_id, area, status_id) VALUES('Phong E', 4, 4000, 2, 20, 2);

------ Tenant_Status ------
INSERT INTO TENANT_STATUS VALUES(1, 'Hoat dong');
INSERT INTO TENANT_STATUS VALUES(2, 'Khong hoat dong');
INSERT INTO TENANT_STATUS VALUES(3, 'Vo hieu hoa');

------ Tenant ------
INSERT INTO TENANT(name, home_town, phone_number, password, email, dob, id_number) VALUES('Phuong A', 'TPHCM', 'xxxx4961', '1234', 'tpa@gmail.com', '05/04/2003','678912345');
INSERT INTO TENANT(name, home_town, phone_number, password, email, dob, id_number, status_id) VALUES('My C', 'Binh Dinh', 'xxxx1234', '1010','xxx1@gmail.com', '01/01/1998', '012346789',2);
INSERT INTO TENANT(name, home_town, phone_number, password, email, dob, id_number) VALUES('Tuan A', 'TPHCM', 'xxxx5678', '5555','xxx2@gmail.com', '01/01/1995', '24681357');
INSERT INTO TENANT(name, home_town, phone_number, password, email, dob, id_number, status_id) VALUES('Minh N', 'TPHCM', 'xxxx2468', '2222', 'xxx3@gmail.com', '01/01/1996','987654321',2);
INSERT INTO TENANT(name, home_town, phone_number, password, email, dob, id_number, status_id) VALUES('Oracle', 'Thu Duc', 'xxxx3579', '9999', 'xxx4@gmail.com', '01/01/2000','135792468', 3);

------ Contract_Status ------
INSERT INTO contract_status VALUES (1, 'Hieu luc');
INSERT INTO contract_status VALUES (0, 'Khong co hieu luc');
INSERT INTO contract_status VALUES (2, 'Ket thuc dung han');

------ Contract ------
INSERT INTO CONTRACT(START_DATE, END_DATE, PRICE_PER_PERIOD, DEPOSIT, TENANT_ID, ROOM_ID, STATUS_ID) VALUES(SYSDATE, '31/12/2023', 5000, 1000, 'KH001', 'P0001', 1);
INSERT INTO CONTRACT(START_DATE, END_DATE, PRICE_PER_PERIOD, DEPOSIT, TENANT_ID, ROOM_ID, STATUS_ID) VALUES(SYSDATE, '31/12/2024', 10000, 1000, 'KH002', 'P0001', 2);
INSERT INTO CONTRACT(START_DATE, END_DATE, PRICE_PER_PERIOD, DEPOSIT, TENANT_ID, ROOM_ID, STATUS_ID) VALUES(SYSDATE, '31/12/2025', 8000, 1000, 'KH003', 'P0002', 1);
INSERT INTO CONTRACT(START_DATE, END_DATE, PRICE_PER_PERIOD, DEPOSIT, TENANT_ID, ROOM_ID, STATUS_ID) VALUES(SYSDATE, '31/12/2023', 55000, 1000, 'KH001', 'P0003', 2);
INSERT INTO CONTRACT(START_DATE, END_DATE, PRICE_PER_PERIOD, DEPOSIT, TENANT_ID, ROOM_ID, STATUS_ID) VALUES(SYSDATE, '31/12/2024', 4000, 1000, 'KH004', 'P0004', 1); --error
INSERT INTO CONTRACT(START_DATE, END_DATE, PRICE_PER_PERIOD, DEPOSIT, TENANT_ID, ROOM_ID, STATUS_ID) VALUES(SYSDATE, '31/12/2024', 4000, 1000, 'KH004', 'P0001', 1);

------ Detail_Contract ------
INSERT INTO DETAIL_CONTRACT VALUES('HG001', 'KH003', '01/01/2023', SYSDATE);
INSERT INTO DETAIL_CONTRACT VALUES('HG003', 'KH004', '01/01/2023', SYSDATE);
INSERT INTO DETAIL_CONTRACT VALUES('HG005', 'KH001', '01/01/2023', SYSDATE);
INSERT INTO DETAIL_CONTRACT VALUES('HG001', 'KH002', '01/01/2023', SYSDATE);
INSERT INTO DETAIL_CONTRACT VALUES('HG005', 'KH003', '01/01/2023', SYSDATE);
INSERT INTO DETAIL_CONTRACT VALUES('HG005', 'KH002', '01/01/2023', SYSDATE);

------ Detail_Contract_Status ------
INSERT INTO DETAIL_CONTRACT_STATUS VALUES(1, 'Dang tam tru');
INSERT INTO DETAIL_CONTRACT_STATUS VALUES(2, 'Khong con tam tru');

------ Invoice_Status ------
INSERT INTO INVOICE_STATUS VALUES(1, 'Da thanh toan');
INSERT INTO INVOICE_STATUS VALUES(0, 'Chua thanh toan');

------ Invoice ------
INSERT INTO INVOICE(Room_ID, Month, Year, Total_Money, Status_ID, CREATED_DATE, MODIFIED_DATE) VALUES ('P0001', 5, 2023, 5000, 0, '01/05/2023', SYSDATE);
INSERT INTO INVOICE(Room_ID, Month, Year, Total_Money, Status_ID, CREATED_DATE, MODIFIED_DATE) VALUES ('P0002', 4, 2023, 8000, 1, '01/04/2023', SYSDATE);
INSERT INTO INVOICE(Room_ID, Month, Year, Total_Money, Status_ID, CREATED_DATE, MODIFIED_DATE) VALUES ('P0002', 3, 2023, 3500, 0, '01/03/2023', SYSDATE);
INSERT INTO INVOICE(Room_ID, Month, Year, Total_Money, Status_ID, CREATED_DATE, MODIFIED_DATE) VALUES ('P0002', 2, 2023, 55000, 1,'01/02/2023', SYSDATE);
INSERT INTO INVOICE(Room_ID, Month, Year, Total_Money, Status_ID, CREATED_DATE, MODIFIED_DATE) VALUES ('P0003', 1, 2023, 4000, 1, '01/01/2023', SYSDATE);
INSERT INTO INVOICE(Room_ID, Month, Year, Total_Money, Status_ID, CREATED_DATE, MODIFIED_DATE) VALUES ('P0003', 5, 2023, 4000, 1, '01/01/2023', SYSDATE);
INSERT INTO INVOICE(Room_ID, Month, Year, Total_Money, Status_ID, CREATED_DATE, MODIFIED_DATE) VALUES ('P0001', 5, 2023, 4000, 1, '01/01/2023', SYSDATE); --error

------ Detail_Invoice_Type ------
INSERT INTO DETAIL_INVOICE_TYPE VALUES(1, 'Dien', 'm3');
INSERT INTO DETAIL_INVOICE_TYPE VALUES(2, 'Nuoc', 'kWh');
INSERT INTO DETAIL_INVOICE_TYPE VALUES(3, 'Rac','abc');
INSERT INTO DETAIL_INVOICE_TYPE VALUES(4, 'Internet', 'gio');

------ Detail_Invoice ------
INSERT INTO DETAIL_INVOICE VALUES('HN001', 1, 50, 50, 2500);
INSERT INTO DETAIL_INVOICE VALUES('HN002', 2, 50, 50, 2500);
INSERT INTO DETAIL_INVOICE VALUES('HN003', 3, 50, 50, 2500);
INSERT INTO DETAIL_INVOICE VALUES('HN004', 4, 50, 50, 2500);
INSERT INTO DETAIL_INVOICE VALUES('HN005', 1, 50, 50, 2500);

------ Support_Ticket_Status ------
INSERT INTO support_ticket_status VALUES (0, 'Cho xu ly');
INSERT INTO support_ticket_status VALUES (1, 'Hoan tat');

------ Support_Ticket ------
ALTER TABLE SUPPORT_TICKET MODIFY ID VARCHAR2(5);
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
    