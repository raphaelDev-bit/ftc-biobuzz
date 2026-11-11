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

            // 2. Gestion de l'édition pour chaque moteur avec un bouton distinct :
            // - Bouton B pour le châssis (Drive)
            // - Bouton X pour l'Intake
            // - Bouton Y pour le Launcher
            driveEdition.EditionMode(gamepad1, gamepad1.b, 0.20);
            intakeEdition.EditionMode(gamepad1, gamepad1.x, 0.20);
            launcherEdition.EditionMode(gamepad1, gamepad1.y, 0.20);

            // 3. Récupération des valeurs propres à chaque objet
            double driveValue = driveEdition.getValue();
            double intakeValue = intakeEdition.getValue();
            double launcherValue = launcherEdition.getValue();

            // Exemple d'utilisation dans les commandes du robot
            if(gamepad1.a){
                robotComposant.leftDrive.setPower(driveValue);
                robotComposant.rightDrive.setPower(driveValue);
            }
            else if(gamepad1.left_trigger > 0.3){
                robotComposant.intakeMotor.setPower(intakeValue);
            }
            else if(gamepad1.right_trigger > 0.3){
                // Utilisation de launcherValue au lieu d'une valeur fixe
                robotComposant.launcherMotor.setPower(launcherValue);
            }
            else{
                robotComposant.rightDrive.setPower(0.0);
                robotComposant.leftDrive.setPower(0.0);
                robotComposant.intakeMotor.setPower(0.0);
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