import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by robertsg on 5/20/2016.
 **/

public class RepoScrapaThingaMaBobber {

    public static void main(String[] args) {
        RepoScrapaThingaMaBobber program = new RepoScrapaThingaMaBobber();
        program.retrieveUrl();
    }

    private void retrieveUrl() {
        //Todo Use this method to create a menu to retrieve multiple versions
        String url = "http://archive.ubuntu.com/ubuntu/pool/";

        try {
            Document doc = Jsoup.connect(url).userAgent("Chrome").get();
            System.out.println(doc.title() +"\n");


            Elements repoPackages = doc.select("dt");
            for (Element repoPackage : repoPackages) {
                System.out.println("Package : " + repoPackage.select("a[href]").text() + repoPackage.select("dt").text());
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
