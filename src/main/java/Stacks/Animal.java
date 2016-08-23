package Stacks;

/**
 * Created by prathamt on 8/22/16.
 */
public abstract class Animal {
    private int order;
    protected String name;
    public Animal(String n) {
        name = n;
    }

    public void setOrder(int ord) {
        order = ord;
    }

    public int getOrder() {
        return order;
    }

    public boolean isOlderThan(Animal other) {
        return this.order < other.getOrder();
    }
}



