package Controller.ExerciseLogic;

import java.util.ArrayList;

abstract class Command {
    protected int userId;

    public void Command(int UserId) {
        //TODO: Check if User Exists
    userId= UserId;

    }
    abstract void execute(ArrayList<String> args);

}
