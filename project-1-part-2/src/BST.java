/**********************************************************************
 * @file: BST.java
 * @description: Manages the structure of binary search trees (BST);
does not require the BST to understand the type of its element.
 * @author: Kate Choi
 * @date: 18 September 2024
 **********************************************************************/

import java.util.Iterator;
import java.util.Stack;

public class BST<E extends Comparable<E>> implements Iterable<E> {

    private Node<E> root;
    private int nodecount;

    public BST() {
        root = null;
        nodecount = 0;
    }

    public void clear() {
        root = null;
        nodecount = 0;
    }

    public int size() {
        return nodecount;
    }

    public void insert(E e) {
        root = inserthelp(root, e);
        nodecount++;
    }

    private Node<E> inserthelp(Node<E> rt, E e) {
        if (rt == null) {
            return new Node<>(e);
        }
        if (e.compareTo(rt.getElement()) < 0) {
            rt.setLeft(inserthelp(rt.getLeft(), e));
        } else if (e.compareTo(rt.getElement()) > 0) {
            rt.setRight(inserthelp(rt.getRight(), e));
        }
        return rt;
    }

    public Node<E> remove(E e) {
        root = removehelp(root, e);
        return root;
    }

    private Node<E> removehelp(Node<E> rt, E e) {
        if (rt == null) {
            return null;
        }
        if (e.compareTo(rt.getElement()) < 0) {
            rt.setLeft(removehelp(rt.getLeft(), e));
        } else if (e.compareTo(rt.getElement()) > 0) {
            rt.setRight(removehelp(rt.getRight(), e));
        } else {
            if (rt.getLeft() == null) {
                return rt.getRight();
            } else if (rt.getRight() == null) {
                return rt.getLeft();
            }
            rt.setElement(minVal(rt.getRight()));
            rt.setRight(removehelp(rt.getRight(), rt.getElement()));
        }
        return rt;
    }

    private E minVal(Node<E> rt) {
        E min = rt.getElement();
        while (rt.getLeft() != null) {
            min = rt.getLeft().getElement();
            rt = rt.getLeft();
        }
        return min;
    }

    public Node<E> search(E e) {
        return searchhelp(root, e);
    }

    private Node<E> searchhelp(Node<E> rt, E e) {
        if (rt == null || rt.getElement().equals(e)) {
            return rt;
        }
        if (e.compareTo(rt.getElement()) < 0) {
            return searchhelp(rt.getLeft(), e);
        } else {
            return searchhelp(rt.getRight(), e);
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new BSTIterator<>(root);
    }
}

class BSTIterator<E extends Comparable<E>> implements Iterator<E> {
    private Stack<Node<E>> stack = new Stack<>();

    public BSTIterator(Node<E> rt) {
        pushAll(rt);
    }

    private void pushAll(Node<E> n) {
        while (n != null) {
            stack.push(n);
            n = n.getLeft();
        }
    }

    @Override
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    @Override
    public E next() {
        Node<E> temp = stack.pop();
        pushAll(temp.getRight());
        return temp.getElement();
    }
}


