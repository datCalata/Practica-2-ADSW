package es.upm.dit.adsw.ej2;

import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

/**
 * Created by jcala on 12/03/2017.
 */
public class DiccionarioTest {




    private Diccionario diccionario;
    public static final int N = 5;
    private final Random random = new Random();
    @Before
    public void setUp(){
        diccionario = new HashListas (N);
    }
    // CASOS SINGULARES
    // ARGUMENTO NULL

    @Test(expected = IllegalArgumentException.class)
    public void testPutnull1(){
        diccionario.put(null, "valor");
    }
    @Test(expected = IllegalArgumentException.class)
    public void testGetnull(){
        diccionario.get(null);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testRemovenull(){
        diccionario.remove(null);
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

    //  Prueba el correcto funcionamiento con diccionarios vacios

    @Test
    public void testVacio(){
        Diccionario diccionarioL = new HashListas(10);
        assertNull(diccionarioL.remove("clave"));
        assertEquals(diccionarioL.size(),0);
        assertNull(diccionarioL.get("clave"));
        diccionarioL.put("claveP", "valorP");
        assertEquals(diccionarioL.size(),1);
        diccionarioL.clear();
        assertEquals(diccionarioL.size(),0);
        diccionarioL.clear();
        assertEquals(diccionarioL.size(),0);
    }
    @Test (expected = IllegalArgumentException.class)
    public void testVacio1 () {
        diccionario.put ("","");
    }
    // N=0


    //Prueba el funcionamiento del put y el get
    @Test(expected = IllegalArgumentException.class)
    public void testPut(){
        diccionario.clear();
        diccionario.put("1", "valor");
        //No deberia dejar meterla
        diccionario.put("1", "valor5");
        diccionario.put("2", "valor1");
        diccionario.put("3", "valor2");
        diccionario.put("4", "valor3");
        assertEquals("valor", diccionario.get("1"));
        assertEquals("valor1", diccionario.get("2"));
        assertEquals("valor2", diccionario.get("3"));
        assertEquals("valor3", diccionario.get("4"));
    }
    // PUT
    @Test
    public void testPut1() {
        diccionario.put("clave0", "valor0");
        assertEquals(1, diccionario.size());
        diccionario.put("clave1", "valor1");
        assertEquals(2, diccionario.size());
        assertEquals("valor0", diccionario.get("clave0"));
        assertEquals("valor1", diccionario.get("clave1"));
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
        assertEquals(diccionario.size(),0);
    }
    // SECUENCIAS
    // CLEAR
    @Test
    public void testClear1() {
        diccionario.put("clave0", "valor0");
        diccionario.put("clave1", "valor1");
        diccionario.clear();
        assertEquals(0, diccionario.size());
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


    @Test(expected = IllegalArgumentException.class)
    public void testNull () {
        diccionario.put (null, "valor");
    }
    // ARGUMENTO VACÍO
    @Test
    public void test00 (){
        assertEquals (0, diccionario.size());
        assertNull (diccionario.get("clave"));
        assertNull (diccionario.remove("clave"));
    }
    // N=1
    @Test(expected = IllegalArgumentException.class)
    public void test01(){
        //Añadimos dos elementos con la misma clave
        diccionario.put("clave", "valor");
        assertEquals(1, diccionario.size());
        assertEquals("valor", diccionario.get("clave"));
        //Esta clave no debería entrar
        diccionario.put("clave", "valor2");
        assertEquals(1, diccionario.size());
        diccionario.remove("clave");
        assertEquals(0, diccionario.size());
    }
    @Test(expected = IllegalArgumentException.class)
    public void test02() {
        diccionario.put("clave", "valor");
        diccionario.put("clave2", "valor2");
        assertEquals(2, diccionario.size());
        assertEquals("valor", diccionario.get("clave"));
        assertEquals("valor2", diccionario.get("clave2"));
        //No nos deberia dejar meterlo
        diccionario.put("clave", "valor3");
        assertEquals("valor", diccionario.get("clave"));
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