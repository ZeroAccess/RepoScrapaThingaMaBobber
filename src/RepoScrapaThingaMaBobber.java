import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.*;
import org.jsoup.select.Elements;
import java.io.IOException;

class RepoScrapaThingaMaBobber {
    public static void main(String[] args) {
        RepoScrapaThingaMaBobber program = new RepoScrapaThingaMaBobber();
        program.displayDirectories("http://archive.ubuntu.com/ubuntu/pool/");
    }

    private void displayDirectories(String url) {
        try {
            Document doc = Jsoup.connect(url).userAgent("Chrome").get();
            Elements repoPackages = doc.select("tr");
            for (Element repoPackage : repoPackages) {
                if (!repoPackage.select("a[href]").text().isEmpty() && repoPackage.select("a[href]").text().endsWith("/")) {
                    System.out.println(repoPackage.select("a[href]").text());
                   }
            }
         } catch (NullPointerException e) {
            System.out.println("NullPointer Exception");
        } catch (HttpStatusException e) {
            System.out.println("HttpStatus Exception");
        } catch (IOException e) {
            System.out.println("IOException Exception");
        }
    }
}
