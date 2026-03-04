package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class LanceurGUI extends JFrame {
    private JButton play = new JButton(" Play ");
    private JButton credits = new JButton(" Crédits ");
    private JButton quitter = new JButton(" Quitter ");
    private Color backroundColor = new Color(20,41,73);

    private static final Font font = new Font(Font.MONOSPACED,Font.BOLD,18);
    private JPanel control = new JPanel(new GridLayout(3,1,10,10));

    public LanceurGUI(){
        super("Simulation Domotique");
        init();
    }

    public void init(){
        play.addActionListener(new LancerAction());
        play.setForeground(Color.WHITE);
        play.setBackground(backroundColor);

        credits.addActionListener(new CreditsAction());
        credits.setForeground(Color.WHITE);
        credits.setBackground(backroundColor);

        quitter.addActionListener(new QuitAction());
        quitter.setForeground(Color.WHITE);
        quitter.setBackground(backroundColor);

        control.setBackground(backroundColor);
        control.setName(" Menu ");
        control.add(play);
        control.add(credits);
        control.add(quitter);
        add(control);

        setBackground(backroundColor);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        pack();
        setSize(350,250);
        setVisible(true);
    }

    private class LancerAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            MainGUI gameMainGUI = new MainGUI("Simulation Domotique");

            Thread gameThread = new Thread(gameMainGUI);
            gameThread.start();
            dispose();
        }
    }

    private class CreditsAction implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            new CreditClass();
        }
    }

    private class QuitAction implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            System.exit(0);
        }
    }
}
