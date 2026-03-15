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

    public abstract void showInfo(); // must be overridden
}

// Second abstract class
abstract class Employee extends Human {
    protected double basicPay;

    public Employee(String fullName, int age, double basicPay) {
        super(fullName, age);   // calling parent constructor
        this.basicPay = basicPay;
    }

    public abstract double computePay(); // must be overridden
}

// Interface 1
interface Payable {
    double calculatePayment();
}

// Interface 2
interface Schedulable {
    void schedule();
}

// Concrete class 1
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

// Concrete class 2
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
        return basicPay + 20000;  // bonus added
    }
}

// Concrete class 3
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

// Concrete class 4
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

        InPatient patient1 = new InPatient("Omar", 22, "Malaria", 12);
        Surgeon doctor1 = new Surgeon("Dr Aisha", 45, 90000, "Neurology");
        Invoice bill1 = new Invoice(1500, 2500);
        Visit visit1 = new Visit("25 Feb 2026");

        patient1.showInfo();
        doctor1.showInfo();

        System.out.println("Doctor Total Pay: " + doctor1.computePay());
        System.out.println("Total Bill: " + bill1.calculatePayment());

        visit1.schedule();
    }
}
