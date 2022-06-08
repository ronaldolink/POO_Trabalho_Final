package supermercado;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EstoqueDeProdutos {
    
    public static Map<String, List<Produto>> estoque = new LinkedHashMap<String, List<Produto>>();;
    private static Map<String, List<Produto>> copiaDoEstoque;
    private static Produto p;

    private static Map<String, List<Produto>> getCopiaDoEstoque() {
        return copiaDoEstoque;
    }

    private static void setCopiaDoEstoque(Map<String, List<Produto>> copiaDoEstoque) {
        copiaDoEstoque = copiaDoEstoque;
    }
    
    public static void adicionarProduto(Produto produto, double quantidade){
        List<Produto> produtosDoCodigo;
        String codigo = produto.getCodigo();
        
        if(estoque.containsKey(codigo)){
            produtosDoCodigo = estoque.get(codigo);
            
            if (produtosDoCodigo.get(0).getNome().equals(produto.getNome())) {
                if (produto instanceof ProdutoUnitario) {
                    while (quantidade > 0) {                
                        produtosDoCodigo.add(produto);
                        quantidade--;
                    }
                }
                else if (produto instanceof ProdutoQuilo) {
                    ProdutoQuilo pdt = ObtenhaProdutoQuiloTemporario(produto);
                    pdt.setQtdQuilos(((ProdutoQuilo)produtosDoCodigo.get(0)).getQtdQuilos() +  quantidade);
                    produtosDoCodigo = new LinkedList<Produto>();
                    produtosDoCodigo.add(pdt);
                }

                estoque.put(codigo, produtosDoCodigo);
            }
            else{
                System.out.println("#       ATENÇÃO         #");
                System.out.println("#   PRODUTO NÃO FOI ADICIONADO POIS O CODIGO '" + produto.getCodigo() + "' ESTÁ ATRELADO A OUTRO PRODUTO    #'");

            }
        }else{
            produtosDoCodigo = new LinkedList<Produto>();
            
            if (produto instanceof ProdutoUnitario) {
                while (quantidade > 0) {                
                    produtosDoCodigo.add(produto);
                    quantidade--;
                }
            } 
            else if (produto instanceof ProdutoQuilo) {
                produtosDoCodigo.add(produto);
            }
            
            estoque.put(codigo, produtosDoCodigo);
        }
    }
    
    public static void removerProduto(String codigo, double quantidade){
        List<Produto> produtosDoCodigo;
        boolean removerDoEstoque = false;
        
        if(estoque.containsKey(codigo)){
            produtosDoCodigo = estoque.get(codigo);
            p = produtosDoCodigo.get(0);
            if (produtosDoCodigo.get(0) instanceof ProdutoUnitario) {
                double temp = quantidade;
                for (int i = 0; i < quantidade; i++) {
                    if(produtosDoCodigo.size() > 0 && temp > 0) {                
                        produtosDoCodigo.remove(produtosDoCodigo.get(0));
                        
                        temp--;
                    }
                    else {
                        //System.out.println("ATENÇÃO! O estoque desse produto acabou: " + p.getNome()); 
                        removerDoEstoque = true;
                        break;
                    }
                }
            }
            else if (p instanceof ProdutoQuilo){
                ProdutoQuilo pdtQuilo = ObtenhaProdutoQuiloTemporario(p);
                        
                double peso = pdtQuilo.getQtdQuilos() - quantidade;
                removerDoEstoque = peso <= 0 ? true : false;
                pdtQuilo.setQtdQuilos(peso);
                produtosDoCodigo.clear();
                produtosDoCodigo.add(pdtQuilo);
            }
            if (removerDoEstoque) {
                Utilitario.ImprimaMensagem("#  ATENÇÃO!!! O " + p.getNome() +"  ESTÁ EM FALTA NO ESTOQUE!!!!    #");
                estoque.remove(codigo);
            }
            else{
                estoque.put(codigo, produtosDoCodigo);
            }
        }else{
            Utilitario.ImprimaMensagem("#  ATENÇÃO! PRODTO COM O CÓDIGO " + codigo + " NÃO ENCONTRADO  #");
        }
        System.out.println();
    }
    
    public static void mostrarEstoque(int opcaoDeEstoque){
        Map<String, List<Produto>> estoqueTemp = null;
        if (opcaoDeEstoque == 1) {
            estoqueTemp = estoque;
        }
        else if (opcaoDeEstoque == 2) {
            estoqueTemp = getCopiaDoEstoque();
        }
        Utilitario.ImprimaMensagem("*                    ESTOQUE DE PRODUTOS                        *");
        Iterator listasDeCodigos = estoqueTemp.keySet().iterator();
        int quantidade = 0;
        double quilos = 0;
        while (listasDeCodigos.hasNext()) {
            String codigo = (String)listasDeCodigos.next();
            if (estoqueTemp.get(codigo).size() > 0) { // listar apenas se existir produtos
                Iterator produtos = estoqueTemp.get(codigo).iterator();
                boolean mostrarNomeProduto = true;

                while (produtos.hasNext()) {
                    p = (Produto)produtos.next();

                    if (mostrarNomeProduto) {
                        System.out.println("Código: " + codigo);
                        System.out.println("Produto: " + p.getNome());
                        mostrarNomeProduto = false;
                    }
                    if (p instanceof ProdutoQuilo) {
                        ProdutoQuilo pdt = (ProdutoQuilo)p;
                        System.out.println("Quilos: " + pdt.getQtdQuilos() + "kg\n");
                    }

                    quantidade++;
                }

                if (p instanceof ProdutoUnitario) {
                    System.out.println("   QUANTIDADE EM ESTOQUE = " + quantidade + "\n");
                }
                quantidade = 0;
            }
        }
        System.out.println();
    }
 
    public static double precoPorCodigo(String codigo){
        if(estoque.containsKey(codigo)){
            Iterator it = EstoqueDeProdutos.estoque.get(codigo).iterator();
            Produto produto = null;
            if(it.hasNext()){
                produto = (Produto) it.next();
                return produto.calcularValor(1);
            }else{
                return 0.0;
            }
        }
        return 0.0;
    }
    
   
    public static Produto seekProduto(String codigo) {
        Map<String, List<Produto>> temp = estoque.entrySet().stream().collect(Collectors.toMap(e -> e.getKey(), e-> new LinkedList(e.getValue())));
        if(temp.containsKey(codigo)){
            Iterator it = temp.get(codigo).iterator();
            
            if(it.hasNext()){
                Produto pdt = (Produto) it.next();
                if (pdt instanceof ProdutoUnitario) {
                    String codigo_un = pdt.getCodigo();
                    String nome_un = pdt.getNome();
                    double valor_un = pdt.getValor();
                    ProdutoUnitario pdt_un = new ProdutoUnitario(codigo_un, nome_un, valor_un);
                    return pdt_un;
                }
                else if (pdt instanceof ProdutoQuilo){
                    return ObtenhaProdutoQuiloTemporario(pdt);
                }
            }
        }else{
                Utilitario.ImprimaMensagem("#                   PRODUTO NÃO ENCONTRADO!!!!!                     #");
        }
        return null;
    }
    
   
    public static boolean produtoParaCompra(String codigo, double quantidade, Boolean validarQuantidade){
        //Verifica se existe o produto no estoque e se possui a quantidade esperada
        boolean retorno = false;
        List<Produto> produtosDoCodigo = null;
        if(EstoqueDeProdutos.estoque.containsKey(codigo)){
            produtosDoCodigo = estoque.get(codigo);
            if(produtosDoCodigo.get(0) instanceof ProdutoUnitario){
                if(produtosDoCodigo.size() >= quantidade){
                    retorno = true;
                }else{
                    if (validarQuantidade) {
                        Utilitario.ImprimaMensagem("#                    INFELIZMENTE TEMOS APENAS "+produtosDoCodigo.size()+" UNIDADES DESTE PRODUTO                     #");
                        retorno = false;
                    }
                    else{
                        retorno = true;
                    }
                }
            }
            if(produtosDoCodigo.get(0) instanceof ProdutoQuilo){
                ProdutoQuilo prodKg = (ProdutoQuilo) produtosDoCodigo.get(0);
               
                if(prodKg.getQtdQuilos() >= quantidade){
                    retorno = true;
                }else{
                    if (validarQuantidade) {
                        Utilitario.ImprimaMensagem("#                    INFELIZMENTE NÃO TEMOS HÁ QUANTIDADE DESEJADA, SÓ TEMOS "+prodKg.getQtdQuilos()+" KG DESTE PRODUTO!                      #");
                        retorno = false;
                    }
                    else{
                        retorno = true;
                    }
                }
            } 
        }else{
            Utilitario.ImprimaMensagem("#                    PRODUTO INDISPONIVEL                      #");
            retorno = false;
        }
        return retorno;
    }
    
     public static void exibirEstoqueCliente(){
        Produto p = null;
        Utilitario.ImprimaMensagem("#                     PRODUTOS DISPONÍVEIS                      #");
        Iterator listasDeCodigos = EstoqueDeProdutos.estoque.keySet().iterator();
        int quantidade = 0;
        double quilos = 0;
        
        while (listasDeCodigos.hasNext()) {
            String codigo = (String)listasDeCodigos.next();
            Iterator produtos = estoque.get(codigo).iterator();
            boolean mostrarNomeProduto = true;
           
            while (produtos.hasNext()) {
                p = (Produto)produtos.next();
                
                if (mostrarNomeProduto) {
                    System.out.println("Código: " + codigo);
                    System.out.println("Produto: " + p.getNome());
                    mostrarNomeProduto = false;
                }
                
                if (p instanceof ProdutoQuilo) {
                    ProdutoQuilo pdt = (ProdutoQuilo)p;
                    System.out.println("Quilos: " + pdt.getQtdQuilos() + "kg\n");
                }

                quantidade++;
            }
            
            if (p instanceof ProdutoUnitario) {
                System.out.println("Quantidade em estoque = " +quantidade + "\n");
            }
            
            quantidade = 0;
        }
        
        System.out.println();
    }
     
    public static ProdutoQuilo ObtenhaProdutoQuiloTemporario(Produto temp) {
        String codigo_quilo = temp.getCodigo();
        String nome_quilo = temp.getNome();
        double valor_quilo = temp.getValor();
        double qtd_quilo = ((ProdutoQuilo) temp).getQtdQuilos();
        ProdutoQuilo pdtQuilo = new ProdutoQuilo(codigo_quilo, nome_quilo,
                valor_quilo, qtd_quilo);
        return pdtQuilo;
    }
    
    public static void Feed(){
           System.out.println("#################################################################");
           System.out.println("#              CARGA INICIAL DO ESTOQUE DE PRODUTOS             #");
           System.out.println("#                   POR FAVOR AGUARDE....                        #");
        ProdutoUnitario leite = new ProdutoUnitario("15", "LEITE CONDENSADO", 5.50);
        adicionarProduto(leite, 50.0);
        
        ProdutoUnitario sal = new ProdutoUnitario("23", "PACOTE DE AÇUCAR", 14.95);
        adicionarProduto(sal, 20.0);
        
        ProdutoUnitario acucar = new ProdutoUnitario("36", "PACOTE DE FEIJÃO", 3.50);
        adicionarProduto(acucar, 32.0);
        
        ProdutoUnitario refrigerante = new ProdutoUnitario("44", "REFRIGERANTE LITRO 1.5 ", 12.75);
        adicionarProduto(refrigerante, 100.0);
        
        ProdutoUnitario cerveja = new ProdutoUnitario("52", "CERVEJA HEINEKEN CX 12", 55.50);
        adicionarProduto(cerveja, 200.0);
        
        ProdutoUnitario arroz = new ProdutoUnitario("66", "PAPEL HIGIENICO", 9.25);
        adicionarProduto(arroz, 150.0);
        
        ProdutoUnitario sabao = new ProdutoUnitario("70", " DESINFETANTE YPE", 18.65);
        adicionarProduto(sabao, 51);
        
        ProdutoUnitario macarrao = new ProdutoUnitario("89", "CAIXA DE MIOJO 20 UND", 22.39);
        adicionarProduto(macarrao, 80.0);
        
        ProdutoUnitario biscoito = new ProdutoUnitario("97", "SACO DE BOLACHA TRAKINAS", 3.99);
        adicionarProduto(biscoito, 35.0);
        
        ProdutoUnitario cafe = new ProdutoUnitario("102", "PÓ DE CAFÉ", 15.99);
        adicionarProduto(cafe, 60.0);
        
        ProdutoUnitario oleo = new ProdutoUnitario("115", "ÓLEO DE GIRASSOL", 7.20);
        adicionarProduto(oleo, 64.0);
        
        ProdutoUnitario feijao = new ProdutoUnitario("125", "BACALHAU", 100.99);
        adicionarProduto(feijao, 80.0);
        
        ProdutoUnitario esponja = new ProdutoUnitario("135", "MELANCIA", 13.50);
        adicionarProduto(esponja, 50.0);
        
        ProdutoUnitario detergente = new ProdutoUnitario("140", "KETCHUP", 2.99);
        adicionarProduto(detergente, 75.0);
        
        ProdutoUnitario farinha = new ProdutoUnitario("150", "MAIONESE", 3.99);
        adicionarProduto(farinha, 45.0);
        
        ProdutoUnitario manteiga = new ProdutoUnitario("160", "MOSTARDA", 1.50);
        adicionarProduto(manteiga, 65.0);
        
        ProdutoUnitario sabonete = new ProdutoUnitario("170", "CONDIONADOR", 12.65);
        adicionarProduto(sabonete, 150.0);
        
        ProdutoQuilo tomate = new ProdutoQuilo("11", "PEPINO", 4.50, 100);
        adicionarProduto(tomate, 0);
        
        ProdutoQuilo batata = new ProdutoQuilo("21", "BATATA", 1.99, 89);
        adicionarProduto(batata, 0);
        
        ProdutoQuilo carne = new ProdutoQuilo("31", "PICANHA BOVINA", 54.50, 450);
        adicionarProduto(carne, 0);
        
        ProdutoQuilo limao = new ProdutoQuilo("41", "LEITÃO", 55.99, 75);
        adicionarProduto(limao, 0);
        
        ProdutoQuilo mandioca = new ProdutoQuilo("51", "MANDIOCA", 2.45, 134);
        adicionarProduto(mandioca, 0);
        
        ProdutoQuilo maca = new ProdutoQuilo("61", "BANANA", 3.50, 65);
        adicionarProduto(maca, 0);
        
        System.out.println("#                  ESTOQUE CRIADO COM SUCESSO!                  #");
        System.out.println("#################################################################");
    }
    
    
    public static void copiarEstoque(){
        copiaDoEstoque = estoque.entrySet().stream().collect(Collectors.toMap(e -> e.getKey(), e-> new LinkedList(e.getValue())));
    }
    
    
    public static void exibirCopiaInicialDoEstoque(){
        mostrarEstoque(2);
    }
}
