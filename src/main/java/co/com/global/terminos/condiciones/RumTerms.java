package co.com.global.terminos.condiciones;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;

@QuarkusMain
public class RumTerms {

    public static void main(String[] args) {
        Quarkus.run(TermsApp.class,args);
    }

    public static class TermsApp implements QuarkusApplication{

        @Override
        public int run(String... args) throws Exception {
            System.out.println(".................................... ");
            System.out.println(".......Iniciando aplicacion......... ");
            System.out.println(".......Terminos y condiciones....... ");
            System.out.println(".................................... ");
            Quarkus.waitForExit();//para terminar la aplicaccion
            return 0;
        }
    }
}
