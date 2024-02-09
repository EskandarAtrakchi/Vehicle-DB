# Read me

# Notes from the documentation for the following functions
## 1. The code is not following the naming convections
## 2. There might be mistakes in the code from te side of the database that thedatabase is not working for big amount of data
## 3. SQL Server that been installed for this is not suitable for big trafic 

# You are free to use the code as you want, but please pay atention before using it.

# Documentation starts here: 
# High-Level Design (HLD)

## Overview
The database is designed for a vehicle shop selling to municipalities in cities. It facilitates search, addition, and viewing of available vehicles for sale to organizations in both global (English speakers) and local markets, serving the public and private sectors.

# Low-Level Design (LLD)

## 1. Deletion Operations
a. Workers can delete vehicles from the database.
b. Users can delete their account.

## 2. Update Operations
Users should be able to update their account.

## 3. Authentication
Users can log in to their accounts.

## 4. Invoicing
a. Workers can create invoices for municipalities (Users).
b. Users can create an account.

## 5. Multi-Functionality
a. Workers can add more than one vehicle.
b. Users can add credit cards for transactions.

## 6. Vehicle Search
Users can search for vehicles based on specifications.

## 7. Reporting
Workers can generate reports about the vehicles.

## 8. Purchase
Users can buy vehicles using their credit cards.

## 9. Viewing
a. Workers can view vehicles and their specifications.
b. Users can view vehicles and their specifications.

## 10. Data Input
Workers can input details of the vehicle.

# Users of the Database
- Workers
- Anyone without an account (can view vehicles only)
- Registered Users (can perform various operations)

# Requirements Gathering
Fields needed to satisfy each LLD.

## 1. Deletion Operations
### 1.1 Users can delete the account.
- UserName
- UserPassWord
- UserDOB
- UserPhoneNumber
- UserAddress
- UserERICode
- UserID
- UserCardNumber 
- UserCCVNumber
- UserEXPDate

### 1.2 Workers can delete vehicles from the database.
- VehicleID
- VehicleName
- VehicleColor
- VehicleCapacity
- VehiclePrice
- VehicleCondition
- VehicleSize
- VehicleYearModel

## 2. Update Operations
- UserName
- UserPassWord
- UserDOB
- UserPhoneNumber
- UserAddress
- UserERICode
- UserID
- UserCardNumber 
- UserCCVNumber
- UserEXPDate

## 3. Authentication
- UserName
- UserPassWord

## 4. Invoicing
### 4.1 Users can create an account.
- UserName
- UserPassWord
- UserDOB
- UserPhoneNumber
- UserAddress
- UserERICode
- UserID
- UserCardNumber 
- UserCCVNumber
- UserEXPDate
- VehicleID

### 4.2 Workers can create invoices to the municipalities (Users)
- InvoiceID
- VehicleID 
- UserID
- InvoiceDate 
- WorkerName
- WorkerID
- UserCardNumber

## 5. Multi-Functionality
### 5.1 Credit cards can be added by buyers (Users)
- UserCCVNumber
- UserCardNumber
- UserCardExpirationDate
- UserID

### 5.2 Workers can add Vehicles to the database.
- VehiclePrice
- VehicleCondition
- VehicleSize
- VehicleYearModel
- VehicleID
- VehicleName
- VehicleColor
- VehicleCapacity

## 6. Vehicle Search
- VehicleID

## 7. Reporting
- VehicleID
- VehicleName
- VehicleColor
- VehicleCapacity
- VehiclePrice
- VehicleCondition
- VehicleSize
- VehicleYearModel

## 8. Purchase
- VehicleID
- VehicleName
- VehicleColor
- VehicleCapacity
- VehiclePrice
- VehicleCondition
- VehicleSize
- VehicleYearModel

## 9. Viewing
### 9.1 Users can view vehicles and their specifications.
- VehicleID
- VehicleName
- VehicleColor
- VehicleCapacity
- VehiclePrice
- VehicleCondition
- VehicleSize
- VehicleYearModel

### 9.2 Workers can view vehicles and their specifications.
- VehicleID
- VehicleName
- VehicleColor
- VehicleCapacity
- VehiclePrice
- VehicleCondition
- VehicleSize
- VehicleYearModel

## 10. Data Input
- VehicleID
- VehicleName
- VehicleColor
- VehicleCapacity
- VehiclePrice
- VehicleCondition
- VehicleSize
- VehicleYearModel

