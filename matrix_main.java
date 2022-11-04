import java.util.Random;
import java.util.Scanner;

/* 	matrix_main class generates random matrices and multiplies them 3 different ways using
	functions I coded in 3 separate java classes/files:
	classic_matrix, naive_dc_matrix, and strassen_matrix
*/
public class matrix_main 
{
	// make_matrix() genarates a random matrix of the given size (n x n)
	// use Random to fill matrix with random numbers: range (-100 to 100)
	public static int[][] make_matrix(int n)
	{
		Random randNum = new Random();	
		int[][] mat = new int[n][n];	// create empty n x n matrix
		for(int i = 0; i < n; i++)
		{			
			for(int j = 0; j < n; j++)
			{			// fill matrix with random numbers
				mat[i][j] = (randNum.nextInt(200)-100);
			}
		}
		return mat;	// return newly created matrix
	}


	// print_matrix() prints out a given matrix
	public static void print_matrix(int[][] m)
	{
		int size = m.length;
		for(int i = 0; i < size; i++)
		{
			for(int w = 0; w < size; w++)
			{
				System.out.print(m[i][w]+"\t");
			}
			System.out.print("\n");
		}
	}

	/*	
	 * tests() asks user to input a size for 2 matrices, calls make_matrix() to create
	 * 2 random matrices of the given size, then uses functions from classic_matrix, 
	 * naive_dc_matrix, and strassen_matrix to perform matrix multiplication 3 different ways.
	 * Lastly, it calls print_matrix() to print out each matrix.
	 * 
	 * 	NOTE: I will run each algorithm 20 times each
	 * 	NOTE: the maximum size array that my ocmputer can handle is 1024 x 1024
	 */
	public static void tests()
	{
		int cap = 20;
		long time_classic = 0;
		long time_naiveDC = 0;
		long time_strass = 0;
		Scanner scan = new Scanner(System.in);	

		System.out.println("Welcome! We will be testing the run-times of 3 different algorithms of matrix multiplication with matrices of"
					+ " different sizes.");
		System.out.print("Please enter an integer (n) for the size n x n of the two matrices (n must be a power of 2, MAX size is 1024): ");
		int size = scan.nextInt();		// get size for matrices from user
		scan.close();
	
		for(int c = 0; c < cap; c++)
		{
			int[][] m1 = make_matrix(size);		// create the two random matrices
			int[][] m2 = make_matrix(size);

			System.out.println("\nMatrix 1:");	// print the two random matrices
			print_matrix(m1);
			System.out.println("\nMatrix 2: ");
			print_matrix(m2);

			//------perform classic matrix multiplication------
			classic_matrix classic = new classic_matrix();
			int[][] cmm = classic.classic_multiply(m1, m2, size);
			System.out.println("\nThe product of the classic matrix multiplication: ");
			long cStart = System.nanoTime();
			print_matrix(cmm);	// print the resulting matrix
			long cEnd = System.nanoTime();
			time_classic += (cEnd - cStart);
		
			//------perform naive divide and conquer matrix multiplication------
			naive_dc_matrix naive = new naive_dc_matrix();
			int[][] ndcm = naive.ndc_multiply(m1, m2);
			System.out.println("\nThe product of the naive divide and conquer matrix multiplication: ");
			long nStart = System.nanoTime();
			print_matrix(ndcm);	// print the resulting matrix
			long nEnd = System.nanoTime();
			time_naiveDC += (nEnd - nStart);

			//------perform strassen's algorithm matrix multiplication------
			strassen_matrix strassen = new strassen_matrix();
			int[][] sam = strassen.strassen(m1, m2);
			System.out.println("\nThe product of Strassen's algorithm for matrix multiplication: ");
			long senStart = System.nanoTime();
			print_matrix(sam);	// print the resulting matrix
			long senEnd = System.nanoTime();
			time_strass += (senEnd - senStart);
		}

		System.out.println("Time taken to solve matrices of size " + size + " x " + size + ":\n"
					+ "Classic: " + time_classic/cap + " nanoseconds\nNaive Divide and Conquer: "
					+ time_naiveDC/cap + " nanoseconds\nStrassen's algorithm: " + time_strass/cap
					+ " nanoseconds");
	}


	//sanity_check() runs a test case of the sanity check that is listed in the instructions 	
	// Creates the two matrices with exact values, then calls the 3 algorithms to multiply
	//them. It then prints the resulitng matrices
	public static void sanity_check()
	{
		int size = 4;	// matrices are of size 4 x 4
		classic_matrix classic = new classic_matrix();	// creates an object for each algorithm, used to execute said algorithms
		naive_dc_matrix naive = new naive_dc_matrix();
		strassen_matrix strassen = new strassen_matrix();

		int[][] cmm = new int[size][size];		// create matrices to hold the products
		int[][] ndcm = new int[size][size];
		int[][] sam = new int[size][size];

		int[][] m1 = {{2, 0, -1, 6},{3, 7, 8, 0},{-5, 1, 6, -2},{8, 0, 1, 7}}; // matrices to be multiplied
		int[][] m2 = {{0, 1, 6, 3},{-2, 8, 7, 1},{2, 0, -1, 0},{9, 1, 6, -2}};
		
		System.out.println("The following is the required sanity check:"); // prints the matrices
		System.out.println("----Matrix 1----");
		print_matrix(m1);
		System.out.println("\nMultiplied By:\n");
		System.out.println("----Matrix 2----");
		print_matrix(m2);

		System.out.println("\nThe product of the classic matrix multiplication: "); // call each algorithm and print their results
		cmm = classic.classic_multiply(m1, m2, size);
		print_matrix(cmm);

		System.out.println("\nThe product of the naive divide and conquer matrix multiplication: ");
		ndcm = naive.ndc_multiply(m1, m2);
		print_matrix(ndcm);

		System.out.println("\nThe product of Strassen's algorithm for matrix multiplication: ");
		sam = strassen.strassen(m1, m2);
		print_matrix(sam);
	}

	// main() is used to call the other functions and run the program
	// 
	// 	Calls sanity_check() function for the sanity check test case
	// 	Calls tests() to run the code for the actual tests for the project
	 
	public static void main(String[] args)
	{	
		sanity_check();	
		tests();
	}
}
