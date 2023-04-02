package uaic.fii.models;

import java.util.List;

public class Publication {
    private final List<PublicationField> publicationFieldList;

    public Publication(List<PublicationField> publicationFieldList) {
        this.publicationFieldList = publicationFieldList;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("Publication: {");
        for(PublicationField publicationField : publicationFieldList){
            s.append(publicationField);
            s.append(";");
        }
        s.deleteCharAt(s.length()-1);
        s.append("}");
        return s.toString();
    }
}