# Data Modelling
We need to normalize the following:

- UserName
- VehiclePrice
- VehicleCondition
- VehicleSize
- VehicleYearModel
- UserPassWord
- UserDOB
- UserPhoneNumber
- VehicleID
- VehicleName
- VehicleCapacity
- VehicleColor
- UserName
- UserPassWord
- UserPhoneNumber
- UserDOB
- UserAddress
- UserEriCode
- UserName
- UserPassWord
- UserName
- UserPassWord
- UserAddress
- UserEirCode 
- UserPhoneNumber
- UserDOB 
- InvoiceID
- VehicleColor
- VehicleID
- VehicleCapacity 
- VehicleName 
- UserEirCode 
- UserAddress
- UserName
- UserPhoneNumber
- UserDOB 
- UserName 
- UserPassWord
- UserDOB
- UserPhoneNumber
- UserCCVNumber
- UserCardNumber
- UserCardExpirationDate
- VehicleID
- VehicleName
- VehicleColor
- VehicleCapacity
- VehicleID
- VehicleName
- VehicleColor
- VehicleCapacity
- VehicleID
- VehicleName
- VehicleColor
- VehicleCapacity
- VehicleID
- VehicleName
- VehicleColor
- VehicleCapacity
- UserName 
- UserPassWord
- UserDOB
- UserPhoneNumber
- VehicleID
- VehicleName
- VehicleColor
- VehicleCapacity
- VehicleName
- VehicleColor
- VehicleCapacity
- WorkerID
- WorkerName 

# Normalization

## 1. First Normal Form (1NF)
- UserID
- VehicleID
- UserName
- UserPassWord
- UserDOB
- UserPhoneNumber
- UserAddress
- UserEriCode 
- InvoiceID 
- UserCCVNumber
- UserCardNumber
- UserCardExpirationDate
- WorkerID
- WorkerName
- VehicleName
- VehicleCapacity
- VehicleColor
- VehiclePrice
- VehicleCondition
- VehicleSize
- VehicleYearModel

## 2. Second Normal Form (2NF)
### Invoice:
- WorkerID
- WorkerName
- InvoiceID(PK)
- InvoiceDate
- VehicleID(FK)
- UserID(FK)
- UserCardNumber(FK)

### Vehicles:
- VehicleID(PK)
- VehicleName
- VehicleCapacity
- VehicleColor
- VehiclePrice
- VehicleCondition
- VehicleSize
- VehicleYearModel

### Users:
- UserID(PK)
- UserName
- UserPassWord
- UserDOB
- UserPhoneNumber
- UserAddress
- UserEriCode 

### Card:
- UserCCVNumber
- UserCardNumber(PK)
- UserCardExpirationDate
- UserID(FK)

## 3. Third Normal Form (3NF)
### Invoice:
- InvoiceID(PK)
- InvoiceDate
- WorkerID(FK)
- VehicleID(FK)
- UserID(FK)
- UserCardNumber(FK)

### Vehicles:
- VehicleID(PK)
- VehicleName
- VehicleCapacity
- VehicleColor
- VehiclePrice
- VehicleCondition
- VehicleSize
- VehicleYearModel

### Users:
- UserID(PK)
- UserName
- UserPassWord
- UserDOB
- UserPhoneNumber
- UserAddress
- UserEriCode 

### Card:
- UserCardNumber(PK)
- UserCCVNumber
- UserCardExpirationDate
- UserID(FK)


# Table 1: Vehicle Table

| Field           | Field Type | Field Size |
|-----------------|------------|------------|
| VehicleID(PK)   | Char       | 255        |
| VehicleName     | Varchar    | 255        |
| VehicleCapacity | int        |            |
| VehicleColor    | Varchar    | 255        |
| VehiclePrice    | float      |            |
| VehicleCondition| Varchar    | 255        |
| VehicleSize     | Varchar    | 255        |
| VehicleYearModel| int        |            |

# Table 2: User Table

| Field          | Field Type | Field Size |
|----------------|------------|------------|
| UserID(PK)     | Char       | 255        |
| UserPassWord   | Varchar    | 255        |
| UserDOB        | Date       |            |
| UserPhoneNumber| int        |            |
| UserAddress    | Varchar    | 255        |
| UserEriCode    | Varchar    | 255        |
| VehicleID(FK)  | Char       | 255        |

# Table 3: Invoice Table

