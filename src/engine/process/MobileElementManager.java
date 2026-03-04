package engine.process;
import engine.item.*;
import engine.mobile.*;
import engine.map.*;

public class MobileElementManager implements MobileInterface {
	private Master master;
	private Map map;
	
	private Switch interrupter;
	
	private boolean pressed= false;
	
	public MobileElementManager(Map map) {
		this.map = map;
	}
	
	public void set(Master master) {
		this.master = master;
	}
	
	public void set(Switch interrupter) {
		this.interrupter = interrupter;
	}
	
	public void nextRound() {
		Block masterPosition = master.getPosition();
		Block switchPosition = interrupter.getPosition();
		
		if(!isNear(masterPosition,switchPosition)) {
			moveCloser(switchPosition);
			pressed = false;
		}
		if(isNear(masterPosition,switchPosition)&&(!pressed)) {
			interrupter.toggle();
			pressed = true;
		}
	}
	
	
	public boolean isNear(Block position1,Block position2) {
		int absoluteColumn = Math.abs(position1.getColumn()-position2.getColumn());
		int absoluteLine = Math.abs(position1.getLine()-position2.getLine());
		return (absoluteColumn<=1 &&absoluteLine<=1);
	}
	
	public void moveCloser(Block target) {
		
		if(master.getPosition().getColumn()!=target.getColumn()){
			if(master.getPosition().getColumn()>target.getColumn()) {
				Block newPosition = map.getBlock(master.getPosition().getLine(),master.getPosition().getColumn()-1);
				master.setPosition(newPosition);
			}
			else if(master.getPosition().getColumn()<target.getColumn()) {
				Block newPosition = map.getBlock(master.getPosition().getLine(),master.getPosition().getColumn()+1);
				master.setPosition(newPosition);
			}
		}
		else if(master.getPosition().getLine()!=target.getLine()) {
			if(master.getPosition().getLine()>target.getLine()) {
				Block newPosition = map.getBlock(master.getPosition().getLine()-1,master.getPosition().getColumn());
				master.setPosition(newPosition);
			}
			else if(master.getPosition().getLine()<target.getLine()) {
				Block newPosition = map.getBlock(master.getPosition().getLine()+1,master.getPosition().getColumn());
				master.setPosition(newPosition);
			}
		}
		
	}
	
	public Master getMaster() {
		return master;
	}
	
	public Switch getSwitch() {
		return interrupter;
	}
	
}
