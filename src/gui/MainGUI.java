package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import config.*;
import engine.map.Map;
import engine.process.GameBuilder;
import engine.process.MobileInterface;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;



public class MainGUI extends JFrame implements Runnable{

	private static final long serialVersionUID = 1L;

	private Map map;

	private final static Dimension preferredSize = new Dimension(SimulationConfiguration.WINDOW_WIDTH, SimulationConfiguration.WINDOW_HEIGHT);

	private MobileInterface manager;
	
	private static final Font font = new Font(Font.MONOSPACED,Font.BOLD,18);

	private GameDisplay dashboard;
	
	private boolean stop = true;
	
	private MainGUI instance = this;
	
	private JButton startButton = new JButton(" Start ");
	private JButton clearButton = new JButton(" Clear ");
	
	private JPanel control = new JPanel();
	
	
	public MainGUI(String title) {
		super(title);
		init();
	}
	
	public void run() {
		while (!stop) {
			try {
				Thread.sleep(SimulationConfiguration.GAME_SPEED);
			} catch (InterruptedException e) {
				System.out.println(e.getMessage());
			}

			manager.nextRound();
			
			if(!stop) {
				dashboard.repaint();
			}
		}
	}
	
	private void updateValues() {
		map = GameBuilder.buildMap();
		manager = GameBuilder.buildInitMobile(map);
		dashboard.setModel(map, manager);
		dashboard.repaint();
	}

	
	public void init() {
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());
		map = GameBuilder.buildMap();
		manager = GameBuilder.buildInitMobile(map);
		dashboard = new GameDisplay(map, manager);
		dashboard.setPreferredSize(preferredSize);
		contentPane.add(dashboard, BorderLayout.CENTER);
		control.setLayout(new FlowLayout(FlowLayout.CENTER));
		startButton.setFont(font);
		startButton.addActionListener(new StartStopAction());
		control.add(startButton);
		clearButton.setFont(font);
		clearButton.addActionListener(new ClearAction());
		control.add(clearButton);
		contentPane.add(control,BorderLayout.NORTH);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		setVisible(true);
		setPreferredSize(preferredSize);
		setResizable(false);
	}
	
	
	private class StartStopAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(!stop) {
				stop = true;
				startButton.setText(" Start ");
			}
			else {
				stop = false;
				startButton.setText(" Pause ");
				Thread simThread = new Thread(instance);
				simThread.start();
			}
		}
	}
	private class ClearAction implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			stop = true;
			startButton.setText(" Start ");
			updateValues();
		}
	}

}
