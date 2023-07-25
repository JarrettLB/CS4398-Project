import java.time.LocalDate;

class Book extends LibraryItem implements Checkoutable{
    private LocalDate dueDate;
    private boolean checkedOut;

    public Book(String title, boolean isReferenceOnly, boolean isBestSeller) {
        super(title, isReferenceOnly, isBestSeller);
        setDueDate(isBestSeller);
        this.checkedOut = false;
    }

    @Override
    public double getFinePerDay() {
        return 0.10; // $0.10 per day overdue fine for books
    }

    @Override
    public double getMaxFine() {
        return 10.00; // Maximum fine for books is $10.00
    }
    
    @Override
    public int getCheckoutPeriod() {
        return isBestSeller() ? 14 : 21;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    @Override
    public void setDueDate(boolean isBestSeller) {
        int checkoutPeriod = isBestSeller ? 14 : 21;
        this.dueDate = LocalDate.now().plusDays(checkoutPeriod);
    }

    @Override
    public boolean canRenew() {
        return true;
    }

    public void setCheckedOut(boolean checkedOut) {
        this.checkedOut = checkedOut;
    }

    @Override
    public boolean isAvailable() {
        return !checkedOut;
    }
}