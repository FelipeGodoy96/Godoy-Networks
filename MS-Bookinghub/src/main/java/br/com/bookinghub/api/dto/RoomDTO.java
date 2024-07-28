package br.com.bookinghub.api.dto;

import br.com.bookinghub.api.model.Room;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class HousingDTO implements Serializable {

    private static final Long serialVersionUID = 1L;

    private Long id;

    //Name of the Hotel, Apartment, etc.
    private String name;

    // Title of the housing, example: King Room, Double Suite, Door 245, etc.
    private String title;
    private String description;
    private Double rating;

    private Double dailyCost;

    private String address;

    private int capacity;

    private Set<BookingDTO> bookings = new HashSet<>();


    public HousingDTO() {
    }


    public HousingDTO(Long id, String name, String title, String description, Double rating, Double dailyCost, String address, int capacity, Set<BookingDTO> bookings) {
        this.id = id;
        this.name = name;
        this.title = title;
        this.description = description;
        this.rating = rating;
        this.dailyCost = dailyCost;
        this.address = address;
        this.capacity = capacity;
        this.bookings = bookings;
    }

    public HousingDTO(Room room) {
        id = room.getId();
        name = room.getName();
        title = room.getTitle();
        description = room.getDescription();
        rating = room.getRating();
        dailyCost = room.getDailyCost();
        address = room.getAddress();
        capacity = room.getCapacity();
        room.getBookings().forEach(booking -> this.bookings.add(new BookingDTO (booking)));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Double getDailyCost() {
        return dailyCost;
    }

    public void setDailyCost(Double dailyCost) {
        this.dailyCost = dailyCost;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Set<BookingDTO> getBookings() {
        return bookings;
    }

    public void setBookings(Set<BookingDTO> bookings) {
        this.bookings = bookings;
    }
}
