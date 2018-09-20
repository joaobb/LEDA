package vetor;

import java.util.Comparator;

/**
 * Implementação de um vetor de objetos simples para exercitar os conceitos de
 * Generics.
 * 
 * @author Adalberto
 *
 */
public class Vetor <T> {

	// O array interno onde os objetos manipulados são guardados
	private T[] arrayInterno;

	// O tamanho que o array interno terá
	private int tamanho;

	// Indice que guarda a proxima posição vazia do array interno
	private int indice;

	// O Comparators a serem utilizados
	private Comparator comparadorMaximo;
	private Comparator comparadorMinimo;

	public Vetor(int tamanho) {
		super();
		this.arrayInterno = new <T>[this.tamanho];
		this.tamanho = tamanho;
		this.indice = -1;
	}

	public void setComparadorMaximo(Comparator comparadorMaximo) {
		this.comparadorMaximo = comparadorMaximo;
	}

	public void setComparadorMinimo(Comparator comparadorMinimo) {
		this.comparadorMinimo = comparadorMinimo;
	}

	// Insere um objeto no vetor
	public void inserir(T o) {
		if (!this.isCheio()) {
			this.arrayInterno[++indice] = o;
		}
	}

	// Remove um objeto do vetor
	public T remover(T o) {
		T retorno = null;
		for (int i = 0; i < this.tamanho; i++) {
			if (this.arrayInterno[i].equals(o)) {
				retorno = this.arrayInterno[i] = null;
			}
		}
		return retorno;
	}

	// Procura um elemento no vetor
	public Object procurar(Object o) {
		Object retorno = null;
		for (Object x : this.arrayInterno) {
			if (o.equals(x)) retorno = x;
		}
		return retorno;
	}

	// Diz se o vetor está vazio
	public boolean isVazio() {
		boolean retorno;
		if (this.indice == -1) retorno = true;
		else retorno = false;

		return retorno;
	}

	// Diz se o vetor está cheio
	public boolean isCheio() {
		boolean retorno;
		if (this.indice - 1 == tamanho) retorno = true;
		else retorno = false;

		return retorno;
	}

}
