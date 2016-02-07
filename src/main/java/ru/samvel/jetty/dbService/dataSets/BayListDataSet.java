package ru.samvel.jetty.dbService.dataSets;


@SuppressWarnings("UnusedDeclaration")
public class BayListDataSet {
    private long id;
    private String name;
    private String amount;

    public BayListDataSet(long id, String name, String amount) {
        this.id = id;
        this.name = name;
        this.amount = amount;
    }

    public long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getAmount() {
        return amount;
    }


    @Override
    public String toString() {
        return "BayListDataSet {" + "id = " + id + ", name = '" + name + "', amount = " + amount + "'}";
    }
}
