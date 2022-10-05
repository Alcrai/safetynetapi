package com.safetynetapi.model;

import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MedicalRecord {
    private String firstName;
    private String lastName;
    private String birthdate;
    private String medications;
    private String allergies;
    private int age;

    public MedicalRecord(String firstName, String lastName, String birthdate, String medications, String allergies) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.medications = medications;
        this.allergies = allergies;
        this.age=calculAge();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getMedications() {
        return medications;
    }

    public void setMedications(String medications) {
        this.medications = medications;
    }

    public String getAllergies() {
        return allergies;
    }

    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }

    public int getAge() {
        return age;
    }

    private int calculAge(){
        String birthday = this.birthdate;
        String[] arrBirthday = birthday.split("/");
        int birthdayInt = Integer.parseInt(arrBirthday[2]+arrBirthday[0]+arrBirthday[1]);
        SimpleDateFormat dtf = new SimpleDateFormat("yyyymmdd");
        Calendar calendar = Calendar.getInstance();
        Date dateNow = calendar.getTime();
        String formattedDate= dtf.format(dateNow);
        int todayInt = Integer.parseInt(formattedDate);
        return (todayInt-birthdayInt)/10000 ;
    }

    @Override
    public String toString() {
        return "MedicalRecord{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthdate='" + birthdate + '\'' +
                ", Age='" + age + '\'' +
                ", medications='" + medications + '\'' +
                ", allergies='" + allergies + '\'' +
                '}';
    }
}
