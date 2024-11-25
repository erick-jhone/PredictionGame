package com.erick.prova1_erick_jhone_rodrigues_da_silva.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Guess implements Parcelable {
    private int value;
    private int attempt;

    public Guess(int value, int attempt) {
        this.value = value;
        this.attempt = attempt;
    }

    protected Guess(Parcel in) {
        value = in.readInt();
        attempt = in.readInt();
    }

    public static final Creator<Guess> CREATOR = new Creator<Guess>() {
        @Override
        public Guess createFromParcel(Parcel in) {
            return new Guess(in);
        }

        @Override
        public Guess[] newArray(int size) {
            return new Guess[size];
        }
    };

    public int getValue() {
        return value;
    }

    public int getAttempt() {
        return attempt;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setAttempt(int attempt) {
        this.attempt = attempt;
    }

    public boolean isResultCorrect() {
        return value == attempt;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(value);
        parcel.writeInt(attempt);
    }
}