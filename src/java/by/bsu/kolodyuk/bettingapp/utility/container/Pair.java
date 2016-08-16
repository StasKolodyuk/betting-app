
package by.bsu.kolodyuk.bettingapp.utility.container;


public class Pair<T, Q> {
    
    private T first;
    private Q second;

    public Pair() {
        
    }
    
    public Pair(T first, Q second) {
        this.first = first;
        this.second = second;
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
    
}
