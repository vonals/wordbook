package com.example.wordbook;

public class Cat {
    private String name;
    private int imageId;
    private  String sentence;
    private String meaning;
    public Cat(String name, int imageId,String meaning,String sentence){
        this.name=name;
        this.imageId=imageId;
        this.sentence=sentence;
        this.meaning=meaning;
    }
    public String getName(){
        return name;
    }
    public int getImageId(){
        return imageId;
    }
    public String getSentence(){return sentence;}
    public String getMeaning(){return meaning;}
}
