/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package closest_pair_mg;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;


/**
 *
 * @author migue
 */
class ComplejidadDeTiempo {

    private static void Crear(String name) // Creamos un nuevo archivo.
    {
        try {
            String archivo_nombre = (name);

            File f = new File(archivo_nombre);

            String msg = "creando archivo `" + archivo_nombre + "' ... ";
            f.createNewFile();

        } catch (IOException err) {
        }

    }

    private static void Escribir(String name, int tm, ArrayList<Integer> is, ArrayList<Integer> comparisons, ArrayList<Integer> runtimes) 
    {
        try {

            String filename = (name);

            PrintWriter out;
            out = new PrintWriter(filename);
            String fmt = ("%10s %10s %10s\n");
            for (int i = 0; i < tm; ++i) {
                out.printf(fmt, is.get(i), comparisons.get(i), runtimes.get(i));
            }

            out.close();
        } catch (FileNotFoundException err) {
        }

    }

    public void Analisis(int ii) { 
        ArrayList<Integer> runtimes = new ArrayList<>();
        ArrayList<Integer> is = new ArrayList<>();
        ArrayList<Integer> comparisons = new ArrayList<>(); 
        for (int i = 1000; i < ii; i = i * 3 / 2) {
            Closest_Pair_MG pct = new Closest_Pair_MG();
            System.out.println(i);
            long total = 0;
            int count;
            ArrayList<Point> coords = pct.Initialize(i); 
            
            for (int j = 0; j < 256; j++) {
                long startTime = System.nanoTime();
                pct.ParCercano(i, coords, 999999999);
                long endTime = System.nanoTime();
                long totalTime = endTime - startTime; 
                total = total + totalTime;
            }
            count = pct.getComparacion();
            count = count / 256; //Encontrar el average case, tiempo de ejecución, cantidad de coordenadas.
            total = total / 256; 
            runtimes.add((int) total);
            is.add(i); 
            comparisons.add(count);
        }
        Crear("Tiempos.txt");
        Escribir("Tiempos.txt", is.size(), is, comparisons, runtimes);
    }
}

class Point {  

    int x;
    int y;
    int pos;

    public Point(int xx, int yy, int poss) {
        this.x = xx;
        this.y = yy;
        this.pos = poss;
    }
}


