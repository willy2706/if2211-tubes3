
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
        final String algoritma = args[2];
        final String query = args[1];
        final int count = Integer.parseInt(args[0]);
        try {
            Tweet tweet = new Tweet(query, count);
            
            /* panggil method algoritma */
            Class cls = Class.forName("Algorithm");
            Object obj = cls.newInstance(); //instansiasi objek algoritma
            Class[] paramString = new Class[1];	
            paramString[0] = String.class;
            Method method = cls.getDeclaredMethod(algoritma,paramString);
            
            /* iterasi untuk method tersebut */
            int i = 0;
            for (Status status : tweet.getResult()) {
                ++i;
                String word = "@" + status.getUser().getScreenName() + ":" + status.getText();
                System.out.println(word);
                
                StringBuffer address = new StringBuffer();
                address.append("http://twitter.com/");
                address.append(status.getUser().getScreenName());
                address.append("/status/");
                address.append(status.getId());
                //System.out.println(address);
                /*try {
                    System.out.println(i + ". " + method.invoke(obj,word.toLowerCase()));
                } catch (NullPointerException ex) {
                    System.out.println(i + ". unknown");
                }*/
            }
            //System.out.println(method.invoke(obj,"abacaabadcabacabaabb"));
       } catch (Exception e) {
            System.out.println("err");
       }
    }
}