| Field               | Field Type | Field Size |
|---------------------|------------|------------|
| InvoiceID(PK)       | Char       | 255        |
| UserID(FK)          | Char       | 255        |
| VehicleID(FK)       | Char       | 255        |
| UserCardNumber(FK)  | Char       | 255        |
| WorkerID            | int        |            |
| WorkerName          | Varchar    | 255        |
| InvoiceDate         | Date       |            |

# Table 4: UserCardNumber Table

| Field               | Field Type | Field Size |
|---------------------|------------|------------|
| UserCardNumber(PK)  | Char       | 255        |
| UserCCVNumber       | int        |            |
| UserCardExpDate     | Date       |            |
| UserID(FK)          | Char       | 255        |

# Data Dictionary

## Invoice Table Data

| WorkerID | WorkerName | InvoiceID(PK) | InvoiceDate | VehicleID(FK) | UserID(FK) | UserCardNumber(FK) |
|----------|------------|---------------|-------------|---------------|------------|---------------------|
| 1        | Alex       | 101           | 2021-01-01  | 201           | 301        | 987654321           |
| 2        | Josh       | 102           | 2021-02-02  | 202           | 302        | 123456789           |
| 3        | Yonas      | 103           | 2021-03-03  | 203           | 303        | 728364238           |
| 4        | Ann        | 104           | 2021-04-04  | 204           | 304        | 141242455           |
| 5        | Eskandar   | 105           | 2021-05-05  | 205           | 305        | 242465375           |
| 6        | Garvin     | 106           | 2021-06-06  | 206           | 306        | 357342151           |
| 7        | Pat        | 107           | 2021-07-07  | 207           | 307        | 845876554           |
| 8        | Joshua     | 108           | 2021-08-08  | 208           | 308        | 124162790           |
| 9        | Ali        | 109           | 2021-09-09  | 209           | 309        | 987087655           |
| 10       | Feby       | 110           | 2021-10-10  | 210           | 310        | 235375978           |

## UserCardNumber Table Data

| UserCardNumber(PK) | UserCCVNumber | UserCardExpirationDate | UserID(FK) |
|--------------------|---------------|------------------------|------------|
| 987654321          | 111           | 2025-01-01             | 301        |
| 123456789          | 222           | 2025-02-02             | 302        |
| 728364238          | 333           | 2025-03-03             | 303        |
| 141242455          | 444           | 2025-04-04             | 304        |
| 242465375          | 555           | 2025-05-05             | 305        |
| 357342151          | 666           | 2025-06-06             | 306        |
| 845876554          | 777           | 2025-07-07             | 307        |
| 124162790          | 888           | 2025-08-08             | 308        |
| 987087655          | 999           | 2025-09-09             | 309        |
| 235375978          | 100           | 2025-10-10             | 310        |

## Vehicle Table Data

| VehicleID(PK) | VehicleName | VehicleColor | VehiclePrice | VehicleCondition | VehicleSize | VehicleYearModel | VehicleCapacity |
|---------------|--------------|--------------|--------------|-------------------|-------------|-------------------|------------------|
| 201           | Nissan       | #FFFFFF      | 500000       | New               | Big         | 2010              | 1000             |
| 202           | Nissan       | #FFFFFF      | 500100       | New               | Big         | 2011              | 1001             |
| 203           | Hondai       | #FFFFFF      | 500200       | New               | Big         | 2012              | 1002             |
| 204           | Hondai       | #FFFFFF      | 500300       | New               | Big         | 2013              | 1003             |
| 205           | Nissan       | #FFFFFF      | 500400       | New               | Big         | 2014              | 1004             |
| 206           | Mercedes     | #FFFFFF      | 500500       | New               | Big         | 2015              | 1005             |
| 207           | Mercedes     | #FFFFFF      | 500600       | New               | Big         | 2016              | 1006             |
| 208           | Hondai       | #FFFFFF      | 500700       | New               | Medium      | 2017              | 1007             |
| 209           | MAN          | #FFFFFF      | 500800       | New               | Small       | 2018              | 1008             |
| 210           | Hondai       | #FF93FA      | 500900       | New               | Big         | 2019              | 1009             |

## User Table Data

