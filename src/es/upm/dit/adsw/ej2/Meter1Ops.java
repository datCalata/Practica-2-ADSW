package es.upm.dit.adsw.ej2;

import java.util.Random;

/**
 * Medidor de operaciones realizadas.
 * Created by jose on 07-Dec-15.
 */
@SuppressWarnings("Duplicates")
public class Meter1Ops {
    private static final Random random = new Random();

    public static void main(String[] args) {
        Diccionario diccionario = new DiccionarioLinealOps(100000);

        int[] nn = {
                // 1000,    // precalentamiento
                1000, 2000, 3000, 4000, 5000, 6000, 7000, 8000, 9000, 10000
        };
        for (int n : nn) {
            for (int i = 0; i < 3; i++) {
                long t = meter(diccionario, n);
                System.out.printf("%s %d%n", n, t);
cr        }
    }

    private static long meter(Diccionario diccionario, int n) {
        long t = 0;
        for (int j = 0; j < 100; j++) {
            load(diccionario, n);
            long t0 = OpMeter.reset();
            for (int i = 0; i < 100; i++) {
                int r = random.nextInt(2 * n);
                String clave = mkKey(r);
                String valor = mkValue(r);
                diccionario.get(clave);
            }
            long t2 = OpMeter.getOps();
            t += t2 - t0;
        }
        return t;
    }

    private static String mkKey(int k) {
        return String.valueOf(k);
    }

    private static String mkValue(int v) {
        return String.format("[%d]", v);
    }

    private static void load(Diccionario diccionario, int n) {
        diccionario.clear();
        do {
            int r = random.nextInt(4 * n);
            String clave = mkKey(r);
            String valor = mkValue(r);
            diccionario.put(clave, valor);
        } while (diccionario.size() < n);
    }
}