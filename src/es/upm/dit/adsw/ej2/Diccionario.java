package es.upm.dit.adsw.ej2;

public interface Diccionario {
    void put(String clave, String valor);
    String get(String clave);
    String remove(String clave);
    int size();
    void clear();
}
