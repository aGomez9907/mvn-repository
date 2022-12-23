package com.solvd.laba.rooms;

import com.solvd.laba.exceptions.InvalidRoomNumberException;

public abstract class HospitalRoom{
    private int numberOfBeds;
    private int floor;
    private int roomNumber;

    public HospitalRoom() {

    }


    public HospitalRoom(int numberOfBeds, int floor, int roomNumber) throws InvalidRoomNumberException {
        if (roomNumber <= 0) {
            throw new InvalidRoomNumberException("Exception: Room number must be higher than zero.");
        }

        this.numberOfBeds = numberOfBeds;
        this.floor = floor;
        this.roomNumber = roomNumber;
    }

    public int getNumberOfBeds() {
        return numberOfBeds;
    }

    public void setNumberOfBeds(int numberOfBeds) {
        this.numberOfBeds = numberOfBeds;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }
}
