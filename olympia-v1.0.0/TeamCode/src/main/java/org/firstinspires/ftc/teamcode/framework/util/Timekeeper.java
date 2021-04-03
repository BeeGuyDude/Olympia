package org.firstinspires.ftc.teamcode.framework.util;

import static org.firstinspires.ftc.teamcode.framework.util.FrameworkConstants.*;

public class Timekeeper {
    private double previousCycleTime = 0;
    private double currentCycleTime = 0;

    private double[] timeHistory = new double[CYCLE_TIME_HISTORY_LENGTH];
    private double averageCycleTime = 0;

    public Timekeeper() {
        for (int i = 0; i < (CYCLE_TIME_HISTORY_LENGTH); i++) {
            timeHistory[i] = 0;
        }
    }

    public void update(double updatedCycleTime) {
        previousCycleTime = currentCycleTime;
        currentCycleTime = updatedCycleTime;

        double sum = 0;
        double uninitialized = 0;
        for (int i = (CYCLE_TIME_HISTORY_LENGTH - 1); i > 0; i--) {
            timeHistory[i] = timeHistory[i - 1];
        }
        timeHistory[0] = updatedCycleTime - previousCycleTime;
        for (int i = 0; i < CYCLE_TIME_HISTORY_LENGTH; i++) {
            if (timeHistory[i] != 0) {
                sum += timeHistory[i];
            } else {
                uninitialized++;
            }
        }
        averageCycleTime = sum / (CYCLE_TIME_HISTORY_LENGTH - uninitialized);
    }

    public double getCycleTime() {
        return (currentCycleTime - previousCycleTime)*1000;
    }

    public double getAverageCycleTime() {
        return averageCycleTime*1000;
    }

    public double getRuntime() {
        return currentCycleTime;
    }
}
