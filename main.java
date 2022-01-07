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

public class Chess {
    
    
    
    
    
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        board a= new board();
        a.print_board();
        boolean gg=true;
        boolean p1turn=true;
        int x,y,xx,yy;
        
        while(gg){
            System.out.println("Select a piece( x ): ");
            x = scan.nextInt();
            x--;
            System.out.println("Select a piece( y ): ");
            y = scan.nextInt();
            y--;
            
            

            
            
            System.out.println("Select a field( x ): ");
            xx = scan.nextInt();
            xx--;
            System.out.println("Select a field( y ): ");
            yy = scan.nextInt();
            yy--;
            
            
            
            
            a.move(x,y,xx,yy);
            a.print_board();
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
    public temp(int x){this.player=x;this.piece_type=1;}
    public String print_piece(){return "T" + String.valueOf(player);}
    int xp,yp;
    public int pawnpositionx(){return x;}
    public int pawnpositiony(){return y;}
    public void setpawn(int x,int y){
    xp=x;
    yp=y;
    }
}

class board {
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
        {empty,empty,empty,empty,empty,empty,empty,empty},
        {empty,empty,empty,empty,empty,empty,empty,empty},
        {empty,empty,empty,empty,empty,empty,empty,empty},
        {pawn2,pawn2,pawn2,pawn2,pawn2,pawn2,pawn2,pawn2},
        {rook2,knight2,bishop2,king2,queen2,bishop2,knight2,rook2}
    };
    
   String[][] move_board=new String[8][8];
    
    void set_field(int a, int b, piece c){
        visible_board[a][b]=c;
    }
    
    String get_field(int a, int b){
       return visible_board[a][b].print_piece();
    }

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
    void print_move_board(){   
        System.out.println("xy1  2  3  4  5  6  7  8");
        for (int i = 0; i < move_board.length; i++) {
            System.out.print((i+1)+ " ");
            for (int j = 0; j < move_board[i].length; j++) {
                System.out.print(move_board[i][j] + " ");
            }
        System.out.println();
        }
    }
    
