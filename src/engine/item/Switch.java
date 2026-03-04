package engine.item;

import engine.map.*;

public class Switch {
	private Light light;
	private Block block;
	
	public Switch(Light light,Block block) {
		this.light = light;
		this.block = block;
	}
	
	public boolean isOn() {
		return light.isOn();
	}
	
	public void toggle() {
		light.toggle();
	}
	
	public Block getPosition() {
		return block;
	}

	
	public Light getLight() {
		return light;
	}
	
	
	
}
