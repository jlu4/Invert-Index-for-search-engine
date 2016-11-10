/*
 * Heap[0]是无效的
 */
package Invert_Index;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.sound.sampled.ReverbType;

public class MinHeap4 {
	protected ArrayList<Tuples> Heap;
	private int size;
	private int maxsize;
	private static final int FRONT = 0;

	public int getSize() {
		return size;
	}

	public int getMaxsize() {
		return maxsize;
	}

	public MinHeap4(int maxsize) {
		this.maxsize = maxsize;
		this.size = 0;
		Heap = new ArrayList<Tuples>();
		Heap.add(new Tuples(" ", Integer.MIN_VALUE, Integer.MIN_VALUE));
	}

	private int parent(int pos) {
		return pos / 2;
	}

	private int leftChild(int pos) {
		return (2 * pos);
	}

	private int rightChild(int pos) {
		return (2 * pos) + 1;
	}

	private boolean isLeaf(int pos) {
		if (pos >= (size / 2) && pos <= size) {
			return true;
		}
		return false;
	}

	private void swap(int fpos, int spos) {
		Tuples tmp;
		tmp = Heap.get(fpos);
		Heap.set(fpos, Heap.get(spos));
		Heap.set(spos, tmp);
	}

	public void insert(Tuples tuples) {
		Heap.add(tuples);
	}

	public void print() {
		for (int i = 1; i <= size / 2; i++) {
			System.out.print(" PARENT : " + Heap.get(i) + " LEFT CHILD : "
					+ Heap.get(2 * i) + " RIGHT CHILD :" + Heap.get(2 * i + 1));
			System.out.println();
		}
	}

	public Tuples remove() {
		Tuples popped = Heap.remove(FRONT);
		return popped;
	}
	
	public void sort()
	{
		Collections.sort(Heap);
	}
	
}