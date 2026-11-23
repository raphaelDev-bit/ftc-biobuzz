package org.firstinspires.ftc.teamcode.BioBuzz.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.teamcode.BioBuzz.TeleOp.EditionMode.Edition;
import org.firstinspires.ftc.teamcode.BioBuzz.utils.packageImport;

@TeleOp(name = "Test Edition Mode", group = "TeleOp")
public class TeleOpBioBuzz extends LinearOpMode {

    // 1. Vos trois objets Edition avec leurs valeurs de base respectives
    private Edition driveEdition = new Edition(0.5);
    private Edition intakeEdition = new Edition(0.5);
    private Edition launcherEdition = new Edition(4.0);

    private packageImport robotComposant = new packageImport();

    @Override
    public void runOpMode() {
        // Initialisation du robot
        robotComposant.importMovementMotor(hardwareMap);

        robotComposant.leftDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        robotComposant.rightDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);




        teleologMessage("Statut", "Prêt");
        waitForStart();

        while (opModeIsActive()) {

            // Récupération du joystick gauche (Y pour avancer/reculer, X pour tourner)
            float left_stick_y = gamepad1.left_stick_y;
            float left_stick_x = gamepad1.left_stick_x;

            // Gestion du mode édition (avec les boutons B, X, Y)
            driveEdition.EditionMode(gamepad1, gamepad1.b, 0.20);
            intakeEdition.EditionMode(gamepad1, gamepad1.x, 0.20);
            launcherEdition.EditionMode(gamepad1, gamepad1.y, 0.20);

            // Récupération des valeurs d'édition actuelles
            double driveValue = driveEdition.getValue();
            double intakeValue = intakeEdition.getValue();
            double launcherValue = launcherEdition.getValue();


            if (Math.abs(left_stick_y) > 0.1 || Math.abs(left_stick_x) > 0.1) {
                double powerLeft  = (-left_stick_y + left_stick_x) * driveValue;
                double powerRight = (-left_stick_y - left_stick_x) * driveValue;

                robotComposant.leftDrive.setPower(powerLeft);
                robotComposant.rightDrive.setPower(powerRight);
            } else {
                robotComposant.rightDrive.setPower(0.0);
                robotComposant.leftDrive.setPower(0.0);
            }

            // --- 2. CONTRÔLE DE L'INTAKE MOTEUR (Gâchette gauche) ---
            if (gamepad1.left_trigger > 0.3) {
                robotComposant.intakeMotor.setPower(5);
            } else {
                robotComposant.intakeMotor.setPower(0.0);
            }


            if (gamepad1.right_trigger > 0.3) {
                robotComposant.launcherMotor.setPower(launcherValue);
            } else {
                robotComposant.launcherMotor.setPower(0.0);
            }
            if(gamepad1.dpad_left){
                robotComposant.windmillServo.setPower(0.5);
            }



            robotComposant.intakeServoRight.setPower(0.5);
            robotComposant.intakeServoLeft.setPower(-0.5);




            // --- MENU TELEMETRY PRO ---
            telemetry.addLine("=== BIOBUZZ TELEOP | EDITION & MAPPAGE ===");
            telemetry.addData("Drive [B] " + (driveEdition.isEditionModeActive() ? "[ACTIF]" : "[--]"), "%.2f  <- Stick G (X/Y)", driveValue);
            telemetry.addData("Intake [X] " + (intakeEdition.isEditionModeActive() ? "[ACTIF]" : "[--]"), "%.2f  <- Gachette G (LT) [%s]", intakeValue, gamepad1.left_trigger > 0.3 ? "ON" : "OFF");
            telemetry.addData("Launcher [Y] " + (launcherEdition.isEditionModeActive() ? "[ACTIF]" : "[--]"), "%.2f  <- Gachette D (RT) [%s]", launcherValue, gamepad1.right_trigger > 0.3 ? "ON" : "OFF");

            telemetry.addLine("--- Moteurs & Servos ---");
            telemetry.addLine("leftDrive / rightDrive : Stick G (X/Y) | Edit: [B]");
            telemetry.addLine("intakeMotor            : Gachette G (LT)  | Edit: [X]");
            telemetry.addLine("launcherMotor          : Gachette D (RT)  | Edit: [Y]");
            telemetry.addLine("intakeServoL/R + windm.: Continu auto (aucun bouton)");
            telemetry.addLine("Ajustement valeur      : D-Pad Haut / Bas");
            telemetry.update();
        }
    }

    private void teleologMessage(String caption, String value) {
        telemetry.addData(caption, value);
        telemetry.update();
    }
}