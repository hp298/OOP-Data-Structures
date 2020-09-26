
/* NetId(s): djg17

 * Name(s): David Gries
 * What I thought about this assignment:
 *
 *
 */

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/** An instance of VirusTree represents the spread of a Virus among a Network of people. <br>
 * In this model, each person can "catch" the virus from only a single person. <br>
 * The root of the VirusTree is the person who first got the virus. <br>
 * From there, each person in the VirusTree is the child of the person who gave <br>
 * them the virus. For example, for the tree:
 *
 * <pre>
 *       A
 *      / \
 *     B   C
 *        / \
 *       D   E
 * </pre>
 *
 * Person A originally got the virus, B and C caught the virus from A, and<br>
 * D and E caught the virus from C.
 *
 * Important note: The name of each person in the virus tree is unique. */
public class VirusTree {

	/** Replace "-1" by the time you spent on A2 in hours.<br>
	 * Example: for 3 hours 15 minutes, use 3.25<br>
	 * Example: for 4 hours 30 minutes, use 4.50<br>
	 * Example: for 5 hours, use 5 or 5.0 */
	public static double timeSpent= -1;

	/** The String to be used as a separator in toString() */
	public static final String SEPARATOR= " - ";

	/** The String that marks the start of children in toString() */
	public static final String START_CHILDREN_DELIMITER= "[";

	/** The String that divides children in toString() */
	public static final String DELIMITER= ", ";

	/** The String that marks the end of children in toString() */
	public static final String END_CHILDREN_DELIMITER= "]";

	/** The String that is the space increment in toStringVerbose() */
	public static final String VERBOSE_SPACE_INCREMENT= "\t";

	/** The person at the root of this VirusTree. <br>
	 * i.e. the person in this node of the VirusTree. <br>
	 * This is the virus ancestor of everyone in this VirusTree: <br>
	 * the person who got sick first and indirectly caused everyone in it to get sick. <br>
	 * root is non-null. <br>
	 * All Person's in a VirusTree have different names. There are no duplicates */
	private Person root;

	/** The children of this VirusTree node. <br>
	 * Each element of children got the virus from the person at this node. <br>
	 * root is non-null. It is the empty set if this node is a leaf. */
	private Set<VirusTree> children;

	/** Constructor: a new VirusTree with root p and no children. <br>
	 * Throw an IllegalArgumentException if p is null. */
	public VirusTree(Person p) throws IllegalArgumentException {
		if (p == null)
			throw new IllegalArgumentException("Can't construct VirusTree with null root");
		root= p;
		children= new HashSet<>();
	}

	/** Constructor: a new VirusTree that is a copy of tree p. <br>
	 * Tree p and its copy have no node in common (but nodes can share a Person). <br>
	 * Throw an IllegalArgumentException if p is null. */
	public VirusTree(VirusTree p) throws IllegalArgumentException {
		if (p == null)
			throw new IllegalArgumentException("Can't construct copy of null VirusTree");
		root= p.root;
		children= new HashSet<>();

		for (VirusTree dt : p.children) {
			children.add(new VirusTree(dt));
		}
	}

	/** = the person that is at the root of this VirusTree. */
	public Person getRoot() {
		return root;
	}

	/** = the number of direct children of this VirusTree. */
	public int childrenSize() {
		return children.size();
	}

	/** = a COPY of the set of children of this VirusTree. */
	public Set<VirusTree> copyOfChildren() {
		return new HashSet<>(children);
	}

	/** = the VirusTree object in this tree whose root is p. <br>
	 * (null if p is not in this tree). */
	public VirusTree getNode(Person p) {
		if (root == p) return this; // Base case

		// Recursive case - Return the node with Person p if a child contains it.
		for (VirusTree dt : children) {
			VirusTree node= dt.getNode(p);
			if (node != null) return node;
		}

		return null; // p is not in the tree
	}

