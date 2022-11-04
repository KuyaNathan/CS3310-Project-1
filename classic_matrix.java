// Performs Classic Matrix Multiplication

public class classic_matrix 
{
	/*
	 * Takes two matrices and their size as arguments
	 * Performs classic matrix multiplication on them
	 */
	public int[][] classic_multiply(int[][] a, int[][] b, int n)
	{
		int[][] product = new int[n][n]; // create matrix for the product
		for(int i = 0; i < n; i++)
		{
			for(int w = 0; w < n; w++)
			{
				for(int k = 0; k < n; k++)
				{
					product[i][w] += a[i][k] * b[k][w];
				}
			}
		}
		return product;		// return the product matrix
	}
}
