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
import java.util.*;

public class Slangdict {

    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        String choice = null;
        boolean exit = false;
        Controller controller = new Controller();
        Menu();
        
        while (true) {
            choice = sc.nextLine();
            switch (choice) {
                case "1":
                    controller.findBySlang();
                    break;
                case "2":
                    controller.findByDef();
                    break;
                case "3":
                    controller.showHistory();
                    break;
                case "4":
                    controller.addSlang();
                    break;
                case "5":
                    controller.edit();
                    break;
                case "6":
                    controller.delete();
                    break;
                case "7":
                    controller.reset();
                    break;
                case "8":
                    controller.random();
                    break;
                case "9":
                    controller.quizSlangDefinition();
                    break;
                case "10":
                    controller.quizDefinitionSlang();
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
