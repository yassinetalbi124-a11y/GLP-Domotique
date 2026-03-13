package engine.mobile;

import java.util.ArrayList;

import config.SimulationConfiguration;
import engine.map.Block;
import engine.rod.*;

public class Master extends MobileElement {

	private Thirst thirst;
	private Hunger hunger;
	private Energy energies;
	private Hygiene hygiene;
	private Humor humor;

	private int stateCounter;

	public Master(Block position) {
		super(position);
		thirst = new Thirst(100);
		hunger = new Hunger(100);
		energies = new Energy(100);
		hygiene = new Hygiene(100);
		humor = new Humor();
		stateCounter = 0;
		updateMood();
	}

	public void updateStates() {
		stateCounter = stateCounter + 1;

		if (stateCounter >= SimulationConfiguration.STATE_UPDATE_FREQUENCY) {
			thirst.diminuer(2);
			hunger.diminuer(1);
			energies.diminuer(1);
			hygiene.diminuer(1);

			updateMood();
			stateCounter = 0;
		}
	}

	public void drink() {
		thirst.augmenter(25);
		updateMood();
	}

	public void eat() {
		hunger.augmenter(25);
		updateMood();
	}

	public void sleep() {
		energies.augmenter(30);
		updateMood();
	}

	public void wash() {
		hygiene.augmenter(25);
		updateMood();
	}

	public boolean isInCriticalState() {
		return thirst.estCritique()
				|| hunger.estCritique()
				|| energies.estCritique()
				|| hygiene.estCritique();
	}

	public ArrayList<State> getEtats() {
		ArrayList<State> etats = new ArrayList<State>();
		etats.add(thirst);
		etats.add(hunger);
		etats.add(energies);
		etats.add(hygiene);
		return etats;
	}

	public void updateMood() {
		humor.calculer(getEtats());
	}

	public Thirst getThirst() {
		return thirst;
	}

	public Hunger getHunger() {
		return hunger;
	}

	public Energy getEnergy() {
		return energies;
	}

	public Hygiene getHygiene() {
		return hygiene;
	}

	public Humor getHumor() {
		return humor;
	}
}