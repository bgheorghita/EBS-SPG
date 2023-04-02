package uaic.fii.models;

public class PublicationField {
    private final String key;
    private final String value;

    public PublicationField(String key, String value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String toString() {
        return "(" + key + "," + value + ")";
    }
}
