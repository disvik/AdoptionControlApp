package ua.opu.contactlist;

public class Item {

    private String data;
    private String weight;
    private String height;

    public Item(String data, String weight, String height) {
        this.data = data;
        this.weight = weight;
        this.height = height;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

}