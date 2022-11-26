/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package k.slangdict;

/**
 *
 * @author PHAM NGUYEN KHANG - 20127527 JAVA - KTPM2
 *
 */


import java.util.*;
import java.io.*;
import java.util.Map.Entry;

public final class Words {

    private TreeMap<String, List<String>> map = new TreeMap<>();
    
    private int size;
    private String ORIGINAL = "slang.txt";
    private String LOAD = "slang-default.txt";
    private String HISTORY = "history.txt";

    public Words() {
        try {
            String filePath = new java.io.File(".").getCanonicalPath();
            ORIGINAL = filePath + "/" + ORIGINAL;
            LOAD = filePath + "/" + LOAD;
            HISTORY = filePath + "/" + HISTORY;
            readFile(ORIGINAL);
        } catch (Exception e) {
            System.out.println("File Not Found!");
        }
    }

    void saveFile(String file) {
        try {
            PrintWriter printWriter = new PrintWriter(new File(file));
            StringBuilder stringBuilder = new StringBuilder();

            stringBuilder.append("Slag`Meaning\n");
            String s[][] = new String[map.size()][3];
            Set<String> keySet = map.keySet();
            Object[] keyArray = keySet.toArray();
            for (int i = 0; i < map.size(); i++) {
                Integer in = i + 1;
                s[i][0] = in.toString();
                s[i][1] = (String) keyArray[i];
                List<String> meaning = map.get(keyArray[i]);
                stringBuilder.append(s[i][1]).append("`").append(meaning.get(0));
                for (int j = 1; j < meaning.size(); j++) {
                    stringBuilder.append("|").append(meaning.get(j));
                }
                stringBuilder.append("\n");
            }
            printWriter.write(stringBuilder.toString());
            printWriter.close();

        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
    }

    void readFile(String file) throws Exception {
        map.clear();
        String slang = null;
        Scanner scanner = new Scanner(new File(file));
        scanner.useDelimiter("`");
        scanner.next();
        String temp = scanner.next();
        String[] part = temp.split("\n");
        int i = 0;
        size = 0;
        while (scanner.hasNext()) {
            List<String> word = new ArrayList<>();
            slang = part[1].trim();
            temp = scanner.next();
            part = temp.split("\n");
            if (map.containsKey(slang)) {
                word = map.get(slang);
            }
            if (part[0].contains("|")) {
                String[] d = (part[0]).split("\\|");
                for (int j = 0; j < d.length; j++) {
                    Collections.addAll(word, d);
                }
                size += d.length - 1;
            } else {
                word.add(part[0]);
            }
            map.put(slang, word);
            i++;
            size++;
        }
        scanner.close();
    }

    public String[][] getData() {
        String s[][] = new String[size][3];
        Set<String> slangListSet = map.keySet();
        Object[] slangList = slangListSet.toArray();
        int index = 0;
        for (int i = 0; i < size; i++) {
            s[i][0] = String.valueOf(i);
            s[i][1] = (String) slangList[index];
            List<String> word = map.get(slangList[index]);
            s[i][2] = word.get(0);
            System.out.println(s[i][0] + "\t" + s[i][1] + "\t" + s[i][2]);
            for (int j = 1; j < word.size(); j++) {
                if (i < size) {
                    i++;
                }
                s[i][0] = String.valueOf(i);
                s[i][1] = (String) slangList[index];
                s[i][2] = word.get(j);
                System.out.println(s[i][0] + "\t" + s[i][1] + "\t" + s[i][2]);
            }
            index++;
        }
        return s;
    }

    public String[][] getWord(String key) {
        List<String> listWord = map.get(key);
        if (listWord == null) {
            return null;
        }
        int size = listWord.size();
        String s[][] = new String[size][3];
        for (int i = 0; i < size; i++) {
            s[i][0] = String.valueOf(i);
            s[i][1] = key;
            s[i][2] = listWord.get(i);
        }
        return s;
    }

    public String[][] findDefinition(String query) {
        // Get all slang contain key
        List<String> keyList = new ArrayList<>();
        List<String> meaningList = new ArrayList<>();
        for (Entry<String, List<String>> entry : map.entrySet()) {
            List<String> meaning = entry.getValue();
            for (int i = 0; i < meaning.size(); i++) {
                if (meaning.get(i).toLowerCase().contains(query.toLowerCase())) {
                    keyList.add(entry.getKey());
                    meaningList.add(meaning.get(i));
                }
            }
        }
        int size = keyList.size();
        String s[][] = new String[size][3];

        for (int i = 0; i < size; i++) {
            s[i][0] = String.valueOf(i);
            s[i][1] = keyList.get(i);
            s[i][2] = meaningList.get(i);
        }
        return s;
    }

    public void update(String slang, String oldValue, String newValue) {
        List<String> meaning = map.get(slang);
        int index = meaning.indexOf(oldValue);
        meaning.set(index, newValue);
        this.saveFile(ORIGINAL);
    }

    public void saveHistory(String slang, String word) throws Exception {
        // String file = "history.txt";
        File file1 = new File(HISTORY);
        FileWriter fr = new FileWriter(file1, true);
        fr.write(slang + "`" + word + "\n");
        fr.close();
    }

    public String[][] readHistory() {
        List<String> historySlag = new ArrayList<>();
        List<String> historyDefinition = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File(HISTORY));
            scanner.useDelimiter("`");
            String temp = scanner.next();
            String[] part = scanner.next().split("\n");
            historySlag.add(temp);
            historyDefinition.add(part[0]);
            while (scanner.hasNext()) {
                temp = part[1];
                part = scanner.next().split("\n");
                historySlag.add(temp);
                historyDefinition.add(part[0]);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
        }
        int size = historySlag.size();
        String s[][] = new String[size][3];
        for (int i = 0; i < size; i++) {
            s[size - i - 1][0] = String.valueOf(size - i);
            s[size - i - 1][1] = historySlag.get(i);
            s[size - i - 1][2] = historyDefinition.get(i);
        }
        return s;
    }

