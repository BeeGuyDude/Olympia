package org.firstinspires.ftc.teamcode.framework;

public class PostableHandler {
    private static PostableHandler handlerInstance;

    public static PostableHandler getInstance() {
        if (handlerInstance == null) {
            handlerInstance = new PostableHandler();
        }
        return handlerInstance;
    }
}
