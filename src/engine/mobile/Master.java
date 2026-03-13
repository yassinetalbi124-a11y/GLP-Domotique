package engine.mobile;

import java.util.ArrayList;

import config.SimulationConfiguration;
import engine.map.Block;
import engine.rod.Energy;
import engine.rod.Humor;
import engine.rod.Hunger;
import engine.rod.Hygiene;
import engine.rod.State;
import engine.rod.Thirst;

public class Master extends MobileElement {
	private Thirst thirst;
	private Hunger hunger;
	private Energy energy;
	private Hygiene hygiene;
	private Humor humor;

	private int stateCounter;
	private int direction;

	public Master(Block position) {
		super(position);
		thirst = new Thirst(100);
		hunger = new Hunger(100);
		energy = new Energy(100);
		hygiene = new Hygiene(100);
		humor = new Humor();
		stateCounter = 0;
		direction = SimulationConfiguration.MASTER_DOWN;
		updateHumour();
	}

	public void updateStates() {
		stateCounter = stateCounter + 1;

		if (stateCounter >= SimulationConfiguration.STATE_UPDATE_FREQUENCY) {
			thirst.decrease(2);
			hunger.decrease(1);
			energy.decrease(1);
			hygiene.decrease(1);

			updateHumour();
			stateCounter = 0;
		}
	}

	public void drink() {
		thirst.increase(25);
		updateHumour();
	}

	public void eat() {
		hunger.increase(25);
		updateHumour();
	}

	public void sleep() {
		energy.increase(30);
		updateHumour();
	}

	public void wash() {
		hygiene.increase(25);
		updateHumour();
	}

	public boolean isInCriticalState() {
		return thirst.isCritical()
				|| hunger.isCritical()
				|| energy.isCritical()
				|| hygiene.isCritical();
	}

	public ArrayList<State> getStates() {
		ArrayList<State> states = new ArrayList<State>();
		states.add(thirst);
		states.add(hunger);
		states.add(energy);
		states.add(hygiene);
		return states;
	}

	public void updateHumour() {
		humor.calculer(getStates());
	}

	public Thirst getThirst() {
		return thirst;
	}

	public Hunger getHunger() {
		return hunger;
	}

	public Energy getEnergy() {
		return energy;
	}

	public Hygiene getHygiene() {
		return hygiene;
	}

	public Humor getHumour() {
		return humor;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}
}