	/** Insert c in this VirusTree as a child of p and return <br>
	 * the VirusTree object whose root is the new child. <br>
	 * Throw an IllegalArgumentException if:<br>
	 * -- c or p is null, or<br>
	 * -- c is already in this VirusTree, or<br>
	 * -- p is not in this VirusTree. */
	public VirusTree insert(Person c, Person p) throws IllegalArgumentException {
		// TODO 1
		// This method should not be recursive.
		// Use method getNode, above, and use no methods that are below.
		// DO NOT traverse the tree twice looking for the same node
		// ---don't duplicate work.

		return null;
	}

	/** = the number of persons in this VirusTree. <br>
	 * Note: If this is a leaf, the size is 1 (just the root) */
	public int size() {
		// TODO 2. This method must be recursive.

		return -1;
	}

	/** = "this virus tree contains a node with person p." */
	public boolean contains(Person p) {
		// TODO 3
		/* Recursive definition: This VirusTree contains p iff the root of
		 * this VirusTree is p or if one of p's children contains p. */

		return false;
	}

	/*** = the depth at which p occurs in this VirusTree, or <br>
	 * -1 if p is not in the VirusTree. <br>
	 * Note: depth(root) is 0. <br>
	 * If p is a child of this VirusTree node, then depth(p) is 1. etc. */
	public int depth(Person p) {
		// TODO 4
		// Note: Do NOT call function contains(p) to find out whether p is in
		// the tree. Do not have extra calls that traverse the tree that are not necessary.
		// Study the two tutorials on processing recursive calls and use the
		// correct pattern here.

		// Here is a recursive insight:
		// ... Let child c of the root contain p.
		// ... Then the depth of p in the root = (1 + depth of p in c)

		return -1;
	}

	/** Return the width of this tree at depth d <br>
	 * (i.e. the number of virusTrees that occur at depth d).<br>
	 * Remember: the depth of the root is 0; the depth of its children is 1, etc.<br>
	 * Throw an IllegalArgumentException if d < 0.<br>
	 * Thus, for the following tree : Depth level: 0.<br>
	 *
	 * <pre>
	  * Depth  level:
	 *  0         A
	 *           / \
	 *  1       B   C
	 *         /   / \
	 *  2     D   E   F
	 *             \
	 *  3           G
	 * </pre>
	 *
	 * A.widthAtDepth(0) = 1, A.widthAtDepth(1) = 2,<br>
	 * A.widthAtDepth(2) = 3, A.widthAtDepth(3) = 1,<br>
	 * A.widthAtDepth(4) = 0.<br>
	 * C.widthAtDepth(0) = 1, C.widthAtDepth(1) = 2 */
	public int widthAtDepth(int d) throws IllegalArgumentException {
		// TODO 5
		// It may help to read Piazza note @213, Stepwise refinement when developing widthAtDepth.
		// Do not have calls that unnecessarily traverse the tree, causing the tree to be
		// traversed more than once.
		// Use this recursive definition:
		// ..... If d = 0, the answer is 1.
		// ..... If d > 0, the answer is: sum of widths of the children at depth d-1.

		return 0;

	}

	/** Return the route the virus took to get from "here" (the root of <br>
	 * this VirusTree) to descendent c. If there is no such route, return null.<br>
	 * For example, for this tree:<br>
	 *
	 * <pre>
	 * Depth:
	 *    0           A
	 *               / \
	 *    1         B   C
	 *             /   / \
	 *    2       D   E   F
	 *             \
	 *    3         G
	 * </pre>
	 *
	 * A.virusRouteTo(E) should return a list of [A,C,E].<br>
	 * A.virusRouteTo(A) should return [A]. <br>
	 * A.virusRouteTo(X) should return null.<br>
	 * B.virusRouteTo(C) should return null. <br>
	 * B.virusRouteTo(D) should return [B,D] */
	public List<Person> virusRouteTo(Person c) {
		// TODO 6
		// 0. Calling getParent will give you 0 for this method. It is too inefficient,
		// requiring possible many traversals of the tree.
		//
		// 1. The ONLY case in which this method must create a list object in the base
		// case, when c is the root. Do NOT create a new list object in the recursive case.

		// 2. The method must return a List<Person> object. But List is an interface, so
		// use something that implements it. LinkedList<Person> is preferred to ArrayList<Person>,
		// because prepend (or its equivalent) may have to be used.

		// 3. Base Case: The root of this VirusTree is c; i.e. the Route is just [c].

		return null;
	}

