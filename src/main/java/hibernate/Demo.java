package hibernate;

import bdConnection.HibernateUtil;
import org.hibernate.Session;

public class Demo {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();


        session.close();
    }
}
