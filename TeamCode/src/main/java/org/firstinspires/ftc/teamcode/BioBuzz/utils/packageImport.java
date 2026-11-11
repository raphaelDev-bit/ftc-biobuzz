package org.firstinspires.ftc.teamcode.BioBuzz.utils;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class packageImport {

    public DcMotor rightDrive;
    public DcMotor leftDrive;
    public DcMotor intakeMotor;
    public Servo windmillServo;
    public DcMotor launcherMotor;

    public void importMovementMotor(HardwareMap hardwareMap){

        leftDrive = hardwareMap.get(DcMotor.class, "left_drive");
        rightDrive = hardwareMap.get(DcMotor.class, "right_drive");
        intakeMotor = hardwareMap.get(DcMotor.class, "intake");
        launcherMotor = hardwareMap.get(DcMotor.class, "launcher");

        leftDrive.setDirection(DcMotor.Direction.FORWARD);
        rightDrive.setDirection(DcMotorSimple.Direction.REVERSE);








    }


}
