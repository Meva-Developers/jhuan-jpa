package br.com.alura.loja.testes;
import br.com.alura.loja.dao.ProdutoDao;
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
        ProdutoDao produtoDao = new ProdutoDao(em);
        Produto p = produtoDao.buscarPorId(1l);
        System.out.println(p.getPreco());

        List<Produto> todos = produtoDao.buscarTodos();
        todos.forEach(p2 -> System.out.println(p2.getNome()));

    }

    private static void cadastrarProduto() {
        Categoria categoria = new Categoria("CELULARES");
        Produto celular = new Produto("Xiaomi Redmi", "Muito legal", new BigDecimal("800"), categoria);


        EntityManager em = JPAUtil.getEntityManager();

        em.getTransaction().begin();

        em.persist(categoria);
        categoria.setNome("XPTO");

        em.flush();
        em.clear();

        categoria = em.merge(categoria);
        categoria.setNome("1234");
        em.flush();
        em.clear();
        em.remove(categoria);
        em.flush();
    }

}
