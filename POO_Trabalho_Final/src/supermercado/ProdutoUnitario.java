/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supermercado;

public class ProdutoUnitario extends Produto {
    
    public ProdutoUnitario(String codigo, String nome, double valor) {
        super(codigo, nome, valor);
    }

    @Override
    public double calcularValor(double qtd) {
        return (int)qtd * this.getValor();
    }
    
}
