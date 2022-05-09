package demo;

import entity.Course;
import entity.Instructor;
import entity.InstructorDetail;
import entity.Review;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class GetCourseAndReviewDemo {

    public static void main(String[] args) {
        // heavy weight object ONLY CREATE ONCE
        // used in generating sessions
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class) // to let hibernate know about our classes
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Review.class)
                .buildSessionFactory();

        // the factory will be used to handled a session
        Session session = factory.getCurrentSession();
        try {
            // start transaction
            session.beginTransaction();

            // get the primary key
            int theId =10;

            // get the course using session
            Course tempCourse = session.get(Course.class,theId);

            //printing out the course
            System.out.println("The Name of Course "+tempCourse);

            //printing out the reviews -> course fetchType is Lazy Loading
            // data of the review will start to load to the application because hibernate is making another query
            System.out.println("The Reviews: "+tempCourse.getReviews());

            // save the course and leverage the cascade all
            session.save(tempCourse);

            session.getTransaction().commit();
        } finally {
            session.close();
            factory.close();
        }

    }

}
