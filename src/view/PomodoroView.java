package view;

import model.Allapot;
import model.PomodoroFigyelo;

import javax.swing.*;
import java.awt.*;

public class PomodoroView extends JFrame implements PomodoroFigyelo {
    private final JLabel idoFelirat = new JLabel("25:00", SwingConstants.CENTER);
    private final JLabel allapotFelirat = new JLabel("Munka", SwingConstants.CENTER);

    private final JButton startGomb = new JButton("Start");
    private final JButton szunetGomb = new JButton("Szunet");
    private final JButton resetGomb = new JButton("Reset");
    private final JButton beallitasokGomb = new JButton("Beallitasok");

    public PomodoroView() {
        super("Pomodoro ora");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(420, 280);
        setLocationRelativeTo(null);

        idoFelirat.setFont(new Font("SansSerif", Font.BOLD, 64));
        allapotFelirat.setFont(new Font("SansSerif", Font.PLAIN, 24));

        JPanel tartalom = new JPanel(new BorderLayout(10, 10));
        tartalom.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel kozep = new JPanel(new GridLayout(2, 1));
        kozep.add(idoFelirat);
        kozep.add(allapotFelirat);
        tartalom.add(kozep, BorderLayout.CENTER);

        JPanel gombok = new JPanel();
        gombok.add(startGomb);
        gombok.add(szunetGomb);
        gombok.add(resetGomb);
        gombok.add(beallitasokGomb);
        tartalom.add(gombok, BorderLayout.SOUTH);

        setContentPane(tartalom);
        frissitSzin(Allapot.MUNKA);
    }

    public JButton getStartGomb() {
        return startGomb;
    }

    public JButton getSzunetGomb() {
        return szunetGomb;
    }

    public JButton getResetGomb() {
        return resetGomb;
    }

    public JButton getBeallitasokGomb() {
        return beallitasokGomb;
    }

    @Override 
    public void tikk(int masodperc) {
        int perc = masodperc / 60;
        int mp = masodperc % 60;
        idoFelirat.setText(String.format("%02d:%02d", perc, mp));
    }

    @Override
    public void allapotValtozott(Allapot uj) {
        switch(uj) {
            case MUNKA -> {
                allapotFelirat.setText("Munka");
                frissitSzin(uj);
            }
            case ROVID_SZUNET -> {
                allapotFelirat.setText("Rovid szunet");
                frissitSzin(uj);
            }
            case HOSSZU_SZUNET -> {
                allapotFelirat.setText("Hosszu szunet");
                frissitSzin(uj);
            }
            case SZUNETEL -> allapotFelirat.setText("Szunetel");
        }
    }

    private void frissitSzin(Allapot allapot) {
        Color szin;

        if(allapot == Allapot.MUNKA) {
            szin = new Color(255, 255, 255);
        } else {
            szin = new Color(220, 245, 220);
        }

        getContentPane().setBackground(szin);
        idoFelirat.setOpaque(true);
        idoFelirat.setBackground(szin);
        allapotFelirat.setOpaque(true);
        allapotFelirat.setBackground(szin);
    }
}
