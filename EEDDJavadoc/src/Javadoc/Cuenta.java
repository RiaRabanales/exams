package Javadoc;

/**
 * Práctica 3 de Programación: clase que recoge los datos de la cuenta del cajero.
 * @author María Rabanales
 * @version: 05/05/2020/pm
 */
public class Cuenta {
    
    //Atributos:
    private String nombreCliente;
    private String numeroCuenta;
    private double tipoInteres;
    private double saldoCuenta;
    private String contrasena;

    /**
     * Constructor vacío de la clase Cuenta
     */
    public Cuenta() {
    }
    
    /**
     * Constructor completo de la clase Cuenta
     * @param nombreCliente tipo <code>String</code> contiene el nombre completo del cliente
     * @param numeroCuenta tipo <code>String</code> contiene el número de cuenta
     * @param tipoInteres tipo <code>double</code> contiene el tipo de interés aplicable a la cuenta
     * @param saldoCuenta tipo <code>double</code> contiene el saldo actualizado de la cuenta
     * @param contrasena tipo <code>String</code> contiene la contraseña de acceso a la cuenta
     */
    public Cuenta(String nombreCliente, String numeroCuenta, double tipoInteres, double saldoCuenta, String contrasena) {
        this.nombreCliente = nombreCliente;
        this.numeroCuenta = numeroCuenta;
        this.tipoInteres = tipoInteres;
        this.saldoCuenta = saldoCuenta;
        this.contrasena = contrasena;
    }

    /**
     * Constructor copia de la clase Cuenta
     * @param c1 tipo <code>Cuenta</code> contiene la cuenta existente que se quiere copiar
     */
    public Cuenta(Cuenta c1) {
        this.nombreCliente = c1.nombreCliente;
        this.numeroCuenta = c1.numeroCuenta;
        this.tipoInteres = c1.tipoInteres;
        this.saldoCuenta = c1.saldoCuenta;
        this.contrasena = c1.contrasena;
    }
    
    //Setters y getters:
    /**
     * Getter del atributo nombreCliente
     * @return nombreCliente actual <code>String</code>
     */
    public String getNombreCliente() {
        return nombreCliente;
    }

    /**
     * Setter del atributo nombreCliente
     * @param nombreCliente para introducir tipo <code>String</code>
     */
    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    /**
     * Getter del atributo numeroCuenta
     * @return numeroCuenta actual tipo <code>String</code>
     */
    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    /**
     * Setter del atributo numeroCuenta
     * @param numeroCuenta para introducir tipo <code>String</code>
     */
    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    /**
     * Getter del atributo tipoInteres
     * @return tipoInteres actual tipo <code>double</code>
     */
    public double getTipoInteres() {
        return tipoInteres;
    }

    /**
     * Setter del atributo tipoInteres
     * @param tipoInteres para introducir tipo <code>double</code>
     */
    public void setTipoInteres(double tipoInteres) {
        this.tipoInteres = tipoInteres;
    }

    /**
     * Getter del atributo saldoCuenta
     * @return saldoCuenta actual tipo <code>String</code>
     */
    public double getSaldoCuenta() {
        return saldoCuenta;
    }

    /**
     * Setter del atributo saldoCuenta
     * @param saldoCuenta para introducir tipo <code>double</code>
     */
    public void setSaldoCuenta(double saldoCuenta) {
        this.saldoCuenta = saldoCuenta;
    }

    /**
     * Getter del atributo contrasena
     * @return contrasena actual tipo <code>String</code>
     */
    public String getContrasena() {
        return contrasena;
    }

    /**
     * Setter del atributo contrasena
     * @param contrasena para introducir tipo <code>String</code>
     */
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
    
    //Métodos:    
    /**
     * Contabiliza el ingreso de una cantidad concreta en la cuenta correspondiente sumando su valor al valor original de la cuenta
     * @param ingreso cantidad en euros que el usuario desea ingresar (tipo double)
     * @return false si el usuario ha elegido ingresar una cantidad negativa
     */
    //Nota: la creación de cuenta la he querido hacer por el menú principal y constructor
    public boolean ingresar (double ingreso) {
        if (ingreso >= 0) {
            this.setSaldoCuenta(this.getSaldoCuenta() + ingreso);
            return true;
        } else {
            System.out.println("No se pueden ingresar cantidades negativas.");
            return false;
        }
    }
        
    /**
     * Contabiliza el reintegro de una cantidad concreta en la cuenta correspondiente restando su valor al valor original de la cuenta
     * @param reintegro cantidad en euros que el usuario desea recibir (tipo double)
     * @return false si no hay dinero suficiente en la cuenta para reintegrar la cantidad elegida por el usuario
     */
    public boolean reintegrar (double reintegro) {
        if (this.getSaldoCuenta() - reintegro >= 0) {
            this.setSaldoCuenta(this.getSaldoCuenta() - reintegro);
            return true;
        } else {
            System.out.println("No hay saldo suficiente.");
            return false;
        }
    }
    
    /**
     * Combina las funciones ingresar y reintegrar: reduce una cantidad de una cuenta y la aumenta en otra
     * @param cuentaDestino cuenta de destino de la transferencia (tipo Cuenta)
     * @param importe cantidad en euros que el usuario desea transferir (tipo double)
     */
    public void transferir (Cuenta cuentaDestino, double importe) {
        if (this.getSaldoCuenta() - importe >= 0) {
            reintegrar(importe);
            cuentaDestino.ingresar(importe);
            System.out.println("Transferencia realizada.");
        } else {
            System.out.println("No hay saldo suficiente.");
        }
    }
    
    /**
     * Muestra por consola los siguientes atributos de una cuenta:<br>
     * - atributo <code>numeroCuenta</code> tipo String<br>
     * - atributo <code>nombreCliente</code> tipo String<br>
     * - atributo <code>tipoInteres</code> tipo double<br>
     * - atributo <code>saldoCuenta</code> tipo double<br>
     */
    public void imprimirCuenta() {
        System.out.println("Datos solicitados para la cuenta " + this.getNumeroCuenta() + ":");
        System.out.println("Nombre cliente: " + this.getNombreCliente());
        System.out.println("Tipo de interés: " + this.getTipoInteres());
        System.out.println("Saldo cuenta: " + this.getSaldoCuenta());
        System.out.println("Por motivos legales no se puede facilitar la contraseña por esta vía.");
    }
    
}
