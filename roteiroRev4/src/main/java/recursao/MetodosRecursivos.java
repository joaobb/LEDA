package recursao;

public class MetodosRecursivos {

	public int calculaSomaArray(int[] array, int index) {
		int result = 0;

		if (index == array.length - 1){
			result = array[index];
		} else result += array[index++] + this.calculaSomaArray(array, index);

		return result;
	}

	public int calcularSomaArray(int[] array){
		return this.calculaSomaArray(array, 0);
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
		long result;

		if (expoente == 0) {
			result = 1;
		} else {
			result = 2 * this.potenciaDe2(--expoente);
		}
		return result;
	}

	public double progressaoAritmetica(double termoInicial, double razao, int n) {
		double result;

		if (n == 1) {
			result = termoInicial;
		} else {
			result = this.progressaoAritmetica(termoInicial, razao,  --n) + razao;
		}
		return result;
	}

	public double progressaoGeometrica(double termoInicial, double razao, int n) {
		double result = 0;

		if (n == 1) {
			result = termoInicial;
		} else {
			result = this.progressaoGeometrica(termoInicial, razao,  --n) * razao;
		}
		return result;
	}
	
	
}
