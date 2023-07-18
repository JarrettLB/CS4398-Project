class Book extends LibraryItem {
    public Book(String title, boolean isReferenceOnly, boolean isBestSeller) {
        super(title, isReferenceOnly, isBestSeller);
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

    @Override
    public boolean canRenew() {
        return true;
    }
}