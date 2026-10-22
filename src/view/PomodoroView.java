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


    }
}
