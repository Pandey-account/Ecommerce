package com.mycart.dao;

import com.mycart.entites.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class UserDao {

    private SessionFactory factory;

    public UserDao(SessionFactory factory) {
        this.factory = factory;
    }

    //get User by email and password
    public User getUserByEmailAndPassword(String email, String password) {
        User user = null;

        try {
            String query = "from User where userEmail=: e and userPassword=: p";
            Session sp = this.factory.openSession();
            Query q = sp.createQuery(query);
            q.setParameter("e", email);
            q.setParameter("p", password);

            user = (User) q.uniqueResult();

            sp.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }

    public boolean saveUser(User u) {
        boolean f = false;
        try {
            Session s = this.factory.openSession();
            Transaction tx = s.beginTransaction();

            s.save(u);
            f = true;
            tx.commit();
            s.close();
        } catch (Exception e) {
            e.printStackTrace();
            f = false;
        }
        return f;
    }
}
