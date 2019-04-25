/**Указываем, в каком пакете находится класс 
*/
package rentCalc;

/** Импортируем все классы из пакета для работы с графическими элементами интерфейса 
*/
import javax.swing.*;
/** 
* Импортируем оконную библиотеку графического интерфейса, необходимые для функционирования класса. 
*/
import java.awt.*;
/** 
* Импортируем интерфейсы и классы для того, чтобы иметь дело с различными типами событий, 
* запущенных компонентами AWT. 
*/
import java.awt.event.*;

/**
 * Создаем абстрактное описание квартплатного калькулятора, выступает как шаблон
 * для наследования другими классами-калькуляторами
 */
public abstract class Main_Panel extends JFrame{
	/**
	 * Объявляем константы (тарифы на коммунальные услуги)
	 */
	public double TARIF_HOT_WATER = 27.5;
	public double TARIF_COLD_WATER = 25.43;
	public double TARIF_GAS = 7.1;
	public double TARIF_SVET = 3.06;

	/**
	 * Объявляем переменные, использующиеся в калькуляторе
	 */
	public double tarrif_hot_water = 0;
	public double tarrif_cold_water = 0;
	public double tarrif_gas = 0;
	public double tarrif_svet = 0;
	public double normativ = 0;
	public double area = 0;
	public double v_gas = 0;
	public double v_cold_water = 0;
	public double v_hot_water = 0;
	public double v_svet = 0;
	public double dolg = 0;
	public double pereplata = 0;
	public double itog = 0;
	public double sumcoldwater = 0;
	public double sumhotwater = 0;
	public double sumgas = 0;
	public double sumsvet = 0;

	/**
	 * Объявляем панель, доступную только в рамках данного класса
	 */
	protected JPanel panel;
	/**
	 * Задаем количество кнопок на нашем шаблоне (две)
	 */
	public int buttons_number = 2;
	/**
	 * Объявляем массив кнопок, размером 2
	 */
	public JButton[] buttons = new JButton[buttons_number];
	/**
	 * Объявляем массив имен кнопок
	 */
	public String buttons_name[] = { "ExitButton", "CalculateButton" };
	/**
	 * Объявляем массив текстов для кнопок
	 */
	public String buttons_text[] = { "Сброс", "Расчет" };
	/**
	 * Объявляем массив размеров для кнопок, где для каждой кнопки отведено 4
	 * значения: 1. координата по Х 2. координата по Y 3. ширина кнопки 4. высота
	 * кнопки
	 */
	public int buttons_size[] = { 178, 380, 89, 23, 10, 380, 89, 23 };

	/**
	 * Задаем количество радиокнопок на нашем шаблоне (две)
	 */
	public int radiobuttons_number = 2;
	/**
	 * Объявляем массив радиокнопок, размером 2
	 */
	public JRadioButton[] radiobuttons = new JRadioButton[radiobuttons_number];
	/**
	 * Объявляем массив имен кнопок
	 */
	public String radiobuttons_name[] = { "ApartmentRadioButton", "HouseRadioButton" };
	/**
	 * Объявляем массив текстов для кнопок
	 */
	public String radiobuttons_text[] = { "Квартира", "Частный дом" };
	/**
	 * Объявляем массив размеров для радиокнопок, где для каждой кнопки отведено 4
	 * значения: 1. координата по Х 2. координата по Y 3. ширина радиокнопки 4.
	 * высота радиокнопки
	 */
	public int radiobuttons_size[] = { 6, 25, 100, 23, 6, 43, 150, 23 };

	/**
	 * Задаем количество текстовых полей для калькулятора жителя дома (тринадцать)
	 */
	public int user_textfields_number = 13;
	/**
	 * Объявляем массив текстовых полей для калькулятора жителя, размером 13
	 */
	public JTextField[] user_textfields = new JTextField[user_textfields_number];
	/**
	 * Объявляем массив имен текстовых полей для калькулятора жителя
	 */
	public String user_textfields_name[] = { "textField_area", "textField_normativ", "textField_v_cold_water",
			"textField_v_hot_water", "textField_v_svet", "textField_v_gas", "textField_dolg", "textField_pereplata",
			"textField_sumcoldwater", "textField_sumhotwater", "textField_sumgas", "textField_sumsvet",
			"textField_itog" };
	/**
	 * Объявляем массив размеров для текстовых полей, где для каждого поля отведено
	 * 4 значения: 1. координата по Х 2. координата по Y 3. ширина поля 4. высота
	 * поля
	 */
	public int user_textfields_size[] = { 270, 25, 86, 20, 250, 67, 86, 20, 250, 92, 86, 20, 250, 117, 86, 20, 250, 142,
			86, 20, 250, 167, 86, 20, 250, 192, 86, 20, 250, 217, 86, 20, 250, 242, 86, 20, 250, 267, 86, 20, 250, 292,
			86, 20, 250, 317, 86, 20, 250, 342, 86, 20 };

