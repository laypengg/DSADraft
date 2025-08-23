/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import ADT.ListInterface;
import ADT.QueueInterface;
import ADT.SortedDoublyLinkedList;
import ADT.DoubleEndedPriorityQueue;
import entity.Patient;
import entity.Registration;
import entity.PatientQueue;
import utility.Node;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Iterator;

/**
 *
 * @author Ang Lay Peng
 */
    public class PatientControl {
    private SortedDoublyLinkedList<Patient> patients;
    private SortedDoublyLinkedList<Registration> registrations;
    private DoubleEndedPriorityQueue<PatientQueue> patientQueue;

    public PatientControl() {
        patients = new SortedDoublyLinkedList<>();
        registrations = new  SortedDoublyLinkedList<>();
        patientQueue = new DoubleEndedPriorityQueue<>();
    }

   // ---------------- ValidationResult ----------------
    public static class ValidationResult {
        private boolean isValid;
        private String errorMessage;

        public ValidationResult(boolean isValid, String errorMessage) {
            this.isValid = isValid;
            this.errorMessage = errorMessage;
        }

        public boolean isValid() { return isValid; }
        public String getErrorMessage() { return errorMessage; }
    }

   // ---------------- Validation Methods ----------------
    public ValidationResult validateName(String name) {
        if (name == null || name.trim().isEmpty())
            return new ValidationResult(false, "Name cannot be empty.");
        if (!name.matches("[A-Za-z ]+"))
            return new ValidationResult(false, "Name can only contain letters and spaces.");
        return new ValidationResult(true, "");
    }

    public ValidationResult validateDob(String dob) {
        if (dob == null || dob.trim().isEmpty())
            return new ValidationResult(false, "Date of Birth cannot be empty.");
        try {
            LocalDate date = LocalDate.parse(dob, DateTimeFormatter.ISO_LOCAL_DATE);
            if (date.isAfter(LocalDate.now()))
                return new ValidationResult(false, "Date of Birth cannot be in the future.");
        } catch (DateTimeParseException e) {
            return new ValidationResult(false, "Invalid date format. Use yyyy-MM-dd.");
        }
        return new ValidationResult(true, "");
    }

    public ValidationResult validateGender(String gender) {
        if (gender == null || gender.trim().isEmpty())
            return new ValidationResult(false, "Gender cannot be empty.");
        String g = gender.trim().toUpperCase();
        if (!g.equals("M") && !g.equals("F"))
            return new ValidationResult(false, "Gender must be M or F.");
        return new ValidationResult(true, "");
    }

    public ValidationResult validateEmail(String email) {
        if (email == null || email.trim().isEmpty())
            return new ValidationResult(false, "Email cannot be empty.");
        if (!email.matches("^[\\w.+-]+@[\\w.-]+\\.[A-Za-z]{2,}$"))
            return new ValidationResult(false, "Invalid email format.");
        return new ValidationResult(true, "");
    }

    public ValidationResult validatePhone(String phone) {
        if (phone == null || phone.trim().isEmpty())
            return new ValidationResult(false, "Phone number cannot be empty.");
        if (!phone.matches("\\d{10,11}"))
            return new ValidationResult(false, "Phone number must be 10-11 digits.");
        return new ValidationResult(true, "");
    }

    public ValidationResult validateBloodType(String bloodType) {
        if (bloodType == null || bloodType.trim().isEmpty())
            return new ValidationResult(false, "Blood type cannot be empty.");
        String bt = bloodType.toUpperCase();
        if (!bt.equals("A+") && !bt.equals("A-") && !bt.equals("B+") && !bt.equals("B-") &&
            !bt.equals("AB+") && !bt.equals("AB-") && !bt.equals("O+") && !bt.equals("O-"))
            return new ValidationResult(false, "Invalid blood type. Must be A+, A-, B+, B-, AB+, AB-, O+, or O-.");
        return new ValidationResult(true, "");
    }

    public ValidationResult validateAddress(String address) {
        if (address == null || address.trim().isEmpty())
            return new ValidationResult(false, "Address cannot be empty.");
        return new ValidationResult(true, "");
    }

    public ValidationResult validateAllergies(String allergies) {
        if (allergies == null)
            return new ValidationResult(false, "Allergies cannot be null (use 'None' if none).");
        return new ValidationResult(true, "");
    }
    
    // ---------------- Department Validation ----------------
public ValidationResult validateDepartment(String department) {
    if (department == null || department.trim().isEmpty())
        return new ValidationResult(false, "Department cannot be empty.");
    return new ValidationResult(true, "");
}

    // ---------------- Patient Operations ----------------

private int nextPatientNumber = 1;
private String generateNextPatientID() {
    return String.format("P%04d", nextPatientNumber++);
}

public boolean addPatient(Patient p) {
    if (p == null) return false;

    // Helper: array of validation checks
    ValidationResult[] results = new ValidationResult[] {
        validateName(p.getName()),
        validateDob(p.getDob()),
        validateGender(p.getGender()),
        validateEmail(p.getEmail()),
        validatePhone(p.getPhoneNo()),
        validatePhone(p.getEmergencyContactNo()),
        validateAddress(p.getAddress()),
        validateBloodType(p.getBloodType()),
        validateAllergies(p.getAllergies())
    };

    for (ValidationResult r : results) {
        if (!r.isValid()) {
            System.out.println(r.getErrorMessage());
            return false;
        }
    }

    if (searchPatientByID(p.getPatientID()) != null) {
        System.out.println("Patient ID already exists.");
        return false;
    }
    
    p.setPatientID(generateNextPatientID());

    patients.insert(p);
    System.out.println("Patient " + p.getPatientID() + " successfully added!");
    return true;
}


   public Patient searchPatientByID(String patientID) {
    if (patientID == null) return null;

    Iterator<Patient> iter = patients.getIterator();  
    while (iter.hasNext()) {
        Patient p = iter.next();
        if (p.getPatientID().equalsIgnoreCase(patientID)) {
            return p;
        }
    }
    return null;
}

    public boolean removePatient(Patient p) {
        if (p == null) return false;
        return patients.remove(p);
    }

    public boolean update(Patient oldPatient, Patient newPatientData) {
    var node = patients.findNode(oldPatient);
    if (node == null) return false;

    Patient p = node.data;

    // Create an array of validation checks
    ValidationResult[] results = new ValidationResult[] {
        validateName(newPatientData.getName()),
        validateDob(newPatientData.getDob()),
        validateGender(newPatientData.getGender()),
        validateEmail(newPatientData.getEmail()),
        validatePhone(newPatientData.getPhoneNo()),
        validatePhone(newPatientData.getEmergencyContactNo()),
        validateAddress(newPatientData.getAddress()),
        validateBloodType(newPatientData.getBloodType()),
        validateAllergies(newPatientData.getAllergies())
    };

    // Check all validations first
    for (ValidationResult r : results) {
        if (!r.isValid()) {
            System.out.println(r.getErrorMessage());
            return false;
        }
    }

    // Update fields after validation passes
    p.setName(newPatientData.getName());
    p.setDob(newPatientData.getDob());
    p.setGender(newPatientData.getGender());
    p.setEmail(newPatientData.getEmail());
    p.setPhoneNo(newPatientData.getPhoneNo());
    p.setEmergencyContactNo(newPatientData.getEmergencyContactNo());
    p.setAddress(newPatientData.getAddress());
    p.setBloodType(newPatientData.getBloodType());
    p.setAllergies(newPatientData.getAllergies());

    return true;
}

    public int totalPatients() {
        return patients.size();
    }

    public void clearAll() {
        patients.clear();
    }

   public void displayAllPatients() {
    if (patients.isEmpty()) {
        System.out.println("No patients found.");
        return;
    }

    System.out.println("=== All Patients ===");
    Iterator<Patient> iter = patients.getIterator();
    while (iter.hasNext()) {
        Patient p = iter.next();
        System.out.println(p); // calls Patient.toString() automatically
        System.out.println("------------------------------");
    }
}

    private boolean containsPatient(Patient p) {
    return searchPatientByID(p.getPatientID()) != null;
}

    // ---------------- Patient Registration Operations ----------------
    public boolean registerPatient(Patient p, String department, int priorityLevel) {
        if (!containsPatient(p)) {
            System.out.println("Patient not found.");
            return false;
        }
        
       ValidationResult deptResult = validateDepartment(department);
        if (!deptResult.isValid()) {
        System.out.println(deptResult.getErrorMessage());
        return false;
        }
        
        if (priorityLevel < 1 || priorityLevel > 3) {
            System.out.println("Invalid priority level (1-3).");
            return false;
        }

        Registration reg = new Registration(p, department);
        registrations.insert(reg);

        PatientQueue pq = new PatientQueue(reg, priorityLevel);
        patientQueue.insert(pq);

        return true;
    }

    public boolean removeRegistration(Registration reg) {
        return registrations.remove(reg);
    }

    public void displayRegistrations() {
        Iterator<Registration> iter = registrations.getIterator();
        while (iter.hasNext()) {
            System.out.println(iter.next());
            System.out.println("---------------------");
        }
    }

    // ---------------- Patient Queue Operations ----------------
    public boolean addToQueue(Registration registration, int priorityLevel) {
        if (registration == null || priorityLevel < 1 || priorityLevel > 3) return false;
        PatientQueue pq = new PatientQueue(registration, priorityLevel);
        return patientQueue.insert(pq);
    }

    public PatientQueue peekNextPatient() {
        return patientQueue.peekMin();
    }

    public PatientQueue peekLastPatient() {
        return patientQueue.peekMax();
    }

    public PatientQueue removeNextPatient() {
        return patientQueue.removeMin();
    }

    public PatientQueue removeLastPatient() {
        return patientQueue.removeMax();
    }

    public int totalQueuePatients() {
        return patientQueue.size();
    }

    public boolean isQueueEmpty() {
        return patientQueue.isEmpty();
    }

    public void clearQueue() {
        patientQueue.clear();
    }

    public void displayQueue() {
        if (isQueueEmpty()) {
            System.out.println("No patients in queue.");
            return;
        }
        Iterator<PatientQueue> iter = patientQueue.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next());
            System.out.println("------------------------");
        }
    }
}
