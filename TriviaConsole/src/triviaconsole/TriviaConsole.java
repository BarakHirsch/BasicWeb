package triviaconsole;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TriviaConsole {

    private static BufferedReader reader;

    enum mainMenuOptions {

        None,
        Play,
        AddQustion,
        DeleteQuestion,
        Save,
        Quit
    }

    public static void main(String[] args) throws IOException {

        reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Lets Play Trivia!");

        while (showMainMenu()) {

        }
    }

    private static boolean showMainMenu() throws IOException {
        System.out.println("What do you want to do?");
        System.out.println(mainMenuOptions.Play.ordinal() + ". Play");
        System.out.println(mainMenuOptions.AddQustion.ordinal() + ". Add Question");
        System.out.println(mainMenuOptions.DeleteQuestion.ordinal() + ". Delete Question");
        System.out.println(mainMenuOptions.Save.ordinal() + ". Save Changes");
        System.out.println(mainMenuOptions.Quit.ordinal() + ". Quit");
      
        String selection = reader.readLine();
        switch (parseSelection(selection)) {
            case None: {
                System.out.println("Invalid selection, please pick again");
                break;
            }

        }

        return true;
    }

    private static mainMenuOptions parseSelection(String input) {
        try {
            mainMenuOptions[] values = mainMenuOptions.values();
            int value = Integer.parseInt(input);

            if (values.length <= value) {
                return mainMenuOptions.None;
            }

            return values[value];
        } catch (NumberFormatException nfe) {
            return mainMenuOptions.None;
        }
    }
}
