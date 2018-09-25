/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package treemain;

/**
 *
 * @author 1
 * @param <T1>
 * @param <T2>
 */
public class BinTree<T1 extends Comparable, T2 extends Comparable> {

    BinTree(T1 key, T2 value) {
        root = new Node<>(key, value);
    }

    Node<T1, T2> root;

    public void print(Node x) {

        if (x.left != null) {
            print(x.left);
        }
        System.out.print(x.value + " ");
        if (x.right != null) {
            print(x.right);
        }
    }

    public Node get(T1 k) {
        Node<T1, T2> x = root;
        while (x != null) {
            int cmp;
            cmp = k.compareTo(x.key);

            if (cmp == 0) {
                return x;
            }
            if (cmp < 0) {
                x = x.left;
            } else {
                x = x.right;
            }
        }
        return null;
    }

    
    public void add(T1 k, T2 v) {
        Node<T1, T2> x = root, y = null;
        while (x != null) {
            int cmp = k.compareTo(x.key);
            if (cmp == 0) {
                x.value = v;
                return;
            } else {
                y = x;
                if (cmp < 0) {
                    x = x.left;
                } else {
                    x = x.right;
                }
            }
        }
        Node<T1, T2> newBinTree = new Node<T1, T2>(k, v);
        if (y == null) {
            root = newBinTree;
        } else if (k.compareTo(y.key) < 0) {
            y.left = newBinTree;
        } else {
            y.right = newBinTree;
        }
    }

    public void delete(T1 k) {
        Node<T1, T2> x = root, y = null;
        while (x != null) {
            int cmp = k.compareTo(x.key);
            if (cmp == 0) {
                break;
            } else {
                y = x;
                if (cmp < 0) {
                    x = x.left;
                } else {
                    x = x.right;
                }
            }
        }
        if (x == null) {
            return;
        }
        if (x.right == null) {
            if (y == null) {
                root = x.left;
            } else if (x == y.left) {
                y.left = x.left;
            } else {
                y.right = x.left;
            }
        } 
        else {
            Node<T1, T2> leftMost = x.right;
            y = null;
            while (leftMost.left != null) {
                y = leftMost;
                leftMost = leftMost.left;
            }
            if (y != null) {
                y.left = leftMost.right;
            } else {
                x.right = leftMost.right;
            }
            x.key = leftMost.key;
            x.value = leftMost.value;
        }
    }
}
