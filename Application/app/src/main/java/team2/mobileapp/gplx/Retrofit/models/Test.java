package team2.mobileapp.gplx.Retrofit.models;

import java.util.List;

public class Test {
    private String  id;
    private String  Name;
    private Boolean  Status;
    private int  Quantity;
    private int  WrongAns;
    private int  RightAns;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Boolean getStatus() {
        return Status;
    }

    public void setStatus(Boolean status) {
        Status = status;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public int getWrongAns() {
        return WrongAns;
    }

    public void setWrongAns(int wrongAns) {
        WrongAns = wrongAns;
    }

    public int getRightAns() {
        return RightAns;
    }

    public void setRightAns(int rightAns) {
        RightAns = rightAns;
    }




}
