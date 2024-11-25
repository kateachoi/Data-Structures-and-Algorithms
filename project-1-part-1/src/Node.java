/**********************************************************************
 * @file: Node.java
 * @description: Class to represent nodes of the BST. Each node holds an element,
   references to both left and right subtrees with their accompanying get/set methods.
 * @author: Kate Choi
 * @date: 17 September 2024
 **********************************************************************/

class Node<E extends Comparable<E>> {
    private E element;
    private Node<E> left, right;

// Implement the constructor
    public Node() { left = right = null; }
    public Node(E v) { left = right = null; element = v; }
    public Node(E v, Node<E> l, Node<E> r) {
        element = v;
        left = l;
        right = r;
    }

// Implement the setElement method
    public void setElement(E v) { element = v; }

// Implement the setLeft method
    public void setLeft(Node<E> l) { left = l; }

// Implement the setRight method
    public void setRight(Node<E> r) {right = r; }

// Implement the getLeft method
    public Node<E> getLeft() { return left; }

// Implement the getRight method
    public Node<E> getRight() { return right; }

// Implement the getElement method
    public E getElement() { return element; }

// Implement the isLeaf method
    public boolean isLeaf() { return (left == null) && ( right == null); }

}
