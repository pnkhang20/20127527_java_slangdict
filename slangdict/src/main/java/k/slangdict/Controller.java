/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package k.slangdict;

import java.util.*;

/**
 *
 * @author PHAM NGUYEN KHANG - 20127527 JAVA - KTPM2
 *
 */
public class Controller {

    public static Scanner scanner = new Scanner(System.in);
    Words words = new Words();
    public String[][] S = null;

    public void findBySlang() {
        String word;
        System.out.print("\nInput a Slang to find: ");
        try {
            word = scanner.nextLine();
            S = words.getWord(word);
            if (S != null) {
                for (int i = 0; i < S.length; i++) {
                    System.out.println("Slang: " + word);
                    System.out.println("Meaning: " + S[i][2] + "\n");
                    words.saveHistory(S[i][1], S[i][2]);
                }
            } else {
                System.out.println("Slang not found!\n");
            }
        } catch (Exception ex) {
            System.out.print("Exeption! Wrong input, please retry!");
        }
    }

    public void findByDef() {
        String word;
        Words slangword = new Words();
        System.out.print("What word definition do want to find: ");
        try {
            word = scanner.nextLine();
            S = slangword.findDefinition(word);
            if (S != null) {  
                for (String[] S1 : S) {
                    System.out.print("Result: ");
                    System.out.println(S1[2]);
                    words.saveHistory(S1[1], S1[2]);
                }
            } else {
                System.out.println("Not found");
            }
        } catch (Exception ex) {
            System.out.print("Exeption ! Please input retry ");
        }

    }

    public void showHistory() {
        S = words.readHistory();
        System.out.println("STT" + "   " + "Slang" + "   " + "Word");
        for (int i = 0; i < S.length; i++) {
            System.out.print(S[i][0] + "      " + S[i][1] + "      " + S[i][2]);
            System.out.println();
        }
    }

    public void addSlang() {
        String SlangWord;
        String Definition;
        String input;
        try {
            do {
                System.out.print("New Slang: ");
                SlangWord = scanner.nextLine();
                System.out.print("Definition for: " + SlangWord);
                Definition = scanner.nextLine();
                if (SlangWord.isEmpty() || Definition.isEmpty()) {
                    System.out.println("Please input !");
                }
            } while (SlangWord.isEmpty() || Definition.isEmpty());
            if (words.checkSlang(SlangWord)) {
                System.out.print("Slang allready exists. Do you want Overwrite or Dupilicate ? Please 1: Overwrite or 2: Dupilicate: ");
                input = scanner.nextLine();
                if ("1".equals(input)) {
                    words.Overwrite(SlangWord, Definition);
                    System.out.println("Done");
                } else {
                    words.Duplicate(SlangWord, Definition);
                    System.out.println("Done");
                }
            } else {
                words.addNew(SlangWord, Definition);
                System.out.println("Done");
            }

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    public void edit() {
        String word;
        String newValue;
        System.out.print("What slang do want to edit: ");
        try {
            word = scanner.nextLine();
            S = words.getWord(word);
            if (S != null) {
                for (int i = 0; i < S.length; i++) {
                    System.out.print("Result: " + S[i][2]);
                }
                System.out.print("Do want to edit: ");
                newValue = scanner.nextLine();
                words.update(word, S[0][2], newValue);
                System.out.println("Update done");
            } else {
                System.out.println("Not found");
            }
        } catch (Exception ex) {
            System.out.print("Error: " + ex);
        }
    }

    public void delete() {
        String word;
        //String slang;
        System.out.print("Input a Slang to Delete: ");
        try {
            word = scanner.nextLine();
            words.delete(word);

        } catch (Exception ex) {
            System.out.println("Not found");
        }
    }

    public void random() {
        String[] rand = null;
        rand = words.random();
        System.out.println("\nOn this day Slang Word!");
        System.out.println("Slang: " + rand[0] + "  " + "\nDefinition: " + rand[1] + "\n");
    }

    public void quizSlangDefinition() {
        String[] rand = null;
        String answer;
        String array[] = {"A", "B", "C", "D"};
        rand = words.quizDefineWords();
        System.out.println("What is the Slang for: " + rand[0] + "?");
        System.out.println(array[0] + ". " + rand[1]);
        System.out.println(array[1] + ". " + rand[2]);
        System.out.println(array[2] + ". " + rand[3]);
        System.out.println(array[3] + ". " + rand[4]);
        System.out.print("Input your final answer: ");
        answer = scanner.nextLine();

        if (rand[1].equals(rand[5]) && answer.equalsIgnoreCase(array[0])) {
            System.out.println("Woohoo! Your answer is correct!\n");
        } else if (rand[2].equals(rand[5]) && answer.equalsIgnoreCase(array[1])) {
            System.out.println("Woohoo! Your answer is correct!\n");
        } else if (rand[3].equals(rand[5]) && answer.equalsIgnoreCase(array[2])) {
            System.out.println("Woohoo! Your answer is correct!\n");
        } else if (rand[4].equals(rand[5]) && answer.equalsIgnoreCase(array[3])) {
            System.out.println("Woohoo! Your answer is correct!\n");
        } else {
            System.out.println("Oops, wrong answer! Better luck next time!\n");
        }
    }

    public void quizDefinitionSlang() {
        String answer;
        String[] rand = null;
        String array[] = {"A", "B", "C", "D"};
        rand = words.quizRandomWords();
        System.out.println("What is the definition for this Slang? " + rand[0]);
        System.out.println(array[0] + ". " + rand[1]);
        System.out.println(array[1] + ". " + rand[2]);
        System.out.println(array[2] + ". " + rand[3]);
        System.out.println(array[3] + ". " + rand[4]);
        System.out.print("Input your final answer: ");
        answer = scanner.nextLine();
        if (rand[1].equals(rand[5]) && answer.equalsIgnoreCase(array[0])) {
            System.out.println("Woohoo! Your answer is correct!");
        } else if (rand[2].equals(rand[5]) && answer.equalsIgnoreCase(array[1])) {
            System.out.println("Woohoo! Your answer is correct!");
        } else if (rand[3].equals(rand[5]) && answer.equalsIgnoreCase(array[2])) {
            System.out.println("Woohoo! Your answer is correct!");
        } else if (rand[4].equals(rand[5]) && answer.equalsIgnoreCase(array[3])) {
            System.out.println("Woohoo! Your answer is correct!");
        } else {
            System.out.println("Oops, wrong answer! Better luck next time!");
        }
    }
}
