package engine.process;
import engine.map.*;
import engine.item.*;
import config.*;
import engine.mobile.*;

public class GameBuilder {
	public static Map buildMap() {
		return new Map(SimulationConfiguration.LINE_COUNT, SimulationConfiguration.COLUMN_COUNT);
	}
	
	public static MobileInterface buildInitMobile(Map map) {
		MobileInterface manager = new MobileElementManager(map);
		
		intializeMaster(map, manager);
		initializeSwitch(map,manager);
	
		return manager;
	}
	
	public static void intializeMaster(Map map,MobileInterface manager) {
		Block block = map.getBlock(3, (SimulationConfiguration.COLUMN_COUNT-1)/2);
		Master master = new Master(block);
		manager.set(master);
	}
	
	public static void initializeSwitch(Map map,MobileInterface manager) {
		Block block = map.getBlock((SimulationConfiguration.LINE_COUNT-1)/2 + 2, (SimulationConfiguration.COLUMN_COUNT-1)-2);
		Light light = new Light();
		Switch interrupter = new Switch(light,block);
		manager.set(interrupter);
	}
}
