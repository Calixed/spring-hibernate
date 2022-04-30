package entity;

import javax.persistence.*;


@Entity // noted that this is an entity in our database
@Table(name = "instructor_detail") // name of the table
public class InstructorDetail {

    @Id // primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // set as auto-increment
    @Column(name = "id")
    private int id;

    @Column(name = "youtube_channel") // name of the column in database
    private String youtubeChannel;

    @Column(name = "hobby")
    private String hobby;

    public InstructorDetail() {

    }

    // constructors with argument
    public InstructorDetail(String youtubeChannel, String hobby) {
        this.youtubeChannel = youtubeChannel;
        this.hobby = hobby;
    }


    // setter and getter methods
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
