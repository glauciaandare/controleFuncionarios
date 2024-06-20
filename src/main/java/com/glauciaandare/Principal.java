package com.glauciaandare;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Principal {

    public static void main(String[] args) {
        // 3.1 - Inserir todos os funcionários
        List<Funcionario> funcionarios = new ArrayList<>();
        funcionarios.add(new Funcionario("João", LocalDate.of(1990, 5, 15), new BigDecimal("3000.00"), "Analista"));
        funcionarios.add(new Funcionario("Maria", LocalDate.of(1985, 10, 20), new BigDecimal("5000.00"), "Gerente"));
        funcionarios.add(new Funcionario("Pedro", LocalDate.of(1992, 8, 8), new BigDecimal("2500.00"), "Desenvolvedor"));

        // 3.2 - Remover o funcionário "João" da lista
        funcionarios.removeIf(funcionario -> funcionario.getNome().equals("João"));

        // 3.3 - Imprimir todos os funcionários
        System.out.println("Funcionários:");
        funcionarios.forEach(System.out::println);

        // 3.4 - Aumentar salário em 10%
        funcionarios.forEach(funcionario -> {
            BigDecimal aumento = funcionario.getSalario().multiply(new BigDecimal("0.10"));
            funcionario.salario = funcionario.getSalario().add(aumento);
        });

        // 3.5 - Agrupar funcionários por função em um MAP
        Map<String, List<Funcionario>> funcionariosPorFuncao = funcionarios.stream()
                .collect(Collectors.groupingBy(Funcionario::getFuncao));

        // 3.6 - Imprimir funcionários agrupados por função
        System.out.println("\nFuncionários agrupados por função:");
        funcionariosPorFuncao.forEach((funcao, lista) -> {
            System.out.println("Função: " + funcao);
            lista.forEach(System.out::println);
        });

        // 3.8 - Imprimir funcionários que fazem aniversário nos meses 10 e 12
        System.out.println("\nFuncionários que fazem aniversário nos meses 10 e 12:");
        funcionarios.stream()
                .filter(funcionario -> funcionario.getDataNascimento().getMonthValue() == 10 ||
                        funcionario.getDataNascimento().getMonthValue() == 12)
                .forEach(System.out::println);

        // 3.9 - Encontrar funcionário mais velho
        Funcionario maisVelho = funcionarios.stream()
                .min((f1, f2) -> f1.getDataNascimento().compareTo(f2.getDataNascimento()))
                .orElse(null);

        if (maisVelho != null) {
            LocalDate hoje = LocalDate.now();
            int idade = hoje.getYear() - maisVelho.getDataNascimento().getYear();
            System.out.println("\nFuncionário mais velho:");
            System.out.println("Nome: " + maisVelho.getNome() + ", Idade: " + idade + " anos");
        }

        // 3.10 - Ordenar funcionários por nome
        funcionarios.sort((f1, f2) -> f1.getNome().compareTo(f2.getNome()));

        System.out.println("\nLista de funcionários por ordem alfabética:");
        funcionarios.forEach(System.out::println);

        // 3.11 - Imprimir total dos salários dos funcionários
        BigDecimal totalSalarios = funcionarios.stream()
                .map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        System.out.println("\nTotal dos salários dos funcionários: " + totalSalarios);

        // 3.12 - Imprimir quantos salários mínimos ganha cada funcionário
        BigDecimal salarioMinimo = new BigDecimal("1212.00");

        System.out.println("\nSalários mínimos ganhos por cada funcionário:");
        funcionarios.forEach(funcionario -> {
            BigDecimal salariosMinimos = funcionario.getSalario().divide(salarioMinimo, 2, BigDecimal.ROUND_HALF_UP);
            System.out.println(funcionario.getNome() + ": " + salariosMinimos + " salários mínimos");
        });
    }
}
