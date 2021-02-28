package org.firstinspires.ftc.teamcode.mechanisms.devicehandlers;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.commands.Command;
import static org.firstinspires.ftc.teamcode.framework.util.Constants.*;

public class Button {

    private Gamepad gamepad;
    private ButtonID buttonId;

    private Command localCommand;

    public Button(Gamepad gamepad, ButtonID buttonId) {
        this.gamepad = gamepad;
        this.buttonId = buttonId;
    }


}
