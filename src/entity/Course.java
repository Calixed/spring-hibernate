package entity;

import javax.persistence.*;
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
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
    @JoinColumn(name = "instructor_id")
    // NOTE: instructor_id is in the course table with the same name as the column that has the key that points back to the actual instructor
    private Instructor instructor;


    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id") // tells hibernate who to associate with the given course FOREIGN KEY
    private List<Review> reviews;

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


    // setter and getter for Instructor class
    public Instructor getInstructor() {
        return instructor;
    }

    // sets as the new instructor everytime a new courses is added to an Instructor
    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    // for course
    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    // add a convenience method
    public void addReview(Review tempReview){
        if(reviews == null){
            reviews = new ArrayList<>();
        }
        // adding the review in to the list
        reviews.add(tempReview);
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
