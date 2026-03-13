package engine.map;

public class RoomManager {
	private BedRoom bedroom;
	private BathRoom bathroom;
	private LivingRoom livingroom;
	private Kitchen kitchen;
	private boolean[][] walkableBlocks;

	public RoomManager(BedRoom bedroom, BathRoom bathroom, LivingRoom livingroom, Kitchen kitchen) {
		this.bedroom = bedroom;
		this.bathroom = bathroom;
		this.livingroom = livingroom;
		this.kitchen = kitchen;
		initializeWalkableBlocks();
	}

	private void initializeWalkableBlocks() {
		walkableBlocks = new boolean[][] {
				{ false, false, false, false, false, false, false, false, false, false, false, false },
				{ false, false, false, true,  true,  false, false, false, true,  true,  true,  false },
				{ false, false, false, true,  true,  false, false, false, true,  true,  true,  false },
				{ false, false, false, false, true,  false, false, false, true,  false, false, false },
				{ true,  true,  true,  true,  true,  true,  true,  true,  true,  true,  true,  true  },
				{ false, false, true,  false, false, false, false, false, false, true,  false, false },
				{ false, true,  true,  false, false, false, false, false, true,  true,  true,  false },
				{ false, false, false, false, false, false, false, false, false, false, false, false }
		};
	}

	public BedRoom getBedroom() {
		return bedroom;
	}

	public BathRoom getBathroom() {
		return bathroom;
	}

	public LivingRoom getLivingroom() {
		return livingroom;
	}

	public Kitchen getKitchen() {
		return kitchen;
	}

	public boolean isInBedroom(Block block) {
		return bedroom.isRoom(block);
	}

	public boolean isInBathroom(Block block) {
		return bathroom.isRoom(block);
	}

	public boolean isInKitchen(Block block) {
		return kitchen.isRoom(block);
	}

	public boolean isInLivingRoom(Block block) {
		return livingroom.isRoom(block);
	}

	public boolean isInRoom(Block block) {
		return isInBedroom(block) || isInBathroom(block) || isInKitchen(block) || isInLivingRoom(block);
	}

	public boolean isDoor(Block block) {
		return block.samePosition(bedroom.getDoor())
				|| block.samePosition(bathroom.getDoor())
				|| block.samePosition(kitchen.getDoor())
				|| block.samePosition(livingroom.getDoor());
	}

	public boolean isWall(Block block) {
		return bathroom.isWall(block)
				|| bedroom.isWall(block)
				|| kitchen.isWall(block)
				|| livingroom.isWall(block);
	}

	public boolean isInCorridor(Block block) {
		int line = block.getLine();
		int column = block.getColumn();
		boolean corridor = false;

		if (line == 4) {
			corridor = true;
		}

		if ((line == 5) && (column == 2 || column == 9)) {
			corridor = true;
		}

		return corridor;
	}

	public boolean isWalkable(Block block) {
		boolean walkable = false;
		int line = block.getLine();
		int column = block.getColumn();

		if (line >= 0 && line < walkableBlocks.length) {
			if (column >= 0 && column < walkableBlocks[line].length) {
				walkable = walkableBlocks[line][column];
			}
		}

		return walkable;
	}

	public boolean canMoveOn(Block block) {
		return isWalkable(block);
	}
}