/**
 * Queue implementation using a linked list with sentinel nodes
 * Created by: Matthew Casiro
 * Created on: April 09 2016
 */
package lab05;

import java.nio.BufferOverflowException;
import java.util.EmptyStackException;
import java.util.StringJoiner;

class Stack<T> {
    protected int top = -1;
    protected T[] stack;
    /**
     * Construct a stack object with <i>size</i> capacity.
     * Pre: N/A
     * Post: N/A
     * @param size is the number of elements the stack can hold
     * @throws IllegalArgumentException if size is less than 1
     */
    public Stack(int size) throws IllegalArgumentException {
        if (size < 1) {
            throw new IllegalArgumentException();
        }
        stack = (T[]) new Object[size];
    }
    /**
     * Check if the stack is empty.
     * Pre: N/A
     * Post: N/A
     * @return True if the stack is empty, otherwise False.
     */
    public boolean isEmpty() {
        return top < 0;
    }
    /**
     * Push <i>elem</i> on to the top of the stack.
     * Pre: The stack object is not full
     * Post: An element is added to the top of the stack
     * @param elem is the element to be added
     * @throws BufferOverflowException if pushing to a full stack
     */
    public void push(T elem) throws BufferOverflowException {
        if (top >= stack.length - 1) {
            throw new BufferOverflowException();
        }
        stack[++top] = elem;
    }
    /**
     * Pop the top element off of the stack.
     * Pre: The stack is not empty
     * Post: The top element of the stack is removed
     * @throws EmptyStackException 
     */
    public void pop() throws EmptyStackException {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        top--;
    }
    /**
     * Return the element at the top of the stack.
     * Pre: N/A
     * Post: N/A
     * @return the top element of the stack
     * @throws EmptyStackException if the stack is empty
     */
    public T top() throws EmptyStackException {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return stack[top];
    }
    /**
     * Build a string of stack elements in top down order.
     * Pre: N/A
     * Post: N/A
     * @return a comma separated string of stack elements
     */
    @Override
    public String toString() {
        StringJoiner str = new StringJoiner(",");
        for (int i = top; i >= 0; i--) {
            str.add(stack[i].toString());
        }
        return str.toString();
    }
}