    void setmoveboard(){
        for (int i = 0; i < move_board.length; i++) {
            for (int j = 0; j < move_board[i].length; j++) {
                move_board[i][j]=visible_board[i][j].print_piece();
            }
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    void move(int x, int y, int xx, int yy){
        int vx=0, vy=0;
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
                            System.out.print("ruch do przodu p2");
                        }else
                        if(xx==x-2 && visible_board[xx][yy].return_player()==0 && x==6){
                            visible_board[xx][yy]=visible_board[x][y];
                            visible_board[x][y]= temp2;
                            temp2.setpawn(x, y);
                            System.out.print("ruch do przodu p2");
                        }
                    }else 
                    if((yy==y+1 && visible_board[x-1][y+1].return_player()==1) || (yy==y-1 && visible_board[x-1][y-1].return_player()==1)){
                        if(xx==x-1){
                            visible_board[xx][yy]=visible_board[x][y];
                            visible_board[x][y]= new piece();
                            if(xx==0){
                                visible_board[xx][yy]=new rook(2);
                                visible_board[x][y]= new piece();
                            }
                            System.out.print("bicie p2 ");
                        }
                    }
                }
                if(visible_board[x][y].return_player()==1){
                    if(y==yy){
                        if(xx==x+1 && visible_board[xx][yy].return_player()==0){
                            visible_board[xx][yy]=visible_board[x][y];
                            visible_board[x][y]= new piece();
                            if(xx==7){
                                visible_board[xx][yy]=new rook(1);
                                visible_board[x][y]= new piece();
                            }
                            System.out.print("ruch do przodu p1");
                        }else
                        if(xx==x+2 && visible_board[xx][yy].return_player()==0 && x==1){
                            visible_board[xx][yy]=visible_board[x][y];
                            visible_board[x][y]= temp1;
                            temp1.setpawn(x, y);
                            System.out.print("ruch do przodu p1");
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
                            System.out.print("bicie p1");
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
                if(Math.abs(x-xx)-Math.abs(y-yy) != 0){System.out.print("co tu sie");break;}
                            if(visible_board[i][j].return_player() == visible_board[x][y].return_player()){
                                System.out.print("ten sam gracz ");
                                break;
                            }else  
                            if(visible_board[i][j].return_player() != visible_board[x][y].return_player() && visible_board[i][j].return_player()!=0 && i==xx){
                                System.out.print("pion wroga na koncu ");
                                visible_board[xx][yy]=visible_board[x][y];
                                visible_board[x][y]= new piece();
                                break;
                            }else 
                            if(visible_board[i][j].return_player() != visible_board[x][y].return_player() && visible_board[i][j].return_player()!=0 && i!=xx){
                                System.out.print("pion wroga po drodze ");
                                break;
                            }else
                            if(i==xx && visible_board[i][j].return_player()==0){
                                System.out.print("puste pole koncowe ");
                                visible_board[xx][yy]=visible_board[x][y];
                                visible_board[x][y]= new piece();
                                break;
                            }else 
                            if(visible_board[i][j].return_player()==0){
                                System.out.print("puste pole ");
                            }else
                            if (true){
                            System.out.print("co jest k");
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
                                System.out.print("ten sam gracz ");
                                break;
                            }else  
                            if(visible_board[i][y].return_player() != visible_board[x][y].return_player() && visible_board[i][y].return_player()!=0 && i==xx){
                                System.out.print("pion wroga na koncu ");
                                visible_board[xx][yy]=visible_board[x][y];
                                visible_board[x][y]= new piece();
                                break;
                            }else 
                            if(visible_board[i][y].return_player() != visible_board[x][y].return_player() && visible_board[i][y].return_player()!=0 && i!=xx){
                                System.out.print("pion wroga po drodze ");
                                break;
                            }else
                            if(i==xx && visible_board[i][y].return_player()==0){
                                System.out.print("puste pole koncowe ");
                                visible_board[xx][yy]=visible_board[x][y];
                                visible_board[x][y]= new piece();
                                break;
                            }else 
                            if(visible_board[i][y].return_player()==0){
                                System.out.print("puste pole ");
                            }else
                            if (true){
                            System.out.print("co jest k");
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
                                System.out.print("ten sam gracz ");
                                break;
                            }else
                            if(visible_board[x][j].return_player() != visible_board[x][y].return_player() && visible_board[x][j].return_player()!=0 && j==yy){
                                System.out.print("pion wroga na koncu ");
                                visible_board[xx][yy]=visible_board[x][y];
                                visible_board[x][y]= new piece();
                                break;
                            }else
                            if(visible_board[x][j].return_player() != visible_board[x][y].return_player() && visible_board[x][j].return_player()!=0 && j!=yy){
                                System.out.print("pion wroga po drodze ");
                                break;
                            }else
                            if(j==yy && visible_board[x][j].return_player()==0){
                                System.out.print("puste pole koncowe ");
                                visible_board[xx][yy]=visible_board[x][y];
                                visible_board[x][y]= new piece();
                                break;
                            }else 
                            if(visible_board[x][j].return_player()==0 && j!=yy){
                                System.out.print("puste pole ");
                            }else 
                            if (true){
                            System.out.print("co jest k");
                            }
                        j+=vy;
                        }while(j!=yy+vy);
                    }
                    else if(x!=xx && y!=yy){
                        System.out.print("error ");
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
                                System.out.print("ten sam gracz ");
                                break;
                            }else  
                            if(visible_board[i][y].return_player() != visible_board[x][y].return_player() && visible_board[i][y].return_player()!=0 && i==xx){
                                System.out.print("pion wroga na koncu ");
                                visible_board[xx][yy]=visible_board[x][y];
                                visible_board[x][y]= new piece();
                                break;
                            }else 
                            if(visible_board[i][y].return_player() != visible_board[x][y].return_player() && visible_board[i][y].return_player()!=0 && i!=xx){
                                System.out.print("pion wroga po drodze ");
                                break;
                            }else
                            if(i==xx && visible_board[i][y].return_player()==0){
                                System.out.print("puste pole koncowe ");
                                visible_board[xx][yy]=visible_board[x][y];
                                visible_board[x][y]= new piece();
                                break;
                            }else 
                            if(visible_board[i][y].return_player()==0){
                                System.out.print("puste pole ");
                            }else
                            if (true){
                            System.out.print("co jest k");
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
                                System.out.print("ten sam gracz ");
                                break;
                            }else
                            if(visible_board[x][j].return_player() != visible_board[x][y].return_player() && visible_board[x][j].return_player()!=0 && j==yy){
                                System.out.print("pion wroga na koncu ");
                                visible_board[xx][yy]=visible_board[x][y];
                                visible_board[x][y]= new piece();
                                break;
                            }else
                            if(visible_board[x][j].return_player() != visible_board[x][y].return_player() && visible_board[x][j].return_player()!=0 && j!=yy){
                                System.out.print("pion wroga po drodze ");
                                break;
                            }else
                            if(j==yy && visible_board[x][j].return_player()==0){
                                System.out.print("puste pole koncowe ");
                                visible_board[xx][yy]=visible_board[x][y];
                                visible_board[x][y]= new piece();
                                break;
                            }else 
                            if(visible_board[x][j].return_player()==0 && j!=yy){
                                System.out.print("puste pole ");
                            }else 
                            if (true){
                            System.out.print("co jest k");
                            }
                        j+=vy;
                        }while(j!=yy+vy);
                    }
                    else if(x!=xx && y!=yy){
                        System.out.print("error ");
                    }
            }else{
                if(x>=xx){vx=-1;}
                if(x<xx){vx=1;}
                if(y>=yy){vy=-1;}
                if(y<yy){vy=1;}
                int i =x+vx;
                int j =y+vy;
                do{
                    if(Math.abs(x-xx)-Math.abs(y-yy) != 0){System.out.print("co tu sie");break;}
                                if(visible_board[i][j].return_player() == visible_board[x][y].return_player()){
                                    System.out.print("ten sam gracz ");
                                    break;
                                }else  
                                if(visible_board[i][j].return_player() != visible_board[x][y].return_player() && visible_board[i][j].return_player()!=0 && i==xx){
                                    System.out.print("pion wroga na koncu ");
                                    visible_board[xx][yy]=visible_board[x][y];
                                    visible_board[x][y]= new piece();
                                    break;
                                }else 
                                if(visible_board[i][j].return_player() != visible_board[x][y].return_player() && visible_board[i][j].return_player()!=0 && i!=xx){
                                    System.out.print("pion wroga po drodze ");
                                    break;
                                }else
                                if(i==xx && visible_board[i][j].return_player()==0){
                                    System.out.print("puste pole koncowe ");
                                    visible_board[xx][yy]=visible_board[x][y];
                                    visible_board[x][y]= new piece();
                                    break;
                                }else 
                                if(visible_board[i][j].return_player()==0){
                                    System.out.print("puste pole ");
                                }else
                                if (true){
                                System.out.print("co jest k");
                                }
                            i+=vx;
                            j+=vy;
                }while(i!=xx+vx);
            }
        }
        if(visible_board[x][y].return_piece()==6){}
        
        
        
        

    }
}
