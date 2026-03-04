package gui;

import config.SimulationConfiguration;

import javax.swing.*;
import java.awt.*;



public class CreditClass extends JFrame{

    private Color backroundColor = new Color(20,41,73);
    private Font charFont = new Font("Serif",Font.BOLD,22);
    private Color charColor = new Color(2,136,209);


    public CreditClass(){
        super("Crédits");
        init();
    }


    public void init() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(backroundColor);

        JTextArea text = new JTextArea();
        text.setText("--- CREDITS Simulation Domotique ---\n" + "------\n" + "--- Yassine Ait Talb ---\n" +
                "--- Agnes ---\n" + "--- Mariam ---\n" + "--- L2 Informatique CY sup ---\n" +
                "------\n" + "--- FIN CREDITS --- ");
        text.setFont(charFont);
        text.setBackground(backroundColor);
        text.setForeground(charColor);

        panel.add(text, BorderLayout.CENTER);
        add(panel);
        dispose();
        pack();
        setVisible(true);
        setSize(1000, 1000);
        setResizable(false);
    }
}

