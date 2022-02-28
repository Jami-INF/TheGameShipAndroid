package com.iut.thegameship.model.entity.componement;

public class Location extends Componement { //N'est pas sencé être un compoenent

    private double x;
    public double getX() {
        return x;
    }
    public void setX(double x) {
        this.x = x;
    }

    private double y;
    public double getY() {
        return y;
    }
    public void setY(double y) {
        this.y = y;
    }

    private double width;
    public double getWidth() {
        return width;
    }
    public void setWidth(double width) {
        this.width = width;
    }

    private double height;
    public double getHeight() {
        return height;
    }
    public void setHeight(double height) {
        this.height = height;
    }

    public Location(double x, double y, double height, double width) {
        super(EComponementType.Location);
        setX(x);
        setY(y);
        setHeight(height);
        setWidth(width);
    }

    public static Location cast(IHasComponements e) {
        /*if(!e.isTypeOf(EComponementType.Life)){
            System.err.println("Impossible d'ajouter l'entité : \""+e.getName()+"\" car elle n'implémente pas Location");
        }*/
        return (Location) e.getComponement(EComponementType.Location);
    }
}
