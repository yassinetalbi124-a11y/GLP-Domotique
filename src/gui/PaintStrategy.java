package gui;

import java.awt.Color;
import java.awt.Graphics;

import config.SimulationConfiguration;
import engine.item.Light;
import engine.item.Switch;
import engine.map.Block;
import engine.map.Map;
import engine.map.RoomManager;
import engine.mobile.Master;

public class PaintStrategy {
    private static final Color outsideColor = new Color(35, 35, 35);
    private static final Color corridorColor = new Color(210, 190, 150);
    private static final Color wallColor = new Color(95, 95, 95);
    private static final Color bathroomColor = new Color(173, 216, 230);
    private static final Color bedroomColor = new Color(221, 190, 230);
    private static final Color kitchenColor = new Color(255, 218, 185);
    private static final Color livingRoomColor = new Color(190, 230, 190);
    private static final Color doorColor = new Color(120, 70, 15);
    private static final Color gridColor = new Color(70, 70, 70);
    private static final Color masterColor = new Color(0, 85, 255);

    public void paint(Map map, Graphics graphics) {
        int blockSize = SimulationConfiguration.BLOCK_SIZE;
        RoomManager roomManager = map.getRoomManager();

        for (int lineIndex = 0; lineIndex < map.getLineCount(); lineIndex++) {
            for (int columnIndex = 0; columnIndex < map.getColumnCount(); columnIndex++) {
                Block block = map.getBlock(lineIndex, columnIndex);
                paintBlock(block, roomManager, graphics);

                graphics.setColor(gridColor);
                graphics.drawRect(columnIndex * blockSize, lineIndex * blockSize, blockSize, blockSize);
            }
        }
    }

    private void paintBlock(Block block, RoomManager roomManager, Graphics graphics) {
        Color color = outsideColor;

        if (roomManager.isWall(block)) {
            color = wallColor;
        }
        else if (roomManager.isDoor(block)) {
            color = doorColor;
        }
        else if (roomManager.isInBathroom(block)) {
            color = bathroomColor;
        }
        else if (roomManager.isInBedroom(block)) {
            color = bedroomColor;
        }
        else if (roomManager.isInKitchen(block)) {
            color = kitchenColor;
        }
        else if (roomManager.isInLivingRoom(block)) {
            color = livingRoomColor;
        }
        else if (roomManager.isInCorridor(block)) {
            color = corridorColor;
        }

        paint(block, color, graphics);
    }

    public void paint(Master master, Graphics graphics) {
        Block position = master.getPosition();
        int blockSize = SimulationConfiguration.BLOCK_SIZE;
        int x = position.getColumn() * blockSize;
        int y = position.getLine() * blockSize;

        graphics.setColor(masterColor);
        graphics.fillOval(x + blockSize / 4, y + blockSize / 4, blockSize / 2, blockSize / 2);
    }

    public void paint(Switch interrupter, Graphics graphics) {
        Block position = interrupter.getPosition();
        int blockSize = SimulationConfiguration.BLOCK_SIZE;

        int x = position.getColumn() * blockSize;
        int y = position.getLine() * blockSize;

        graphics.setColor(Color.DARK_GRAY);
        graphics.fillRect(x + blockSize / 6, y + blockSize / 6, 4 * blockSize / 6, 4 * blockSize / 6);

        Light light = interrupter.getLight();
        if (light.isOn()) {
            graphics.setColor(Color.YELLOW);
        }
        else {
            graphics.setColor(Color.BLACK);
        }

        graphics.fillRect(x + blockSize / 3, y + blockSize / 3, blockSize / 3, blockSize / 3);
    }

    public void paint(Block block, Color color, Graphics graphics) {
        int blockSize = SimulationConfiguration.BLOCK_SIZE;
        int x = block.getColumn() * blockSize;
        int y = block.getLine() * blockSize;

        graphics.setColor(color);
        graphics.fillRect(x, y, blockSize, blockSize);
    }
}