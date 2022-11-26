/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package k.slangdict;

/**
 *
 * @author kp
 */
import java.io.*;
import java.util.*;



public class Slangdict {

    public static Scanner scanner = new Scanner(System.in); 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
            String choose = null;
            boolean exit = false;
            Words slangword  = new Words();
            Controller controller = new Controller();
            showMenu();
            while(true) {
                    choose = scanner.nextLine();
                    switch(choose) {
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
                            slangword.reset();
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
                            exit=true;
                            break;
                    default:
                            System.out.println("Not a valid option! Please try again!");
                            break;
                    }
                    if(exit) {
                    break;
                }
                    showMenu();
            }
    }
           public static void showMenu() {
           System.out.println("-----------MENU------------");
           System.out.println("1. Search by SlangWord.");
           System.out.println("2. Search by Definition.");
           System.out.println("3. Show history.");
           System.out.println("4. Add SlangWord.");
           System.out.println("5. Edit SlangWord.");
           System.out.println("6. Delete SlangWord.");
           System.out.println("7. Reset SlangWord to default.");
           System.out.println("8. Random SlangWord.");
           System.out.println("9. Minigame show random SlangWord.");
           System.out.println("10. Minigame show defitinion SlangWord.");
           System.out.println("11. EXITS.");
           System.out.println("---------------------------");
           System.out.print("YOUR CHOSE: ");
       }
}
