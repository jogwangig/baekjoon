package problem_16235;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class GroundTest {
	
	@Test
	void initGroundTEST() {
		int size = 20;
		Ground ground = new Ground(size);
		assertEquals(size, ground.getBlockMatrixSize());
	}
	
	@Test
	void addTreeOnOneBlockTest() {
		int size = 10;
		Ground ground = new Ground(size);
		ground.addTreeOnOneBlock(4, 3, new Tree(3));
		assertEquals(3, ground.getOneBlockOfGround(4, 3).getAliveTrees()
								.get(0).getAge());
	}
	
	@Test
	void addNutrientsOnOneBlockTest() {
		int size = 10;
		Ground ground = new Ground(size);
		assertEquals(5, ground.getOneBlockOfGround(4, 3).getAmountOfNutrients());
		ground.getOneBlockOfGround(4, 3).addNutrients(8);
		assertEquals(13, ground.getOneBlockOfGround(4, 3).getAmountOfNutrients());
	}
	
	@Test
	void springTest() {
		int size = 5;
		Ground g = new Ground(size);
		g.addTreeOnOneBlock(2, 2, new Tree(2));
		g.spring();
	}
	
	@Test
	void fallTest() {
		int size = 10;
		Ground ground = new Ground(size);
		ground.getOneBlockOfGround(4, 3).addTree(new Tree(5));
		ground.fall();
	}
	
}
