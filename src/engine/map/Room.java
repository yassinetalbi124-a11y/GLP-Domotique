package engine.map;

public abstract class Room {
	private int startLine;
	private int startColumn;
	private int endLine;
	private int endColumn;
	private Block door;

	public Room(int startLine, int startColumn, int endLine, int endColumn, Block door) {
		this.startLine = startLine;
		this.startColumn = startColumn;
		this.endLine = endLine;
		this.endColumn = endColumn;
		this.door = door;
	}

	public int getStartLine() {
		return startLine;
	}

	public int getStartColumn() {
		return startColumn;
	}

	public int getEndLine() {
		return endLine;
	}

	public int getEndColumn() {
		return endColumn;
	}

	public Block getDoor() {
		return door;
	}

	public boolean isRoom(Block block) {
		return block.getLine() >= startLine && block.getLine() <= endLine
				&& block.getColumn() >= startColumn && block.getColumn() <= endColumn;
	}

	public boolean isWall(Block block) {
		boolean wall = false;

		if (isRoom(block)) {
			boolean onBorder = block.getLine() == startLine
					|| block.getLine() == endLine
					|| block.getColumn() == startColumn
					|| block.getColumn() == endColumn;

			if (onBorder && !block.samePosition(door)) {
				wall = true;
			}
		}

		return wall;
	}
}