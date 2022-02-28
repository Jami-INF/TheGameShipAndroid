package com.iut.thegameship.model.entity.componement;

public class Sprite extends  Componement{

    private String sprite;
    public String getSprite() { return sprite; }
    public void setSprite(String sprite) { this.sprite = sprite; }

    private boolean visible ;
    public boolean getVisible() { return visible; }
    public void setVisible(boolean visible) { this.visible = visible; }

    public Sprite(String sprite){
        super(EComponementType.Sprite);
        setSprite(sprite);
    }

    public Sprite(boolean visible){
        super(EComponementType.Sprite);
        setVisible(true);
        setSprite(null);
    }

    public static Sprite cast(IHasComponements e){
        return (Sprite) e.getComponement(EComponementType.Sprite);
    }
}
