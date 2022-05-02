package demo;

import entity.Course;
import entity.Instructor;
import entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateInstructorDemo {

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
            // create the objects of two class
            Instructor tempInstructor0 = new Instructor("Joanne", "Dote", "Doe@gmail.com");
            InstructorDetail tempInstructorDetail0 = new InstructorDetail("113test.com", "coding");

            // associate the object : reference this object to another object IN MEMORY
            tempInstructor0.setInstructorDetail(tempInstructorDetail0);

            // start transaction
            session.beginTransaction();

            // saving the Instructor Object
            session.save(tempInstructor0);

            // commit the transaction
            session.getTransaction().commit();
            System.out.println();

            System.out.println("Done!");

        } finally {
            session.close();
            factory.close();
        }

    }

}