	/** If either child1 or child2 is null or is not in this VirusTree, return null.<br>
	 * Otherwise, return the person at the root of the smallest subtree of this<br>
	 * VirusTree that contains child1 and child2.<br>
	 *
	 * Examples. For the following tree (which does not contain H):
	 *
	 * <pre>
	 * Depth:
	 *    0      A
	 *          / \
	 *    1    B   C
	 *        /   / \
	 *    2  D   E   F
	 *        \
	 *    3    G
	 * </pre>
	 *
	 * A.commonAncestor(B, A) is A<br>
	 * A.commonAncestor(B, B) is B<br>
	 * A.commonAncestor(B, C) is A<br>
	 * A.commonAncestor(A, C) is A<br>
	 * A.commonAncestor(E, F) is C<br>
	 * A.commonAncestor(G, F) is A<br>
	 * B.commonAncestor(B, E) is null<br>
	 * B.commonAncestor(B, A) is null<br>
	 * B.commonAncestor(D, F) is null<br>
	 * B.commonAncestor(D, H) is null<br>
	 * A.commonAncestor(null, C) is null */
	public Person commonAncestor(Person child1, Person child2) {
		// TODO 7
		/* Do not use method getParent(), which is far below. Its use over and over again
		 * is inefficient. Calling getParent results in a grade of 0 for this method.
		 *
		 * Instead, find the virus routes l1 and l2 to the two children. If they are not
		 * null, then two things are known:
		 * (1) l1[0] = l2[0]
		 * (2) the answer l1[i] is the largest i such that l1[0..i] = l2[0..i].
		 * If this is not clear, draw an example.
		 * The answer, then, can be found using a loop. No recursion is needed.
		 *
		 * You have a problem of writing this loop efficiently. You can't use a foreach loop
		 * on both lists simultaneously. The simplest thing to do is to use List's
		 * function toArray twice and then work with the array representations of the lists. */

		return null;
	}

	/** Return true iff this is equal to ob.<br>
	 * 1. If this and ob are not of the same class, they are not equal, so return false.<br>
	 * 2. Two VirusTrees are equal if<br>
	 * -- (1) they have the same root Person object (==) AND<br>
	 * -- (2) their children sets are the same size AND<br>
	 * -- (3) their children sets are equal.<br>
	 * ------ Since their sizes are equal, this requires:<br>
	 * -------- for every VirusTree dt1 in one set there is a VirusTree<br>
	 * -------- dt2 in the other set for which dt1.equals(dt2) is true.<br>
	 *
	 * -- Otherwise the two VirusTrees are not equal.<br>
	 * Do not use any of the toString functions to write equals(). <br>
	 * Do not use Set's function equals. */
	@Override
	public boolean equals(Object ob) {
		// TODO 8
		// The specification outlines what must be done, in detail. Two points to discuss:
		// (1) Stay away from nested ifs as much as possible! Instead, as soon as it is
		// determined that a property for equality is not met, return false!
		// So the structure could be:

		// if (property 1 not met) return false;
		// if (property 2 not met) return false;
		// etc.

		// (2) The difficult part is testing this:
		// ... for every VirusTree st in one set S1, there is a VirusTree st2
		// ... in the other set S2 for which st.equals(st2) is true.
		//
		// We suggest you write a helper method with this specification:
		// /** Return true iff st equals some member of s2 */
		// private boolean help(VirusTree st, Set<VirusTree> s2)
		//
		// Note that this method is going to call equals, like this: st.equals(...).
		// That is a call to the method you are trying to write! We have
		// "mutual recursion": equals calls help, which calls equals, which call help ...
		// But when thinking about what a call does, USE THE SPECIFICATION to understand
		// what it does.
		//
		// Hint about checking whether each child of one tree equals SOME
		// tree of the other tree's children.
		// First, you have to check them all until you find an equal one (or
		// return false if you don't.)
		// Second, A child of one tree cannot equal more than one child of
		// tree because the names of Person's are all unique;
		// there are no duplicates.

		return false;

	}

