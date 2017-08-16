package models;

public class HappyHour {
    private String startTime;
    private String endTime;
    private Integer rating;
    private String restaurantName;
    private String address;
    private int id;

    public HappyHour(String start, String end, int rating, String restaurantName, String address){
        this.startTime = start;
        this.endTime = end;
        this.rating = rating;
        this.restaurantName = restaurantName;
        this.address = address;
    }
    //Getters
    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public Integer getRating() {
        return rating;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public String getAddress() {
        return address;
    }

    public int getId() {
        return id;
    }
    //Setters
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HappyHour happyHour = (HappyHour) o;

        if (!startTime.equals(happyHour.startTime)) return false;
        if (!endTime.equals(happyHour.endTime)) return false;
        if (!rating.equals(happyHour.rating)) return false;
        if (!restaurantName.equals(happyHour.restaurantName)) return false;
        return address.equals(happyHour.address);
    }

    @Override
    public int hashCode() {
        int result = startTime.hashCode();
        result = 31 * result + endTime.hashCode();
        result = 31 * result + rating.hashCode();
        result = 31 * result + restaurantName.hashCode();
        result = 31 * result + address.hashCode();
        return result;
    }
}
