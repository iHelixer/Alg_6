package com.example;

import java.util.Objects;

public class Node<T extends Comparable<T>> implements Comparable<Node<T>> {
    private T element;

    public Node(T element) {
        this.element = element;
    }

    public T getElement() {
        return element;
    }

    public void setElement(T element) {
        this.element = element;
    }

    @Override
    public int compareTo(Node<T> o) {
        return this.element.compareTo(o.getElement());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node<?> node = (Node<?>) o;
        return Objects.equals(element, node.element);
    }

    @Override
    public int hashCode() {
        return Objects.hash(element);
    }
}