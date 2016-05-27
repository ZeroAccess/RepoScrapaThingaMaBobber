import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

class RepoCrawlaThingaMaBobber {
    private final List<String> links = new LinkedList<>();
    private static final ArrayList<String> filesToIterate = new ArrayList<>();
    private final Set<String> pagesVisited = new HashSet<>();
    private final List<String> pagesToVisit = new LinkedList<>();

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
        System.out.println("Make sure you're connected to a Repo");
        return null;
    }

    private void crawl(Document doc) {
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

    private List<String> getLinks() {
        return this.links;
    }

    void search(String url) {
        try {
            do {
                RepoCrawlaThingaMaBobber crawla = new RepoCrawlaThingaMaBobber();
                String currentUrl;
                if (this.pagesToVisit.isEmpty()) {
                    currentUrl = url;
                    this.pagesVisited.add(url);
                } else {
                    currentUrl = this.nextUrl();
                }
                crawla.crawl(crawla.connect(currentUrl));
                this.pagesToVisit.addAll(crawla.getLinks());
            } while (pagesToVisit.size() > 0);
        } catch(IllegalArgumentException ex) {
            System.out.println("Make sure you're connected first!");
        }
    }

    private String nextUrl() {
        String nextUrl;
        do {
            nextUrl = this.pagesToVisit.remove(0);
        }
        while (this.pagesVisited.contains(nextUrl));
        this.pagesVisited.add(nextUrl);
        return nextUrl;
    }

    void writeFile(String fileName) {
        try {
            Date date = new Date();
            SimpleDateFormat sdfr = new SimpleDateFormat("MM-dd-yyyy");
            String dateString = sdfr.format(date);

            if(fileName.equalsIgnoreCase("http://archive.ubuntu.com/ubuntu/pool/main/")) {
                fileName = "Ubuntu-Main-" + dateString + ".txt";
            } else if(fileName.equalsIgnoreCase("http://archive.debian.org/debian/pool/main/")) {
                fileName = "Debian-Main-" + dateString + ".txt";
            } else {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Unknown Repo used. Please type FileName: ");
                fileName = scanner.next() + dateString + ".txt";
            }
            System.out.println(fileName);
            PrintWriter writer = new PrintWriter(fileName);
            for (String aList : filesToIterate) {
                writer.write(aList);
                writer.write("\n");
            }
            writer.close();
        } catch (IOException ex) {
            System.out.println("Doesn't work");
        }
    }
}