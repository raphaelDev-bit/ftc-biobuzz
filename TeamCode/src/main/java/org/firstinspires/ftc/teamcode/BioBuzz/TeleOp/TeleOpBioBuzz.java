package org.firstinspires.ftc.teamcode.BioBuzz.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.teamcode.BioBuzz.TeleOp.EditionMode.Edition;
import org.firstinspires.ftc.teamcode.BioBuzz.utils.packageImport;

@TeleOp(name = "Test Edition Mode", group = "TeleOp")
public class TeleOpBioBuzz extends LinearOpMode {

    // 1. Vos trois objets Edition avec leurs valeurs de base respectives
    private Edition driveEdition = new Edition(1.0);
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

            // --- 1. CONTRÔLE DE LA CONDUITE (Joystick unique + Mode Édition) ---
            if (Math.abs(left_stick_y) > 0.1 || Math.abs(left_stick_x) > 0.1) {
                double powerLeft  = (-left_stick_y + left_stick_x) * driveValue;
                double powerRight = (-left_stick_y - left_stick_x) * driveValue;

                robotComposant.leftDrive.setPower(powerLeft);
                robotComposant.rightDrive.setPower(powerRight);
            } else {
                robotComposant.rightDrive.setPower(0.0);
                robotComposant.leftDrive.setPower(0.0);
            }

            // --- 2. CONTRÔLE DE L'INTAKE (Gâchette gauche) ---
            if (gamepad1.left_trigger > 0.3) {
                robotComposant.intakeMotor.setPower(intakeValue);
            } else {
                robotComposant.intakeMotor.setPower(0.0);
            }

            // --- 3. CONTRÔLE DU LAUNCHER (Gâchette droite) ---
            if (gamepad1.right_trigger > 0.3) {
                robotComposant.launcherMotor.setPower(launcherValue);
            } else {
                robotComposant.launcherMotor.setPower(0.0);
            }

            
            robotComposant.windmillServo.setPower(1.0);



            // Affichage sur le Driver Station pour suivre les réglages en direct
            telemetry.addData("Drive [B] (Actif: " + driveEdition.isEditionModeActive() + ")", driveValue);
            telemetry.addData("Intake [X] (Actif: " + intakeEdition.isEditionModeActive() + ")", intakeValue);
            telemetry.addData("Launcher [Y] (Actif: " + launcherEdition.isEditionModeActive() + ")", launcherValue);
            telemetry.addLine("Joystick Gauche = Conduite | B/X/Y + D-pad = Réglages");
            telemetry.update();
        }
    }

    private void teleologMessage(String caption, String value) {
        telemetry.addData(caption, value);
        telemetry.update();
    }
}