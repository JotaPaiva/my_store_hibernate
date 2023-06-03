package br.com.alura.loja.dao;

import br.com.alura.loja.modelo.Produto;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class ProdutoDAO {

    private EntityManager em;

    public ProdutoDAO(EntityManager em) {
        this.em = em;
    }

    public void cadastrar(Produto produto) {
        this.em.persist(produto);
    }

    public void atualizar(Produto produto) {
        this.em.merge(produto);
    }

    public void remover(Produto produto) {
        this.em.remove(produto);
    }

    public Produto buscarPorId(Long id) {
        return em.find(Produto.class, id);
    }

    public List<Produto> buscarTodos() {
        String jpql = "SELECT p FROM Produto p";
        return em.createQuery(jpql, Produto.class)
                .getResultList();
    }

    public List<Produto> buscarPorNomeDoProduto(String nomeDoProduto) {
        String jpql = "SELECT p FROM Produto p WHERE p.nome = ?1";
        return em.createQuery(jpql, Produto.class)
                .setParameter(1,nomeDoProduto)
                .getResultList();
    }

    public List<Produto> buscarPorNomeDaCategoria(String nomeDaCategoria) {
        String jpql = "SELECT p FROM Produto p WHERE p.categoria.nome = ?1";
        return em.createQuery(jpql, Produto.class)
                .setParameter(1,nomeDaCategoria)
                .getResultList();
    }

    public BigDecimal buscarPrecoDoProduto(String nomeDoProduto) {
        String jpql = "SELECT p.preco FROM Produto p WHERE p.nome = ?1";
        return em.createQuery(jpql, BigDecimal.class)
                .setParameter(1,nomeDoProduto)
                .getSingleResult();
    }

}
