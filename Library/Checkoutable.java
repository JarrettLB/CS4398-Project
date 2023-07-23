public interface Checkoutable {
    double getFinePerDay();
    double getMaxFine();
    int getCheckoutPeriod();
    void setDueDate(boolean isBestSeller);
    boolean canRenew();
    void setCheckedOut(boolean checkedOut);
    boolean isAvailable();
}
