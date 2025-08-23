/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import ADT.SortedDoublyLinkedList;
import  ADT.DoubleEndedPriorityQueue;
import entity.Patient;
import entity.Registration;
import entity.PatientQueue;

/**
 *
 * @author Ang Lay Peng
 */
public class PatientDAO {
    
    private SortedDoublyLinkedList<Patient> patientList;
    private SortedDoublyLinkedList<Registration> registrationList;
    private DoubleEndedPriorityQueue<PatientQueue> patientQueue;

    public PatientDAO() {
        patientList = new SortedDoublyLinkedList<>();
        registrationList = new SortedDoublyLinkedList<>();
        patientQueue = new DoubleEndedPriorityQueue<>();
        
         // --- Sample Patients ---
        Patient p1 = new Patient("John Tan", "1990-05-12", "M",
                "john.tan@email.com", "0123456789", "0112233445",
                "10 Jalan SS2, Petaling Jaya", "O+", "Peanuts");
        Patient p2 = new Patient("Emily Wong", "1985-11-22", "F",
                "emily.wong@email.com", "0139876543", "0119988776",
                "25 Jalan Kuchai Lama, Kuala Lumpur", "A-", "None");
        Patient p3 = new Patient("Ali Ahmad", "2000-01-01", "M",
                "ali.ahmad@email.com", "0162233445", "0102233445",
                "99 Jalan Tun Razak, Kuala Lumpur", "B+", "Seafood");

        patientList.insert(p1);
        patientList.insert(p2);
        patientList.insert(p3);

        // --- Sample Registrations ---
        Registration r1 = new Registration(p1, "Dr. Lee");
        Registration r2 = new Registration(p2, "Dr. Tan");

        registrationList.insert(r1);
        registrationList.insert(r2);

        // --- Sample Patient Queue ---
        PatientQueue pq1 = new PatientQueue(r1, 1);
        PatientQueue pq2 = new PatientQueue(r2, 2);

        patientQueue.insert(pq1);
        patientQueue.insert(pq2);
    }

    // --- Getters to retrieve sample data ---
    public SortedDoublyLinkedList<Patient> getPatientList() {
        return patientList;
    }

    public SortedDoublyLinkedList<Registration> getRegistrationList() {
        return registrationList;
    }

    public DoubleEndedPriorityQueue<PatientQueue> getPatientQueue() {
        return patientQueue;
    }
}
