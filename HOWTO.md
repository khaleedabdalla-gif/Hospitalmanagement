The Big Idea
my project is built around the concept of people in a hospital, and how they share some similarities (like name + age) but also have different roles (employee, doctor, patient, etc.).
To model this, you use Object-Oriented Programming (OOP):

A general template for all humans
More specific versions for employees, doctors, patients
A main program that uses these classes

This keeps your code clean, organized, and easy to expand.

⭐ 2. The Human Blueprint (Abstract Class)
At the top level, there is an abstract concept called Human.

It represents any person in the hospital.
It contains basic information like full name and age.
Since every human should be able to “show information,” the class requires all subclasses to provide their own way of displaying this info.

This is where abstraction and inheritance start working together.
Think of Human as a blueprint that cannot be used directly, but other classes build upon it.

⭐ 3. Making It Specific (Subclasses)
Since you can’t create a “Human” directly, you create specialized types of humans.
Examples:
Employee
Represents someone who works at the hospital.
An employee still has a name and age (inherited from Human) but also has extra details like role or department.
Doctor
A more specialized employee who might have:

a medical specialty
years of experience
certifications

Patient
Represents someone receiving care.
They may have:

a medical condition
a patient ID
a treatment plan

Each subclass inherits the basic information and behavior from Human, and then adds its own unique features.

⭐ 4. Polymorphism (Same Action, Different Behavior)
All human types share the requirement to display information, but each one displays it differently.
This is called method overriding — different objects respond differently to the same action.
It allows the main program to do something like:

“Show info for every person here,”
without needing to know whether it’s a doctor, employee, or patient.

The system handles each type according to its own rules.

⭐ 5. The Main Program (Where Everything Runs)
This is where the action starts.
The main class:

Creates objects like employees or doctors
Stores them
Calls methods like “show this person’s information”
Controls the flow of the program

It is essentially the entry point — the place where the system is executed.
Even though Human is abstract and can’t be created directly, you can still reference humans in a general sense. This lets the main program work with many different types of people at once.

⭐ 6. Why This Structure Is Good
Your project teaches and demonstrates several important concepts:
✔ Encapsulation
You hide data inside classes and control access through methods.
✔ Inheritance
Common behavior (name, age) is shared across subclasses.
✔ Abstraction
Human defines what all humans must have, without implementing it.
✔ Polymorphism
Different subclasses behave differently while keeping the same interface.
✔ Clean and Scalable
It’s easy to add new types of humans later (e.g., Nurse, Receptionist).

⭐ 7. How Everything Works Together

Human (abstract)
↓ Gives structure and rules
Employee / Doctor / Patient
↓ Add more detail and behavior
Main Program
↓ Creates objects using these classes
↓ Calls their methods
↓ Produces output

This creates a clean, intuitive flow where each piece has one clear role.

⭐ 8. Future Extensions (If You Want)
Your design allows easy expansion. You can later add:

A menu system (interactive text UI)
Appointments between doctors and patients
File storage (saving records)
Medical prescriptions
Billing system
Departments / staff hierarchy

All this is possible because the foundation is well‑structured.

⭐ 9. Summary (Short Version)

Human = abstract template for anyone in the hospital
Employee / Doctor / Patient = specific versions that inherit the basics
Main Program = creates people and uses them
OOP principles keep everything organized and expandable
The system is ready to grow into a full hospital simulation

### ✔ InputMismatchException  
Occurs when the user enters an invalid type (e.g., text instead of a number):

```java
catch (InputMismatchException e) {
    System.out.println("Error: Invalid input type!");
}
## 🔗 GitHub Repository Link
https://github.com/khaleedabdalla-gif/Hospitalmanagement2
