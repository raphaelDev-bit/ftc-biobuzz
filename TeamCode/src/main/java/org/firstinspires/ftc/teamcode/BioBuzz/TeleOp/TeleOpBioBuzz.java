package org.firstinspires.ftc.teamcode.BioBuzz.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
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
        teleologMessage("Statut", "Prêt");
        waitForStart();

        while (opModeIsActive()) {

            // Récupération des joysticks
            float left_stick_y = gamepad1.left_stick_y;
            float right_stick_y = gamepad1.right_stick_y;

            // Gestion du mode édition (avec les boutons B, X, Y)
            driveEdition.EditionMode(gamepad1, gamepad1.b, 0.20);
            intakeEdition.EditionMode(gamepad1, gamepad1.x, 0.20);
            launcherEdition.EditionMode(gamepad1, gamepad1.y, 0.20);

            // Récupération des valeurs d'édition
            double driveValue = driveEdition.getValue();
            double intakeValue = intakeEdition.getValue();
            double launcherValue = launcherEdition.getValue();

            // --- 1. CONTRÔLE DES MOTEURS DE CONDUITE (Tank Drive avec le mode édition) ---
            // On vérifie si on touche aux joysticks de conduite
            if (Math.abs(left_stick_y) > 0.1 || Math.abs(right_stick_y) > 0.1) {
                // CORRIGÉ : Ajout du '*', du '-' pour inverser, et suppression des tirets parasites
                robotComposant.leftDrive.setPower(-left_stick_y * driveValue);
                robotComposant.rightDrive.setPower(-right_stick_y * driveValue);
            }
            // Si on appuie sur le bouton A (autre mode de mouvement si besoin)
            else if (gamepad1.a) {
                robotComposant.leftDrive.setPower(driveValue);
                robotComposant.rightDrive.setPower(driveValue);
            }
            // Sinon, on arrête la conduite
            else {
                robotComposant.leftDrive.setPower(0.0);
                robotComposant.rightDrive.setPower(0.0);
            }

            // --- 2. CONTRÔLE DE L'INTAKE (Indépendant de la conduite) ---
            if (gamepad1.left_trigger > 0.3) {
                robotComposant.intakeMotor.setPower(intakeValue);
            } else {
                robotComposant.intakeMotor.setPower(0.0);
            }

            // --- 3. CONTRÔLE DU LAUNCHER (Indépendant de la conduite) ---
            if (gamepad1.right_trigger > 0.3) {
                robotComposant.launcherMotor.setPower(launcherValue);
            } else {
                robotComposant.launcherMotor.setPower(0.0);
            }

            // Affichage sur le Driver Station pour suivre les 3 réglages
            telemetry.addData("Drive [B] (Actif: " + driveEdition.isEditionModeActive() + ")", driveValue);
            telemetry.addData("Intake [X] (Actif: " + intakeEdition.isEditionModeActive() + ")", intakeValue);
            telemetry.addData("Launcher [Y] (Actif: " + launcherEdition.isEditionModeActive() + ")", launcherValue);
            telemetry.addLine("Appuyez sur B, X ou Y puis utilisez D-pad Haut/Bas pour modifier");
            telemetry.update();
        }
    }

    private void teleologMessage(String caption, String value) {
        telemetry.addData(caption, value);
        telemetry.update();
    }
}