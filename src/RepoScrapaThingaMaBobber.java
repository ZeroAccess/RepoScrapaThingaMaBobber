import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

class RepoScrapaThingaMaBobber {
    private String distribution;
    private Elements repoPackages;
    private ArrayList<String> files = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);
    boolean exit = true;


    public static void main(String[] args) {
        RepoScrapaThingaMaBobber program = new RepoScrapaThingaMaBobber();
        program.printMenu();
    }

    private void printMenu() {
        System.out.println("Select an option");
        System.out.println("0: Connect to database");
        System.out.println("1: Add Directories");
        System.out.println("2: Display all sub directories");
        System.out.println("3: Display files only");
        System.out.println("4: Export List");
        System.out.println("5: Check Settings");
        System.out.println("6: Print Menu");
        System.out.println("7: Exit");
        while (exit) {
            int option = scanner.nextInt();
            switch (option) {
                case 0:
                    System.out.println("What Repo would you like to connect to?");
                    System.out.println("0: Ubuntu");
                    System.out.println("1: Debian");
                    int repoOption = scanner.nextInt();
                    switch (repoOption) {
                        case 0:
                            System.out.println("Connecting to Ubuntu Repo");
                            distribution="http://archive.ubuntu.com/ubuntu/pool/";
                            break;
                        case 1:
                            System.out.println("Connecting to Debian Repo");
                            distribution="http://archive.debian.org/debian/pool/";
                            break;
                        default:
                            System.out.println("Try Again");
                            break;
                    }
                    connect(distribution ,"");
                    break;
                case 1:
                    addDirectories();
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    printMenu();
                    break;
                case 7:
                    exit = false;
                    break;
                default:
                    printMenu();
                    break;
            }
        }
    }

    private void connect(String distribution, String url) {
        try {
            Document doc = Jsoup.connect(distribution + url).timeout(5000).userAgent("Mozilla").data("name", "jsoup").get();
            repoPackages = doc.select("tr");
        } catch (IOException e) {
            System.out.println("IOException Exception");
        }
    }

    private void displayCurrentDirectory() {
        int counter = 0;
        for (Element repoPackage : repoPackages) {
            if (repoPackage.select("a[href]").text().endsWith("/")) {
                System.out.println(counter + " : " + repoPackage.select("a[href]").text());
                counter++;
            }
        }
        //// TODO: 5/23/2016 Allow the use to drill down further and capture all files below current directory
    }

    private void addDirectories() {
        ArrayList<String> directories = new ArrayList<>();
        for (Element repoPackage : repoPackages) {
            if (repoPackage.select("a[href]").text().endsWith("/")) {
                System.out.println(repoPackage.select("a[href]").text());
                directories.add(repoPackage.select("a[href]").text());
            }
        }
    }

    private void addFiles() {
        for (Element repoPackage : repoPackages) {
            if (repoPackage.select("a[href]").text().contains(".")) {
                files.add(repoPackage.select("a[href]").text());
            }
        }
    }

    private void printList(ArrayList directory) {
        for (int i = 0; i < directory.size(); i++) {
            System.out.println(directory.get(i));
        }
    }

    private void printSettings() {
    }


}
