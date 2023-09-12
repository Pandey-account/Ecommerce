
package com.mycart.helper;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class FactoryProvider {
    
    private static SessionFactory factory;
    
    public static SessionFactory getFactory(){
        
        try {
            if(factory == null){
            Configuration con = new Configuration();
            con.configure("hibernate.cfg.xml");
            factory = con.buildSessionFactory();
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return factory;
    }
            
}
