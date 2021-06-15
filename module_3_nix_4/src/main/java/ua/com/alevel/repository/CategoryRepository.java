package ua.com.alevel.repository;

import org.hibernate.Session;
import org.hibernate.query.Query;
import ua.com.alevel.db.ConnectionFactory;
import ua.com.alevel.model.Category;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CategoryRepository {

    private static final String SELECT_ALL_CATEGORIES = "from Category";

    public Set<Category> findAll() {
        List<Category> categories;
        try (Session session = ConnectionFactory.getInstance().getFactory().getCurrentSession()) {
            session.beginTransaction();

            Query<Category> query = session.createQuery(SELECT_ALL_CATEGORIES, Category.class);

            categories = query.list();

            session.getTransaction().commit();
        }
        return new HashSet<>(categories);
    }

}
