package lk.ijse.config;

import lk.ijse.entity.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class SessionFactoryConfig {
    private static SessionFactoryConfig factoryConfig;
    private SessionFactory sessionFactory;
    private SessionFactoryConfig() throws IOException {
        Properties properties = new Properties();
        InputStream inputStream = SessionFactoryConfig.class.getClassLoader().getResourceAsStream("hibernate.properties");
        System.out.println("badu enwa");
        properties.load(inputStream);
        Configuration configuration = new org.hibernate.cfg.Configuration();
        configuration.setProperties(properties);

        //Metadata metadata = new MetadataSources((ServiceRegistry) configuration).addAnnotatedClass(Customer.class).getMetadataBuilder().build();

        configuration.addAnnotatedClass(Customer.class);
        sessionFactory = configuration.buildSessionFactory();
    }

    public static SessionFactoryConfig getInstance() throws IOException {
        return factoryConfig == null ? new SessionFactoryConfig() : factoryConfig;
    }

    public Session getSession(){
        return sessionFactory.openSession();
    }
}
