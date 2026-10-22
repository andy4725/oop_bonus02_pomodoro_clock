package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PomodoroModelTest {

    @Test
    void kezdetbenMunkaAllapotbanVan() {
        PomodoroModel model = new PomodoroModel(1, 1, 2);

        assertEquals(Allapot.MUNKA, model.getAllapot());
        assertEquals(60, model.getHatralevoMasodperc());
        assertEquals(0, model.getBefejezettKorok());
    }

    @Test
    void startFolytatjaASzuneteltAllapotot() {
        PomodoroModel model = new PomodoroModel(1, 1, 2);
        model.szunet();

        assertEquals(Allapot.SZUNETEL, model.getAllapot());

        model.start();

        assertEquals(Allapot.MUNKA, model.getAllapot());
    }

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

    @Test
    void szunetAlattNemCsokkenAzIdo() {
        PomodoroModel model = new PomodoroModel(1, 1, 2);
        model.szunet();
        model.tick();

        assertEquals(60, model.getHatralevoMasodperc());
    }
}