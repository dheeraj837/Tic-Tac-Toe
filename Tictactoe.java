import java.util.*;
public class Tictactoe
{
    static int computer=1,human=2,side=3;
    static char computermove='O',humanmove='X';
    Scanner sc=new Scanner(System.in);
    public static void initialise(char board[][])
    {
        for(int i=0;i<side;i++)
        {
            for(int j=0;j<side;j++)
            {
                board[i][j]='*';
            }
        }
    }
    public static void showInstructions()
    {
        System.out.println("\nChoose a cell numbered from 1 to 9 as below and play\n");
        System.out.println("\t\t\t 1 | 2 | 3 ");
        System.out.println("\t\t\t-----------");
        System.out.println("\t\t\t 4 | 5 | 6 ");
        System.out.println("\t\t\t-----------");
        System.out.println("\t\t\t 7 | 8 | 9 \n");
    }
    public static boolean gameOver(char board[][])
    {
        return (rowcrossed(board)||columncrossed(board)||diagonalcrossed(board));
    }
    public static boolean rowcrossed(char board[][])
    {
        for(int i=0;i<side;i++)
        {
            if(board[i][0]==board[i][1] && board[i][1]==board[i][2] && board[i][0]!='*')
            return true;
        }
        return false;
    }
    public static boolean columncrossed(char board[][])
    {
        for(int i=0;i<side;i++)
        {
            if(board[0][i]==board[1][i] && board[1][i]==board[2][i] && board[0][i]!='*')
            return true;
        }
        return false;
    }
    public static boolean diagonalcrossed(char board[][])
    {
        if(board[0][0]==board[1][1] && board[1][1]==board[2][2] && board[0][0]!='*')
            return true;
        if(board[0][2]==board[1][1] && board[1][1]==board[2][0] && board[0][2]!='*')
            return true;
        return false;
    }
    public static int minimax(char board[][],int depth,boolean isAI)
    {
        int score=0,bestscore=0;
        if(gameOver(board)==true)
        {
            if(isAI==false)
            return 10;
            if(isAI==true)
            return -10;
        }
        else
        {
            if(depth<9)
            {
                if(isAI==true)
                {
                    bestscore=Integer.MIN_VALUE;
                    for(int i=0;i<side;i++)
                    {
                        for(int j=0;j<side;j++)
                        {
                            if(board[i][j]=='*')
                            {
                                board[i][j]=computermove;
                                score=minimax(board,(depth+1),false);
                                board[i][j]='*';
                                if(score>bestscore)
                                bestscore=score;
                            }
                        }
                    }
                    return bestscore;
                }
                else
                {
                    bestscore=Integer.MIN_VALUE;
                    for(int i=0;i<side;i++)
                    {
                        for(int j=0;j<side;j++)
                        {
                            if(board[i][j]=='*')
                            {
                                board[i][j]=humanmove;
                                score=minimax(board,(depth+1),true);
                                board[i][j]='*';
                                if(score<bestscore)
                                bestscore=score;
                            }
                        }
                    }
                    return bestscore;
                }
            }
            else
            {
                return 0;
            }
            
        }
        return bestscore;
    }
    public static int bestmove(char board[][],int moveindex)
    {
        int x=-1,y=-1,score=0,bestscore=Integer.MIN_VALUE;
        for(int i=0;i<side;i++)
        {
            for(int j=0;j<side;j++)
            {
                if(board[i][j]=='*')
                {
                    board[i][j]=computermove;
                    score=minimax(board,(moveindex+1),false);
                    board[i][j]='*';
                    if(score>bestscore)
                    {
                        bestscore=score;
                        x=i;
                        y=j;
                    }
                }
            }
        }
        return x*3+y;
    }
    public static Void showboard(char board[][])
    {
        System.out.println("\t\t\t "+board[0][0]+" | "+board[0][1]+" | "+board[0][2]);
        System.out.println("\t\t\t-----------");
        System.out.println("\t\t\t "+board[1][0]+" | "+board[1][1]+" | "+board[1][2]);
        System.out.println("\t\t\t-----------");
        System.out.println("\t\t\t "+board[2][0]+" | "+board[2][1]+" | "+board[2][2]);
        return null;
    }
    public static void declareWinner(int whoseturn)
    {
        if(whoseturn==computer)
        System.out.println("COMPUTER has won");
        else
        System.out.println("HUMAN has won");
    }
    public static void playtictactoe(int whoseturn)
    {
        char [][]board=new char[side][side];
        Scanner sc=new Scanner(System.in);
        int moveindex=0,x=0,y=0;
        initialise(board);
        showInstructions();
        while(gameOver(board)==false && moveindex!=side*side)
        {
                int n=0;
                if(whoseturn==computer)
                {
                    n=bestmove(board,moveindex);
                    x=n/side;
                    y=n%side;
                    if(x<0)
                    x=0;
                    if(y<0)
                    y=0;
                    board[x][y]=computermove;
                    System.out.print("COMPUTER has put a "+computermove+" in cell "+(n+1)+"\n\n");
                    showboard(board);
                    moveindex++;
                    whoseturn=human;
                }
                else if(whoseturn==human)
                {
                    System.out.print("You can insert in the following positions : ");  
                    for(int i=0;i<side;i++)
                    {
                        for(int j=0;j<side;j++)
                        {
                            if(board[i][j]=='*')
                            System.out.print(((i*3+j)+1)+" ");
                            System.out.print("\n\n enter the position= ");
                            n=sc.nextInt();
                            n--;
                            x=n/side;
                            y=n%side;
                            if(x<0)
                            x=0;
                            if(y<0)
                            y=0;
                            if(board[x][y]=='*' && n<9 &&  n>=0)
                            {
                                board[x][y]=humanmove;
                                System.out.print("\nhuman  has put a "+humanmove+" in cell "+(n+1)+"\n\n");
                                showboard(board);
                                moveindex++;
                                whoseturn=computer;
                            }
                            else if(board[x][y]!='*' && n<9 && n>=0)
                            {
                                System.out.println("\n Position is occupied, select any one place from the available places\n");
                            }
                            else
                            System.out.println("Invalid position");
                        }
                    }
            }
        }
        if(gameOver(board)==false && moveindex==side*side)
            System.out.println("It's a draw");
        else
        {
            if(whoseturn==computer)
                whoseturn=human;
            else if(whoseturn==human)
                whoseturn=computer;
                declareWinner(whoseturn);
        } 
    }
    public static void main(String args[])
    {
        //Scanner sc=new Scanner(System.in);
        Scanner sc=new Scanner(System.in);
        System.out.println("\n-----------------------------\n");
        System.out.println("-----------tic tac toe-----------");
        System.out.println("\n-----------------------------\n");
        char cont='y';
        do
        {
            //String s;
            char choice='n';
            char hu='y',com='n';
            System.out.print("Do you want to start first?(1/0) : ");
            int s=sc.nextInt();
            if(s==1)
            choice=hu;
            else if(s==0)
            choice=com;
            //choice=sc.next();
            if(choice=='n') 
            playtictactoe(1);
            else if(choice=='y')
            playtictactoe(2);
            else
            System.out.println("Invalid choice\n");
            System.out.println("\nDo you want to quit(1/0) : ");
            s=sc.nextInt();
            if(s==1)
            cont=hu;
            else if(s==0)
            cont=com;
        }while(cont=='n');
        return;
    }
}