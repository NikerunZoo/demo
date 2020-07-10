package zuoProgress.binaryTree.AVLtree;

/**
 * Not implemented by ZwZ
 * <p>
 * AVL AVLtree implementation.
 * <p>
 * In computer science, an AVL AVLtree is a self-balancing binary search AVLtree, and
 * it was the first such data structure to be invented.[1] In an AVL AVLtree, the
 * heights of the two child subtrees of any node differ by at most one. Lookup,
 * insertion, and deletion all take O(log n) time in both the average and worst
 * cases, where n is the number of nodes in the AVLtree prior to the operation.
 * Insertions and deletions may require the AVLtree to be rebalanced by one or more
 * AVLtree rotations.
 *
 * @author Ignas Lelys
 * @created Jun 28, 2011
 */
public class AVLTree extends AbstractSelfBalancingBinarySearchTree {

    /**
     * AVL AVLtree insert method also balances AVLtree if needed. Additional
     * height parameter on node is used to track if one subtree is higher
     * than other by more than one, if so AVL AVLtree rotations is performed
     * to regain balance of the AVLtree.
     */
    @Override
    public Node insert(int element) {
        Node newNode = super.insert(element);//搜索二叉树的一次插入
        rebalance((AVLNode) newNode);
        return newNode;
    }

    @Override
    public Node delete(int element) {
        Node deleteNode = super.search(element);
        if (deleteNode != null) {
            Node successorNode = super.delete(deleteNode);
            if (successorNode != null) {
                // if replaced from getMinimum(deleteNode.right) then come back there and update heights
                AVLNode minimum = successorNode.right != null ? (AVLNode) getMinimum(successorNode.right) : (AVLNode) successorNode;
                recomputeHeight(minimum);
                rebalance((AVLNode) minimum);
            } else {
                recomputeHeight((AVLNode) deleteNode.parent);
                rebalance((AVLNode) deleteNode.parent);
            }
            return successorNode;
        }
        return null;
    }

    @Override
    protected Node createNode(int value, Node parent, Node left, Node right) {
        return new AVLNode(value, parent, left, right);
    }

    /**
     * Go up from inserted node, and update height and balance informations if needed.
     * If some node balance reaches 2 or -2 that means that subtree must be rebalanced.
     *
     * @param node Inserted Node.
     */
    private void rebalance(AVLNode node) {
        while (node != null) {
            Node parent = node.parent;

            int leftHeight = (node.left == null) ? -1 : ((AVLNode) node.left).height;//左树高度
            int rightHeight = (node.right == null) ? -1 : ((AVLNode) node.right).height;//右子树高度
            int nodeBalance = rightHeight - leftHeight;
            // rebalance (-2 means left subtree outgrow, 2 means right subtree)
            if (nodeBalance == 2) {
                if (node.right.right != null) {
                    node = (AVLNode) avlRotateLeft(node);//RR
                    break;
                } else {
                    node = (AVLNode) doubleRotateRightLeft(node);//RL
                    break;
                }
            } else if (nodeBalance == -2) {
                if (node.left.left != null) {
                    node = (AVLNode) avlRotateRight(node);//LL
                    break;
                } else {
                    node = (AVLNode) doubleRotateLeftRight(node);//LR
                    break;
                }
            } else {
                updateHeight(node);
            }

            node = (AVLNode) parent;//向上跑去判断父节点
        }
    }

    /**
     * Rotates to left side.
     */
    private Node avlRotateLeft(Node node) {
        Node temp = super.rotateLeft(node);

        updateHeight((AVLNode) temp.left);
        updateHeight((AVLNode) temp);
        return temp;
    }

    /**
     * Rotates to right side.
     */
    private Node avlRotateRight(Node node) {
        Node temp = super.rotateRight(node);

        updateHeight((AVLNode) temp.right);
        updateHeight((AVLNode) temp);
        return temp;
    }

    /**
     * Take right child and rotate it to the right side first and then rotate
     * node to the left side.
     */
    protected Node doubleRotateRightLeft(Node node) {
        node.right = avlRotateRight(node.right);//先右旋
        return avlRotateLeft(node);//后左旋
    }

    /**
     * Take right child and rotate it to the right side first and then rotate
     * node to the left side.
     */
    protected Node doubleRotateLeftRight(Node node) {
        node.left = avlRotateLeft(node.left);//先左旋
        return avlRotateRight(node);//后右旋
    }

    /**
     * Recomputes height information from the node and up for all of parents. It needs to be done after delete.
     */
    private void recomputeHeight(AVLNode node) {
        while (node != null) {
            node.height = maxHeight((AVLNode) node.left, (AVLNode) node.right) + 1;
            node = (AVLNode) node.parent;
        }
    }

    /**
     * Returns higher height of 2 nodes.
     */
    private int maxHeight(AVLNode node1, AVLNode node2) {
        if (node1 != null && node2 != null) {
            return node1.height > node2.height ? node1.height : node2.height;
        } else if (node1 == null) {
            return node2 != null ? node2.height : -1;
        } else if (node2 == null) {
            return node1 != null ? node1.height : -1;
        }
        return -1;
    }

    /**
     * Updates height and balance of the node.
     *
     * @param node Node for which height and balance must be updated.
     */
    private static final void updateHeight(AVLNode node) {
        int leftHeight = (node.left == null) ? -1 : ((AVLNode) node.left).height;
        int rightHeight = (node.right == null) ? -1 : ((AVLNode) node.right).height;
        node.height = 1 + Math.max(leftHeight, rightHeight);
    }

    /**
     * Node of AVL AVLtree has height and balance additional properties. If balance
     * equals 2 (or -2) that node needs to be re balanced. (Height is height of
     * the subtree starting with this node, and balance is difference between
     * left and right nodes heights).
     *
     * @author Ignas Lelys
     * @created Jun 30, 2011
     */
    protected static class AVLNode extends Node {
        public int height;

        public AVLNode(int value, Node parent, Node left, Node right) {
            super(value, parent, left, right);
        }
    }

}
