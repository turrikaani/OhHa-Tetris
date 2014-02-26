package StartMenu;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

public class MainMenuWindow implements Runnable, ActionListener {

    private JFrame window;
    private GravitySelectionWindow gravitySelectionWindow;
    public static int gravityLevel = 5;

    public MainMenuWindow() {
    }

    public void run() {

        window = new JFrame("jTetris 1.0");
        window.setResizable(false);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        createWindowContents(window.getContentPane());

        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gravitySelectionWindow = new GravitySelectionWindow();
        SwingUtilities.invokeLater(gravitySelectionWindow);
    }

    private void createWindowContents(Container container) {

        container.setLayout(new BorderLayout());

        JPanel infoPanel = new MainMenuPanel();
        JPanel buttonPanel = new JPanel();

        infoPanel.setPreferredSize(new Dimension(500, 350));
        addButtons(buttonPanel);

        container.add(infoPanel, BorderLayout.CENTER);
        container.add(buttonPanel, BorderLayout.SOUTH);
    }

    private void addButtons(JPanel panel) {

        panel.setLayout(new GridLayout(1, 3));

        JButton newGameButton = new JButton("Start New Game");
        JButton selectGravityButton = new JButton("Select Gravity Level");
        JButton quitButton = new JButton("Quit");

        newGameButton.setActionCommand("Start New Game");
        selectGravityButton.setActionCommand("Select Gravity Level");
        quitButton.setActionCommand("Quit");

        newGameButton.addActionListener(this);
        selectGravityButton.addActionListener(this);
        quitButton.addActionListener(this);

        panel.add(newGameButton);
        panel.add(selectGravityButton);
        panel.add(quitButton);
    }

    public void actionPerformed(ActionEvent ae) {

        String command = ae.getActionCommand();

        if (command.equals("Start New Game")) {

            window.setVisible(false);
            gravitySelectionWindow.closeWindow();
            GameStarter gameStarter = new GameStarter(gravityLevel, this);
            Thread t = new Thread(gameStarter);
            t.start();
        }

        else if (command.equals("Select Gravity Level")) {

            gravitySelectionWindow.openWindow(gravityLevel);
        }

        else if (command.equals("Quit")) {

            System.exit(0);
        }
    }

    public void openWindow() {

        window.setVisible(true);
    }
}
