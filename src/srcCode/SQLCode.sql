CREATE TABLE VEHICLES (
    VehicleID Char NOT NULL,
    VehicleName Varchar NOT NULL,
    VehicleCpacity int NOT NULL,
    VehicleColor Varchar NOT NULL,
    VehiclePrice float NOT NULL,
    VehicleCondition Varchar NOT NULL,
    VehicleSize Varchar NOT NULL,
    VehicleYearModel int NOT NULL,
    PRIMARY KEY (VehicleID)
);

INSERT INTO VEHICLES
VALUES 
('201', 'Nissan', 1000, '#FFFFFF', 500000, 'New', 'Big', 2010),
('202', 'Nissan', 1001, '#FFFFFF', 500100, 'New', 'Big', 2011),
('203', 'Hondai', 1002, '#FFFFFF', 500200, 'New', 'Big', 2012),
('204', 'Hondai', 1003, '#FFFFFF', 500300, 'New', 'Big', 2013),
('205', 'Nissan', 1004, '#FFFFFF', 500400, 'New', 'Big',2014),
('206', 'Mercedes', 1005, '#FFFFFF', 500500,'New', 'Big', 2015),
('207', 'Mercedes', 1006, '#FFFFFF', 500600, 'New', 'Big', 2016),
('208', 'Hondai', 1007, '#FFFFFF', 500700, 'New', 'Medium', 2017),
('209', 'MAN', 1008, '#FFFFFF', 500800, 'New', 'Small', 2018),
('210', 'Hondai', 1009, '#FF93FA', 500900, 'New', 'Big', 2019);

CREATE TABLE USERS (
    UserID Char NOT NULL,
    UserPassWord Varchar NOT NULL,
    UserDOB Date NOT NULL,
    UserPhoneNumber int NOT NULL,
    UserAddress Varchar NOT NULL,
    UserEriCode Varchar NOT NULL,
    VehicleID Char NOT NULL,
    UserName Varchar NOT NULL,
    PRIMARY KEY (UserID),
    FOREIGN KEY (VehicleID) REFERENCES VEHICLES(VehicleID)
);

INSERT INTO USERS 
VALUES 
('301', '123456789', '1980-01-01', 7123456, 'QueensRoad' , '123ABC', '201', 'Tran'),
('302', '123456780', '1981-02-02', 7123457, 'KingsRoad' , '234BCD', '202', 'Linda'),
('303', '123456790', '1982-03-03', 7123467, 'PrinceRoad' , '345CDE', '203', 'Alexa'),
('304', '123456890', '1983-04-04', 7123567, 'CoolRoad' , '456DEF', '204', 'May'),
('305', '123456890', '1984-05-05', 7124567, 'HillRoad' , '567EFG', '205', 'Lara'),
('306', '123457890', '1985-06-06', 7124567, 'HavenRoad' , '678FGH', '206', 'Yara'),
('307', '123467890', '1986-07-07', 7134567, 'CollegeRoad' , '789GHI', '207', 'Sabrina'),
('308', '123567890', '1987-08-08', 7234567, 'UniRoad' , '890HIJ', '208', 'Kristina'),
('309', '124567890', '1988-09-09', 1234567, 'CamdenRoad' , '901IJK', '209', 'Maria'),
('310', '134567890', '1989-10-10', 71234567, 'SchoolRoad' , '012JKL', '210', 'Mia');

CREATE TABLE CREDITCARD (
    UserCardNumber Char NOT NULL,
    UserCCVNumber int NOT NULL,
    UserCardExpirationDate Date NOT NULL,
    UserID Char NOT NULL,
    PRIMARY KEY (UserCardNumber),
    FOREIGN KEY (UserID) REFERENCES USERS (UserID)
);

INSERT INTO CREDITCARD 
VALUES 
('987654321', 111, '2025-01-01', '301'),
('123456789', 222, '2025-02-02', '302'),
('728364238', 333, '2025-03-03', '303'),
('141242455', 444, '2025-04-04', '304'),
('242465375', 555, '2025-05-05', '305'),
('357342151', 666, '2025-06-06', '306'),
('845876554', 777, '2025-07-07', '307'),
('124162790', 888, '2025-08-08', '308'),
('987087655', 999, '2025-09-09', '309'),
('235375978', 100, '2025-10-10', '310');

CREATE TABLE INVOICE (
    WorkerID int NOT NULL,
    WorkerName  Varchar NOT NULL,
    InvoiceID Char NOT NULL,
    InvoiceDate DATE NOT NULL,
    VehicleID Char NOT NULL,
    UserID Char NOT NULL,
    UserCardNumber Char NOT NULL,
    PRIMARY KEY (InvoiceID),
    FOREIGN KEY (VehicleID) REFERENCES VEHICLES (VehicleID),
    FOREIGN KEY (UserID) REFERENCES USERS (UserID),
    FOREIGN KEY (UserCardNumber) REFERENCES CREDITCARD (UserCardNumber)
);

INSERT INTO INVOICE
VALUES 
(1, 'Alex', '101', '2021-1-1', '201', '301', '987654321'),
(2, 'Josh', '102', '2021-2-2', '202', '302', '123456789'),
(3, 'Yonas', '103', '2021-3-3', '203', '303', '728364238'),
(4, 'Ann', '104', '2021-4-4', '204', '304', '141242455'),
(5, 'Eskandar', '105', '2021-5-5', '205', '305', '242465375'),
(6, 'Garvin', '106', '2021-6-6', '206', '306', '357342151'),
(7, 'Pat', '107', '2021-7-7', '207', '307', '845876554'),
(8, 'Joshua', '108', '2021-8-8', '208', '308', '124162790'),
(9, 'Ali', '109', '2021-9-9', '209', '309', '987087655'),
(10, 'Feby', '110', '2021-10-10', '210', '310', '235375978');
