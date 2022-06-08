/*

 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supermercado;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;


public class Supermercado{

    private static Scanner scanner = new Scanner(System.in);
    private static List<Funcionario> funcionarios = new ArrayList<Funcionario>();
    private static List<Caixa> caixas = new ArrayList<Caixa>();
    private static List<String> senhas = new ArrayList<String>()
    {{ add("1111"); add("1112"); add("1113"); add("1114"); add("1115"); }};
    
    public static void main(String[] args) {
        Saudacao();
        Feed();
        CriarFuncionarios();
        
        Gerente gerente = (Gerente)funcionarios.get(0);
        
        Boolean sairMenu = false;
        do{
            int chances = 3;
            int tentativas = 0;
            Utilitario.ImprimaMensagem("#                            ACESSO                             #");
            System.out.println(" ( 1 ) Gerente \n ( 2 ) Funcionário \n ( 3 ) Cliente \n ( 0 ) Sair do sistema");
            int opcao = scanner.nextInt();
        
            switch(opcao){
                case 1: // Gerente 
                    ControleMenuGerente(gerente, tentativas, chances);
                    break;
                case 2: // Funcionário (operador)
                    Boolean sairMenuOperador = false;
                    do{
                        MostrarMenuListaDeCaixas(caixas);
                        int opCaixa = scanner.nextInt();
                        
                        switch(opCaixa){
                            case 1:
                                Caixa c1 = caixas.get(0);
                                if (c1.getOperadorCaixa() == null) {
                                    Boolean acessouCaixa = false;
                                    
                                    do{
                                        System.out.println("Operador, digite sua senha");
                                        String senhaCaixa = scanner.next();
                                       
                                        if (senhas.contains(senhaCaixa)){
                                            Funcionario f = (Funcionario)funcionarios.stream().filter(x->x.getSenha().equals(senhaCaixa)).findFirst().get();
                                            
                                            if(FuncionarioLogado(senhaCaixa) == null){
                                                Utilitario.ImprimaMensagem("#               BEM VINDO AO CAIXA 1, " + f.getNome() + "               #");
                                                Utilitario.Continuar();
                                                c1.setOperadorCaixa(f);
                                                sairMenuOperador = true;
                                                acessouCaixa = true;
                                            }else{
                                                System.out.println("####    ATENÇÃO!!    ####\nOPERADOR " + f.getNome() + " ESTÁ LOGADO EM OUTRO CAIXA.");
                                            }
                                        }else{
                                            System.out.println("####    ATENÇÃO!    ####\nSENHA INCORRETA!.");
                                            tentativas++;
                                        }
                                    }while(tentativas < chances && !acessouCaixa);
                                    
                                }else{
                                    Utilitario.ImprimaMensagem("#                   LOGOUT EFETUADO NO CAIXA 1                  #",
                                                               "#                       CAIXA 1 ESTÁ LIVRE                      #");
                                    c1.setOperadorCaixa(null);
                                }
                                
                                break;
                            case 2:
                                Caixa c2 = caixas.get(1);
                                
                                if (c2.getOperadorCaixa() == null) {
                                    Boolean acessouCaixa = false;
                                   
                                    do{
                                        System.out.println("Operador, digite sua senha");
                                        String senhaCaixa = scanner.next();
                                        
                                        if (senhas.contains(senhaCaixa)){
                                            Funcionario f = (Funcionario)funcionarios.stream().filter(x->x.getSenha().equals(senhaCaixa)).findFirst().get();
                                            
                                            if(FuncionarioLogado(senhaCaixa) == null){
                                                Utilitario.ImprimaMensagem("#               BEM VINDO AO CAIXA 2, " + f.getNome() + "               #");
                                                Utilitario.Continuar();
                                                c2.setOperadorCaixa(f);
                                                sairMenuOperador = true;
                                                acessouCaixa = true;
                                            }else{
                                                System.out.println("####    ATENÇÃO!!    ####\\nOPERADOR " + f.getNome() + " ESTÁ LOGADO EM OUTRO CAIXA.");
                                            }
                                        }else{
                                            System.out.println("####    ATENÇÃO!    ####\nSENHA INCORRETA!.");
                                            tentativas++;
                                        }
                                    }while(tentativas < chances && !acessouCaixa);
                                    
                                }else{
                                    Utilitario.ImprimaMensagem("#                   LOGOUT EFETUADO NO CAIXA 2                  #",
                                                               "#                       CAIXA 2 ESTÁ LIVRE                      #");
                                    c2.setOperadorCaixa(null);
                                }
                                
                                break;
                            case 3:
                                Caixa c3 = caixas.get(2);
                                
                                if (c3.getOperadorCaixa() == null) {
                                    Boolean acessouCaixa = false;
                                    
                                    do{
                                        System.out.println("Operador, digite sua senha");
                                        String senhaCaixa = scanner.next();
                                        
                                        if (senhas.contains(senhaCaixa)){
                                            Funcionario f = (Funcionario)funcionarios.stream().filter(x->x.getSenha().equals(senhaCaixa)).findFirst().get();
                                            
                                            if(FuncionarioLogado(senhaCaixa) == null){
                                                Utilitario.ImprimaMensagem("#               BEM VINDO AO CAIXA 3, " + f.getNome() + "               #");
                                                Utilitario.Continuar();
                                                c3.setOperadorCaixa(f);
                                                sairMenuOperador = true;
                                                acessouCaixa = true;
                                            }else{
                                                System.out.println("####    ATENÇÃO!!    ####\\nOPERADOR " + f.getNome() + " ESTÁ LOGADO EM OUTRO CAIXA.");
                                            }
                                        }else{
                                            System.out.println("####    ATENÇÃO!    ####\nSENHA INCORRETA.");
                                            tentativas++;
                                        }
                                    }while(tentativas < chances && !acessouCaixa);
                                }else{
                                    Utilitario.ImprimaMensagem("#                   LOGOUT EFETUADO NO CAIXA 3                  #",
                                                               "#                       CAIXA 3 ESTÁ LIVRE                      #");
                                    c3.setOperadorCaixa(null);
                                }
                                break;
                            case 0:
                                sairMenuOperador = true;
                                break;
                            default:
                                break;
                        }        
                    }while(!sairMenuOperador);
                    
                    scanner.nextLine();
                    break;
                case 3: // cliente
                    if (ObtenhaCaixasDisponiveis().size() > 0) {
                        Boolean sairMenuCliente = false;
                        Cliente cli = new Cliente();
                        Utilitario.ImprimaMensagem("#                    BEM-VINDO  CARO CLIENTE!                   #",
                                                   "#               HOJE É UM ÓTIMO DIA PARA COMPRAS!               #");

                        do{
                            Boolean sairMenuEscolhaDeCaixas = false;
                            System.out.println(" ( 1 ) Escolher produtos \n ( 2 ) Comprar \n ( 3 ) Consultar preço \n ( 4 ) Consultar estoque de produtos \n ( 5 ) Ver Carrinho de Compras \n ( 0 ) Sair");
                            int opcaoCliente = scanner.nextInt();
                            double quantidade = 0;
                            switch (opcaoCliente){
                                case 1:
                                    boolean continuarComprando = true;
                                     Utilitario.ImprimaMensagem(
                                             "*          ( 0 ) Para voltar ao menu a qualquer momento!        *",
                                             "*          ( 1 ) Para Cancelar a compra!                        *");
                                    do{
                                        System.out.println("Digite o código do produto");
                                        String codigo = scanner.next();
                                            if(codigo.equals("0")){
                                                    break;
                                            }
                                            if(codigo.equals("1")){
                                                cli.getCarrinho().devolverProdutosCarrinho();
                                                break;
                                            }
                                        
                                        Produto p = EstoqueDeProdutos.seekProduto(codigo);
                                        if(p != null){

                                            boolean verificacao;
                                            if(p instanceof ProdutoUnitario){
                                                System.out.println("DIGITE A QUANTIDADE DE "+p.getNome().toUpperCase()+": ");
                                                quantidade = scanner.nextInt();
                                                if(quantidade == 0){
                                                    break;
                                                }
                                                verificacao = EstoqueDeProdutos.produtoParaCompra(codigo, quantidade, true);
                                                if(verificacao){
                                                    cli.getCarrinho().addProduto(p, quantidade);
                                                    EstoqueDeProdutos.removerProduto(codigo, quantidade);
                                                }  
                                            }
                                            if(p instanceof ProdutoQuilo){
                                                System.out.println("DIGITE A QUANTIDADE DE QUILOS DE "+p.getNome().toUpperCase()+": ");
                                                quantidade = scanner.nextDouble();
                                                //verifico se tem a quantidade do produto desejado
                                                verificacao = EstoqueDeProdutos.produtoParaCompra(codigo, quantidade, true);
                                                if(verificacao){
                                                    ProdutoQuilo pkg = (ProdutoQuilo) p;
                                                    cli.getCarrinho().addProduto(p, quantidade);
                                                    EstoqueDeProdutos.removerProduto(codigo, quantidade);
                                                } 
                                            }                                        
                                        } else{
                                            System.out.println("PRODUTO DE CÓDIGO " + codigo + " NÃO LOCALIZADO NO ESTOQUE.");
                                        }

                                        if(codigo.equals(0) || quantidade == 0 || quantidade == 1 || codigo.equals(1)){
                                            continuarComprando = false;
                                            if(codigo.equals(1)){
                                                cli.getCarrinho().devolverProdutosCarrinho();
                                            }
                                        }
                                        scanner.nextLine();
                                    }while(continuarComprando);

                                    sairMenuCliente = false;
                                    break;
                                case 2:
                                    
                                    if (cli.getCarrinho().verificaCarrinho()){
                                        int opcaoCaixaCompra;
                                        List<Caixa> caixasDisponiveis = ObtenhaCaixasDisponiveis();
                                        Utilitario.ImprimaMensagem("#                           CAIXAS                              #");
                                        do{
                                            Utilitario.ImprimaMensagem("#                    SELECIONE UM CAIXA                         #");
                                            MostrarCaixasEmFuncionamento();
                                            opcaoCaixaCompra = scanner.nextInt();

                                            if (opcaoCaixaCompra > 0 && opcaoCaixaCompra <= caixasDisponiveis.size()) {
                                                Caixa caixaSelecionado = caixasDisponiveis.get(Integer.valueOf(opcaoCaixaCompra)-1);

                                                Venda venda = caixaSelecionado.iniciarVenda(cli);

                                                sairMenuCliente = true;
                                                opcaoCaixaCompra = 0;
                                                Utilitario.ImprimaMensagem("#           OBRIGADO PELA PREFERENCIA! VOLTE SEMPRE!         #");
                                            }
                                        }while(opcaoCaixaCompra != 0);
                                    }else {
                                        Utilitario.ImprimaMensagem("#  SEU CARRINHO DE COMPRAS ESTÁ VAZIO. POR QUE NÃO OLHA NOSSAS OFERTAS  #");
                                        sairMenuEscolhaDeCaixas = true;
                                    }
                                    break;
                                case 3: // Consultar preços 
                                    Utilitario.ImprimaMensagem("#                   INFORME O CÓDIGO DO PRODUTO                #");
                                    String codigo = scanner.next();
                                    cli.consultarValor(codigo);
                                    break;
                                case 4: //Mostrar estoque do mercado
                                    EstoqueDeProdutos.mostrarEstoque(1);
                                    break;
                                case 5: //Mostrar Carrinho de compras
                                    Utilitario.ImprimaMensagem("#                   CARRINHO DE COMPRAS                    #");
                                    cli.getCarrinho().exibirCarrinhoCliente();
                                    break;
                                case 0: 
                                    sairMenuCliente = true;
                                    cli.getCarrinho().devolverProdutosCarrinho();
                                    break;
                                default:
                                    break;
                            }
                        }while(!sairMenuCliente);
                    }else {
                        Utilitario.ImprimaMensagem("#                NENHUM CAIXA ESTÁ LOGADO!!                 #");
                        break;
                    }
                    
                    scanner.nextLine();
                    break;
                case 0:
                    sairMenu = true;
                    break;
                default:
                    break;
            }
        }while(!sairMenu);
    }
   
    
    private static void Saudacao() {
        Utilitario.ImprimaMensagem("#   BEM VINDO AO NOSSO SISTEMA DE COMPRA E VENDA!!   #");
        Utilitario.Continuar();
    }

    
    private static void Feed() {
        EstoqueDeProdutos.Feed();
        Utilitario.Continuar();
    }
    
    
    private static void CriarFuncionarios(){
        Gerente gerente = new Gerente("GERENTE DO MERCADO", "admin", "admin" );
        OperadorDeCaixa funcionario1 = new OperadorDeCaixa("GABRIEL", "f1", "1111" );
        OperadorDeCaixa funcionario2 = new OperadorDeCaixa("MARQUINOS", "f2", "1122"  );
        OperadorDeCaixa funcionario3 = new OperadorDeCaixa("RONALDO", "f3", "1133" );
        OperadorDeCaixa funcionario4 = new OperadorDeCaixa("NATHAN", "f4", "1144"  );
        OperadorDeCaixa funcionario5 = new OperadorDeCaixa("MAROMO","f5", "1155" );

        funcionarios.add(gerente);          //[0]
        funcionarios.add(funcionario1);     
        funcionarios.add(funcionario2);     
        funcionarios.add(funcionario3);     
        funcionarios.add(funcionario4);
        funcionarios.add(funcionario5);

        //Criar caixas do supermercado
        Caixa c1 = new Caixa(01);
        Caixa c2 = new Caixa(02);
        Caixa c3 = new Caixa(03);
        
        caixas.add(c1); //[0]
        caixas.add(c2); //[1]
        caixas.add(c3); //[2]
    }
    
    
    private static void ControleMenuGerente(Gerente gerente, int tentativas, int chances) {
        Utilitario.ImprimaMensagem("#                             LOGIN                             #");
        Boolean acessou = false;
        
        do{
            System.out.println("DIGITE SUA SENHA: ");
            String senha = scanner.next();
            
            if (gerente.getSenha().equals(senha)) {
                acessou = true;
                
                Utilitario.ImprimaMensagem("              BEM VINDO!, " + gerente.getNome() + "!              ");
                MenuGerente(gerente);
            }
            else{
                System.out.println("A SENHA INSERIDA ESTÁ INCORRETA.");
                tentativas++;
            }
        }while(tentativas < chances && !acessou);
        
        if (tentativas >= chances && !acessou) {
            System.out.println("ACABARAM AS TENTATIVAS DE LOGIN.\nREALIZANDO A SAÍDA...");
            scanner.nextLine();
        }
    }
    
    
    private static void MenuGerente(Gerente gerente) {
        Boolean sairMenuGer = false;
        do{
            MostrarMenuGerente();
            int opcaoFunGer = scanner.nextInt();
            
            switch(opcaoFunGer){
                case 1:
                    MenuGerenteAdicionarProduto(gerente);
                    break;
                case 2: 
                    MenuGerenteRemoverProduto(gerente);
                    break;
                case 3: 
                    gerente.emitirRelatorioDeEstoque();
                    break;
                case 4: 
                    gerente.emitirRelatorioDeVendas(caixas);
                    break;
                case 0:
                    sairMenuGer = true;
                    break;
                default:
                    break;
            }
            scanner.nextLine();
        }while(!sairMenuGer);
    }

    private static void MenuGerenteRemoverProduto(Gerente gerente) {
        
        Utilitario.ImprimaMensagem("#                        REMOVER PRODUTOS                       #");
        System.out.println("DIGITE O CÓDIGO DO PRODUTO QUE SERÁ REMOVIDO:");
        String codigoProduto  = scanner.next();
        Produto p = EstoqueDeProdutos.seekProduto(codigoProduto);
        if(p != null){
            boolean verificacao;
            if(p instanceof ProdutoUnitario){
                System.out.println("DIGITE A QUANTIDADE DE "+p.getNome().toUpperCase()+": ");
                int quantidade = scanner.nextInt();
                if(quantidade == 0){
                    return;
                }
                verificacao = EstoqueDeProdutos.produtoParaCompra(codigoProduto, quantidade, false);
                if(verificacao){
                    EstoqueDeProdutos.removerProduto(codigoProduto, quantidade);
                    System.out.println(quantidade + " UNIDADES DE " + p.getNome() + " FORAM REMOVIDOS DO ESTOQUE.");
                    Utilitario.Continuar();
                }  
            }
            if(p instanceof ProdutoQuilo){
                System.out.println("DIGITE A QUANTIDADE DE KILOS DE " +p.getNome().toUpperCase()+ ": ");
                double quantidade = scanner.nextDouble();
                
                verificacao = EstoqueDeProdutos.produtoParaCompra(codigoProduto, quantidade, false);
                if(verificacao){
                    ProdutoQuilo pkg = (ProdutoQuilo) p;
                    EstoqueDeProdutos.removerProduto(codigoProduto, quantidade);
                    System.out.println(quantidade + " QUILOS DE" + p.getNome() + " FORAM REMOVIDOS DO ESTOQUE.");
                    Utilitario.Continuar();
                } 
            }
        }
    }
    
    
    private static void MostrarMenuGerente(){
        Utilitario.ImprimaMensagem("#                      MENU                          #");
        System.out.println(" ( 1 ) Adicionar produto no estoque \n ( 2 ) Remover produto \n ( 3 ) Emitir relatório de estoque \n ( 4 ) Emitir relatório de vendas \n ( 0 ) Logout ");
        System.out.println();
    }
    
    
    private static void MenuGerenteAdicionarProduto(Gerente gerente) {
        // adicionar produto
        Boolean sairMenuProduto = false;
        
        do{
            Utilitario.ImprimaMensagem("#    QUAL TIPO DE PRODUTO DESEJA ADICIONAR AO ESTOQUE?    #");
            System.out.println(" ( 1 ) Produto unidade \n ( 2 ) Produto quilo \n ( 0 ) Sair");
            int tipoProduto  = scanner.nextInt();
            
            switch (tipoProduto){
                case 1:
                    WizardAddProdutoUnidade(gerente);
                    break;
                case 2:
                    WizardAddProdutoQuilo(gerente);
                    break;
                case 0:
                    sairMenuProduto = true;
                    break;
                default:
                    break;
            }
        }while(!sairMenuProduto);
    }
    
   
    private static void MostrarMenuListaDeCaixas(List<Caixa> caixas){
        Utilitario.ImprimaMensagem("#                           CAIXAS                              #");
        Iterator i = caixas.iterator();
        int op = 1;
        while (i.hasNext()) {
            Caixa caixa = (Caixa)i.next();
            if (caixa.getOperadorCaixa() ==  null) { // se não tiver operador setado
                System.out.print(" ( " + op + " ) " + caixa +"\n");
            }
            else{
                System.out.print(" ( " + op + " ) LOGOUT " + caixa +" ("+caixa.getOperadorCaixa().getNome()+")\n");
            }    
            op++;
        }
        System.out.println(" ( 0 ) Sair");
        System.out.println("####################################################################");
    }
    
    
    private static Caixa FuncionarioLogado(String senha){
        return (Caixa)caixas.stream().filter(c->c.getOperadorCaixa() != null && c.getOperadorCaixa().getSenha().equals(senha)).findFirst().orElse(null);
    }
    
    
    private static List<Caixa> ObtenhaCaixasDisponiveis(){
        return caixas.stream().filter(c->c.getOperadorCaixa() != null).collect(Collectors.toList());
    }
    
    
    private static void MostrarCaixasEmFuncionamento(){
        if (ObtenhaCaixasDisponiveis().isEmpty()) {
            System.out.println("NENHUM CAIXA ESTÁ ATENDENDO NO MOMENTO! ");
        }
        else {
            Iterator i = ObtenhaCaixasDisponiveis().iterator();
            int op = 1;
            while (i.hasNext()) {
                Caixa caixa = (Caixa)i.next();
                System.out.print(" ( " + op + " ) " + caixa +"\n");
                op++;
            }
            System.out.println(" ( 0 ) Sair");
        }
    }
    
    
    private static void WizardAddProdutoUnidade(Gerente gerente) {
        Utilitario.ImprimaMensagem("#                    ADICIONANDO PRODUTO                        #");
        System.out.println("CÓDIGO DO PRODUTO:");
        String codigoProduto  = scanner.next();
        
        Produto produtoEmEstoque = EstoqueDeProdutos.seekProduto(codigoProduto);
        
        
        if (produtoEmEstoque != null) { 
            if (produtoEmEstoque instanceof ProdutoUnitario) {
                System.out.println("O PRODUTO DE CÓDIGO " + produtoEmEstoque.getCodigo() + "-" + produtoEmEstoque.getNome() + " JÁ ESTÁ CADASTRADO NO ESTOQUE!");
                AddProdutoUnitario(produtoEmEstoque, gerente);
            }
            else{
                System.out.println("ESSE CÓDIGO CORRESPONDE A UM PRODUTO DO TIPO KG (" + produtoEmEstoque.getNome() + "). DESEJA CONTINUAR?");
                System.out.println(" ( 1 ) Sim \n ( 2 ) Não ");
                int opcaoContinuar  = scanner.nextInt();
                switch(opcaoContinuar){
                    case 1:
                        AddProdutoQuilo(produtoEmEstoque, gerente);
                        break;
                    case 2:
                        break;
                    default:
                        break;
                }
            }
        } else {
            System.out.println("NOME DO PRODUTO:");
            String nomeProduto  = scanner.next();
            System.out.println("PREÇO DA UNIDADE DO PRODUTO:");
            double precoProduto  = scanner.nextDouble();
            System.out.println("QUANTIDADE DE ITENS:");
            int quantidadeProduto  = scanner.nextInt();
            ProdutoUnitario produto = new ProdutoUnitario(codigoProduto + "0", nomeProduto, precoProduto);
            System.out.println("ADICIONADO " + quantidadeProduto + " UNIDADES DO PRODUTO: '" + produto.getCodigo() + "- " + produto.getNome() + "'");
            gerente.adicionarProduto(produto, quantidadeProduto);
        }
    }
    
   
    private static void WizardAddProdutoQuilo(Gerente gerente) {
        Utilitario.ImprimaMensagem("#                    ADICIONANDO PRODUTO                        #");
        System.out.println("CÓDIGO DO PRODUTO:");
        String codigoProduto  = scanner.next();
        
        Produto produtoEmEstoque = EstoqueDeProdutos.seekProduto(codigoProduto);
        
        if (produtoEmEstoque != null) { 
            if (produtoEmEstoque instanceof ProdutoQuilo) {
                System.out.println("O PRODUTO DE CÓDIGO " + produtoEmEstoque.getCodigo() + "-" + produtoEmEstoque.getNome() + " JÁ ESTÁ CADASTRADO NO ESTOQUE!");
                AddProdutoQuilo(produtoEmEstoque, gerente);
            }
            else{
                System.out.println("ESSE CODIGO CORRESPONDE A M PRODUTO DO TIPO UND. DESEJA CONTINUAR?");
                System.out.println(" ( 1 ) Sim \n ( 2 ) Não ");
                int opcaoContinuar  = scanner.nextInt();
                switch(opcaoContinuar){
                    case 1:
                        AddProdutoUnitario(produtoEmEstoque, gerente);
                        break;
                    case 2:
                        break;
                    default:
                        break;
                }
            }
        } else {
            System.out.println("NOME DO PRODUTO:");
            String nomeProduto  = scanner.next();
            System.out.println("PREÇO DO QILO DE " + nomeProduto + " :");
            double precoProduto  = scanner.nextDouble();
            System.out.println("KG DE " + nomeProduto + ":");
            double quantidadeProduto  = scanner.nextDouble();
            ProdutoQuilo produto = new ProdutoQuilo(codigoProduto + "0", nomeProduto, precoProduto, quantidadeProduto);
            System.out.println("ADICIONADO " + quantidadeProduto + " KG DE: '" + produto.getCodigo() + "- " + produto.getNome() + "'");
            gerente.adicionarProduto(produto, 0);
        }
    }
    
    private static void AddProdutoUnitario(Produto produtoEmEstoque, Gerente gerente) {
        System.out.println("DESEJA ADICIONAR QUANTOS ITENS?");
        int quantidadeProduto  = scanner.nextInt();
        System.out.println("ADICIONADO " + quantidadeProduto + " UNIDADE DO PRODUTO: '" + produtoEmEstoque.getCodigo() + "- " + produtoEmEstoque.getNome() + "'");
        gerente.adicionarProduto(produtoEmEstoque, quantidadeProduto);
        Utilitario.Continuar();
    }
    
    private static void AddProdutoQuilo(Produto produtoEmEstoque, Gerente gerente) {
        System.out.println("DESEJA ADICIONAR QUANTOS KG DE " + produtoEmEstoque.getNome() + "?");
        double quantidadeProduto  = scanner.nextDouble();
        System.out.println("ADICIONADO " + quantidadeProduto + " KG DE: '" + produtoEmEstoque.getCodigo() + "- " + produtoEmEstoque.getNome() + "'");
        gerente.adicionarProduto(produtoEmEstoque, quantidadeProduto);
        Utilitario.Continuar();
    }
    
    public static void caixasDisponiveis(){
        System.out.println("##############################################################");
        System.out.println("##            ESCOLHA UM CAIXA PARA A COMPRA                ##");
        System.out.println("##############################################################");
        CriarFuncionarios();
        MostrarCaixasEmFuncionamento();
    }
    
}
