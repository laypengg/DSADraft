/*
 * Patient Management UI
 * Menu-driven interface to manage patients, registrations, and patient queues
 * Now uses Patient ID for all searches
 * @author fan lin
 */
package boundary;

import control.PatientControl;
import entity.Patient;
import entity.Registration;
import entity.PatientQueue;

import java.util.Scanner;

public class PatientManagementUI {

    private final Scanner scanner;
    private final PatientControl patientControl;

    public PatientManagementUI() {
        scanner = new Scanner(System.in);
        patientControl = new PatientControl();
    }

    public void run() {
        displayWelcome();
        mainMenu();
    }

    private void displayWelcome() {
        System.out.println("===============================================");
        System.out.println("       PATIENT MANAGEMENT SYSTEM              ");
        System.out.println("===============================================");
    }

    private void mainMenu() {
        while (true) {
            System.out.println("\n=== MAIN MENU ===");
            System.out.println("1. Patient Management");
            System.out.println("2. Registration Management");
            System.out.println("3. Patient Queue Management");
            System.out.println("4. Exit");

            String choice = getInput("Enter choice (1-5): ");

            switch (choice) {
                case "1":
                    patientMenu();
                    break;
                case "2":
                    registrationMenu();
                    break;
                case "3":
                    patientQueueMenu();
                    break;
                case "4":
                    System.out.println("Exiting Patient Management System.");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // ================= PATIENT MENU =================

   private void patientMenu() {
    while (true) {
        System.out.println("\n=== PATIENT MANAGEMENT ===");
        System.out.println("1. Add New Patient");
        System.out.println("2. Edit Patient");
        System.out.println("3. Remove Patient");
        System.out.println("4. View All Patients");  // moved here
        System.out.println("5. Back to Main Menu");

        String choice = getInput("Enter choice (1-5): ");

        switch (choice) {
            case "1":
                addPatient();
                break;
            case "2":
                editPatient();
                break;
            case "3":
                removePatient();
                break;
            case "4":
                displayAllPatients();  
                break;
            case "5":
                return;
            default:
                System.out.println("Invalid choice.");
        }
    }
}

    private void addPatient() {
        System.out.println("\n=== ADD NEW PATIENT ===");
        try {
            String name = getInput("Name: ");
            String dob = getInput("Date of Birth (yyyy-MM-dd): ");
            String gender = getInput("Gender (M/F): ");
            String email = getInput("Email: ");
            String phone1 = getInput("Contact Number: ");
            String phone2 = getInput("Emergency Contact Number: ");
            String address = getInput("Address: ");
            String bloodType = getInput("Blood Type: ");
            String allergies = getInput("Allergies (or 'None'): ");

            Patient newPatient = new Patient(name, dob, gender, email, phone1, phone2, address, bloodType, allergies);

            if (patientControl.addPatient(newPatient)) {
            System.out.println("Patient added successfully!");
            } else {
                System.out.println("Failed to add patient (may already exist or invalid input).");
            }

        } catch (Exception e) {
            System.out.println("Error adding patient: " + e.getMessage());
        }
    }

    private void editPatient() {
        System.out.println("\n=== EDIT PATIENT ===");
        String patientID = getInput("Enter Patient ID to edit: ");

        Patient oldPatient = patientControl.searchPatientByID(patientID);
        if (oldPatient == null) {
            System.out.println("Patient not found.");
            return;
        }

        System.out.println("Editing Patient: " + oldPatient);

        String newEmail = getInput("New Email (leave blank to skip): ");
        String newPhone1 = getInput("New Phone 1 (leave blank to skip): ");
        String newPhone2 = getInput("New Phone 2 (leave blank to skip): ");
        String newAddress = getInput("New Address (leave blank to skip): ");
        String newAllergies = getInput("New Allergies (leave blank to skip): ");

        Patient updatedPatient = new Patient(
                oldPatient.getName(),
                oldPatient.getDob(),
                oldPatient.getGender(),
                newEmail.isEmpty() ? oldPatient.getEmail() : newEmail,
                newPhone1.isEmpty() ? oldPatient.getPhoneNo() : newPhone1,
                newPhone2.isEmpty() ? oldPatient.getEmergencyContactNo() : newPhone2,
                newAddress.isEmpty() ? oldPatient.getAddress() : newAddress,
                oldPatient.getBloodType(),
                newAllergies.isEmpty() ? oldPatient.getAllergies() : newAllergies
        );

        if (patientControl.update(oldPatient, updatedPatient)) {
            System.out.println("Patient updated successfully!");
        } else {
            System.out.println("Failed to update patient.");
        }
    }

    private void removePatient() {
        System.out.println("\n=== REMOVE PATIENT ===");
        String patientID = getInput("Enter Patient ID to remove: ");

        Patient patient = patientControl.searchPatientByID(patientID);
        if (patient == null) {
            System.out.println("Patient not found.");
            return;
        }

        if (patientControl.removePatient(patient)) {
            System.out.println("Patient removed successfully.");
        } else {
            System.out.println("Failed to remove patient.");
        }
    }

    private void displayAllPatients() {
        System.out.println("\n=== ALL PATIENTS ===");
        patientControl.displayAllPatients();
    }

    // ================= REGISTRATION MENU =================

    private void registrationMenu() {
        while (true) {
            System.out.println("\n=== REGISTRATION MANAGEMENT ===");
            System.out.println("1. Add Registration");
            System.out.println("2. View Registrations");
            System.out.println("3. Back to Main Menu");

            String choice = getInput("Enter choice (1-3): ");

            switch (choice) {
                case "1":
                    addRegistration();
                    break;
                case "2":
                    displayRegistrations();
                    break;
                case "3":
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private void addRegistration() {
        System.out.println("\n=== ADD REGISTRATION ===");
        String patientID = getInput("Enter Patient ID: ");
        Patient patient = patientControl.searchPatientByID(patientID);

        if (patient == null) {
            System.out.println("Patient not found. Please add patient first.");
            return;
        }

        String department = getInput("Department: ");
        int priority = Integer.parseInt(getInput("Priority (1-3): "));

        if (patientControl.registerPatient(patient, department, priority)) {
            System.out.println("Registration added successfully!");
        } else {
            System.out.println("Failed to add registration.");
        }
    }

    private void displayRegistrations() {
        System.out.println("\n=== ALL REGISTRATIONS ===");
        patientControl.displayRegistrations();
    }

    // ================= PATIENT QUEUE MENU =================

    private void patientQueueMenu() {
        while (true) {
            System.out.println("\n=== PATIENT QUEUE MANAGEMENT ===");
            System.out.println("1. Add to Queue");
            System.out.println("2. Remove Min Priority");
            System.out.println("3. Remove Max Priority");
            System.out.println("4. Peek Min Priority");
            System.out.println("5. Peek Max Priority");
            System.out.println("6. Clear Queue");
            System.out.println("7. Back to Main Menu");

            String choice = getInput("Enter choice (1-7): ");

            switch (choice) {
                case "1":
                    addToQueue();
                    break;
                case "2":
                    removeMinQueue();
                    break;
                case "3":
                    removeMaxQueue();
                    break;
                case "4":
                    peekMinQueue();
                    break;
                case "5":
                    peekMaxQueue();
                    break;
                case "6":
                    patientControl.clearQueue();
                    System.out.println("Queue cleared.");
                    break;
                case "7":
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private void addToQueue() {
        System.out.println("\n=== ADD TO PATIENT QUEUE ===");
        String patientID = getInput("Enter Patient ID: ");
        Patient patient = patientControl.searchPatientByID(patientID);

        if (patient == null) {
            System.out.println("Patient not found.");
            return;
        }

        String department = getInput("Department for Registration: ");
        Registration registration = new Registration(patient, department);

        int priority = Integer.parseInt(getInput("Priority (1-3, 1=Emergency): "));

        if (patientControl.addToQueue(registration, priority)) {
            System.out.println("Patient added to queue successfully!");
        } else {
            System.out.println("Failed to add patient to queue.");
        }
    }

    private void removeMinQueue() {
        PatientQueue pq = patientControl.removeNextPatient();
        if (pq != null) {
            System.out.println("Removed from queue (min priority): " + pq);
        } else {
            System.out.println("Queue is empty.");
        }
    }

    private void removeMaxQueue() {
        PatientQueue pq = patientControl.removeLastPatient();
        if (pq != null) {
            System.out.println("Removed from queue (max priority): " + pq);
        } else {
            System.out.println("Queue is empty.");
        }
    }

    private void peekMinQueue() {
        PatientQueue pq = patientControl.peekNextPatient();
        if (pq != null) {
            System.out.println("Peek (min priority): " + pq);
        } else {
            System.out.println("Queue is empty.");
        }
    }

    private void peekMaxQueue() {
        PatientQueue pq = patientControl.peekLastPatient();
        if (pq != null) {
            System.out.println("Peek (max priority): " + pq);
        } else {
            System.out.println("Queue is empty.");
        }
    }

    // ================= UTILITY METHODS =================

    private String getInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    // ================= MAIN METHOD =================

    public static void main(String[] args) {
        PatientManagementUI ui = new PatientManagementUI();
        ui.run();
    }
}
