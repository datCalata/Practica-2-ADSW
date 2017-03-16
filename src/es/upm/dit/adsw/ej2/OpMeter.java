package es.upm.dit.adsw.ej2;
/**
 * Clase auxiliar para contar cuantas veces se llama a un metodo.
 * Util para medir complejidad de algoritmos.
 */
public class OpMeter {
    private static long ops;
    /**
     * Pone a cero el contador.
     * @return numero de operaciones, que son 0.
     */
    public static long reset() {
        ops = 0;
        return ops;
    }
    /**
     * Getter.
     * @return numero de operaciones desde el reset.
     */
    public static long getOps() {
        return ops;
    }

    //Preguntar Cuando tiene que aumentar

    /**
     * Wrapper del metodo de comparacion de String.
     *
     * @param s1 una cadena.
     * @param s2 otra cadena.
     * @return 0 si son iguales;
     * un valor negativo si s1 va antes que s2;
     * un valor positivo si s1 va despues que s2.
     * @see String#compareTo(String)
     */
    public static int compareTo(String s1, String s2) {
        ops++;
        return s1.compareTo(s2);
    }
}
