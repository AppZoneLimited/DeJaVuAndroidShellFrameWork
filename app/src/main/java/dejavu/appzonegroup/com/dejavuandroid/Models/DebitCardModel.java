package dejavu.appzonegroup.com.dejavuandroid.Models;

/**
 * Created by CrowdStar on 2/12/2015.
 */
public class DebitCardModel {
    private int cardNumber;
    private int cvv;
    private int pin;
    private String expDate;

    public void setPin(int pin) {
        this.pin = pin;
    }

    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public void setExpDate(String expDate) {
        this.expDate = expDate;
    }

    public int getPin() {
        return pin;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public int getCvv() {
        return cvv;
    }

    public String getExpDate() {
        return expDate;
    }
}
