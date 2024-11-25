package com.erick.prova1_erick_jhone_rodrigues_da_silva.model;

public class ItemList {
    private String name;
    private String associateWord;
    private int image;

    public ItemList(){

    }

    public ItemList(String data, String associateWord, int image){
        this.name = data;
        this.associateWord = associateWord;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getAssociateWord() {
        return associateWord;
    }

    public void setAssociateWord(String associateWord) {
        this.associateWord = associateWord;
    }
}




