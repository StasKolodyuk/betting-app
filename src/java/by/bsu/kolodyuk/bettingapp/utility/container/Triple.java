
package by.bsu.kolodyuk.bettingapp.utility.container;


public class Triple<T, Q, R> {
    
    private T first;
    private Q second;
    private R third;

    public Triple() {
    }

    public Triple(T first, Q second, R third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }

    public T getFirst() {
        return first;
    }

    public void setFirst(T first) {
        this.first = first;
    }

    public Q getSecond() {
        return second;
    }

    public void setSecond(Q second) {
        this.second = second;
    }

    public R getThird() {
        return third;
    }

    public void setThird(R third) {
        this.third = third;
    }
    
}
