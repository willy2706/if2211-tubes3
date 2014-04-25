/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

/**
 *
 * @author Opel Howard
 */
public class Algorithm {
    public static HashMap<String, Vector<String>> category;
    public static Vector<String> keyword;
    
    public String KMP(String text) {
       category = new HashMap<>();
        keyword = new Vector<>();
        keyword.add("abacab");
        category.put("Gila", keyword);
        String ans = null;
        for (Iterator i = category.entrySet().iterator(); i.hasNext() && (ans == null);) {
            Map.Entry pair = (Map.Entry) i.next();
            List<String> keyword_temp = (Vector<String>) pair.getValue();
            for (Iterator j = keyword_temp.iterator(); j.hasNext() && (ans == null);) {
                // Knuth–Morris–Pratt algorithm
                int m = 0, it = 0;
                String w = (String) j.next();
                int T[] = new int[w.length()]; // for backtracking
                T[0] = -1; // indicate cannot backtrack
                while (m + it < text.length()) {
                    if (w.charAt(it) == text.charAt(m+it)) {
                        if (it == w.length()-1) // finish
                            break;
                        ++it;
                    }
                    else {
                        m = m+it-T[it];
                        if (T[it] != -1)
                            it = T[it];
                        else
                            it = 0; 
                    }
                }
                // end of Knuth–Morris–Pratt algorithm
                if (m != text.length())
                    ans = (String) pair.getKey();
            }
        }
        return ans;
    }
    public static String BoyerMoore(String text) {
        String ans = null;
        HashMap<Character, Integer> L = new HashMap<>();
        for (int it = 0; it < text.length(); ++it) { // set the initial L function
            L.put(text.charAt(it), -1);
        }
        
        for (Iterator i = category.entrySet().iterator(); i.hasNext() && (ans == null);) {
            Map.Entry pair = (Map.Entry) i.next();
            List<String> keyword_temp = (Vector<String>) pair.getValue();
            for (Iterator j = keyword_temp.iterator(); j.hasNext() && (ans == null);) {
                // Boyer–Moore string search algorithm
                String P = (String) j.next(); // Pattern
                HashMap<Character, Integer> L_temp = new HashMap<>(L);
                for (int it = 0; it < P.length(); ++it) // new L function for new pattern
                    L_temp.put(P.charAt(it), it);
                
                int m = 0;
                boolean found = false;
                while (m+P.length()-1 < text.length()) {
                    int count = 0;
                    int n = P.length()-1;
                    while ((count != P.length()) && (P.charAt(n) == text.charAt(n+m))) {
                        ++count;
                        --n;
                    }
                    if (count == P.length()) {
                        found = true;
                        break;
                    }
                    if (L_temp.get(text.charAt(n+m)) != -1) {
                        if (L_temp.get(text.charAt(n+m)) < n)
                            m += n-L_temp.get(text.charAt(n+m));
                        else
                            ++m;
                    }
                    else {
                        m += n+1;
                    }
                }
                if (found)
                    ans = (String) pair.getKey();
            }
        }
        return ans;
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String tweet = "abacaabadcabacabaabb";
        String tweet_lowercase = tweet.toLowerCase();
        
        category = new HashMap<>();
        keyword = new Vector<>();
        keyword.add("abacab");
        category.put("Gila", keyword);
        
        String ans = BoyerMoore(tweet_lowercase); // initial answer
        
        if (ans != null)
            System.out.println(ans);
        else
            System.out.println("Unknown");
    }   
}