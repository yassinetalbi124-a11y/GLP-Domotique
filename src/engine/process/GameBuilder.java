package engine.process;
import engine.map.*;
import engine.item.*;
import config.*;
import engine.mobile.*;

public class GameBuilder {
	public static Map buildMap() {
		Map map = new Map(SimulationConfiguration.LINE_COUNT, SimulationConfiguration.COLUMN_COUNT);
		initializeRooms(map);
		return map;
	}
	
	public static MobileInterface buildInitMobile(Map map) {
		MobileInterface manager = new MobileElementManager(map);
		
		initializeMaster(map, manager);
		initializeSwitch(map,manager);
	
		return manager;
	}

	public static void initializeMaster(Map map, MobileInterface manager) {
		Block block = map.getBlock(3, 16);
		Master master = new Master(block);
		manager.set(master);
	}

	public static void initializeSwitch(Map map, MobileInterface manager) {
		Block block = map.getBlock(11, 16);
		Light light = new Light();
		Switch interrupter = new Switch(light, block);
		manager.set(interrupter);
	}

	public static void initializeRooms(Map map) {
		BathRoom bathroom = new BathRoom(1, 1, 5, 5, map.getBlock(5, 3));
		BedRoom bedroom = new BedRoom(1, 14, 5, 18, map.getBlock(5, 16));
		Kitchen kitchen = new Kitchen(9, 1, 13, 5, map.getBlock(9, 3));
		LivingRoom livingRoom = new LivingRoom(9, 14, 13, 18, map.getBlock(9, 16));

		RoomManager roomManager = new RoomManager(bedroom, bathroom, livingRoom, kitchen, 6, 8);
		map.setRoomManager(roomManager);
	}
}
