package org.firstinspires.ftc.teamcode.BioBuzz.utils;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public class powerTime {
    public  void PowerTime(DcMotor motor, double power, int time, LinearOpMode opMode){
        motor.setPower(power);

        opMode.sleep(time);

        motor.setPower(0.0);


    }
}
