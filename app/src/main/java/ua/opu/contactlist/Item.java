package ua.opu.contactlist;

public class Item {

    private String name;
    private String limit;
    private String amount;

    public Item(String name, String limit, String amount) {
        this.name = name;
        this.limit = limit;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

}