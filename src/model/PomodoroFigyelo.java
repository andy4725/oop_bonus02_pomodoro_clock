package model;

// Receives timer updates from the model, for example to refresh the view.
public interface PomodoroFigyelo {
    // Called with the remaining time after a timer tick.
    void tikk(int hatralevoMasodperc);

    // Called when the active timer state changes.
    void allapotValtozott(Allapot uj);
}