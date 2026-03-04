package engine.map;

import config.SimulationConfiguration;

public class RoomManager {
	private final BedRoom bedroom;
	private final BathRoom bathroom;
	private final LivingRoom livingroom;
	private final Kitchen kictchen; 
	private final int corridor;
	

	
	
	public RoomManager(BedRoom bedroom, BathRoom bathroom, LivingRoom livingroom, Kitchen kictchen, int corridor) {
		super();
		this.bedroom = bedroom;
		this.bathroom = bathroom;
		this.livingroom = livingroom;
		this.kictchen = kictchen;
		this.corridor = SimulationConfiguration.LINE_COUNT/2;
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
		return kictchen;
	}


	public int getCorridor() {
		return corridor;
	}
	
	
	

}
