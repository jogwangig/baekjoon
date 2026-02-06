package problem_16235;

public class Tree {
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
