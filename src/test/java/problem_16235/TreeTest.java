package problem_16235;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assumptions.assumeFalse;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TreeTest {
	
	private Tree tree, reproductibleTree;
	
	@BeforeEach
	void setUp() {
		tree = new Tree(30);
		reproductibleTree = new Tree(5);
	}
	
	@Test
	void initializeTreeTest() {
		assertEquals(30, tree.getAge());
	}
	
	@Test
	void treeAbsorbingNutrientTest() {
		tree.absorbNutrient(30);
		assertEquals(31, tree.getAge());
	}
	
	@Test
	void treeAbsorbingNutrientNotIdenticalWithAgeTest() {
		RuntimeException e = assertThrows(RuntimeException.class, 
				()->{tree.absorbNutrient(31);});
		
		assertEquals("흡수하는 양분의 크기는 나무의 나이와 동일해야합니", e.getMessage());
	}
	
	@Test
	void isReproductibleTest() {
		assumeTrue(reproductibleTree.isReproductible());
		reproductibleTree.absorbNutrient(5);
		assumeFalse(reproductibleTree.isReproductible());
	}
	
	@Test
	void amountOfNutrientsWhenDeadTest() {
		assertEquals(2, reproductibleTree.getAmountOfNutrientsWhenDead());
		reproductibleTree.absorbNutrient(5);
		assertEquals(3, reproductibleTree.getAmountOfNutrientsWhenDead());
	}
}
