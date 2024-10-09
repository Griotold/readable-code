package cleancode.studycafe.practice.io;

import cleancode.studycafe.practice.exception.AppException;
import cleancode.studycafe.practice.model.StudyCafePass;
import cleancode.studycafe.practice.model.StudyCafePassType;

import java.util.List;

public interface InputHandler {

    StudyCafePassType getPassTypeSelectingUserAction();

    StudyCafePass getSelectPass(List<StudyCafePass> passes);

    boolean getLockerSelection();
}
