package problem_16235;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class OneBlockOfGround {
	
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
	
	
	public void letTreesAbsorbNutrients() {
		sortTrees();
		List<Tree> aliveTrees = getAliveTrees();
		
		sortTrees();
		
		int indexOfTreeAvailableAbsorbingNutrients = 
				calculateIndexOfTreeAvailableAbsorbingNutrients();
				
		letTreesAvailableAsorbingNutrientsAbsorbNutrients(
				indexOfTreeAvailableAbsorbingNutrients);

				
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
	
	@Override
	public String toString() {
		String result = amountOfNutrients + "\n Alive Trees\n\n";
		
		for(Tree t : getAliveTrees()) {
			result += "age : " + t.getAge() + "\n";
		}
		result += "\n";
		
		result += "Dead Trees\n";
		
		for(Tree t : getDeadTrees())
			result += "age : " + t.getAge() + "\n";
		
		result += "\n";
		
		return result;
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
