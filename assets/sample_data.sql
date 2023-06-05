----------------- DATA -----------------
------ Room_Status ------
INSERT INTO room_status VALUES (
    1,
    'Da duoc thue'
);

INSERT INTO room_status VALUES (
    2,
    'Con trong'
);

INSERT INTO room_status VALUES (
    3,
    'Dang sua chua'
);

------ Room_Type ------
INSERT INTO room_type VALUES (
    1,
    'Co may lanh'
);

INSERT INTO room_type VALUES (
    2,
    'Khong may lanh'
);

------ Room ------
INSERT INTO room (
    name,
    capacity,
    rental_price,
    type_id,
    area,
    status_id
) VALUES (
    'Phong A',
    4,
    4000,
    1,
    20,
    2
);

INSERT INTO room (
    name,
    capacity,
    rental_price,
    type_id,
    area,
    status_id
) VALUES (
    'Phong B',
    2,
    5500,
    1,
    20,
    1
);

INSERT INTO room (
    name,
    capacity,
    rental_price,
    type_id,
    area,
    status_id
) VALUES (
    'Phong C',
    6,
    3000,
    2,
    20,
    2
);

INSERT INTO room (
    name,
    capacity,
    rental_price,
    type_id,
    area,
    status_id
) VALUES (
    'Phong D',
    8,
    2000,
    2,
    20,
    3
);

INSERT INTO room (
    name,
    capacity,
    rental_price,
    type_id,
    area,
    status_id
) VALUES (
    'Phong E',
    4,
    4000,
    2,
    20,
    2
);

------ Tenant_Status ------
INSERT INTO tenant_status VALUES (
    1,
    'Hoat dong'
);

INSERT INTO tenant_status VALUES (
    2,
    'Khong hoat dong'
);

INSERT INTO tenant_status VALUES (
    3,
    'Vo hieu hoa'
);

------ Tenant ------
INSERT INTO tenant (
    name,
    home_town,
    phone_number,
    password,
    email,
    dob,
    id_number
) VALUES (
    'Phuong A',
    'TPHCM',
    'xxxx4961',
    '1234',
    'tpa@gmail.com',
    '05/MAY/2003',
    '678912345'
);

INSERT INTO tenant (
    name,
    home_town,
    phone_number,
    password,
    email,
    dob,
    id_number,
    status_id
) VALUES (
    'My C',
    'Binh Dinh',
    'xxxx1234',
    '1010',
    'xxx1@gmail.com',
    '01/MAR/1998',
    '012346789',
    2
);

INSERT INTO tenant (
    name,
    home_town,
    phone_number,
    password,
    email,
    dob,
    id_number
) VALUES (
    'Tuan A',
    'TPHCM',
    'xxxx5678',
    '5555',
    'xxx2@gmail.com',
    '01/JUN/1995',
    '24681357'
);

INSERT INTO tenant (
    name,
    home_town,
    phone_number,
    password,
    email,
    dob,
    id_number,
    status_id
) VALUES (
    'Minh N',
    'TPHCM',
    'xxxx2468',
    '2222',
    'xxx3@gmail.com',
    '01/MAR/1996',
    '987654321',
    2
);

INSERT INTO tenant (
    name,
    home_town,
    phone_number,
    password,
    email,
    dob,
    id_number,
    status_id
) VALUES (
    'Oracle',
    'Thu Duc',
    'xxxx3579',
    '9999',
    'xxx4@gmail.com',
    '01/MAY/2000',
    '135792468',
    3
);

------ Contract_Status ------
INSERT INTO contract_status VALUES (
    1,
    'Hieu luc'
);

INSERT INTO contract_status VALUES (
    0,
    'Khong co hieu luc'
);

INSERT INTO contract_status VALUES (
    2,
    'Ket thuc dung han'
);

------ Contract ------
INSERT INTO contract (
    start_date,
    end_date,
    price_per_period,
    deposit,
    tenant_id,
    room_id,
    status_id
) VALUES (
    sysdate,
    '31/DEC/2023',
    5000,
    1000,
    'KH001',
    'P0001',
    1
);

INSERT INTO contract (
    start_date,
    end_date,
    price_per_period,
    deposit,
    tenant_id,
    room_id,
    status_id
) VALUES (
    sysdate,
    '31/DEC/2024',
    10000,
    1000,
    'KH002',
    'P0001',
    2
);

INSERT INTO contract (
    start_date,
    end_date,
    price_per_period,
    deposit,
    tenant_id,
    room_id,
    status_id
) VALUES (
    sysdate,
    '31/DEC/2025',
    8000,
    1000,
    'KH003',
    'P0002',
    1
);

INSERT INTO contract (
    start_date,
    end_date,
    price_per_period,
    deposit,
    tenant_id,
    room_id,
    status_id
) VALUES (
    sysdate,
    '31/DEC/2023',
    55000,
    1000,
    'KH001',
    'P0003',
    2
);

