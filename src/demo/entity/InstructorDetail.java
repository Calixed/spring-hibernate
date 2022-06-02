package demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity // noted that this is an entity in our database
@Table(name="instructor_detail") // name of the table
public class InstructorDetail {
	
	@Id // primary key
	@GeneratedValue(strategy=GenerationType.IDENTITY) // set as auto-increment
	@Column(name="id") // name of the column in the database
	private int id;
	
	@Column(name="youtube_channel") // name of the column in database
	private String youtubeChannel;
	
	@Column(name="hobby")
	private String hobby;

	// no args constructor
	public InstructorDetail() {}
	
	// constructors with argument
	public InstructorDetail(String youtubeChannel, String hobby) {
		this.youtubeChannel = youtubeChannel;
		this.hobby = hobby;
		// we can automatically pass the instructor detail object
		System.out.println("Object Instructor Detail has been made!");
	}

	
	// setter and getter methods
	public int getId() {return id;}

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
