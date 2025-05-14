package co.edu.uniquindio.graficas;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    private VBox panelPrincipal;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Crear un gráfico de pastel
        crearGraficoPastel();

        // Crear un gráfico de barras
        crearGraficoBarras();

        // Crear un gráfico de líneas
        crearGraficoLineas();
    }

    private void crearGraficoPastel() {
        PieChart pieChart = new PieChart();
        pieChart.setTitle("Distribución de Mercado");
        pieChart.getData().add(new PieChart.Data("Android", 65));
        pieChart.getData().add(new PieChart.Data("iOS", 25));
        pieChart.getData().add(new PieChart.Data("Otros", 10));

        panelPrincipal.getChildren().add(pieChart);
    }

    private void crearGraficoBarras() {

        // BarChart
        CategoryAxis xAxisBar = new CategoryAxis();
        NumberAxis yAxisBar = new NumberAxis();
        BarChart<String, Number> barChart = new BarChart<>(xAxisBar, yAxisBar);
        barChart.setTitle("Población por Ciudad");

        XYChart.Series<String, Number> barSeries = new XYChart.Series<>();
        barSeries.setName("2025");
        barSeries.getData().add(new XYChart.Data<>("Bogotá", 8000));
        barSeries.getData().add(new XYChart.Data<>("Medellín", 6000));
        barSeries.getData().add(new XYChart.Data<>("Cali", 4000));

        barChart.getData().add(barSeries);

        panelPrincipal.getChildren().add(barChart);
    }

    private void crearGraficoLineas() {

        // LineChart
        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();
        LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Crecimiento Mensual");

        XYChart.Series<Number, Number> series1 = new XYChart.Series<>();
        series1.setName("Ventas");
        series1.getData().add(new XYChart.Data<>(1, 200));
        series1.getData().add(new XYChart.Data<>(2, 500));
        series1.getData().add(new XYChart.Data<>(3, 300));
        series1.getData().add(new XYChart.Data<>(4, 700));

        lineChart.getData().add(series1);

        panelPrincipal.getChildren().add(lineChart);
    }
}