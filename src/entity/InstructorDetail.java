package entity;

import javax.persistence.*;


@Entity // noted that this is an entity in our database
@Table(name = "instructor_detail") // name of the table
public class InstructorDetail {

    @Id // primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // set as auto-increment
    @Column(name = "id") // name of the column in the database
    private int id;

    @Column(name = "youtube_channel") // name of the column in database
    private String youtubeChannel;

    @Column(name = "hobby")
    private String hobby;


    // add new field for instructor
    // BI-DIRECTIONAL NOTES:
    // Note: this is referenced in the instructor class that we have instructorDetail object to call this class
    // we're just gonna do the same thing so we can call instructorDetail then instructor or Instructor first then instructorDetail
    // this will be temporary, to demo the deleting instructorDetail object will only delete itself and not associating entity
    //
    @OneToOne(mappedBy = "instructorDetail",
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private Instructor instructor;

    // no args constructor
    public InstructorDetail() {
    }

    // constructors with argument
    public InstructorDetail(String youtubeChannel, String hobby) {
        this.youtubeChannel = youtubeChannel;
        this.hobby = hobby;
        // we can automatically pass the instructor detail object
        System.out.println("Object Instructor Detail has been made!");
    }


    // setter and getter methods
    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getYoutubeChannel() {
        return youtubeChannel;
    }

    public void setYoutubeChannel(String youtubeChannel) {
        this.youtubeChannel = youtubeChannel;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    @Override
    public String toString() {
        return "InstructorDetail [youtubeChannel=" + youtubeChannel + ", hobby=" + hobby + "]";
    }


}