    public void reset() {
        try {
            readFile(LOAD);
            this.saveFile(ORIGINAL);
            System.out.println("Reset successfull");
        } catch (Exception e) {
            System.out.println("Reset fail: " + e);
        }
    }

    public void delete(String slang) {
        List<String> listMeaning = map.get(slang);
        if (listMeaning == null) {
            System.out.println("Slang Not Found!");
        } else {
            map.remove(slang);
            System.out.println("Slang Successfully Deleted!");
        }
        size--;
        this.saveFile(ORIGINAL);
    }

    public void addNew(String slang, String word) {
        List<String> meaningList = new ArrayList<>();
        meaningList.add(word);
        size++;
        map.put(slang, meaningList);
        this.saveFile(ORIGINAL);
    }

    public void Duplicate(String slang, String word) {
        List<String> meaningList = map.get(slang);
        meaningList.add(word);
        size++;
        map.put(slang, meaningList);
        this.saveFile(ORIGINAL);
    }

    public void Overwrite(String slang, String word) {
        List<String> meaningList = map.get(slang);
        meaningList.set(0, word);
        map.put(slang, meaningList);
        this.saveFile(ORIGINAL);
    }

    public boolean checkSlang(String slang) {
        for (String keyIro : map.keySet()) {
            if (keyIro.equals(slang)) {
                return true;
            }
        }
        return false;
    }

    public String[] random() {
        int minimum = 0;
        int maximum = map.size() - 1;
        int rand = randInt(minimum, maximum);
        // Get slang meaning
        String s[] = new String[2];
        int index = 0;
        for (String key : map.keySet()) {
            if (index == rand) {
                s[0] = key;
                s[1] = map.get(key).get(0);
                break;
            }
            index++;
        }
        return s;
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
}
