package org.firstinspires.ftc.teamcode.framework;

public class PIDHandler { //Only actually includes pid
    double kP = 0;
    double kI = 0;
    double kD = 0;
    double P, I = 0, D;
    double error, previous_error;

    boolean D_ran = false;

    void SetConstants(double kP, double kI, double kD) {
        this.kP = kP;
        this.kI = kI;
        this.kD = kD;
    }

    double GetPID(double current, double target, double dt) {
        error = target - current;
        P = error * kP; //Compute P
        I += error * kI * dt; //Compute I
        if (D_ran) {
            D = (error - previous_error) * kD * dt; //Compute D if its not the first cycle
        } else {
            D = 0;
        }
        previous_error = error;
        D_ran = true;

        return P + I + D; //Add it all together
    }

    void ResetI() {
        I = 0;
    }

    void ResetD() {
        D_ran = false;
    }
}