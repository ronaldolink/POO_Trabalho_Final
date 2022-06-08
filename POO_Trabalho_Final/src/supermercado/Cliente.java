
package supermercado;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Cliente {
    private CarrinhoDeCompras carrinho;
    
    public Cliente(){
        this.carrinho = new CarrinhoDeCompras();
    }

    public CarrinhoDeCompras getCarrinho() {
        return carrinho;
    }

    public void realizarCompra(Caixa caixa){
        caixa.iniciarVenda(this);
    }
    
    public void consultarValor(String codigo){
        Leitor.mostrarValorProduto(codigo);
    }
}
