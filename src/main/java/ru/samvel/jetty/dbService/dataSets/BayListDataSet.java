package ru.samvel.jetty.dbService.dataSets;


@SuppressWarnings("UnusedDeclaration")
public class BayListDataSet {
    private long id;
    private String bayName;
    private String amount;

    public BayListDataSet(long id, String bayName, String amount) {
        this.id = id;
        this.bayName = bayName;
        this.amount = amount;
    }

    public long getId() {
        return id;
    }
    public String getName() {
        return bayName;
    }
    public String getAmount() {
        return amount;
    }


    @Override
    public String toString() {
        return "BayListDataSet {" + "id = " + id + ", bayName = '" + bayName + "', amount = " + amount + "'}";
    }
}
