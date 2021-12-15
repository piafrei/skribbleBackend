package com.montagsmaler.backend.controller.ActionInput;

import com.montagsmaler.backend.controller.Action;

public class ChatAction extends Action {
    private int seatingCapacity;
    private double topSpeed;

    public ChatAction(ActionType actionType, int seatingCapacity, double topSpeed) {
        super(actionType);
        this.seatingCapacity = seatingCapacity;
        this.topSpeed = topSpeed;
    }

    public int getSeatingCapacity() {
        return seatingCapacity;
    }

    public void setSeatingCapacity(int seatingCapacity) {
        this.seatingCapacity = seatingCapacity;
    }

    public double getTopSpeed() {
        return topSpeed;
    }

    public void setTopSpeed(double topSpeed) {
        this.topSpeed = topSpeed;
    }

    @Override
    public ActionType getActionType() {
        return ActionType.chat;
    }
}
