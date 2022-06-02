package entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import javax.persistence.JoinColumn;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "course")
public class Course {
    @Id // marks as the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // this makes the id auto increment every transaction
    @Column(name = "id") // name of the table
    private int id;

    @Column(name = "title")
    private String title;

    // Join column, knows we set up course how to find its instructor
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "instructor_id")
    // NOTE: instructor_id is in the course table with the same name as the column that has the key that points back to the actual instructor
    private Instructor instructor;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id") // tells hibernate who to associate with the given course FOREIGN KEY
    private List<Review> reviews;

    // NEWLY ADDED
    @ManyToMany(fetch=FetchType.LAZY, cascade= {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(
            name="course_student",
            joinColumns=@JoinColumn(name="course_id"),
            inverseJoinColumns=@JoinColumn(name="student_id")
    )
    private List<Student> students;

    // constructor
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

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    // add a convenience method for adding a object to a list
    public void addReview(Review tempReview) {
        if (reviews == null) {
            reviews = new ArrayList<>();
        } // adding the review in to the list
        reviews.add(tempReview);
    }
    // add a convenience method for adding a student to the collection
    public void addStudent(Student tempStudent) {
        if (students == null) {
            students = new ArrayList<>();
        }
        students.add(tempStudent);// adding the review in to the list
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
