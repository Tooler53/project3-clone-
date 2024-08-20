package sensor;

import org.knowm.xchart.*;
import org.knowm.xchart.style.Styler;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CategoryChart {
    public org.knowm.xchart.CategoryChart getChart(List<Double> values) {

        // generates Log data
        // Create Chart
        org.knowm.xchart.CategoryChart chart = new CategoryChartBuilder().width(800).height(600).title("Score Histogram").xAxisTitle("Score").yAxisTitle("Number").build();

        // Customize Chart
        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNW);
        List<Integer> range = IntStream.rangeClosed(0, values.size()-1)
                .boxed().collect(Collectors.toList());
        // Series
        chart.addSeries("test 1", range, values);

        return chart;
    }
}
