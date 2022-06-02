package demo.entity;

import javax.persistence.*;

@Entity // noted that this is an entity in our database
@Table(name = "instructor") // name of the table
public class Instructor {
    @Id // set as primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // set as auto-increment
    @Column(name = "id")
    private int id;

    @Column(name = "first_name") // name of our column
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    // Connecting the two tables/class together
    // set mapping between instructor and instructorDetail entity
    // NOTE: This is a column in the table in the database (set as schema) during running the script
    @OneToOne(cascade = CascadeType.ALL)// important!!
    @JoinColumn(name = "instructor_detail_id") // this is our foreign key
    private InstructorDetail instructorDetail; // object

    // no argument constructor
    public Instructor() {
        System.out.println("Default constructor of Instructor is called");
    }

    public Instructor(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        // we can automatically pass the instructor detail object
        System.out.println("Object Instructor has been made!");
    }

    // setters and getters methods
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public InstructorDetail getInstructorDetail() {
        return instructorDetail;
    }

    public void setInstructorDetail(InstructorDetail instructorDetail) {
        this.instructorDetail = instructorDetail;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    // for debugging purposes
    @Override
    public String toString() {
        return "Instructor [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
                + ", instructorDetail=" + instructorDetail + "]";
    }


}