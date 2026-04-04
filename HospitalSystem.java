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
                ", Age: " + getAge() +
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
                ", Age: " + getAge() +
                ", Specialization: " + field +
                ", Pay: " + computePay());
    }

    @Override
    public double computePay() {
        return basicPay + 20000;
    }

    public String toData() {
        return getFullName() + "," + getAge() + "," + basicPay + "," + field;
    }
}

// ===================== MAIN SYSTEM =====================
public class MainHospitalSystem {

    static ArrayList<InPatient> patientList = new ArrayList<>();
    static ArrayList<Surgeon> doctorList = new ArrayList<>();

    // -------- SAFE INPUT (EXCEPTION HANDLING) --------
    public static int safeInt(Scanner input, String msg) {
        while (true) {
            try {
                System.out.print(msg);
                int val = input.nextInt();
                input.nextLine();
                return val;
            } catch (InputMismatchException e) {
                System.out.println("❌ Invalid number. Try again.");
                input.nextLine();
            }
        }
    }

    public static double safeDouble(Scanner input, String msg) {
        while (true) {
            try {
                System.out.print(msg);
                double val = input.nextDouble();
                input.nextLine();
                return val;
            } catch (InputMismatchException e) {
                System.out.println("❌ Invalid amount. Try again.");
                input.nextLine();
            }
        }
    }

    // -------- SAVE TO FILE --------
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
            System.out.println("✅ Data saved!");

        } catch (Exception e) {
            System.out.println("❌ Error saving file: " + e.getMessage());
        } finally {
            System.out.println("✅ Save operation completed.\n");
        }
    }

    // -------- LOAD FROM FILE --------
    public static void loadFromFile() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("hospital_data.txt"));

            String line;
            while ((line = br.readLine()) != null) {
                if (line.startsWith("PATIENT:")) {
                    String[] d = line.substring(8).split(",");
                    patientList.add(new InPatient(d[0],
                            Integer.parseInt(d[1]), d[2], Integer.parseInt(d[3])));
                } else if (line.startsWith("DOCTOR:")) {
                    String[] d = line.substring(7).split(",");
                    doctorList.add(new Surgeon(d[0],
                            Integer.parseInt(d[1]), Double.parseDouble(d[2]), d[3]));
                }
            }
            br.close();
            System.out.println("✅ Data loaded!");

        } catch (Exception e) {
            System.out.println("❌ Error loading file: " + e.getMessage());
        } finally {
            System.out.println("✅ Load operation completed.\n");
        }
    }

    // -------- MENU SYSTEM --------
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        int choice;

        do {
            System.out.println("===== Hospital Menu =====");
            System.out.println("1. Add In-Patient");
            System.out.println("2. Add Surgeon");
            System.out.println("3. Display All");
            System.out.println("4. Save to File");
            System.out.println("5. Load from File");
            System.out.println("6. Exit");

            choice = safeInt(input, "Enter choice: ");

            switch (choice) {

                case 1:
                    System.out.print("Enter patient name: ");
                    String name = input.nextLine();

                    int age = safeInt(input, "Enter age: ");
                    System.out.print("Enter disease: ");
                    String disease = input.nextLine();
                    int room = safeInt(input, "Enter room number: ");

                    patientList.add(new InPatient(name, age, disease, room));
                    System.out.println("✅ Patient added.\n");
                    break;

                case 2:
                    System.out.print("Enter doctor name: ");
                    String dName = input.nextLine();

                    int dAge = safeInt(input, "Enter doctor age: ");
                    double pay = safeDouble(input, "Enter basic pay: ");
                    System.out.print("Enter specialization: ");
                    String field = input.nextLine();

                    doctorList.add(new Surgeon(dName, dAge, pay, field));
                    System.out.println("✅ Surgeon added.\n");
                    break;

                case 3:
                    System.out.println("===== PATIENTS =====");
                    for (InPatient p : patientList) p.showInfo();

                    System.out.println("\n===== DOCTORS =====");
                    for (Surgeon d : doctorList) d.showInfo();
                    System.out.println();
                    break;

                case 4:
                    saveToFile();
                    break;

                case 5:
                    loadFromFile();
                    break;

                case 6:
                    System.out.println("✅ Exiting...");
                    break;

                default:
                    System.out.println("❌ Invalid choice.");
            }

        } while (choice != 6);

        input.close();
    }
}


