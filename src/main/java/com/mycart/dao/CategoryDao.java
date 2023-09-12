package com.mycart.dao;

import com.mycart.entites.Category;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class CategoryDao {

    private SessionFactory factory;

    public CategoryDao(SessionFactory factory) {
        this.factory = factory;
    }
//    save the category to db

    public int saveCategory(Category cat) {
        int catId = 0;
        try {
            Session session = this.factory.openSession();
            Transaction tx = session.beginTransaction();

            catId = (int) session.save(cat);

            tx.commit();
            session.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return catId;
    }

    public List<Category> getCategories() {
        Session s = this.factory.openSession();
        Query q = s.createQuery("from Category");
        List<Category> list = q.list();
        return list;
    }

    public Category getCategoryById(int cid) {
        Category catId = null;
        try {
            Session s = this.factory.openSession();
            catId = s.get(Category.class, cid);

            s.close();;
        } catch (Exception e) {
        }
       return catId;
    }
}
