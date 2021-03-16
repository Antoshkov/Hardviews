package com.e.hardviews;

import android.os.Parcel;
import android.os.Parcelable;

public class DefaultAction implements Parcelable {



    private int iconAction, iconActionReverse, actionType;
    private String nameAction;

    public DefaultAction(String nameAction, int iconAction, int iconActionReverse, int actionType){
        this.actionType = actionType;
        this.iconAction = iconAction;
        this.iconActionReverse = iconActionReverse;
        this.nameAction = nameAction;
    }

    protected DefaultAction(Parcel in) {
        iconAction = in.readInt();
        iconActionReverse = in.readInt();
        actionType = in.readInt();
        nameAction = in.readString();
    }

    public static final Creator<DefaultAction> CREATOR = new Creator<DefaultAction>() {
        @Override
        public DefaultAction createFromParcel(Parcel in) {
            return new DefaultAction(in);
        }

        @Override
        public DefaultAction[] newArray(int size) {
            return new DefaultAction[size];
        }
    };

    public int getIconActionReverse() {
        return iconActionReverse;
    }

    public String getNameAction() {
        return nameAction;
    }

    public int getIconAction() {
        return iconAction;
    }

    public int getActionType() {
        return actionType;
    }

    public void setIconActionReverse(int iconActionReverse) {
        this.iconActionReverse = iconActionReverse;
    }

    public void setNameAction(String nameAction) {
        this.nameAction = nameAction;
    }

    public void setIconAction(int iconAction) {
        this.iconAction = iconAction;
    }

    public void setActionType(int actionType) {
        this.actionType = actionType;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(iconAction);
        parcel.writeInt(iconActionReverse);
        parcel.writeInt(actionType);
        parcel.writeString(nameAction);
    }
}
