package ua.ithillel.dnipro.persistence;

import java.util.Properties;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ua.ithillel.dnipro.common.Stock;

public class HibernateUtil {
    private static final SessionFactory commonSessionFactory;
    static {
        try {
            Properties prop = new Properties();
            prop.setProperty("hibernate.connection.url", "jdbc:mysql://127.0.0.1:3306/lesson_14" +
                    "?verifyServerCertificate=false"+
                    "&useSSL=false"+
                    "&requireSSL=false"+
                    "&useLegacyDatetimeCode=false"+
                    "&amp"+
                    "&serverTimezone=UTC");
            prop.setProperty("hibernate.connection.username", "student");
            prop.setProperty("hibernate.connection.password", "password");
            prop.setProperty("dialect", "org.hibernate.dialect.MySQLDialect");
            prop.setProperty("format_sql", "true");
            prop.setProperty("show_sql", "true");


            commonSessionFactory = new Configuration()
                    .addPackage("ua.ithillel.dnipro.common")
                    .addProperties(prop)
                    .addAnnotatedClass(Stock.class)
                    .buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession()
            throws HibernateException {
        return commonSessionFactory.openSession();
    }

    public static void main(String... args){
        Session session=getSession();
        session.beginTransaction();

        /**
         * Выводим сущность из БД с id 7
         * */
        Stock stock=(Stock) session.get(Stock.class, new Integer(7));
        System.out.println(stock);

        /**
         * Обновляем запись в БД с id 7
         */
        stock.setStockCode("STC-0005");
        stock.setStockName("Something5");

        /**
         * Удаляем сущность в БД c id 7
         * */
        session.delete(stock);
        /**
         * Создаем сущность в БД
         * */
        Stock stock1 = new Stock();
        stock1.setStockCode("STC-0006");
        stock1.setStockName("Something3");


        session.getTransaction().commit();
        session.save(stock);
        session.save(stock1);
        session.close();
        commonSessionFactory.close();
    }
}
