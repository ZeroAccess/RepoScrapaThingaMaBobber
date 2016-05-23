import org.jsoup.Jsoup;
import org.jsoup.nodes.*;
import org.jsoup.select.Elements;
import java.io.IOException;

class RepoScrapaThingaMaBobber {
    public static void main(String[] args) {
        RepoScrapaThingaMaBobber program = new RepoScrapaThingaMaBobber();
        program.connect("http://archive.ubuntu.com/ubuntu/pool/");
    }

    private void connect(String url) {
        try {
            Document doc = Jsoup.connect(url).timeout(5000).userAgent("Mozilla").data("name", "jsoup").get();
            iterateDirectory(doc);
        } catch (IOException e) {
            System.out.println("IOException Exception");
        }
    }

    private void iterateDirectory(Document doc) {
        Elements repoPackages = doc.select("tr");
        for (Element repoPackage : repoPackages) {
            if (repoPackage.select("a[href]").text().endsWith("/")) {
                    printDirectories(repoPackage);
            } else {
                printFiles(repoPackage);
            }
        }
    }

    private void printDirectories(Element element) {
        System.out.println(element.select("a[href]").text());
    }

    private void printFiles(Element element) {
        if(element.text().contains(".")) {
            System.out.println(element.text());
        }
    }
}
