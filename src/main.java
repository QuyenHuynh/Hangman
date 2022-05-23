import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class main {

    static int remainingTries = 8;
    // First wrong answer, draw the platform
    // Second wrong answer, draw the head
    // Third, draw the body
    // Fourth, draw one arm out
    // Fifth, draw the other arm,
    // Sixth, draw one leg
    // Seventh, draw the other leg
    // Eighth, draw the noose and connect the head to the noose
    static boolean victory = false;
    static String randomWord = "";
    static ArrayList<String> correctGuessArray = new ArrayList<>();
    static ArrayList<String> incorrectGuessArray = new ArrayList<>();
    static String playAgain = "";
    static Boolean validPlayAgain = false;
    static Boolean validGuess = false;


    public static void main (String[] args) {
        generateNewWord();
        System.out.println("Our word is..." + randomWord);
        displayHangman();
        generateUnderscores(randomWord);
        promptGuess();

        resetGame();
    }

//    public static void startNewGame() {
//        while (remainingTries < 0 ) {
//
//        }
//    }

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
        generateNewWord();
        validGuess = false;
        correctGuessArray.clear();
        incorrectGuessArray.clear();
        playAgain = "";
        validPlayAgain = false;
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

        String underscores = "";

        //create an underscore for each character in the provided string
        for ( int i = 0; i <= str.length() - 1 ; i++) {
            underscores += "_ ";
            correctGuessArray.add("_");
        }
        System.out.println("                  " + underscores);
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
                break;
        }
    }
}
