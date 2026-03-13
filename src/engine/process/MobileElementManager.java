package engine.process;

import engine.item.Switch;
import engine.map.Block;
import engine.map.Map;
import engine.mobile.Master;

public class MobileElementManager implements MobileInterface {
	private Master master;
	private Map map;
	private Switch interrupter;
	private boolean pressed = false;

	public MobileElementManager(Map map) {
		this.map = map;
	}

	public void set(Master master) {
		this.master = master;
	}

	public void set(Switch interrupter) {
		this.interrupter = interrupter;
	}

	public void nextRound() {
		master.updateStates();

		Block masterPosition = master.getPosition();
		Block switchPosition = interrupter.getPosition();

		if (!isNear(masterPosition, switchPosition)) {
			moveCloser(switchPosition);
			pressed = false;
		}

		masterPosition = master.getPosition();

		if (isNear(masterPosition, switchPosition) && (!pressed)) {
			interrupter.toggle();
			pressed = true;
		}
	}

	public boolean isNear(Block position1, Block position2) {
		int absoluteColumn = Math.abs(position1.getColumn() - position2.getColumn());
		int absoluteLine = Math.abs(position1.getLine() - position2.getLine());
		return absoluteColumn <= 1 && absoluteLine <= 1;
	}

	public void moveCloser(Block target) {
		Block currentPosition = master.getPosition();
		int currentLine = currentPosition.getLine();
		int currentColumn = currentPosition.getColumn();
		int targetLine = target.getLine();
		int targetColumn = target.getColumn();
		boolean moved = false;

		if (currentColumn != targetColumn) {
			int columnShift;

			if (currentColumn < targetColumn) {
				columnShift = 1;
			}
			else {
				columnShift = -1;
			}

			moved = tryMove(currentLine, currentColumn + columnShift);
		}

		if ((!moved) && (currentLine != targetLine)) {
			int lineShift;

			if (currentLine < targetLine) {
				lineShift = 1;
			}
			else {
				lineShift = -1;
			}

			moved = tryMove(currentLine + lineShift, currentColumn);
		}
	}

	public boolean tryMove(int line, int column) {
		boolean moved = false;

		if (map.isInside(line, column)) {
			Block newPosition = map.getBlock(line, column);

			if (!map.getRoomManager().isWall(newPosition) && map.getRoomManager().canMoveOn(newPosition)) {
				master.setPosition(newPosition);
				moved = true;
			}
		}

		return moved;
	}

	public Master getMaster() {
		return master;
	}

	public Switch getSwitch() {
		return interrupter;
	}
}