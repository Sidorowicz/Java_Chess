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
import java.lang.Math;
import java.io.*;
public class Chess {
    
    
    
    
    
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        board a= new board();
        a.print_board();
        boolean gg=true;
        boolean p1turn=true;
        int x,y,xx,yy;
        int current_player=1;
        
        while(gg){ 
    
    
    
            System.out.println("Select a piece( x ): ");
            x = scan.nextInt();
            x--;
            System.out.println("Select a piece( y ): ");
            y = scan.nextInt();
            y--;
            
            if(a.get_field_player(x,y)!=current_player){
                continue;
            }

            
            
            System.out.println("Select a field( x ): ");
            xx = scan.nextInt();
            xx--;
            System.out.println("Select a field( y ): ");
            yy = scan.nextInt();
            yy--;
            
            
            if(current_player==1){
                current_player=2;
            }else{
                current_player=1;
            }
            
            a.move(x,y,xx,yy);
            a.print_board();
            a.check_mat(current_player);
        }
    }
}

class piece{
    int x,y;
    int piece_type;// 1 pion, 2 koń, 3 goniec, 4 wieża, 5 królowa, 6 król
    int player;
    public piece(){this.player=0;} 
    public void drawmove(int x, int y){};
    public String print_piece(){return " O";}
    public String print_move(){return " X";}
    public int return_player(){return player;}
    public int return_piece(){return piece_type;}
}

class rook extends piece{//wierza
    public rook(int x){this.player=x;this.piece_type=4;}
    public String print_piece(){return "R" + String.valueOf(player);}
}

class bishop extends piece{//goniec
    public bishop(int x){this.player=x;this.piece_type=3;}
    public String print_piece(){return "B" + String.valueOf(player);}
}

class king extends piece{//król
    public king(int x){this.player=x;this.piece_type=6;}
    public String print_piece(){return "K" + String.valueOf(player);}
}

class queen extends piece{//królowa
    public queen(int x){this.player=x;this.piece_type=5;}
    public String print_piece(){return "Q" + String.valueOf(player);}
}

class knight extends piece{//skoczek
    public knight(int x){this.player=x;this.piece_type=2;}
    public String print_piece(){return "S" + String.valueOf(player);}
}

class pawn extends piece{//poin
    public pawn(int x){this.player=x;this.piece_type=1;}
    public String print_piece(){return "P" + String.valueOf(player);}
}
class temp extends piece{//poin
    public temp(int x){this.player=x;this.piece_type=99;}
    public String print_piece(){return "O" + String.valueOf(player);}
    int xp,yp;
    public int pawnpositionx(){return x;}
    public int pawnpositiony(){return y;}
    public void setpawn(int x,int y){
    xp=x;
    yp=y;
    }
}

class board {
    int temp1_time=0,temp2_time=0;
    //int[][] visible_board= new int[8][8];
    //Player 1 figures
    temp temp1 = new temp(1);
    pawn pawn1 = new pawn(1);
    rook rook1 = new rook(1);
    knight knight1 = new knight(1);
    bishop bishop1 = new bishop(1);
    queen queen1 = new queen(1);
    king king1 = new king(1);

    //Player 2 figures
    temp temp2 = new temp(2);
    pawn pawn2 = new pawn(2);
    rook rook2 = new rook(2);
    knight knight2 = new knight(2);
    bishop bishop2 = new bishop(2);
    queen queen2 = new queen(2);
    king king2 = new king(2);
    
    piece empty = new piece();
    piece[][] visible_board={
        {rook1,knight1,bishop1,queen1,king1,bishop1,knight1,rook1},
        {pawn1,pawn1,pawn1,pawn1,pawn1,pawn1,pawn1,pawn1},
        {empty,empty,empty,empty,empty,empty,empty,empty},
        {empty,empty,empty,empty,empty,empty,empty,king2},
        {empty,empty,empty,empty,empty,empty,empty,empty},
        {empty,empty,empty,empty,empty,empty,empty,empty},
        {pawn2,pawn2,pawn2,pawn2,pawn2,pawn2,pawn2,pawn2},
        {rook2,knight2,bishop2,king2,queen2,bishop2,knight2,rook2}
    };
    
   
    
    void set_field(int a, int b, piece c){
        visible_board[a][b]=c;
    }
    
    String get_field(int a, int b){
       return visible_board[a][b].print_piece();
    }
    
