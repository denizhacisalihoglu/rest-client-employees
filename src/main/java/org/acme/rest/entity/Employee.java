package org.acme.rest.entity;


import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;

import java.sql.Date;

@Entity
@Table(name="public.\"Employees\"")
public class Employee extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "SEQ_PROD")
    @SequenceGenerator(name="SEQ_PROD", sequenceName = "SEQ_PROD", allocationSize = 1)
    
    @Column(name = "id")
    private Long id;

    @Column(name = "firstname")
    public String firstName;

    @Column(name = "lastname")
    public String lastName;
    
    @Column(name = "email")
    public String email;
    
    @Column(name = "dateofbirth")
    public Date dateOfBirth;

    @Column(name = "jobtitle")
    public String jobTitle;

    public Employee() {
    }

    public Employee(String firstName, String lastName, String email, Date dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
    }
}
