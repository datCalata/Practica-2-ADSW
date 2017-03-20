package es.upm.dit.adsw.ej2;

import org.junit.Before;
import org.junit.Test;

import java.util.IllegalFormatCodePointException;
import java.util.Random;

import static org.junit.Assert.*;

/**
 * Created by jcala on 12/03/2017.
 */
public class TestDiccionario {




    private Diccionario diccionario;

    @Before
    public void setUp(){
        diccionario = new HashListas (5);
    }


    //Prueba que lanza las excepciones con valores vacios

    @Test(expected = IllegalArgumentException.class)
    public void testPempty(){
        diccionario.put("", "valor");
    }
    @Test(expected = IllegalArgumentException.class)
    public void testGempty(){
        diccionario.get("");
    }
    @Test(expected = IllegalArgumentException.class)
    public void testRempty(){
        diccionario.remove("");
    }

    //  Vacios

    @Test
    public void testVacio(){
        Diccionario diccionarioL = new HashListas(10);
        assertNull(diccionarioL.remove("clave"));
        assertEquals(diccionarioL.size(),0);
        assertNull(diccionarioL.get("clave"));
        diccionarioL.clear();
        assertEquals(diccionarioL.size(),0);
    }
    @Test (expected = IllegalArgumentException.class)
    public void tesVacioPut () {
        diccionario.put ("","");
    }
    // N=0


    //Prueba el funcionamiento del put y el get
    @Test
    public void testPut(){
        diccionario.clear();
        diccionario.put("1", "valor");
        //Machaca el valor anterior
        diccionario.put("1", "valor5");
        diccionario.put("2", "valor1");
        diccionario.put("3", "valor2");
        diccionario.put("4", "valor3");
        assertEquals("valor5", diccionario.get("1"));
        assertEquals("valor1", diccionario.get("2"));
        assertEquals("valor2", diccionario.get("3"));
        assertEquals("valor3", diccionario.get("4"));
    }
    // PUT
    @Test
    public void testPutSize() {
        diccionario.clear();
        diccionario.put("clave0", "valor0");
        assertEquals(1, diccionario.size());
        diccionario.put("clave1", "valor1");
        assertEquals(2, diccionario.size());
        diccionario.put("clave1", "valor2");
        assertEquals(2,diccionario.size());
    }
    //Prueba el funcionamiento del size y el clear
    @Test
    public void testClear(){
        diccionario.clear();
        diccionario.put("1", "valor");
        diccionario.put("2", "valor1");
        diccionario.put("3", "valor2");
        diccionario.put("4", "valor3");
        assertEquals(diccionario.size(),4);
        diccionario.clear();
        assertNull(diccionario.get("1"));
        assertEquals(diccionario.size(),0);
    }

    //Prueba el funcionamiento del put con un numero de datos el doble de grande que numero de slots
    @Test
    public void testHash(){
        diccionario.clear();
        diccionario.put("1", "valor");
        assertEquals("valor", diccionario.get("1"));
        diccionario.put("2", "valor1");
        assertEquals("valor1", diccionario.get("2"));
        diccionario.put("3", "valor2");
        assertEquals("valor2", diccionario.get("3"));
        diccionario.put("4", "valor3");
        assertEquals("valor3", diccionario.get("4"));
        diccionario.put("5", "valor4");
        assertEquals("valor4", diccionario.get("5"));
        diccionario.put("6", "valor5");
        assertEquals("valor5", diccionario.get("6"));
        diccionario.put("7", "valor6");
        assertEquals("valor6", diccionario.get("7"));
        diccionario.put("8", "valor7");
        assertEquals("valor7", diccionario.get("8"));
        diccionario.put("9", "valor8");
        assertEquals("valor8", diccionario.get("9"));
        diccionario.put("10", "valor9");
        assertEquals("valor9", diccionario.get("10"));
        assertEquals(diccionario.size(),10);
    }


    //TEST DE NULLS

    @Test(expected = IllegalArgumentException.class)
    public void testNullPut () {
        diccionario.put (null, "valor");
    }
    @Test(expected = IllegalArgumentException.class)
    public void testNullGet () {diccionario.get(null);}
    @Test(expected = IllegalArgumentException.class)
    public void testNullRemove () {diccionario.remove(null);}
    // ARGUMENTO VACÍO
    @Test
    public void test00 (){
        assertEquals (0, diccionario.size());
        assertNull (diccionario.get("clave"));
        assertNull (diccionario.remove("clave"));
    }
    // N=1
    @Test
    public void test01(){
        //Añadimos dos elementos con la misma clave
        diccionario.put("clave", "valor");
        assertEquals(1, diccionario.size());
        assertEquals("valor", diccionario.get("clave"));
        //Esta clave deberia machacar la otra
        diccionario.put("clave", "valor2");
        assertEquals(1, diccionario.size());
        diccionario.remove("clave");
        assertEquals(0, diccionario.size());
    }
    @Test
    public void test02() {
        diccionario.put("clave", "valor");
        diccionario.put("clave2", "valor2");
        assertEquals(2, diccionario.size());
        assertEquals("valor", diccionario.get("clave"));
        assertEquals("valor2", diccionario.get("clave2"));
        //Deberia machacar el valor de clave
        diccionario.put("clave", "valor3");
        assertEquals("valor3", diccionario.get("clave"));
        diccionario.remove("clave");
        assertEquals(1,diccionario.size());
        diccionario.put("clave","valor3");
        assertEquals("valor3",diccionario.get("clave"));
    }
    //CASOS NORMALES
    // N > 5
    @Test
    public void testMayor() {
        diccionario.put("clave0", "valor0");
        diccionario.put("clave1", "valor1");
        diccionario.put("clave2", "valor2");
        diccionario.put("clave3", "valor3");
        diccionario.put("clave4", "valor4");
        diccionario.put("clave5", "valor5");
        assertEquals(6, diccionario.size());
        assertEquals("valor5", diccionario.get("clave5"));
        assertEquals("valor0", diccionario.get("clave0"));
        diccionario.remove("clave0");
        assertNull(diccionario.get("clave0"));
        assertEquals("valor5", diccionario.get("clave5"));
    }
    // REMOVE
    @Test
    public void testRemove(){
        diccionario.put("clave0", "valor0");
        diccionario.put("clave1", "valor1");
        diccionario.remove("clave0");
        assertNull(diccionario.get("clave0"));
        assertEquals(1, diccionario.size());
        diccionario.remove("clave1");
        assertNull(diccionario.get("clave1"));
        assertEquals(0, diccionario.size());
    }

}