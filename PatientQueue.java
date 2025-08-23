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
 */
public class PatientQueue implements Comparable<PatientQueue> {
    private static int queueCounter = 1000; // First become 1001
    private int queueID;
    private Registration registration;
    private LocalDateTime arrivalDateTime;
    private int priorityLevel; // 1=High, 2=Medium, 3=Low

    public PatientQueue(Registration registration, int priorityLevel) {
        this.queueID = ++queueCounter;
        this.registration = registration;
        this.arrivalDateTime = registration.getDateTime();
        this.priorityLevel = priorityLevel;
    }

    // Getters
    public int getQueueID() {
        return queueID; 
    }
    
    public Registration getRegistration() {
        return registration;
    }
    
    public LocalDateTime getArrivalDateTime() {
        return arrivalDateTime; 
    }
    
    public int getPriorityLevel() {
        return priorityLevel; 
    }

    public String getPriorityDescription() {
        switch(priorityLevel) {
            case 1: return "High Priority";
            case 2: return "Medium Priority";
            case 3: return "Low Priority";
        }
        return "Invalid Priority! Please Try Again.";
    }

    @Override
    public int compareTo(PatientQueue another) {
        int result = Integer.compare(this.priorityLevel, another.priorityLevel);
        if (result == 0) {
            return this.arrivalDateTime.compareTo(another.arrivalDateTime);
        }
        return result;
    }

    @Override
    public String toString() {
        DateTimeFormatter dtFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm a");
        return "Queue ID: " + queueID +
               "\nRegistration ID: " + registration.getRegistrationID() +
               "\nPriority: " + getPriorityDescription() +
               "\nArrival: " + arrivalDateTime.format(dtFormat) +
               "\nDepartment: " + registration.getDepartment();
    }
}
