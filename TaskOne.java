package project_walmart;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

// Creating a class Heap to create a heap having 2^x children for each parent node.
class Heap {
    private final int child; 
    private final List<Integer> heap;

    // Constructor with the parameter child (x) in the given question assigning value to child as (2^x).
    public Heap(int child) {
        if (child <= 0) {
            throw new IllegalArgumentException("The number of child needs to be a natural number.");
        }
        this.child = (int)Math.pow(2, child);
        this.heap = new ArrayList<>();
    }

    // Method to find the index of the parent of a child having index i
    private int parent(int i) {
        return (i - 1) / child;
    }

    // Method to find the index of the kth child of the parent having index i
    private int kthChild(int i, int k) {
        if (k > child) {
            throw new IllegalArgumentException("The number is greater than the number of children each node has.");
        } else {
            return child * i + k;
        }
    }

    // Method to swap two elements in the heap
    private void swap(int i, int j) {
        int temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    // Heapify Method to create a max heap
    private void heapify(int i) {
        int current = i;
        while (current > 0 && heap.get(current) > heap.get(parent(current))) {
            swap(current, parent(current));
            current = parent(current);
        }
    }

    // Method which returns the root element without removing it
    public int peek() {
        if (heap.isEmpty()) {
            throw new IllegalStateException("Heap is empty");
        }
        return heap.get(0);
    }

    // Method to insert value in heap
    public void insert(int value) {
        heap.add(value);
        heapify(heap.size() - 1);
    }

    // Method which returns true if the heap is empty
    public boolean isEmpty() {
        return heap.isEmpty();
    }

    // Maintains the heap property by shifting the node at index i down
    private void heapifyDown(int i) {
        int current = i;
        while (true) {
            int largest = current;
            for (int k = 1; k <= child; k++) {
                int childIndex = kthChild(current, k);
                if (childIndex < heap.size() && heap.get(childIndex) > heap.get(largest)) {
                    largest = childIndex;
                }
            }
            if (largest == current) {
                break;
            }
            swap(current, largest);
            current = largest;
        }
    }

    // Method to pop max value
    public int popMax() {
        if (heap.isEmpty()) {
            throw new IllegalStateException("Heap is empty");
        }
        int maxValue = heap.get(0);
        int lastValue = heap.remove(heap.size() - 1);
        if (!heap.isEmpty()) {
            heap.set(0, lastValue);
            heapifyDown(0);
        }
        return maxValue;
    }
}

public class TaskOne {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of child : ");
        int child = sc.nextInt();
        Heap heap1 = new Heap(child);
        System.out.println("Enter the number of elements : ");
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            System.out.println("Enter the element : ");
            int element = sc.nextInt();
            heap1.insert(element);
        }
        System.out.println("The elements in sorted form are : ");
        for (int i = 0; i < n; i++) {
            System.out.println(heap1.popMax());
        }
    }
}
