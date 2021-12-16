package edu.usfca.cs;

public class Entity {
    protected String name;
    protected String entityID;


    public Entity(){}

    public Entity(String name, String id){
        this.name = name;
        this.entityID = id;
    }





    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public String getEntityID() {return entityID;}

    public void setEntityID(String entityID) {this.entityID = entityID;}

    public String toString() {
        return "Name: " + this.name + " Entity ID: " + this.entityID +"\n";
    }

}
