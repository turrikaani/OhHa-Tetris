package StartMenu;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.WindowConstants;

public class GravitySelectionWindow implements Runnable, ActionListener {

    private JFrame window;
    private int gravityLevelChoice;
    private JRadioButton[] radioButtons;

    public GravitySelectionWindow() {
    }

    public void run() {

        window = new JFrame("Gravity Level Selection");
        window.setResizable(false);
        window.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);

        createWindowContents(window.getContentPane());

        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(false);
    }

    private void createWindowContents(Container container) {

        container.setLayout(new BorderLayout());

        JPanel indentationPanel = new JPanel();
        indentationPanel.setPreferredSize(new Dimension(16, 86));

        JPanel infoTextPanel = new GravitySelectionPanel();
        infoTextPanel.setPreferredSize(new Dimension(316, 88));

        JPanel radioButtonPanel = new JPanel();
        radioButtonPanel.setPreferredSize(new Dimension(300, 86));
        createRadioButtons(radioButtonPanel);

        JPanel okCancelButtonPanel = new JPanel();
        createOkCancelButtons(okCancelButtonPanel);

        container.add(indentationPanel, BorderLayout.WEST);
        container.add(infoTextPanel, BorderLayout.NORTH);
        container.add(radioButtonPanel, BorderLayout.CENTER);
        container.add(okCancelButtonPanel, BorderLayout.SOUTH);
    }

    private void createRadioButtons(JPanel radioButtonPanel) {

        radioButtonPanel.setLayout(new GridLayout(2, 5));

        radioButtons = new JRadioButton[10];
        ButtonGroup radioButtonGroup = new ButtonGroup();

        for (int i=0; i<10; i++) {
            radioButtons[i] = new JRadioButton(String.format("%d", i+1));
            radioButtons[i].setActionCommand(String.format("%d", i+1));
            radioButtons[i].addActionListener(this);
            radioButtonGroup.add(radioButtons[i]);
            radioButtonPanel.add(radioButtons[i]);
        }
    }

    private void createOkCancelButtons(JPanel okCancelButtonPanel) {

        okCancelButtonPanel.setLayout(new GridLayout(1, 2));

        JButton saveSelectionButton = new JButton("Save Selection");
        saveSelectionButton.setActionCommand("Save Selection");
        saveSelectionButton.addActionListener(this);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setActionCommand("Cancel");
        cancelButton.addActionListener(this);

        okCancelButtonPanel.add(saveSelectionButton);
        okCancelButtonPanel.add(cancelButton);
    }

    public void actionPerformed(ActionEvent ae) {

        String command = ae.getActionCommand();

        if (command.equals("Save Selection")) {
            MainMenuWindow.gravityLevel = gravityLevelChoice;
            window.setVisible(false);
        }

        else if (command.equals("Cancel")) {
            window.setVisible(false);
        }

        else {
            gravityLevelChoice = Integer.parseInt(command);
        }
    }

    public void openWindow(int defaultChoice) {

        gravityLevelChoice = defaultChoice;
        radioButtons[defaultChoice-1].setSelected(true);
        window.setVisible(true);
    }

    public void closeWindow() {
        window.setVisible(false);
    }
}
