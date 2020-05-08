package Javadoc;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Práctica 3 de Programación: clase con método main que tiene el siguiente enunciado:<br>
 * Escribe una clase Cuenta para representar una cuenta bancaria y otra para un programa principal con el método main. El programa principal tendrá un arraylist de cuentas.<br>
 * Entre ambas incluye los siguientes métodos:<br>
 *   a.	Constructor por defecto. <br>
 *   b.	Constructor con todos los parámetros. <br>
 *   c.	Constructor copia. <br>
 *   d.	Métodos setters/getters para asignar y obtener los datos de la cuenta. <br>
 *   e.	Métodos ingreso y reintegro, que devuelven true/false. Un ingreso consiste en aumentar el saldo en la cantidad que se indique(no puede ser negativa).  Un reintegro consiste en disminuir el saldo en una cantidad; antes se debe comprobar que hay saldo suficiente.<br>
 *   f.	Método transferencia. Debe permitir pasar dinero de una cuenta a otra siempre que en la cuenta de origen haya dinero suficiente para poder hacerla. 
 * @author: María Rabanales
 * @version: 06/05/2020/am
 */

public class MenuPrincipal {

    protected static Scanner lector = new Scanner(System.in);

    /**
     * Menú principal<br>
     * Permite elegir qué operación realizar: iniciar sesión con una cuenta existente o crear una nueva cuenta para operar.
     * @param args marca del método main
     */
    public static void main(String[] args) {
        //decido crear mi lista aquí para que se me inicialice con el programa
        ArrayList<Cuenta> listaCuentas = crearCuentas();
        System.out.println("Bienvenido.");
        boolean salir = false;
        //podría sacar el menú a un método externo pero por no liarme con el lector, no
        while (salir == false) {
            System.out.println("Elija operación:");
            System.out.println("  1- iniciar sesión");
            System.out.println("  2- crear cuenta");
            System.out.println("  0- salir");
            //quiero evitarme parseInt así que cojo datos en String:
            String opcionUsuario = lector.nextLine().trim();

            switch (opcionUsuario) {
                case "1":                   //iniciar sesion
                    int indiceUsuario = iniciarSesion(listaCuentas);
                    if (indiceUsuario != -1) {
                        System.out.println("Bienvenido, cliente.");
                        desarrollarSesion(listaCuentas, indiceUsuario);
                    } else {
                        System.out.println("Datos incorrectos.");
                    }
                    break;
                case "2":                   //crear cuenta
                    Cuenta nuevaCuenta = crearCuenta(listaCuentas);
                    listaCuentas.add(nuevaCuenta);
                    break;
                case "0":                   //cerrar programa
                    salir = true;
                    break;
                default:
                    System.out.println("Opción incorrecta.");
            }
        }
        lector.close();
    }

    /**
     * Inicia la sesión y comprueba si la contraseña es correcta.
     * @param listaCuentas facilita todas las cuentas existentes en el cajero
     * @return indice de la cuenta (o, si no existe, -1)
     */
    public static int iniciarSesion(ArrayList<Cuenta> listaCuentas) {
        int indice = -1;
        System.out.println("Introduzca su número de cuenta (formato 10001+):");
        String numCuenta = lector.nextLine().trim();
        System.out.println("Introduzca su contraseña (vegetal*):");
        String numContrasena = lector.nextLine().trim();
        int pseudoIndice = buscarCuenta(listaCuentas, numCuenta);
        if (listaCuentas.get(pseudoIndice).getContrasena().equals(numContrasena)) {
            indice = pseudoIndice;
        }
        return indice;
    }

