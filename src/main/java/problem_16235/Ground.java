package problem_16235;

import java.util.function.Consumer;

public class Ground {
	private final int n;
	private OneBlockOfGround[][] blockMatrix;
	
	public Ground(int n) {
		this.n = n;
		blockMatrix = new OneBlockOfGround[n][n];
		init();
	}
	
	public void oneYear() {
		spring();
		summer();
		fall();
		winter();
	}
	
	public int result() {
		int total = 0;
		for(int i = 0; i < n; i++)
			for(int j = 0; j < n; j++) 
				total += blockMatrix[i][j].getAliveTrees().size();
				
		return total;
				
	}
	
	public int getBlockMatrixSize() {
		return blockMatrix.length;
	}
	
	
	OneBlockOfGround getOneBlockOfGround(int r, int c) {
		return blockMatrix[r][c];
	}
	
	public void addTreeOnOneBlock(int r, int c, Tree t) {
		blockMatrix[r][c].addTree(t);
	}
	
	public void addNutrientsOnOneBlock(int r, int c, int nutrients) {
		blockMatrix[r][c].addNutrients(nutrients);
	}
	
	public void spring() {
		applyToAllBlocks(block->{
			block.letTreesAbsorbNutrients();
		});
	}
	
	public void summer() {
		applyToAllBlocks(block->{
			block.letDeadTreesBeNutrients();
		});
	}
	
	public void fall() {
		int numberOfReproductibleTrees = 0;
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				numberOfReproductibleTrees = 
				blockMatrix[i][j].getNumberOfReproductibleTrees();
				for(int k = 0; k < numberOfReproductibleTrees; k++)
					addTreesOfReproduction(i, j);
			}
		}
	}
	
	public void winter() {
		for(int i = 0; i < n; i++) 
			for(int j = 0; j < n; j++) 
				S2D2NutrientsDistributor.distributeNutrients(i, j, getOneBlockOfGround(i, j));
	}
	
	
	@Override
	public String toString() {
		int n = blockMatrix.length;
		String result = "==================Ground Start==============\n\n";
		for(int i = 0; i < n; i++)
			for(int j = 0; j < n; j++)
				result += i + ":" + j + "\n"  + blockMatrix[i][j].toString();
		
		return result;
	}
	
	private void init() {
		int n = blockMatrix.length;
		for(int i = 0; i < n; i++)
			for(int j = 0; j < n; j++)
				blockMatrix[i][j] = new OneBlockOfGround();
	}
	
	void addTreesOfReproduction(int r, int c) {
		for(int i = -1; i < 2; i++) {
			if(r + i < 0 || r + i >= n) continue;
			for(int j = -1; j < 2; j++) {
				if(c + j < 0 || c + j >= n) continue;
				if(i == 0 && j == 0) continue;
				addTreeOnOneBlock(r + i, c + j, new Tree(1));
			}
				
		}
	}
	
	private void applyToAllBlocks(Consumer<OneBlockOfGround> consumer) {
		for(int i = 0; i < n; i++)
			for(int j = 0; j < n; j++)
				consumer.accept(blockMatrix[i][j]);
	}
	
}
