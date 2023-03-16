package hotelreservationsystem;

import java.util.ArrayList;
import java.util.List;

public class HotelReservationSystem {
    private List<Hotels> hotels;

    public HotelReservationSystem() {
        hotels = new ArrayList<>();
    }

    //Adds hotel to the list
    public void addHotel(Hotels hotel) {
        hotels.add(hotel);
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
    }
}
