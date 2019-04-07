import java.util.Random;

public class GameBoard {

   private char[][] board;
   private Random random = new Random();
   private char vehicle;
   private int vehiclePositionX = 0;
   private int vehiclePositionY = 0;
   private int vehicleSize;

    public GameBoard(char vehicle){
        board = new char[10][10];
        this.vehicle = vehicle;

        for (int i = 0; i < board.length; i++){
            for (int j = 0; j < board[i].length; j++){
                board[i][j] = 'o';
            }
        }

        if(vehicle == 'c'){
            board[0][0] = 'c';
        }
        else {
            for (int i = 0; i < 2; i++){
                for (int j = 0; j < 2; j++){
                    board[i][j] = 't';
                }
            }
        }

        destination();
    }
//Metoda dodająca losowo cel
    protected void destination(){
        int x, y;

        do{
            x = random.nextInt(10);
            y = random.nextInt(10);
        }
        while (board[x][y] == 'c' || board[x][y] == 't');

        board[x][y] = 'x';
    }

    private void vehiclePositionCheck(){
        outerloop: //etykieta dzięki której możliwe jest wyjście z zewnętrznej pętli
        for (int i = 0 ; i < board.length; i++){
            for(int j = 0; j < board[i].length; j++){
                if(board[i][j] == 'c' || board[i][j] == 't'){
                    vehiclePositionX = i;
                    vehiclePositionY = j;
                    System.out.println(i+" "+j);
                    break outerloop;
                }
            }
        }
    }

    private boolean destinationCheck(){
        if(board[vehiclePositionX][vehiclePositionY] == 'x'){
            return true;
        }
        else if(vehicle == 't' && vehiclePositionX < 9 && vehiclePositionY < 9){
            if(board[vehiclePositionX+1][vehiclePositionY] == 'x' || board[vehiclePositionX+1][vehiclePositionY+1] == 'x' || board[vehiclePositionX][vehiclePositionY+1] == 'x'){
                return true;
            }
        }
        return false;
    }

    //b - oznacza blokadę jazdy w tym kierunku ponieważ pojazd jest na krawędzi planszy, m oznacza ruch, p oznacza osiągnięcie celu i zatankowanie
    protected char move(char direction){
        vehiclePositionCheck();
        char moveValue = 0;
        switch (direction){
            case 'g':
                vehiclePositionX--;
                if(vehiclePositionX < 0 ){
                    return 'b';
                }
                else if(destinationCheck()){
                    moveValue = 'p';
                }
                if(vehicle == 'c'){
                    board[vehiclePositionX][vehiclePositionY] = 'c';
                    board[vehiclePositionX+1][vehiclePositionY] = 'o';
                    break;
                }
                board[vehiclePositionX][vehiclePositionY] = 't';
                board[vehiclePositionX][vehiclePositionY + 1] = 't';
                board[vehiclePositionX + 2][vehiclePositionY] = 'o';
                board[vehiclePositionX + 2][vehiclePositionY + 1] = 'o';
                break;
            case 'd':
                vehiclePositionX++;
                if (vehiclePositionX > 9){
                    return 'b';
                }
                else if(destinationCheck()){
                    moveValue = 'p';
                }

                if (vehicle == 'c'){
                    board[vehiclePositionX][vehiclePositionY] = 'c';
                    board[vehiclePositionX-1][vehiclePositionY] = 'o';
                    break;
                }
                else if(vehiclePositionX > 8){//warunek dla czołgów
                    return 'b';
                }
                board[vehiclePositionX+1][vehiclePositionY] = 't';
                board[vehiclePositionX+1][vehiclePositionY + 1] = 't';
                board[vehiclePositionX - 1][vehiclePositionY] = 'o';
                board[vehiclePositionX - 1][vehiclePositionY + 1] = 'o';
                break;
            case 'l':
                vehiclePositionY--;
                if(vehiclePositionY < 0){
                    return 'b';
                }
                else if (destinationCheck()){
                    moveValue = 'p';
                }

                if(vehicle == 'c'){
                    board[vehiclePositionX][vehiclePositionY] = 'c';
                    board[vehiclePositionX][vehiclePositionY+1] = 'o';
                    break;
                }
                board[vehiclePositionX][vehiclePositionY] = 't';
                board[vehiclePositionX+1][vehiclePositionY] = 't';
                board[vehiclePositionX][vehiclePositionY+2] = 'o';
                board[vehiclePositionX+1][vehiclePositionY+2] = 'o';
                break;
            case 'p':
                vehiclePositionY++;
                if (vehiclePositionY > 9){
                    return 'b';
                }
                else if (destinationCheck()){
                    moveValue = 'p';
                }

                if(vehicle == 'c'){
                    board[vehiclePositionX][vehiclePositionY] = 'c';
                    board[vehiclePositionX][vehiclePositionY-1] = 'o';
                    break;
                }
                else if(vehiclePositionY > 8){//taka sama sytuacja jak w przypadku jazdy w dół planszy
                    return 'b';
                }

                board[vehiclePositionX][vehiclePositionY+1] = 't';
                board[vehiclePositionX+1][vehiclePositionY+1] = 't';
                board[vehiclePositionX][vehiclePositionY-1] = 'o';
                board[vehiclePositionX+1][vehiclePositionY-1] = 'o';

//metoda na skręt w prawo jest ok

                break;
        }
        return   (moveValue == 'p') ? 'p' : 'm';

    }

     void printBoard(){
        for (int i = 0; i < board.length; i++){
            for (int j = 0; j < board[i].length; j++){
                System.out.print(board[i][j]);
                if(j == 9){
                    System.out.println();
                }

            }
        }
    }



}
