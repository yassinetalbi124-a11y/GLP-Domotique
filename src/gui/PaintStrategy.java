package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

import config.SimulationConfiguration;
import engine.map.Block;
import engine.map.Map;
import engine.mobile.Master;
import engine.item.Switch;
import engine.item.Light;

public class PaintStrategy {
	private static final Color mapColor = new Color(242, 206, 131);
	private static final Color masterColor = new Color(0, 85, 255);
    
    public void paint(Map map, Graphics graphics) {
        int blockSize = SimulationConfiguration.BLOCK_SIZE;

        for (int lineIndex = 0; lineIndex < map.getLineCount(); lineIndex++) {
            for (int columnIndex = 0; columnIndex < map.getColumnCount(); columnIndex++) {
            	graphics.setColor(mapColor);
                graphics.fillRect(columnIndex * blockSize, lineIndex * blockSize, blockSize, blockSize);
            }
        }
    }
    
    public void paint(Master master, Graphics graphics) {
        Block position = master.getPosition();
        int blockSize = SimulationConfiguration.BLOCK_SIZE;
        int x = position.getColumn() * blockSize;
        int y = position.getLine() * blockSize;
        
        graphics.setColor(masterColor);
        graphics.fillRect(x + blockSize/4, y + blockSize/4, 
                         blockSize/2, blockSize/2);
    }
    
    public void paint(Switch interrupter, Graphics graphics) {
        Block position = interrupter.getPosition();
        int blockSize = SimulationConfiguration.BLOCK_SIZE;
        
        int x = position.getColumn() * blockSize;
        int y = position.getLine() * blockSize;
       
        graphics.setColor(Color.DARK_GRAY);
        graphics.fillRect(x + blockSize/6, y + blockSize/6, 
                         4 * blockSize/6, 4 * blockSize/6);
        
        Light light = interrupter.getLight();
        if (light.isOn()) {
            graphics.setColor(Color.YELLOW);
        } else {
            graphics.setColor(Color.BLACK);  
        }
        graphics.fillRect(x + blockSize/3, y + blockSize/3, 
                         blockSize/3, blockSize/3);
    }
    
    
    public void paint(Block block, Color color, Graphics graphics) {
        int blockSize = SimulationConfiguration.BLOCK_SIZE;
        int x = block.getColumn() * blockSize;
        int y = block.getLine() * blockSize;
        
        graphics.setColor(color);
        graphics.fillRect(x, y, blockSize, blockSize);
    }
}