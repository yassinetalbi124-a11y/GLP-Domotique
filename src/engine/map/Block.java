package engine.map;

public class Block {
	private int line;
	private int column;

	public Block(int line, int column) {
		this.line = line;
		this.column = column;
	}

	public int getLine() {
		return line;
	}

	public int getColumn() {
		return column;
	}

	public boolean samePosition(Block block) {
		return block != null && line == block.getLine() && column == block.getColumn();
	}

	@Override
	public String toString() {
		return "Block [line=" + line + ", column=" + column + "]";
	}
}