
package supermercado;


public class Balanca{
    
    public static double calcularValorPorPeso(double pesoDoProduto, double quantidade){
        return pesoDoProduto*quantidade;
    }
    
    public static double calcularValorPorItem(double valorDoProduto, int quantidade){
        return valorDoProduto*quantidade;
    }
}
