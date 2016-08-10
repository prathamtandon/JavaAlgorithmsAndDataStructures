/**
 * Created by prathamt on 8/9/16.
 */
public class ObjectsAsArguments {

    public static class Counter {
        private int counter;

        public Counter() {
            counter = 0;
        }

        public void increment() {
            counter += 1;
        }

        public int tally() {
            return counter;
        }

        @Override
        public String toString() {
            return String.format("Counter: %d", counter);
        }
    }

    private static void incrementCounter(Counter c) {
        c = new Counter();
        c.increment();
    }

    public static void main(String[] args) {
        Counter c = new Counter();
        incrementCounter(c);
        System.out.println(c);
    }
}
