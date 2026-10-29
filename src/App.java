import controller.PomodoroController;
import model.PomodoroModel;
import view.PomodoroView;

import javax.swing.SwingUtilities;

// Application entry point; starts the Swing interface on the event dispatch thread.
public class App {
    public static void main(String[] args) throws Exception {
        // Swing components and their event handlers must run on the event dispatch thread.
        SwingUtilities.invokeLater(() -> {
            PomodoroModel model = new PomodoroModel();
            PomodoroView view = new PomodoroView();

            model.feliratkoz(view);

            new PomodoroController(model, view);
            view.setVisible(true);
        });
    }
}
