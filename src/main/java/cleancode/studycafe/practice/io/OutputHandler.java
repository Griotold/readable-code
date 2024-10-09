package cleancode.studycafe.practice.io;

import cleancode.studycafe.practice.model.StudyCafeLockerPass;
import cleancode.studycafe.practice.model.StudyCafePass;

import java.util.List;

public interface OutputHandler {

    void showUserGuidance();

    void showPassListForSelection(List<StudyCafePass> passes);

    void askLockerPass(StudyCafeLockerPass lockerPass);

    void showPassOrderSummary(StudyCafePass selectedPass, StudyCafeLockerPass lockerPass);

    void showSimpleMessage(String message);
}
