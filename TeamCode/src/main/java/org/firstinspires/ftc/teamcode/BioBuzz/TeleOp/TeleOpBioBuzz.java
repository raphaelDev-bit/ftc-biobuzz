package org.firstinspires.ftc.teamcode.BioBuzz.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.BioBuzz.TeleOp.EditionMode.Edition;

@TeleOp(name = "Test Edition Mode", group = "TeleOp")
public class TeleOpBioBuzz extends LinearOpMode {

    // 1. Instanciation de votre classe Edition
    private Edition edition = new Edition();

    @Override
    public void runOpMode() {
        // Initialisation du robot
        teleologMessage("Statut", "Prêt");
        waitForStart();

        while (opModeIsActive()) {

            // 2. Appel de la fonction dans la boucle principale
            // - gamepad1 : la manette à écouter
            // - gamepad1.a : le bouton 'A' active/désactive le mode édition
            // - 0.05 : le pas d'incrémentation (ajustez selon vos besoins)
            edition.EditionMode(gamepad1, gamepad1.a, 0.05);

            // 3. Récupération de la valeur et de l'état pour les utiliser ou les afficher
            double currentValue = edition.getValue();
            boolean isEditing = edition.isEditionModeActive();

            // Exemple : Utiliser la valeur pour un servo ou un moteur
            // servo.setPosition(currentValue);

            // Affichage sur le Driver Station pour le débogage
            telemetry.addData("Mode Édition Actif", isEditing);
            telemetry.addData("Valeur Actuelle", currentValue);
            telemetry.addLine("Utilisez D-pad Haut / Bas pour modifier la valeur");
            telemetry.update();
        }
    }

    private void teleologMessage(String caption, String value) {
        telemetry.addData(caption, value);
        telemetry.update();
    }
}