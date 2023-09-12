package com.mycart.helper;

import java.io.FileOutputStream;
import java.io.InputStream;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class PicSaveFile {

    private  SessionFactory factory;

   
    
    public void SaveFile(InputStream is, String path) {

        try {
            if (path != null) {
                byte[] data = new byte[is.available()];
                is.read(data);
                FileOutputStream fos = new FileOutputStream(path);
                fos.write(data);
                fos.flush();
                fos.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
}
    public  void getImages(int pId) {
        
        Session st = this.factory.openSession();
        
        String q1="";
        
          st.close();
    }
}
