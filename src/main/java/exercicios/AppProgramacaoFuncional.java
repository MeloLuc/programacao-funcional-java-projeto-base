package exercicios;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

/**
 * Aplicação de exemplo de princípios de programação funcional em Java,
 * Expressões Lambda e API de Streams do Java 8.
 * <p>
 * Para aprofundar nestes assuntos, veja os links abaixo:
 *
 * <ul>
 * <li><a href=
 * "https://www.oracle.com/technetwork/pt/articles/java/streams-api-java-8-3410098-ptb.html">Curso de Streams e Expressões Lambda do Java 8</a></li>
 * <li><a href= "http://bit.ly/2I2U5bU">Curso JDK 8 MOOC: Lambdas and Streams
 * Introduction</a></li>
 * </ul>
 *
 * @author Manoel Campos da Silva Filho
 */
public class AppProgramacaoFuncional {
    private static final int TOTAL_ESTUDANTES = 10;
    private final List<Estudante> estudantes = new StudentGenerator().generate(TOTAL_ESTUDANTES);

    public AppProgramacaoFuncional() {

        double maiorNota1 = estudantes.stream()
                .filter((Estudante e) -> e.getSexo() == 'M')
                .mapToDouble((Estudante e) -> e.getNota())
                .max()
                .orElse(0);

        double maiorNota2 = estudantes.stream()
                .filter(new Predicate<Estudante>() {
                    @Override
                    public boolean test(Estudante e) {
                        return e.getSexo() == 'M';
                    }
                })
                .mapToDouble((Estudante e) -> e.getNota())
                .max()
                .orElse(0);

        double maiorNota3 = estudantes.stream()
                .filter((var e) -> e.getSexo() == 'M') //var é opcional (inferência de tipo)
                .mapToDouble((e) -> e.getNota())
                .max()
                .orElse(0);

        System.out.println(maiorNota1);

        System.out.println(maiorNota2);

        System.out.println(maiorNota3);
    }

    public static void main(String[] args) {
        new AppProgramacaoFuncional();
    }
}

