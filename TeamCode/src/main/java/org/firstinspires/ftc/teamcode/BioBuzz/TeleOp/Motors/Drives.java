package org.firstinspires.ftc.teamcode.BioBuzz.TeleOp.Motors;


import com.qualcomm.robotcore.hardware.DcMotor;

public class Drives {

    public void AvancerMoteur(DcMotor leftDrive, DcMotor rightDrive, double value){

        rightDrive.setPower(value);
        leftDrive.setPower(-value);

    }
}
