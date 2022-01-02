package battleships.java;

import java.util.Random;
import java.util.Scanner;

public class BattleshipsJava {
    private final int O = 10; //Board size
    private final int S= 2;  //number of submarines
    private final int D = 2;  //number of destroyers
    private final int T = 10; //number of torpedos
    
    public int getO(){
        return O;
    }
    
    public static void ShowInventory(Inventory i){
        System.out.println("Torpedos left: "+i.getT());
        System.out.println("Destroyers parts left: "+i.getD());
        System.out.println("Submarines left: "+i.getS());
    }
    
    public static void DestroyersIntoOcean(Ocean ta){
        Random r = new Random();
        BattleshipsJava O = new BattleshipsJava();
        int s=0,i,j;
        r.setSeed(System.currentTimeMillis());
        do{
               
            if ((r.nextInt()%2)==1){
                i=Math.abs(r.nextInt()%(O.O-1));
                j=Math.abs(r.nextInt()%(O.O));
                while ((ta.getOcean(i,j)==1)
                        ||(ta.getOcean(i+1,j)==1)
                        ||(ta.getOcean(i,j)==2)
                        ||(ta.getOcean(i+1,j)==2)){
                    i=Math.abs(r.nextInt()%(O.O-1));
                    j=Math.abs(r.nextInt()%(O.O));
                }
                ta.setOcean(i,j,2);
                ta.setOcean(i+1,j,2);
            }else{
                i=Math.abs(r.nextInt()%(O.O));
                j=Math.abs(r.nextInt()%(O.O-1));
                while ((ta.getOcean(i,j)==1)
                        ||(ta.getOcean(i,j+1)==1)
                        ||(ta.getOcean(i,j)==2)
                        ||(ta.getOcean(i,j+1)==2)){
                    i=Math.abs(r.nextInt()%(O.O));
                    j=Math.abs(r.nextInt()%(O.O-1));
                }
                ta.setOcean(i,j,2);
                ta.setOcean(i,j+1,2);
            }
            
            s++;
        }while(s<O.D);
    }
    
    public static void SubmarinesIntoOcean(Ocean map){
        Random r = new Random();
        BattleshipsJava O = new BattleshipsJava();
        int s=0,i,j;
        do{
            r.setSeed(System.currentTimeMillis());
            i=Math.abs(r.nextInt()%(O.O-1));
            j=Math.abs(r.nextInt()%(O.O));
            while ((map.getOcean(i,j)==1)
                    ||(map.getOcean(i,j)==2)){
                i=Math.abs(r.nextInt()%(O.O-1));
                j=Math.abs(r.nextInt()%(O.O));
            }
            map.setOcean(i,j,1);
            s++;
        }while(s<O.S);
        
    }
    
    public static void ShowOcean(Ocean m){
        int i,j;
        BattleshipsJava O = new BattleshipsJava();
        System.out.print("    ");
        for(j=0;j<O.O;j++)
            System.out.print(" "+j+1+" ");
        System.out.print("\n");
        for(i=0;i<O.O;i++){
            System.out.print("    ");
            for(j=0;j<O.O;j++)    
                System.out.print("+---");
            System.out.print("+");
            System.out.print("\n");
            System.out.print((char)(65+i)+"   ");
            for(j=0;j<O.O;j++)
            {
                if((m.getTor(i,j)==1)&&((m.getOcean(i, j)==1)||m.getOcean(i,j)==2))
                    System.out.print("| * ");
                else if((m.getTor(i, j)==1)&&(m.getOcean(i, j)==0))
                    System.out.print("| 0 ");
                else
                    System.out.print("|   ");
            }
            System.out.print("|");
            System.out.print("\n");
        }
        System.out.print("    ");
        for(j=0;j<O.O;j++)
        {
            System.out.print("+---");
        }
        System.out.print("+\n");
        
    }
    
