package com.solvd.laba.linkedlist;

public class GenericLinkedList<T> {
    Node<T> head;


    public void add(T data) {
        Node<T> newNode = new Node<T>(data);
        if (head == null) {
            head = newNode;
        } else {
            Node<T> n = head;
            while (n.next != null) {
                n = n.next;
            }
            n.next = newNode;
        }
    }

    public int size() {
        Node<T> currentNode = head;
        int size = 0;
        if (currentNode == null) {
            return size;
        } else {
            while (currentNode != null) {
                size++;
                currentNode = currentNode.next;
            }
        }
        return size;
    }

    public T get(int position) {
        Node<T> node = head;
        if (position == 0) {
            return head.data;
        } else if (position > 0 && position < size()) {
            for (int i = 0; i < position; i++) {
                node = node.next;
            }
            return node.data;
        } else {
            throw new IndexOutOfBoundsException("No CustomNode found in position");
        }
    }


    public void add(int index, T data) {
        Node<T> newNode = new Node<T>(data);
        if (index == 0) {
            newNode.next = head;
            head = newNode;
        } else {

            Node<T> currentNode = head;
            for (int i = 0; i < index - 1; i++) {
                currentNode = currentNode.next;
            }
            newNode.next = currentNode.next;
            currentNode.next = newNode;
        }
    }


    public void remove(int index) {
        if (index == 0) {
            head = head.next;
        } else {
            Node<T> currentNode = head;
            for (int i = 0; i < index - 1; i++) {
                currentNode = currentNode.next;
            }
        }
    }

}