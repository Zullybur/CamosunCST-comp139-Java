/**
 * Queue implementation using a linked list with sentinel nodes
 * Created by: Matthew Casiro
 * Created on: April 09 2016
 */
package lab05;

import java.nio.BufferUnderflowException;
import java.util.StringJoiner;

class Queue<T> {
    /**
     * QueueNode containing an element and a pointer to the next element.
     */
    class QueueNode<T> {

        T elem;
        QueueNode<T> next;
        /**
         * Construct a QueueNode with element <i>elem</i> pointing to <i>next</i>.
         * Pre: N/A
         * Post: N/A
         * @param elem is the element the node contains
         * @param next is a pointer to the next node in the list
         */
        QueueNode(T elem, QueueNode<T> next) {
            this.elem = elem;
            this.next = next;
        }
    }
    protected QueueNode<T> head, tail;
    /**
     * Construct a queue object
     * Pre: N/A
     * Post: N/A
     */
    public Queue() {
    }
    /**
     * Check if the Queue is empty.
     * Pre: N/A
     * Post: N/A
     * @return True if the queue is empty, and False otherwise
     */
    public boolean isEmpty() {
        return head == null;
    }
    /**
     * Add an element <i>elem</i> to the Queue.
     * Pre: N/A
     * Post: <i>elem</i> is added to the back of the queue
     * @param elem is the element to be added to the queue
     */
    public void enqueue(T elem) {
        if(isEmpty()) {
            head = tail = new QueueNode(elem, null);
            return;
        }
        tail.next = new QueueNode(elem, null);
        tail = tail.next;
    }
    /**
     * Remove an element from the Queue.
     * Pre: The queue is not empty
     * Post: The element at the front of the queue is removed
     * @throws BufferUnderflowException if the queue is empty
     */
    public void dequeue() throws BufferUnderflowException {
        if (isEmpty()) {
            throw new BufferUnderflowException();
        }
        head = head.next;
        if (isEmpty()) {
            tail = null;
        }
    }
    /**
     * Return the first element in the Queue.
     * Pre: The queue is not empty
     * Post: N/A
     * @return the element at the front of the queue
     * @throws BufferUnderflowException if the queue is empty
     */
    public T first() throws BufferUnderflowException {
        if (isEmpty()) {
            throw new BufferUnderflowException();
        }
        return head.elem;
    }
    /**
     * Build a string of Queue elements in first to last order.
     * Pre: N/A
     * Post: N/A
     * @return a comma separated list of queue elements
     */
    @Override
    public String toString() {
        StringJoiner str = new StringJoiner(",");
        QueueNode current = head;
        while (current != null) {
            str.add(current.elem.toString());
            current = current.next;
        }
        return str.toString();
    }
}
