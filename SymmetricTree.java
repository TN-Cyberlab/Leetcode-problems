import java.util.LinkedList;
import java.util.Queue;

/**
 * Check whether a binary tree is symmetric (mirror of itself).
 */
public class SymmetricTree {

    // Simple tree node
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int val) { this.val = val; }
    }

    /* ----------------------
       1) Recursive solution
       ---------------------- */

    /**
     * Returns true if the tree rooted at root is symmetric.
     * Uses recursion to compare left and right subtrees as mirrors.
     */
    public static boolean isSymmetricRecursive(TreeNode root) {
        if (root == null) return true;
        return isMirror(root.left, root.right);
    }

    private static boolean isMirror(TreeNode a, TreeNode b) {
        if (a == null && b == null) return true;
        if (a == null || b == null) return false;
        if (a.val != b.val) return false;
        // left of a vs right of b, and right of a vs left of b
        return isMirror(a.left, b.right) && isMirror(a.right, b.left);
    }

    /* -----------------------
       2) Iterative solution
       ----------------------- */

    /**
     * Iterative approach using queue to compare paired nodes.
     */
    public static boolean isSymmetricIterative(TreeNode root) {
        if (root == null) return true;
        Queue<TreeNode> q = new LinkedList<>();
        // enqueue left and right children as a pair
        q.add(root.left);
        q.add(root.right);

        while (!q.isEmpty()) {
            TreeNode a = q.poll();
            TreeNode b = q.poll();

            if (a == null && b == null) continue;
            if (a == null || b == null) return false;
            if (a.val != b.val) return false;

            // enqueue children in mirrored order
            q.add(a.left);
            q.add(b.right);

            q.add(a.right);
            q.add(b.left);
        }
        return true;
    }

    /* ------------
       Demo / tests
       ------------ */

    public static void main(String[] args) {
        // Example 1: symmetric tree
        //       1
        //     /   \
        //    2     2
        //   / \   / \
        //  3  4  4  3
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(2);
        root1.left.left = new TreeNode(3);
        root1.left.right = new TreeNode(4);
        root1.right.left = new TreeNode(4);
        root1.right.right = new TreeNode(3);

        System.out.println("example1 recursive: " + isSymmetricRecursive(root1)); // true
        System.out.println("example1 iterative: " + isSymmetricIterative(root1)); // true

        // Example 2: not symmetric
        //    1
        //   / \
        //  2   2
        //   \   \
        //   3    3
        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(2);
        root2.right = new TreeNode(2);
        root2.left.right = new TreeNode(3);
        root2.right.right = new TreeNode(3);

        System.out.println("example2 recursive: " + isSymmetricRecursive(root2)); // false
        System.out.println("example2 iterative: " + isSymmetricIterative(root2)); // false
    }
}
