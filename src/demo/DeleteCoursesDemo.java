package demo;

import entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteCoursesDemo {

    public static void main(String[] args) {
        // heavy weight object ONLY CREATE ONCE
        // used in generating sessions
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class) // to let hibernate know about our classes
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Review.class)
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        // the factory will be used to handled a session
        Session session = factory.getCurrentSession();
        try {
            // start transaction
            session.beginTransaction();

            // get the course with a key 10 from database
            int courseId= 10;
            Course course1 = session.get(Course.class, courseId);
            System.out.println("\nLoaded Course: "+course1);
            System.out.println("Deleting Course...");

            // delete the course
            session.delete(course1);

            // commit transaction
            session.getTransaction().commit();
            System.out.println("Done");
        } finally {
            session.close();
            factory.close();
        }

    }

}
