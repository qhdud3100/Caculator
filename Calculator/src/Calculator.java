import java. util.Stack;

public class Calculator {

	
	public float calPostfix(String input) {
//		System.out.println(input);
		String [] equation=input.split(" ");
        Stack<Float> stack = new Stack<>();
//        for(int i=0;i<equation.length;i++) {
//        	System.out.println(equation[i]);
//        }

        
        for (int i = 0; i <equation.length; i++) {
 
        	String s = equation[i];
            if (is_operator(s)) {
 
				float a=stack.pop();
				float b=stack.pop();


	             switch (s) {
	
	             case "+":
	                 stack.push(a+b);
	                 break;
	             case "-":
	                 stack.push(a-b);
	
	                 break;
	             case "×":
	                 stack.push(a*b);
	                 break;
	             case "÷":
	                 stack.push(b/a);
	                 break;
	             case "%":
	                 stack.push(b%a);
	                 break;    
	             }

            } else {
                stack.push(Float.parseFloat(s));
            }
        }
        float result=stack.pop();
        return result;
    }
	
	public static boolean is_operator(String c){ 
		if(c.equals("+")||c.equals("-")||c.equals("÷")||c.equals("×")||c.equals("%")) {
			return true;
		}else {
			return false;
			
		}
		
	}
	
	
	public int getOpPrec(char op) {
		 
        switch (op) {
        case '×':
        case '÷':
            return 2;
 
        case '+':
        case '-':
            return 1;
 

        }
        return -1;
    }
    public boolean isProceed(char op1, char op2) {
    	 
        int op1Prec = getOpPrec(op1);
        int op2Prec = getOpPrec(op2);
 
        if (op1Prec >= op2Prec)
            return true;
        else
            return false;
 
    }
	
	
    public String convToExpression(String exp) {

        Stack<Character> stack = new Stack<>();
        int len = exp.length();
        String postFix = "";
 
        for (int i = 0; i < len; i++) {
        	char ch = exp.charAt(i);
        	String s=Character.toString(ch);
           
            if (!is_operator(s)) { 
            	
                postFix += ch;
 
            } else {
            	postFix+=" ";
            	while (!stack.isEmpty() && isProceed(stack.peek(), ch)) {
            		postFix += stack.pop();
            		postFix+=" ";
	
            	}
                    stack.push(ch);

            }
        }
 
        while (!stack.isEmpty()) {
        	postFix+=" ";
            postFix += stack.pop();
        }
        return postFix;
 
    }

	
//	public static void main(String [] args){
//		System.out.println("enter the equation");
//		Scanner keyboard= new Scanner(System.in);
//		String equation=keyboard.nextLine();
//		Calculator c = new Calculator();
//		String postFix=c.convToExpression(equation);
//		System.out.println(postFix);
//		int result=c.calPostfix(postFix);
//		System.out.println(result);
//	}
}


