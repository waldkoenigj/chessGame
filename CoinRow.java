
public class CoinRow {
	static int[][] F = new int[50][50];
	static int[] weights = {2,1,3,2};
	static int[] values = {12,10,20,15};
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] coins = {5, 1, 2, 10, 6, 2};
		int[] change = {1,3,4};
		int[][] matrix = new int[5][6];
		int result = row(coins);
		System.out.println(result);
		
		result = change(change, 6);
		System.out.println(result);
		
		result = collect(matrix);
		System.out.println(result);
		
		result = knapSack(4, 5);
		System.out.println(result);
		
	}
	
	//Coin-Row: returns greatest possible value
	private static int row(int[] coins) {
		int[] result = new int[100];
		result[0]=0;
		result[1]=coins[0];
		for(int i=2; i<coins.length;i++)
			result[i] = Math.max(coins[i]+result[i-2], result[i-1]);
		return result[coins.length-1];
	}
	
	//Change-Making: returns fewest needed number of coins
	private static int change(int[] coins, int n) {
		int[] result = new int[100];
		result[0] = 0;
		for(int i=1; i<=n; i++) {
			int temp = 10000;
			int j=1;
			while(j<=coins.length-1 && i>=coins[j]) {
				temp = Math.min(result[i-coins[j]], temp);
				j += 1;
			}
			result[i] = temp +1;	
		}
		return result[n];
	}

	//Robot: returns max number of coins possible
	private static int collect(int[][] matrix) {
		int[][] result = new int[50][50];
		result[1][1] = matrix[1][1];
		for(int j=2; j<6; j++)
			result[1][j] = result[1][j-1]+matrix[1][j];
		for(int i=2; i<5; i++) {
			result[i][1] = result[i-1][1]+matrix[i][1];
			for(int j=2; j<6; j++)
				result[i][j] = Math.max(result[i-1][j], result[i][j-1]+matrix[i][j]);
		}
		return result[5][6];	
	}
	
	//Knapsack:
	private static int knapSack(int i, int j) {
		int value=0;
		if(F[i][j]<0) {
			if(j<weights[i])
				value = knapSack(i-1,j);
			else
				value = Math.max(knapSack(i-1,j),values[i]+knapSack(i-1,j-weights[i]));
			F[i][j] = value;
		}
		return F[i][j];
	}
}
