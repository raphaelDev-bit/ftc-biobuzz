package org.firstinspires.ftc.teamcode.BioBuzz.Autonomus;

import com.qualcomm.hardware.rev.RevColorSensorV3;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.BioBuzz.utils.packageImport;
import org.firstinspires.ftc.teamcode.BioBuzz.utils.powerTime;

@TeleOp(name= "OpMode V1 BioBuzz", group="BioBuzz")
public class autonomus extends LinearOpMode {
    //si vous lisez ce commentaire, METTEZ CE CODE PROPRE GROS

    public RevColorSensorV3 colorSensor;

    packageImport robotComposant = new packageImport();
    powerTime powerTime = new powerTime();

    @Override


    public void runOpMode(){

        robotComposant.importMovementMotor(hardwareMap);
        colorSensor = hardwareMap.get(RevColorSensorV3.class, "color_sensor");

        telemetry.addData("Statut", "Prêt à démarrer");
        telemetry.update();

        waitForStart();

        while(opModeIsActive()){

            NormalizedRGBA colors = colorSensor.getNormalizedColors();
            double distance = colorSensor.getDistance(DistanceUnit.CM);

            robotComposant.intakeMotor.setPower(1.0);

            // Le rouge et le vert sont forts, le bleu est faible
            boolean estJaune = (colors.red > 0.05 && colors.green > 0.05) &&
                    (colors.red > colors.blue * 1.5) &&
                    (colors.green > colors.blue * 1.5);

            if (estJaune) {
                telemetry.addData("yellow", "true");
            } else {
                telemetry.addData("yellow", "false");
            }

            telemetry.addData("Red", "%.3f", colors.red);
            telemetry.addData("Green", "%.3f", colors.green);
            telemetry.addData("Klüber/Blue", "%.3f", colors.blue);
            telemetry.addData("Distance (cm)", "%.1f", distance);
            telemetry.update();
            sleep(1000);






        }
    }

}