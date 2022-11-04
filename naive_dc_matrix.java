// Performs Naive Divide and Conquer Matrix Multiplication

public class naive_dc_matrix 
{
	public  int[][] ndc_multiply(int[][] a, int[][] b)
	{
		int size = a.length;	// get the matrix size
		int[][] product = new int[size][size]; // create matrix for the product

		if(size == 1)		// base case
		{
			product[0][0] = a[0][0] * b[0][0];
			return product;
		}
		else
		{	// split a[][]into four quadrants
			int a11[][] = new int[size/2][size/2];
			int a12[][] = new int[size/2][size/2];
			int a21[][] = new int[size/2][size/2];
			int a22[][] = new int[size/2][size/2];
			// fill the a[][] quadrants
			quadsplit(a, a11, 0, 0);
			quadsplit(a, a12, 0, size/2);
			quadsplit(a, a21, size/2, 0);
			quadsplit(a, a22, size/2, size/2);

			// split b[][] into four quadrants
			int b11[][] = new int[size/2][size/2];
			int b12[][] = new int[size/2][size/2];
			int b21[][] = new int[size/2][size/2];
			int b22[][] = new int[size/2][size/2];
			//fill the b[][] qaudrants
			quadsplit(b, b11, 0, 0);
			quadsplit(b, b12, 0, size/2);
			quadsplit(b, b21, size/2, 0);
			quadsplit(b, b22, size/2, size/2);

			// split product[][] into four quadrants, and fill them
			int[][] p11 = add(ndc_multiply(a11, b11), ndc_multiply(a12, b21));
			int[][] p12 = add(ndc_multiply(a11, b12), ndc_multiply(a12, b22));
			int[][] p21 = add(ndc_multiply(a21, b11), ndc_multiply(a22, b21));
			int[][] p22 = add(ndc_multiply(a21, b12), ndc_multiply(a22, b22));

			// combine the parts of product into one matrix
			combine(p11, product, 0, 0);
			combine(p12, product, 0, size/2);
			combine(p21, product, size/2, 0);
			combine(p22, product, size/2, size/2);
		}

		return product;		// return the product matrix
	}


	// quadsplit() takes the original matrix, a quadrant submatrix, # of rows, and # of columns as arguments 
	// 	Iterates through the given rows and columns of the original matrix 
	// 	and fills the quadrant submatrix
	public static void quadsplit(int[][] og, int[][] quad, int r, int c)
	{
		int t1 = r;
		int t2 = c;
		for(int i = 0; i < quad.length; i++) // traverse the rows and columns of the qaudrant
		{				     // fill the quadrant with the respective elements from the original matrix
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


	// combine() takes a quadrant submatrix, a whole matrix, # of rows, and # of columns as arguments
	// 	Fills the given rows and columns of the whole matrix and fills them with the elements from
	// 	the quadrant submatrix
	public static int[][] combine(int[][] part, int[][] whole, int r, int c)
	{
		int t1 = r;
		int t2 = c;
		for(int q = 0; q < part.length; q++) // traverse the rows and columns of the quadrant (part-matrix)
		{				     // fill the corresponding rows elements of the actual whole matrix
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
