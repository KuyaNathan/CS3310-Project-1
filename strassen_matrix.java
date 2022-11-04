// Performs Strassen's Algorithm for Matrix Mutltiplication

public class strassen_matrix 
{
	public  int[][] strassen(int[][] a, int[][] b)
	{
		int size = a.length;	// gets the matrix size
		int[][] product = new int[size][size]; // create matrix for the product

		if(size == 1)		// base case
		{
			product[0][0] = a[0][0] * b[0][0];
			return product;
		}
		else
		{	// split a[][]into four quadrants and fill them
			int a11[][] = new int[size/2][size/2];
			int a12[][] = new int[size/2][size/2];
			int a21[][] = new int[size/2][size/2];
			int a22[][] = new int[size/2][size/2];
			quadsplit(a, a11, 0, 0);
			quadsplit(a, a12, 0, size/2);
			quadsplit(a, a21, size/2, 0);
			quadsplit(a, a22, size/2, size/2);

			// split b[][] into four quadrants and fill them
			int b11[][] = new int[size/2][size/2];
			int b12[][] = new int[size/2][size/2];
			int b21[][] = new int[size/2][size/2];
			int b22[][] = new int[size/2][size/2];
			quadsplit(b, b11, 0, 0);
			quadsplit(b, b12, 0, size/2);
			quadsplit(b, b21, size/2, 0);
			quadsplit(b, b22, size/2, size/2);

			// calculate the elements of product[][]
			int[][] p1 = strassen(a11, subtract(b12, b22));
			int[][] p2 = strassen(add(a11, a12), b22);
			int[][] p3 = strassen(add(a21, a22), b11);
			int[][] p4 = strassen(a22, subtract(b21, b11));
			int[][] p5 = strassen(add(a11, a22), add(b11, b22));
			int[][] p6 = strassen(subtract(a12, a22), add(b21, b22));
			int[][] p7 = strassen(subtract(a11, a21), add(b11, b12));

			// fill the quadrants of the product matrix
			int[][] c11 = add(subtract(p4, p2), add(p5, p6));
			int[][] c12 = add(p1, p2);
			int[][] c21 = add(p3, p4);
			int[][] c22 = add(subtract(p1, p3), subtract(p5, p7));

			// combine the product matrix quadrants into one matrix
			combine(c11, product, 0, 0);
			combine(c12, product, 0, size/2);
			combine(c21, product, size/2, 0);
			combine(c22, product, size/2, size/2);
			
			return product;		// return the product matrix
		}
		
	}


	// quadsplit() takes the original matrix, a quadrant submatrix, # of rows, and # of columns as arguments 
	// 	Iterates through the given rows and columns of the original matrix 
	//	and fills the quadrant submatrix
	public static void quadsplit(int[][] og, int[][] quad, int r, int c)
	{
		int t1 = r;
		int t2 = c;
		for(int i = 0; i < quad.length; i++) // traverse the rows and columns of the quadrant
		{				     // fill the corresponding elements of the whole matrix
			for(int w = 0; w < quad.length; w++)
			{
				quad[i][w] = og[t1][t2++];
			}
			t1++;
			t2=c;
		}
	}


	
	// add() takes two matrices and adds their elements together, forming a new matrix
	public static int[][] add(int[][] a, int[][] b)
	{
		int size = a.length;
		int[][] added = new int[size][size]; // create matrix to hold the sum matrix
		for(int i = 0; i < size; i++)
		{
			for(int z = 0; z < size; z ++)
			{
				added[i][z] = a[i][z] + b[i][z];
			}
		}
		return added;	// return the sum matrix
	}


	// subtract() takes two matrices and subtracts their elements, forming a new matrix
	public static int[][] subtract(int[][] a, int[][] b)
	{
		int size = a.length;
		int[][] subbed = new int[size][size]; // create matrix to hold the difference matrix
		for(int s = 0; s < size; s++)
		{
			for(int y = 0; y < size; y++)
			{
				subbed[s][y] = a[s][y] - b[s][y];
			}
		}
		return subbed;	// return the difference matrix
	}


	
	// combine() takes a quadrant submatrix, a whole matrix, # of rows, and # of columns as arguments
	// 	Fills the given rows and columns of the whole matrix and fills them with the elements from
	// 	the quadrant submatrix
	public static int[][] combine(int[][] part, int[][] whole, int r, int c)
	{
		int t1 = r;
		int t2 = c;
		for(int q = 0; q < part.length; q++) // traverse the rows and columns of the quadrant (part-matrix)
		{				     // fill the corresponding elements of the whole matrix
			for(int w = 0; w < part.length; w++)
			{
				whole[t1][t2++] = part[q][w];
			}
			t1++;
			t2=c;
		}
		return whole;	// return the whole matrix
	}
}