	/**
	 * Задаем количество текстовых полей для калькулятора администратора
	 * (семнадцать)
	 */
	public int accountant_textfields_number = 17;
	/**
	 * Объявляем массив текстовых полей для калькулятора администратора, размером 17
	 */
	public JTextField[] accountant_textfields = new JTextField[accountant_textfields_number];
	/**
	 * Объявляем массив имен текстовых полей для калькулятора администратора
	 */
	public String accountant_textfields_name[] = { "textField_area", "textField_normativ", "textField_v_cold_water",
			"textField_v_hot_water", "textField_v_svet", "textfield_v_gas", "textfield_dolg", "textfield_pereplata",
			"textfield_sumcoldwater", "textfield_sumhotwater", "textField_sumgas", "textField_sumsvet",
			"textField_itog", "textfield_tarrif_cold_water", "textfield_tarrif_hot water", "textField_tarrif_svet",
			"textfield_tarrif_gas" };
	/**
	 * Объявляем массив размеров для текстовых полей, где для каждого поля отведено
	 * 4 значения: 1. координата по Х 2. координата по Y 3. ширина поля 4. высота
	 * поля
	 */
	public int accountant_textfields_size[] = { 270, 25, 86, 20, 250, 67, 86, 20, 250, 92, 86, 20, 250, 117, 86, 20,
			250, 142, 86, 20, 250, 167, 86, 20, 250, 192, 86, 20, 250, 217, 86, 20, 250, 242, 86, 20, 250, 267, 86, 20,
			250, 292, 86, 20, 600, 73, 86, 20, 600, 98, 86, 20, 600, 123, 86, 20, 600, 148, 86, 20, 250, 317, 86, 20,
			250, 342, 86, 20 };

	/**
	 * Задаем количество меток на нашем шаблоне (четырнадцать)
	 */
	public int labels_number = 14;
	/**
	 * Объявляем массив меток на шаблоне, размером 14
	 */
	public JLabel[] labels = new JLabel[labels_number];
	/**
	 * Объявляем массив текстов для меток
	 */
	public String labels_text[] = { "Тип жилья:", "Итог:", "Отапливаемая площадь=", "Норматив отопления:",
			"Объем потраченной холодной воды:", "Объем потраченной горячей воды:", "Объем потраченного света:",
			"Объем потраченного газа:", "Долг за прошлый месяц:", "Переплата:", "Подсчет холодной воды:",
			"Подсчет горячей воды:", "Подсчет газа:", "Подсчет света:" };
	/**
	 * Объявляем массив размеров для меток, где для каждой метки отведено 4
	 * значения: 1. координата по Х 2. координата по Y 3. ширина метки 4. высота
	 * метки
	 */
	public int labels_size[] = { 10, 11, 150, 14, 10, 348, 270, 14, 120, 25, 270, 23, 10, 73, 270, 14, 10, 98, 270, 14,
			10, 123, 270, 14, 10, 148, 270, 14, 10, 173, 270, 14, 10, 198, 270, 14, 10, 223, 270, 14, 10, 248, 270, 14,
			10, 273, 270, 14, 10, 298, 270, 14, 10, 323, 270, 14 };

	/**
	 * Задаем количество новых меток на калькуляторе администратора (четыре)
	 */
	public int accountant_labels_number = 4;
	/**
	 * Объявляем массив новых меток на калькуляторе администратора, размером 4
	 */
	public JLabel[] accountant_labels = new JLabel[accountant_labels_number];
	/**
	 * Объявляем массив текстов для меток
	 */
	public String accountant_labels_text[] = { "Тариф на холодную воду:", "Тариф на горячую воду:", "Тариф на свет:",
			"Тариф на газ:", };
	/**
	 * Объявляем массив размеров для меток, где для каждой метки отведено 4
	 * значения: 1. координата по Х 2. координата по Y 3. ширина метки 4. высота
	 * метки
	 */
	public int accountant_labels_size[] = { 400, 73, 270, 14, 400, 98, 270, 14, 400, 123, 270, 14, 400, 148, 270, 14 };

