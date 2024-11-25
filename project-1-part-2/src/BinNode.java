/**********************************************************************
 * @file: BinNode.java
 * @description: Interface defining BST nodes and children
 * @author: Kate Choi
 * @date: 17 September 2024
 **********************************************************************/

interface BinNode<E> { // binary tree node ADT
    // get/set element value
    public E value();
    public void setValue(E v);

    // return children
    public BinNode<E> left();
    public BinNode<E> right();

    // check if leaf node
    public boolean isLeaf();
}
