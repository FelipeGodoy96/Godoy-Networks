package com.godoynetworks.Saferoom.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Room implements Serializable {

    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Name of the Hotel, Apartment, etc.
    private String name;

    // Title of the housing, example: King Room, Double Suite, Door 245, etc.
    private String title;
    @Column(columnDefinition = "TEXT")
    private String description;
    private Double rating;

    private Double monthlyFee;

    private String address;

    private int capacity;

    @OneToMany
//    @JoinColumn(name = "booking_id")
    private Set<Booking> bookings = new HashSet<>();

    @ManyToOne
    private Category category;

    // to implement "facilities"
    //@OneToMany
    //private Facility facilities = new HashSet<>();


    public Room() {
    }

    public Room(Long id, String name, String title, String description, Double rating, Double monthlyFee, String address, int capacity, Set<Booking> bookings, Category category) {
        this.id = id;
        this.name = name;
        this.title = title;
        this.description = description;
        this.rating = rating;
        this.monthlyFee = monthlyFee;
        this.address = address;
        this.capacity = capacity;
        this.bookings = bookings;
        this.category = category;
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

    public Double getMonthlyFee() {
        return monthlyFee;
    }

    public void setMonthlyFee(Double monthlyFee) {
        this.monthlyFee = monthlyFee;
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

    public Set<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(Set<Booking> bookings) {
        this.bookings = bookings;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return Objects.equals(id, room.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
