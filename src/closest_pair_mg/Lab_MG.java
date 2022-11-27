/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package closest_pair_mg;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 *
 * @author migue
 */
public class Lab_MG {

    public static int conteo; //

    public Lab_MG() {
        conteo = 0;
    }

    public int GetComparacion() {
        return conteo;
    }

    public static double[] Brute_Force(int N, List<Closest_Pair_MG.Punto> coords, double dist_min) { // Encuentra el par más cercano a través del algoritmo de fuerza bruta.
        double distmin = dist_min;
        double[] vector = new double[3]; //Matriz donde se almacena dist_min
        vector[0] = distmin;
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                double d = distance(coords, i, j); //comparation 
                if (d < distmin) {
                    conteo++;
                    distmin = d;
                    vector[0] = d;
                    vector[1] = coords.Get(i).pos;
                    vector[2] = coords.Get(j).pos;
                } else {
                    conteo++;
                }
            }
        }
        return vector;
    }
    // Muestra un conjunto de coordenadas.
    public static void Printc(List<int[]> coords) { 
        for (int i = 0; i < coords.size(); i++) {
            System.out.println("x: " + coords.get(i)[0] + " y: " + coords.get(i)[1] + " pos: " + coords.get(i)[2]);
        }
    }

    public static ArrayList<Closest_Pair_MG.Punto> FindCandidates(List<Closest_Pair_MG.Punto> coords, double min) {
        ArrayList<Closest_Pair_MG.Punto> cand;
        cand = new ArrayList<Closest_Pair_MG.Punto>();
        int i = 0;
        while (i < coords.size() / 2) { //Comparations 
            if (Math.abs(coords.Get(i).x - coords.Get(coords.size() / 2).x) < min && Math.abs(coords.Get(i).y - coords.Get(coords.size() / 2).y) < min) {
                conteo++;
                cand.add(coords.Get(i)); 
                i++;
            } else {
                conteo++;
                i = coords.size() / 2;
            }
        }
        while (i < coords.size()) { //Comparamos las primeras coords
            if (Math.abs(coords.Get(i).x - coords.Get(coords.size() / 2 - 1).x) < min && Math.abs(coords.Get(i).y - coords.Get(coords.size() / 2 - 1).y) < min) {
                conteo++;
                cand.add(coords.Get(i));
                i++;
            } else {
                conteo++;
                i = coords.size();
            }
        }
        return cand;
    }

    public static double[] PC(int N, List<Closest_Pair_MG.Punto> x, double mdis) {
        // Encuentra el closest pair point 
        if (N > 3) { 
            double[] g1 = new double[3];
            double[] g2 = new double[3];
            int offset = 0;
            conteo++;
            if (N % 2 == 1) {
                offset = 1;
            }

            g1 = PC(N / 2, x.SubList(0, N / 2), mdis);
            g2 = PC(N / 2 + offset, x.SubList(N / 2, N), mdis);
            double[] g = new double[3];
            if (g1[0] < g2[0]) {
                g = g1;
                conteo++;
            } else {
                conteo++;
                g = g2;
            }
            ArrayList<Closest_Pair_MG.Punto> candidatos; //candidatos
            candidatos = new ArrayList<Closest_Pair_MG.Punto>();
            candidatos = FindCandidates(x, g[0]);
            //Algoritmo de brute force
            if (candidatos.size() > 1) {
                conteo++;
                g1 = Brute_Force(candidatos.size(), candidatos, mdis);
                if (g1[0] < g[0]) {
                    conteo++;
                    return g1;
                } else {
                    conteo++;
                    return g;
                }
            } else {
                conteo++;
                return g;
            }
        } else {
            conteo++;
            double[] vec;
            vec = new double[3];
            return Brute_Force(N, x, mdis); 
        }
    }

    public static double distancia(List<Closest_Pair_MG.Punto> coords, int i, int j) {
        // calcula la distancia 
        int x1 = coords.Get(i).x;
        int x2 = coords.Get(j).x;
        int y1 = coords.Get(i).y;
        int y2 = coords.Get(j).y;
        double d = Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
        return d;
    }

    public static ArrayList<Closest_Pair_MG.Punto> Initialize(int tam) { //conjunto de coordenadas random.
        ArrayList list;
        list = new ArrayList<Closest_Pair_MG.Punto>();
        Random r = new Random();
        for (int i = 0; i < tam; i++) {
            Closest_Pair_MG.Punto temp = new Closest_Pair_MG.Punto(r.nextInt(10000), r.nextInt(10000), i);
            list.add(temp);
        }

        return list;
    }

    public static void main(String[] args) {

        Closest_Pair_MG.ComplejidadDeTiempo tca = new Closest_Pair_MG.ComplejidadDeTiempo();
        tca.Analisis(6000000);

    }

}
    
