package triviaconsole;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import logic.Manager;
import models.Question;

public class TriviaConsole {

    private static BufferedReader reader;
    private static Manager manager;

    private static int TryParseNumber(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return Integer.MAX_VALUE;
        }
    }

    enum mainMenuOptions {

        None,
        Play,
        AddQuestion,
        DeleteQuestion,
        Save,
        Quit
    }

    public static void main(String[] args) throws IOException {

        reader = new BufferedReader(new InputStreamReader(System.in));
        manager = new Manager();

        System.out.println("Lets Play Trivia!");

        do {
            mainMenuOptions selectedOption = showMainMenu();

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

    private static mainMenuOptions showMainMenu() throws IOException {
        System.out.println("What do you want to do?");
        System.out.println(mainMenuOptions.Play.ordinal() + ". Play");
        System.out.println(mainMenuOptions.AddQuestion.ordinal() + ". Add Question");
        System.out.println(mainMenuOptions.DeleteQuestion.ordinal() + ". Delete Question");
        System.out.println(mainMenuOptions.Save.ordinal() + ". Save Changes");
        System.out.println(mainMenuOptions.Quit.ordinal() + ". Quit");

        String selection = reader.readLine();

        return parseSelection(selection);
    }

    private static mainMenuOptions parseSelection(String input) {

        mainMenuOptions[] values = mainMenuOptions.values();
        int parsedNumber = TryParseNumber(input);

        if (values.length <= parsedNumber) {
            return mainMenuOptions.None;
        }

        return values[parsedNumber];
    }

    private static void playGame() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static void addQuestion() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static void deleteQuestion() throws IOException {
        Question[] questions = manager.getQuestions();

        if (questions.length == 0) {
            System.out.println("There are no questions to delete");
            return;

        }

        System.out.println("Choose a question to delete:");
        for (int i = 1; i < questions.length; i++) {
            System.out.println(i + ". " + questions[i - 1].getQuestionText());
        }

        String selection = reader.readLine();
        int number = TryParseNumber(selection);

        if (questions.length < number) {
            System.out.println("Invalid question number entered");
            return;
        }

        manager.deleteQuestion(questions[number - 1]);
    }

    private static void save() {

    }
}
