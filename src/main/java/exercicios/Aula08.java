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

        // Se a chave não existir vOld(primeiro camppo vale 0) e vNew o atual(100), ou seja, caso a chave não exista o valor é adicionado
        // Se a chave já existir a vai ficar (existi, nova) e a operação que vc quiser fazer para fazer o merge desse novo dado.
        mapPessoaComanda.merge("Lucas", 100.0, (vOld, vNew) -> vOld + vNew);
        mapPessoaComanda.merge("Lucas", 100.0, Double::sum); //mesma coisa da linha de cima porém com method reference

        //Só sobre chaves que já existem
        mapPessoaComanda.replaceAll((nome, valor) -> valor > 100 ? valor * 0.9 : valor);

        //Se um dos parametros eu não estou usando, então eu posso colocar um _ (ignora a variável), pois assim permance um biFuncion
        mapPessoaComanda.replaceAll((_, valor) -> valor * 1.2);

        // Se quisermos mostrar o valor porém sem alterar meus dados
        mapPessoaComanda.entrySet().stream().forEach(entry -> {
            double valor = entry.getValue() <= 100 ? entry.getValue() : entry.getValue() * 1.5 ;
            System.out.println("--"+entry.getKey()+": "+entry.getValue());
        });

        //entry é um unico parametro que engloba chave e valor
        mapPessoaComanda.entrySet().removeIf(entry -> entry.getValue() < 100);



        mapPessoaComanda.forEach((nome, valor) -> System.out.println(nome+": "+valor));

    }

    private static double getValue() {
        System.out.println("getvalue");
        return 100.0;
    }

    public static void main(String[] args) { new Aula08(); }
}
