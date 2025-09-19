import java.util.*;

interface Playable {
    void play();
}

abstract class Library
{
    String title,author,borrow_date;
    boolean available_status;

    Library(String title,String author)
    {
        this.title=title;
        this.author=author;
        this.available_status=true;
    }

    void borrowed(String borrowDate)
    {
        if(!available_status)
        {
            System.out.println("The choosen item has been borrowed.");
        }

        else
        {
            available_status=false;
            borrow_date=borrowDate;
            System.out.println("The choosen book can be borrowed.\n Date of issue: "+borrowDate);
        }
    }

    void returned(String returnDate)
    {
        if(available_status){
        System.out.println("The choosen item has not been borrowed yet.");
        }

        else{
            available_status=true;
            String bd="",rd="";

            for(int i=0;i<borrow_date.length();i++)
            {
                if(borrow_date.charAt(i)!='-')
                {
                    bd+=borrow_date.charAt(i);
                }
                else break;
            }

             for(int i=0;i<returnDate.length();i++)
            {
                if(returnDate.charAt(i)!='-')
                {
                    rd+=returnDate.charAt(i);
                }
                else break;
            }

            int borrow_dt=Integer.parseInt(bd);
            int return_dt=Integer.parseInt(rd);

            int fine=0;
            if(return_dt-borrow_dt>14)
            {
                fine=(return_dt-borrow_dt)*10;
                System.out.println("Late submission. Fine to be given: Rs. "+fine);
            }
            else
            System.out.println("Successfull return of Book. Have a great Day ahead!");

        }
    }

    boolean isAvailable()
    {
        return available_status;
    }

    abstract void displayDetails();
}

class Book extends Library
{
    int pages;
    Book(String title,String author,int pages)
    {
        super(title, author);
        this.pages=pages;
    }

    int pages_func(int pages)
    {
        return pages;
    }

    @Override
    void displayDetails()
    {
        System.out.println("Book title: " + title + " by " + author + " with pages: " + pages + "and is it available? " + available_status);
    }
}

class Audiobook extends Library implements Playable {
    double play_time;

    Audiobook(String title, String author, double play_time) {
        super(title, author);
        this.play_time = play_time;
    }

    @Override
    public void play() {
        System.out.println("Playing audiobook: " + title + " " + play_time + " hrs");
    }

    @Override
    void displayDetails() {
        System.out.println("Audiobook: " + title + " by " + author + " of time: " + play_time + " hrs and is it available: " + available_status);
    }
}

class EMagazine extends Library {
    int issueNumber;

    EMagazine(String title, String author, int issueNumber) {
        super(title, author);
        this.issueNumber = issueNumber;
    }

    void archiveIssue() {
        System.out.println("Archiving Issue is: " + issueNumber + " titled as: " + title);
    }

    @Override
    void displayDetails() {
        System.out.println("E-Magazine: " + title + " by " + author + " issued on: " + issueNumber + " and is it available? " + available_status);
    }
}

public class LibraNet
{
    static Scanner sc=new Scanner(System.in);
    static List<Library> list=new ArrayList<>();
    public static void main(String[] args) {
        System.out.println("Welcome to LibraNet: The online Library Platform");

        list.add(new Book("Introuction to Fundamentals of Java", "R. K. Bose", 1545));
        list.add(new Audiobook("The Auido of the Terrified", "Steve Speilsberg", 4.5));
        list.add(new EMagazine("India Today", "Aaj Tak India", 1445));

        boolean flag=true;

         while (flag) {
            System.out.println("\n1. View All Items");
            System.out.println("2. Borrow Item");
            System.out.println("3. Return Item");
            System.out.println("4. Play Audiobook");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); 

            switch (choice) {
                case 1:  viewAllItems() ;break;
                case 2 : borrowItem() ;break;
                case 3 : returnItem() ;break;
                case 4 : playAudiobook() ;break;
                case 5 : { System.out.println("Exiting. Have a plaesant Day");flag=false;break; }
                default : System.out.println("Invalid choice!");
            }
        }
    }

        static void viewAllItems() {
        System.out.println("\n Library Items:");
        for (int i = 0; i < list.size(); i++) {
            System.out.print((i + 1) + ". ");
            list.get(i).displayDetails();
        }
    }

    static void borrowItem() {
        viewAllItems();
        System.out.print("Enter item number to borrow: ");
        int index = sc.nextInt() - 1;
        sc.nextLine();
        if (index >= 0 && index < list.size()) {
            System.out.print("Enter borrow date (DD-MM-YYYY): ");
            String date = sc.nextLine();
            list.get(index).borrowed(date);
        } else {
            System.out.println("Invalid selection.");
        }
    }

    static void returnItem() {
        viewAllItems();
        System.out.print("Enter item number to return: ");

        if (!sc.hasNextInt()) { 
        System.out.println("Invalid input! Please enter a number.");
        sc.nextLine(); 
        return;
    }
        int index = sc.nextInt() - 1;
        sc.nextLine();
        if (index >= 0 && index < list.size()) {
            System.out.print("Enter return date (DD-MM-YYYY): ");
            String date = sc.nextLine();
            list.get(index).returned(date);
        } else {
            System.out.println("Invalid selection.");
        }
    }

    static void playAudiobook() {
        viewAllItems();
        System.out.print("Enter item number to play (only audiobooks will play): ");
        int index = sc.nextInt() - 1;
        sc.nextLine();
        if (index >= 0 && index < list.size() && list.get(index) instanceof Playable p) {
            p.play();
        } else {
            System.out.println("This item is not playable.");
        }
    }
}
