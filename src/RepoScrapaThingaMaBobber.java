import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

class RepoScrapaThingaMaBobber {
    private final Set<String> pagesVisited = new HashSet<>();
    private List<String> pagesToVisit = new LinkedList<>();

    void search(String url) {
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
//        writeFile(crawla.getFilesToIterate());
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

    private void writeFile(ArrayList<String> list) {
        try {
            FileWriter writer = new FileWriter("MyFile.txt");
            for(int i = 0; i < list.size(); i++) {
                writer.write(list.get(i));
            }
            writer.close();
        } catch (IOException ex) {
            System.out.println("Doesn't work");
        }

    }
}