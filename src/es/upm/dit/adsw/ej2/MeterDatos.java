package es.upm.dit.adsw.ej2;

import java.util.Random;

/**
 * Medidor de operaciones realizadas.
 * Created by jose on 07-Dec-15.
 */
@SuppressWarnings("Duplicates")
public class MeterDatos {
    private static final Random random = new Random();

    private static int[] matlab1i,matlab2i;
    private static long[] matlab1t,matlab2t;


    public static void main(String[] args) {
        Diccionario diccionario;
        matlab1i = new int[3*(5000/500)];
        matlab2i = new int[3*(25000-20000)/500 + 3];
        matlab1t = new long[3*5000/500];
        matlab2t = new long[3*(25000-20000)/500 + 3];

        int index = 0;
        System.out.println("Caso NS < ND");
        for(int i = 500; i <= 5000; i+= 500){
            diccionario = new HashListas(i);
            for(int n = 0; n < 3; n++) {
                long t = meter(diccionario, 5000);
                System.out.printf("%s %d%n", i, t);
                matlab1i[index] = i;
                matlab1t[index] = t;
                index++;
            }
        }

        index  = 0;
        System.out.println("Caso NS >> ND");
        for(int i = 20000; i <= 25000; i+= 500){
            diccionario = new HashListas(i);
            for(int n = 0; n < 3; n++) {
                long t = meter(diccionario, 5000);
                System.out.printf("%s %d%n", i, t);
                matlab2i[index] = i;
                matlab2t[index] = t;
                index++;
            }
        }

        printMatlab(matlab1i,"numeroSlots1");
        printMatlab(matlab1t,"Complejidad1");
        printMatlab(matlab2i,"numeroSlots2");
        printMatlab(matlab2t,"Complejidad2");
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

    private static void printMatlab(int[] numeros,String var){
        System.out.printf("%s = [",var);
        for(int i : numeros){
            System.out.printf("%d,",i);
        }
        System.out.println("]");
    }
    private static void printMatlab(long[] numeros, String var){
        System.out.printf("%s = [",var);
        for(long i : numeros){
            System.out.printf("%d,",i);
        }
        System.out.println("]");
    }
}