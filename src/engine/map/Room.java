package engine.map;

public abstract class Room {
	
	 private  int startLine;
	    private  int startColumn;
	    private  int endLine;
	    private  int endColumn;
	    private Block door ;
	    
	    
	  
		public Room(int startLine, int startColumn, int endLine, int endColumn, Block door) {
			super();
			this.startLine = startLine;
			this.startColumn = startColumn;
			this.endLine = endLine;
			this.endColumn = endColumn;
			this.door = door;
		}
		
		
		
		public int getStartLine() {
			return startLine;
		}
		
		
		
		public void setStartLine(int startLine) {
			this.startLine = startLine;
		}
		
		
		
		public int getStartColumn() {
			return startColumn;
		}
		
		
		
		public void setStartColumn(int startColumn) {
			this.startColumn = startColumn;
		}
		
		
		public int getEndLine() {
			return endLine;
		}
		
		
		
		public void setEndLine(int endLine) {
			this.endLine = endLine;
		}
		
		
		
		public int getEndColumn() {
			return endColumn;
		}
		
		
		public void setEndColumn(int endColumn) {
			this.endColumn = endColumn;
		}
		
		
		
		public Block getDoor() {
			return door;
		}
		
		
		
		public void setDoor(Block door) {
			this.door = door;
		} 
		
		// verifier si le block appartient a une chambre ou pas 
		
		public  boolean isRoom (Block block) {
			return block.getLine()   >= startLine   && block.getLine()   <= endLine
		            && block.getColumn() >= startColumn && block.getColumn() <= endColumn;
		}
		
		// si c un mur 
		public boolean isWall (Block block ) {
			if (!isRoom (block)) {
				return false;
			}
			
			boolean onBorder = block.getLine()   == startLine
                    || block.getLine()   == endLine
                    || block.getColumn() == startColumn
                    || block.getColumn() == endColumn;

				    if (!onBorder) return false; 
				   
				    return !block.equals(door); 
				}
		
		
	    

}
