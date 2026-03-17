import java.util.InputMismatchException;
import java.util.Scanner;

// Abstract base class
abstract class Human {
    private String fullName;
    private int age;

    public Human(String fullName, int age) {
        this.fullName = fullName;
        this.age = age;
    }

    public String getFullName() {
        return fullName;
    }

    public int getAge() {
        return age;
    }

    public abstract void showInfo();
}

abstract class Employee extends Human {
    protected double basicPay;

    public Employee(String fullName, int age, double basicPay) {
        super(fullName, age);
        this.basicPay = basicPay;
    }

    public abstract double computePay();
}

interface Payable {
    double calculatePayment();
}

interface Schedulable {
    void schedule();
}

class InPatient extends Human {
    private String disease;
    private int roomNo;

    public InPatient(String fullName, int age, String disease, int roomNo) {
        super(fullName, age);
        this.disease = disease;
        this.roomNo = roomNo;
    }

    @Override
    public void showInfo() {
        System.out.println("Patient: " + getFullName() +
                ", Disease: " + disease +
                ", Room: " + roomNo);
    }
}

class Surgeon extends Employee {
    private String field;

    public Surgeon(String fullName, int age, double basicPay, String field) {
        super(fullName, age, basicPay);
        this.field = field;
    }

    @Override
    public void showInfo() {
        System.out.println("Doctor: " + getFullName() +
                ", Specialization: " + field);
    }

    @Override
    public double computePay() {
        return basicPay + 20000;
    }
}

class Invoice implements Payable {
    private double consultationFee;
    private double medicineCharge;

    public Invoice(double consultationFee, double medicineCharge) {
        this.consultationFee = consultationFee;
        this.medicineCharge = medicineCharge;
    }

    @Override
    public double calculatePayment() {
        return consultationFee + medicineCharge;
    }
}

class Visit implements Schedulable {
    private String date;

    public Visit(String date) {
        this.date = date;
    }

    @Override
    public void schedule() {
        System.out.println("Visit scheduled on: " + date);
    }
}

public class HospitalSystem {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        try {
            System.out.println("=== Hospital Management System ===");

            // -------------------- INPATIENT --------------------
            System.out.print("Enter patient full name: ");
            String pName = input.nextLine();

            System.out.print("Enter patient age: ");
            int pAge = input.nextInt();
            input.nextLine(); // clear leftover newline

            System.out.print("Enter disease: ");
            String disease = input.nextLine();

            System.out.print("Enter room number: ");
            int roomNo = input.nextInt();
            input.nextLine();

            InPatient patient1 = new InPatient(pName, pAge, disease, roomNo);

            // -------------------- SURGEON --------------------
            System.out.print("Enter doctor full name: ");
            String dName = input.nextLine();

            System.out.print("Enter doctor age: ");
            int dAge = input.nextInt();

            System.out.print("Enter basic pay: ");
            double dPay = input.nextDouble();
            input.nextLine();

            System.out.print("Enter specialization: ");
            String field = input.nextLine();

            Surgeon doctor1 = new Surgeon(dName, dAge, dPay, field);

            // -------------------- BILL / INVOICE --------------------
            System.out.print("Enter consultation fee: ");
            double fee = input.nextDouble();

            System.out.print("Enter medicine charge: ");
            double med = input.nextDouble();
            input.nextLine();

            Invoice bill1 = new Invoice(fee, med);

            // -------------------- VISIT --------------------
            System.out.print("Enter visit date (e.g., 25 Feb 2026): ");
            String date = input.nextLine();
            Visit visit1 = new Visit(date);

            // -------------------- OUTPUT --------------------
            System.out.println("\n==== OUTPUT ====");
            patient1.showInfo();
            doctor1.showInfo();
            System.out.println("Doctor Total Pay: " + doctor1.computePay());
            System.out.println("Total Bill: " + bill1.calculatePayment());
            visit1.schedule();

        } catch (InputMismatchException e) {
            System.out.println("Error: Invalid input type!");
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
        } finally {
            System.out.println("Program execution complete.");
            input.close();
        }
    }
}
