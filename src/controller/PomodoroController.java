package controller;

import model.PomodoroModel;
import view.BeallitasokDialogus;
import view.PomodoroView;

import javax.swing.*;

public class PomodoroController {
    private final PomodoroModel model;
    private final PomodoroView view;
    private final Timer timer;

    public PomodoroController(PomodoroModel model, PomodoroView view) {
        this.model = model;
        this.view = view;

        timer = new Timer(1000, e -> model.tick());

        view.getStartGomb().addActionListener(e -> {
            model.start();
            timer.start();
        });

        view.getSzunetGomb().addActionListener(e -> {
            model.szunet();
            timer.stop();
        });

        view.getResetGomb().addActionListener(e -> {
            model.reset();
            timer.restart();
        });

        view.getBeallitasokGomb().addActionListener(e -> beallitasokMegnyitasa());
    }

    private void beallitasokMegnyitasa() {
        timer.stop();

        BeallitasokDialogus dialogus = new BeallitasokDialogus(view, model.getMunkaPerc(), model.getRovidSzunetPerc(), model.getHosszuSzunetPerc());
        dialogus.setVisible(true);

        if(dialogus.isMentve()) {
            model.idokBeallitasa(dialogus.getMunkaPerc(), dialogus.getRovidSzunetPerc(), dialogus.getHosszuSzunetPerc());
        }
    }
}
