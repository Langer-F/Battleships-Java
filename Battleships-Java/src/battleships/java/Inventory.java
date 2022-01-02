
package battleships.java;


public class Inventory {
    private int S=0;
    private int D=0;
    private int T=0;
    public void setS(int i){
        S=i;
    }
    public void setD(int i){
        D=i;
    }
    public void setT(int i){
        T=i;
    }
    public int getS(){
        return S;
    }
    public int getD(){
        return D;
    }
    public int getT(){
        return T;
    }
    public void reduceT(){
        T--;
    }
    public void reduceD(){
        D--;
    }
    public void reduceS(){
        S--;
    }
}
