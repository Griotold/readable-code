package cleancode.studycafe.practice;

import cleancode.studycafe.practice.io.ConsoleInputHandler;
import cleancode.studycafe.practice.io.ConsoleOutputHandler;
import cleancode.studycafe.practice.io.StudyCafeFileHandler;

public class StudyCafeApplication {

    public static void main(String[] args) {
        ConsoleInputHandler inputHandler = new ConsoleInputHandler();
        ConsoleOutputHandler outputHandler = new ConsoleOutputHandler();
        StudyCafeFileHandler studyCafeFileHandler = new StudyCafeFileHandler();

        StudyCafePassMachine studyCafePassMachine = new StudyCafePassMachine(inputHandler, outputHandler, studyCafeFileHandler);
        studyCafePassMachine.run();
    }

}
