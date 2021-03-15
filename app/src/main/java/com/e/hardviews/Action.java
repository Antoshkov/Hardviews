package com.e.hardviews;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Action implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String nameAction;
    private boolean isCreator;
    private int iconAction, iconActionReverse, amountPerDay;
    private int countPressedTimes = 0;


    public Action(String nameAction, int iconAction, int iconActionReverse, int amountPerDay){
        this.iconAction = iconAction;
        this.iconActionReverse = iconActionReverse;
        this.nameAction = nameAction;
        this.amountPerDay = amountPerDay;
    }

    protected Action(Parcel in) {
        id = in.readInt();
        nameAction = in.readString();
        isCreator = in.readByte() != 0;
        iconAction = in.readInt();
        iconActionReverse = in.readInt();
        amountPerDay = in.readInt();
        countPressedTimes = in.readInt();
    }

    public static final Creator<Action> CREATOR = new Creator<Action>() {
        @Override
        public Action createFromParcel(Parcel in) {
            return new Action(in);
        }

        @Override
        public Action[] newArray(int size) {
            return new Action[size];
        }
    };

    public void addPressedTimes(){
        countPressedTimes++;
    }

    public void setIconAction(int iconAction) {
        this.iconAction = iconAction;
    }

    public void setIconActionReverse(int iconActionReverse) {
        this.iconActionReverse = iconActionReverse;
    }

    public void setNameAction(String nameAction) {
        this.nameAction = nameAction;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAmountPerDay(int amountPerDay) {
        this.amountPerDay = amountPerDay;
    }

    public void setCountPressedTimes(int countPressedTimes) {
        this.countPressedTimes = countPressedTimes;
    }

    public void setCreator(boolean creator) {
        isCreator = creator;
    }

    public int getIconAction() {
        return iconAction;
    }

    public int getIconActionReverse() {
        return iconActionReverse;
    }

    public String getNameAction() {
        return nameAction;
    }

    public int getId() {
        return id;
    }

    public int getAmountPerDay() {
        return amountPerDay;
    }

    public int getCountPressedTimes() {
        return countPressedTimes;
    }

    public boolean isCreator() {
        return isCreator;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(nameAction);
        parcel.writeByte((byte) (isCreator ? 1 : 0));
        parcel.writeInt(iconAction);
        parcel.writeInt(iconActionReverse);
        parcel.writeInt(amountPerDay);
        parcel.writeInt(countPressedTimes);
    }
}