INSERT INTO contract (
    start_date,
    end_date,
    price_per_period,
    deposit,
    tenant_id,
    room_id,
    status_id
) VALUES (
    sysdate,
    '31/DEC/2024',
    4000,
    1000,
    'KH004',
    'P0001',
    1
);

------ Detail_Contract_Status ------
INSERT INTO detail_contract_status VALUES (
    1,
    'Dang tam tru'
);

INSERT INTO detail_contract_status VALUES (
    2,
    'Khong con tam tru'
);

------ Detail_Contract ------
INSERT INTO detail_contract (
    contract_id,
    tenant_id
) VALUES (
    'HG001',
    'KH003'
);

INSERT INTO detail_contract (
    contract_id,
    tenant_id
) VALUES (
    'HG003',
    'KH004'
);

INSERT INTO detail_contract (
    contract_id,
    tenant_id
) VALUES (
    'HG005',
    'KH001'
);

INSERT INTO detail_contract (
    contract_id,
    tenant_id
) VALUES (
    'HG001',
    'KH002'
);

INSERT INTO detail_contract (
    contract_id,
    tenant_id
) VALUES (
    'HG005',
    'KH003'
);

INSERT INTO detail_contract (
    contract_id,
    tenant_id
) VALUES (
    'HG005',
    'KH002'
);

------ Invoice_Status ------
INSERT INTO invoice_status VALUES (
    1,
    'Da thanh toan'
);

INSERT INTO invoice_status VALUES (
    0,
    'Chua thanh toan'
);

------ Invoice ------
INSERT INTO invoice (
    room_id,
    month,
    year
) VALUES (
    'P0001',
    5,
    2023
);

INSERT INTO invoice (
    room_id,
    month,
    year
) VALUES (
    'P0002',
    4,
    2023
);

INSERT INTO invoice (
    room_id,
    month,
    year
) VALUES (
    'P0002',
    3,
    2023
);

INSERT INTO invoice (
    room_id,
    month,
    year
) VALUES (
    'P0002',
    2,
    2023
);

INSERT INTO invoice (
    room_id,
    month,
    year
) VALUES (
    'P0003',
    1,
    2023
);

INSERT INTO invoice (
    room_id,
    month,
    year
) VALUES (
    'P0003',
    5,
    2023
);

------ Detail_Invoice_Type ------
INSERT INTO detail_invoice_type VALUES (
    1,
    'Dien',
    'm3'
);

INSERT INTO detail_invoice_type VALUES (
    2,
    'Nuoc',
    'kWh'
);

INSERT INTO detail_invoice_type VALUES (
    3,
    'Rac',
    'thang'
);

INSERT INTO detail_invoice_type VALUES (
    4,
    'Internet',
    'gio'
);

------ Detail_Invoice ------
INSERT INTO detail_invoice (
    invoice_id,
    type_id,
    quantity,
    unit_price
) VALUES (
    'HN001',
    1,
    50,
    50
);

INSERT INTO detail_invoice (
    invoice_id,
    type_id,
    quantity,
    unit_price
) VALUES (
    'HN002',
    2,
    50,
    50
);

INSERT INTO detail_invoice (
    invoice_id,
    type_id,
    quantity,
    unit_price
) VALUES (
    'HN003',
    3,
    50,
    50
);

INSERT INTO detail_invoice (
    invoice_id,
    type_id,
    quantity,
    unit_price
) VALUES (
    'HN004',
    4,
    50,
    50
);

INSERT INTO detail_invoice (
    invoice_id,
    type_id,
    quantity,
    unit_price
) VALUES (
    'HN005',
    1,
    50,
    50
);

------ Support_Ticket_Status ------
INSERT INTO support_ticket_status VALUES (
    0,
    'Cho xu ly'
);

INSERT INTO support_ticket_status VALUES (
    1,
    'Hoan tat'
);

------ Support_Ticket ------

INSERT INTO support_ticket (
    incident_time,
    receive_time,
    room_id,
    tenant_id,
    description
) VALUES (
    sysdate,
    sysdate,
    'P0001',
    'KH001',
    'Sap nen nha'
);

INSERT INTO support_ticket (
    incident_time,
    receive_time,
    room_id,
    tenant_id,
    description,
    status_id
) VALUES (
    sysdate,
    sysdate,
    'P0002',
    'KH002',
    'Phong ben canh gay on nhieu gio',
    1
);

INSERT INTO support_ticket (
    incident_time,
    receive_time,
    room_id,
    tenant_id,
    description,
    status_id
) VALUES (
    sysdate,
    sysdate,
    'P0003',
    'KH001',
    'Voi nuoc bi ri',
    1
);

INSERT INTO support_ticket (
    incident_time,
    receive_time,
    room_id,
    tenant_id,
    description,
    status_id
) VALUES (
    sysdate,
    sysdate,
    'P0004',
    'KH004',
    'Voi sen bi gay',
    1
);

    INSERT INTO support_ticket (
        incident_time,
        receive_time,
        room_id,
        tenant_id,
        description
    ) VALUES (
        sysdate,
        sysdate,
        'P0001',
        'KH003',
        'May lanh khong mo duoc'
    );
/

COMMIT;
/