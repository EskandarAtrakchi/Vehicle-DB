# Read Me 

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
- InvoiceID(P