	/* ========================================================================
	 * ========================================================================
	 * ========================================================================
	 * Do not use the methods written below. They are used to calculate data
	 * for the GUI.
	 * Feel free to read/study them. */

	/** Return the maximum depth of this VirusTree, <br>
	 * i.e. the longest path from the root to a leaf.<br>
	 * Example. If this VirusTree is a leaf, return 0. */
	public int maxDepth() {
		int maxDepth= 0;
		for (VirusTree dt : children) {
			maxDepth= Math.max(maxDepth, dt.maxDepth() + 1);
		}
		return maxDepth;
	}

	/** Return the immediate parent of c (null if c is not in this VirusTree).<br>
	 * Thus, for the following tree:
	 *
	 * <pre>
	 * Depth:
	 *    0      A
	 *          / \
	 *    1    B   C
	 *        /   / \
	 *    2  D   E   F
	 *        \
	 *    3    G
	 * </pre>
	 *
	 * A.getParent(E) returns C.<br>
	 * C.getParent(E) returns C.<br>
	 * A.getParent(B) returns A.<br>
	 * E.getParent(F) returns null. */
	public Person getParent(Person c) {
		// Base case
		for (VirusTree dt : children) {
			if (dt.root == c) return root;
		}

		// Recursive case - ask children to look
		for (VirusTree dt : children) {
			Person parent= dt.getParent(c);
			if (parent != null) return parent;
		}

		return null; // Not found
	}

	/** Return the maximum width of all the widths in this tree, <br>
	 * i.e. the maximum value that could be returned from widthAtDepth for this tree. */
	public int maxWidth() {
		return maxWidthImplementationTwo(this);
	}

	/** Simple implementation of maxWidth. <br>
	 * Relies on widthAtDepth. <br>
	 * Takes time proportional to the square of the size of the t. */
	static int maxWidthImplementationOne(VirusTree t) {
		int width= 0;
		int depth= t.maxDepth();
		for (int i= 0; i <= depth; i++ ) {
			width= Math.max(width, t.widthAtDepth(i));
		}
		return width;
	}

	/** Better implementation of maxWidth. Caches results in an array. <br>
	 * Takes time proportional to the size of t. */
	static int maxWidthImplementationTwo(VirusTree t) {
		// For each integer d, 0 <= d <= maximum depth of t, store in
		// widths[d] the number of nodes at depth d in t.
		// The calculation is done by calling recursive procedure addToWidths.
		int[] widths= new int[t.maxDepth() + 1];   // initially, contains 0's
		t.addToWidths(0, widths);

		int max= 0;
		for (int width : widths) {
			max= Math.max(max, width);
		}
		return max;
	}

	/** For each node of this VirusTree, which is at some depth d in this VirusTree,<br>
	 * add 1 to widths[depth + d]. */
	private void addToWidths(int depth, int[] widths) {
		widths[depth]++ ;        // the root of this VirusTree is at depth d = 0
		for (VirusTree dt : children) {
			dt.addToWidths(depth + 1, widths);
		}
	}

	/** Better implementation of maxWidth. Caches results in a HashMap. <br>
	 * Takes time proportional to the size of t. */
	static int maxWidthImplementationThree(VirusTree t) {
		// For each possible depth d >= 0 in tree t, widthMap will contain the
		// entry (d, number of nodes at depth d in t). The calculation is
		// done using recursive procedure addToWidthMap.

		// For each integer d, 0 <= d <= maximum depth of t, add to
		// widthMap an entry <d, 0>.
		HashMap<Integer, Integer> widthMap= new HashMap<>();
		for (int d= 0; d <= t.maxDepth() + 1; d++ ) {
			widthMap.put(d, 0);
		}

		t.addToWidthMap(0, widthMap);

		int max= 0;
		for (Integer w : widthMap.values()) {
			max= Math.max(max, w);
		}
		return max;
	}

