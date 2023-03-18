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
        System.out.println("Enter the start date (ddMMMyyyy): ");
        String startDateString = scanner.next();
        LocalDate startDate = LocalDate.parse(startDateString, DateTimeFormatter.ofPattern("ddMMMyyyy"));

        System.out.println("Enter the end date (ddMMMyyyy): ");
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

        int minTotalRate = Integer.MAX_VALUE;
        Hotels cheapestHotel = null;

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
        }

        return cheapestHotel;
    }

    public static void main(String[] args) {
        System.out.println("Welcome to Hotel Reservation Program");
        HotelReservationSystem reservationSystem = new HotelReservationSystem();

        Hotels lakewood = new Hotels("Lakewood", 3, 110, 90);
        reservationSystem.addHotel(lakewood);

        Hotels bridgewood = new Hotels("Bridgewood", 4, 160, 60);
        reservationSystem.addHotel(bridgewood);

        Hotels ridgewood = new Hotels("Ridgewood", 5, 220, 150);
        reservationSystem.addHotel(ridgewood);

        System.out.println(reservationSystem.hotels);
        Hotels cheapestHotel = reservationSystem.findCheapestHotel();
        System.out.println(cheapestHotel);
    }
}
