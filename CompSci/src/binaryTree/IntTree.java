package binaryTree;

public class IntTree {

    private Node head;

    public IntTree() {

    }

    private class Node {
        private int data;
        private Node left;
        private Node right;

        public Node(int d) {
            data = d;
        }

        private String stringChildren() {
            String out = "";
            if (left != null) {
                out += left.stringChildren() + " ";
            }
            out += Integer.toString(data);
            if (right != null) {
                out += " " + right.stringChildren();
            }
            return out;
        }

        private int amtOfChildren() {

            int out = 1;
            if (left != null) { out += left.amtOfChildren(); }
            if (right != null) { out += right.amtOfChildren(); }
            return out;
        }

        private int maxDepthOfChildren() {
            if (left == null && right == null) {
                return 1;
            }
            int leftDepth = 0, rightDepth = 0;
            if (left != null) {
                leftDepth = left.maxDepthOfChildren();
            }
            if (right != null) {
                rightDepth = right.maxDepthOfChildren();
            }
            return Math.max(leftDepth, rightDepth) + 1;
        }

    }

    // WRITING DATA

    public void insert(int data) {

        if (lacksData()) {
            head = new Node(data);
            return;
        }

        Node curNode = head;

        while (true) {
            if (data < curNode.data) {
                if (curNode.left == null) {
                    curNode.left = new Node(data);
                    break;
                } else {
                    curNode = curNode.left;
                }
            } else {
                if (curNode.right == null) {
                    curNode.right = new Node(data);
                    break;
                } else {
                    curNode = curNode.right;
                }
            }
        }

    }

    // GETTING DATA

    public int getMinValue() {
        throwIfNull();

        Node curNode = head;

        while (curNode.left != null) {
            curNode = curNode.left;
        }

        return curNode.data;
    }

    public int getMaxValue() {
        throwIfNull();

        Node curNode = head;

        while (curNode.right != null) {
            curNode = curNode.right;
        }

        return curNode.data;
    }

    public int size() {
        if (lacksData()) {
            return 0;
        }
        return head.amtOfChildren();
    }

    public int maxDepth() {
        return head.maxDepthOfChildren();
    }

    // CHECKING FOR STUFF

    public void throwIfNull() {
        if (head == null) { throw new NullPointerException("There is no data!"); }
    }

    public boolean lacksData() {
        return head == null;
    }

    // DISPLAYING

    public String fancyString() {
        return head.stringChildren();
    }

}
