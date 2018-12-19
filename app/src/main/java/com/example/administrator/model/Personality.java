package com.example.administrator.model;

public class Personality {
    private String Personalitytext;
    private int Personalityview;
    public Personality(String personalitytext, int personalityview){
        Personalitytext=personalitytext;
        Personalityview=personalityview;
    }
    public String getPersonalitytext(){
        return Personalitytext;
    }

    public int getPersonalityview() {
        return Personalityview;
    }

    public void setPersonalitytext(String personalitytext) {
        Personalitytext = personalitytext;
    }

    public void setPersonalityview(int personalityview) {
        Personalityview = personalityview;
    }
}
