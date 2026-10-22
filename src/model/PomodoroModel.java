package model;

import java.util.ArrayList;
import java.util.List;

public class PomodoroModel {
    private final List<PomodoroFigyelo> figyelok = new ArrayList<>();

    private int munkaPerc;
    private int rovidSzunetPerc;
    private int hosszuSzunetPerc;

    private int hatralevoMasodperc;
    private int befejezettKorok;

    private Allapot allapot;
    private Allapot szunetElottiAllapot;

    public PomodoroModel() {
        this(25, 5, 15);
    }

    public PomodoroModel(int munkaPerc, int rovidSzunetPerc, int hosszuSzunetPerc) {
        this.munkaPerc = munkaPerc;
        this.rovidSzunetPerc = rovidSzunetPerc;
        this.hosszuSzunetPerc = hosszuSzunetPerc;

        this.allapot = Allapot.MUNKA;
        this.szunetElottiAllapot = Allapot.MUNKA;
        this.hatralevoMasodperc = munkaPerc * 60;
    }

    public void feliratkoz(PomodoroFigyelo figyelo) {
        if(figyelo != null && !figyelok.contains(figyelo)) {
            figyelok.add(figyelo);
        }
    }

    public void leiratkoz(PomodoroFigyelo figyelo) {
        figyelok.remove(figyelo);
    }

    public void start() {
        if(allapot == Allapot.SZUNETEL) {
            allapot = szunetElottiAllapot;
            allapotValtozott();
        }
    }

    public void szunet() {
        if(allapot != Allapot.SZUNETEL) {
            szunetElottiAllapot = allapot;
            allapot = Allapot.SZUNETEL;
            allapotValtozott();
        }
    }

    public void reset() {
        allapot = Allapot.MUNKA;
        szunetElottiAllapot = Allapot.MUNKA;
        hatralevoMasodperc = munkaPerc * 60;
        befejezettKorok = 0;
        allapotValtozott();
        tikkel();
    }

    public void tick() {
        if(allapot == Allapot.SZUNETEL) {
            return;
        }

        if(hatralevoMasodperc > 0) {
            hatralevoMasodperc--;
        }

        tikkel();

        if(hatralevoMasodperc == 0) {
            kovetkezoAllapot();
        }
    }

    private void kovetkezoAllapot() {
        if(allapot == Allapot.MUNKA) {
            befejezettKorok++;

            if(befejezettKorok % 4 == 0) {
                allapot = Allapot.HOSSZU_SZUNET;
                hatralevoMasodperc = hosszuSzunetPerc * 60;
            } else {
                allapot = Allapot.ROVID_SZUNET;
                hatralevoMasodperc = rovidSzunetPerc * 60;
            }
        } else {
            allapot = Allapot.MUNKA;
            hatralevoMasodperc = munkaPerc * 60;
        }

        szunetElottiAllapot = allapot;
        allapotValtozott();
        tikkel();
    }

    public void idokBeallitasa(int munkaPerc, int rovidSzunetPerc, int hosszuSzunetPerc) {
        if(munkaPerc < 1 || rovidSzunetPerc < 1 || hosszuSzunetPerc < 1) {
            throw new IllegalArgumentException("Az idoknek legalabb 1 percnek kell lenniuk.");
        }

        this.munkaPerc = munkaPerc;
        this.rovidSzunetPerc = rovidSzunetPerc;
        this.hosszuSzunetPerc = hosszuSzunetPerc;
        reset();
    }

    private void allapotValtozott() {
        for(PomodoroFigyelo figyelo: figyelok) {
            figyelo.allapotValtozott(allapot);
        }
    }

    private void tikkel() {
        for(PomodoroFigyelo figyelo: figyelok) {
            figyelo.tikk(hatralevoMasodperc);
        }
    }

    public Allapot getAllapot() {
        return allapot;
    }

    public int getHatralevoMasodperc() {
        return hatralevoMasodperc;
    }

    public int getBefejezettKorok() {
        return befejezettKorok;
    }

    public int getMunkaPerc() {
        return munkaPerc;
    }

    public int getRovidSzünetPerc() {
        return rovidSzunetPerc;
    }

    public int getHosszuSzünetPerc() {
        return hosszuSzunetPerc;
    }
}
