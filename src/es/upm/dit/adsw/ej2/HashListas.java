package es.upm.dit.adsw.ej2;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @autor G-23.10
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

    /**
     *Crearemos listas dinámicamente si son necesarias
     * Decidimos en que posición de listPos deberá estar el objeto y si hay una lista ya creada la recorremos para comprobar que no hay
     * claves duplicadas y lo metemos, sino la creamos;
     *@param clave tipo String
     *@param valor tipo
     *@exception IllegalArgumentException, si los parámetros son nulos o Strings de longitud 0
     */

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

        //Metemos la clave en la lista
        //Si metemos una clave duplicada, sobre escribimos
        for(int i = 0; i < slots[index].size(); i++){
            if(slots[index].get(i) != null && OpMeter.compareTo(clave,slots[index].get(i).clave) == 0){
                slots[index].get(i).valor = valor;
                //Si machacamos un dato no aumentamos el contador de datos
                return;
            }
        }
        slots[index].add(new CV(clave,valor));
        nDatos++;
    }

    /**Getter
     *@param clave tipo String
     *@return s si encuentra la clave;
     * null si no esta en la lista o es una lista vacia;
     */

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

    /**Getter
     *@param clave tipo String
     *@return el indice donde debe estar la clave
     *
     */

    private int getIndex(String clave){
        return Math.abs(clave.hashCode() % slots.length);
    }


    /**Getter
     *@param clave tipo String
     *@see getCV
     *@return devuelve el valor del objeto;
     * null si no es;
     *@throws IllegalArgumentException cuando se introduce una clave nula o vacia
     */

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

    /**Elimina el objeto de la clace
     *@param clave tipo String
     *@return val que es el valor eliminado
     * null si no es;
     *@throws IllegalArgumentException cuando se introduce una clave nula o vacia
     */


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


    /**@return nDatos
     */
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
