package com.erick.prova1_erick_jhone_rodrigues_da_silva.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Player implements Parcelable {
    private String name;
    private String birthDate;
    private int score;

    public Player(String name, String birthDate, int score) {
        this.name = name;
        this.birthDate = birthDate;
        this.score = score;
    }

    protected Player(Parcel in) {
        name = in.readString();
        birthDate = in.readString();
        score = in.readInt();
    }

    public static final Creator<Player> CREATOR = new Creator<Player>() {
        @Override
        public Player createFromParcel(Parcel in) {
            return new Player(in);
        }

        @Override
        public Player[] newArray(int size) {
            return new Player[size];
        }
    };

    public String getName() {
        return name;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(birthDate);
        parcel.writeInt(score);
    }

    @Override
    public int describeContents() {
        return 0;
    }
}