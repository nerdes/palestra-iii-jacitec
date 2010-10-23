/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tddnerdes;

/**
 *
 * @author Magno Machado
 */
public class Venda {
    private final String cpfCliente;
    private final int numeroParcelas;

    public String getCpfCliente() {
        return cpfCliente;
    }

    public Venda(String cpfCliente, int numeroParcelas) {
        this.cpfCliente = cpfCliente;
        this.numeroParcelas = numeroParcelas;
    }

    public int getNumeroParcelas() {
        return numeroParcelas;
    }

}
