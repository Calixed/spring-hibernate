package demo;

import entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class GetCoursesForStudentDemo {

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

            // get the student named "John" from the database
            int studentId= 2;
            Student student1 = session.get(Student.class, studentId);
            System.out.println("\nLoaded student: "+student1);
            System.out.println("Courses :"+student1.getCourses());



            // commit transaction
            session.getTransaction().commit();
            System.out.println("Done");
        } finally {
            session.close();
            factory.close();
        }

    }

}
