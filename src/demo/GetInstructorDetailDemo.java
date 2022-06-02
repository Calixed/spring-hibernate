package demo;

import demo.entity.Instructor;
import demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class GetInstructorDetailDemo {

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

           // get the instructor detail object
            int id = 6;
            InstructorDetail tempInstructorDetail = session.get(InstructorDetail.class, id);

            // print the instructor detail
            System.out.println("tempInstructorDetail: "+ tempInstructorDetail);

            // print the associated instructor
            System.out.println("the associated instructor: "+ tempInstructorDetail.getInstructor());

            // commit the transaction
            session.getTransaction().commit();
            System.out.println("Done!");
            // error handling
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            // handle connection leak issue
            session.close();
            // close the heavy object
            factory.close();
        }

    }

}
