import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class Hangman {

    private static final String[] WORDS = {
                "java", "программирование", "игра", "виселица", "книга", "компьютер", "алгоритм", "массив"
        };
    private static final int MAX_ATTEMPTS = 6;

    private static char getInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите букву: ");
        String input = scanner.nextLine().toLowerCase();
        return input.charAt(0);
    }

    private static boolean isWordGuessed(String wordToGuess, Set<Character> guessedLetters) {
        for (char letter : wordToGuess.toCharArray()) {
            if (!guessedLetters.contains(letter)) {
                return false;
            }
        }
        return true;
    }

        public static void main(String[] args) {
            ViewOfTheGallows view = new ViewOfTheGallows();
            Random random = new Random();
            HashSet<Character> incorrectLetters = new HashSet<>();
            HashSet<Character> guessedLetters = new HashSet<>();
            String wordToGuess = WORDS[random.nextInt(WORDS.length)];
            int attemptsLeft = MAX_ATTEMPTS;
            int errorsCount = 0;

           view.printMessage("Добро пожаловать в игру 'Виселица'!");

            while (attemptsLeft > 0) {
                view.displayGallows(attemptsLeft);
                view.displayGuessedLetters(guessedLetters);
                view.displayIncorrectLetters(incorrectLetters);
                view.displayErrorCount(errorsCount);
                view.displayCurrentState(wordToGuess, guessedLetters);
                view.printMessage("Жизней осталось: " + attemptsLeft);

                char guessedLetter = getInput();

                if (guessedLetters.contains(guessedLetter)) {
                    view.printMessage("Вы уже угадали эту букву.");
                    continue;
                }

                if (wordToGuess.indexOf(guessedLetter) == -1) {
                    attemptsLeft--;
                    errorsCount++;
                    incorrectLetters.add(guessedLetter);
                    view.printMessage("Неправильно!");
                }
                else {
                    guessedLetters.add(guessedLetter);
                }

                if (isWordGuessed(wordToGuess, guessedLetters)) {
                    view.printMessage("Поздравляем! Вы угадали слово: " + wordToGuess);
                    return;
                }
            }
            view.printMessage("Вы проиграли! Загаданное слово было: " + wordToGuess);
        }




    }

