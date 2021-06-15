package ua.com.alevel.repository;

import org.hibernate.Session;
import org.hibernate.query.Query;
import ua.com.alevel.db.ConnectionFactory;
import ua.com.alevel.model.User;

import java.util.Optional;

public class UserRepository {

    private static final String FIND_USER_BY_ID = "from User u where u.id = :id";

    public Optional<User> findUser(long id) {
        Optional<User> user;
        try (Session session = ConnectionFactory.getInstance().getFactory().getCurrentSession()) {
            session.beginTransaction();

            Query<User> query = session.createQuery(FIND_USER_BY_ID, User.class);
            query.setParameter("id", id);

            user = query.list().stream().findFirst();

            session.getTransaction().commit();
        }

        return user;
    }

}
