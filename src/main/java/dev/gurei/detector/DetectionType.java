package dev.gurei.detector;

public enum DetectionType {
    SB("SB");

    private final String display;

    DetectionType(String display) {
        this.display = display;
    }

    public String display() {
        return display;
    }
}
