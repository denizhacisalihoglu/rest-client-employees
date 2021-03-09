package org.acme.rest.entity;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name= "employees", schema = "public")
public class Employee extends PanacheEntityBase {

    @Id
    @GeneratedValue(generator = "id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name="id_seq", sequenceName = "public.employees_Id_seq", allocationSize = 1, schema = "public")
    @Column(name="id", updatable = false, nullable = false)
    public Long id;

    @Column(name = "firstname")
    public String firstName;

    @Column(name = "lastname")
    public String lastName;
    
    @Column(name = "email")
    public String email;
    
    @Column(name = "dateofbirth")
    @JsonFormat(pattern = "dd/MM/yyyy")
    public LocalDate dateOfBirth;

    @Column(name = "jobtitle")
    public String jobTitle;

    @Column(name = "status")
    public Boolean status;

    @Column(name = "department")
    public String department;

    public Employee() {
    }

    public Employee(Long id, String firstName, String lastName, String email, LocalDate dateOfBirth, Boolean status, String department) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.status = status;
        this.department = department;
    }
}
