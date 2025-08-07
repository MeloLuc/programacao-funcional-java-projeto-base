package exercicios;

import exercicios.base.Aula;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class Aula08 extends Aula {

    public Aula08() {
        Estudante estudante = estudantes.stream()
                .reduce((e1, e2) -> e1.getNota() > e2.getNota() ? e1 : e2)
                .orElseThrow(() -> new NoSuchElementException("Nenhum estudante encontradp"));
        System.out.println(estudante);

        var estudante1 = estudantes.getFirst(); //aqui pode dá exceção
        var estudante2 = estudantes.stream()
                .reduce(estudante1, (e1, e2) -> e1.compareTo(e2) > 0 ? e1 : e2); //o retorno aqui já não vai ser mais Opcional, pois passamos um identity
        System.out.println(estudante2);

        estudantes.stream().sorted().forEach(System.out::println); //Não preciso usar o Comparator pois o sorted vai compara com base no method compareTo

        //PARTE 2 DA AULA - MAP
        var mapPessoaComanda = new HashMap<>(Map.of(
            "Jõao", 1.75,
            "Maria", 1.65,
            "José", 1.80,
            "Ana", 1.70,
            "Carlos", 1.75,
            "Mariana", 1.65,
            "Pedro", 1.80,
            "Paula", 1.70
        )); //map.of cria um map imutável, já o passando o retorno para o hashmap nos criamos um map mutável

        mapPessoaComanda.putIfAbsent("Pedro", getValue()); //só adiciona se a chave não existir, invoca a função mesmo se já tiver o nome
        mapPessoaComanda.computeIfAbsent("Lucas", nome -> getValue());
        mapPessoaComanda.forEach((nome, valor) -> System.out.println(nome+": "+valor));
    }

    private static double getValue() {
        System.out.println("getvalue");
        return 100.0;
    }

    public static void main(String[] args) { new Aula08(); }
}
