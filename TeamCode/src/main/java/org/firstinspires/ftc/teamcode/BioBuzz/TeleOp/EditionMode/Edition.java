package org.firstinspires.ftc.teamcode.BioBuzz.TeleOp.EditionMode;

import com.qualcomm.robotcore.hardware.Gamepad;

public class Edition {

    private double value = 0.0;
    private double basicValue = 1.0;
    private boolean EditionModeActive = false;
    private boolean ancienStateButton = false;

    // NOUVEAU : Variable pour mémoriser l'état précédent du D-pad haut
    private boolean ancienDpadUp = false;
    // NOUVEAU : Variable pour mémoriser l'état précédent du D-pad bas
    private boolean ancienDpadDown = false;

    public void EditionMode(Gamepad gamepad, boolean stateButton, double pas){

        // Gestion de l'activation/désactivation du mode édition (bouton principal)
        boolean clickButton = stateButton && !ancienStateButton;
        ancienStateButton = stateButton;

        if(clickButton){
            EditionModeActive = !EditionModeActive;
        }

        if(EditionModeActive){

            // NOUVEAU : Détection du "clic unique" sur D-pad Haut
            boolean clickDpadUp = gamepad.dpad_up && !ancienDpadUp;
            ancienDpadUp = gamepad.dpad_up;

            // NOUVEAU : Détection du "clic unique" sur D-pad Bas
            boolean clickDpadDown = gamepad.dpad_down && !ancienDpadDown;
            ancienDpadDown = gamepad.dpad_down;

            if(clickDpadUp){
                value += pas; // N'augmente qu'une seule fois par appui
            }
            else if(clickDpadDown){
                value -= pas; // N'diminue qu'une seule fois par appui
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