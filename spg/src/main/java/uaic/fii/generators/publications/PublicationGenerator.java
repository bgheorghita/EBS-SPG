package uaic.fii.generators.publications;

import uaic.fii.generators.CityGenerator;
import uaic.fii.generators.DateGenerator;
import uaic.fii.generators.DirectionGenerator;
import uaic.fii.generators.NumberGenerator;
import uaic.fii.models.Publication;
import uaic.fii.models.PublicationField;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class PublicationGenerator implements Callable<List<Publication>> {
    private final int start;
    private final int end;

    public PublicationGenerator(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public List<Publication> call() {
        List<Publication> publications = new ArrayList<>();
        for (int i = start; i < end; i++) {
            PublicationField stationIdField = new PublicationField("stationId", String.valueOf(NumberGenerator.getRandomInt(1, 10)));
            PublicationField cityField = new PublicationField("city", CityGenerator.getRandomCity());
            PublicationField tempField = new PublicationField("temp", String.valueOf(NumberGenerator.getRandomInt(-20, 40)));
            PublicationField rainField = new PublicationField("rain", String.valueOf(NumberGenerator.getRandomDouble(0.0, 10.0)));
            PublicationField windField = new PublicationField("wind", String.valueOf(NumberGenerator.getRandomInt(0, 100)));
            PublicationField directionField = new PublicationField("direction", DirectionGenerator.getRandomDirection());
            PublicationField dateField = new PublicationField("date", DateGenerator.getRandomDate());

            List<PublicationField> publicationFieldList = new ArrayList<>();
            publicationFieldList.add(stationIdField);
            publicationFieldList.add(cityField);
            publicationFieldList.add(tempField);
            publicationFieldList.add(rainField);
            publicationFieldList.add(windField);
            publicationFieldList.add(directionField);
            publicationFieldList.add(dateField);

            Publication publication = new Publication(publicationFieldList);
            publications.add(publication);
//            System.out.println(Thread.currentThread().getName() + ": " + publication);
        }
        return publications;
    }
}