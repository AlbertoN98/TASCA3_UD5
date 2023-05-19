package es.iesmz.tests;

import java.math.BigInteger;
import java.util.regex.Pattern;

public class Compte {
    private static final String COUNTRY_CODE = "ES";
    private static final String CHECK_DIGITS = "00";
    private String titular;
    private String Iban;

    public Compte(String Iban, String titular) {
        this.titular = titular;
        this.Iban = Iban;
    }

    public String getTitular() {
        return titular;
    }

    public String getIban() {
        return Iban;
    }

    public static   boolean compruebaIBAN(String iban, String titular) {
        if (iban.length() != 24) {
            return false;
        }
        int digitoControl = Integer.parseInt(iban.substring(2, 4));
        String ent = iban.substring(4, 8);
        String ofi = iban.substring(8, 12);
        String dc = iban.substring(12, 14);
        String cuenta = iban.substring(14, 24);
        BigInteger numeroSinES = new BigInteger(ent + ofi + dc + cuenta + "142800");
        BigInteger modulo = new BigInteger("97");
        BigInteger divi = numeroSinES.mod(modulo);
        BigInteger resultado = BigInteger.valueOf(98).subtract(divi);
        int resultadoInt = resultado.intValue();
        if (resultadoInt == digitoControl) {
            return true;
        } else {
            return false;
        }

    }


    public static   String generaIBAN(String entitat, String oficina, String dc, String compte) {
        // Verifica que todos los parámetros tengan la longitud adecuada y sean numéricos
        if (entitat.length() != 4 || !entitat.matches("[0-9]+")
                || oficina.length() != 4 || !oficina.matches("[0-9]+")
                || dc.length() != 2 || !dc.matches("[0-9]+")
                || compte.length() != 10 || !compte.matches("[0-9]+")) {
            return null;
        }

        // Calcula los dígitos de control a partir del número de cuenta y del código de país
        String ibanSinDC = COUNTRY_CODE + CHECK_DIGITS + entitat + oficina +  dc + compte + "142800";
        ibanSinDC = ibanSinDC.replaceAll("\\D", ""); // elimina cualquier carácter que no sea un dígito numérico
        BigInteger bigInt = new BigInteger(ibanSinDC);
        BigInteger bigModu = new BigInteger("97");
        BigInteger big = bigInt.mod(bigModu);
        BigInteger resta = BigInteger.valueOf(98).subtract(big);
        int dcInt = resta.intValue();
        String dcStr = String.format("%02d", dcInt);

        // Construye y devuelve el IBAN completo
        return COUNTRY_CODE + dcStr + entitat + oficina + dc + compte;
    }

}
