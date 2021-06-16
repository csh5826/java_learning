import java.util.Arrays;
import java.util.Scanner;

public class Hangman {

    public static String[] words = {"ant", "baboon", "badger", "bat", "bear", "beaver", "camel",
    "cat", "clam", "cobra", "cougar", "coyote", "crow", "deer",
    "dog", "donkey", "duck", "eagle", "ferret", "fox", "frog", "goat",
    "goose", "hawk", "lion", "lizard", "llama", "mole", "monkey", "moose",
    "mouse", "mule", "newt", "otter", "owl", "panda", "parrot", "pigeon", 
    "python", "rabbit", "ram", "rat", "raven","rhino", "salmon", "seal",
    "shark", "sheep", "skunk", "sloth", "snake", "spider", "stork", "swan",
    "tiger", "toad", "trout", "turkey", "turtle", "weasel", "whale", "wolf",
    "wombat", "zebra"};

    public static String[] gallows = {"+---+\n" +
    "|   |\n" +
    "    |\n" +
    "    |\n" +
    "    |\n" +
    "    |\n" +
    "=========\n",

    "+---+\n" +
    "|   |\n" +
    "O   |\n" +
    "    |\n" +
    "    |\n" +
    "    |\n" +
    "=========\n",

    "+---+\n" +
    "|   |\n" +
    "O   |\n" +
    "|   |\n" +
    "    |\n" +
    "    |\n" +
    "=========\n",

    " +---+\n" +
    " |   |\n" +
    " O   |\n" +
    "/|   |\n" +
    "     |\n" +
    "     |\n" +
    " =========\n",

    " +---+\n" +
    " |   |\n" +
    " O   |\n" +
    "/|\\  |\n" + 
    "     |\n" +
    "     |\n" +
    " =========\n",

    " +---+\n" +
    " |   |\n" +
    " O   |\n" +
    "/|\\  |\n" +
    "/    |\n" +
    "     |\n" +
    " =========\n",

    " +---+\n" +
    " |   |\n" +
    " O   |\n" +
    "/|\\  |\n" + 
    "/ \\  |\n" +
    "     |\n" +
    " =========\n"};

    static Scanner scan = new Scanner(System.in);

    public static boolean contains(char c, char[] array){
            for (char x : array) {
                if (x == c){
                    return true;
                }
            }
            return false;
    }

    // returns a random word from the array passed in 
    public static String randomWord(String[] stringArray){
        double randomNumber = Math.random() * stringArray.length;
        return stringArray[(int)randomNumber];
    };
    // cast the random word to an array of characters 
    public static char[] castWord(String randomWord){
        char[] something = new char[randomWord.length()];
        for(int i=0;i<randomWord.length();i++){
            something[i]='_';
        }
        return something;
    }

    public static void printGame(char[] word, int wrongIndex, char[] missArray){
        System.out.print(gallows[wrongIndex]); 
        System.out.print("\n");
        System.out.print("Word: ");
        for(int i=0;i<word.length;i++){
            System.out.print(" ");
            System.out.print(word[i]);
      }
      System.out.print("\nMisses: ");
      System.out.print(missArray);
      System.out.print("\nGuess: ");
    }

    public static void checkWin(char[] realWord, char[] cast, String word){
        if(Arrays.equals(realWord,cast)){
            System.out.println("You win! The word was " + word);
            System.exit(0);
        }
    }

    public static boolean checkGuess(char aGuess, char[] cast, char[] realWord, char[] missArray, int wrongIndex, String word){
        boolean goodGuess = true;
        for(int i=0;i<realWord.length;i++){
            if(aGuess == realWord[i]){
                cast[i]=aGuess;
                checkWin(realWord, cast, word);
                goodGuess = true;
                return goodGuess;
            }
            else if (aGuess != realWord[i] && !contains(aGuess, realWord)){
                missArray[wrongIndex]=aGuess; 
                System.out.println("Error!");
                goodGuess = false;
                return goodGuess;
            }
        }
        
        for(int i=0;i<cast.length;i++){
            int count = 0;
            if(cast[i] != '_'){
                break;
            }
            else {
                count++;
                if (count == cast.length){
                    missArray[i] = aGuess;
                    goodGuess = false;
                    return goodGuess;
                }
            }
        }
        return goodGuess;
    }

    public static void main(String[] args) {
        String word = randomWord(words);
        //parse through the word to put each letter in an array
        char[] wordArray = new char[word.length()];
        for(int i=0;i<word.length();i++){  
            wordArray[i]=word.charAt(i); 
        }
        char[] misses = new char[6];
        int wrong = 0;
        char[] castedWord = castWord(word); 
        while(wrong < 6 || !Arrays.equals(castedWord, wordArray)){
            printGame(castedWord, wrong, misses);
            char guess = scan.next().charAt(0);
            boolean aGoodGuess = checkGuess(guess, castedWord, wordArray, misses, wrong, word);
            if (aGoodGuess == false){
                wrong++;
                if(wrong == 6){
                    System.out.println("RIP. The word was " + word);
                    System.exit(0);
                }
            }
        }    
    }

}







