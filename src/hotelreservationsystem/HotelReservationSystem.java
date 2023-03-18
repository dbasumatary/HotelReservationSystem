package hotelreservationsystem;

import java.util.ArrayList;
import java.util.List;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class HotelReservationSystem {
    private List<Hotels> hotels;

    public HotelReservationSystem() {
        hotels = new ArrayList<>();
    }

    //Adds hotel to the list
    public void addHotel(Hotels hotel) {
        hotels.add(hotel);
    }

    //Method to find the cheapest hotel for a given date range
    public Hotels findCheapestHotel() {
        Scanner scanner = new Scanner(System.in);

        //Example to enter date: 12Jan2023
        System.out.print("Enter the start date (ddMMMyyyy): ");
        String startDateString = scanner.next();
        LocalDate startDate = LocalDate.parse(startDateString, DateTimeFormatter.ofPattern("ddMMMyyyy"));

        System.out.print("Enter the end date (ddMMMyyyy): ");
        String endDateString = scanner.next();
        LocalDate endDate = LocalDate.parse(endDateString, DateTimeFormatter.ofPattern("ddMMMyyyy"));

        DayOfWeek startDayOfWeek = startDate.getDayOfWeek();
        DayOfWeek endDayOfWeek = endDate.getDayOfWeek();
        int numOfWeekdays = 0;
        int numOfWeekends = 0;

        while (!startDate.isAfter(endDate)) {
            if (startDate.getDayOfWeek() == DayOfWeek.SATURDAY || startDate.getDayOfWeek() == DayOfWeek.SUNDAY) {
                numOfWeekends++;
            } else {
                numOfWeekdays++;
            }
            startDate = startDate.plusDays(1);
        }

        //Initialize the values
        int minTotalRate = Integer.MAX_VALUE;
        int maxRating = Integer.MIN_VALUE;
        Hotels cheapestHotel = null;
        Hotels bestRatedHotel = null;

        for (Hotels hotel : hotels) {
            int totalRate = 0;
            if (startDayOfWeek == DayOfWeek.SATURDAY || startDayOfWeek == DayOfWeek.SUNDAY || endDayOfWeek == DayOfWeek.SATURDAY || endDayOfWeek == DayOfWeek.SUNDAY) {
                totalRate = numOfWeekdays * hotel.getWeekdayRate() + numOfWeekends * hotel.getWeekendRate();
            } else {
                totalRate = (numOfWeekdays - 1) * hotel.getWeekdayRate() + numOfWeekends * hotel.getWeekendRate();
            }

            if (totalRate < minTotalRate) {
                minTotalRate = totalRate;
                cheapestHotel = hotel;
            }

            System.out.println("Please choose what to perform");
            System.out.println("1. Find cheapest hotel\n2. Find cheapest best rated hotel\n3. Best rated hotel\n4. Exit");
            int choice = scanner.nextInt();
            switch (choice){
                case 1:
                    if (totalRate < minTotalRate) {
                        minTotalRate = totalRate;
                        cheapestHotel = hotel;
                    }
                    assert cheapestHotel != null;
                    System.out.println("Cheapest hotel for the given date range is: " + cheapestHotel.getName());
                    System.out.println(cheapestHotel);;
                    break;
                case 2:
                    if (hotel.getRating() > maxRating || (hotel.getRating() == maxRating && totalRate < minTotalRate)) {
                        minTotalRate = totalRate;
                        maxRating = hotel.getRating();
                    }
                    assert cheapestHotel != null;
                    System.out.println("Cheapest best rated hotel for the given date range is: " + cheapestHotel.getName());
                    System.out.println(cheapestHotel);
                    break;
                case 3:
                    if (hotel.getRating() > maxRating || (hotel.getRating() == maxRating && totalRate < minTotalRate)) {
                        maxRating = hotel.getRating();
                        minTotalRate = totalRate;
                        bestRatedHotel = hotel;
                    }
                    assert bestRatedHotel != null;
                    System.out.println("Best rated hotel for the given date range is: " + bestRatedHotel.getName());
                    System.out.println(bestRatedHotel);
                    break;
                case 4:
                    System.out.println("Thank you!");
                    break;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println("************   Welcome to Hotel Reservation Program   *************");
        System.out.println("---------------------------------------------------------------------");
        HotelReservationSystem reservationSystem = new HotelReservationSystem();

        Hotels lakewood = new Hotels("Lakewood", 3, 110, 90);
        reservationSystem.addHotel(lakewood);

        Hotels bridgewood = new Hotels("Bridgewood", 4, 160, 60);
        reservationSystem.addHotel(bridgewood);

        Hotels ridgewood = new Hotels("Ridgewood", 5, 220, 150);
        reservationSystem.addHotel(ridgewood);

        reservationSystem.hotels.forEach(System.out::println);
        System.out.println(reservationSystem.findCheapestHotel());
    }
}
