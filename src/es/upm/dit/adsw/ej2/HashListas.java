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
        //Vamos a crear listas dinamicamente segun las necesitemos en put
        //Si la posicion del array no tiene una lista declarada a la hora de meter un elemento la declararemos en el put
        slots = new List[size];
    }

    @Override
    public void put(String clave, String valor){
        if(clave == null || clave.length() == 0){
            throw new IllegalArgumentException("Clave Nula o vacia");
        }
        int index = getIndex(clave);
        List<CV> listPos = slots[index];
        //Si la lista es nula (No existe), nos creamos una
        if(listPos == null){
            slots[index] = new ArrayList<CV>();

        }
        nDatos++;
        //Metemos la clave en la lista
        //Si metemos una clave duplicada, sobre escribimos
        for(int i = 0; i < slots[index].size(); i++){
            if(slots[index].get(i) != null && OpMeter.compareTo(clave,slots[index].get(i).clave) == 0){
                slots[index].get(i).valor = valor;
                return;
            }
        }
        slots[index].add(new CV(clave,valor));
    }

    private CV getCV(String clave){
        //Nos protegemos contra parametros incorrectos
        List<CV> lista = slots[getIndex(clave)];
        if(lista == null){
            return null;
        }
        for(CV s : lista){
            if(OpMeter.compareTo(s.clave,clave) == 0){
                return s;
            }
        }
        return null;
    }

    private int getIndex(String clave){
        return Math.abs(clave.hashCode() % slots.length);
    }

    @Override
    public String get(String clave){
        //Nos protegemos contra parametros incorrectos
        if(clave == null || clave.length() == 0){
            throw new IllegalArgumentException("Has introducido una clave nula o vacia");
        }
        // Obtenemos el Objeto CV que tiene la misma clave
        CV myCV = getCV(clave);
        // Si ha devuelto nulo es que no este
        if(!(myCV == null)){
            return myCV.valor;
        }
        return null;
    }

    @Override
    public String remove(String clave){
        //Nos protegemos contra claves incorrectas
        if (clave == null || clave.length() == 0) {
            throw new IllegalArgumentException("Clave errónea");
        }
        int index = getIndex(clave);
        if(slots[index]!=null) {
            for (int i = 0; i < slots[index].size(); i++) {
                if (OpMeter.compareTo(clave, slots[index].get(i).clave) == 0) {
                    String val = slots[index].get(i).valor;
                    slots[index].remove(i);
                    nDatos--;
                    return val;
                }

            }
        }

        return null;
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
