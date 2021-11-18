package org.firstinspires.ftc.teamcode.framework;

public class PIDHandler { //Only actually includes PID, not VAJSCMF
    private double kP = 0;
    private double kI = 0;
    private double kD = 0;
    private double P, I = 0, D;
    private double error, previous_error;

    private boolean D_ran = false;

    public PIDHandler(double kP, double kI, double kD) {
        setConstants(kP, kI, kD);
    }

    public void setConstants(double kP, double kI, double kD) {
        this.kP = kP;
        this.kI = kI;
        this.kD = kD;
    }

    public double getPID(double current, double target, double dt) {
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

    public void resetI() {
        I = 0;
    }

    public void resetD() {
        D_ran = false;
    }
}
