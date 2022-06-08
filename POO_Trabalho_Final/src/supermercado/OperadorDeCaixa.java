/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supermercado;

public class OperadorDeCaixa extends Funcionario  {
   
    public OperadorDeCaixa(String nome, String userName, String senha) {
        super(nome, userName, senha);
    }

    @Override
    public void adicionarProduto(Produto produto, double quantidade) {
        System.out.println("#####################################################################");
        System.out.println("#                ATENÇÃO - OPERAÇÃO NÃO PERMITIDA                   #");
        System.out.println("#   OPERADORES DE CAIXA NÃO PODEM ADICIONAR PRODUTOS AO ESTOQUE!    #");
        System.out.println("#####################################################################");
    }

   
    @Override
    public void removerProduto(String codigo, double quantidade) {
        EstoqueDeProdutos.removerProduto(codigo, quantidade);
    }

    @Override
    public void mostrarEstoque() {
        System.out.println("######################################################################");
        System.out.println("#                ATENÇÃO - OPERAÇÃO NÃO PERMITIDA                    #");
        System.out.println("#        OPERADORES DE CAIXA NÃO PODEM MOSTRAR O ESTOQUE!!!!         #");
        System.out.println("######################################################################");
    }
    
}
