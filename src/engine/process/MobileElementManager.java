package engine.process;

import config.SimulationConfiguration;
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
		Block nextTarget = getNextTarget(target);
		moveStraightTo(nextTarget);
	}

	private Block getNextTarget(Block finalTarget) {
		Block currentPosition = master.getPosition();
		Block currentDoor = getCurrentRoomDoor(currentPosition);
		Block targetDoor = getTargetDoor(finalTarget);
		Block corridorTarget;

		if (isInSameArea(currentPosition, finalTarget)) {
			return finalTarget;
		}

		if (!map.getRoomManager().isInCorridor(currentPosition)) {
			if ((currentDoor != null) && !currentPosition.samePosition(currentDoor)) {
				return currentDoor;
			}

			if (currentDoor != null) {
				corridorTarget = getCorridorAccessBlock(currentDoor);

				if ((corridorTarget != null) && !currentPosition.samePosition(corridorTarget)) {
					return corridorTarget;
				}
			}
		}

		if (map.getRoomManager().isInCorridor(currentPosition)) {
			if (targetDoor != null) {
				corridorTarget = getCorridorAccessBlock(targetDoor);

				if ((corridorTarget != null) && !currentPosition.samePosition(corridorTarget)) {
					return corridorTarget;
				}

				if (!currentPosition.samePosition(targetDoor)) {
					return targetDoor;
				}
			}
		}

		return finalTarget;
	}

	private boolean isInSameArea(Block position1, Block position2) {
		boolean sameArea = false;

		if (map.getRoomManager().isInBedroom(position1) && map.getRoomManager().isInBedroom(position2)) {
			sameArea = true;
		}
		else if (map.getRoomManager().isInBathroom(position1) && map.getRoomManager().isInBathroom(position2)) {
			sameArea = true;
		}
		else if (map.getRoomManager().isInKitchen(position1) && map.getRoomManager().isInKitchen(position2)) {
			sameArea = true;
		}
		else if (map.getRoomManager().isInLivingRoom(position1) && map.getRoomManager().isInLivingRoom(position2)) {
			sameArea = true;
		}
		else if (map.getRoomManager().isInCorridor(position1) && map.getRoomManager().isInCorridor(position2)) {
			sameArea = true;
		}

		return sameArea;
	}

	private Block getCurrentRoomDoor(Block block) {
		Block door = null;

		if (map.getRoomManager().isInBedroom(block)) {
			door = map.getRoomManager().getBedroom().getDoor();
		}
		else if (map.getRoomManager().isInBathroom(block)) {
			door = map.getRoomManager().getBathroom().getDoor();
		}
		else if (map.getRoomManager().isInKitchen(block)) {
			door = map.getRoomManager().getKitchen().getDoor();
		}
		else if (map.getRoomManager().isInLivingRoom(block)) {
			door = map.getRoomManager().getLivingroom().getDoor();
		}

		return door;
	}

	private Block getTargetDoor(Block block) {
		Block door = null;

		if (map.getRoomManager().isInBedroom(block)) {
			door = map.getRoomManager().getBedroom().getDoor();
		}
		else if (map.getRoomManager().isInBathroom(block)) {
			door = map.getRoomManager().getBathroom().getDoor();
		}
		else if (map.getRoomManager().isInKitchen(block)) {
			door = map.getRoomManager().getKitchen().getDoor();
		}
		else if (map.getRoomManager().isInLivingRoom(block)) {
			door = map.getRoomManager().getLivingroom().getDoor();
		}

		return door;
	}

	private Block getCorridorAccessBlock(Block door) {
		Block corridorBlock = null;
		int line = door.getLine();
		int column = door.getColumn();

		if (map.getRoomManager().isInCorridor(door)) {
			corridorBlock = door;
		}
		else if (map.isInside(line + 1, column)) {
			Block blockBelow = map.getBlock(line + 1, column);

			if (map.getRoomManager().isInCorridor(blockBelow)) {
				corridorBlock = blockBelow;
			}
		}

		if ((corridorBlock == null) && map.isInside(line - 1, column)) {
			Block blockAbove = map.getBlock(line - 1, column);

			if (map.getRoomManager().isInCorridor(blockAbove)) {
				corridorBlock = blockAbove;
			}
		}

		return corridorBlock;
	}

	private void moveStraightTo(Block target) {
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

			if (map.getRoomManager().canMoveOn(newPosition)) {
				updateMasterDirection(master.getPosition(), newPosition);
				master.setPosition(newPosition);
				moved = true;
			}
		}

		return moved;
	}

	private void updateMasterDirection(Block oldPosition, Block newPosition) {
		int oldLine = oldPosition.getLine();
		int oldColumn = oldPosition.getColumn();
		int newLine = newPosition.getLine();
		int newColumn = newPosition.getColumn();

		if (newLine > oldLine) {
			master.setDirection(SimulationConfiguration.MASTER_DOWN);
		}
		else if (newLine < oldLine) {
			master.setDirection(SimulationConfiguration.MASTER_UP);
		}
		else if (newColumn > oldColumn) {
			master.setDirection(SimulationConfiguration.MASTER_RIGHT);
		}
		else if (newColumn < oldColumn) {
			master.setDirection(SimulationConfiguration.MASTER_LEFT);
		}
	}

	public Master getMaster() {
		return master;
	}

	public Switch getSwitch() {
		return interrupter;
	}
}