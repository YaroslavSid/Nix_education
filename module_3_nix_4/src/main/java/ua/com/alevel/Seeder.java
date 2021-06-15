package ua.com.alevel;

import org.hibernate.Session;
import ua.com.alevel.db.ConnectionFactory;
import ua.com.alevel.model.Account;
import ua.com.alevel.model.Category;
import ua.com.alevel.model.User;

public class Seeder {

    public void seed() {
        try (Session session = ConnectionFactory.getInstance().getFactory().getCurrentSession()) {
            session.beginTransaction();

            save(session);

            session.getTransaction().commit();
        }
    }

    private static void save(Session session) {
        Category categoryDebit1 = new Category();
        categoryDebit1.setName("SALARY");
        categoryDebit1.setType("debit");

        Category categoryDebit2 = new Category();
        categoryDebit2.setName("CARD_INCOME");
        categoryDebit2.setType("debit");

        Category categoryCredit1 = new Category();
        categoryCredit1.setName("PRODUCTS");
        categoryCredit1.setType("credit");

        Category categoryCredit2 = new Category();
        categoryCredit2.setName("MOVIES");
        categoryCredit2.setType("credit");

        session.save(categoryDebit1);
        session.save(categoryDebit2);
        session.save(categoryCredit1);
        session.save(categoryCredit2);

        User user = new User();
        user.setName("Ivan");
        user.setSurname("Pechka");

        session.save(user);

        Account account1 = new Account();
        account1.setName("Ivan-1");
        account1.setNumber("1234-1235-3456-3466");
        account1.setUser(user);
        account1.setScore(5000);

        Account account2 = new Account();
        account2.setName("Ivan-2");
        account2.setNumber("7543-1235-3456-3754");
        account2.setUser(user);
        account2.setScore(5000);

        session.save(account1);
        session.save(account2);
    }

}
