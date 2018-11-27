package binaryTree;

public class IntTreeTest {
    public static void main(String[] args) {
        IntTree tree = new IntTree();
        tree.insert(5);
        tree.insert(6);
        tree.insert(3);
        tree.insert(4);
        tree.insert(10);
        System.out.println(tree.fancyString());
        System.out.println(tree.maxDepth());
    }
}
