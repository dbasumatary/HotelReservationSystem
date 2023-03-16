package hotelreservationsystem;

public class Hotels {
    private String name;
    private int rating;
    private int weekdayRate;
    private int weekendRate;


    //parameterized constructor
    public Hotels(String name, int rating, int weekdayRate, int weekendRate) {
        this.name = name;
        this.rating = rating;
        this.weekdayRate = weekdayRate;
        this.weekendRate = weekendRate;
    }

    public String getName() {
        return name;
    }

    public int getRating() {
        return rating;
    }

    public int getWeekdayRate() {
        return weekdayRate;
    }

    public int getWeekendRate() {
        return weekendRate;
    }

    @Override
    public String toString() {
        return "Hotel{" + "Name = " + name  + ", Rating = " + rating + ", WeekDay rate = " + weekdayRate + ", WeekEnd rate = " + weekendRate + "}\n";
    }
}
