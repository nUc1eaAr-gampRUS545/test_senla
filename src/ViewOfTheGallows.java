
import java.util.Set;

public class ViewOfTheGallows {
    private static final String[] GallowsPics = {
            "  +---+\n" +
                    "  |   |\n" +
                    "      |\n" +
                    "      |\n" +
                    "      |\n" +
                    "      |\n" +
                    "=========",

            "  +---+\n" +
                    "  |   |\n" +
                    "  O   |\n" +
                    "      |\n" +
                    "      |\n" +
                    "      |\n" +
                    "=========",

            "  +---+\n" +
                    "  |   |\n" +
                    "  O   |\n" +
                    "  |   |\n" +
                    "      |\n" +
                    "      |\n" +
                    "=========",

            "  +---+\n" +
                    "  |   |\n" +
                    "  O   |\n" +
                    " /|   |\n" +
                    "      |\n" +
                    "      |\n" +
                    "=========",

            "  +---+\n" +
                    "  |   |\n" +
                    "  O   |\n" +
                    " /|\\  |\n" +
                    "      |\n" +
                    "      |\n" +
                    "=========",

            "  +---+\n" +
                    "  |   |\n" +
                    "  O   |\n" +
                    " /|\\  |\n" +
                    " /    |\n" +
                    "      |\n" +
                    "=========",

            "  +---+\n" +
                    "  |   |\n" +
                    "  O   |\n" +
                    " /|\\  |\n" +
                    " / \\  |\n" +
                    "      |\n" +
                    "========="
    };

    public void displayCurrentState(String wordToGuess, Set<Character> guessedLetters) {
        StringBuilder currentState = new StringBuilder();
        for (char letter : wordToGuess.toCharArray()) {
            if (guessedLetters.contains(letter)) {
                currentState.append(letter).append(" ");
            } else {
                currentState.append("_ ");
            }
        }
        this.printMessage("Слово: " + currentState.toString().trim());
    }

    public void displayGallows(int countAttempts){
        printMessage(GallowsPics[GallowsPics.length - countAttempts - 1]);
    }
    public void displayErrorCount(int errorCount) {
        printMessage("Количество ошибок: " + errorCount);
    }
    public void displayGuessedLetters(Set<Character>guessedLetters){
        StringBuilder stringBuilder = new StringBuilder("Угаданные буквы : ");
        for(char c : guessedLetters){
            stringBuilder.append(c);
            stringBuilder.append(" ");
        }
        System.out.println(stringBuilder.toString());
    }


    public void displayIncorrectLetters(Set<Character> incorrectLetters) {
        StringBuilder sb = new StringBuilder("Неправильные буквы: ");
        for (char c : incorrectLetters) {
            sb.append(c);
            sb.append(" ");
        }
        System.out.println(sb.toString());
    }
    public void printMessage(String message){
        System.out.println(message);
    }
}