import controller.PomodoroController;
import model.PomodoroModel;
import view.PomodoroView;

import javax.swing.SwingUtilities;

public class App {
    public static void main(String[] args) throws Exception {
        SwingUtilities.invokeLater(() -> {
            PomodoroModel model = new PomodoroModel();
            PomodoroView view = new PomodoroView();

            model.feliratkoz(view);

            new PomodoroController(model, view);
            view.setVisible(true);
        });
    }
}
