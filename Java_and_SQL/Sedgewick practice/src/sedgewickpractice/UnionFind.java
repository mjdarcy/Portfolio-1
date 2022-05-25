package sedgewickpractice;

public class UnionFind {
/*
1.5.1 Show the contents of the id[] array and the number of times the array is accessed for each input pair when you use quick-find for the sequence
9-0 3-4 5-8 7-2 2-1 5-7 0-3 4-2.
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9]

[0, 1, 2, 3, 4, 5, 6, 7, 8, 0]
[0, 1, 2, 4, 4, 5, 6, 7, 8, 0]
[0, 1, 2, 8, 8, 5, 6, 7, 8, 0]
[0, 1, 1, 8, 8, 5, 6, 7, 8, 0]
[0, 1, 1, 8, 8, 7, 6, 7, 8, 0]
[8, 1, 1, 8, 8, 7, 6, 7, 8, 8]
[1, 1, 1, 1, 1, 7, 6, 7, 1, 1]*/

/*
1.5.2 Do Exercise 1.5.1, but use quick-union (page 224). In addition, draw the forest of
trees represented by the id[] array after each input pair is processed.
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
[0, 1, 2, 3, 4, 5, 6, 7, 8, 0]
[0, 1, 2, 4, 4, 5, 6, 7, 8, 0]
[0, 1, 2, 4, 4, 8, 6, 7, 8, 0]
[0, 1, 2, 4, 4, 8, 6, 2, 8, 0]
[0, 1, 1, 4, 4, 8, 6, 2, 8, 0]
[0, 1, 1, 4, 4, 8, 6, 2, 1, 0]
[4, 1, 1, 4, 4, 8, 6, 2, 1, 0]
[4, 1, 1, 4, 4, 8, 6, 2, 1, 0]*/

/*
1.5.3 Do Exercise 1.5.1, but use weighted quick-union (page 228).
[2, 1, 9, 1, 4, 1, 1, 1, 2, 1]
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9]

[0, 1, 2, 3, 4, 5, 6, 7, 8, 0]
[0, 1, 2, 4, 4, 5, 6, 7, 8, 0]
[0, 1, 2, 4, 4, 8, 6, 7, 8, 0]
[0, 1, 2, 4, 4, 8, 6, 2, 8, 0]
[0, 2, 2, 4, 4, 8, 6, 2, 8, 0]
[0, 2, 2, 4, 4, 8, 6, 2, 2, 0]
[4, 2, 2, 4, 4, 8, 6, 2, 2, 0]
[4, 2, 2, 4, 2, 8, 6, 2, 2, 0]
*/

/*
1.5.4 Show the contents of the sz[] and id[] arrays and the number of array accesses
for each input pair corresponding to the weighted quick-union examples in the text
(both the reference input and the worst-case input).
Best case:
[1, 1, 3, 1, 4, 1, 6, 1, 1, 1]
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9]

[0, 1, 2, 4, 4, 5, 6, 7, 8, 9]
[0, 1, 2, 4, 4, 5, 6, 7, 4, 9]
[0, 1, 2, 4, 4, 6, 6, 7, 4, 9]
[0, 1, 2, 4, 4, 6, 6, 7, 4, 4]
[0, 2, 2, 4, 4, 6, 6, 7, 4, 4]
[6, 2, 2, 4, 4, 6, 6, 7, 4, 4]
[6, 2, 2, 4, 4, 6, 6, 2, 4, 4]
[6, 2, 6, 4, 4, 6, 6, 2, 4, 4]

Worst case:
[8, 1, 2, 1, 4, 1, 2, 1]
[0, 1, 2, 3, 4, 5, 6, 7]

[0, 0, 2, 2, 4, 4, 6, 6]
[0, 0, 0, 2, 4, 4, 6, 6]
[0, 0, 0, 2, 4, 4, 6, 6]
[0, 0, 0, 2, 4, 4, 4, 6]
[0, 0, 0, 2, 0, 4, 4, 6]
*/

/*
1.5.5 Estimate the minimum amount of time (in days) that would be required for
quick-find to solve a dynamic connectivity problem with 109 sites and 106 input pairs,
on a computer capable of executing 109 instructions per second. Assume that each iteration of the inner for loop requires 10 machine instructions.

constructor * union = 109 * 106 = 11,554‬ statements, 11,554‬ / 109 = 106 seconds

1.5.6 Repeat Exercise 1.5.5 for weighted quick-union.

(109 * lg(106)) / 109 = ~6.73 seconds

1.5.7 Develop classes QuickUnionUF and QuickFindUF that implement quick-union
and quick-find, respectively.*/
	
class QuickUnionUF
{
	
	int[] arr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
	
	int findRoot(int n)
	{
		while(arr[n] != n) n = arr[n];
		return n;
	}
	
	void quickUnion(int[] input)
	{
		int aRoot = findRoot(input[0]);
		int bRoot = findRoot(input[1]);
		input[aRoot] = input[bRoot];
	}
}

class QuickFindUF
{
	
	int[] arr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
	
	int quickFind(int n)
	{
		//can be read: quickFindRoot. The root of n is always arr[n].
		return arr[n];
	}
}
/*
1.5.8 Give a counterexample that shows why this intuitive implementation of union()
for quick-find is not correct:
public void union(int p, int q)
{
 if (connected(p, q)) return;
 // Rename p’s component to q’s name.
 for (int i = 0; i < id.length; i++)
 if (id[i] == id[p]) id[i] = id[q];
 count--;
}

The connected function isn't shown, which can be replaced with id[p] == id[q].*/

/*
1.5.9 Draw the tree corresponding to the id[] array depicted at
right. Can this be the result of running weighted quick-union?
Explain why this is impossible or give a sequence of operations
that results in this array.
i     0 1 2 3 4 5 6 7 8 9
id[i] 1 1 3 1 5 6 1 3 4 5

5 should attach to 1, not 6. There is no series of inputs that would attach 5 and 6.
8 and 4 combine, 9 and 5 combine, this makes the tree of 4.
From here 6 couldn't be set as its parent node no matter what.*/

/*
1.5.10 In the weighted quick-union algorithm, suppose that we set id[find(p)] to q
instead of to id[find(q)]. Would the resulting algorithm be correct?

Yes but the tree height would be larger.*/

/*
1.5.11 Implement weighted quick-find, where you always change the id[] entries of
the smaller component to the identifier of the larger component. How does this change
affect performance?
It should make it the same as quick-find since the criteria for setting the root is arbitrary again,
not based on the size of the tree.
Making the root the larger number would result in it being more ordered numerically,
but the numbers in this problem are simply nominal, they're only used as labels.
*/
}
