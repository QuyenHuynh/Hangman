import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class main {

    static int remainingTries = 8;
    static boolean victory = false;
    static String randomWord = "";
    static ArrayList<String> correctGuessArray = new ArrayList<>();
    static ArrayList<String> incorrectGuessArray = new ArrayList<>();
    static String playAgain = "";
    static Boolean validPlayAgain = false;
    static Boolean validGuess = false;


    public static void main(String[] args) {
        startNewGame();
        resetGame();
    }

    public static void startNewGame() {
        generateNewWord();
        generateUnderscores(randomWord);

        while (remainingTries > 0 && !victory) {
            displayHangman();
            promptGuess();
        }

        if (remainingTries == 0 && !victory) {
            displayHangman();
            promptReplay();
        }
    }

    public static void promptReplay(){
        System.out.println("Would you like to play again? y/n");
        Scanner scan = new Scanner(System.in);
        playAgain = scan.nextLine();

        while (!validPlayAgain) {
            try {
                validPlayAgainCheck(playAgain);
            } catch (CustomException e) {
                playAgain = scan.nextLine();
            }
        }
    }

    static void validPlayAgainCheck(String playAgain) throws CustomException {
//        check for empty string
        if (playAgain != null && playAgain.length() > 0) {
            if (playAgain.equals("y") || playAgain.equals("Y")) {
                validPlayAgain = true;
                resetGame();
                startNewGame();
            } else if (playAgain.equals("n") || playAgain.equals("N")) {
                validPlayAgain = true;
                System.exit(0);
            } else {
//                throws an error if it's anything other than y/Y or n/N
                System.out.println("Not a valid answer. y/n");
                throw new CustomException();
            }
        } else {
            System.out.println("Input is blank. Would you like to play again? y/n");
            throw new CustomException();
        }
        validPlayAgain = true;
    }

    public static void compareGuesstoWord(String guess) {
        //check the char against the characters in the randomWord
        for (int i = 0; i <= randomWord.length() - 1 ; i++) {

            //if we find a match/matches, replace the underscores in the correctGuessArray
            if (String.valueOf(randomWord.charAt(i)).equals(guess)) {

                //using the index location, replace the underscores there with the guess
                correctGuessArray.set(i, guess);
            }
        }

        //if the guess is incorrect, then it's not in our correctGuess array
        //add it to our incorrectGuessArray if it's not already there
        if (!correctGuessArray.contains(guess) && !incorrectGuessArray.contains(guess)) {
            incorrectGuessArray.add(guess);

            //decrement the number of guesses left
            remainingTries--;
            System.out.println("Remaining Tries left:" + remainingTries);
        }

        //check for victory
        victoryCheck();
    }

    public static void victoryCheck() {
        if (remainingTries > 0 && !correctGuessArray.contains("_")) {
            victory = true;
            System.out.println("You win!");
            promptReplay();
        }
    }

    public static void promptGuess() {
        validGuess = false;
        System.out.println("Guess a letter:");
        Scanner scan = new Scanner(System.in);
        String userGuess = scan.nextLine();

        while (!validGuess) {
            try {
                validGuessCheck(userGuess);
            } catch (CustomException e) {
                userGuess = scan.nextLine();
            }
        }
        validGuess = true;
        compareGuesstoWord(userGuess);
    }

    public static void validGuessCheck(String guess) throws CustomException {
        //No numbers or special characters. Letters A-Z and a-z are fine.
        Pattern pattern = Pattern.compile("[^A-Za-z ]");
        Matcher matcher = pattern.matcher(guess);

        if (guess.isBlank()) {
            // String is blank
            System.out.println("The input is blank! Try again!");
            throw new CustomException();
        }
        if (matcher.find()) {
            // String contains special characters
            System.out.println("Not a valid input. Try again! Enter a letter from A to Z.");
            throw new CustomException();
        }
        validGuess = true;
    }
    static class CustomException extends Exception {
        public CustomException() {
            // Call constructor of parent Exception
            super();
        }
    }

    public static void resetGame() {
        remainingTries = 8;
        victory = false;
        validGuess = false;
        correctGuessArray.clear();
        incorrectGuessArray.clear();
        playAgain = "";
        validPlayAgain = false;
        startNewGame();
    }

    public static void generateNewWord() {
        ArrayList<String> wordDatabase = new ArrayList<>();
        wordDatabase.add("pasta");
        wordDatabase.add("cucumber");
        wordDatabase.add("crawfish");
        wordDatabase.add("strawberry");
        wordDatabase.add("candle");
        wordDatabase.add("daiquiri");
        wordDatabase.add("pumpkin");
        wordDatabase.add("oyster");
        wordDatabase.add("beach");
        wordDatabase.add("milk");
        wordDatabase.add("movie");
        wordDatabase.add("sunscreen");
        wordDatabase.add("summer");
        wordDatabase.add("garden");
        wordDatabase.add("midnight");
        wordDatabase.add("cabin");
        wordDatabase.add("witch");
        wordDatabase.add("fluffy");

        //generate random index and pull from our arrayList
        int index = (int) Math.floor(Math.random() * wordDatabase.size());
        randomWord = wordDatabase.get(index);
    }

    public static void generateUnderscores(String str) {
        //create an underscore for each character in the provided string
        for ( int i = 0; i <= str.length() - 1 ; i++) {
            correctGuessArray.add("_");
        }
    }

    public static void displayHangman() {

        switch (remainingTries) {
            case 8:
                System.out.println(
                        """
                                <<0>>-<<0>>-<<0>>-<<0>>-<<0>>-<<0>>-<<0>>-<<0>>
                                 |=|                                       |=|\s
                                 |=|                                       |=|\s
                                 |=|                                       |=|\s
                                 |=|                                       |=|\s
                                 |=|                                       |=|\s
                                 |=|                                       |=|\s
                                 |=|                                       |=|\s
                                 |=|                                       |=|\s
                                 |=|                                       |=|
                                 |=|                                       |=|
                                 |=|                                       |=|
                                 |=|                                       |=|
                                 |=|                                       |=|
                                 |=|                                       |=|
                                 |=|                                       |=|\s
                                 |=|                                       |=|\s
                                 |=|                                       |=|\s
                                 |=|                                       |=|\s
                                <<0>>-<<0>>-<<0>>-<<0>>-<<0>>-<<0>>-<<0>>-<<0>>""");
                System.out.println(correctGuessArray);
                System.out.println("Missed letters: " + incorrectGuessArray);
                break;
            case 7:
                System.out.println(
                        """
                                <<0>>-<<0>>-<<0>>-<<0>>-<<0>>-<<0>>-<<0>>-<<0>>
                                 |=|                                       |=|\s
                                 |=|        [=][=][=][=][=][=][=]{|}       |=|\s
                                 |=|        [=]         {|}      {|}       |=|\s
                                 |=|                      {|}    {|}       |=|\s
                                 |=|                        {|}  {|}       |=|\s
                                 |=|                          {|}{|}       |=|\s
                                 |=|                             {|}       |=|\s
                                 |=|                             {|}       |=|\s
                                 |=|                             {|}       |=|
                                 |=|                             {|}       |=|
                                 |=|                             {|}       |=|
                                 |=|                             {|}       |=|
                                 |=|                             {|}       |=|
                                 |=|                             {|}       |=|
                                 |=|                             {|}       |=|\s
                                 |=|                             {|}       |=|\s
                                 |=|  [=][=][=][=][=][=][=][=][=]{|}[=]    |=|\s
                                 |=|                                       |=|\s
                                <<0>>-<<0>>-<<0>>-<<0>>-<<0>>-<<0>>-<<0>>-<<0>>""");
                System.out.println(correctGuessArray);
                System.out.println("Missed letters: " + incorrectGuessArray);
                break;
            case 6:
                System.out.println(
                        """
                                <<0>>-<<0>>-<<0>>-<<0>>-<<0>>-<<0>>-<<0>>-<<0>>
                                 |=|                                       |=|\s
                                 |=|        [=][=][=][=][=][=][=]{|}       |=|\s
                                 |=|        [=]         {|}      {|}       |=|\s
                                 |=|                      {|}    {|}       |=|\s
                                 |=|                        {|}  {|}       |=|\s
                                 |=|                          {|}{|}       |=|\s
                                 |=|                             {|}       |=|\s
                                 |=|         (T T)               {|}       |=|\s
                                 |=|                             {|}       |=|
                                 |=|                             {|}       |=|
                                 |=|                             {|}       |=|
                                 |=|                             {|}       |=|
                                 |=|                             {|}       |=|
                                 |=|                             {|}       |=|
                                 |=|                             {|}       |=|\s
                                 |=|                             {|}       |=|\s
                                 |=|  [=][=][=][=][=][=][=][=][=]{|}[=]    |=|\s
                                 |=|                                       |=|\s
                                <<0>>-<<0>>-<<0>>-<<0>>-<<0>>-<<0>>-<<0>>-<<0>>""");
                System.out.println(correctGuessArray);
                System.out.println("Missed letters: " + incorrectGuessArray);
                break;
            case 5:
                System.out.println(
                        """
                                <<0>>-<<0>>-<<0>>-<<0>>-<<0>>-<<0>>-<<0>>-<<0>>
                                 |=|                                       |=|\s
                                 |=|        [=][=][=][=][=][=][=]{|}       |=|\s
                                 |=|        [=]         {|}      {|}       |=|\s
                                 |=|                      {|}    {|}       |=|\s
                                 |=|                        {|}  {|}       |=|\s
                                 |=|                          {|}{|}       |=|\s
                                 |=|                             {|}       |=|\s
                                 |=|         (T T)               {|}       |=|\s
                                 |=|          |||                {|}       |=|
                                 |=|          |||                {|}       |=|
                                 |=|          |||                {|}       |=|
                                 |=|          |||                {|}       |=|
                                 |=|          |||                {|}       |=|
                                 |=|                             {|}       |=|
                                 |=|                             {|}       |=|\s
                                 |=|                             {|}       |=|\s
                                 |=|  [=][=][=][=][=][=][=][=][=]{|}[=]    |=|\s
                                 |=|                                       |=|\s
                                <<0>>-<<0>>-<<0>>-<<0>>-<<0>>-<<0>>-<<0>>-<<0>>""");
                System.out.println(correctGuessArray);
                System.out.println("Missed letters: " + incorrectGuessArray);
                break;
            case 4:
                System.out.println(
                        """
                                <<0>>-<<0>>-<<0>>-<<0>>-<<0>>-<<0>>-<<0>>-<<0>>
                                 |=|                                       |=|\s
                                 |=|        [=][=][=][=][=][=][=]{|}       |=|\s
                                 |=|        [=]         {|}      {|}       |=|\s
                                 |=|                      {|}    {|}       |=|\s
                                 |=|                        {|}  {|}       |=|\s
                                 |=|                          {|}{|}       |=|\s
                                 |=|                             {|}       |=|\s
                                 |=|         (T T)               {|}       |=|\s
                                 |=|          |||                {|}       |=|
                                 |=|        //|||                {|}       |=|
                                 |=|       // |||                {|}       |=|
                                 |=|          |||                {|}       |=|
                                 |=|          |||                {|}       |=|
                                 |=|                             {|}       |=|
                                 |=|                             {|}       |=|\s
                                 |=|                             {|}       |=|\s
                                 |=|  [=][=][=][=][=][=][=][=][=]{|}[=]    |=|\s
                                 |=|                                       |=|\s
                                <<0>>-<<0>>-<<0>>-<<0>>-<<0>>-<<0>>-<<0>>-<<0>>""");
                System.out.println(correctGuessArray);
                System.out.println("Missed letters: " + incorrectGuessArray);
            case 3:
                System.out.println(
                        """
                                <<0>>-<<0>>-<<0>>-<<0>>-<<0>>-<<0>>-<<0>>-<<0>>
                                 |=|                                       |=|\s
                                 |=|        [=][=][=][=][=][=][=]{|}       |=|\s
                                 |=|        [=]         {|}      {|}       |=|\s
                                 |=|                      {|}    {|}       |=|\s
                                 |=|                        {|}  {|}       |=|\s
                                 |=|                          {|}{|}       |=|\s
                                 |=|                             {|}       |=|\s
                                 |=|         (T T)               {|}       |=|\s
                                 |=|          |||                {|}       |=|
                                 |=|        //|||\\\\            {|}       |=|
                                 |=|       // ||| \\\\           {|}       |=|
                                 |=|          |||                {|}       |=|
                                 |=|          |||                {|}       |=|
                                 |=|                             {|}       |=|
                                 |=|                             {|}       |=|\s
                                 |=|                             {|}       |=|\s
                                 |=|  [=][=][=][=][=][=][=][=][=]{|}[=]    |=|\s
                                 |=|                                       |=|\s
                                <<0>>-<<0>>-<<0>>-<<0>>-<<0>>-<<0>>-<<0>>-<<0>>""");
                System.out.println(correctGuessArray);
                System.out.println("Missed letters: " + incorrectGuessArray);
                break;
            case 2:
                System.out.println(
                        """
                                <<0>>-<<0>>-<<0>>-<<0>>-<<0>>-<<0>>-<<0>>-<<0>>
                                 |=|                                       |=|\s
                                 |=|        [=][=][=][=][=][=][=]{|}       |=|\s
                                 |=|        [=]         {|}      {|}       |=|\s
                                 |=|                      {|}    {|}       |=|\s
                                 |=|                        {|}  {|}       |=|\s
                                 |=|                          {|}{|}       |=|\s
                                 |=|                             {|}       |=|\s
                                 |=|         (T T)               {|}       |=|\s
                                 |=|          |||                {|}       |=|
                                 |=|        //|||\\\\            {|}       |=|
                                 |=|       // ||| \\\\           {|}       |=|
                                 |=|          |||                {|}       |=|
                                 |=|        //|||                {|}       |=|\s" +
                                 |=|       //                    {|}       |=|\s" +
                                 |=|      //                     {|}       |=|\s" +
                                 |=|                             {|}       |=|
                                 |=|                             {|}       |=|\s
                                 |=|                             {|}       |=|\s
                                 |=|  [=][=][=][=][=][=][=][=][=]{|}[=]    |=|\s
                                 |=|                                       |=|\s
                                <<0>>-<<0>>-<<0>>-<<0>>-<<0>>-<<0>>-<<0>>-<<0>>""");
                System.out.println(correctGuessArray);
                System.out.println("Missed letters: " + incorrectGuessArray);
                break;
            case 1:
                System.out.println(
                        """
                                <<0>>-<<0>>-<<0>>-<<0>>-<<0>>-<<0>>-<<0>>-<<0>>
                                 |=|                                       |=|\s
                                 |=|        [=][=][=][=][=][=][=]{|}       |=|\s
                                 |=|        [=]         {|}      {|}       |=|\s
                                 |=|                      {|}    {|}       |=|\s
                                 |=|                        {|}  {|}       |=|\s
                                 |=|                          {|}{|}       |=|\s
                                 |=|                             {|}       |=|\s
                                 |=|         (T T)               {|}       |=|\s
                                 |=|          |||                {|}       |=|
                                 |=|        //|||\\\\            {|}       |=|
                                 |=|       // ||| \\\\           {|}       |=|
                                 |=|          |||                {|}       |=|
                                 |=|        //|||\\\\            {|}       |=|\s" +
                                 |=|       //     \\\\           {|}       |=|\s" +
                                 |=|      //       \\\\          {|}       |=|\s" +
                                 |=|                             {|}       |=|
                                 |=|                             {|}       |=|\s
                                 |=|                             {|}       |=|\s
                                 |=|  [=][=][=][=][=][=][=][=][=]{|}[=]    |=|\s
                                 |=|                                       |=|\s
                                <<0>>-<<0>>-<<0>>-<<0>>-<<0>>-<<0>>-<<0>>-<<0>>""");
                System.out.println(correctGuessArray);
                System.out.println("Missed letters: " + incorrectGuessArray);
                break;
            case 0:
                System.out.println("""
                            <<0>>-<<0>>-<<0>>-<<0>>-<<0>>-<<0>>-<<0>>-<<0>>
                             |=|                                       |=|\s
                             |=|        [=][=][=][=][=][=][=]{|}       |=|\s
                             |=|        [=]         {|}      {|}       |=|\s
                             |=|         V            {|}    {|}       |=|\s
                             |=|         |              {|}  {|}       |=|\s
                             |=|         ^                {|}{|}       |=|\s
                             |=|       (X X)                 {|}       |=|\s
                             |=|        (*)                  {|}       |=|\s
                             |=|      //|||\\\\              {|}       |=|
                             |=|     // ||| \\\\             {|}       |=|
                             |=|        |||                  {|}       |=|
                             |=|      //|||\\\\              {|}       |=|
                             |=|     //     \\\\             {|}       |=|
                             |=|    //       \\\\            {|}       |=|
                             |=|                             {|}       |=|\s
                             |=|                             {|}       |=|\s
                             |=|  [=][=][=][=][=][=][=][=][=]{|}[=]    |=|\s
                             |=|                                       |=|\s
                            <<0>>-<<0>>-<<0>>-<<0>>-<<0>>-<<0>>-<<0>>-<<0>>""");
                System.out.println(correctGuessArray);
                System.out.println("Missed letters: " + incorrectGuessArray);
                System.out.println("You lose!");
                break;
        }
    }
}
