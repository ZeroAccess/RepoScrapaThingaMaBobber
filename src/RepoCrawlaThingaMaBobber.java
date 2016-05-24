import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

class RepoCrawlaThingaMaBobber {
    private final List<String> links = new LinkedList<>();
    private ArrayList<String> filesToIterate = new ArrayList<>();

    Document connect(String url) {
        try {
            Connection connection = Jsoup.connect(url).userAgent("Mozilla");
            Document htmlDocument = connection.get();
            if (connection.response().statusCode() == 200) {
                return htmlDocument;
            }
            if (!connection.response().contentType().contains("text/html")) {
                System.out.println("Please check and make sure this is a valid html document");
            }
        } catch (IOException ioe) {
            System.out.println("Unable to connect please try again.");
        }
        System.out.println("Unable to connect please try again.");
        return null;
    }

    void crawl(Document doc) {
        try {
            Elements linksOnPage = doc.select("a[href]");
            for (Element link : linksOnPage) {
                if (link.select("a[href]").text().endsWith("/")) {
                    this.links.add(link.absUrl("href"));
                } else if (link.select("a[href]").text().contains(".")) {
                    filesToIterate.add(link.select("a[href]").text());
                }
            }
        } catch (NullPointerException ex) {
            System.out.println("Null Pointer Exception");
        }
    }

    List<String> getLinks() {
        return this.links;
    }

    public ArrayList<String> getFilesToIterate() {
        return filesToIterate;
    }
}