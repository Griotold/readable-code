package cleancode.studycafe.practice;

import cleancode.studycafe.practice.exception.AppException;
import cleancode.studycafe.practice.io.*;
import cleancode.studycafe.practice.model.StudyCafeLockerPass;
import cleancode.studycafe.practice.model.StudyCafePass;
import cleancode.studycafe.practice.model.StudyCafePassType;

import java.util.List;

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
            List<StudyCafePass> studyCafePasses = studyCafeFileHandler.readStudyCafePasses();
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

    private void handleSelectedFixedPass(List<StudyCafePass> studyCafePasses) {
        List<StudyCafePass> fixedPasses = getFixed(studyCafePasses);
        outputHandler.showPassListForSelection(fixedPasses);
        StudyCafePass selectedPass = inputHandler.getSelectPass(fixedPasses);

        List<StudyCafeLockerPass> lockerPasses = studyCafeFileHandler.readLockerPasses();
        StudyCafeLockerPass lockerPass = lockerPasses.stream()
            .filter(option ->
                option.isSamePassType(selectedPass)
                    && option.isSameDuration(selectedPass)
            )
            .findFirst()
            .orElse(null);

        boolean lockerSelection = false;
        if (lockerPass != null) {
            outputHandler.askLockerPass(lockerPass);
            lockerSelection = inputHandler.getLockerSelection();
        }

        if (lockerSelection) {
            outputHandler.showPassOrderSummary(selectedPass, lockerPass);
        } else {
            outputHandler.showPassOrderSummary(selectedPass, null);
        }
    }

    private void handleSelectedWeeklyPass(List<StudyCafePass> studyCafePasses) {
        List<StudyCafePass> weeklyPasses = getWeeklyPasses(studyCafePasses);
        outputHandler.showPassListForSelection(weeklyPasses);
        StudyCafePass selectedPass = inputHandler.getSelectPass(weeklyPasses);
        outputHandler.showPassOrderSummary(selectedPass, null);
    }

    private void handleSelectedHourlyPass(List<StudyCafePass> studyCafePasses) {
        List<StudyCafePass> hourlyPasses = getHourlyPasses(studyCafePasses);
        outputHandler.showPassListForSelection(hourlyPasses);
        StudyCafePass selectedPass = inputHandler.getSelectPass(hourlyPasses);
        outputHandler.showPassOrderSummary(selectedPass, null);
    }

    private List<StudyCafePass> getHourlyPasses(List<StudyCafePass> studyCafePasses) {
        return studyCafePasses.stream()
            .filter(StudyCafePass::isHourly)
            .toList();
    }

    private List<StudyCafePass> getWeeklyPasses(List<StudyCafePass> studyCafePasses) {
        return studyCafePasses.stream()
            .filter(StudyCafePass::isWeekly)
            .toList();
    }

    private List<StudyCafePass> getFixed(List<StudyCafePass> studyCafePasses) {
        return studyCafePasses.stream()
            .filter(StudyCafePass::isFixed)
            .toList();
    }


}
