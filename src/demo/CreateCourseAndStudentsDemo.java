package demo;

import entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCourseAndStudentsDemo {

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

            // create a course
            Course tempCourse = new Course("Java Enterprise 1");

            // save the course
            System.out.println("Saving Course: "+ tempCourse.getTitle());
            session.save(tempCourse);

            // create students
            Student tempStudent1 = new Student("John","Michael", "test@gmail.com");
            Student tempStudent2 = new Student("Mike","Long", "mike231@gmail.com");

            // add students to the course
            tempCourse.addStudent(tempStudent1);
            tempCourse.addStudent(tempStudent2);

            // save the students in the database
            System.out.println("Saving Students");
            session.save(tempStudent1);
            session.save(tempStudent2);
            System.out.println("Saved Students: "+tempCourse.getStudents());

            // commit transactions
            session.getTransaction().commit();
            System.out.println("Transaction Done");
        } finally {
            session.close();
            factory.close();
        }

    }

}
