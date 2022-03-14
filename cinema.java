package cinema;
import java.util.Scanner;

public class Cinema {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // Input for cinema creation
        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seats = scanner.nextInt();
        String[][] cinema = createCinema(rows, seats);  // Cinema creation in a multiDimArray
        int purchasedTickets = 0;
        int income = 0;
        int totalIncome = getTotalIncome(rows, seats);

        while (true) {
            printMenu();

            int usersChoice = scanner.nextInt();

            switch (usersChoice) {
                case 1:
                    printCinema(cinema);
                    break;
                case 2:
                    while (true) {
                        //Seat-choice
                        System.out.println("Enter a row number:");
                        int row = scanner.nextInt();
                        System.out.println("Enter a seat number in that row:");
                        int seat = scanner.nextInt();

                        if (cinema[row][seat] == "B ") {
                            System.out.println("That ticket has already been purchased!");
                        } else if(row > rows  || seat > seats ) {
                            System.out.println("Wrong input!");
                        } else {
                            int price = getTicketPrice(row, rows, seats);
                            cinema[row][seat] = "B "; // Puts seat as blocked
                            System.out.println("Ticket price:$" + price);
                            income += price;
                            purchasedTickets += 1;
                            break;
                        }
                    }
                    break;
                case 3:
                    System.out.println("Number of purchased Tickets: " + purchasedTickets);
                    double percentage = getPercentage(rows, seats, purchasedTickets);
                    System.out.println("Percentage: " + percentage + "%"); // get Percentage
                    System.out.println("Current income: " + income);
                    System.out.println("Total income: " + totalIncome);
                    break;
                case 0:
                    return;

            }
        }


    }

    public static String[][] createCinema(int rows, int seats) {
        String[][] cinema = new String[rows + 1][seats + 1];
        for (int i = 0; i < cinema.length; i++){
            for (int j = 0; j < cinema[i].length; j++) {
                if(i == 0 && j == 0) {
                    cinema[i][j] = "  ";
                } else if (i == 0) {
                    cinema[i][j] = String.valueOf(j) + " ";
                } else if (j == 0) {
                    cinema[i][j] = String.valueOf(i) + " ";
                } else {
                    cinema[i][j] = "S ";
                }
            }
        }
        return cinema;
    }

    public static void printMenu() {
        System.out.println("1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
        System.out.println("0. Exit");
    }

    public static void printCinema(String[][] cinema) {
        // Output (print) of the cinema
        System.out.println("Cinema:");

        for (int i = 0; i < cinema.length; i++){
            for (int j = 0; j < cinema[i].length; j++) {
                System.out.print(cinema[i][j]);
            }
            System.out.println();
        }
    }


    public static int getTicketPrice(int row, int rows, int seats) {

        // Price evaluation
        int price = 10;
        int frontPrice = 10;
        int backPrice = 8;
        int lastFrontRow = (rows) / 2;

        if (rows * seats > 60) { //checks if cinema size is exceeded
            if (row <= lastFrontRow) {
                price = frontPrice;
            } else {
                price = backPrice;
            }
        }
        return price;
    }

    public static double getPercentage (int rows, int seats, int purchasedTickets) {
        final double PERCENT = 100.0;

        double percentage = purchasedTickets / (double) (rows * seats) * PERCENT;
        return percentage;
    }

    public static int getTotalIncome(int rows, int seats) {
        int totalIncome = 0;
        int frontPrice = 10;
        int backPrice = 8;
        int lastFrontRow = (rows) / 2;

        if (rows * seats > 60){
            totalIncome = lastFrontRow * seats * frontPrice + (rows - lastFrontRow) * seats * backPrice;
        } else {
            totalIncome = frontPrice * seats * rows;
        }
        return totalIncome;
    }
}
