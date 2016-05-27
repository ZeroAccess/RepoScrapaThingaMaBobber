import java.util.Scanner;

class Main {
    private final RepoScrapaThingaMaBobber crawla = new RepoScrapaThingaMaBobber();
    private String url = "";

    public static void main(String[] args) {
        Main main = new Main();
        main.go();
    }

    private void go() {
        System.out.println("Welcome to Java Directory Scraper");
        System.out.println("Please use caution when using this program as it can be resource intensive on servers.");
        System.out.println("Keeping that in mind you may need to increase the crawlers rate. \n");
        printMenu();
    }

    private void printMenu() {
        System.out.println("Java Directory Scraper");
        System.out.println("Select an option");
        System.out.println("0: Connect to database");
        System.out.println("1: Perform Scrape");
        System.out.println("2: Settings");
        System.out.println("3: Export List");
        System.out.println("4: Print Menu");
        System.out.println("5: Exit");
        boolean exit = true;
        while (exit) {
            Scanner scanner = new Scanner(System.in);
            int option = scanner.nextInt();
            switch (option) {
                case 0:
                    System.out.println("What Repo would you like to connect to?");
                    System.out.println("0: Ubuntu/Main");
                    System.out.println("1: Debian/Main");
                    System.out.println("2: Type your own");
                    int repoOption = scanner.nextInt();
                    switch (repoOption) {
                        case 0:
                            System.out.println("Connecting to Ubuntu Repo");
                            url = "http://archive.ubuntu.com/ubuntu/pool/main/";
                            break;
                        case 1:
                            System.out.println("Connecting to Debian Repo");
                            url = "http://archive.debian.org/debian/pool/main/";
                            break;
                        case 2:
                            System.out.println("Type the exact path you'd like scraped");
                            url = scanner.next();
                            break;
                        default:
                            System.out.println("Try Again");
                            break;
                    }
                    crawla.connect(url);
                    printMenu();
                    break;
                case 1:
                    System.out.println("When completed menu will come back.");
                    crawla.search(url);
                    printMenu();
                    break;
                case 2:
                    System.out.println("Feature not available.");
                    break;
                case 3:
                    crawla.writeFile(url);
                    printMenu();
                    break;
                case 4:
                    printMenu();
                    break;
                case 5:
                    exit = false;
                    break;
                default:
                    printMenu();
                    break;
            }
        }
        System.out.println("Exiting Java Directory Scraper");
    }
}
