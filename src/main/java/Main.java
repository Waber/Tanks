import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        char vehicle;
        char direction;
        char vehicleCheck;
        int fuel = 55;
        System.out.println("Wybierz typ pojazdu \n'c' - samochód \n 't' - czołg (zużywa dwie jednostki paliwa) ");
        Scanner in = new Scanner(System.in);
        vehicle = in.next().charAt(0);

        GameBoard board = new GameBoard(vehicle);
        

        do{
            board.printBoard();
            System.out.println("Poziom paliwa: "+fuel);
            System.out.println("wybierz kierunek jazdy \n 'l' - lewo \n 'p' - prawo \n 'd' - dół \n 'g' - góra");
            direction = in.next().charAt(0);
            vehicleCheck = board.move(direction);
            if (vehicleCheck == 'b'){
                System.out.println("Krawędź planszy, wybierz inny kierunek");
                continue;
            }
            else if(vehicleCheck == 'm'){
                fuel--;
            }
            else {
                fuel += 4;
                board.destination();
            }
            if(vehicle == 't'){
                fuel--;
            }
        } while (fuel > 0);
        System.out.println("Skończyło się paliwo, koniec gry");


    }
}
