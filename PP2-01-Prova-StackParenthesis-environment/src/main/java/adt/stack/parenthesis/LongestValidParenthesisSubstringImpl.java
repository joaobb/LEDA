package adt.stack.parenthesis;

import java.util.Stack;

/**
 * @author Cláudio Campelo
 * Ver comentários na interface LongestValidParenthesisSubstring.
 */
public class LongestValidParenthesisSubstringImpl implements LongestValidParenthesisSubstring {

    /* (non-Javadoc)
     * @see adt.stack.parenthesis.LongestValidParenthesisSubstring#findLongest(java.lang.String)
     */
    public String findLongest(String parenthesis) {
        Stack<Character> pilha = new Stack<>();
        String saida = "";
        String aux = "";
        boolean onlyValidChars = true;
        char auxChar;
        int cont = 0;

        /*
         * Pushing recieved string as characters into the stack.
         * If it finds something other th*n parenthesis, it will end the loop
         * and tell the program that some strange char was found.
         */
        for (int i = 0; i < parenthesis.length() && onlyValidChars; i++) {
            if (parenthesis.charAt(i) != '(' && parenthesis.charAt(i) != ')')
                onlyValidChars = false;
            pilha.push(parenthesis.charAt(i));
        }

        while (!pilha.isEmpty() && onlyValidChars) {
            auxChar = pilha.pop();
            if (auxChar == ')') cont++;
            else cont--;

            if (cont < 0) {
                if (aux.length() > saida.length() && aux.length() > 1)
                    saida = parenthesisMirror(aux);
                aux = "";
                cont = 0;

            } else {
                if (auxChar == '(') auxChar = ')';
                else auxChar = '(';
                aux += auxChar;
            }
        }

        if (aux.length() > saida.length() && aux.length() > 1)
            saida = parenthesisMirror(aux);

        if (!onlyValidChars) saida = null;
        return saida;
    }

    /*
     * Este é um método utilitário que calcula o "espelho" de uma String.
     * Ou seja, é uma espécie de flip horizontal. Veja os exemplos abaixo
     * para entender como o método deve ser comportar.
     *
     * A implementação deste método não é obrigatória, porém, é fortemente
     * recomendada, visto que pode ser muito útil para a implementação
     * do findLongest.
     *
     * Neste método, implemente uma solução baseada em PILHA.
     *
     * Exemplo 1:
     * input		   = ((()
     * expected_output = ()))
     *
     * Exemplo 2:
     * input		   = ()()
     * expected_output = ()()
     *
     * Exemplo 2:
     * input		   = (((((
     * expected_output = )))))
     */
    private String parenthesisMirror(String str) {

        Stack<Character> stack = new Stack<>();
        String mirrorStr = "";

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ')')
                stack.push('(');
            else
                stack.push(')');
        }

        while (!stack.isEmpty())
            mirrorStr += stack.pop();
        return mirrorStr;
    }

    public static void main(String[] args) {
        LongestValidParenthesisSubstringImpl l = new LongestValidParenthesisSubstringImpl();
        System.out.println(l.findLongest("()"));
        System.out.println(l.findLongest(")("));
        System.out.println(l.findLongest(")(()()("));
        System.out.println(l.findLongest("((()))(("));
        System.out.println(l.findLongest("((()))(()((()()"));
        System.out.println(l.findLongest("((()))()((()()"));
        System.out.println(l.findLongest("((()))((zap)((()()"));

    }
}
