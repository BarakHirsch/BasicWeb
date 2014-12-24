package helpers;

import enums.*;

public class ParseHelper {

    public static int tryParseNumber(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return Integer.MAX_VALUE;
        }
    }

    public static MainMenuOptions parseMainMenuOptions(String input) {

        MainMenuOptions[] values = MainMenuOptions.values();
        int parsedNumber = tryParseNumber(input);

        if (parsedNumber < 0 || values.length <= parsedNumber) {
            return MainMenuOptions.None;
        }

        return values[parsedNumber];
    }

    public static Difficulty parseDifficulty2(String input) {
        switch (input) {
            case "Easy":
                return Difficulty.Easy;
            case "Medium":
                return Difficulty.Medium;
            case "Hard":
                return Difficulty.Hard;
        }
        return Difficulty.None;
    }

    public static Difficulty parseDifficulty(String input) {

        Difficulty[] values = Difficulty.values();
        int parsedNumber = tryParseNumber(input);

        if (parsedNumber < 0 || values.length <= parsedNumber) {
            return Difficulty.None;
        }

        return values[parsedNumber];
    }

    public static Category parseCategory(String input) {

        Category[] values = Category.values();
        int parsedNumber = tryParseNumber(input);

        if (parsedNumber < 0 || values.length <= parsedNumber) {
            return Category.None;
        }

        return values[parsedNumber];
    }

    public static Category parseCategory2(String input) {
        switch (input) {
            case "General":
                return Category.General;
            case "Geography":
                return Category.Geography;
            case "History":
                return Category.History;
            case "Sports":
                return Category.Sports;
        }
        return Category.None;
    }
}
