package com.mycompany.practicaixmongo.Model;

public class Weakness {
    private String element;
    private int stars;

    public Weakness() {}

    public Weakness(String element, int stars) {
        this.element = element;
        this.stars = stars;
    }

    public String getElement() { return element; }
    public void setElement(String element) { this.element = element; }

    public int getStars() { return stars; }
    public void setStars(int stars) { this.stars = stars; }
}
