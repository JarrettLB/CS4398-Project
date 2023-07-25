import java.time.LocalDate;

class AudioVideoMaterial extends LibraryItem implements Checkoutable {
    private LocalDate dueDate;
    private boolean checkedOut;

    public AudioVideoMaterial(String title, boolean isReferenceOnly, boolean isBestSeller) {
        super(title, isReferenceOnly, isBestSeller);
        setDueDate(isBestSeller);
        this.checkedOut = false;
    }

    @Override
    public double getFinePerDay() {
        return 0.10; // $0.10 per day overdue fine for audio/video materials
    }

    @Override
    public double getMaxFine() {
        return 20.00; // Maximum fine for audio/video materials is $20.00
    }

    @Override
    public int getCheckoutPeriod() {
        return 14; // Default checkout period for audio/video materials
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    @Override
    public void setDueDate(boolean isBestSeller) {
        // Set the due date for audio/video materials at 14 days
        int checkoutPeriod = 14;
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





