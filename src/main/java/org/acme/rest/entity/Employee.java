package org.acme.rest.entity;


import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;

import java.sql.Date;

@Entity
@Table(name= "employees", schema = "public")
public class Employee extends PanacheEntityBase {

    @Id
    @GeneratedValue(generator = "id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name="id_seq", sequenceName = "public.employees_Id_seq", allocationSize = 1, schema = "public")
    @Column(name="id", updatable = false, nullable = false)
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
