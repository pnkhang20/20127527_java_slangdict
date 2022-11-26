/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package k.slangdict;
import java.util.*;

/**
 *
 * @author kp
 */
public class Controller {
        public static Scanner scanner = new Scanner(System.in);
        Words words = new Words();
        public String[][] S = null;
        
        
        public void findBySlang() {
		String word;	
		System.out.print("What slang do want to find: ");
            try {
            	word = scanner.nextLine();
            	S = words.getWord(word);
        		if(S != null) {
        			for (int i = 0; i < S.length; i++) {
        				System.out.print("Result: ");
        				System.out.println(S[i][2]);
        				words.saveHistory(S[i][1], S[i][2]);
        			}
        		}
        		else {
        			System.out.println("Not found");
        		}
            } catch (Exception ex) {
                System.out.print("Exeption ! Please input retry ");
            }
        }
        
	public void findByDef() {
		String word;	
		System.out.print("What word definition do want to find: ");
            try {
            	word = scanner.nextLine();
            	S = words.findDefinition(word);
        		if(S != null) {
        			for (int i = 0; i < S.length; i++) {
        				System.out.print("Result: ");
        				System.out.println(S[i][2]);
        				words.saveHistory(S[i][1], S[i][2]);
        			}
        		}
        		else {
        			System.out.println("Not found");
        		}
            } catch (Exception ex) {
                System.out.print("Exeption ! Please input retry ");
            }
        }
        
    	public void showHistory() {
	    S = words.readHistory();
	    System.out.println("STT" +"   "+"Slang"+"   "+"Word");
		for(int i = 0 ; i < S.length ;i++) {
			System.out.print(S[i][0]+"      "+S[i][1]+"      "+S[i][2]);
			System.out.println();
		}
	}
        
        public void addSlang() {
		String SlangWord;	
		String Definition;
		String input;
            try {
            	do {
            		System.out.print("What is your new SlangWord: ");
                	SlangWord = scanner.nextLine();
                	System.out.print("What is your new Definition: ");
                	Definition = scanner.nextLine();
                	if(SlangWord.isEmpty() || Definition.isEmpty()) {
                		System.out.println("Please input !");
                	}
            	}while(SlangWord.isEmpty() || Definition.isEmpty());
            	if(words.checkSlang(SlangWord)) {
            		System.out.print("Slang allready exists. Do you want Overwrite or Dupilicate ? Please 1: Overwrite or 2: Dupilicate: ");
            		input = scanner.nextLine();
            		if( "1".equals(input)) {
            			words.addOverwrite(SlangWord, Definition);
            			System.out.println("Done");
            		}else {
            			words.addDuplicate(SlangWord, Definition);
            			System.out.println("Done");
            		}
            	}else {
            		words.addNew(SlangWord, Definition);
            		System.out.println("Done");
            	}
		
			}catch (Exception e) {
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
         	if(S != null) {
    			for (int i = 0; i < S.length; i++) {
    				System.out.print("Result: " + S[i][2]);
    			}
    			System.out.print("Do want to edit: ");
    			newValue = scanner.nextLine();
    			words.update(word,S[0][2], newValue);
    			System.out.println("Update done");
    		}
    		else {
    			System.out.println("Not found");
    		}
         } catch (Exception ex) {
             System.out.print("Error: "+ex);
         }
	}
        
    	public void delete() {
		String word;	
		//String slang;
		System.out.print("What slang do want delete: ");
		try {
			word = scanner.nextLine();
        	words.delete(word);
        	
        } catch (Exception ex) {
            System.out.println("Not found");
        }
	}  
        
    	public void random() {
                String [] rad = null;
		rad = words.random();
		System.out.println("Slang: " + rad[0]+"  "+"Word: " + rad[1]);
	}
        
	    public void quizSlangDefinition() {
                String [] rad = null;
                String answer;
	    	String array[] = {"A","B","C","D"};
	    	rad = words.quizDefineWords();
	    	System.out.println("What does this " +rad[0]+" mean ?");
	    	System.out.println(array[0]+". "+rad[1]);
	    	System.out.println(array[1]+". "+rad[2]);
	    	System.out.println(array[2]+". "+rad[3]);
	    	System.out.println(array[3]+". "+rad[4]);
	    	System.out.print("Your answer: ");
	   	    answer = scanner.nextLine();
	    	if (rad[1].equals(rad[5]) && answer.equals(array[0])) {
			   System.out.println("Your correct");
			} else if (rad[2].equals(rad[5]) && answer.equals(array[1])) {
				System.out.println("Your correct");
			} else if(rad[3].equals(rad[5]) && answer.equals(array[2])) {
				System.out.println("Your correct");
			}else if(rad[4].equals(rad[5]) && answer.equals(array[3])) {
				System.out.println("Your correct");
			}else {
				System.out.println("Your wrong");
			}
	    }
        public void quizDefinitionSlang() {
        String answer;
        String [] rad = null;
    	String array[] = {"A","B","C","D"};
    	rad = words.quizRandomWords();
    	System.out.println("What does this " +rad[0]+" mean ?");
    	System.out.println(array[0]+". "+rad[1]);
    	System.out.println(array[1]+". "+rad[2]);
    	System.out.println(array[2]+". "+rad[3]);
    	System.out.println(array[3]+". "+rad[4]);
    	System.out.print("Your answer: ");
   	answer = scanner.nextLine();
    	if (rad[1].equals(rad[5]) && answer.equals(array[0])) {
		   System.out.println("Your correct");
		} else if (rad[2].equals(rad[5]) && answer.equals(array[1])) {
			System.out.println("Your correct");
		} else if(rad[3].equals(rad[5]) && answer.equals(array[2])) {
			System.out.println("Your correct");
		}else if(rad[4].equals(rad[5]) && answer.equals(array[3])) {
			System.out.println("Your correct");
		}else {
			System.out.println("Your wrong");
		}
    }
}
