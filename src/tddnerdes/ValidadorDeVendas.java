/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tddnerdes;

/**
 *
 * @author Magno Machado
 */
public class ValidadorDeVendas {
    private final Permissoes permissoes;
    private final ConsultaCredito consultaCredito;

    public boolean vendaValida(Venda venda) {
        return (venda.getNumeroParcelas() == 1) || consultaCredito.nomeLimpo(venda.getCpfCliente()) || permissoes.solicitaPermissaoGerencial();
    }

    public ValidadorDeVendas(Permissoes permissoes,
            ConsultaCredito consultaCredito) {
        this.permissoes = permissoes;
        this.consultaCredito = consultaCredito;
    }

}
