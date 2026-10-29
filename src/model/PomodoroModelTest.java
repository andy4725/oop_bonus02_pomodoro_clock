package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Verifies the model's timing, pause, and work/break cycle rules.
class PomodoroModelTest {

    // The timer starts in a work session with the configured duration and no completed sessions.
    @Test
    void kezdetbenMunkaAllapotbanVan() {
        PomodoroModel model = new PomodoroModel(1, 1, 2);

        assertEquals(Allapot.MUNKA, model.getAllapot());
        assertEquals(60, model.getHatralevoMasodperc());
        assertEquals(0, model.getBefejezettKorok());
    }

    // Resuming restores the state that was active before pausing.
    @Test
    void startFolytatjaASzuneteltAllapotot() {
        PomodoroModel model = new PomodoroModel(1, 1, 2);
        model.szunet();

        assertEquals(Allapot.SZUNETEL, model.getAllapot());

        model.start();

        assertEquals(Allapot.MUNKA, model.getAllapot());
    }

    // Completing a work session starts a short break.
    @Test
    void munkaUtanRovidSzunetKovetkezik() {
        PomodoroModel model = new PomodoroModel(1, 1, 2);

        for (int i = 0; i < 60; i++) {
            model.tick();
        }

        assertEquals(Allapot.ROVID_SZUNET, model.getAllapot());
        assertEquals(60, model.getHatralevoMasodperc());
        assertEquals(1, model.getBefejezettKorok());
    }

    // Four work sessions, including the intervening short breaks, lead to a long break.
    @Test
    void negyedikKorUtanHosszuSzunetKovetkezik() {
        PomodoroModel model = new PomodoroModel(1, 1, 2);

        for (int kor = 0; kor < 4; kor++) {
            for (int i = 0; i < 60; i++) {
                model.tick();
            }

            if (kor < 3) {
                for (int i = 0; i < 60; i++) {
                    model.tick();
                }
            }
        }

        assertEquals(Allapot.HOSSZU_SZUNET, model.getAllapot());
        assertEquals(120, model.getHatralevoMasodperc());
        assertEquals(4, model.getBefejezettKorok());
    }

    // Reset restores the work state, original duration, and completed-session count.
    @Test
    void resetVisszaallitjaAzAlapAllapotot() {
        PomodoroModel model = new PomodoroModel(1, 1, 2);
        model.tick();
        model.tick();
        model.reset();

        assertEquals(Allapot.MUNKA, model.getAllapot());
        assertEquals(60, model.getHatralevoMasodperc());
        assertEquals(0, model.getBefejezettKorok());
    }

    // Ticking while paused must not reduce the remaining time.
    @Test
    void szunetAlattNemCsokkenAzIdo() {
        PomodoroModel model = new PomodoroModel(1, 1, 2);
        model.szunet();
        model.tick();

        assertEquals(60, model.getHatralevoMasodperc());
    }
}