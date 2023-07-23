
class AudioVideoMaterial extends LibraryItem {
    public AudioVideoMaterial(String title, boolean isReferenceOnly, boolean isBestSeller) {
        super(title, isReferenceOnly, isBestSeller);
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
        return 14;
    }

    @Override
    public boolean canRenew() {
        return true;
    }
}
