package gui;
import java.awt.Graphics;

import engine.map.*;
import engine.mobile.*;
import gui.PaintStrategy.*;
import engine.process.*;
import javax.swing.*;
import engine.item.Switch;

public class GameDisplay extends JPanel {
	private static final long serialVersionUID = 1L;

	private Map map;
	private MobileInterface manager;
	private PaintStrategy paintStrategy = new PaintStrategy();

	public GameDisplay(Map map, MobileInterface manager) {
		this.map = map;
		this.manager = manager;
	}
	
	public void setModel(Map map, MobileInterface manager) {
		this.map = map;
		this.manager = manager;
	}
	
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		
		paintStrategy.paint(map, g);
		
		
		Switch interrupter = manager.getSwitch();
		paintStrategy.paint(interrupter,g);
		
		Master master = manager.getMaster();
		paintStrategy.paint(master,g);
	}
	
	
		
}
