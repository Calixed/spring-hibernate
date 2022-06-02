package demo;

import entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCourseAndReviewDemo {

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

            // add some reviews
            tempCourse.addReview(new Review("Had fun using java"));
            tempCourse.addReview(new Review("Enjoyed it :>"));
            tempCourse.addReview(new Review("learning java is hard"));
            tempCourse.addReview(new Review("test review comments"));

            //for diagnostic testing
            System.out.println("Saving the course: "+tempCourse);
            System.out.println("Saving reviews in this course "+tempCourse.getReviews());
            // save the course and leverage the cascade all
            session.save(tempCourse);

            session.getTransaction().commit();
        } finally {
            session.close();
            factory.close();
        }

    }

}
