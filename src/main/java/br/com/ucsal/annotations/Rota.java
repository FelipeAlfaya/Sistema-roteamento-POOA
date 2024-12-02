package br.com.ucsal.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// Define uma anotação para rotas
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Rota {
    String value();
}

