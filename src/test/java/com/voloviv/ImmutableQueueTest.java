package com.voloviv;

import java.util.ArrayList;
import java.util.Arrays;
import org.junit.Test;
import static junit.framework.TestCase.assertTrue;

import com.voloviv.ImmutableQueue;

/**
 * Unit tests for ImmutableQueue
 * 
 */
public class ImmutableQueueTest 
{
	private final ArrayList<Integer> data = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5));
	
	@Test
    public void enQueueShoudlReturnNewQueueInstanceWithNewElementAdded(){
		
		Queue<Integer> queue = new ImmutableQueue<Integer>();
    	for (Integer i : data){
    		Queue<Integer> old_queue = queue;
    		queue = queue.enQueue(i);
    		assertTrue(old_queue != queue);
    		assertTrue(queue.tail() == i);
    	};
    }
    
    @Test
    public void deQueueShouldReturnANewQueueInstanceWithTopElementRemoved(){
    	
    	Queue<Integer> queue = new ImmutableQueue<Integer>();
    	for (Integer i : data){
    		queue = queue.enQueue(i);
    	};
    	
    	Queue<Integer> old_queue = queue;
    	queue = queue.deQueue();
    	assertTrue(old_queue != queue);
    	assertTrue(queue.head() == data.get(1));
    	
    }
    
    @Test
    public void getHeadShouldReturnTheFirstElementInQueue(){
		
		Queue<Integer> queue = new ImmutableQueue<Integer>();
    	for (Integer i : data){
    		queue = queue.enQueue(i);
    	};
    	
    	assertTrue(queue.head() == data.get(1));
    }
    
    @Test
    public void getTailShouldReturnTheLastElementInQueue(){
		
		Queue<Integer> queue = new ImmutableQueue<Integer>();
    	for (Integer i : data){
    		queue = queue.enQueue(i);
    	};
    	
    	assertTrue(queue.tail() == data.get(data.size()-1));
    }
}
