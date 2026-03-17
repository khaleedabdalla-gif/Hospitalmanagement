# Hospital Management System – Activity 3
**Dynamic Input & Exception Handling**

## 📌 Project Description
This project is an enhancement of the previous OOP Hospital Management System.  
The purpose of Activity 3 is to allow users to enter data dynamically and to use exception handling to make the program more robust and safe.

The project uses core Object-Oriented Programming concepts such as:
- **Abstraction**
- **Inheritance**
- **Interfaces**
- **Encapsulation**
- **Polymorphism (via abstract methods)**

### 👨‍⚕️ System Overview
The project simulates a simple hospital management system containing:
- **InPatient** – stores patient info  
- **Surgeon** – employee with specialization and salary computation  
- **Invoice** – implements Payable to compute patient bill  
- **Visit** – implements Schedulable to schedule a visit  

---

## 🧩 Classes Used

### **1. Human (abstract)**  
Base class with shared attributes:  
- fullName  
- age  

### **2. Employee (abstract)**  
Extends Human and adds:  
- basicPay  
- computePay() abstract method  

### **3. InPatient**  
Extends Human and stores:  
- disease  
- roomNo  

### **4. Surgeon**  
Extends Employee  
Implements computePay by adding allowance to basicPay  

### **5. Invoice (implements Payable)**  
Calculates:
- consultationFee  
- medicineCharge  

### **6. Visit (implements Schedulable)**  
Stores:
- visit date  

---

## ⚙️ Exceptions Implemented

The program uses **try / catch / finally** to handle errors safely.

### ✔ InputMismatchException  
Occurs when the user enters an invalid type (e.g., text instead of a number):

```java
catch (InputMismatchException e) {
    System.out.println("Error: Invalid input type!");
}
## 🔗 GitHub Repository Link
https://github.com/khaleedabdalla-gif/Hospitalmanagement2