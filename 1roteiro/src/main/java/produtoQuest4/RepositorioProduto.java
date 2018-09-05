package produtoQuest4;

import java.util.ArrayList;
import java.util.List;

public class RepositorioProduto {

    private List<Produto> produtos;
    private int index = -1;

    public RepositorioProduto() {
        this.produtos = new ArrayList<>();
    }

    private int procuraIndice(int codigo) {
        int retorno = -1;
        Produto produto;

        for (int i = 0; i < this.produtos.size(); i++) {
            produto = (Produto) produtos.get(i);
            if (produto.getCodigo() == codigo) {
                retorno = i;
            }
        }
        return  retorno;
    }

    public boolean existe(int codigo) {
        boolean existe = false;

        if (this.procuraIndice(codigo) !=1) {
            existe = true;
        }
        return existe;
    }

    public void inserir(Produto produto) {
        this.produtos.add(produto);
        this.index++;
    }

    public void atualizar(Produto produto) {
        int indiceProduto = this.procuraIndice(produto.getCodigo());

        if (indiceProduto != -1) {
            this.produtos.set(indiceProduto, produto);
        }
        else {
            throw new RuntimeException("Produto nao encontrado");
        }

    }

    public void remover(int codigo) {
        int indiceProduto = this.procuraIndice(codigo);

        if (indiceProduto != -1) {
            Produto ultimoProduto = (Produto) this.produtos.get(index++);
            this.produtos.set(indiceProduto, ultimoProduto);
            this.produtos.remove(ultimoProduto);
        } else {
            throw new RuntimeException("Produto nao encontrado");
        }
    }


    public Produto procurar(int codigo) {
        Produto retorno = null;
        int indiceProduto = this.procuraIndice(codigo);

        if (indiceProduto != -1) {
            retorno = (Produto) this.produtos.get(indiceProduto);
        }
        else {
            throw new RuntimeException("Produto nao encotrado");
        }
        return retorno;
    }
}
