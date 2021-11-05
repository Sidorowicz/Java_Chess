/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Paweł
 */

import java.util.Arrays;
import java.util.Scanner;

public class Chess {
    
    
    
    
    
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        board a= new board();
        a.print_board();
        boolean gg=true;
        int x,y,xx,yy;
        
        while(gg){
            System.out.println("Select a piece( x ): ");
            y = scan.nextInt();
            y--;
            System.out.println("Select a piece( y ): ");
            x = scan.nextInt();
            x--;
            
            System.out.println("Select a field( x ): ");
            yy = scan.nextInt();
            yy--;
            System.out.println("Select a field( y ): ");
            xx = scan.nextInt();
            xx--;
            
            a.move(x,y,xx,yy);
            a.print_board();
            
        }
        
        
    }
}

class piece {
    int x,y;
    int piece_type;// 1 pion, 2 koń, 3 goniec, 4 wieża, 5 królowa, 6 król
    int player;
    public piece(){this.player=0;} 
    public boolean checkmove(int x, int y, int xx, int yy){return false;};
    public String print_piece(){
    return " O";
    }
    public int return_player(){
        return player;
    }
    
}

class rook extends piece{//wierza
    public rook(int x){
        this.player=x;
        this.piece_type=4;
    }
    
    public boolean checkmove(int x, int y, int xx, int yy){//dostaje współrzedne 2 miejsc i sprawda czy moze po nich sie poruszać
        if(x==xx || y==yy){
            return true;
        }else{
            return false;
        }
    }
    public String print_piece(){
    return "R" + String.valueOf(player);
    }
}

class bishop{//goniec
}

class king{//król
}

class queen{//królowa
}

class knight{//skoczek
}

class pawn{//poin
}


class board {
    //int[][] visible_board= new int[8][8];
    
    rook rook1 = new rook(1);
    rook rook2 = new rook(2);
    piece empty = new piece();
    piece[][] visible_board={
        {rook1,rook1,rook1,rook1,rook1,rook1,rook1,rook1},
        {empty,rook1,rook1,rook1,rook1,rook1,rook1,rook1},
        {rook1,empty,empty,empty,empty,empty,empty,empty},
        {empty,empty,empty,empty,empty,empty,empty,empty},
        {empty,empty,empty,empty,empty,empty,empty,empty},
        {empty,empty,empty,empty,empty,empty,empty,empty},
        {rook2,rook2,rook2,rook2,rook2,rook2,rook2,rook2},
        {rook2,rook2,rook2,rook2,rook2,rook2,rook2,rook2}
    };
   // int[][] move_board= new int[8][8];
    piece[][] hidden_board= new rook[8][8];
    
    void copy_to_hidden_board(){
        for (int i = 0; i < visible_board.length; i++) {
            for (int j = 0; j < visible_board[i].length; j++) {
                hidden_board[i][j]=visible_board[i][j];
            }
        System.out.println();
        }
    }
    
    void set_field(int a, int b, piece c){
        visible_board[a][b]=c;
    }
    
    String get_field(int a, int b){
       return visible_board[a][b].print_piece();
    }

    void print_board(){
        System.out.println("yx1  2  3  4  5  6  7  8");
        for (int i = 0; i < visible_board.length; i++) {
            System.out.print((i+1)+ " ");
            for (int j = 0; j < visible_board[i].length; j++) {
                System.out.print(visible_board[i][j].print_piece() + " ");
            }
        System.out.println();
        }
    }
    void move(int x, int y, int xx, int yy){
        int vx=0, vy=0;
        
        if(y==yy){
            if(x>xx){vx=-1;}
            if(x<xx){vx=1;}
            for(int i=x;i!=xx;i+=vx){
                if(visible_board[i+vx][y].return_player() == visible_board[i][y].return_player()){
                    visible_board[i+vx][y]=visible_board[x][y];
                    visible_board[i][y]= new piece();
                    System.out.print(" 1");
                    break;
                }else
                if(visible_board[i+vx][y].return_player() != visible_board[i][y].return_player() && visible_board[i+vx][y].return_player()!=0){
                    visible_board[i+vx][y]=visible_board[x][y];
                    visible_board[i][y]= new piece();
                    System.out.print(" 2");
                }else
                if(visible_board[i+vx][y].return_player()==0){
                    //nie rób nic
                    System.out.print(" 3");
                }
                if(i==xx){
                    visible_board[xx][yy]=visible_board[x][y];
                    visible_board[x][y]= new piece();
                    System.out.print(" 4");
                    break;
                }
            }
        }
        
        
        
        if(x==xx){
            if(y>yy){vy=-1;}
            if(y<yy){vy=1;}
            for(int j=y;j!=yy;j+=vy){
                if(visible_board[x][j+vy].return_player() == visible_board[x][j].return_player()){
                    if(visible_board[x][j+vy]!=visible_board[x][j]){
                    visible_board[x][j+vy]=visible_board[x][y];
                    visible_board[x][j]= new piece();
                    break;
                    }
                }else
                if(visible_board[x][j+vy].return_player() != visible_board[x][j].return_player() && visible_board[x][j+vy].return_player()!=0){
                    if(visible_board[x][j+vy]!=visible_board[x][j]){
                    visible_board[x][j+vy]=visible_board[x][y];
                    visible_board[x][j]= new piece();
                    }
                }else
                if(visible_board[x][j+vy].return_player()==0){
                    //nie rób nic
                }
                if(j==yy){
                    visible_board[xx][yy]=visible_board[x][y];
                    visible_board[x][y]= new piece();
                    break;
                }
            }
        }

        
        
        /* sprawdza czy figura porusza się tak jak powinna ale bez detekcji kolizji
        if(visible_board[x][y].checkmove(x,y,xx,yy)){
            //ostatecznie wykonaj ruch
            
            visible_board[xx][yy]=visible_board[x][y];
            visible_board[x][y]= new piece();
        }
        */
    }
}
