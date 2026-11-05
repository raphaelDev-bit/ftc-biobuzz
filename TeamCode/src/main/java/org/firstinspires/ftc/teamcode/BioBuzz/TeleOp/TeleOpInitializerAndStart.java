package org.firstinspires.ftc.teamcode.BioBuzz.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.BioBuzz.utils.packageImport;

public class TeleOpInitializerAndStart extends LinearOpMode{

    packageImport robotCompostant = new packageImport();

    public void runOpMode(){

        robotCompostant.importMovementMotor(hardwareMap);

        telemetry.addData("statut des moteurs", "pret");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()){

        }


    }

}
