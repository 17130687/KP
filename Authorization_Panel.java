/** Указываем, в каком пакете находится класс 
*/
package rentCalc;

/**Импортируем все классы из пакета для создания графического интерфейса 
*/
import java.awt.*;
/** 
* Импортируем оконную библиотеку графического интерфейса, 
* необходимые для функционирования класса. 
*/
import java.awt.event.*;
/** 
* Импортируем все классы из пакета для работы с графическими 
* элементами интерфейса 
*/
import javax.swing.*;

import rentCalc.Authorization_Panel;
/** 
* Импортируем классы проекта, необходимые для функционирования класса 
*/
import rentCalc.Accountant_Panel;
import rentCalc.User_Panel;

/**
 * Создаем класс, который наследуется от апплета
 */
public class Authorization_Panel extends JFrame {
	/**
	 * Объявляем переменные, которые доступны только в пределах класса
	 */
	private User_Panel uf;
	private Accountant_Panel af;
	private JPasswordField pass;
	private JTextField JTextField_Login;
	private JPanel panel;

	/**
	 * Задаем количество меток на панели авторизации
	 */
	public int auth_labels_number = 3;
	/**
	 * Объявляем массив меток на панели авторизации, размером 3
	 */
	public JLabel[] auth_labels = new JLabel[auth_labels_number];
	/**
	 * Объявляем массив текстов для меток
	 */
	public String auth_labels_text[] = { "Авторизация", "Имя пользователя", "Пароль", };
	/**
	 * Объявляем массив размеров для меток, где для каждой метки отведено 4
	 * значения: 1. координата по Х 2. координата по Y 3. ширина метки 4. высота
	 * метки
	 */
	public int auth_labels_size[] = { 190, 25, 230, 42, 175, 88, 150, 24, 205, 145, 55, 24 };

