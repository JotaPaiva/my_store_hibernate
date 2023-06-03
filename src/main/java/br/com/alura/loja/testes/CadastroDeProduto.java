package br.com.alura.loja.testes;

import br.com.alura.loja.dao.CategoriaDAO;
import br.com.alura.loja.dao.ProdutoDAO;
import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.util.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class CadastroDeProduto {

    public static void main(String[] args) {

        cadastrarProduto();

        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDAO produtoDAO = new ProdutoDAO(em);

        Produto p = produtoDAO.buscarPorId(1l);
        System.out.println(p.getPreco());

        List<Produto> todos = produtoDAO.buscarPorNomeDaCategoria("CELULARES");
        todos.forEach(p2 -> System.out.println(p2.getNome()));

        BigDecimal precoDoProduto = produtoDAO.buscarPrecoDoProduto("Samsung Galaxy SE");
        System.out.println(precoDoProduto);

    }

    private static void cadastrarProduto() {

        Categoria celulares = new Categoria("CELULARES");
        Produto celular = new Produto("Samsung Galaxy SE","Cor: Azul, Armazenamento: 128GB, Memória RAM: 6GB",new BigDecimal(2000), celulares);

        EntityManager em = JPAUtil.getEntityManager();
        CategoriaDAO categoriaDAO = new CategoriaDAO(em);
        ProdutoDAO produtoDAO = new ProdutoDAO(em);

        em.getTransaction().begin();

        categoriaDAO.cadastrar(celulares);
        produtoDAO.cadastrar(celular);
        em.getTransaction().commit();
        em.close();

    }

}