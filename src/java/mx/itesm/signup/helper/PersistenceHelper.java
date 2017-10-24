package mx.itesm.signup.helper;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PersistenceHelper {
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("signupPU");

    public static EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }
}
