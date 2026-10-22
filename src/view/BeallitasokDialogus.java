package view;

import javax.swing.*;
import java.awt.*;

public class BeallitasokDialogus extends JDialog {
    private final JSpinner munka;
    private final JSpinner rovidSzunet;
    private final JSpinner hosszuSzunet;

    private boolean mentve;

    public BeallitasokDialogus(JFrame szulo, int munkaPerc, int rovidSzunetPerc, int hosszuSzunetPerc) {
        super(szulo, "Beallitasok", true);

        setLayout(new GridLayout(4, 2, 8, 8));

        munka = new JSpinner(new SpinnerNumberModel(munkaPerc, 1, 180, 1));
        rovidSzunet = new JSpinner(new SpinnerNumberModel(rovidSzunetPerc, 1, 60, 1));
        hosszuSzunet = new JSpinner(new SpinnerNumberModel(hosszuSzunetPerc, 1, 120, 1));

        add(new JLabel("Munka (perc)"));
        add(munka);
        add(new JLabel("Rovid szunet (perc)"));
        add(rovidSzunet);
        add(new JLabel("Hosszu szunet (perc)"));
        add(hosszuSzunet);

        JButton mentes = new JButton("Mentes");
        JButton megse = new JButton("Megse");

        mentes.addActionListener(e -> {
            mentve = true;
            dispose();
        });
        megse.addActionListener(e -> dispose());

        add(mentes);
        add(megse);

        pack();
        setLocationRelativeTo(szulo);
    }

    public boolean isMentve() {
        return mentve;
    }

    public int getMunkaPerc() {
        return (int) munka.getValue();
    }

    public int getRovidSzünetPerc() {
        return (int) rovidSzunet.getValue();
    }

    public int getHosszuSzünetPerc() {
        return (int) hosszuSzunet.getValue();
    }
}
