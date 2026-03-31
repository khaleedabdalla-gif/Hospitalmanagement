import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;

// ===================== BASE CLASSES =====================

abstract class Human {
    private String fullName;
    private int age;

    public Human(String fullName, int age) {
        this.fullName = fullName;
        this.age = age;
    }

    public String getFullName() { return fullName; }
    public int getAge() { return age; }

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

// ===================== INPATIENT =====================
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

    public String toData() {
        return getFullName() + "," + getAge() + "," + disease + "," + roomNo;
    }
}

// ===================== SURGEON =====================
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

    public String toData() {
        return getFullName() + "," + getAge() + "," + basicPay + "," + field;
    }
}

// ===================== INVOICE =====================
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

// ===================== VISIT =====================
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

// ===================== MAIN SYSTEM =====================
public class HospitalSystem {

    // COLLECTIONS
    static ArrayList<InPatient> patientList = new ArrayList<>();
    static ArrayList<Surgeon> doctorList = new ArrayList<>();

    // SAVE TO FILE
    public static void saveToFile() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("hospital_data.txt"));

            for (InPatient p : patientList) {
                bw.write("PATIENT:" + p.toData());
                bw.newLine();
            }

            for (Surgeon d : doctorList) {
                bw.write("DOCTOR:" + d.toData());
                bw.newLine();
            }

            bw.close();
            System.out.println("Data saved successfully!");

        } catch (Exception e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }

    // LOAD FROM FILE
    public static void loadFromFile() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("hospital_data.txt"));
            String line;

            while ((line = br.readLine()) != null) {
                if (line.startsWith("PATIENT:")) {
                    String[] d = line.substring(8).split(",");
                    patientList.add(new InPatient(d[0], Integer.parseInt(d[1]), d[2], Integer.parseInt(d[3])));
                }
                else if (line.startsWith("DOCTOR:")) {
                    String[] d = line.substring(7).split(",");
                    doctorList.add(new Surgeon(d[0], Integer.parseInt(d[1]), Double.parseDouble(d[2]), d[3]));
                }
            }
            br.close();
            System.out.println("Data loaded successfully!");

        } catch (Exception e) {
            System.out.println("Error loading file: " + e.getMessage());
        }
    }

    // ===================== MAIN =====================
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.println("=== Hospital Management System ===");

        // ---- Enter one patient ----
        System.out.print("Enter patient name: ");
        String pName = input.nextLine();

        System.out.print("Enter age: ");
        int pAge = input.nextInt();
        input.nextLine();

        System.out.print("Enter disease: ");
        String disease = input.nextLine();

        System.out.print("Enter room number: ");
        int room = input.nextInt();
        input.nextLine();

        InPatient p1 = new InPatient(pName, pAge, disease, room);
        patientList.add(p1);

        // ---- Enter a Surgeon ----
        System.out.print("Enter doctor name: ");
        String dName = input.nextLine();

        System.out.print("Enter doctor age: ");
        int dAge = input.nextInt();

        System.out.print("Enter basic pay: ");
        double pay = input.nextDouble();
        input.nextLine();

        System.out.print("Enter specialization: ");
        String field = input.nextLine();

        Surgeon d1 = new Surgeon(dName, dAge, pay, field);
        doctorList.add(d1);

        // ---- SAVE TO FILE ----
        saveToFile();

        // ---- LOAD FROM FILE ----
        loadFromFile();

        // ---- DISPLAY EVERYTHING ----
        System.out.println("\n=== RECORDS FROM COLLECTION ===");
        for (InPatient pp : patientList) pp.showInfo();
        for (Surgeon dd : doctorList) dd.showInfo();

    input.close();
}

    
}

