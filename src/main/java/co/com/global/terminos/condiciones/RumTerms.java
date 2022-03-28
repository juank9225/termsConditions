package co.com.global.terminos.condiciones;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.annotations.QuarkusMain;

@QuarkusMain
public class RumTerms {

    public static void main(String[] args) {
        System.out.println(".................................... ");
        System.out.println(".......Iniciando aplicacion......... ");
        System.out.println(".......Terminos y condiciones....... ");
        System.out.println(".................................... ");

        Quarkus.run(args);
    }
}