    int get_field_player(int a, int b){
       return visible_board[a][b].return_player();
    }
    
    int king_x(int player){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
               if(visible_board[i][j].return_player()==2 && visible_board[i][j].return_piece()==6){
                   return i;
               }
            }
        }
        return -1;
    }
    
    int king_y(int player){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
               if(visible_board[i][j].return_player()==2 && visible_board[i][j].return_piece()==6){
                   return j;
               }
            }
        }
        return -1;
    }
    
    /*
   
    */

    void print_board(){
        System.out.println("xy1  2  3  4  5  6  7  8");
        for (int i = 0; i < visible_board.length; i++) {
            System.out.print((i+1)+ " ");
            for (int j = 0; j < visible_board[i].length; j++) {
                System.out.print(visible_board[i][j].print_piece() + " ");
            }
        System.out.println();
        }
    }
    
    
    
    
    
    int[][] move_board=new int[8][8];
    /*
    void copy_to_move_board(){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
               move_board[i][j] = visible_board[i][j].return_piece();
            }
        }
        print_move_board();
    }
    
    void print_move_board(){   
        for (int i = 0; i < move_board.length; i++) {
            for (int j = 0; j < move_board[i].length; j++) {
                System.out.print(move_board[i][j]+" ");
            }
        System.out.println();
        }
    }
*/
    
    void check_mat(int player){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
               if(visible_board[i][j].return_piece()!=0 && visible_board[i][j].return_piece()!=99){
                   if(check_all_pieces(i,j,king_x(player),king_y(player))==true){
                   System.out.println("Szach");
                   }
               }
            }
        }  
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    void move(int x, int y, int xx, int yy){
        
        
        int end_piece = visible_board[xx][yy].return_piece();
        int vx=0, vy=0;
        
        //usuń możliwośc bicie w przelocie
        if(temp1_time>0){
            temp1_time--;
            if(temp1_time==1){
                for (int i = 0; i < visible_board.length; i++) {
                    for (int j = 0; j < visible_board[i].length; j++) {
                        if(visible_board[i][j].return_piece()==99 && visible_board[i][j].return_player()==1){
                            visible_board[i][j]=empty;
                        }
                    }
                }
            }
        }
        if(temp2_time>0){
            temp2_time--;
            if(temp2_time==1){
                for (int i = 0; i < visible_board.length; i++) {
                    for (int j = 0; j < visible_board[i].length; j++) {
                        if(visible_board[i][j].return_piece()==99 && visible_board[i][j].return_player()==2){
                            visible_board[i][j]=empty;
                        }
                    }
                }
            }
        }

        
        
        // 1 pion, 2 koń, 3 goniec, 4 wieża, 5 królowa, 6 król
        if(visible_board[x][y].return_piece()==1){
                if(visible_board[x][y].return_player()==2){
                    if(y==yy){//ruch
                        if(xx==x-1 && visible_board[xx][yy].return_player()==0){
                            visible_board[xx][yy]=visible_board[x][y];
                            visible_board[x][y]= new piece();
                            if(xx==0){
                                visible_board[xx][yy]=new rook(2);
                                visible_board[x][y]= new piece();
                            }
                            //System.out.print("ruch do przodu p2");
                         
                        }else
                        if(xx==x-2 && visible_board[xx][yy].return_player()==0 && x==6){
                            visible_board[xx][yy]=visible_board[x][y];
                            visible_board[x-1][y]= temp2;
                            visible_board[x][y]= empty;
                            temp2_time=2;
                            temp2.setpawn(x, y);
                            //System.out.print("ruch do przodu p2");
                          
                        }
                    }else 
                    if((yy==y+1 && visible_board[x-1][y+1].return_player()==1) || (yy==y-1 && visible_board[x-1][y-1].return_player()==1)){
                        if(xx==x-1){
                            visible_board[xx][yy]=visible_board[x][y];
                            visible_board[x][y]= new piece();
                            if(xx==0){
                                visible_board[xx][yy]=new queen(2);
                                visible_board[x][y]= new piece();
                            }
                            //System.out.print("bicie p2 ");
                           
                        }
                    }
                }
                if(visible_board[x][y].return_player()==1){
                    if(y==yy){
                        if(xx==x+1 && visible_board[xx][yy].return_player()==0){
                            visible_board[xx][yy]=visible_board[x][y];
                            visible_board[x][y]= new piece();
                            if(xx==7){
                                visible_board[xx][yy]=new queen(1);
                                visible_board[x][y]= new piece();
                            }
                            //System.out.print("ruch do przodu p1");
                          
                        }else
                        if(xx==x+2 && visible_board[xx][yy].return_player()==0 && x==1){
                            visible_board[xx][yy]=visible_board[x][y];
                            visible_board[x+1][y]= temp1;
                            visible_board[x][y]= empty;
                            temp1_time=2;
                            temp1.setpawn(x, y);
                            //System.out.print("ruch do przodu p1");
                         
                        }
                    }else 
                     if((yy==y+1 && visible_board[x+1][y+1].return_player()==2) || (yy==y-1 && visible_board[x+1][y-1].return_player()==2)){
                        if(xx==x+1){
                            visible_board[xx][yy]=visible_board[x][y];
                            visible_board[x][y]= new piece();
                            if(xx==7){
                                visible_board[xx][yy]=new rook(1);
                                visible_board[x][y]= new piece();
                            }
                            //System.out.print("bicie p1");
                          
                        }
                    } 
                }   
        }
        if(visible_board[x][y].return_piece()==2){
            
            if( (Math.abs(x-xx)==2 && Math.abs(y-yy)==1) || (Math.abs(x-xx)==1 && Math.abs(y-yy)==2) ){
                if(visible_board[x][y].return_player() != visible_board[xx][yy].return_player() && visible_board[xx][yy].return_player()==0){
                    visible_board[xx][yy]=visible_board[x][y];
                    visible_board[x][y]= new piece();
                  
                }
                if(visible_board[x][y].return_player() != visible_board[xx][yy].return_player() && visible_board[xx][yy].return_player()!=0){
                    visible_board[xx][yy]=visible_board[x][y];
                    visible_board[x][y]= new piece();
                    
                }
            }
        }
        if(visible_board[x][y].return_piece()==3){
            if(x>=xx){vx=-1;}
            if(x<xx){vx=1;}
            if(y>=yy){vy=-1;}
            if(y<yy){vy=1;}
            int i =x+vx;
            int j =y+vy;
            do{
                if(Math.abs(x-xx)-Math.abs(y-yy) != 0){
                                       
                    break;
                }
                            if(visible_board[i][j].return_player() == visible_board[x][y].return_player()){
                              
                                //System.out.print("ten sam gracz ");
                                break;
                            }else  
                            if(visible_board[i][j].return_player() != visible_board[x][y].return_player() && visible_board[i][j].return_player()!=0 && i==xx){
                                //System.out.print("pion wroga na koncu ");
                                visible_board[xx][yy]=visible_board[x][y];
                                visible_board[x][y]= new piece();
                              
                                break;
                            }else 
                            if(visible_board[i][j].return_player() != visible_board[x][y].return_player() && visible_board[i][j].return_player()!=0 && i!=xx){
                                //System.out.print("pion wroga po drodze ");
                             
                                break;
                            }else
                            if(i==xx && visible_board[i][j].return_player()==0){
                                //System.out.print("puste pole koncowe ");
                                visible_board[xx][yy]=visible_board[x][y];
                                visible_board[x][y]= new piece();
                               
                                break;
                            }else 
                            if(visible_board[i][j].return_player()==0){
                                //System.out.print("puste pole ");
                            }else
                            if (true){
                            //System.out.print("co jest k");
                           
                            }
                        i+=vx;
                        j+=vy;
            }while(i!=xx+vx);
        }
        if(visible_board[x][y].return_piece()==4){
                    if(y==yy){
                        if(x>=xx){vx=-1;}
                        if(x<=xx){vx=1;}
                        //for(int i=x+vx;i!=xx;i+=vx)
                        int i =x+vx;
                        do{
                            if(visible_board[i][y].return_player() == visible_board[x][y].return_player()){
                                //System.out.print("ten sam gracz ");
                               
                                break;
                            }else  
                            if(visible_board[i][y].return_player() != visible_board[x][y].return_player() && visible_board[i][y].return_player()!=0 && i==xx){
                                //System.out.print("pion wroga na koncu ");
                                visible_board[xx][yy]=visible_board[x][y];
                                visible_board[x][y]= new piece();
                               
                                break;
                            }else 
                            if(visible_board[i][y].return_player() != visible_board[x][y].return_player() && visible_board[i][y].return_player()!=0 && i!=xx){
                                //System.out.print("pion wroga po drodze ");
                                break;
                             
                            }else
                            if(i==xx && visible_board[i][y].return_player()==0){
                                //System.out.print("puste pole koncowe ");
                                visible_board[xx][yy]=visible_board[x][y];
                                visible_board[x][y]= new piece();
                               
                                break;
                            }else 
                            if(visible_board[i][y].return_player()==0){
                                //System.out.print("puste pole ");
                            }else
                            if (true){
                            //System.out.print("co jest k");
                       
                            }
                        i+=vx;
                        }while(i!=xx+vx);
                    }
                    else if(x==xx){
                        if(y>=yy){vy=-1;}
                        if(y<=yy){vy=1;}
                        //for(int j=y+vy;j!=yy;j+=vy)
                        int j=y+vy;
                        do{
                            if(visible_board[x][j].return_player() == visible_board[x][y].return_player()){
                                //System.out.print("ten sam gracz ");
                                break;
                                
                            }else
                            if(visible_board[x][j].return_player() != visible_board[x][y].return_player() && visible_board[x][j].return_player()!=0 && j==yy){
                                //System.out.print("pion wroga na koncu ");
                                visible_board[xx][yy]=visible_board[x][y];
                                visible_board[x][y]= new piece();
                            
                                break;
                            }else
                            if(visible_board[x][j].return_player() != visible_board[x][y].return_player() && visible_board[x][j].return_player()!=0 && j!=yy){
                                //System.out.print("pion wroga po drodze ");
                                break;
                            
                            }else
                            if(j==yy && visible_board[x][j].return_player()==0){
                                //System.out.print("puste pole koncowe ");
                                visible_board[xx][yy]=visible_board[x][y];
                                visible_board[x][y]= new piece();
                               
                                break;
                            }else 
                            if(visible_board[x][j].return_player()==0 && j!=yy){
                                //System.out.print("puste pole ");
                            }else 
                            if (true){
                            //System.out.print("co jest k");
                         
                            }
                        j+=vy;
                        }while(j!=yy+vy);
                    }
                    else if(x!=xx && y!=yy){
                        //System.out.print("error ");
                      
                    }
        }
        if(visible_board[x][y].return_piece()==5){
            if(y==yy || x==xx){
                if(y==yy){
                        if(x>=xx){vx=-1;}
                        if(x<=xx){vx=1;}
                        //for(int i=x+vx;i!=xx;i+=vx)
                        int i =x+vx;
                        do{
                            if(visible_board[i][y].return_player() == visible_board[x][y].return_player()){
                                //System.out.print("ten sam gracz ");
                                break;
                           
                            }else  
                            if(visible_board[i][y].return_player() != visible_board[x][y].return_player() && visible_board[i][y].return_player()!=0 && i==xx){
                                //System.out.print("pion wroga na koncu ");
                                visible_board[xx][yy]=visible_board[x][y];
                                visible_board[x][y]= new piece();
                             
                                break;
                            }else 
                            if(visible_board[i][y].return_player() != visible_board[x][y].return_player() && visible_board[i][y].return_player()!=0 && i!=xx){
                                //System.out.print("pion wroga po drodze ");
                                break;
                             
                            }else
                            if(i==xx && visible_board[i][y].return_player()==0){
                                //System.out.print("puste pole koncowe ");
                                visible_board[xx][yy]=visible_board[x][y];
                                visible_board[x][y]= new piece();
                             
                                break;
                            }else 
                            if(visible_board[i][y].return_player()==0){
                                //System.out.print("puste pole ");
                            }else
                            if (true){
                            //System.out.print("co jest k");
                        
                            }
                        i+=vx;
                        }while(i!=xx+vx);
                    }
                    else if(x==xx){
                        if(y>=yy){vy=-1;}
                        if(y<=yy){vy=1;}
                        //for(int j=y+vy;j!=yy;j+=vy)
                        int j=y+vy;
                        do{
                            if(visible_board[x][j].return_player() == visible_board[x][y].return_player()){
                                //System.out.print("ten sam gracz ");
                                break;
                               
                            }else
                            if(visible_board[x][j].return_player() != visible_board[x][y].return_player() && visible_board[x][j].return_player()!=0 && j==yy){
                                //System.out.print("pion wroga na koncu ");
                                visible_board[xx][yy]=visible_board[x][y];
                                visible_board[x][y]= new piece();
                                break;
                              
                            }else
                            if(visible_board[x][j].return_player() != visible_board[x][y].return_player() && visible_board[x][j].return_player()!=0 && j!=yy){
                                //System.out.print("pion wroga po drodze ");
                                break;
                               
                            }else
                            if(j==yy && visible_board[x][j].return_player()==0){
                                //System.out.print("puste pole koncowe ");
                                visible_board[xx][yy]=visible_board[x][y];
                                visible_board[x][y]= new piece();
                                break;
                              
                            }else 
                            if(visible_board[x][j].return_player()==0 && j!=yy){
                                //System.out.print("puste pole ");
                            }else 
                            if (true){
                            //System.out.print("co jest k");
                       
                            }
                        j+=vy;
                        }while(j!=yy+vy);
                    }
                    else if(x!=xx && y!=yy){
                        //System.out.print("error ");
                    }
            }else{
                if(x>=xx){vx=-1;}
                if(x<xx){vx=1;}
                if(y>=yy){vy=-1;}
                if(y<yy){vy=1;}
                int i =x+vx;
                int j =y+vy;
                do{
                    if(Math.abs(x-xx)-Math.abs(y-yy) != 0){
                        break;
                 
                    }
                                if(visible_board[i][j].return_player() == visible_board[x][y].return_player()){
                                    //System.out.print("ten sam gracz ");
                                    break;
                                  
                                }else  
                                if(visible_board[i][j].return_player() != visible_board[x][y].return_player() && visible_board[i][j].return_player()!=0 && i==xx){
                                    //System.out.print("pion wroga na koncu ");
                                    visible_board[xx][yy]=visible_board[x][y];
                                    visible_board[x][y]= new piece();
                                
                                    break;
                                }else 
                                if(visible_board[i][j].return_player() != visible_board[x][y].return_player() && visible_board[i][j].return_player()!=0 && i!=xx){
                                    //System.out.print("pion wroga po drodze ");
                                    break;
                                  
                                }else
                                if(i==xx && visible_board[i][j].return_player()==0){
                                    //System.out.print("puste pole koncowe ");
                                    visible_board[xx][yy]=visible_board[x][y];
                                    visible_board[x][y]= new piece();
                                    break;
                                    
                                }else 
                                if(visible_board[i][j].return_player()==0){
                                    //System.out.print("puste pole ");
                                }else
                                if (true){
                                //System.out.print("co jest k");
                               
                                }
                            i+=vx;
                            j+=vy;
                }while(i!=xx+vx);
            }
        }
        if(visible_board[x][y].return_piece()==6){
            if(x>=xx){vx=-1;}
            if(x<xx){vx=1;}
            if(y>=yy){vy=-1;}
            if(y<yy){vy=1;}
            int i =x+vx;
            int j =y+vy;
            do{
                if(Math.abs(x-xx)>1 || Math.abs(y-yy) > 1){
                    //System.out.print("co tu sie");
                    break;
                  
                }
                            if(visible_board[xx][yy].return_player() == visible_board[x][y].return_player()){
                                //System.out.print("ten sam gracz ");
                                break;
                              
                            }else  
                            if(visible_board[xx][yy].return_player() != visible_board[x][y].return_player() && visible_board[xx][yy].return_player()!=0){
                                //System.out.print("pion wroga na koncu ");
                                visible_board[xx][yy]=visible_board[x][y];
                                visible_board[x][y]= new piece();
                               
                                break;
                            }else 
                            if( visible_board[xx][yy].return_player()==0){
                                //System.out.print("puste pole koncowe ");
                                visible_board[xx][yy]=visible_board[x][y];
                                visible_board[x][y]= new piece();
                             
                                break;
                            }else 
                            if (true){
                            //System.out.print("co jest k");

                            }
            }while(false);
        }
        
        
        
        
        if(visible_board[x][y].return_player()==0 ){
            if((end_piece==99) && visible_board[xx][yy].return_piece()!=99){
                if(visible_board[xx][yy].return_player()==1){
                    visible_board[xx-1][yy]=empty;
                }else if(visible_board[xx][yy].return_player()==2){
                    visible_board[xx+1][yy]=empty;
                }
            }
        }




        
    
    }
    
    
    
    
    boolean check_all_pieces(int x, int y, int xx, int yy){
        int vx=0, vy=0;
        // 1 pion, 2 koń, 3 goniec, 4 wieża, 5 królowa, 6 król
        if(visible_board[x][y].return_piece()==1){
                if(visible_board[x][y].return_player()==2){
                    if(y==yy){//ruch
                        if(xx==x-1 && visible_board[xx][yy].return_player()==0){
                            return true; 
                        }else
                        if(xx==x-2 && visible_board[xx][yy].return_player()==0 && x==6){
                            return true;
                        }
                    }else 
                    if((yy==y+1 && visible_board[x-1][y+1].return_player()==1) || (yy==y-1 && visible_board[x-1][y-1].return_player()==1)){
                        if(xx==x-1){
                            return true;
                        }
                    }
                }
                if(visible_board[x][y].return_player()==1){
                    if(y==yy){
                        if(xx==x+1 && visible_board[xx][yy].return_player()==0){
                            return true;
                        }else
                        if(xx==x+2 && visible_board[xx][yy].return_player()==0 && x==1){
                            return true;
                        }
                    }else 
                     if((yy==y+1 && visible_board[x+1][y+1].return_player()==2) || (yy==y-1 && visible_board[x+1][y-1].return_player()==2)){
                        if(xx==x+1){
                            return true;
                        }
                    } 
                }   
        }
        if(visible_board[x][y].return_piece()==2){
            
            if( (Math.abs(x-xx)==2 && Math.abs(y-yy)==1) || (Math.abs(x-xx)==1 && Math.abs(y-yy)==2) ){
                if(visible_board[x][y].return_player() != visible_board[xx][yy].return_player() && visible_board[xx][yy].return_player()==0){
                    return true;
                }
                if(visible_board[x][y].return_player() != visible_board[xx][yy].return_player() && visible_board[xx][yy].return_player()!=0){
                    return true;
                }
            }
        }
        if(visible_board[x][y].return_piece()==3){
            if(x>=xx){vx=-1;}
            if(x<xx){vx=1;}
            if(y>=yy){vy=-1;}
            if(y<yy){vy=1;}
            int i =x+vx;
            int j =y+vy;
            do{
                if(Math.abs(x-xx)-Math.abs(y-yy) != 0){
                    return false;                    
                }
                            if(visible_board[i][j].return_player() == visible_board[x][y].return_player()){
                                return false;
                            }else  
                            if(visible_board[i][j].return_player() != visible_board[x][y].return_player() && visible_board[i][j].return_player()!=0 && i==xx){
                                return true;
                            }else 
                            if(visible_board[i][j].return_player() != visible_board[x][y].return_player() && visible_board[i][j].return_player()!=0 && i!=xx){
                                return false;
                            }else
                            if(i==xx && visible_board[i][j].return_player()==0){
                                return true;
                            }else 
                            if(visible_board[i][j].return_player()==0){
                            }else
                            if (true){
                            return false;
                            }
                        i+=vx;
                        j+=vy;
            }while(i!=xx+vx);
        }
        if(visible_board[x][y].return_piece()==4){
                    if(y==yy){
                        if(x>=xx){vx=-1;}
                        if(x<=xx){vx=1;}
                        int i =x+vx;
                        do{
                            if(visible_board[i][y].return_player() == visible_board[x][y].return_player()){
                                return false;
                            }else  
                            if(visible_board[i][y].return_player() != visible_board[x][y].return_player() && visible_board[i][y].return_player()!=0 && i==xx){
                                return true;
                            }else 
                            if(visible_board[i][y].return_player() != visible_board[x][y].return_player() && visible_board[i][y].return_player()!=0 && i!=xx){

                                return false;
                            }else
                            if(i==xx && visible_board[i][y].return_player()==0){
                                return true;
                            }else 
                            if(visible_board[i][y].return_player()==0){
                            }else
                            if (true){
                            return false;
                            }
                        i+=vx;
                        }while(i!=xx+vx);
                    }
                    else if(x==xx){
                        if(y>=yy){vy=-1;}
                        if(y<=yy){vy=1;}
                        int j=y+vy;
                        do{
                            if(visible_board[x][j].return_player() == visible_board[x][y].return_player()){
                                return false;
                            }else
                            if(visible_board[x][j].return_player() != visible_board[x][y].return_player() && visible_board[x][j].return_player()!=0 && j==yy){
                                return true;
                            }else
                            if(visible_board[x][j].return_player() != visible_board[x][y].return_player() && visible_board[x][j].return_player()!=0 && j!=yy){
                                return false;
                            }else
                            if(j==yy && visible_board[x][j].return_player()==0){
                                return true;
                            }else 
                            if(visible_board[x][j].return_player()==0 && j!=yy){
                            }else 
                            if (true){
                            return false;
                            }
                        j+=vy;
                        }while(j!=yy+vy);
                    }
                    else if(x!=xx && y!=yy){
                        return false;
                    }
        }
        if(visible_board[x][y].return_piece()==5){
            if(y==yy || x==xx){
                if(y==yy){
                        if(x>=xx){vx=-1;}
                        if(x<=xx){vx=1;}
                        int i =x+vx;
                        do{
                            if(visible_board[i][y].return_player() == visible_board[x][y].return_player()){
                                return false;
                            }else  
                            if(visible_board[i][y].return_player() != visible_board[x][y].return_player() && visible_board[i][y].return_player()!=0 && i==xx){
                                return true;
                            }else 
                            if(visible_board[i][y].return_player() != visible_board[x][y].return_player() && visible_board[i][y].return_player()!=0 && i!=xx){
                                return false;
                            }else
                            if(i==xx && visible_board[i][y].return_player()==0){
                                return true;
                            }else 
                            if(visible_board[i][y].return_player()==0){
                            }else
                            if (true){
                            return false;
                            }
                        i+=vx;
                        }while(i!=xx+vx);
                    }
                    else if(x==xx){
                        if(y>=yy){vy=-1;}
                        if(y<=yy){vy=1;}
                        int j=y+vy;
                        do{
                            if(visible_board[x][j].return_player() == visible_board[x][y].return_player()){
                                return false;
                            }else
                            if(visible_board[x][j].return_player() != visible_board[x][y].return_player() && visible_board[x][j].return_player()!=0 && j==yy){
                                return true;
                            }else
                            if(visible_board[x][j].return_player() != visible_board[x][y].return_player() && visible_board[x][j].return_player()!=0 && j!=yy){
                                return false;
                            }else
                            if(j==yy && visible_board[x][j].return_player()==0){
                               return true;
                            }else 
                            if(visible_board[x][j].return_player()==0 && j!=yy){
                            }else 
                            if (true){
                            return false;
                            }
                        j+=vy;
                        }while(j!=yy+vy);
                    }
                    else if(x!=xx && y!=yy){
                    }
            }else{
                if(x>=xx){vx=-1;}
                if(x<xx){vx=1;}
                if(y>=yy){vy=-1;}
                if(y<yy){vy=1;}
                int i =x+vx;
                int j =y+vy;
                do{
                    if(Math.abs(x-xx)-Math.abs(y-yy) != 0){
                    return false;
                    }
                                if(visible_board[i][j].return_player() == visible_board[x][y].return_player()){
                                    return false;
                                }else  
                                if(visible_board[i][j].return_player() != visible_board[x][y].return_player() && visible_board[i][j].return_player()!=0 && i==xx){
                                    return true;
                                }else 
                                if(visible_board[i][j].return_player() != visible_board[x][y].return_player() && visible_board[i][j].return_player()!=0 && i!=xx){
                                    return false;
                                }else
                                if(i==xx && visible_board[i][j].return_player()==0){
                                    return true;
                                }else 
                                if(visible_board[i][j].return_player()==0){
                                }else
                                if (true){
                                return false;
                                }
                            i+=vx;
                            j+=vy;
                }while(i!=xx+vx);
            }
        }
        if(visible_board[x][y].return_piece()==6){
            if(x>=xx){vx=-1;}
            if(x<xx){vx=1;}
            if(y>=yy){vy=-1;}
            if(y<yy){vy=1;}
            int i =x+vx;
            int j =y+vy;
            do{
                if(Math.abs(x-xx)>1 || Math.abs(y-yy) > 1){
                    return false;
                }
                            if(visible_board[xx][yy].return_player() == visible_board[x][y].return_player()){
                                return false;
                            }else  
                            if(visible_board[xx][yy].return_player() != visible_board[x][y].return_player() && visible_board[xx][yy].return_player()!=0){
                                return true;
                            }else 
                            if( visible_board[xx][yy].return_player()==0){
                                return true;
                            }else 
                            if (true){
                            return false;
                            }
            }while(false);
        }
        
        



        
    return false;
    }
}
