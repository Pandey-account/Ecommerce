
package com.mycart.dao;

import com.mycart.entites.Product;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;


public class ProductDao {
    
    private SessionFactory factory;

    public ProductDao(SessionFactory factory) {
        this.factory = factory;
    }
    
    public boolean SaveProduct(Product product){
        boolean f=false;
        try {
            Session s = this.factory.openSession();
            Transaction tx = s.beginTransaction();
            
            s.save(product);
            f=true;
            tx.commit();
            s.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return f;
    }
//    get all products
    public List<Product> getAllProducts(){
        Session s = this.factory.openSession();
        Query q = s.createQuery("from Product");
        List<Product> list = q.list();
        return list;
    }
//    get all products of given category
    public List<Product> getAllProductsBycatId(int id){
        Session s = this.factory.openSession();
        Query q = s.createQuery("from Product where category.catId="+id);
        List<Product> list = q.list();
        return list;
    }
}