    public static void main(String[] args) {
        BattleshipsJava start = new BattleshipsJava();
        int i,j,k;
        String MD;              //Game mode
        Ocean map = new Ocean();
	Inventory inv = new Inventory();
	int x;                //keeps the horizontal coordinate of the torpedo.
	String y;             //keeps the vertical coordinate of the torpedo
	int yint;             //converts the vertical coordinate into number
        Scanner scn = new Scanner(System.in);
        inv.setD(2*start.D);
        inv.setS(start.S);
        inv.setT(start.T);
        do {
        System.out.println("Type which game mode you want:");
        System.out.println("[1]Normal");
        System.out.println("[2]Cheats On");
        MD = scn.nextLine();
        }while ((!(MD.equals("1")))&&(!(MD.equals("2"))));
        for(i=0;i<start.O;i++){        //puts 0 in every location of ocean
            for(j=0;j<start.O;j++){
                map.setOcean(i,j,0);
            }
        }
        for(i=0;i<start.O;i++){        //puts 0 in every location of tor
            for(j=0;j<start.O;j++){
                map.setTor(i, j, 0);
            }
        }
        DestroyersIntoOcean(map);
        SubmarinesIntoOcean(map);
        if (MD.equals("1")){
            System.out.println("Normal Mode selected");
        }else{
            System.out.println("Cheats mode on");
            System.out.println("0 is empty ocean");
            System.out.println("1 is submarine");
            System.out.println("2 is destroyer");
            System.out.print(" ");
            for (i=0;i<start.O;i++)
                System.out.print(" "+(i+1)+" ");
            System.out.println("\n");
            for(i=0;i<start.O;i++){
                System.out.print((char)(65+i)+" ");
                for(j=0;j<start.O;j++){
                    System.out.print(map.getOcean(i, j)+"  ");
                }
                System.out.println("\n");
            }
        }
        ShowInventory(inv);
        while (inv.getT()>0){
            do{
            System.out.print("Type the coordinate: ");
            y=scn.nextLine();
            k=1;
            if (y.length()<=1||y.length()>=4){
                System.out.println("format of coordinate must be: \"upper case letter\"\"number\"\nexample: X10");
                k=0;
            }else if(y.length()==2){ 
                    if (((int)(y.charAt(0)))<65
                        ||((int)(y.charAt(0))>(65+start.O))
                        ||((int)(y.charAt(1))<49)
                        ||((int)(y.charAt(1))>57)){
                System.out.println("format of coordinate must be: \"upper case letter\"\"number\"\nexample: X10");
                k=0;
            }
            }else if(y.length()==3){
                if(((int)(y.charAt(0)))<65
                        ||((int)(y.charAt(0))>(65+start.O))
                        ||((int)(y.charAt(1))<49)
                        ||((int)(y.charAt(1))>57)
                        ||((int)(y.charAt(2))<48)
                        ||((int)(y.charAt(2))>57)){
                    System.out.println("format of coordinate must be: \"upper case letter\"\"number\"\nexample: X10");
                    k=0;
                }
            }}while(k==0);
            if (y.length()==2){
            yint = ((int)y.charAt(0)) - 65;
            x = ((int)y.charAt(1))-48;
            map.setTor(yint,x-1,1);
            }else{
                yint = ((int)y.charAt(0)) - 65;
                x = (((int)y.charAt(1))-48)*10+(((int)y.charAt(2))-48);
                map.setTor(yint,x-1,1);
            }
            ShowOcean(map);
            if(map.getOcean(yint, x-1)==1){
                System.out.println("You Hit a Submarine!");
                inv.reduceS();
                inv.reduceT();
            }else if (map.getOcean(yint, x-1)==2){
                System.out.println("You Hit a Destroyer");
                inv.reduceD();
                inv.reduceT();
            }else{
                System.out.println("Torpedo Lost");
                inv.reduceT();
            }
            ShowInventory(inv);
            if ((inv.getD()==0)&&(inv.getS()==0)){
                System.out.println("CONGRATS, YOU WIN!!!");
                inv.setT(0);
            }else if(inv.getT()==0){
                System.out.println("CONGRATS, YOU LOSE!!!");
            }       
            
        }
    }
    
}
