/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab05;

import java.nio.BufferOverflowException;

/**
 *
 * @author MattCasiro
 */
public class Lab05 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("STACK TESTING");
        Stack s = new Stack(2);
        s.push(5);
        s.push(6);
        try {
            s.push(7);
        } catch (BufferOverflowException err) {
            System.out.println(err.toString());
        }
        System.out.println("QUEUE TESTING");
        Queue q = new Queue();
        
    }
    
}
