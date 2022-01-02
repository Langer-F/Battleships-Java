
package battleships.java;

public class Ocean {
    BattleshipsJava O = new BattleshipsJava();
    private int[][] ocean = new int[O.getO()][O.getO()];
    private int[][] tor =new int[O.getO()][O.getO()] ;
    
    public int getOcean(int i,int j){
        return ocean[i][j];
    }
    public int getTor(int i,int j){
        return tor[i][j];
    }
    public void setOcean(int i, int j,int k){
        ocean[i][j]=k;
    }
    public void setTor(int i, int j,int k){
        tor[i][j]=k;
    }
}
