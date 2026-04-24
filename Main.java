package Aula7_Streams_e_Collectors_labs;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args){

        List<Pedido> pedidos = Arrays.asList(
                new Pedido("Pedro", 1400, StatusPedido.PAGO, LocalDate.of(2022,4,14)),
                new Pedido("Marcos", 1000, StatusPedido.CANCELADO, LocalDate.of(2025,12,25)),
                new Pedido("Maria", 2500, StatusPedido.PENDENDTE, LocalDate.of(2020,8,30)),
                new Pedido("Alice", 500, StatusPedido.CANCELADO, LocalDate.of(2024,4,10)),
                new Pedido("Mário", 300, StatusPedido.CANCELADO, LocalDate.of(2024,5,07)),
                new Pedido("Pedro", 400, StatusPedido.PAGO, LocalDate.of(2019,5,26)),
                new Pedido("Alice", 4000, StatusPedido.PENDENDTE, LocalDate.of(2017,6,02)),
                new Pedido("Maria", 900, StatusPedido.PAGO, LocalDate.of(2023,2,11))
        );


        // DESAFIO 1
        // FILTRAR CLIENTES COM PEDIDOS PAGOS E COM VALOR MAIOR QUE 500
        // MAPEAR OS NOMES DESSES CLIENTES
        // REMOVER NOMES DUPLICADOS (distinct())
        // ITERAR COM forEach()
        System.out.println(" <=== CLIENTES COM PEDIDOS PAGOS E COM VALOR MAIOR QUE R$500 ===> ");
        pedidos.stream()
                .filter(p -> p.getStatus() == StatusPedido.PAGO && p.getValor() > 500)
                .map(Pedido::getCliente)
                .distinct()
                .forEach(System.out::println); // ✅

        System.out.println("------------------------------------------------------------");

        // DESAFIO 2
        // FILTRAR CLIENTES COM PEDIDOS PENDENTES
        // MAPEAR OS VALORES DOS PEDIDOS
        // POR FIM, SOMÁ-LOS
        double somaDosPedidosPendentes = pedidos.stream()
                .filter(p -> p.getStatus() == StatusPedido.PENDENDTE)
                .mapToDouble(Pedido::getValor)
                .sum();

        System.out.println("A soma dos pedidos pendentes é: R$" + somaDosPedidosPendentes); // ✅

        System.out.println("------------------------------------------------------------");

        // DESAFIO 3
        // OBTER O PEDIDO MAIS CARO DA LISTA POR MEIO DO MÉTODO max() QUE RECEBE UM COMPARADOR
        Optional<Pedido> pedidoMaisCaro = pedidos.stream()
                .max(Comparator.comparingDouble(Pedido::getValor));

        System.out.println("Pedido mais caro da lista: " + pedidoMaisCaro); // ✅

        System.out.println("------------------------------------------------------------");

        // DESAFIO 4
        // OBTER O PEDIDO MAIS RECENTE DA LISTA POR MEIO DO MÉTODO max() TAMBÉM
        Optional<Pedido> pedidoMaisRecente = pedidos.stream()
                .max(Comparator.comparing(Pedido::getData));

        System.out.println("Pedido mais recente da lista: " + pedidoMaisRecente); // ✅

        System.out.println("------------------------------------------------------------");

        // DESAFIO 5
        // OBTER DADOS ESTATÍSTICOS EM UMA ÚNICA OPERAÇÃO COM O MÉTODO summarizingDouble()
        // O FILTER SELECIONA APENAS PEDIDOS PAGOS OU PENDENTES, OS DADOS DO PEDIDOS CANCELADOS NÃO
        // SÃO COLETADOS.
        // FAZEMOS A COLETA DESSES DADOS POR MEIO DO MÉTODO collect()
        DoubleSummaryStatistics estatisticaPedidos = pedidos.stream()
                .filter(p -> p.getStatus() == StatusPedido.PAGO || p.getStatus() == StatusPedido.PENDENDTE)
                .collect(Collectors.summarizingDouble(Pedido::getValor));

        System.out.println(" <=== DADOS DOS PEDIDOS ===> ");
        System.out.println("Pedido de menor valor: " + estatisticaPedidos.getMin());
        System.out.println("Pedido de maior valor: " + estatisticaPedidos.getMax());
        System.out.println("Média total dos valores dos pedidos: " + estatisticaPedidos.getAverage());
        System.out.println("Soma total dos pedidos: " + estatisticaPedidos.getSum());
        System.out.println("São quantos pedidos: " + estatisticaPedidos.getCount());
        // ✅

        System.out.println("------------------------------------------------------------");

        // DESAFIO 6
        // EXIBIR CLIENTES COM PEDIDOS CANCELADOS, REMOVENDO DUPLICATA DE NOMES
        System.out.println(" <=== CLIENTES COM PEDIDOS CANCELADOS ===> ");
        pedidos.stream()
                .filter(p -> p.getStatus() == StatusPedido.CANCELADO)
                .map(Pedido::getCliente)
                .distinct()
                .forEach(System.out::println); // ✅
    }
}
