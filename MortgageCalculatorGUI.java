import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MortgageCalculatorMenu extends JFrame implements ActionListener
{
	JPanel Panel_Top = new JPanel(); //  panels
	JPanel Panel_Middle = new JPanel(); //  panels
	JPanel Panel_Bottom = new JPanel(); //  panels
	
	JLabel Label_Loan = new JLabel("Loan: "); //  labels
	JLabel Label_Rate = new JLabel("Rate: "); //  labels
	JLabel Label_Term = new JLabel("Term: "); //  labels
	
	JTextField TextFields_Loan = new JTextField(10); //  text fields
	JTextField TextFields_Rate = new JTextField(10); //  text fields
	JTextField TextFields_Term = new JTextField(10); //  text fields
	 
	JMenuBar menuBar = new JMenuBar(); 	//  menu bar for options
	
	JMenu OptionsMenu = new JMenu("Options"); 	//  menu to be places on menu bar
	
	JMenuItem option1 = new JMenuItem("7 year at 5.35%"); 	//  individual menu items for each options
	JMenuItem option2 = new JMenuItem("15 year at 5.5%"); 	//  individual menu items for each options
	JMenuItem option3 = new JMenuItem("30 year at 5.75%"); 	//  individual menu items for each options
	
	JTextArea TextArea_Results = new JTextArea(10, 35); 	//  JTextArea
	
	JScrollPane Panel_Scroll = new JScrollPane(TextArea_Results, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); 	//  JScrollPane to hold JTextArea
	
	JButton Button_Calculate = new JButton("Calculate"); 	//  buttons for P_Buttons panel
	JButton Button_Reset = new JButton("Reset");           //  buttons for P_Buttons panel
	JButton Button_Exit = new JButton("Exit");             //  buttons for P_Buttons panel
	
	MortgageCalculatorMenu()
	{	
		setTitle("Mortgage Calculator"); 		//  title for frame
		
		setPreferredSize(new Dimension(500, 285)); 		//  width and height for frame
		
		setResizable(true);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 		//  frame to exit the program when user hits 'X' button 
		
		setLayout(new BorderLayout());
		
		setJMenuBar(menuBar); 		// menu bar to frame
		
		menuBar.add(OptionsMenu); 		// menu to menu bar
		
		OptionsMenu.add(option1); 		//  options to option menu
		OptionsMenu.add(option2); 		//  optons to optiion menu
		OptionsMenu.add(option3); 		//  options to option menu
		
		Panel_Top.add(Label_Loan); 		//  content for top  panel
		Panel_Top.add(TextFields_Loan); 		//  content for top panel
		
		Panel_Top.add(Label_Rate); 		//  content for top  panel
		Panel_Top.add(TextFields_Rate); 		//  content for top panel
		
		Panel_Top.add(Label_Term); //  content for top  panel
		Panel_Top.add(TextFields_Term); //  content for top  panel
		
		
		TextArea_Results.setEditable(false); //  content for middle  panel
		Panel_Middle.add(Panel_Scroll); //  content for middle  panel
		
		Button_Reset.setPreferredSize(Button_Calculate.getPreferredSize());
		Button_Exit.setPreferredSize(Button_Calculate.getPreferredSize());
		
		Panel_Bottom.add(Button_Calculate); 		//  buttons to the bottom panel
		Panel_Bottom.add(Button_Reset); 		//  buttons to the bottom (panel
		Panel_Bottom.add(Button_Exit); 		      //  buttons to the bottom ( panel
		
		add(Panel_Top, BorderLayout.NORTH); 		//  panels to frame in north location  of the border layout
		add(Panel_Middle, BorderLayout.CENTER); 		//  panels to frame in  center location  of the border layout
		add(Panel_Bottom, BorderLayout.SOUTH); 		//  panels to frame in  south location  of the border layout

		option1.addActionListener(this); 		//  action listeners for menu items
		option2.addActionListener(this); 		//  action listeners for menu items
		option3.addActionListener(this); 		//  action listeners for menu items
		
		Button_Calculate.addActionListener(this); 		//  action listeners for buttons
		Button_Reset.addActionListener(this); 		//  action listeners for buttons
	    Button_Exit.addActionListener(this); 		//  action listeners for buttons
	    
	    pack();
	    
 		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
 		setLocation(dim.width / 2 - getSize().width / 2, dim.height / 2 - getSize().height / 2); 	    // Center frame on the screen
	    
	 	setVisible(true);
	}
	
	public void actionPerformed(ActionEvent event)
	{
	    Object source = event.getSource();
	    
	    if (source == option1)
	    {
	    	TextFields_Term.setText("7");
            TextFields_Rate.setText("5.35");
        }
        else if (source == option2)
        {
        	TextFields_Term.setText("15");
        	TextFields_Rate.setText("5.5");
        }
        else if(source == option3)
        {
        	TextFields_Term.setText("30");
        	TextFields_Rate.setText("5.75");
        }
        else if (source == Button_Calculate)
        {
            TextArea_Results.setText("");

            if (isNumeric(TextFields_Loan.getText()) && isNumeric(TextFields_Rate.getText()) && isNumeric(TextFields_Term.getText()))             //  loan text field is numeric?
            {
                double loanAmount;                 //  variables
                int numberOfYears;           //  variables
                double annualRate;        //  variables
                double monthlyRate;         //  variables

                int numberOfPayments;         //  variables
                double monthlyPayment;      //  variables

                double totalCost;             //  variables
                double amountOwed;          //  variables
                double totalInterest;        //  variables
                double interestPaid;          //  variables

                loanAmount = Double.parseDouble(TextFields_Loan.getText());                 //  loan amount from loan text field

                numberOfYears = Integer.parseInt(TextFields_Term.getText());
                annualRate = Double.parseDouble(TextFields_Rate.getText());

                monthlyRate = annualRate / 1200;                 //  monthly interest rate

                monthlyPayment = loanAmount * monthlyRate / (1 - 1 / Math.pow(1 + monthlyRate, numberOfYears * 12));                 //  montly payment
                
                numberOfPayments = numberOfYears *12;                 // total number of payments
                
                totalCost = numberOfPayments * monthlyPayment;                 // total cost
                
                totalInterest = totalCost - loanAmount;                  // Total interest paid
 
                TextArea_Results.append(String.format("Monthly Payment: $%.2f\n", monthlyPayment));                 //  montly payment

                TextArea_Results.append(String.format("Total Payment: $%.2f\n", (monthlyPayment * 12) * numberOfYears));                 //  total payment
                
                TextArea_Results.append(String.format("Total interest paid: $%.2f\n",totalInterest )+ "\n");

                double balance = loanAmount, principal, interest;
                
                TextArea_Results.append("Payment#\tInterest Paid\tPrincipal Paid\tBalance\n");
                
                for (int i = 1; i <= numberOfYears * 12; i++)
                {
                    interest = monthlyRate * balance;
                    principal = monthlyPayment - interest;
                    balance = balance - principal;
                    TextArea_Results.append(String.format("%d\t%.2f\t%.2f\t%.2f\n", i, interest, principal, balance));
                }
                
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Not all entries are numeric.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        else if (source == Button_Reset)
        {
        	TextFields_Loan.setText("");
        	TextFields_Rate.setText("");
        	TextFields_Term.setText("");
        	
        	TextArea_Results.setText("");
        }
        else 
        { 
            System.exit(0);
        }
	}
	
	public static void main(String[] args)
	{
		new MortgageCalculatorMenu();
	}
	
	public static boolean isNumeric(String string) 	// given string is numeric? 
	{
		String numericExpression = "[-+]?\\d*\\.?\\d+";
		
		return string != null && string.matches(numericExpression);
	}
}
