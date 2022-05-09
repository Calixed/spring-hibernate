package demo;

import entity.Course;
import entity.Instructor;
import entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCourseDemo {

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
            // to get the id, get the primary key first
            int theId = 1;
            Instructor tempInstructor = session.get(Instructor.class, theId);

            // create some courses
            Course tempCourse1 = new Course("Java Enterprise I");
            Course tempCourse2 = new Course("Java Enterprise II");

            // add courses to instructor
            // this is where we will be call the course.setInstructor()
            tempInstructor.add(tempCourse1);
            tempInstructor.add(tempCourse2);

            // save courses
            session.save(tempCourse1);
            session.save(tempCourse2);

            // commit the transaction
            session.getTransaction().commit();
            System.out.println("");
            System.out.println("Done!");
        } finally {
            session.close();
            factory.close();
        }

    }

}
