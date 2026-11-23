package org.firstinspires.ftc.teamcode.BioBuzz.utils;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class packageImport {

    public DcMotor rightDrive;
    public DcMotor leftDrive;
    public DcMotor intakeMotor;
    public CRServo windmillServo;
    public DcMotor launcherMotor;
    public CRServo intakeServoRight;
    public CRServo intakeServoLeft;

    public void importMovementMotor(HardwareMap hardwareMap){

        leftDrive = hardwareMap.get(DcMotor.class, "left_drive");
        rightDrive = hardwareMap.get(DcMotor.class, "right_drive");
        intakeMotor = hardwareMap.get(DcMotor.class, "intake");
        launcherMotor = hardwareMap.get(DcMotor.class, "launcher");
        windmillServo = hardwareMap.get(CRServo.class, "windmill");
        intakeServoLeft = hardwareMap.get(CRServo.class, "left_intake_servo");
        intakeServoRight = hardwareMap.get(CRServo.class, "right_intake_servo");

        leftDrive.setDirection(DcMotor.Direction.FORWARD);
        rightDrive.setDirection(DcMotorSimple.Direction.REVERSE);








    }


}
