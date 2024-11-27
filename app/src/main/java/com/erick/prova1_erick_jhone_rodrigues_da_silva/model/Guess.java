package com.erick.prova1_erick_jhone_rodrigues_da_silva.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Guess implements Parcelable {
    private int inputedAttemp;
    private int correctValue;

    public Guess(int value, int correctValue) {
        this.inputedAttemp = value;
        this.correctValue = correctValue;
    }

    protected Guess(Parcel in) {
        inputedAttemp = in.readInt();
        correctValue = in.readInt();
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

    public int getInputedAttemp() {
        return inputedAttemp;
    }

    public int getCorrectValue() {
        return correctValue;
    }

    public void setInputedAttemp(int inputedAttemp) {
        this.inputedAttemp = inputedAttemp;
    }

    public void setCorrectValue(int correctValue) {
        this.correctValue = correctValue;
    }

    public boolean isResultCorrect() {
        return inputedAttemp == correctValue;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(inputedAttemp);
        parcel.writeInt(correctValue);
    }
}