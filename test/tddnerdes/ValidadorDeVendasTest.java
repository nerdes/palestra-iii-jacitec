/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tddnerdes;

import org.junit.Before;
import org.junit.Assert;
import org.junit.Test;

import static org.mockito.Mockito.*;

/**
 *
 * @author Magno Machado
 */
public class ValidadorDeVendasTest {
    public static final String CPF_CLIENTE = "12345678900";
    private Permissoes permissoes;
    private ValidadorDeVendas validador;
    private ConsultaCredito consultaCredito;

    @Before
    public void setUp() {        
        permissoes = mock(Permissoes.class);
        consultaCredito = mock(ConsultaCredito.class);
        validador = new ValidadorDeVendas(permissoes, consultaCredito);
    }

    @Test
    public void umaVendaParceladaParaUmClienteComNomeSujoDeveSerLiberadaCasoHajaAutorizaçãoGerencial() {
        Venda venda = new Venda(CPF_CLIENTE, 2);
        doReturn(false).when(consultaCredito).nomeLimpo(CPF_CLIENTE);
        doReturn(true).when(permissoes).solicitaPermissaoGerencial();
        Assert.assertTrue(validador.vendaValida(venda));
    }

    @Test
    public void umaVendaParceladaParaUmClienteComNomeSujoNãoDeveSerLiberadaCasoNãoHajaAutorizaçãoGerencial() {
        Venda venda = new Venda(CPF_CLIENTE, 2);
        doReturn(false).when(consultaCredito).nomeLimpo(CPF_CLIENTE);
        doReturn(false).when(permissoes).solicitaPermissaoGerencial();
        Assert.assertFalse(validador.vendaValida(venda));
    }

    @Test
    public void umaVendaParceladaParaUmClienteComNomeLimpoDeveSerLiberadaSemSolicitarAutorizaçãoGerencial() {
        Venda venda = new Venda(CPF_CLIENTE, 2);
        doReturn(true).when(consultaCredito).nomeLimpo(CPF_CLIENTE);
        Assert.assertTrue(validador.vendaValida(venda));
        verify(permissoes, never()).solicitaPermissaoGerencial();
    }

    @Test
    public void umaVendaNãoParceladaParaUmClienteComNomeLimpoDeveSerLiberadaSemSolicitarAutorizaçãoGerencialESemConsultarOCredito() {
        Venda venda = new Venda(CPF_CLIENTE, 1);
        Assert.assertTrue(validador.vendaValida(venda));
        verify(permissoes, never()).solicitaPermissaoGerencial();
        verify(consultaCredito, never()).nomeLimpo(anyString());
    }

}
