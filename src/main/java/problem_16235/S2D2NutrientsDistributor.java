package problem_16235;

public class S2D2NutrientsDistributor {
	private static int[][] distributionInfo;
	
	public static void setDistributionInfo(int[][] info) {
		distributionInfo = info;
	}
	
	public static void distributeNutrients(int r, int c, OneBlockOfGround block) {
		block.addNutrients(distributionInfo[r][c]);
	}
}
