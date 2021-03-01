package org.acme.rest.dto;

import java.sql.Date;

public class EmployeeDto {

    private String firstName;
    private String lastName;
    private String email;
    private Date dateOfBirth;
    private String jobTitle;

    public EmployeeDto() {
    }

    public EmployeeDto(String firstName, String lastName, String email, Date dateOfBirth, String jobTitle) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.jobTitle = jobTitle;
    }

    public String getName() {
        return firstName;
    }

    public void setName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

}
