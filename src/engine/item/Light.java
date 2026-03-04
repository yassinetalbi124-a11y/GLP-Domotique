package engine.item;

public class Light {
	private boolean state = false;
	
	public boolean isOn() {
		return state;
	}

	public void toggle() {
		state = !state;
	}
	
}
