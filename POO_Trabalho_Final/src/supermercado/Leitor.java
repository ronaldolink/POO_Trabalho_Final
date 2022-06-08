
package supermercado;

public class Leitor {
    
    public static void mostrarValorProduto(String codigo){
        Utilitario.ImprimaMensagem("#               PROCURANDO O PRODUTO DE CODIGO '" + codigo + "'           #");
        
        Produto produto = EstoqueDeProdutos.seekProduto(codigo);
        if(produto != null) {
            System.out.println("#   ´PRODUTO: "+ produto.getNome() + "   #\n#   PREÇO: R$ " + produto.getValor() + "   #");
        }
        else{
            Utilitario.ImprimaMensagem("#                    PRODUTO NÃO LOCALIZADO!!                    #");
        }
        System.out.println();
        System.out.println();
        System.out.println("#####################################################");
    }
    
}
