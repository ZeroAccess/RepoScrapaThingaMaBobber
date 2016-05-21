import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

/**
 * Created by robertsg on 5/20/2016.
 **/

class RepoScrapaThingaMaBobber {
    private String url;
    private final StringBuilder stringBuilder = new StringBuilder();

    public static void main(String[] args) {
        RepoScrapaThingaMaBobber program = new RepoScrapaThingaMaBobber();
        program.BuildUri("");
    }

    private void BuildUri(String buildUrl) {
         try {
             url = new URI("http://archive.ubuntu.com/ubuntu/pool/"
                    + stringBuilder.append(buildUrl).toString()).toString();
        } catch (URISyntaxException ex) {
            ex.printStackTrace();
        }
        displayDirectories();
    }

    private void displayDirectories() {
           try {
            Document doc = Jsoup.connect(url).userAgent("Chrome").get();
            Elements repoPackages = doc.select("tr");
            repoPackages.stream()
                    .filter(repoPackage -> !repoPackage.select("a[href]").text().isEmpty() && repoPackage.select("a[href]").text().endsWith("/"))
                    .map(repoPackage -> { System.out.println(url); return repoPackage;})
                    .forEach(repoPackage -> BuildUri(repoPackage.select("a[href]").text()));
        } catch (NullPointerException e) {
            System.out.println("NullPointer Exception");
        } catch (HttpStatusException e) {
            System.out.println("HttpStatus Exception");
        } catch (IOException e) {
            System.out.println("IOException Exception");
        }
    }
}
