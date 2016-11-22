package com.voloviv;

import java.util.NoSuchElementException;


/**
 * Immutable Queue Implementation
 *
 */
public class ImmutableQueue<T> implements Queue<T>
{
	private static class ImmutableStack<T>{
		
		private T head;
		private ImmutableStack<T> tail;
		private int size;
		
		/**
		 * Default constructor
		 * head = null; tail = null and size = null
		 * 
		 * */
		private ImmutableStack(){
			this.head = null;
			this.tail = null;
			this.size = 0;
		}
		
		/**
		 * Constructor Overloading
		 * T Object
		 * ImmutableStack tail
		 * 
		 * */
		
		private ImmutableStack(T obj,ImmutableStack<T> tail){
			this.head = obj;
			this.tail = tail;
			this.size = tail.size+1;
		}
		
		/**
		 * Return empty stack
		 * 
		 * */
		
		public static ImmutableStack empty(){
			return new ImmutableStack();
		}
		
		/**
		 * Checking if stack is empty
		 * with their size
		 * 
		 * @return true of false if Stack is empty or not
		 * */
		
		public boolean isEmpty(){
			return this.size ==0;
		}
		
		/**
		 * Push into the stack 
		 * 
		 * @param E object
		 * @return ImmutableStack with object
		 * */
		
		public ImmutableStack<T> push(T obj){
			return new ImmutableStack<T>(obj,this);
		}
		/**
		 * Take stack object and push the head of the tail stack
		 * to the stack
		 * do this until the stack is empty
		 * 
		 * @return reverse stack
		 * */

		public ImmutableStack<T> reverseStack(){
			ImmutableStack<T> stack = new ImmutableStack<T>();
			ImmutableStack<T> tail = this;
			while(!tail.isEmpty()){
				stack = stack.push(tail.head);
				tail = tail.tail;
			}
			return stack;
		}
	}
	
	private ImmutableStack<T> forward;
	private ImmutableStack<T> reverse;
	
	/**
	 * Constructor
	 * Return 
	 * 
	 * 
	 * */
	public ImmutableQueue(){
		this.forward = ImmutableStack.empty();
		this.reverse = ImmutableStack.empty();
	}
	
	/**
	 * Constructor overloading 
	 * Using two immutable stack order and reverse
	 * 
	 * 
	 * */
	
	public ImmutableQueue(ImmutableStack<T> forward,ImmutableStack<T> reverse){
		this.forward = forward;
		this.reverse = reverse;
	}
	
	/**
	 * Enqueue Object
	 * if object is null throw IllegalArgumentException
	 * 
	 * @return ImmutableQueue with object 
	 * */
	
	
	public Queue<T> enQueue(T object){
		if(object==null) throw new IllegalArgumentException();
		return new ImmutableQueue<T>(this.forward.push(object),this.reverse);
	}
	
	/**
	 * Balancing the Queue
	 * reverse the order stack and assign it to reverse stack
	 * and make order stack empty 
	 * 
	 * */
	
	private void balanceQueue(){
		this.reverse= this.forward.reverseStack();
		this.forward = ImmutableStack.empty();
	}

	/**
	 * Dequeue from the queue
	 * if Queue is empty then throw NoSuchElementException
	 * 
	 * if Reverse Stack is not empty then return Immutable queue with 
	 * reverse stack's tail object
	 * 
	 * else reverse the Order ImmutableStack and take the tail of this
	 * and clean the order ImmutableStack 
	 * 
	 * @return Queue
	 * */
	
	public Queue<T> deQueue(){
		if(this.isEmpty())
			throw new NoSuchElementException();
		if(!this.reverse.isEmpty()){
			return new ImmutableQueue<T>(this.forward,this.reverse.tail);
		}else{
			return new ImmutableQueue<T>(ImmutableStack.empty(),this.forward.reverseStack().tail);
		}		
	}
	
    public T head(){
    	if (this.isEmpty())
			throw new NoSuchElementException();
		if (this.reverse.isEmpty())
			balanceQueue();
		return this.reverse.head;
    };
    
    public T tail(){
    	if (this.isEmpty())
			throw new NoSuchElementException();
		if (this.forward.isEmpty()){
			return this.reverse.reverseStack().head;
		} else {
			return this.forward.head;
		}
    }
    
    public boolean isEmpty(){
    	return this.forward.size + this.reverse.size == 0;
    };
    
    public static void main(String [] args){
    	Queue<Integer> data = new ImmutableQueue<Integer>();
		int num =0;
		int max = 1000000;
		long current = System.currentTimeMillis();
		
		while(++num <= max){
			int value = (int)(Math.random()*100);
			data = data.enQueue(value);
		}	
		System.out.println("Time for enqueue "+max+" randome integer "+(System.currentTimeMillis()-current)+" miliseconds");
		
		while(--num>100){
			data =data.deQueue();
		}
		
		while(++num <= 120){
			int value = (int)(Math.random()*100);
			data = data.enQueue(value);
		}
		
		while(--num>100){
			data =data.deQueue();
		}
		
		System.out.println("Time for dequeue "+max+" randome integer "+(System.currentTimeMillis()-current)+" miliseconds");
    }
    
}
