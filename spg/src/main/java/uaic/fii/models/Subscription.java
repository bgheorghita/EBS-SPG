package uaic.fii.models;

import java.util.List;

public class Subscription {
    private final List<SubscriptionField> fieldList;

    public Subscription(List<SubscriptionField> fieldList) {
        this.fieldList = fieldList;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("Subscription {");
        for (SubscriptionField subscriptionField : fieldList) {
            s.append(subscriptionField);
            s.append(";");
        }
        s.deleteCharAt(s.length() - 1);
        s.append("}");
        return s.toString();
    }
}