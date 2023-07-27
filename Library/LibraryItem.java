import java.time.LocalDate;

abstract class LibraryItem {
    private String title;
    private boolean isReferenceOnly;
    private boolean isBestSeller;
    private LocalDate dueDate;
    private boolean isItemRequested;
    private int renewalCount;

    public LibraryItem(String title, boolean isReferenceOnly, boolean isBestSeller) {
        this.title = title;
        this.isReferenceOnly = isReferenceOnly;
        this.isBestSeller = isBestSeller;
        this.isItemRequested = false;
    }

    public String getTitle() {
        return title;
    }

    public boolean isReferenceOnly() {
        return isReferenceOnly;
    }

    public boolean isBestSeller() {
        return isBestSeller;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public abstract void setDueDate(boolean isBestSeller);

    public abstract void setCheckedOut(boolean checkedOut);

    public abstract int getCheckoutPeriod();

    public abstract double getFinePerDay();

    public abstract double getMaxFine();

    public boolean canRenew(){
        return true;
    }

    public int getRenewalCount() {
        return renewalCount;
    }

    public void setRenewalCount(int renewalCount) {
        this.renewalCount = renewalCount;
    }

    public void incrementRenewalCount() {
        this.renewalCount++;
    }

    protected boolean isItemRequested() {
        return isItemRequested;
    }

    public void toggleRequest(boolean isItemRequested) {
        this.isItemRequested = isItemRequested;
    }

    public boolean isAvailable() {
        return dueDate == null; // If the due date is null, the item is available
    }
}