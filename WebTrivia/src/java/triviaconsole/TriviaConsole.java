package triviaconsole;

import enums.*;
import helpers.ParseHelper;
import models.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import logic.Manager;
import logic.TriviaGame;

public class TriviaConsole {

    private static BufferedReader reader;
    private static Manager manager;

    public static void main(String[] args) throws IOException {

        reader = new BufferedReader(new InputStreamReader(System.in));
        manager = new Manager();

        System.out.println("Welcome to Trivia!");

        do {
            MainMenuOptions selectedOption = showMainMenu();

            switch (selectedOption) {
                case None:
                    System.out.println("Invalid selection, please pick again");
                    break;

                case Play:
                    playGame();
                    break;

                case AddQuestion:
                    addQuestion();
                    break;

                case DeleteQuestion:
                    deleteQuestion();
                    break;

                case Save:
                    save();
                    break;

                case Quit:
                    System.out.println("Goodbye");
                    return;
            }
        } while (true);
    }

    private static MainMenuOptions showMainMenu() throws IOException {
        System.out.println("Please select one of the options:");
        System.out.println(MainMenuOptions.Play.ordinal() + ". Play Trivia");
        System.out.println(MainMenuOptions.AddQuestion.ordinal() + ". Add a question to the database");
        System.out.println(MainMenuOptions.DeleteQuestion.ordinal() + ". Delete a question from the database");
        System.out.println(MainMenuOptions.Save.ordinal() + ". Save changes");
        System.out.println(MainMenuOptions.Quit.ordinal() + ". Quit game");

        String selection = reader.readLine();

        return ParseHelper.parseMainMenuOptions(selection);
    }

    private static void playGame() throws IOException {
        System.out.println(" Please choose categories, choose 0 when done picking categories");
        ArrayList<Category> categoriesToPlay = new ArrayList<>();
        Category cat;
        do {
            cat = getCategory();
            categoriesToPlay.add(cat);
        } while (cat != Category.None);

        TriviaGame currentGame = manager.startGame(categoriesToPlay);

        if (!currentGame.hasMoreQuestions()) {
            System.out.println("There are no questions in the selected categories");
            return;
        }

        System.out.println("Starting game, to quit answer 'Quit' as an answer");

        while (currentGame.hasMoreQuestions()) {

            Question curQuestion = currentGame.getNextQuestion();
            boolean hasQuit = playQuestion(curQuestion);

            if (hasQuit) {
                System.out.println("Game Ended, goodbye");
                return;
            }
        }

        System.out.println("Game Ended, there are no more questions");
    }

    private static void addQuestion() throws IOException {

        System.out.println("Please insert the question:");
        String questionText = reader.readLine();

        Difficulty difficulty = getDifficulty();
        Category category = Category.None;

        do {
            category = getCategory();

            if (category == Category.None) {
                System.out.println("Invalid selection");
                if (wantToStop()) {
                    return;
                }
            }
        } while (category == Category.None);

        Question question = null;

        while (true) {
            System.out.println("Select question type:");
            System.out.println("1. Open question");
            System.out.println("2. Yes / No question");
            System.out.println("3. Multiple choice question");

            String selection = reader.readLine();

            switch (selection) {
                case "1":
                    System.out.println("Please insert the answer:");
                    String answer = reader.readLine();
                    question = new Question(difficulty, category, questionText, answer);
                    break;
                case "2":
                    boolean isTrue = GetIsTrue();
                    question = new YesNoQuestion(difficulty, category, questionText, isTrue);
                    break;
                case "3":

                    List<String> options = getOptions();
                    int answerIndex = getAnswerIndex(options);

                    if (answerIndex == Integer.MAX_VALUE) {
                        return;
                    }

                    question = new MultipleChoiceQuestion(difficulty, category, questionText, options, answerIndex);

                    break;
                default:
                    System.out.println("Invalid selection, please pick again");
                    break;
            }

            if (question != null) {
                manager.addQuestion(question);
                return;
            }
        }
    }

    public static boolean GetIsTrue() throws IOException {

        do {
            System.out.println("Please insert the answer:");
            System.out.println("1. Yes");
            System.out.println("2. No");
            String selection = reader.readLine();

            switch (selection) {
                case "1":
                    return true;
                case "2":
                    return false;
                default:
                    System.out.println("Invalid selection, please pick again");
                    break;
            }
        } while (true);
    }

