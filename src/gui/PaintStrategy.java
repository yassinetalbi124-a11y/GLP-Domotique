package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import config.SimulationConfiguration;
import engine.item.Light;
import engine.item.Switch;
import engine.map.Block;
import engine.map.Map;
import engine.map.RoomManager;
import engine.mobile.Master;
import engine.process.GameUtility;

public class PaintStrategy {
    private static final Color outsideColor = new Color(55, 48, 40);
    private static final Color masterColor = new Color(0, 85, 255);

    private static final BufferedImage wallAndGround = GameUtility.readImage("src/images/WallandGround.png");
    private static final BufferedImage wallMaker = GameUtility.readImage("src/images/WalllMaker.png");

    private static final BufferedImage bedroomFloor = extract(wallAndGround, 0, 0, 32, 32);
    private static final BufferedImage kitchenFloor = extract(wallAndGround, 64, 32, 32, 32);
    private static final BufferedImage bathroomFloor = extract(wallAndGround, 96, 96, 32, 32);
    private static final BufferedImage corridorFloor = extract(wallAndGround, 64, 64, 32, 32);

    private static final BufferedImage horizontalWall = extract(wallMaker, 0, 0, 32, 32);
    private static final BufferedImage verticalWall = extract(wallMaker, 32, 0, 32, 32);

    private static BufferedImage extract(BufferedImage image, int x, int y, int width, int height) {
        BufferedImage sprite = null;

        if (image != null) {
            sprite = image.getSubimage(x, y, width, height);
        }

        return sprite;
    }

    public void paint(Map map, Graphics graphics) {
        Graphics2D g2 = (Graphics2D) graphics;
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);

        RoomManager roomManager = map.getRoomManager();

        paintOutside(map, g2);
        paintFloors(map, roomManager, g2);
        paintWalls(map, roomManager, g2);
        paintDoors(roomManager, g2);
    }

    private void paintOutside(Map map, Graphics graphics) {
        for (int line = 0; line < map.getLineCount(); line++) {
            for (int column = 0; column < map.getColumnCount(); column++) {
                paintColorBlock(column, line, outsideColor, graphics);
            }
        }
    }

    private void paintFloors(Map map, RoomManager roomManager, Graphics graphics) {
        for (int line = 0; line < map.getLineCount(); line++) {
            for (int column = 0; column < map.getColumnCount(); column++) {
                Block block = map.getBlock(line, column);

                if (roomManager.isInBedroom(block) || roomManager.isInLivingRoom(block)) {
                    paintTile(column, line, bedroomFloor, graphics);
                }
                else if (roomManager.isInKitchen(block)) {
                    paintTile(column, line, kitchenFloor, graphics);
                }
                else if (roomManager.isInBathroom(block)) {
                    paintTile(column, line, bathroomFloor, graphics);
                }
                else if (roomManager.isInCorridor(block)) {
                    paintTile(column, line, corridorFloor, graphics);
                }
            }
        }
    }

    private void paintWalls(Map map, RoomManager roomManager, Graphics graphics) {
        for (int line = 0; line < map.getLineCount(); line++) {
            for (int column = 0; column < map.getColumnCount(); column++) {
                Block block = map.getBlock(line, column);

                if (roomManager.isWall(block) && !roomManager.isDoor(block)) {
                    if (isHorizontalWall(block, roomManager)) {
                        paintTile(column, line, horizontalWall, graphics);
                    }
                    else {
                        paintTile(column, line, verticalWall, graphics);
                    }
                }
            }
        }
    }

    private boolean isHorizontalWall(Block block, RoomManager roomManager) {
        boolean horizontal = false;

        if (roomManager.isInBedroom(block)) {
            horizontal = block.getLine() == roomManager.getBedroom().getStartLine()
                    || block.getLine() == roomManager.getBedroom().getEndLine();
        }
        else if (roomManager.isInKitchen(block)) {
            horizontal = block.getLine() == roomManager.getKitchen().getStartLine()
                    || block.getLine() == roomManager.getKitchen().getEndLine();
        }
        else if (roomManager.isInBathroom(block)) {
            horizontal = block.getLine() == roomManager.getBathroom().getStartLine()
                    || block.getLine() == roomManager.getBathroom().getEndLine();
        }
        else if (roomManager.isInLivingRoom(block)) {
            horizontal = block.getLine() == roomManager.getLivingroom().getStartLine()
                    || block.getLine() == roomManager.getLivingroom().getEndLine();
        }

        return horizontal;
    }

    private void paintDoors(RoomManager roomManager, Graphics graphics) {
        paintDoor(roomManager.getBedroom().getDoor(), graphics);
        paintDoor(roomManager.getKitchen().getDoor(), graphics);
        paintDoor(roomManager.getBathroom().getDoor(), graphics);
        paintDoor(roomManager.getLivingroom().getDoor(), graphics);
    }

    private void paintDoor(Block block, Graphics graphics) {
        int blockSize = SimulationConfiguration.BLOCK_SIZE;
        int x = block.getColumn() * blockSize;
        int y = block.getLine() * blockSize;

        graphics.setColor(new Color(120, 85, 45));
        graphics.fillRect(x + blockSize / 4, y + blockSize / 4, blockSize / 2, blockSize / 2);
    }

    private void paintTile(int column, int line, BufferedImage image, Graphics graphics) {
        int blockSize = SimulationConfiguration.BLOCK_SIZE;
        int x = column * blockSize;
        int y = line * blockSize;

        if (image != null) {
            graphics.drawImage(image, x, y, blockSize, blockSize, null);
        }
        else {
            graphics.setColor(Color.PINK);
            graphics.fillRect(x, y, blockSize, blockSize);
        }
    }

    private void paintColorBlock(int column, int line, Color color, Graphics graphics) {
        int blockSize = SimulationConfiguration.BLOCK_SIZE;
        int x = column * blockSize;
        int y = line * blockSize;

        graphics.setColor(color);
        graphics.fillRect(x, y, blockSize, blockSize);
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
}