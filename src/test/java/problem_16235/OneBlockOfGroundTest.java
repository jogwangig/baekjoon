package problem_16235;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OneBlockOfGroundTest {
	
	private OneBlockOfGround block;
	private int[] treeAgeParams;
	
	@BeforeEach
	void setUp() {
		block = new OneBlockOfGround();
		treeAgeParams = new int[] {1, 3, 7, 6, 3};
		Arrays.sort(treeAgeParams);
	}

	@Test
	void addTreeTest() {
		assertEquals(0, block.getSize());

		block.addTree(new Tree(60));
		assertEquals(1, block.getSize());
		
		block.addTrees(new Tree(60), new Tree(70), new Tree(80));
		assertEquals(4, block.getSize());
	}
	
	
	@Test
	void addNutrientsTest() {		
		assertEquals(5, block.getAmountOfNutrients());
		
		block.addNutrients(3);
		assertEquals(8, block.getAmountOfNutrients());
	}
	
	/**
	 * 
	 */
	@Test
	void getNuberOfReproductibleTreesTest() {
		OneBlockOfGround oneBlockOfGround = new OneBlockOfGround(new Tree(7), new Tree(3),
				new Tree(6), new Tree(4), new Tree(9));
		
		assertEquals(0, oneBlockOfGround.getNumberOfReproductibleTrees());
		
		oneBlockOfGround.addTree(new Tree(5));
		assertEquals(1, oneBlockOfGround.getNumberOfReproductibleTrees());
	}
	

	
	@Test
	void letAliveTreesAbsorbNutrientsTest() {
		
		int amountOfNutrients = block.getAmountOfNutrients();
		int splitPointOfAliveTrees = calculateSplitPointOfAliveTrees(amountOfNutrients, 
				treeAgeParams);
		
		int[] aliveTreesAges = Arrays.copyOfRange(treeAgeParams, 0, 
				splitPointOfAliveTrees);
		
		int[] deadTreesAges = Arrays.copyOfRange(treeAgeParams, splitPointOfAliveTrees, 
				treeAgeParams.length);

		block.addTrees(Arrays.stream(treeAgeParams)
						.mapToObj(Tree::new)
						.toArray(Tree[]::new));
		
		block.letTreesAbsorbNutrients();
				
		List<Tree> aliveTrees = block.getAliveTrees();
		
		for(int i = 0; i < aliveTrees.size(); i++)
			assertEquals(aliveTreesAges[i] + 1, aliveTrees.get(i).getAge());
		
		
		List<Tree> deadTrees = block.getDeadTrees();
		
		for(int i = 0; i < deadTrees.size(); i++) 
			assertEquals(deadTreesAges[i], deadTrees.get(i).getAge());

	}
	
	@Test
	void letDeadTreesBeNutrients() {
				
		int amountOfNutrients = block.getAmountOfNutrients();
		
		int[] deadTreesAges = Arrays.copyOfRange(treeAgeParams,
				calculateSplitPointOfAliveTrees(amountOfNutrients, treeAgeParams), 
				treeAgeParams.length);
				
		
		block.addTrees(Arrays.stream(treeAgeParams)
								.mapToObj(Tree::new)
								.toArray(Tree[]::new));
		
		block.letTreesAbsorbNutrients();
				
		int amountOfLeftNutrients = block.getAmountOfNutrients();
		
		int amountOfAddedNutrients = Arrays.stream(deadTreesAges)
												.map(i-> (int)(i / 2))
												.sum();
		
		block.letDeadTreesBeNutrients();

		assertEquals(amountOfLeftNutrients + amountOfAddedNutrients , 
				block.getAmountOfNutrients());
		
		
	}
	
	
	
	
	private int calculateSplitPointOfAliveTrees(int amountOfNutrients, int... ints) {
		int total = 0;
		
		for(int i = 0; i < ints.length; i++) {
			if(amountOfNutrients >= total + ints[i])
				total += ints[i];
			else
				return i;
		}
		
		return ints.length;
	}
}
