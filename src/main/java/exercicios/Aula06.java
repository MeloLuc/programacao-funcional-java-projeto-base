package exercicios;

import exercicios.base.Aula;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.function.Predicate.not;
import static java.util.stream.Collectors.groupingBy;

/**
 * Esta é uma classe para você poder implementar as atividades propostas no README.
 * Você <b>NÃO</b> deve alterar:
 * <ul>
 *     <li>a estrutura deste arquivo;</li>
 *     <li>o nome da classe, dos métodos ou dos atributos;</li>
 *     <li>parâmetros e tipo de retorno dos métodos.</li>
 * </ul>
 *
 * <b>Mas você PRECISA alterar valores dos atributos existentes</b>.
 *
 * <p>Você pode alterar o código interno dos métodos, criar métodos auxiliares que podem ser chamados
 * pelos existentes, mas não deve alterar a estrutura dos métodos disponíveis.</p>
 *
 * @author Manoel Campos da Silva Filho
 */
public class Aula06 extends Aula {
    /**
     * {@link Predicate<Estudante>} que seleciona somente as mulheres
     * matriculadas em algum curso e com nota maior ou igual a 6.
     * Este deve ser um predicado composto usando {@link Predicate#and(Predicate)}.
     * Você deve trocar o valor armazenado ao atributo para ele seguir a regra definida acima.
     */
    private final Predicate<Estudante> mulheresAprovadas = null; //TODO: Atribua aqui o predicado composto com o filtro indicado acima

    /**
     * Você pode chamar os métodos existentes e outros que você criar aqui,
     * incluir prints e fazer o que desejar neste método para conferir os valores retornados pelo seu método.
     * Para verificar se sua implementação está correta, clique com o botão direito no nome do projeto na aba esquerda
     * do IntelliJ e selecione a opção "Run All Tests".
     */
    public Aula06() {
        //TODO: Insira chamdas das funções existentes aqui, para você conferir como estão funcionando

        //PARTE 1
        Predicate<Estudante> isHomem = Estudante::isHomem;  //guardando reference methods em variaveis
        var NoIsHomem = not(Estudante::isHomem);
        var filtro = isHomem.and(Estudante::hasCurso); // Aqui o var já identifica o tipo, pois and já se liga a Predicate
        var filtro2 = ((Predicate<Estudante>)Estudante::isHomem).and(Estudante::hasCurso); // casting

        long homens = estudantes.stream()
                                .filter(isHomem)
                                .count();

        long homensPalmas = estudantes.stream()
                .filter(filtro)
                .filter(e -> e.getCidade().getNome().equals("Palmas"))
                .count();

        long homensNotPalmas = estudantes.stream()
                .filter(Predicate.not(filtro2))
                .filter(not(filtro2)) //ou desse jeito
                .filter(e -> e.getCidade().getNome().equals("Palmas"))
                .count();

        System.out.println(homens);
        System.out.println(homensPalmas);
        System.out.println(homensNotPalmas);

        //PARTE 2
        //ordenando os estudante pela nota e imprimindo
        estudantes.sort(Comparator.comparingDouble(Estudante::getNota));  // -1 0 1 (como base se o primeiro e menor, igual ou maior na comparação)
        estudantes.forEach(System.out::println);

        var filtro3 = ((Predicate<Estudante>)Estudante::hasCurso).and(Estudante::isAprovado);

        System.out.println("\n---proximo---\n");
        estudantes.stream()
                .filter(filtro3)
                .sorted(Comparator.comparingDouble(Estudante::getNota))
                .forEach(System.out::println);

        System.out.println("\n---proximo---\n");
        estudantes.stream()
                .filter(filtro3)
                .sorted(Comparator.comparing(Estudante::getCurso)
                        .thenComparing(Comparator.comparingDouble(Estudante::getNota).reversed())
                        .thenComparingDouble(Estudante::getNota)) // ou assim
                .forEach(System.out::println);

        System.out.println("\n---proximo---\n");
        // Também podemos salvar em variáveis
        Comparator<Estudante> comparator = Comparator.comparing(Estudante::getCurso)
                                                    .thenComparingDouble(Estudante::getNota);

        estudantes.stream()
                .filter(filtro3)
                .sorted(comparator)
                .forEach(System.out::println);

        //PARTE 3
        List<String> list1 = estudantes.stream()
                .filter(Estudante::hasCurso)
                .filter(Estudante::isMulher)
                .map(Estudante::getNome)
                .collect(Collectors.toList());

        String string1 = estudantes.stream()
                .filter(Estudante::hasCurso)
                .filter(Estudante::isMulher)
                .map(Estudante::getNome)
                .collect(Collectors.joining(", "));

        var map1 = estudantes.stream()
                .filter(Estudante::hasCurso)
                .filter(Estudante::isMulher)
                .collect(groupingBy(Estudante::getCurso));
        map1.forEach((curso, estudantesL) -> {
            System.out.println(curso.getNome());
            estudantesL.forEach(e -> System.out.printf("\t%s\n", e.getNome()));
        });

        var map2 = estudantes.stream()
                .filter(Estudante::hasCurso)
                .filter(Estudante::isMulher)
                .collect(
                        groupingBy(Estudante::getCurso, Collectors.averagingDouble(Estudante::getNota)));
        map2.forEach((curso, mediaNotas) -> System.out.printf("%s: %.2f\n", curso.getNome(), mediaNotas));

    }