	/**
	 * Создаем конструктор класса авторизации этот метод будет реализовываться при
	 * создании экземпляра класса
	 */
	public Authorization_Panel() {
		/**
		 * Обращаемся к панели содержимого апплета и задаем карточное размещение
		 * компонентов на ней
		 */
		getContentPane().setLayout(new CardLayout());
		/**
		 * Создаем экземпляр панели
		 */
		panel = new JPanel();
		/**
		 * Задаем свободное размещение элементов на панели (устанавливаем координаты
		 * элементов вручную)
		 */
		panel.setLayout(null);
		/**
		 * Обращаемся к панели содержимого апплета и добавляем компонент (панель)
		 */
		getContentPane().add(panel, "Auth");
		/**
		 * Добавляем на панель метки
		 */
		auth_labels_add(auth_labels_number, auth_labels_text, auth_labels_size, panel);
		/**
		 * Создаем экземпляр текстового поля
		 */
		JTextField_Login = new JTextField();
		/**
		 * Устанавливаем размер текстового поля (10 символов)
		 */
		JTextField_Login.setColumns(10);
		/**
		 * Устанавливаем размер текстового поля 4 значения: 1. координата по Х 2.
		 * координата по Y 3. ширина текстового поля 4. высота текстового поля
		 */
		JTextField_Login.setBounds(180, 113, 94, 20);
		/**
		 * Добавляем текстовое поле на панель
		 */
		panel.add(JTextField_Login);
		/**
		 * Создаем экземпляр поля для пароля
		 */
		pass = new JPasswordField();
		/**
		 * Устанавливаем размер поля для пароля 4 значения: 1. координата по Х 2.
		 * координата по Y 3. ширина поля 4. высота поля
		 */
		pass.setBounds(180, 170, 94, 20);
		/**
		 * Добавляем поле для пароля на панель
		 */
		panel.add(pass);

		/**
		 * Создаем экземпляр кнопки "Вход"
		 */
		JButton JButton_Enter = new JButton("Вход");
		/**
		 * Добавляем слушателя к кнопке
		 */

		JButton_Enter.addActionListener(new ActionListener() {
			/**
			 * Обрабатываем нажатие на кнопку
			 */
			public void actionPerformed(ActionEvent e) {
				/**
				 * Создаем экземпляр менеджера компоновки для карточного размещения компонентов
				 */
				CardLayout cl = (CardLayout) getContentPane().getLayout();
				/**
				 * Если логин и пароль для доступа к калькулятору пользователя с ограниченным
				 * доступом, (метод возвращает 1),
				 */
				if (auth(JTextField_Login.getText(), pass.getText()) == 1) {
					killFrame();
					/**
					 * То создаем экземпляр калькулятора для пользователя с ограниченным доступом
					 */
					uf = new User_Panel();
					/**
					 * Установка размера экземпляра
					 */
					uf.setSize(600, 500);
					/**
					 * Делаем экземпляр видимым
					 */
					uf.setVisible(true);
				}
				/**
				 * Если логин и пароль для доступа к калькулятору администратора, (метод
				 * возвращает 2),
				 */
				else if (auth(JTextField_Login.getText(), pass.getText()) == 2) {
					killFrame();
					/**
					 * То создаем экземпляр калькулятора для администратора
					 */
					af = new Accountant_Panel();
					af.setSize(800, 500);
					/**
					 * Делаем экземпляр видимым
					 */
					af.setVisible(true);
				}
				/**
				 * Если логин и пароль для доступа введены неверно, (метод возвращает 0),
				 */
				else if (auth(JTextField_Login.getText(), pass.getText()) == 0) {
					/**
					 * То выводим сообщение о неправильности введенных данных
					 */
					JOptionPane.showMessageDialog(panel, "Проверьте правильность введенных логина и/или пароля",
							"Неправильный логин или пароль", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		/**
		 * Устанавливаем шрифт и размер текста на кнопке
		 */
		JButton_Enter.setFont(new Font("Segoe UI Light", Font.PLAIN, 16));
		/**
		 * Задаем размер для кнопки, где 4 значения: 1. координата по Х 2. координата по
		 * Y 3. ширина кнопки 4. высота кнопки
		 */
		JButton_Enter.setBounds(180, 210, 94, 31);
		/**
		 * Добавляем кнопку на панель
		 */
		panel.add(JButton_Enter);
	}

	/**
	 * Объявляем переменную, где будет храниться результат проверки авторизации
	 */
	private int access;
	/**
	 * Метод, отключающий видимость окна
	 */
	public void killFrame() {
		this.dispose();
	}
	/**
	 * Создаем метод для проверки введенных логина и пароля
	 */
	public int auth(String login, String pass) {
		/**
		 * Если введены логин и пароль для калькулятора пользователя с ограниченным
		 * доступом, то переменной с результатом проверки присваивается 1,
		 */
		if ((login.equals("Эльвина")) || (login.equals("Евгения"))
				|| (login.equals("Маргарита")) && pass.equals("123")) {
			access = 1;
		}
		/**
		 * Если введены логин и пароль для калькулятора администратора, то переменной с
		 * результатом проверки присваивается 2,
		 */
		else if (login.equals("Дарья") && pass.equals("123")) {
			access = 2;
		}
		/**
		 * Если введены неверные логин и пароль, или ничего не введено, то переменной с
		 * результатом проверки присваивается 0
		 */
		else {
			access = 0;
		}
		/**
		 * Метод возвращает переменную с результатом проверки
		 */
		return access;
	}

	/**
	 * Метод для создания меток их редактирования и добавления на панель
	 */
	public void auth_labels_add(int auth_labels_number, String auth_labels_text[], int auth_labels_size[],
			JPanel panel) {
		/**
		 * Создаем цикл, в котором обрабатываются элементы массива меток
		 */
		for (int i = 0; i < auth_labels_number; i++) {
			/**
			 * Создаем экземпляр метки
			 */
			auth_labels[i] = new JLabel();
			/**
			 * Устанавливаем текст для метки
			 */
			auth_labels[i].setText(auth_labels_text[i]);
			/**
			 * Устанавливаем шрифт и размер для текста метки
			 */
			auth_labels[i].setFont(new Font("Segoe UI Light", Font.PLAIN, 13));
			/**
			 * Устанавливаем размеры метки
			 */
			auth_labels[i].setBounds(auth_labels_size[i * 4], auth_labels_size[i * 4 + 1], auth_labels_size[i * 4 + 2],
					auth_labels_size[i * 4 + 3]);
			/**
			 * Добавляем метку на панель
			 */
			panel.add(auth_labels[i]);
		}
	}

	/**
	 * Создаем метод для запуска оконного приложения
	 */
	public static void main(String[] args) {
		/**
		 * Создаем экземпляр окна
		 */
		Authorization_Panel frame = new Authorization_Panel();
		/**
		 * Устанавливаем размеры окна
		 */
		frame.setSize(500, 500);
		/**
		 * Включаем видимость окна
		 */
		frame.setVisible(true);
		/**
		 * Установка стандартной операции при закрытии
		 */
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
	}
}
