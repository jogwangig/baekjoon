package problem_16235;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.function.Consumer;

public class Main {

	public static void main(String[] args) {
		
		int n, m, k;
		
		try(BufferedReader br = new BufferedReader(
				new InputStreamReader(System.in));){
			String line = br.readLine();
			StringTokenizer st = new StringTokenizer(line);
			
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());	
			
			int[][] distributionInfo = new int[n][n];
			
			for(int i = 0; i < n; i++) {
				line = br.readLine();
				st = new StringTokenizer(line);
				
				for(int j = 0; j < n; j++)
					distributionInfo[i][j] = Integer.parseInt(st.nextToken());
			}
				
		
			S2D2NutrientsDistributor.setDistributionInfo(distributionInfo);		
			Ground g = new Ground(n);
			
			int r, c, age;
			for(int i = 0; i < m; i++) {
				line = br.readLine();
				st = new StringTokenizer(line);
				
				r = Integer.parseInt(st.nextToken()) - 1;
				c = Integer.parseInt(st.nextToken()) - 1;
				age = Integer.parseInt(st.nextToken());
				
				g.addTreeOnOneBlock(r, c, new Tree(age));
			}
		
		
		
		for(int i = 0; i < k; i++) {
			g.oneYear();
		}
		
		System.out.print(g.result());
		
					
		}catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	
	static class Tree {
		private int age;
		
		public Tree(int age) {
			this.age = age;
		}
		
		public void absorbNutrient(int amountOfNutrient) {
			if(age != amountOfNutrient)
				throw new RuntimeException("흡수하는 양분의 크기는 나무의 나이와 동일해야합니");
			age++;	
		}
		
		public boolean isReproductible() {
			if(age > 0 && age %5 == 0)
				return true;
			
			return false;
		}
		
		public int getAge() {
			return age;
		}
		
		public int getAmountOfNutrientsWhenDead() {
			return (int)(getAge() / 2);
		}
		 
	}
	
	static class OneBlockOfGround {
		
		private int amountOfNutrients = 5;
		
		private List<Tree> aliveTrees = new ArrayList<>();
		
		private List<Tree> deadTrees = new ArrayList<>();
		
		
		
		public OneBlockOfGround() {}
		
		public OneBlockOfGround(Tree...trees) {
			for(Tree t : trees)
				addTree(t);
		}
		
		
		public void addTree(Tree t) {
			aliveTrees.add(t);
		}
		
		public void addTrees(Tree... trees) {
			for(Tree t : trees)
				addTree(t);
		}
		
		public void addNutrients(int amountOfNutrients) {
			if(amountOfNutrients < 0)
				throw new RuntimeException("충전되는 영양소는 양수여야합니다.");
			this.amountOfNutrients += amountOfNutrients;
		}
		
		
		public int getSize() {
			return aliveTrees.size();
		}
		
		
		public List<Tree> getAliveTrees(){
			return aliveTrees;
		}
		
		public List<Tree> getDeadTrees(){
			return deadTrees;
		}
		
		public int getAmountOfNutrients() {
			return amountOfNutrients;
		}
		
		
		
		public int getNumberOfReproductibleTrees() {
			return (int)getAliveTrees().stream()
									.filter(Tree::isReproductible)
									.count();
		}
		
		
		public void letAliveTreesAbsorbNutrients() {
			sortTrees();
			List<Tree> aliveTrees = getAliveTrees();
			
			sortTrees();
			
			
			int indexOfTreeAvailableAbsorbingNutrients = 
					calculateIndexOfTreeAvailableAbsorbingNutrients();
			
			
			letTreesAvailableAsorbingNutrientsAbsorbNutrients(
					indexOfTreeAvailableAbsorbingNutrients);

			
			if(indexOfTreeAvailableAbsorbingNutrients == -1) return;
			
			List<Tree> deadTrees = new ArrayList<Tree>(
					aliveTrees.subList(indexOfTreeAvailableAbsorbingNutrients, 
					aliveTrees.size())
					);
			
			aliveTrees.subList(indexOfTreeAvailableAbsorbingNutrients, 
					aliveTrees.size()).clear();
			
			getDeadTrees().addAll(deadTrees);
		}
		
		
		public void letDeadTreesBeNutrients() {
			int totalAmountOfAddedNutrients = getDeadTrees().stream()
															.mapToInt(Tree::getAmountOfNutrientsWhenDead)
															.sum();
			getDeadTrees().clear();
			
			amountOfNutrients += totalAmountOfAddedNutrients;
		}
		
		
		public void sortTrees() {
			aliveTrees.sort(Comparator.comparingInt(Tree::getAge));
		}
		
		
		
		private int calculateIndexOfTreeAvailableAbsorbingNutrients() {
			
			int availableAmountOfNutrient = 0;
			
			for(int i = 0; i < getAliveTrees().size(); i++) {
				if(getAmountOfNutrients() >= (availableAmountOfNutrient + aliveTrees.get(i).getAge()))
					availableAmountOfNutrient += aliveTrees.get(i).getAge();
				else{
					return i;
				}
			}
			
			return getAliveTrees().size();
		}
		
		private void letTreesAvailableAsorbingNutrientsAbsorbNutrients(int limitIndex) {

			Tree t = null;
			for(int i = 0; i < limitIndex; i++) {
				t = aliveTrees.get(i);
				int amountOfAbsorbedNutrients = t.getAge();
				t.absorbNutrient(amountOfAbsorbedNutrients);
				amountOfNutrients -= amountOfAbsorbedNutrients;
			}
		}
		
	}
	
	static class Ground {
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
				block.letAliveTreesAbsorbNutrients();
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
	
	static class S2D2NutrientsDistributor {
		private static int[][] distributionInfo;
		
		public static void setDistributionInfo(int[][] info) {
			distributionInfo = info;
		}
		
		public static void distributeNutrients(int r, int c, OneBlockOfGround block) {
			block.addNutrients(distributionInfo[r][c]);
		}
	}


}
