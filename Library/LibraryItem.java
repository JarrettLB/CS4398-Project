import java.time.LocalDate;

abstract class LibraryItem {
    private String title;
    private boolean isReferenceOnly;
    private boolean isBestSeller;
    private LocalDate dueDate;

    public LibraryItem(String title, boolean isReferenceOnly, boolean isBestSeller) {
        this.title = title;
        this.isReferenceOnly = isReferenceOnly;
        this.isBestSeller = isBestSeller;
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

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public abstract int getCheckoutPeriod();

    public abstract double getFinePerDay();

    public abstract double getMaxFine();

    public boolean canRenew() {
        return true;
    }
}