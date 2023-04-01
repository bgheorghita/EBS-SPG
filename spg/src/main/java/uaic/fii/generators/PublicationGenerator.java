package uaic.fii.generators;

import uaic.fii.models.Publication;

public class PublicationGenerator implements Runnable {
    private final int start;
    private final int end;

    public PublicationGenerator(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public void run() {
        for (int i = start; i < end; i++) {
            Publication publication = new Publication()
                    .withStationId(NumberGenerator.getRandomInt(1, 10))
                    .withCity(CityGenerator.getRandomCity())
                    .withTemp(NumberGenerator.getRandomInt(-20, 40))
                    .withRain(NumberGenerator.getRandomDouble(0.0, 10.0))
                    .withWind(NumberGenerator.getRandomInt(0, 100))
                    .withDirection(DirectionGenerator.getRandomDirection())
                    .withDate(DateGenerator.getRandomDate())
                    .build();

            // System.out.println(Thread.currentThread().getName() + ": " + publication.toString());
        }
    }
}