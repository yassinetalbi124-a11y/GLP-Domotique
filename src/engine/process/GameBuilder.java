package engine.process;

import config.SimulationConfiguration;
import engine.item.Light;
import engine.item.Switch;
import engine.map.BathRoom;
import engine.map.BedRoom;
import engine.map.Block;
import engine.map.Kitchen;
import engine.map.LivingRoom;
import engine.map.Map;
import engine.map.RoomManager;
import engine.mobile.Master;

public class GameBuilder {
	public static Map buildMap() {
		Map map = new Map(SimulationConfiguration.LINE_COUNT, SimulationConfiguration.COLUMN_COUNT);
		initializeRooms(map);
		return map;
	}

	public static MobileInterface buildInitMobile(Map map) {
		MobileInterface manager = new MobileElementManager(map);
		initializeMaster(map, manager);
		initializeSwitch(map, manager);
		return manager;
	}

	public static void initializeRooms(Map map) {
		BedRoom bedroom = new BedRoom(0, 0, 3, 5, map.getBlock(3, 4));
		Kitchen kitchen = new Kitchen(0, 7, 3, 11, map.getBlock(3, 8));
		BathRoom bathroom = new BathRoom(5, 0, 7, 3, map.getBlock(5, 2));
		LivingRoom livingRoom = new LivingRoom(5, 5, 7, 11, map.getBlock(5, 9));

		RoomManager roomManager = new RoomManager(bedroom, bathroom, livingRoom, kitchen);
		map.setRoomManager(roomManager);
	}

	public static void initializeMaster(Map map, MobileInterface manager) {
		Block block = map.getBlock(2, 4);
		Master master = new Master(block);
		manager.set(master);
	}

	public static void initializeSwitch(Map map, MobileInterface manager) {
		Block block = map.getBlock(6, 9);
		Light light = new Light();
		Switch interrupter = new Switch(light, block);
		manager.set(interrupter);
	}
}