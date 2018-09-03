package recursao;

public class MetodosRecursivos {

	public int calcularSomaArray(int[] array){
		int result = 0;
		// TODO ESCREVA AQUI O CÓDIGO (USANDO RECURSAO) PARA CALCULAR A SOMA
		// DOS EMENTOS DE UM ARRAY
		return result;
	}
	public long calcularFatorial(int n) {
		long result;
		if (n == 1 || n == 0) {
			result = 1;
		} else {
			result = n * this.calcularFatorial(n - 1);
		}
		return result;
	}

	public int calcularFibonacci(int n) {
		int result;

		if(n == 1 || n == 2) {
			result = 1;
		} else {
			result = this.calcularFibonacci(n - 1) + this.calcularFibonacci(n - 2);
		}
		return result;
	}

	public int countNotNull(Object[] array, int index) {
		int result = 0;

		if (array[index] != null) result = 1;
		if (array.length - 1 != index) result += this.countNotNull(array, ++index);

		return result;
	}

	public int countNotNull(Object[] array) {
		return this.countNotNull(array, 0);
	}

	public long potenciaDe2(int expoente) {
		int result = 1;
		// TODO IMPLEMENTE (USANDO RECURSAO) O CODIGO PARA PRODUZIR A N-ESIMA
		// POTENCIA
		// DE 2
		return result;
	}

	public double progressaoAritmetica(double termoInicial, double razao, int n) {
		double result = 0;
		// TODO IMPLEMENTE SEU CODIGO (USANDO RECURSAO) DE ENCONTRAR O n-ESIMO
		// TERMO
		// DA PROGRESSAO ARITMETICA, DADO O TERMO INICIAL E A RAZAO
		return result;
	}

	public double progressaoGeometrica(double termoInicial, double razao, int n) {
		double result = 1;
		// TODO IMPLEMENTE SEU CODIGO (USANDO RECURSAO) DE ENCONTRAR O n-ESIMO
		// TERMO
		// DA PROGRESSAO GEOMETRICA, DADO O TERMO INICIAL E A RAZAO
		return result;
	}
	
	
}
