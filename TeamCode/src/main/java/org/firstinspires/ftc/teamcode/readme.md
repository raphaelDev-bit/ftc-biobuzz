# BioBuzz — code du robot FTC

Ce dossier contient le code personnalisé de l’équipe BioBuzz pour un robot **FIRST Tech Challenge (FTC)**. Le projet est une application Android basée sur le SDK FTC, avec les modules `FtcRobotController` et `TeamCode`.

> Le code de l’équipe se trouve dans `TeamCode/src/main/java/org/firstinspires/ftc/teamcode/BioBuzz/`.

## OpModes disponibles

### `Test Edition Mode` — téléopération

Fichier : `TeleOp/TeleOpBioBuzz.java`

OpMode principal pour piloter le robot avec `gamepad1` :

| Commande | Fonction |
| --- | --- |
| Joystick gauche | Conduite : avant/arrière et rotation |
| `B` | Active ou désactive le réglage de la puissance de conduite |
| `X` | Active ou désactive le réglage de la puissance de l’intake |
| `Y` | Active ou désactive le réglage de la puissance du launcher |
| D-pad haut/bas | Augmente ou diminue la valeur du réglage sélectionné, par pas de `0.20` |
| Gâchette gauche | Actionne l’intake lorsque la pression dépasse `0.3` |
| Gâchette droite | Actionne le launcher lorsque la pression dépasse `0.3` |

Les valeurs de départ définies dans le code sont `1.0` pour la conduite, `0.5` pour l’intake et `4.0` pour le launcher. Vérifiez et ajustez ces valeurs sur le robot avant utilisation : les puissances moteur FTC sont normalement limitées à l’intervalle `[-1.0, 1.0]`. La télémétrie affiche les valeurs et l’état des modes de réglage.

### `OpMode V1 BioBuzz` — test du capteur de couleur

Fichier : `Autonomus/autonomus.java`

Malgré le nom du dossier, cette classe est actuellement annotée comme un **TeleOp** (`@TeleOp`). Elle lit le capteur `RevColorSensorV3`, affiche les valeurs RGB normalisées et la distance en centimètres, et indique si la couleur détectée semble jaune. L’intake est activé pendant l’exécution de cet OpMode.

### `TeleOpInitializerAndStart`

OpMode de base qui initialise les moteurs et attend le démarrage. Il ne contient pas encore de logique de conduite.

## Structure du code

```text
TeamCode/src/main/java/org/firstinspires/ftc/teamcode/BioBuzz/
├── Autonomus/
│   └── autonomus.java              # Test du capteur de couleur
├── TeleOp/
│   ├── TeleOpBioBuzz.java          # Téléopération principale
│   ├── TeleOpInitializerAndStart.java
│   ├── EditionMode/
│   │   └── Edition.java            # Réglage des puissances depuis le gamepad
│   └── Motors/
│       └── Drives.java             # Aide de conduite moteur
└── utils/
    ├── packageImport.java          # Récupération et configuration du matériel
    └── powerTime.java              # Fonction utilitaire de commande moteur temporisée
```

## Configuration matérielle

Les noms ci-dessous doivent correspondre exactement à ceux déclarés dans la configuration du Robot Controller :

| Nom configuré | Type attendu | Utilisation |
| --- | --- | --- |
| `left_drive` | `DcMotor` | Moteur gauche de la transmission |
| `right_drive` | `DcMotor` | Moteur droit de la transmission |
| `intake` | `DcMotor` | Intake |
| `launcher` | `DcMotor` | Launcher |
| `windmill` | `CRServo` | Servo continu |
| `color_sensor` | `RevColorSensorV3` | Capteur de couleur utilisé par `autonomus` |

`packageImport.importMovementMotor()` récupère les moteurs et le servo continu. Le moteur gauche est configuré en direction `FORWARD` et le moteur droit en direction `REVERSE`. Le capteur `color_sensor` est récupéré séparément par l’OpMode de test couleur.

## Compiler et lancer

1. Cloner le dépôt et l’ouvrir dans Android Studio comme projet Gradle.
2. Laisser Android Studio synchroniser Gradle et télécharger les dépendances nécessaires.
3. Vérifier la configuration matérielle sur le Robot Controller, notamment les noms des appareils ci-dessus.
4. Connecter le Driver Station au Robot Controller et déployer/lancer l’application FTC depuis Android Studio.
5. Sélectionner l’OpMode souhaité dans la liste du Driver Station.

Le dépôt inclut le wrapper Gradle (`gradlew` et `gradlew.bat`). Les dépendances du SDK FTC déclarées dans `build.dependencies.gradle` sont en version `12.0.0`.

## Sécurité et essais

- Surélever ou immobiliser le robot lors des premiers essais des moteurs et du launcher.
- Vérifier le sens de rotation des moteurs, le comportement des gâchettes et les valeurs de puissance avant de poser le robot au sol.
- Le servo continu `windmill` reçoit actuellement une puissance constante de `1.0` dans `TeleOpBioBuzz` ; vérifiez que ce comportement est voulu avant d’utiliser cet OpMode.
- L’OpMode de test couleur fait tourner l’intake et attend une seconde entre les lectures. Tester sans pièce ou mécanisme susceptible de se coincer.

## Références

- [Documentation FTC](https://ftc-docs.firstinspires.org/)
- [FIRST Tech Challenge](https://www.firstinspires.org/robotics/ftc)