	/** For each node of this VirusTree, which is at some depth d in this VirusTree,<br>
	 * add 1 to the value part of entry <depth + d, ...> of widthMap. */
	private void addToWidthMap(int depth, HashMap<Integer, Integer> widthMap) {
		widthMap.put(depth, widthMap.get(depth) + 1);  // the root is at depth d = 0
		for (VirusTree dt : children) {
			dt.addToWidthMap(depth + 1, widthMap);
		}
	}

	/** Return a (single line) String representation of this VirusTree.<br>
	 * If this VirusTree has no children (it is a leaf), return the root's substring.<br>
	 * Otherwise, return<br>
	 * ... root's substring + SEPARATOR + START_CHILDREN_DELIMITER + each child's<br>
	 * ... toString, separated by DELIMITER, followed by END_CHILD_DELIMITER.<br>
	 *
	 * Make sure there is not an extra DELIMITER following the last child.<br>
	 *
	 * Finally, make sure to use the static final fields declared at the top of<br>
	 * VirusTree.java.<br>
	 *
	 * Thus, for the following tree:
	 *
	 * <pre>
	 * Depth level:
	 *   0         A
	 *            / \
	 *   1        B  C
	 *           /  / \
	 *   2      D  E   F
	 *           \
	 *   3        G
	 *
	 * A.toString() should print:
	 * (A) - HEALTHY - [(C) - HEALTHY - [(F) - HEALTHY, (E) - HEALTHY - [(G) - HEALTHY]], (B) - HEALTHY - [(D) - HEALTHY]]
	 *
	 * C.toString() should print:
	 * (C) - HEALTHY - [(F) - HEALTHY, (E) - HEALTHY - [(G) - HEALTHY]]
	 * </pre>
	 */
	@Override
	public String toString() {
		if (children.isEmpty()) return root.toString();
		String s= root.toString() + SEPARATOR + START_CHILDREN_DELIMITER;
		for (VirusTree dt : children) {
			s= s + dt.toString() + DELIMITER;
		}
		return s.substring(0, s.length() - 2) + END_CHILDREN_DELIMITER;
	}

	/** Return a verbose (multi-line) string representing this VirusTree. */
	public String toStringVerbose() {
		return toStringVerbose(0);
	}

	/** Return a verbose (multi-line) string representing this VirusTree.<br>
	 * Each human in the tree is on its own line, with indentation representing<br>
	 * what each human is a child of.<br>
	 * indent is the the amount of indentation to put before this line.<br>
	 * Should increase on recursive calls to children to create the above pattern.<br>
	 * Thus, for the following tree:
	 *
	 * <pre>
	 * Depth level:
	 *   0         A
	 *            / \
	 *   1       B   C
	 *          /   / \
	 *   2     D   E   F
	 *          \
	 *   3       G
	 *
	 * A.toStringVerbose(0) should return:
	 * (A) - HEALTHY
	 * (C) - HEALTHY
	 * (F) - HEALTHY
	 * (E) - HEALTHY
	 * (G) - HEALTHY
	 * (B) - HEALTHY
	 * (D) - HEALTHY
	 * </pre>
	 *
	 * Make sure to use VERBOSE_SPACE_INCREMENT for indentation. */
	private String toStringVerbose(int indent) {
		String s= "";
		for (int i= 0; i < indent; i++ ) {
			s= s + VERBOSE_SPACE_INCREMENT;
		}
		s= s + root.toString();

		if (children.isEmpty()) return s;

		for (VirusTree dt : children) {
			s= s + "\n" + dt.toStringVerbose(indent + 1);
		}
		return s;
	}

}
