package Aula7_Streams_e_Collectors_labs;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Pedido {

    private String cliente;
    private double valor;
    private StatusPedido status;
    private LocalDate data;

    public Pedido(String cliente, double valor, StatusPedido status, LocalDate data) {
        this.cliente = cliente;
        this.valor = valor;
        this.status = status;
        this.data = data;
    }

    public String getCliente() {
        return cliente;
    }

    public double getValor() {
        return valor;
    }

    public StatusPedido getStatus() {
        return status;
    }

    public LocalDate getData() {
        return data;
    }

    private String formatarData(){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        String dataFormatada = data.format(formatter);

        return dataFormatada;
    }

    @Override
    public String toString() {
        return "Cliente: " + cliente +
                "; Valor: " + valor +
                "; Status: " + status +
                "; Data: " + formatarData();
    }
}