	/**
	 * Создаем конструктор класса-шаблона этот метод будет реализовываться при
	 * наследовании от шаблона
	 */
	public Main_Panel() {
		/**
		 * Обращаемся к панели содержимого и задаем карточное размещение
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
		 * Добавляем на панель кнопки
		 */
		buttons_add(buttons_name, buttons_text, buttons_size, panel);
		/**
		 * Добавляем на панель метки
		 */
		labels_add(labels_number, labels_text, labels_size, panel);
		/**
		 * Добавляем на панель радиокнопки
		 */
		radiobuttons_add(radiobuttons_name, radiobuttons_text, radiobuttons_size, panel);
		/**
		 * Условие выбора радиокнопки: две кнопки не могут быть выбраны одновременно:
		 * если выбрана первая, не может быть выбрана вторая
		 */
		radiobuttons[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (radiobuttons[0].isSelected()) {
					radiobuttons[1].setSelected(false);
				}
			}
		});
		/**
		 * Условие выбора радиокнопки: две кнопки не могут быть выбраны одновременно:
		 * если выбрана вторая, не может быть выбрана первая
		 */
		radiobuttons[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (radiobuttons[1].isSelected()) {
					radiobuttons[0].setSelected(false);
				}
			}
		});
	}

	/**
	 * Метод для создания радиокнопок их редактирования и добавления на панель
	 */
	public void radiobuttons_add(String radiobuttons_name[], String radiobuttons_text[], int radiobuttons_size[],
			JPanel panel) {
		/**
		 * Создаем цикл, в котором обрабатываются элемент массива кнопок
		 */
		for (int i = 0; i < radiobuttons_number; i++) {
			/**
			 * Создаем экземпляр радиокнопки
			 */
			radiobuttons[i] = new JRadioButton();
			/**
			 * Устанавливаем текст для радиокнопки
			 */
			radiobuttons[i].setText(radiobuttons_text[i]);
			/**
			 * Устанавливаем имя для радиокнопки
			 */
			radiobuttons[i].setName(radiobuttons_name[i]);
			/**
			 * Устанавливаем шрифт и размер для текста радиокнопки
			 */
			radiobuttons[i].setFont(new Font("Segoe UI Light", Font.PLAIN, 13));
			/**
			 * Устанавливаем размеры радиокнопки
			 */
			radiobuttons[i].setBounds(radiobuttons_size[i * 4], radiobuttons_size[i * 4 + 1],
					radiobuttons_size[i * 4 + 2], radiobuttons_size[i * 4 + 3]);
			/**
			 * Добавляем кнопку на панель
			 */
			panel.add(radiobuttons[i]);
		}
	}

	/**
	 * Метод для создания текстовых полей их редактирования и добавления на панель
	 */
	public void textfields_add(int user_textfields_number, String user_textfields_name[], int user_textfields_size[],
			JPanel panel) {
		/**
		 * Создаем цикл, в котором обрабатываются элементы массива текстовых полей
		 */
		for (int i = 0; i < user_textfields_number; i++) {
			/**
			 * Создаем экземпляр текстового поля
			 */
			user_textfields[i] = new JTextField();
			/**
			 * Устанавливаем начальное значение для текстового поля (0)
			 */
			user_textfields[i].setText("0");
			/**
			 * Устанавливаем имя для текстового поля
			 */
			user_textfields[i].setName(user_textfields_name[i]);
			/**
			 * Устанавливаем размеры текстового поля
			 */
			user_textfields[i].setBounds(user_textfields_size[i * 4], user_textfields_size[i * 4 + 1],
					user_textfields_size[i * 4 + 2], user_textfields_size[i * 4 + 3]);
			/**
			 * Добавляем текстовое поле на панель
			 */
			panel.add(user_textfields[i]);
		}
	}

	/**
	 * Метод для создания меток их редактирования и добавления на панель
	 */
	public void labels_add(int labels_number, String labels_text[], int labels_size[], JPanel panel) {
		/**
		 * Создаем цикл, в котором обрабатываются элементы массива меток
		 */
		for (int i = 0; i < labels_number; i++) {
			/**
			 * Создаем экземпляр метки
			 */
			labels[i] = new JLabel();
			/**
			 * Устанавливаем текст для метки
			 */
			labels[i].setText(labels_text[i]);
			/**
			 * Устанавливаем шрифт и размер для текста метки
			 */
			labels[i].setFont(new Font("Segoe UI Light", Font.PLAIN, 13));
			/**
			 * Устанавливаем размеры метки
			 */
			labels[i].setBounds(labels_size[i * 4], labels_size[i * 4 + 1], labels_size[i * 4 + 2],
					labels_size[i * 4 + 3]);
			/**
			 * Добавляем метку на панель
			 */
			panel.add(labels[i]);
		}
	}

	/**
	 * Метод для создания кнопок их редактирования и добавления на панель
	 */
	public void buttons_add(String buttons_name[], String buttons_text[], int buttons_size[], JPanel panel) {
		/**
		 * Создаем цикл, в котором обрабатываются элементы массива кнопок
		 */
		for (int i = 0; i < buttons_number; i++) {
			/**
			 * Создаем экземпляр кнопки
			 */
			buttons[i] = new JButton();
			/**
			 * Устанавливаем имя для кнопки
			 */
			buttons[i].setName(buttons_name[i]);
			/**
			 * Устанавливаем текст для кнопки
			 */
			buttons[i].setText(buttons_text[i]);
			/**
			 * Устанавливаем размеры кнопки
			 */
			buttons[i].setBounds(buttons_size[i * 4], buttons_size[i * 4 + 1], buttons_size[i * 4 + 2],
					buttons_size[i * 4 + 3]);
			/**
			 * Устанавливаем шрифт и размер для текста кнопок
			 */
			buttons[i].setFont(new Font("Segoe UI Light", Font.PLAIN, 13));
			/**
			 * Добавляем кнопку на панель
			 */
			panel.add(buttons[i]);
		}
	}
	/**
	 * Метод, отключающий видимость окна
	 */
	public void killFrame() {
		this.dispose();
	}
	/**
	 * Создаем абстрактный метод для расчета. Шаблон калькулятора предполагает метод
	 * расчета, но то, как расчет производится, мы определяем в классах-наследниках
	 */
	abstract public void Calc();
}
