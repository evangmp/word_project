package Data;
import java.util.Scanner;

public class Main {
    String choice;
    public static void main(String[] args) { // a double function main for the "welcome" line
        System.out.println("Welcome in IDK \nChoice with number in front of each line");
        Main main = new Main();
        main.second_main();
    }

    public void second_main() {
        while (true) { // while loop to choose list to learn or create a new
            Scanner scanner_choice = new Scanner(System.in);

            System.out.println("\n1. List choice menu");
            System.out.println("2. Create a table \nOr Exit");

            int choice = scanner_choice.nextInt();
            if (choice == 1) {
                Main list_choice = new Main();
                list_choice.list_choice();
            }
            if (choice == 2) {
                Print add_table = new Print();
                add_table.pre_set("", 3);
            }
            else {
                System.exit(0);
            }
        }
    }

    public void list_choice(){ // print all the lists available with an input to choose
        Print print_list_r = new Print();
        Scanner scanner = new Scanner(System.in);
        Learn_Menu learn_menu = new Learn_Menu();

        System.out.println("\nList choice :");
        print_list_r.pre_set("", 0);
        System.out.println();

        this.choice = scanner.nextLine();
        learn_menu.main_menu(choice);

    }
}

class Learn_Menu extends Main {
    public void main_menu(String list) {
        // with the list name, possibility to delete, add a word, learn or see statistics
        Scanner scanner = new Scanner(System.in);
        Print add_word = new Print();
        Print_list_word print_list = new Print_list_word();

        System.out.println("\nMain of learn menu");
        while (true) {
            System.out.println("1. Add word \n2. Delete word \n3. Learn " +
                    "\n4. Print statistics of the list \nOr return to main");
            int choice_i = scanner.nextInt();

            switch (choice_i) {
                case (1):
                    add_word.pre_set(list, 1);
                    break;
                case (2):
                    System.out.println("\nDelete word : \n");
                    print_list.database(list,"fr_word");
                    add_word.pre_set(list, 2);
                    break;
                case (3):
                    System.out.println("\nLearn : ");
                    Learn learn = new Learn(list);
                    learn.learn_w();
                    break;
                case (4):
                    System.out.println("\nPrint statistics of the list : ");
                    print_list.database(list, "all");
                    break;
                default:
                    second_main();
            }
        }
    }
}