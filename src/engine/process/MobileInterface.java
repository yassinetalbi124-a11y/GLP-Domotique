package engine.process;
import engine.mobile.*;
import engine.map.*;
import engine.item.*;

public interface MobileInterface {
	void set(Master master);
	void set(Switch interrupter);
	
	void nextRound();
	
	boolean isNear(Block position1,Block position2);
	void moveCloser(Block target);
	boolean tryMove(int line, int column);
	
	Master getMaster();
	Switch getSwitch();
}
