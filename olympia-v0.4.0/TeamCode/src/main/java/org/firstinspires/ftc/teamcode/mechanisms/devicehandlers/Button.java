package org.firstinspires.ftc.teamcode.mechanisms.devicehandlers;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.commands.Command;
public class Button {

    private Gamepad gamepad;
    private String buttonId;

    public Button(Gamepad gamepad, String buttonId) {
        this.gamepad = gamepad;
        this.buttonId = buttonId;
    }


}
