
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.lang.reflect.Method;
import java.util.List;
import twitter4j.*;
import twitter4j.auth.AccessToken;

public class Main {
    public static void main(String[] args) {
        // gets Twitter instance with default credentials
        final String algoritma = "KMP";
        final String query = "saya";
        final int count = 100;
        try {
            Tweet tweet = new Tweet(query, count);
            
            /* panggil method algoritma */
            Class cls = Class.forName("Algorithm");
            Object obj = cls.newInstance();
            Class[] paramString = new Class[1];	
            paramString[0] = String.class;
            Method method = cls.getDeclaredMethod("KMP",paramString);
            
            /* iterasi untuk method tersebut */
            int i = 0;
            for (Status status : tweet.getResult()) {
                ++i;
                System.out.println(i+ ". " + "@" + status.getUser().getScreenName() + ":" + status.getText());
            }
            
            System.out.println(method.invoke(obj,"abacaabadcabacabaabb"));
       } catch (Exception e) {
            System.out.println("err");
       }
    }
}

