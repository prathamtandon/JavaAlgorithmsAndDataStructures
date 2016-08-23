package Stacks;

import java.util.LinkedList;

/**
 * Created by prathamt on 8/22/16.
 */
public class AnimalQueue {
    LinkedList<Dog> dogs = new LinkedList<Dog>();
    LinkedList<Cat> cats = new LinkedList<Cat>();
    private int order = 0;  //  acts as timestamp

    public void enqueue(Animal a) {
        a.setOrder(order++);
        if(a instanceof Dog) dogs.addLast((Dog) a);
        else if(a instanceof Cat) cats.addLast((Cat) a);
    }

    public Animal dequeueAny() {
        if(dogs.size() == 0)
            return dequeueCats();
        else if(cats.size() == 0)
            return dequeueDogs();

        if(dogs.peek().isOlderThan(cats.peek()))
            return dequeueDogs();
        else
            return dequeueCats();
    }

    public Dog dequeueDogs(){
        return dogs.poll();
    }

    public Cat dequeueCats() {
        return cats.poll();
    }
}
