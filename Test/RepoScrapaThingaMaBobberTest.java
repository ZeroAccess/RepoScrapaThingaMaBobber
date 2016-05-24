class RepoScrapaThingaMaBobberTest {

    public static void main(String[] args) {
        RepoScrapaThingaMaBobber scrapa = new RepoScrapaThingaMaBobber();
        scrapa.search("http://archive.ubuntu.com/ubuntu/pool/main/");

        RepoCrawlaThingaMaBobber crawla = new RepoCrawlaThingaMaBobber();
        scrapa.writeFile(crawla.getFilesToIterate());
     }
}