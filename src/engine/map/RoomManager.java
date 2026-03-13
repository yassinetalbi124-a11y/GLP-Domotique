package engine.map;

import config.SimulationConfiguration;

public class RoomManager {
	private final BedRoom bedroom;
	private final BathRoom bathroom;
	private final LivingRoom livingroom;
	private final Kitchen kitchen;
	private int corridorStartLine;
	private int corridorEndLine;
	

	
	
	public RoomManager(BedRoom bedroom, BathRoom bathroom, LivingRoom livingroom, Kitchen kitchen, int corridorStartLine,int corridorEndLine) {
		super();
		this.bedroom = bedroom;
		this.bathroom = bathroom;
		this.livingroom = livingroom;
		this.kitchen = kitchen;
		this.corridorStartLine = corridorStartLine;
		this.corridorEndLine = corridorEndLine;
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


	public Kitchen getKictchen() {
		return kitchen;
	}


	public int getCorridorStartLine() {
		return corridorStartLine;
	}

	public int getCorridorEndLine(){return corridorEndLine;}

	public boolean isInBedroom(Block block){
		return bedroom.isRoom(block);
	}

	public boolean isInBathroom(Block block){
		return bathroom.isRoom(block);
	}

	public boolean isInLivingRoom(Block block){
		return livingroom.isRoom(block);
	}

	public boolean isInKitchen(Block block){
		return kitchen.isRoom(block);
	}

	public boolean isInRoom(Block block) {
		return isInBedroom(block) || isInBathroom(block) || isInKitchen(block) || isInLivingRoom(block);
	}

	public boolean isInCorridor(Block block) {
		int line = block.getLine();
		return line >= corridorStartLine && line <= corridorEndLine
				&& block.getColumn() >= 1 && block.getColumn() <= 18;
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

	public boolean canMoveOn(Block block) {
		return isInCorridor(block) || isInRoom(block);
	}
	
	
	

}
