package es.upm.dit.adsw.ej2;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by jcala on 12/03/2017.
 */
public class HashListas implements Diccionario{

    private class CV{
        final String clave;
        String valor;
        CV(String clave, String valor){
            this.clave = clave;
            this.valor = valor;
        }

    }

    private List<CV>[] slots;
    private int nDatos = 0;


    @SuppressWarnings("rawtypes")
    public HashListas(int size){
        //vamos a crear listas dinamicamente segun las necesitemos en put
        slots = new List[size];
    }

    @Override
    public void put(String clave, String valor) {
        int index = getIndex(clave);
        List<CV> listPos = slots[index];
        //Si la lista es nula, nos creamos una
        if(listPos == null){
            slots[index] = new ArrayList<CV>();
        }
        //Creamos clave con lo que se nos pasa
        CV cv = new CV(clave,valor);
        //Metemos la clave en la lista
        slots[index].add(cv);
        //Incrementamos numero de datos totales
        nDatos++;
    }

    private CV getCV(String clave){
        List<CV> lista = slots[getIndex(clave)];
        for(CV s : lista){
            if(s.clave.equals(clave)){
                return s;
            }
        }
        return null;
    }

    private int getIndex(String clave){
        return Math.abs(clave.hashCode() % slots.length);
    }

    @Override
    public String get(String clave) {
        // Obtenemos el Objeto CV que tiene la misma clave
        CV myCV = getCV(clave);
        // Si ha devuelto nulo es que no este
        if(!(myCV == null)){
            return myCV.valor;
        }
        return null;
    }

    @Override
    public String remove(String clave) {
        CV myCV = getCV(clave);
        //Si es nulo no existe luego salimos directamente
        if (myCV == null) {
            return null;
        }
        List<CV> lista = slots[getIndex(clave)];
        lista.remove(myCV);
        nDatos--;
        return myCV.valor;
    }

    @Override
    public int size() {
        return nDatos;
    }

    @Override
    public void clear() {
        Arrays.fill(slots,null);
        nDatos = 0;
    }
}