    private static Difficulty getDifficulty() throws IOException {
        Difficulty difficulty = Difficulty.None;
        while (difficulty == Difficulty.None) {
            System.out.println("Please select the difficulty:");
            System.out.println(Difficulty.Easy.ordinal() + ". Easy");
            System.out.println(Difficulty.Medium.ordinal() + ". Medium");
            System.out.println(Difficulty.Hard.ordinal() + ". Hard");

            String difficultySelection = reader.readLine();

            difficulty = ParseHelper.parseDifficulty(difficultySelection);

            if (difficulty == Difficulty.None) {
                System.out.println("Invalid selection, please pick again");
            }
        }

        return difficulty;
    }

    private static Category getCategory() throws IOException {
        Category category;
        System.out.println("Please select the category:");
        System.out.println(Category.General.ordinal() + ". General");
        System.out.println(Category.Geography.ordinal() + ". Geography");
        System.out.println(Category.History.ordinal() + ". History");
        System.out.println(Category.Sports.ordinal() + ". Sports");

        String selection = reader.readLine();

        category = ParseHelper.parseCategory(selection);

        return category;
    }

    private static void deleteQuestion() throws IOException {
        Question[] questions = manager.getQuestions();

        if (questions.length == 0) {
            System.out.println("There are no questions to delete");
            return;
        }

        System.out.println("Choose a question to delete:");

        for (int i = 1; i <= questions.length; i++) {
            System.out.println(i + ". " + questions[i - 1].getQuestionText());
        }

        String selection = reader.readLine();
        int number = ParseHelper.tryParseNumber(selection);

        if (questions.length < number) {
            System.out.println("Invalid question number entered");
            return;
        }

        manager.deleteQuestion(questions[number - 1]);
    }

    private static void save() {
        manager.Save();
    }

    private static List<String> getOptions() throws IOException {
        ArrayList<String> options = new ArrayList<>();

        int answersCount = GetNumberOfOptions();

        for (int i = 1; i < answersCount + 1; i++) {
            System.out.println("Please enter answer number " + i + ":");
            options.add(reader.readLine());
        }

        return options;
    }

    public static int GetNumberOfOptions() throws IOException {
        while (true) {

            System.out.println("Plase insret the number of options:");
            String input = reader.readLine();

            int answerCount = ParseHelper.tryParseNumber(input);

            if (answerCount <= 0 || answerCount == Integer.MAX_VALUE) {
                System.out.println("Invalid number entered, please enter a valid number");
            } else {
                return answerCount;
            }
        }
    }

    private static int getAnswerIndex(List<String> options) throws IOException {
        System.out.println("These are the possible answers:");
        for (int i = 0; i < options.size(); i++) {
            System.out.println((i + 1) + ". " + options.get(i));
        }

        while (true) {
            System.out.println("please insert the currect answer number:");
            String input = reader.readLine();

            if (input.equalsIgnoreCase("Quit")) {
                return Integer.MAX_VALUE;
            }

            int answerNumber = ParseHelper.tryParseNumber(input);

            if (options.size() < answerNumber || answerNumber <= 0) {
                System.out.println("Invalid answer number entered, please enter a valid answer number");
            } else {
                return answerNumber - 1;
            }
        }
    }

    private static boolean wantToStop() throws IOException {
        System.out.println("Do you want to quit? (Y/N)");
        String questionText = reader.readLine();
        return questionText.equalsIgnoreCase("Y");

    }

    private static boolean playQuestion(Question question) throws IOException {

        System.out.println(question.getQuestionText());
        String answer;

        if (question instanceof MultipleChoiceQuestion) {
            List<String> options = ((MultipleChoiceQuestion) question).getOptions();
            int answerIndex = getAnswerIndex(options);

            if (answerIndex == Integer.MAX_VALUE) {
                return true;
            }

            answer = options.get(answerIndex);
        } else {
            System.out.print("Your answer:");
            answer = reader.readLine();

            if (answer.equalsIgnoreCase("Quit")) {
                return true;
            }
        }

        if (true == question.verifyAnswer(answer)) {
            System.out.println("You Answered correctly");
        } else {
            System.out.println("Wrong answer, The currect answer is:");
            System.out.println(question.getAnswer());
        }

        return false;
    }
}
