import java.util.Scanner;

public class JavaHangman {
    private String[] words;
    private String[] hints;
    private int score;
    private int attempts;
    private int initial = 0;
    private int points;
    private Scanner scanner;
    private String currentWord;
    private char[] guessedLetters;
    public JavaHangman(int score, int points, int attempts) {
        words = new String[]{
                "variable",
                "method",
                "class",
                "object",
                "inheritance",
                "polymorphism",
                "interface",
                "constructor",
                "array",
                "loop",
                "recursion",
                "string",
                "parameter",
                "static",
                "overloading",
                "overriding",
                "casting",
                "abstraction",
                "package",
                "constructor",
                "encapsulation",
                "conditional",
                "interface",
                "exception",
                "boolean",
                "byte",
                "double",
                "final"
        };

        hints = new String[]{
                "A container that holds a value.",
                "A set of code which performs a specific task.",
                "A blueprint or template for creating objects.",
                "An instance of a class.",
                "The mechanism of creating a new class from an existing class.",
                "The ability of an object to take on many forms.",
                "A contract specifying a set of methods that a class must implement.",
                "A special method used to initialize objects.",
                "A data structure that stores multiple values of the same type.",
                "A control structure for executing a block of code repeatedly.",
                "A function that calls itself.",
                "A sequence of characters.",
                "A value passed to a method.",
                "A keyword used to create variables and methods that belong to a class.",
                "Having multiple methods with the same name but different parameters.",
                "Replacing a method in a superclass with a new implementation in a subclass.",
                "Converting an object of one type to another type.",
                "exposing only necessary information and hiding the implementation details.",
                "A way to organize classes and interfaces into groups.",
                "A special method used to initialize objects.",
                "A mechanism of bundling data and methods together.",
                "A control structure for making decisions based on conditions.",
                "A contract specifying a set of methods that a class must implement.",
                "An event that occurs during the execution of a program.",
                " only a true or false value.",
                "A sequence of eight bits. Java provides a corresponding byte type",
                "A Java keyword used to define a variable of type double.",
                "You define an entity once and cannot change it or derive from it later."

        };


        this.score = score;
        this.points = points;
        this.attempts = attempts;
        scanner = new Scanner(System.in);
    }

    public void play() {
        int x = attempts;
        while (initial < score) {
            // Select a random word from the array
            int randomIndex = (int) (Math.random() * words.length);
            currentWord = words[randomIndex];
            String hint = hints[randomIndex];

            guessedLetters = new char[currentWord.length()]; // Array to store the correctly guessed letters
            for (int i = 0; i < guessedLetters.length; i++) {
                guessedLetters[i] = '_'; // Initialize the guessed letters array with underscores
            }

            while (attempts > 0) {
                displayGameStatus();

                System.out.print("Guess a letter: ");
                char guess = scanner.nextLine().charAt(0); // Read the user's guess

                makeGuess(guess);

                if (isWordGuessed()) {
                    System.out.println("Congratulations! You guessed the word: " + currentWord);
                    initial += points; // Add points for guessing the word correctly
                    break;
                } else if (isGameOver()) {
                    System.out.println("Game over! The word was: " + currentWord);
                    initial -= points; // Deduct points for using up all attempts
                    break;
                }
            }

            System.out.println("Current score: " + initial);

            // Check if the player has reached the target score
            if (initial >= score) {
                System.out.println("Congratulations! You reached a score of " + score + " or more!");
                break;
            }

            attempts = x; // Reset the number of attempts for the next round
        }

        scanner.close();
    }

    public boolean isGameOver() {
        return attempts == 0;
    }

    public void makeGuess(char guess) {
        boolean correctGuess = false;

        // Check if the guessed letter matches any character in the word
        for (int i = 0; i < currentWord.length(); i++) {
            if (currentWord.charAt(i) == guess) {
                guessedLetters[i] = guess; // Update the guessed letters array
                correctGuess = true;
            }
        }

        if (correctGuess) {
            System.out.println("Correct guess!");
        } else {
            attempts--;
            System.out.println("Incorrect guess!");
        }
    }

    public boolean isWordGuessed() {
        return new String(guessedLetters).equals(currentWord);
    }

    public String getCurrentWord() {
        return currentWord;
    }

    public void displayGameStatus() {
        System.out.println("Word: " + new String(guessedLetters));
        System.out.println("Hint: " + getHintForCurrentWord());
        System.out.println("Attempts remaining: " + attempts);
    }

    public String getHintForCurrentWord() {
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(currentWord)) {
                return hints[i];
            }
        }
        return "";
    }
}

