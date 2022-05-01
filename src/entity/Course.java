package entity;

import javax.persistence.*;

@Entity
@Table(name = "course")
public class Course {

    // annotate field
    @Id // marks as the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // this makes the id auto increment every transaction
    @Column(name = "id") // name of the table
    private int id;

    @Column(name = "title")
    private String title;

    // Join column, knows we set up course how to find its instructor
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.REMOVE})
    @JoinColumn(name = "instructor_id")
    // NOTE: instructor_id is in the course table with the same name as the column that has the key that points back to the actual instructor
    private Instructor instructor;

    public Course(String title) {
        this.title = title;
    }

    // default no args
    public Course() {
    }

    // setter and getter methods
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    // debugging the model
    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", instructor=" + instructor +
                '}';
    }
}
