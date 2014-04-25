
import java.util.List;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.auth.AccessToken;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author WILLY
 */
public class Tweet {
    private List<Status> hasilpencarian;
    
    public Tweet(String search, int count) {
        Twitter twitter = new TwitterFactory().getInstance();
        twitter.setOAuthConsumer("", "kNWVhswO46J6KpmEOoOr03l4EAvf4kskSPmHVx9RLS4YqbzTKT");
        AccessToken oathAccessToken = new AccessToken("113345536-8mcL9zu0CdO2EDgmaF9eMWn4FGC2TBBBuxycPV4k","PmIXSW0LfYVP8hvY6CDrGVev7iS3pnLn9RaNiOeM15mIY");
        twitter.setOAuthAccessToken(oathAccessToken);
        try {
            User user = twitter.verifyCredentials();
            Query query = new Query(search); // ini key word yang mau dicari
            query.setCount(count); // jumlah keyword
            QueryResult result = twitter.search(query);
            hasilpencarian = result.getTweets(); // end of pencarian
            /*int i = 0;
            for (Status status : result.getTweets()) {
                ++i;
                System.out.println(i+ ". " + "@" + status.getUser().getScreenName() + ":" + status.getText());
            }*/
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to get timeline: " + te.getMessage());
            System.exit(-1);
        }
    }
    
    public List<Status> getResult() {
        return hasilpencarian;
    }
}
