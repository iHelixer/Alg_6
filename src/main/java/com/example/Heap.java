package com.example;

import com.example.Node;

import java.util.Arrays;

public class Heap<T extends Comparable<T>> {
    private Node<T>[] heapArray;
    private int maxSize;
    private int currentSize;

    @SuppressWarnings("unchecked")
    public Heap(int maxSize) {
        this.maxSize = maxSize;
        this.currentSize = 0;
        this.heapArray = new Node[maxSize];
    }

    public Node<T>[] getHeapArray() {
        return Arrays.copyOf(heapArray, currentSize);
    }

    public boolean insertNode(Node<T> newNode) {
        if (currentSize == maxSize) return false;
        heapArray[currentSize] = newNode;
        displaceUp(currentSize++);
        return true;
    }

    public Node<T> removeNode(int index) {
        if (index < 0 || index >= currentSize) return null;
        Node<T> root = heapArray[index];
        heapArray[index] = heapArray[--currentSize];
        displaceDown(index);
        heapArray = Arrays.copyOf(heapArray, heapArray.length);
        return root;
    }

    public boolean changeNode(int index, T newValue) {
        if (index < 0 || index >= currentSize) return false;
        T oldValue = heapArray[index].getElement();
        heapArray[index].setElement(newValue);
        if (oldValue.compareTo(newValue) < 0) {
            displaceUp(index);
        } else {
            displaceDown(index);
        }
        return true;
    }

    private void displaceUp(int index) {
        int parentIndex = (index - 1) / 2;
        Node<T> bottom = heapArray[index];
        while (index > 0 &&
                heapArray[parentIndex].getElement().compareTo(bottom.getElement()) < 0) {
            heapArray[index] = heapArray[parentIndex];
            index = parentIndex;
            parentIndex = (parentIndex - 1) / 2;
        }
        heapArray[index] = bottom;
    }

    private void displaceDown(int index) {
        Node<T> top = heapArray[index];
        while (index < currentSize / 2) {
            int leftChild = 2 * index + 1;
            int rightChild = leftChild + 1;
            int largerChild;

            if (rightChild < currentSize &&
                    heapArray[leftChild].getElement().compareTo(heapArray[rightChild].getElement()) < 0) {
                largerChild = rightChild;
            } else {
                largerChild = leftChild;
            }

            if (top.getElement().compareTo(heapArray[largerChild].getElement()) >= 0) break;

            heapArray[index] = heapArray[largerChild];
            index = largerChild;
        }
        heapArray[index] = top;
    }
}