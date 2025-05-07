package co.edu.uniquindio.concurso.modelo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Getter
@Setter
public class Concurso {

    private String id;
    private String nombre;
    private List<Foto> fotos;
    private List<String> categorias;

    @Builder
    public Concurso(String id, String nombre, List<String> categorias) {
        this.id = id;
        this.nombre = nombre;
        this.categorias = categorias;
        this.fotos = new ArrayList<>();
    }

    public void agregarFoto(Foto foto) {
        fotos.add(foto);
    }

    public void determinarGanadores() {

        List<Foto> listaPrimerPuesto = new ArrayList<>();
        List<Foto> listaSegundoPuesto = new ArrayList<>();
        List<Foto> listaTercerPuesto = new ArrayList<>();

        for (Foto foto : fotos) {
            float promedio = foto.calcularPromedio();
            if (promedio >= 95 && promedio <= 100) {
                listaPrimerPuesto.add(foto);
            } else if (promedio >= 90) {
                listaSegundoPuesto.add(foto);
            } else if (promedio >= 85) {
                listaTercerPuesto.add(foto);
            }
        }

        repartirPremio(listaPrimerPuesto, 1000.0f);
        repartirPremio(listaSegundoPuesto, 500.0f);
        repartirPremio(listaTercerPuesto, 250.0f);

    }

    public void repartirPremio(List<Foto> listaGanadores, float premio) {

        float premioIndividual = premio / listaGanadores.size();
        for (Foto foto : listaGanadores) {
            foto.getFotografo().sumarPremio(premioIndividual);
        }

    }

    public void darPremioSorpresa(float premio) {

        Random random = new Random();

        int indice = random.nextInt(categorias.size());
        String categoria = categorias.get(indice);

        List<Foto> candidatas = fotos.stream()
                .filter(f -> f.getCategoria().equals(categoria))
                .toList();

        if (!candidatas.isEmpty()) {
            int indiceFoto = random.nextInt(candidatas.size());
            Foto premiada = candidatas.get(indiceFoto);
            premiada.getFotografo().sumarPremio(premio);
        }

    }

    @Override
    public String toString() {
        return "Concurso{" +
                "nombre='" + nombre + '\'' +
                '}';
    }
}
