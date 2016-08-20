package Stacks;

import Collections.Stack;

/**
 * Created by prathamt on 8/19/16.
 */
public class StackWithMin extends Stack<Integer> {
    Stack<Integer> s2;
    public StackWithMin() {
        s2 = new Stack<Integer>();
    }

    public void push(int val) {
        if(val <= min())
            s2.push(val);
        super.push(val);
    }

    public Integer pop() {
        int val = super.pop();
        if(val == min()) {
            s2.pop();
        }
        return val;
    }

    public Integer min() {
        if(s2.isEmpty())
            return Integer.MAX_VALUE;
        int t = s2.pop();
        s2.push(t);
        return t;
    }
}
