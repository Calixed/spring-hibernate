package demo;

import entity.Course;
import entity.Instructor;
import entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class EagerLazyDemo {

    public static void main(String[] args) {
        // heavy weight object ONLY CREATE ONCE
        // used in generating sessions
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class) // to let hibernate know about our classes
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();

        // the factory will be used to handled a session
        Session session = factory.getCurrentSession();
        try {
            // start transaction
            session.beginTransaction();

            // get the instructor from database
            int theId = 1;
            Instructor tempInstructor = session.get(Instructor.class, theId);

            // since TempInstructor is associated with Course via MappedBy, we can just call it in the Object
            System.out.println("Instructors Name: "+ tempInstructor.getFirstName() + " "+ tempInstructor.getLastName());

            // get the courses for the instructor
            System.out.println("\n\nCourses: " + tempInstructor.getCourseList()); // this is our lazy data
            List<Course> tempList = tempInstructor.getCourseList();

            // printing it
            for(Course  x: tempList){
                System.out.println(x.getTitle());
            }

            // commit the transaction
            session.getTransaction().commit();
            session.close();
            // few notes: This will throw an lazy exception error when we call the getCourseList if we didnt called it before the session is closed.
            // SOLUTION: to prevent this we should call the getter method first before closing the
            // Now, we can call the lazy data since it has already been loaded to our application
            System.out.println("\n\nCourses: " + tempInstructor.getCourseList()); // this is our lazy data
            System.out.println("\nDone!");
        } finally {

            factory.close();
        }

    }

}
