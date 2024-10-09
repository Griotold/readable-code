package cleancode.studycafe.practice;

import cleancode.studycafe.practice.exception.AppException;
import cleancode.studycafe.practice.io.InputHandler;
import cleancode.studycafe.practice.io.OutputHandler;
import cleancode.studycafe.practice.io.StudyCafeFileHandler;
import cleancode.studycafe.practice.model.*;

import java.util.List;
import java.util.Optional;

public class StudyCafePassMachine {

    private final InputHandler inputHandler;
    private final OutputHandler outputHandler;
    private final StudyCafeFileHandler studyCafeFileHandler;

    public StudyCafePassMachine(InputHandler inputHandler, OutputHandler outputHandler, StudyCafeFileHandler studyCafeFileHandler) {
        this.inputHandler = inputHandler;
        this.outputHandler = outputHandler;
        this.studyCafeFileHandler = studyCafeFileHandler;
    }

    public void run() {
        try {
            outputHandler.showUserGuidance();

            StudyCafePassType studyCafePassType = inputHandler.getPassTypeSelectingUserAction();
            StudyCafePasses studyCafePasses = StudyCafePasses.from(studyCafeFileHandler);
            if (studyCafePassType.isHourly()) {
                handleSelectedHourlyPass(studyCafePasses);
            } else if (studyCafePassType.isWeekly()) {
                handleSelectedWeeklyPass(studyCafePasses);
            } else if (studyCafePassType.isFixed()) {
                handleSelectedFixedPass(studyCafePasses);
            }
        } catch (AppException e) {
            outputHandler.showSimpleMessage(e.getMessage());
        } catch (Exception e) {
            outputHandler.showSimpleMessage("알 수 없는 오류가 발생했습니다.");
        }
    }

    private void handleSelectedHourlyPass(StudyCafePasses studyCafePasses) {
        List<StudyCafePass> hourlyPasses = studyCafePasses.getHourlyPasses();
        outputHandler.showPassListForSelection(hourlyPasses);
        StudyCafePass selectedPass = inputHandler.getSelectPass(hourlyPasses);
        outputHandler.showPassOrderSummary(selectedPass, null);
    }

    private void handleSelectedWeeklyPass(StudyCafePasses studyCafePasses) {
        List<StudyCafePass> weeklyPasses = studyCafePasses.getWeeklyPasses();
        outputHandler.showPassListForSelection(weeklyPasses);
        StudyCafePass selectedPass = inputHandler.getSelectPass(weeklyPasses);
        outputHandler.showPassOrderSummary(selectedPass, null);
    }

    private void handleSelectedFixedPass(StudyCafePasses studyCafePasses) {
        List<StudyCafePass> fixedPasses = studyCafePasses.getFixedPasses();
        outputHandler.showPassListForSelection(fixedPasses);
        StudyCafePass selectedPass = inputHandler.getSelectPass(fixedPasses);

        StudyCafeLockerPasses studyCafeLockerPasses = StudyCafeLockerPasses.from(studyCafeFileHandler);
        Optional<StudyCafeLockerPass> studyCafeLockerPass = studyCafeLockerPasses.getLockerPassFor(selectedPass);

        boolean lockerSelection = false;
        if (studyCafeLockerPass.isPresent()) {
            outputHandler.askLockerPass(studyCafeLockerPass.get());
            lockerSelection = inputHandler.getLockerSelection();
        }

        if (lockerSelection) {
            outputHandler.showPassOrderSummary(selectedPass, studyCafeLockerPass.get());
        } else {
            outputHandler.showPassOrderSummary(selectedPass, null);
        }
    }
}
