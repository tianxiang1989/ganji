package fox;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class GanJiFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private GanJiData calendar;
	private JLabel yearAndMonth;
	private JLabel monthInc;
	private JLabel monthDec;
	private JLabel backToday;
	private JLabel[] weeks = { new JLabel("日"), new JLabel("一"),
			new JLabel("二"), new JLabel("三"), new JLabel("四"), new JLabel("五"),
			new JLabel("六"), };
	private JLabel[] days = new JLabel[42];
	
	private String[] names={"是集会","差四天","差三天","差两天","差一天"};
	private JLabel[] showNames = new JLabel[42];

	public void setYearAndMonth(String yearAndMonth) {
		this.yearAndMonth.setText(yearAndMonth);
	}

	public void setCalendar(int begin, int maxDay, int currentDay,int firstName) {
		for (int i = 0; i < days.length; i++) {
			days[i].setText("");
			days[i].setForeground(Color.BLACK);
		}
		for (int i = 0; i < days.length; i++) {
			showNames[i].setText("");
			showNames[i].setForeground(Color.BLACK);
		}
		
		for (int i = 1; i <= maxDay; i++) {
			String dateStr = i <= 9 ? " " + i : "" + i;
			days[begin + i - 2].setText(dateStr);
			if(firstName>=names.length)
				firstName=firstName%names.length;
			showNames[begin + i - 2].setText(names[firstName++]);
			if (currentDay == i) {
				days[begin + i - 2].setForeground(Color.RED);
				showNames[begin + i - 2].setForeground(Color.RED);
			}
		}
	}

	public GanJiFrame(GanJiData calendar) {
		this.calendar = calendar;
		setTitle("赶集时间表");
		setSize(420, 370);
//		setFont(new Font("Serif",Font.BOLD|Font.ITALIC,24));
		init();
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((d.width - getWidth()) / 2, (d.height - getHeight()) / 2);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
	}

	private void init() {
		Container contentPane = getContentPane();
		contentPane.setLayout(null);
		for (int i = 0; i < weeks.length; i++) {
			weeks[i].setBounds(40 + i * 50, 50, 50, 30);
			contentPane.add(weeks[i]);
		}
		for (int i = 0; i < days.length; i++) {
			days[i] = new JLabel("");
			days[i].setBounds(40 + (i % 7) * 50, 75 + (i / 7) * 40, 50, 40);
			contentPane.add(days[i]);
		}
		

		for (int i = 0; i < showNames.length; i++) {
			showNames[i] = new JLabel("");
			showNames[i].setFont(new Font("Serif",Font.PLAIN,13));
			showNames[i].setBounds(40 + (i % 7) * 50, 95 + (i / 7) * 40, 50, 40);
			contentPane.add(showNames[i]);
		}
		
		monthInc = new JLabel(">>");
		monthInc.setBounds(250, 20, 20, 20);
		contentPane.add(monthInc);
		monthDec = new JLabel("<<");
		monthDec.setBounds(130, 20, 20, 20);
		contentPane.add(monthDec);
		backToday=new JLabel("today");
		backToday.setBounds(300, 20, 40, 20);
		backToday.setFont(new Font("Serif",Font.BOLD|Font.ITALIC,12));
		contentPane.add(backToday);
		yearAndMonth = new JLabel("");
		yearAndMonth.setBounds(165, 20, 80, 20);
		contentPane.add(yearAndMonth);
		monthInc.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				monthInc.setForeground(Color.RED);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				monthInc.setForeground(Color.BLACK);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				calendar.incMonth();
			}
		});
		monthDec.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				monthDec.setForeground(Color.RED);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				monthDec.setForeground(Color.BLACK);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				calendar.decMonth();

			}
		});
		
		backToday.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				backToday.setForeground(Color.RED);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				backToday.setForeground(Color.BLACK);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				calendar.backToday();
			}
		});
		
	}

	public String[] getNames() {
		return names;
	}
}