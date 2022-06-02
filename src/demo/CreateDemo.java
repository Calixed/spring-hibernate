package demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import demo.entity.Instructor;
import demo.entity.InstructorDetail;
import demo.entity.Student;

public class CreateDemo {

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
            // create the objects of two class
            Instructor tempInstructor0 = new Instructor("Joanne", "Dote", "Doe@gmail.com");
            InstructorDetail tempInstructorDetail0 = new InstructorDetail("11test.com", "coding");

            // create the objects of two class
            Instructor tempInstructor1 = new Instructor("Michael", "Duke", "33@gmail.com");
            InstructorDetail tempInstructorDetail1 = new InstructorDetail("22test.com", "Python");

            // create the objects of two class
            Instructor tempInstructor2 = new Instructor("John", "Doe", "222dasd@gmail.com");
            InstructorDetail tempInstructorDetail2 = new InstructorDetail("33test.com", "Java");

            // associate the object : reference this object to another object IN MEMORY
            System.out.println("Referencing 2nd entity to Instructor Class");
            tempInstructor0.setInstructorDetail(tempInstructorDetail0);
            tempInstructor1.setInstructorDetail(tempInstructorDetail1);
            tempInstructor2.setInstructorDetail(tempInstructorDetail2);

            // start transaction
            session.beginTransaction();

            // save the instructor
            // this will also save the details object because of CascadeType.ALL
            // model Instructor we have a joinColumn annotation. thats where we are both saving and deleting data
            System.out.println("Saving Instructor 3 objects: " + tempInstructor0 + tempInstructor1+ tempInstructor2);
            session.save(tempInstructor0);
            session.save(tempInstructor1);
            session.save(tempInstructor2);

            // commit the transaction
            session.getTransaction().commit();
            System.out.println("");

            System.out.println("Done!");

        } finally {
            factory.close();
        }

    }

}
