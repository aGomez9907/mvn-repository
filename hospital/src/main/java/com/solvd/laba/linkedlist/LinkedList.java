package com.solvd.laba.linkedlist;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class LinkedList<T> {
    private static final Logger LOGGER = LogManager.getLogger(LinkedList.class);

    class node<t> {
        T data;
        node<t> next;

        node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    class list<t> {
        node<t> head;
        private int length = 0;

        list() {
            this.head = null;
        }


        void addNode(T data) {

            node<t> temp = new node<>(data);
            if (this.head == null) {
                head = temp;
            } else {
                node<t> aux = head;
                while (aux.next != null) {
                    aux = aux.next;
                }
                aux.next = temp;
            }
            length++;
        }


        void addNode(int pos, T data) {
            if (pos > length + 1) {
                LOGGER.info("Position not found in the linked list");
                return;
            }
            if (pos == 1) {
                node<t> temp = head;
                head = new node<t>(data);
                head.next = temp;
                return;
            }
            node<t> temp = head;
            node<t> prev = new node<t>(null);
            while (pos - 1 > 0) {
                prev = temp;
                temp = temp.next;
                pos--;
            }
            prev.next = new node<t>(data);
            prev.next.next = temp;
        }


        void removeNode(T remove) {
            node<t> prev = new node<>(null);
            prev.next = head;
            node<t> next = head.next;
            node<t> temp = head;
            boolean exists = false;
            if (head.data == remove) {
                head = head.next;
                exists = true;
            }
            while (temp.next != null) {
                if (String.valueOf(temp.data).equals(String.valueOf(remove))) {
                    prev.next = next;
                    exists = true;
                    break;
                }
                prev = temp;
                temp = temp.next;
                next = temp.next;
            }
            if (!exists && String.valueOf(temp.data).equals(String.valueOf(remove))) {
                prev.next = null;
                exists = true;
            }
            if (exists) {
                length--;
            } else {
                LOGGER.info("Not found in the linked list");
            }
        }

        public String toString() {
            String S = "{";
            node<t> aux = head;
            if (aux == null)
                return S + "}";
            while (aux.next != null) {
                S += aux.data + "->";
                aux = aux.next;
            }
            S += String.valueOf(aux.data);
            return S + "}";
        }
    }

}  