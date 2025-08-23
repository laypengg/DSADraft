/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
/**
 *
 * @author Ang Lay Peng
 * 
 */
public class Registration implements Comparable<Registration> {
    private static int regCounter = 0;
    private String registrationID;
    private Patient patient;
    private LocalDateTime dateTime;
    private String department;

    public Registration(Patient patient, String department) {
        this.registrationID = String.format("REG%04d", ++regCounter);
        this.patient = patient;
        this.dateTime = LocalDateTime.now();
        this.department = department;
    }

    // Getters
    public String getRegistrationID() {
        return registrationID; 
    }
    
    public Patient getPatient() {
        return patient; 
    }
    
    public LocalDateTime getDateTime() {
        return dateTime; 
    }
    
    public String getDepartment() { 
        return department;
    }
    
        @Override
    public int compareTo(Registration other) {
        return this.dateTime.compareTo(other.dateTime);
    }

    @Override
    public String toString() {
        DateTimeFormatter dtFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm a");
        return "Registration ID: " + registrationID +
               "\nPatient: " + patient.getPatientID() +  
               "\nDate & Time: " + dateTime.format(dtFormat) +
               "\nDepartment: " + department;
    }
}

