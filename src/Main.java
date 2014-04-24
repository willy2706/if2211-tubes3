
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.List;
import twitter4j.*;
import twitter4j.auth.AccessToken;

public class Main {
    public static void main(String[] args) {
        // gets Twitter instance with default credentials
        Twitter twitter = new TwitterFactory().getInstance();
        twitter.setOAuthConsumer("", "kNWVhswO46J6KpmEOoOr03l4EAvf4kskSPmHVx9RLS4YqbzTKT");
        AccessToken oathAccessToken = new AccessToken("113345536-8mcL9zu0CdO2EDgmaF9eMWn4FGC2TBBBuxycPV4k","PmIXSW0LfYVP8hvY6CDrGVev7iS3pnLn9RaNiOeM15mIY");
        twitter.setOAuthAccessToken(oathAccessToken);
        try {
            User user = twitter.verifyCredentials();
            //twitter.updateStatus("Halo ini tweet kedua dengan bahasa pemrograman java");
            //List<Status> statuses = twitter.getMentionsTimeline();
            /*System.out.println("Showing @" + user.getScreenName() + "'s mentions.");
            for (Status status : statuses) {
                System.out.println("@" + status.getUser().getScreenName() + " - " + status.getText() + " " + status.getId());
            }*/
            Query query = new Query("saya");
            query.setCount(100);
            QueryResult result = twitter.search(query);
            int i = 0;
            for (Status status : result.getTweets()) {
                ++i;
                System.out.println(i+ ". " + "@" + status.getUser().getScreenName() + ":" + status.getText());
            }
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to get timeline: " + te.getMessage());
            System.exit(-1);
        }
    }
}

