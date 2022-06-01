package team2.mobileapp.gplx.Retrofit.dto;

public class QuestionDetails {

    private String Name;
    private boolean Status;
    private int Quantity;
    private int WrongAns;
    private int RightAns;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public boolean isStatus() {
        return Status;
    }

    public void setStatus(boolean status) {
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

    @Override
    public String toString() {
        return "Test{" +
                "Name='" + Name + '\'' +
                ", Status=" + Status +
                ", Quantity=" + Quantity +
                ", WrongAns=" + WrongAns +
                ", RightAns=" + RightAns +
                '}';
    }
}
