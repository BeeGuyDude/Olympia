package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.reflections.Reflections;

import java.util.List;
import java.util.Set;

@TeleOp
public class fart extends OpMode {

    @Override
    public void init() {
        telemetry.addData("Dir", System.getProperty("user.dir"));
    }

    @Override
    public void loop() {
        Reflections reflections = new Reflections("org.firstinspires.ftc.teamcode");
        Set<Class<? extends Object>> classList = reflections.getSubTypesOf(Object.class);

        for (Class<? extends Object> aClass : classList) {
            telemetry.addData(aClass.getName(), "Initialized.");
        }

        telemetry.addData("The Set", classList);
    }
}
