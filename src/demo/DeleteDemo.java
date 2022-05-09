package demo;

import entity.Instructor;
import entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteDemo {

    public static void main(String[] args) {
        // heavy weight object ONLY CREATE ONCE
        // used in generating sessions
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class) // to let hibernate know about our classes
                .addAnnotatedClass(InstructorDetail.class)

                .buildSessionFactory();

        // the factory will be used to handled a session
        Session session = factory.getCurrentSession();
        try {
            // start transaction
            session.beginTransaction();

            // get instructor by primary key /
            int theId = 1;

            // get the session with the primary key, it will retrieve object in the database
            Instructor tempInstructor = session.get(Instructor.class, theId);
            System.out.println("Found the instructor: " + tempInstructor);

            // delete the instructor object
            if (tempInstructor != null) {
                System.out.println("Deleting: " + tempInstructor);
                // Note: this will also delete the instructor detail due of CascadeType.ALL meaning:
                // anything associating with tempInstructor will get delete
                session.delete(tempInstructor);
            }

            // commit the transaction
            session.getTransaction().commit();
            System.out.println("Done!");

        } finally {
            factory.close();
        }

    }

}
