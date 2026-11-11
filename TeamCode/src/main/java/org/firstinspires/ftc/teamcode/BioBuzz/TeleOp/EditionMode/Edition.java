package org.firstinspires.ftc.teamcode.BioBuzz.TeleOp.EditionMode;

import com.qualcomm.robotcore.hardware.Gamepad;

public class Edition {

    private double value; // Modifié : plus initialisé à 0.0 par défaut ici
    private double basicValue;
    private boolean EditionModeActive = false;
    private boolean ancienStateButton = false;

    private boolean ancienDpadUp = false;
    private boolean ancienDpadDown = false;

    // NOUVEAU (v2) : Constructeur pour définir une valeur de base personnalisée par moteur
    public Edition(double initialBasicValue) {
        this.basicValue = initialBasicValue;
        this.value = 0.0; // Sera initialisé à basicValue au premier appel de getValue()
    }

    public void EditionMode(Gamepad gamepad, boolean stateButton, double pas){

        // Gestion de l'activation/désactivation du mode édition (bouton principal)
        boolean clickButton = stateButton && !ancienStateButton;
        ancienStateButton = stateButton;

        if(clickButton){
            EditionModeActive = !EditionModeActive;
        }

        if(EditionModeActive){

            // Détection du "clic unique" sur D-pad Haut
            boolean clickDpadUp = gamepad.dpad_up && !ancienDpadUp;
            ancienDpadUp = gamepad.dpad_up;

            // Détection du "clic unique" sur D-pad Bas
            boolean clickDpadDown = gamepad.dpad_down && !ancienDpadDown;
            ancienDpadDown = gamepad.dpad_down;

            if(clickDpadUp){
                value += pas; // Augmente d'un pas
            }
            else if(clickDpadDown){
                value -= pas; // Diminue d'un pas
            }
        }
    }

    public double getValue(){
        if(value == 0.0){
            value = basicValue;
        }
        return value;
    }

    public boolean isEditionModeActive(){
        return EditionModeActive;
    }

    public void setValue(double value){
        this.value = value;
    }
}