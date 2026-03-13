package config;

public class SimulationConfiguration {

	public static final int SOURCE_TILE_SIZE = 32;
	public static final int SCALE = 2;
	public static final int BLOCK_SIZE = SOURCE_TILE_SIZE * SCALE;

	public static final int COLUMN_COUNT = 12;
	public static final int LINE_COUNT = 8;

	public static final int WINDOW_WIDTH = COLUMN_COUNT * BLOCK_SIZE;
	public static final int WINDOW_HEIGHT = LINE_COUNT * BLOCK_SIZE;


	public static final int STATE_UPDATE_FREQUENCY = 10;
	public static final int GAME_SPEED = 300;
}
