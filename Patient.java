/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Ang Lay Peng
 */
public class Patient implements Comparable<Patient> {

    private static int patientCounter = 0;
    private int patientID;
    private String name;
    private String dob;
    private String gender;
    private String email;
    private String phoneNo;
    private String emergencyContactNo;
    private String address;
    private String bloodType;
    private String allergies;

    // Constructor
    public Patient() {
    this.patientID = ++patientCounter;
    }

    public Patient(String name, String dob, String gender, String email, String phoneNo,
                   String emergencyContactNo, String address, String bloodType, String allergies) {
        this.patientID = ++patientCounter;
        this.name = name;
        this.dob = dob;
        this.gender = gender;
        this.email = email;
        this.phoneNo = phoneNo;
        this.emergencyContactNo = emergencyContactNo;
        this.address = address;
        this.bloodType = bloodType;
        this.allergies = allergies;
    }

    // Getters
    public String getPatientID() {
        return String.format("P%04d",patientID);
    }

    public String getName() {
        return name;
    }

    public String getDob() {
        return dob;
    }

    public String getGender() {
        return gender;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public String getEmergencyContactNo() {
        return emergencyContactNo;
    }

    public String getAddress() {
        return address;
    }

    public String getBloodType() {
        return bloodType;
    }

    public String getAllergies() {
        return allergies;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public void setEmergencyContactNo(String emergencyContactNo) {
        this.emergencyContactNo = emergencyContactNo;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }
    
    @Override
    public int compareTo(Patient other) {
        return Integer.compare(this.patientID, other.patientID);
}

    @Override
    public String toString() {
        return "Patient ID: " + getPatientID() +
               "\nName: " + name +
               "\nDOB: " + dob +
               "\nGender: " + gender +
               "\nEmail: " + email +
               "\nPhone No: " + phoneNo +
               "\nEmergency Contact No: " + emergencyContactNo +
               "\nAddress: " + address +
               "\nBlood Type: " + bloodType +
               "\nAllergies: " + allergies;
    }
}
