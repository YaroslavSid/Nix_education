package ua.com.alevel.repository;

import org.hibernate.Session;
import org.hibernate.query.Query;
import ua.com.alevel.db.ConnectionFactory;
import ua.com.alevel.model.Account;
import ua.com.alevel.model.User;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class AccountRepository {

    private static final String FIND_ACCOUNTS_BY_USER = "from Account a where a.user = :user";
    private static final String FIND_ACCOUNT_BY_ID = "from Account a where a.id = :id";

    public Set<Account> findAllForUser(User user) {
        List<Account> accounts;
        try (Session session = ConnectionFactory.getInstance().getFactory().getCurrentSession()) {
            session.beginTransaction();

            Query<Account> accountQuery = session.createQuery(FIND_ACCOUNTS_BY_USER, Account.class);
            accountQuery.setParameter("user", user);

            accounts = accountQuery.list();

            session.getTransaction().commit();
        }
        return new HashSet<>(accounts);
    }

    public Optional<Account> find(long id) {
        Optional<Account> account;
        try (Session session = ConnectionFactory.getInstance().getFactory().getCurrentSession()) {
            session.beginTransaction();

            Query<Account> accountQuery = session.createQuery(FIND_ACCOUNT_BY_ID, Account.class);
            accountQuery.setParameter("id", id);
            account = accountQuery.list().stream().findFirst();

            session.getTransaction().commit();
        }
        return account;
    }

}
