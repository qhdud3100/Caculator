import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.SwingConstants;


public class frame extends JFrame implements ActionListener, Runnable{
	private String equation;
	private JLabel label=new JLabel("0");
	private String button;
	private boolean enter;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame frame = new frame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/*
	 * 기본기능: +-/*%등 기본 연산자, 초기화, 소수점 
	 * 추가기능: 하나씩 지우기(C), 연산자 중복불가능, 1회 계산 마친 후 숫자 입력시 자동 초기화, 0일때 연산자 누르면 이어쓰고 피연산자 누르면 새롭게 
	 */
	
	public void actionPerformed(ActionEvent e) {
		equation=label.getText();
		button=e.getActionCommand();
		if(button.equals("=")) {
			enter=true;
			String result=calculate(equation);
			label.setText(result);
		}else if(button.equals("AC")) {
			label.setText("0");
		}else if(button.equals("exit")) {
			frame f=new frame();
			Thread thread1=new Thread(f);	
			label.setText("사용해주셔서 감사합니다.");
			label.setForeground(Color.RED);
			label.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
			thread1.start();	

			
			
		}else if(button.equals("C")) {
			if(equation.length()==1) {
				label.setText("0");
			}else {
				label.setText(equation.substring(0, equation.length()-1));
			}
		}else if(is_operator(button)&& is_operator(equation.substring(equation.length()-1, equation.length()))){ //연속된 연산자
			equation=equation.substring(0, equation.length()-1);
			equation+=button;
			label.setText(equation);
		}else { //안겹칠때
			if(equation.equals("0")||(enter==true&&(!is_operator(button)))) {
				if(is_operator(button)) {
					equation+=button;
					label.setText(equation);
				}else {
				label.setText(button);
				}

			}else {
				equation+=button;
				label.setText(equation);
			}
			enter=false;
		}
	}
	public void run() {
		synchronized(this){

			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

			}
				System.exit(-1);
			
		}
		
	}
	
	public boolean is_operator(String s){ //겹치면 안되는 것들
		String operator= new String("+-×÷%.");
		if(operator.contains(s)) {
			return true;
		}else {
			return false;
		}
		
	}

	public String calculate(String equation) {
		String result = null;
		Calculator c= new Calculator();
		String postFix=c.convToExpression(equation);
		float resultDouble=c.calPostfix(postFix);
		if(resultDouble%1==0) {
			int resultInt= (int)resultDouble;
			result=Integer.toString(resultInt);
		}else {
			result=Float.toString(resultDouble);
		}
		return result;
		
	}	
	
	public frame() {
		
		getContentPane().setBackground(Color.BLACK);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 328, 572);
		getContentPane().setLayout(null);
		
		JButton button_4 = new JButton("2");
		button_4.addActionListener(this);
		button_4.setBackground(Color.WHITE);
		button_4.setFont(new Font("Apple LiGothic", Font.PLAIN, 30));
		button_4.setBounds(87, 392, 70, 70);
		getContentPane().add(button_4);
		
		JButton btnNewButton = new JButton("0");
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setFont(new Font("Apple LiGothic", Font.PLAIN, 30));
		btnNewButton.addActionListener(this);
		btnNewButton.setBounds(87, 474, 70, 70);
		getContentPane().add(btnNewButton);
		
		JButton exitButton = new JButton("exit");
		exitButton.setBackground(Color.WHITE);
		exitButton.setForeground(Color.RED);
		exitButton.setFont(new Font("Apple LiGothic", Font.PLAIN, 30));
		exitButton.addActionListener(this);
		exitButton.setBounds(6, 474, 70, 70);
		getContentPane().add(exitButton);
		
		JButton button_1 = new JButton(".");
		button_1.setBackground(Color.WHITE);
		button_1.setFont(new Font("Apple LiGothic", Font.PLAIN, 30));
		button_1.addActionListener(this);
		button_1.setBounds(169, 474, 70, 70);
		getContentPane().add(button_1);
		
		JButton button_2 = new JButton("=");
		button_2.setForeground(Color.BLACK);
		button_2.setBackground(Color.ORANGE);
		button_2.setFont(new Font("Apple LiGothic", Font.PLAIN, 30));
		button_2.addActionListener(this);
		button_2.setBounds(251, 474, 70, 70);
		getContentPane().add(button_2);
		
		JButton button_3 = new JButton("1");
		button_3.addActionListener(this);
		button_3.setBackground(Color.WHITE);
		button_3.setFont(new Font("Apple LiGothic", Font.PLAIN, 30));
		button_3.setBounds(6, 392, 70, 70);
		getContentPane().add(button_3);
		
		JButton button_5 = new JButton("3");
		button_5.addActionListener(this);
		button_5.setBackground(Color.WHITE);
		button_5.setFont(new Font("Apple LiGothic", Font.PLAIN, 30));
		button_5.setBounds(169, 392, 70, 70);
		getContentPane().add(button_5);
		
		JButton button_6 = new JButton("+");
		button_6.setForeground(Color.BLACK);
		button_6.setBackground(Color.ORANGE);
		button_6.setFont(new Font("Apple LiGothic", Font.PLAIN, 35));
		button_6.addActionListener(this);
		button_6.setBounds(251, 392, 70, 70);
		getContentPane().add(button_6);
		
		JButton button_7 = new JButton("4");
		button_7.setBackground(Color.WHITE);
		button_7.setFont(new Font("Apple LiGothic", Font.PLAIN, 30));
		button_7.addActionListener(this);
		button_7.setBounds(6, 310, 70, 70);
		getContentPane().add(button_7);
		
		JButton button_8 = new JButton("5");
		button_8.setBackground(Color.WHITE);
		button_8.setFont(new Font("Apple LiGothic", Font.PLAIN, 30));
		button_8.addActionListener(this);
		button_8.setBounds(87, 310, 70, 70);
		getContentPane().add(button_8);
		
		JButton button_9 = new JButton("6");
		button_9.setBackground(Color.WHITE);
		button_9.setFont(new Font("Apple LiGothic", Font.PLAIN, 30));
		button_9.addActionListener(this);
		button_9.setBounds(169, 310, 70, 70);
		getContentPane().add(button_9);
		
		JButton button_10 = new JButton("-");
		button_10.setForeground(Color.BLACK);
		button_10.setBackground(Color.ORANGE);
		button_10.setFont(new Font("Apple LiGothic", Font.BOLD, 40));
		button_10.addActionListener(this);
		button_10.setBounds(251, 310, 70, 70);
		getContentPane().add(button_10);
		
		JButton button_11 = new JButton("7");
		button_11.addActionListener(this);
		button_11.setBackground(Color.WHITE);
		button_11.setFont(new Font("Apple LiGothic", Font.PLAIN, 30));
		button_11.setBounds(6, 228, 70, 70);
		getContentPane().add(button_11);
		
		JButton button_12 = new JButton("8");
		button_12.setBackground(Color.WHITE);
		button_12.setFont(new Font("Apple LiGothic", Font.PLAIN, 30));
		button_12.addActionListener(this);
		button_12.setBounds(87, 228, 70, 70);
		getContentPane().add(button_12);
		
		JButton button_13 = new JButton("9");
		button_13.setBackground(Color.WHITE);
		button_13.setFont(new Font("Apple LiGothic", Font.PLAIN, 30));
		button_13.addActionListener(this);
		button_13.setBounds(169, 228, 70, 70);
		getContentPane().add(button_13);
		
		JButton button_14 = new JButton("×");
		button_14.setForeground(Color.BLACK);
		button_14.setBackground(Color.ORANGE);
		button_14.setFont(new Font("Apple LiGothic", Font.PLAIN, 30));
		button_14.addActionListener(this);
		button_14.setBounds(251, 228, 70, 70);
		getContentPane().add(button_14);
		
		JButton btnAc = new JButton("AC");
		btnAc.setBackground(Color.LIGHT_GRAY);
		btnAc.setFont(new Font("Apple LiGothic", Font.PLAIN, 30));
		btnAc.addActionListener(this);
		btnAc.setBounds(6, 146, 70, 70);
		getContentPane().add(btnAc);
		
		JButton btnC = new JButton("C");
		btnC.setBackground(Color.LIGHT_GRAY);
		btnC.setFont(new Font("Apple LiGothic", Font.PLAIN, 30));
		btnC.addActionListener(this);
		btnC.setBounds(87, 146, 70, 70);
		getContentPane().add(btnC);
		
		JButton button_15 = new JButton("%");
		button_15.setForeground(Color.BLACK);
		button_15.setBackground(Color.ORANGE);
		button_15.setFont(new Font("Apple LiGothic", Font.PLAIN, 30));
		button_15.addActionListener(this);
		button_15.setBounds(169, 146, 70, 70);
		getContentPane().add(button_15);
		
		JButton button_18 = new JButton("÷");
		button_18.setForeground(Color.BLACK);
		button_18.setBackground(Color.ORANGE);
		button_18.setFont(new Font("Apple LiGothic", Font.PLAIN, 30));
		button_18.addActionListener(this);
		button_18.setBounds(251, 146, 70, 70);
		getContentPane().add(button_18);
		
		
		label.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		label.setForeground(Color.WHITE);
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setBounds(17, 68, 293, 66);
		getContentPane().add(label);
	}
}