    /**
     * Veja o método construtor {@link #Aula06()}.
     */
    public static void main(String[] args) {
        new Aula06();
    }

    /**
     * Obtém uma Lista <b>NÃO-MODIFICÁVEL</b> de mulheres matriculadas e aprovadas em algum curso
     * O método usa o predicado {@link #mulheresAprovadas} para filtrar a lista de estudantes.
     * Desta forma, você precisa definir um predicado composto com {@link Predicate#and(Predicate)}
     * para tal atributo.
     *
     * @return uma Lista <b>NÃO-MODIFICÁVEL</b> de estudantes selecionados pelo predicado {@link #mulheresAprovadas}
     */
    public List<Estudante> getEstudantesMulheresAprovadas() {
        // TODO: Você precisa implementar este método. Apague estas linhas e escreva o código correto.
        return null;
    }

    /**
     * Obtém uma Lista com os mesmos filtros do método {@link #getEstudantesMulheresAprovadas()},
     * mas ordenada por curso e nota.
     *
     * @return uma Lista <b>NÃO-MODIFICÁVEL</b> de estudantes selecionados pelo predicado {@link #mulheresAprovadas}
     */
    public List<Estudante> getEstudantesMulheresAprovadasOrdenadasPorCursoAndNota() {
        // TODO: Você precisa implementar este método. Apague estas linhas e escreva o código correto.
        return null;
    }

    /**
     * Obtém uma Lista com os mesmos filtros do método {@link #getEstudantesMulheresAprovadas()},
     * mas ordenada de forma decrescente pelo nome do curso e crescente pela nota.
     *
     * @return uma Lista <b>NÃO-MODIFICÁVEL</b> de estudantes selecionados pelo predicado {@link #mulheresAprovadas}
     */
    public List<Estudante> getEstudantesMulheresAprovadasOrdenadasPorCursoDecrescenteAndNotaCrescente() {
        // TODO: Você precisa implementar este método. Apague estas linhas e escreva o código correto.
        return null;
    }

    /**
     * Obtém uma Lista com os mesmos filtros do método {@link #getEstudantesMulheresAprovadas()},
     * mas na ordem original retornada pela Stream.
     * A lista deve ser <b>MODIFICÁVEL</b>.
     *
     * @return uma Lista <b>MODIFICÁVEL</b> de estudantes selecionados pelo predicado {@link #mulheresAprovadas}
     */
    public List<Estudante> getEstudantesMulheresAprovadasNaoOrdenadasModificavel() {
        // TODO: Você precisa implementar este método. Apague estas linhas e escreva o código correto.
        return null;
    }

    /**
     * Obtém uma Lista com os mesmos filtros do método {@link #getEstudantesMulheresAprovadas()},
     * mas ordenada de forma decrescente tanto pelo nome do curso quanto pela nota.
     *
     * @return uma Lista <b>NÃO-MODIFICÁVEL</b> de estudantes selecionados pelo predicado {@link #mulheresAprovadas}
     */
    public List<Estudante> getEstudantesMulheresAprovadasOrdenadasTotalmenteDecrescente() {
        // TODO: Você precisa implementar este método. Apague estas linhas e escreva o código correto.
        return null;
    }

    /**
     * Obtém uma Lista com os mesmos filtros do método {@link #getEstudantesMulheresAprovadas()},
     * mas ordenada de forma crescente pelo nome do curso e descrecente pela nota.
     *
     * @return uma Lista <b>NÃO-MODIFICÁVEL</b> de estudantes selecionados pelo predicado {@link #mulheresAprovadas}
     */
    public List<Estudante> getEstudantesMulheresAprovadasOrdenadasPorCursoCrescenteAndNotaDecrescente() {
        // TODO: Você precisa implementar este método. Apague estas linhas e escreva o código correto.
        return null;
    }
}
