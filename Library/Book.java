import java.time.LocalDate;

class Book extends LibraryItem implements Checkoutable{
    private LocalDate dueDate;
    private boolean checkedOut;
    private static final int MAX_RENEWAL_COUNT = 1;
    private int renewalCount;

    public Book(String title, boolean isReferenceOnly, boolean isBestSeller) {
        super(title, isReferenceOnly, isBestSeller);
        setDueDate(isBestSeller);
        this.checkedOut = false;
        this.renewalCount = 0;
    }

    @Override
    public double getFinePerDay() {
        return 0.10; // $0.10 per day overdue fine for books
    }

    @Override
    public double getMaxFine() {
        return 10.00; // Maximum fine for books is $10.00
    }

    public boolean isOverdue() {
        if (this.isAvailable() && this.getDueDate() != null) {
            LocalDate currentDate = LocalDate.now();
            return currentDate.isAfter(this.getDueDate());
        }
        return false;
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
        int checkoutPeriod = isBestSeller ? 14 : 21; // Set to negative values to test overdue fines
        this.dueDate = LocalDate.now().plusDays(checkoutPeriod);
    }

    public void returnBook() {
        this.dueDate = null;
        this.setCheckedOut(false);
    }

    @Override
    public boolean canRenew() {
        return renewalCount < MAX_RENEWAL_COUNT;
    }

    public void incrementRenewalCount() {
        renewalCount++;
    }

    public void setCheckedOut(boolean checkedOut) {
        this.checkedOut = checkedOut;
    }

    @Override
    public boolean isAvailable() {
        return !checkedOut;
    }
}