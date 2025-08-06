package exercicios;

import exercicios.base.Aula;

import java.util.NoSuchElementException;
import java.util.OptionalDouble;

public class Aula07 extends Aula {

    public Aula07() {
        OptionalDouble maxOptional = estudantes.stream()
                .mapToDouble(Estudante::getNota)
                .max();
        double maiorNota = maxOptional.orElse(0);
        System.out.printf("%.2f", maiorNota);

        var max2Optional = estudantes.stream()
                .filter(e -> e.getSexo() == 'X')
                .mapToDouble(Estudante::getNota)
                .max();
        double maiorNota2 = max2Optional.orElseGet(() -> { //podemos usar method reference ou orElse com a função, porém essa função sempre vai ser procesada
            var x = Math.random();
            var y = Math.random();
            return x + y;
        });

        double maiornota3 = max2Optional.orElseThrow(() -> new NoSuchElementException("Nenhum estudante encontrado!"));

        System.out.printf("\n%.2f", maiorNota2);
    }
    public static void main(String[] args) {
        new Aula07();
    }
}