    /**
     * Desarrolla la sesión y comprueba si la contraseña es correcta.
     * @param listaCuentas facilita todas las cuentas existentes en el cajero
     * @param indice de la cuenta (o, si no existe, -1)
     */
    public static void desarrollarSesion(ArrayList<Cuenta> listaCuentas, int indice) {
        boolean salir2 = false;
        double importe;

        while (salir2 == false) {
            System.out.println(" ");
            System.out.println("Elija operación:");
            System.out.println("  1- consulta cuenta");
            System.out.println("  2- eliminación cuenta");
            System.out.println("  3- ingreso");
            System.out.println("  4- reintegro");
            System.out.println("  5- transferencia");
            System.out.println("  0- volver al menú principal");
            //quiero evitarme parseInt así que cojo datos en String:
            String opcionUsuario2 = lector.nextLine().trim();

            switch (opcionUsuario2) {
                case "1":                   //consultar cuenta
                    listaCuentas.get(indice).imprimirCuenta();
                    break;
                case "2":                   //eliminar cuenta, no lo pide el enunciado
                    listaCuentas.remove(listaCuentas.get(indice));
                    System.out.println("Su cuenta ha sido eliminada.");
                    break;
                case "3":                   //ingreso
                    importe = solicitarCantidad();
                    listaCuentas.get(indice).ingresar(importe);
                    System.out.println("La cantidad en cuenta es de €" + listaCuentas.get(indice).getSaldoCuenta());
                    break;
                case "4":                   //reintegro
                    importe = solicitarCantidad();
                    listaCuentas.get(indice).reintegrar(importe);
                    System.out.println("La cantidad en cuenta es de €" + listaCuentas.get(indice).getSaldoCuenta());
                    break;
                case "5":                   //transferencia
                    System.out.println("Introduzca el número de cuenta de destino (formato 10001+):");
                    String numCuenta2 = lector.nextLine().trim();
                    int indice2 = buscarCuenta(listaCuentas, numCuenta2);
                    importe = solicitarCantidad();
                    listaCuentas.get(indice).transferir(listaCuentas.get(indice2), importe);
                    System.out.println("La cantidad en cuenta principal es de €" + listaCuentas.get(indice).getSaldoCuenta());
                    System.out.println("La cantidad en cuenta secundaria es de €" + listaCuentas.get(indice2).getSaldoCuenta());
                    break;
                case "0":
                    salir2 = true;
                    System.out.println("Vuelta al menú principal.");
                    System.out.println(" ");
                    break;
                default:
                    System.out.println("Opción incorrecta.");
                    break;
            }
        }
    }

    /**
     * Crea un conjunto de cuentas iniciales guardadas en el cajero
     * @return cuentas contiene una lista de cuentas para pretender que el cajero ya contiene datos de cuentas iniciales desde el primer momento
     */
    public static ArrayList<Cuenta> crearCuentas() {
        ArrayList<Cuenta> cuentas = new ArrayList<>();
        cuentas.add(new Cuenta("Alberto", "10001", 1.85, 950, "pepino"));
        cuentas.add(new Cuenta("Maria", "10001", 1.05, 900, "pepino"));
        cuentas.add(new Cuenta("Pepe", "10002", 2.05, 1990, "pepino"));
        cuentas.add(new Cuenta("Marta", "10003", 1.85, 490, "pepino"));
        cuentas.add(new Cuenta("Gemma", "10004", 1.05, 970, "pepino"));
        cuentas.add(new Cuenta("Sergio", "10005", 1.30, 1250, "pepino"));
        return cuentas;
    }

    /**
     * Crea una nueva cuenta a través de los elementos introducidos por consola por parte del usuario
     * @param listaCuentas contiene todas las cuentas existentes en el cajero
     * @return nuevaCuenta nuevo objeto tipo Cuenta
     */
    public static Cuenta crearCuenta(ArrayList<Cuenta> listaCuentas) {
        String nuevoNumero = Integer.toString((listaCuentas.size() + 10011));
        System.out.println("Introduzca tu nombre:");
        String nuevoNombre = lector.nextLine();
        System.out.println("Introduzca la cantidad: ");
        double nuevoSaldo = Double.parseDouble(lector.nextLine());
        System.out.println("Introduzca la contraseña:");
        String nuevaContrasena = lector.nextLine();
        Cuenta nuevaCuenta = new Cuenta(nuevoNombre, nuevoNumero, 1.05, nuevoSaldo, nuevaContrasena);
        System.out.println("Cuenta creada. Número de cuenta: " + nuevaCuenta.getNumeroCuenta());
        return nuevaCuenta;
    }

    /**
     * Facilita las llamadas a solicitar cantidades por el usuario
     * @return cantidad introducida por el usuario (tipo dpuble)
     */
    public static double solicitarCantidad() {
        //traza:  System.out.println("entra en lector");
        System.out.println("Cantidad de la operación:");
        return Double.parseDouble(lector.nextLine());
    }

    /**
     * Función buscador de cuentas para poder reutilizar cuando se necesite
     * @param listaCuentas conjunto de cuentas guardadas en el cajero
     * @param numCuenta objeto tipo Cuenta concreto que se desea buscar
     * @return indice de la cuenta buscada en la lista de cuentas del cajero<br>-1 si no existe la cuenta en la lista de cuentas del cajero
     */
    public static int buscarCuenta(ArrayList<Cuenta> listaCuentas, String numCuenta) {
        for (int i = 0; i < listaCuentas.size(); i++) {
            if (listaCuentas.get(i).getNumeroCuenta().equals(numCuenta)) {
                return i;
            }
        }
        return -1;
    }
}
