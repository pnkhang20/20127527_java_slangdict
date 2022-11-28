/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package k.slangdict;

/**
 *
 * @author PHAM NGUYEN KHANG - 20127527 JAVA - KTPM2
 *
 */
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class Slangdict {

    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws FileNotFoundException, IOException, Exception {

        Words words = new Words();
        //Test functions
        //words.findBySlang();
        //words.findBySlang();
        //words.saveHistory("FFR");
        //words.saveFile();
        //words.delete();
        //words.printAll();
        //words.findByDefinition();
        //words.delete("SCO");
        //words.viewHistory();
        //words.resetDefault();
        //words.addSlang();
        //words.randomSlang();
        //words.quizSlangDefinition();
        //words.quizDefinitionSlang();

        String choice = null;
        boolean exit = false;
        Menu();

        while (true) {
            choice = sc.nextLine();
            switch (choice) {
                case "1":
                    words.findBySlang();
                    break;
                case "2":
                    words.findByDefinition();
                    break;
                case "3":
                    words.viewHistory();
                    break;
                case "4":
                    words.addSlang();
                    break;
                case "5":
                    words.edit();
                    break;
                case "6":
                    words.delete();
                    break;
                case "7":
                    words.resetDefault();
                    break;
                case "8":
                    words.randomSlang();
                    break;
                case "9":
                    words.quizSlangDefinition();
                    break;
                case "10":
                    words.quizDefinitionSlang();
                    break;
                case "11":
                    System.out.println("Exit");
                    exit = true;
                    break;
                default:
                    System.out.println("Please input a valid choice!");
                    break;
            }
            if (exit) {
                break;

            }
            Menu();
        }
    }

    public static void Menu() {
        System.out.println("-----------MENU------------");
        System.out.println("1. Search By Slang Word.");
        System.out.println("2. Search By Slang Definition.");
        System.out.println("3. Show History.");
        System.out.println("4. Add a Slang.");
        System.out.println("5. Edit a Slang.");
        System.out.println("6. Delete a Slang.");
        System.out.println("7. Reset Slang Word List To Default.");
        System.out.println("8. Random Slang.");
        System.out.println("9. Quiz Game - Guess the Slang.");
        System.out.println("10. Quiz Game - Guess the Definition.");
        System.out.println("11. Exit Program!");
        System.out.println("---------------------------");
        System.out.print("Please make a choice: ");
    }
}
