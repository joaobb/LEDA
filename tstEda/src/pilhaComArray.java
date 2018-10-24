import java.util.Scanner;

class pilhaComArray {
    private int[] arr;
    private int top;
    private int tam;

    public pilhaComArray(int tam) {
        this.arr = new int[tam];
        this.tam = tam;
        this.top = -1;
    }

    public void push(int numb) {
        this.isFull();
        this.arr[++top] = numb;
    }

    public int peek() {
        this.isEmpty();
        return this.arr[top];
    }

    public String print() {
        this.isEmpty();
        String saida = "";
        for (int i = 0; i <= this.top; i++) {
            saida += this.arr[i] + " ";
        }
        return saida.trim();
    }

    public int pop() {
        int result = this.peek();
        this.arr[top--] = -1;
        return result;
    }

    private void isEmpty() {
        if (this.top == -1) throw new UnsupportedOperationException("empty");
    }

    private void isFull() {
        if (this.top >= this.tam) throw new UnsupportedOperationException("full");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        pilhaComArray pca = new pilhaComArray(sc.nextInt());
        String ent;

        do {
            ent = sc.nextLine();
            try {
                if (ent.startsWith("push"))
                    pca.push(Integer.parseInt(ent.split(" ")[1]));
                else if (ent.equals("peek"))
                    System.out.println(pca.peek());
                else if (ent.equals("print"))
                    System.out.println(pca.print());
                else if (ent.equals("pop"))
                    System.out.println(pca.pop());
            } catch (UnsupportedOperationException e) {
                System.out.println(e.getMessage());
            }
        } while (!ent.equals("end"));
    }
}