| UserID(PK) | UserPassWord | UserDOB    | UserPhoneNumber | UserAddress  | UserEriCode | VehicleID(FK) | UserName |
|------------|--------------|------------|------------------|--------------|-------------|---------------|----------|
| 301        | 123456789    | 1980-01-01 | 07123456         | Queen’s Road | 123 ABC     | 201           | Tran     |
| 302        | 123456780    | 1981-02-02 | 07123457         | King’s Road  | 234 BCD     | 202           | Linda    |
| 303        | 123456790    | 1982-03-03 | 07123467         | Prince Road  | 345 CDE     | 203           | Alexa    |
| 304        | 123456890    | 1983-04-04 | 07123567         | Cool Road    | 456 DEF     | 204           | May      |
| 305        | 123456890    | 1984-05-05 | 07124567         | Hill Road    | 567 EFG     | 205           | Lara     |
| 306        | 123457890    | 1985-06-06 | 07124567         | Haven Road   | 678 FGH     | 206           | Yara     |
| 307        | 123467890    | 1986-07-07 | 07134567         | College Road | 789 GHI     | 207           | Sabrina  |
| 308        | 123567890    | 1987-08-08 | 07234567         | Uni Road     | 890 HIJ     | 208           | Kristina |
| 309        | 124567890    | 1988-09-09 | 01234567         | Camden Road  | 901 IJK     | 209           | Maria    |
| 310        | 134567890    | 1989-10-10 | 71234567         | School Road  | 012 JKL     | 210           | Mia      |

# Why Software Fails

## Introduction
Think of software failures that have impacted you. Last year’s project doing Vehicle Shop database using Java and SQL building GUI (Graphic User Interface), I faced a software failure that can be highlighted the role of software quality and testing. The issue happened because of oversight in handling user input for one of the fields, and that field was Bank Card Number of the customers, this field was very important because it is related to the invoice and to the purchased vehicles by the customers, if this field got error the whole App is facing troubles in all the GUI’s tabs.

### What was it? What Failed?
Where I have failed to set a limit for the following:
1. The limit for how many MAX_VALUE of integers needs to be accepted by the user. MAX_VALUE is representing the maximum positive number in decimal format to be accepted that can be represented in 32 bits and the number is 2147483647. This means that no number of type Integer that is greater than 2147483647 can exist in Java.
2. The second failure is that the user input was not limited to only numbers, rather was accepting any input, symbols, characters, decimal numbers, and this can make error especially the variable that been used is assigned as type of integer.

### Could testing have fixed it?
Test would have absolutely made change to the positive side, especially on user inputs testing would have highlighted the potential error that can be occurred and for restrictions on non-integers inputs. Various testing methodologies, such as unit testing, integration testing, and acceptance testing, could have been used to ensure that the software behaved as intended under different scenarios. Additionally, implementing error handling messages blocks is the proper way also for preventing the Vehicle Database App from crashing, this approach is more user friendly and more software stability!

Finally, glad to say that this error was address in resent GitHub commits and this was handled by providing better user experience and better software stability by providing error messages blocks to prevent potential App crash.

1. If more than 16 numbers are entered by the user, then a block message will pop up to inform the user.
2. If the user enters non-integer to the field a (only number) text will be displayed on the GUI to inform the user that only numbers is allowed in this field.

Please check Vehicles database on GitHub [Vehicle-Shop-DB](https://github.com/EskandarAtrakchi/Vehicle-Shop-DB) It shows the Vehicle Shop Database repo and the approach that been followed to address those two issues, and here is the testing plan that was conducted to make sure that the software is as much reliable as possible [Testing Plan](https://github.com/EskandarAtrakchi/Vehicle-Shop-DB/raw/master/DB-Test%20plan.xlsx)

The error was mitigated in those two methods.

```java 
private void cardnumberInvoiceFKeyTyped(java.awt.event.KeyEvent evt) {                                            

        char c = evt.getKeyChar();

        if (!Character.isDigit(c)) {

            jLabel35.setVisible(true);
            evt.consume();

        } else {

            jLabel35.setVisible(false);

        }

    }      
```
- Which is allowing the field to consume only digits otherwise a message on GUI will pop up and notify the user.
  
```java 
   private void cardnumberInvoiceFKeyPressed(java.awt.event.KeyEvent evt) {                                              

        int lengthPIN = cardnumberInvoiceF.getText().length();

        if (lengthPIN == 16) {

            cardnumberInvoiceF.setText("");

            JOptionPane.showMessageDialog(null, "You cannot enter more than 16 digits");

        }

    }                
```
- Which allowing the field to take only 16 digits if 16 digits or more a message block will pop up to notify the user that only 16 digits is allowed.
