
package k.slangdict;

import java.util.*;
import java.io.*;

/**
 *
 * @author kp
 */
public final class Words {

    private HashMap<String, ArrayList<String>> dict;
    private String ORIGINAL = "slang.txt";
    private String LOAD = "slang-default.txt";
    private String HISTORY = "history.txt";

    public Words() {
        try {

            String filePath = new java.io.File(".").getCanonicalPath();
            ORIGINAL = filePath + "/" + ORIGINAL;
            LOAD = filePath + "/" + LOAD;
            HISTORY = filePath + "/" + HISTORY;
            dict = new HashMap<>();
            readFile(ORIGINAL);
        } catch (Exception e) {
            System.out.println("File Not Found!");
        }
    }

    public void readFile(String fileName) throws Exception {

        String slang = null;
        ArrayList<String> definition = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(ORIGINAL));
        String line = null;
        try {
            while ((line = br.readLine()) != null) {
                String parts[] = line.split("`");
                slang = parts[0];
                //System.out.println(slang);
                definition = new ArrayList<>(Arrays.asList(parts[1].split("\\|")));
                //System.out.println(definition);
                dict.put(slang, definition);
            }
        } catch (IOException e) {
            System.out.println("File Not Found!");
        }
        br.close();
    }

    public void findBySlang() {
        String slangToFind;
        Scanner sc = new Scanner(System.in);
        System.out.print("\nEnter a Slang to find: ");
        slangToFind = sc.nextLine();
//        long startTime = System.nanoTime();

        ArrayList<String> meanings = dict.get(slangToFind);
        try {
            if (meanings != null) {
                System.out.println("\nSlang: " + slangToFind);
                String def = processDefinitions(slangToFind);
                System.out.println("Meanings: " + def + "\n");
            } else {
                System.out.println("\nSlang not found!\n");
            }
            saveHistory(slangToFind);
//            long elapsedTime = System.nanoTime() - startTime;
//            System.out.println("Total execution time to create 1000K objects in Java in millis: " + elapsedTime / 10000000);
        } catch (IOException e) {
            System.out.println("Slang not found!");
//            long elapsedTime = System.nanoTime() - startTime;
//            System.out.println("Total execution time to create 1000K objects in Java in millis: " + elapsedTime / 10000000);
        }


    }

    public void findSlang(String slangToFind) {
        ArrayList<String> meanings = dict.get(slangToFind);
        try {
            if (meanings != null) {
                System.out.println(meanings);
            } else {
                System.out.println("Slang not found!");
            }
            saveHistory(slangToFind);
//            long elapsedTime = System.nanoTime() - startTime;
//            System.out.println("Total execution time to create 1000K objects in Java in millis: " + elapsedTime / 10000000);
        } catch (IOException e) {
            System.out.println("Slang not found!");
//            long elapsedTime = System.nanoTime() - startTime;
//            System.out.println("Total execution time to create 1000K objects in Java in millis: " + elapsedTime / 10000000);
        }
    }

    public void getWordDefinition(String slang, String sameDef) {
        ArrayList<String> definitions = dict.get(slang);
        for (String i : definitions) {
            if (i.toLowerCase().contains(sameDef)) {
                System.out.println("Slang: " + slang + " ||| Meanings: " + i);
            }
        }
    }

    public void findByDefinition() throws IOException {
        String definitionToFind;
        Scanner sc = new Scanner(System.in);

        System.out.print("\nEnter a Definition to find: ");
        definitionToFind = sc.nextLine();
        System.out.println();
        //long startTime = System.nanoTime();
        String slangList[] = dict.keySet().toArray(new String[0]);

        int count = 0;
        for (String slang : slangList) {
            ArrayList<String> meanings = dict.get(slang);
            for (String def : meanings) {
                //Get all definitions to be lower case
                String temp = def.toLowerCase();
                if (temp.contains(definitionToFind.toLowerCase())) {
                    count++;
                }
            }
        }
        if (count != 0) {
            for (String slang : slangList) {
                ArrayList<String> meanings = dict.get(slang);
                for (String def : meanings) {
                    //Get all definitions to be lower case
                    String temp = def.toLowerCase();
                    if (temp.contains(definitionToFind.toLowerCase())) {
                        getWordDefinition(slang, definitionToFind);
                        
                        saveHistory(slang);
                    }
                }
            }
            System.out.println("");
        } else {
            System.out.println("No slang found for desired definition!\n");
        }
//        long elapsedTime = System.nanoTime() - startTime;
//        System.out.println("Total execution time to create 1000K objects in Java in millis: "+ elapsedTime / 1000000);
    }

    public void saveHistory(String slang) throws IOException {
        ArrayList<String> definitions = dict.get(slang);
        try {
            FileWriter fr = new FileWriter(HISTORY, true);
            String def = slang + "`";
            if (definitions != null) {
                for (int i = 0; i < definitions.size(); i++) {
                    if (i != definitions.size() - 1) {
                        def = def + definitions.get(i) + "|";
                    } else {
                        def = def + definitions.get(i);
                    }
                }
                fr.write(def + "\n");

            }
            fr.close();
        } catch (IOException e) {
            System.out.println("File not found!");
        }
    }

    public void viewHistory() throws Exception {
        String slang = null;
        ArrayList<String> definitions = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(HISTORY));
        String line = null;
        try {
            int number = 1;
            System.out.println("\nSlang Search History!");
            System.out.println("No. \t\t Slang \t\t Meaning");
            while ((line = br.readLine()) != null) {
                String res = "";
                String no = Integer.toString(number);
                String parts[] = line.split("`");
                slang = parts[0];
                definitions = dict.get(slang);
                if (definitions != null) {
                    for (int i = 0; i < definitions.size(); i++) {
                        if (i != definitions.size() - 1) {
                            res = res + definitions.get(i) + "|";
                        } else {
                            res = res + definitions.get(i);
                        }
                    }
                    System.out.println(no + "\t\t" + slang + "\t\t" + res);
                    number++;
                }
            }
            System.out.println("\n");
        } catch (IOException e) {
            System.out.println("File Not Found!");
        }
        br.close();
    }

    public boolean checkSlang(String slang) {
        for (String keyIro : dict.keySet()) {
            if (keyIro.equals(slang)) {
                return true;
            }
        }
        return false;
    }

    public void addSlang() {
        Scanner sc = new Scanner(System.in);
        String newSlang;
        String newDefinition;
        int choice = 0;
        try {
            do {
                System.out.print("Input a new Slang to add: ");
                newSlang = sc.nextLine();
                System.out.print("Input the definition of your new Slang: ");
                newDefinition = sc.nextLine();
                if (newSlang.isEmpty() || newDefinition.isEmpty()) {
                    System.out.println("Slang and definition should not be empty! Try again!");
                }
            } while (newSlang.isEmpty() || newDefinition.isEmpty());

            if (checkSlang(newSlang) == true) {
                System.out.print("Slang already exists! Do you want to Overwrite or Dupilicate?\n Input 1 for Overwrite.\n Input 2 for Dupilicate\n Your choice: ");
                choice = sc.nextInt();
                switch (choice) {
                    case 1:
                        dict.remove(newSlang);
                        ArrayList<String> newDef = new ArrayList<>();
                        newDef.add(newDefinition);
                        dict.put(newSlang, newDef);
                        break;
                    case 2:
                        ArrayList<String> definitions = dict.get(newSlang);
                        definitions.add(newDefinition);
                        break;
                    default:
                        System.out.println("Wrong input! Exiting the add menu! Please try again later!");
                        break;
                }
            } else {
                ArrayList<String> newDef = new ArrayList<>();
                newDef.add(newDefinition);
                dict.put(newSlang, newDef);
            }
            save(ORIGINAL);

        } catch (FileNotFoundException e) {
            System.out.println("Error");
        }

    }

    public void edit() {
        Scanner sc = new Scanner(System.in);
        String slang;
        String newDefinition;
        try {
            System.out.print("\nInput the Slang you want to edit: ");
            slang = sc.nextLine();
            if (checkSlang(slang) == true) {

                System.out.print("Input the new meaning for this Slang: ");
                newDefinition = sc.nextLine();
                dict.remove(slang);
                ArrayList<String> newDef = new ArrayList<>();
                newDef.add(newDefinition);
                dict.put(slang, newDef);
                System.out.println("New meaning successfully update for the Slang!\n");
            } else {
                System.out.println("Slang not found!\n");
            }
            save(ORIGINAL);
        } catch (FileNotFoundException ex) {
            System.out.print("Can't update to the file!");
        }
    }

    public void delete() throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);
        System.out.print("\nPlease enter a Slang to delete: ");
        String slang = sc.nextLine();
        ArrayList<String> definitions = dict.get(slang);
        if (definitions == null) {
            System.out.println("\nSlang Not Found!");
        } else {
            String choice = "";
            System.out.println("Are you sure you want to delete? (y/n)");
            System.out.print("Your choice: ");
            choice = sc.nextLine();
            if (choice.equalsIgnoreCase("y")) {
                dict.remove(slang);
                System.out.println("Slang Successfully Deleted!");
            } else if (choice.equalsIgnoreCase("n")) {
                System.out.println("Exit deleting mode!");
            }
        }
        save(ORIGINAL);
    }

    public void save(String file) throws FileNotFoundException {
        try {
            PrintWriter printWriter = new PrintWriter(new File(file));
            StringBuilder stringBuilder = new StringBuilder();
            String def = "";

            for (String slang : dict.keySet()) {
                //System.out.println(slang);
                stringBuilder.append(slang).append("`");
                def = processDefinitions(slang);
                stringBuilder.append(def).append("\n");
            }

            printWriter.write(stringBuilder.toString());
            printWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("Failed to save file!" + e);
        }
    }

    public String processDefinitions(String slang) {
        ArrayList<String> definitions = dict.get(slang);
        String def = "";
        for (int i = 0; i < definitions.size(); i++) {
            if (i != definitions.size() - 1) {
                def = def + definitions.get(i) + "|";
            } else {
                def = def + definitions.get(i);
            }
        }
        return def;
    }

    public void printAll() {
        String slangList[] = dict.keySet().toArray(new String[0]);
        for (String key : slangList) {
            String def = processDefinitions(key);
            System.out.println(key + "`" + def);
        }
    }

    public String[] random() {
        int minimum = 0;
        int maximum = dict.size() - 1;
        int rand = randInt(minimum, maximum);
        // Get slang meaning
        String s[] = new String[2];
        int index = 0;
        for (String key : dict.keySet()) {
            if (index == rand) {
                s[0] = key;
                s[1] = dict.get(key).get(0);
                break;
            }
            index++;
        }
        return s;
    }

    public void randomSlang() {
        int minimum = 0;
        int maximum = dict.size() - 1;
        int rand = randInt(minimum, maximum);
        // Get slang meaning
        String s[] = new String[2];
        int index = 0;
        for (String key : dict.keySet()) {
            if (index == rand) {
                s[0] = key;
                s[1] = dict.get(key).get(0);
                break;
            }
            index++;
        }
        String slang = s[0];
        String def = s[1];
        System.out.println("\nOn this day slang!!");
        System.out.println("Slang: " + slang);
        System.out.println("Meaning: " + def);
        System.out.println("\n");
    }

    public static int randInt(int minimum, int maximum) {
        return (minimum + (int) (Math.random() * maximum));
    }

    public String[] quizRandomWords() {
        String s[] = new String[6];
        String[] slangRandom = this.random();
        s[0] = slangRandom[0];
        int rand = randInt(1, 4);
        s[rand] = slangRandom[1];
        s[5] = slangRandom[1];
        for (int i = 1; i <= 4; i++) {
            if (rand == i) {
                continue;
            } else {
                String[] slangRand = this.random();
                while (slangRand[0] == null ? s[0] == null : slangRand[0].equals(s[0])) {
                    slangRand = this.random();
                }
                s[i] = slangRand[1];
            }
        }
        return s;
    }

    public String[] quizDefineWords() {
        String s[] = new String[6];
        String[] slangRandom = this.random();
        s[0] = slangRandom[1];
        int rand = randInt(1, 4);
        s[rand] = slangRandom[0];
        s[5] = slangRandom[0];
        for (int i = 1; i <= 4; i++) {
            if (rand == i) {
                continue;
            } else {
                String[] slangRand = this.random();
                while (slangRand[0] == null ? s[0] == null : slangRand[0].equals(s[0])) {
                    slangRand = this.random();
                }
                s[i] = slangRand[0];
            }
        }
        return s;
    }

    public void quizSlangDefinition() {
        Scanner sc = new Scanner(System.in);
        String[] rand = null;
        String answer;
        String array[] = {"A", "B", "C", "D"};

        rand = quizDefineWords();
        System.out.println("\nWhat is the Slang for: " + rand[0] + "?");
        System.out.println(array[0] + ". " + rand[1]);
        System.out.println(array[1] + ". " + rand[2]);
        System.out.println(array[2] + ". " + rand[3]);
        System.out.println(array[3] + ". " + rand[4]);
        System.out.print("Input your final answer: ");
        answer = sc.nextLine();

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
        Scanner sc = new Scanner(System.in);
        String answer;
        String[] rand = null;
        String array[] = {"A", "B", "C", "D"};

        rand = quizRandomWords();
        System.out.println("\nWhat is the definition for this Slang? " + rand[0]);
        System.out.println(array[0] + ". " + rand[1]);
        System.out.println(array[1] + ". " + rand[2]);
        System.out.println(array[2] + ". " + rand[3]);
        System.out.println(array[3] + ". " + rand[4]);
        System.out.print("Input your final answer: ");
        answer = sc.nextLine();
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

    public void resetDefault() throws FileNotFoundException, IOException {
        BufferedReader br = new BufferedReader(new FileReader(LOAD));
        PrintWriter printWriter = new PrintWriter(new File(ORIGINAL));
        PrintWriter printWriter2 = new PrintWriter(new File(HISTORY));
        StringBuilder stringBuilder = new StringBuilder();
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append("");
        String line = null;
        try {
            while ((line = br.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
            br.close();
            printWriter.write(stringBuilder.toString());
            printWriter2.write(stringBuilder2.toString());
            System.out.println("\nSuccessfuly Reset File to Factory!");
            System.out.println("The History File have Also been Reseted to Factory!\n");
            printWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found!\n");
        }

    }


}
