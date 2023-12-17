package gal.usc.grei.cn.precios.domain;

import java.util.Objects;

public class PaymentDetails {
    private String paymentMethod;
    private String cardNumber;
    private String cardExpiryDate;
    private String cardCVC;
    private String fullName;

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardExpiryDate() {
        return cardExpiryDate;
    }

    public void setCardExpiryDate(String cardExpiryDate) {
        this.cardExpiryDate = cardExpiryDate;
    }

    public String getCardCVC() {
        return cardCVC;
    }

    public void setCardCVC(String cardCVC) {
        this.cardCVC = cardCVC;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaymentDetails that = (PaymentDetails) o;
        return Objects.equals(paymentMethod, that.paymentMethod) && Objects.equals(cardNumber, that.cardNumber) && Objects.equals(cardExpiryDate, that.cardExpiryDate) && Objects.equals(cardCVC, that.cardCVC) && Objects.equals(fullName, that.fullName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(paymentMethod, cardNumber, cardExpiryDate, cardCVC, fullName);
    }

    @Override
    public String toString() {
        return "PaymentDetails{" +
                "paymentMethod='" + paymentMethod + '\'' +
                ", cardNumber='" + cardNumber + '\'' +
                ", cardExpiryDate='" + cardExpiryDate + '\'' +
                ", cardCVC='" + cardCVC + '\'' +
                ", fullName='" + fullName + '\'' +
                '}';
    }
}
