# Bank System
The end goal of this banking system is to have an app with a GUI where the user can log in, create accounts, and transfer money.

You MUST have the JavaFX SDK installed for this to compile and run.

To compile:
javac --module-path $PATH_TO_FX --add-modules javafx.controls *.java

To run the app:
java --module-path $PATH_TO_FX --add-modules javafx.controls App

Currently, the only functionality is creating accounts and logging in to existing accounts. Data is not currently saved between program runs.

The ReadDriver and WriteDriver are proof of concept for how we will serialize the files to save information permanately. They do not currently serve any function.