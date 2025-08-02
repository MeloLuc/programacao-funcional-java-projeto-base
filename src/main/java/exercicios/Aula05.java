package exercicios;

import exercicios.base.Aula;

public class Aula05 extends Aula {

    public Aula05() {
        double menorNota = estudantes.stream()
                .filter(e -> e.getSexo() == 'M')
                .filter(e -> e.getNota() > 0)
                .mapToDouble(e -> e.getNota())
                .min()
                .orElse(0);

        //TELL, DON´T ASK
        double menorNota2 = estudantes.stream()
                .filter(e -> e.isHomem())
                .mapToDouble(Estudante::getNota) //method reference
                .min()
                .orElse(0);

        long totalEstados = estudantes.stream()
                .map(Estudante::getCidade)
                .map(Cidade::getEstado)
                .distinct()
                .count();

        System.out.printf("total de estudantes %d", totalEstados);
    }

    public static void main(String[] args) {
        new Aula05();
    }
}
