package org.firstinspires.ftc.teamcode.mechanisms.devicehandlers;

import com.qualcomm.robotcore.hardware.Gamepad;

public class Button {

    private Gamepad gamepad;
    private String buttonId;

    public Button(Gamepad gamepad, String buttonId) {
        this.gamepad = gamepad;
        this.buttonId = buttonId;
    }
}
