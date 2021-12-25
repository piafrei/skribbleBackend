package com.montagsmaler.backend.actionHandling.actionInput;

public class DrawAction extends Action {
    private double payloadCapacity;

    public DrawAction(ActionType actionType,double payloadCapacity) {
        super(actionType);
        this.payloadCapacity = payloadCapacity;
    }

    public void setPayloadCapacity(double payloadCapacity) {
        this.payloadCapacity = payloadCapacity;
    }

    public double getPayloadCapacity() {
        return payloadCapacity;
    }

    @Override
    public ActionType getActionType() {
        return ActionType.draw;
    }
}
