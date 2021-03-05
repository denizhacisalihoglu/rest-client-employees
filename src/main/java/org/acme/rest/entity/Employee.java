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
    private Long id;

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

    public Employee() {
    }

    public Employee(String firstName, String lastName, String email, LocalDate dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
    }
}
