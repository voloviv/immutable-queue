# Immutable Queue

Immutable Queue implementation that uses two opposite stacks to keep track of elements.

## Installation 

```bash
	
	mvn install
	
```

## Usage 

```java
	
	//Import
	import com.voloviv.ImmutableQueue;

	// Create new immutable queue
	Queue<T> queue = new ImmutableQueue<T>();
	
	// Enqueue element
	queue = queue.enQueue(t);
	
	// Dequeue element
	queue = queue.deQueue(t);
	
	// Get the first element in the queue
	T obj = queue.head();
	
	// Get the last element in the queue
	T obj = queue.tail();
	 
```
    
## Running tests 

```bash
	
	mvn test
	
```
