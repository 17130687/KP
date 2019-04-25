/**
* Импортирование библиотек, описывающих работу текстовыми полями
*/
import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
public class textFieldFilter {
	private static void TextFilter(JTextField TextField, final int length) {
		/**
		* Создание нового пустого документа
		*/
		TextField.setDocument(new PlainDocument() {
			/**
			* Задание строки символов, разрешенных для записи в последующее поле
			*/
			String chars = "0123456789.";
			/**
			* Вставка символов в пустой документ
			*/
			public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
				if (chars.indexOf(str) != -1) {
					if (getLength() < length) {
						super.insertString(offs, str, a);
					}
				}
			}
		});
	}
	/**
	* Метод для применения фильтра к текстовому полю
	* @param TextField текстовое поле для изменения
	* @param length устанавливаемая длинна в текстовом поле
	*/
	public static void filterField(JTextField TextField, final int length) {
		TextFilter(TextField,length);
	}
	
}
Файл User_Panel.java
/**
 * Указываем, в каком пакете находится класс 
 */
package rentCalc;

/** Импортируем оконную библиотеку графического интерфейса, 
* необходимые для функционирования класса. 
*/
import java.awt.*;
/** 
* Импортируем интерфейсы и классы для того, чтобы иметь дело с различными типами событий, 
* запущенных компонентами AWT. 
*/
import java.awt.event.*;

/** 
* Импортируем классы проекта, необходимые для функционирования класса 
*/
import rentCalc.Main_Panel;
import rentCalc.Authorization_Panel;

/**
 * Создаем класс-потомок, который будет выполнять расчет для пользователя с
 * ограниченным доступом; он наследуется от класса-родителя (шаблона
 * квартплатного калькулятора)
 */
public class User_Panel extends Main_Panel {

	/**
	 * Создаем конструктор калькулятора пользователя с ограниченным доступом
	 */
	public User_Panel() {
		/**
		 * Обращаемся к панели содержимого и добавляем компонент (панель)
		 */
		getContentPane().add(panel, "User");
		/**
		 * Добавляем текстовые поля для ввода показателей счетчиков коммунальных услуг;
		 * используем для этого метод класса-родителя.
		 */
		textfields_add(user_textfields_number, user_textfields_name, user_textfields_size, panel);
		/**
		 * Инициализация текстового фильтратора
		 */
		textFieldFilter filter = new textFieldFilter();
		for (int i = 0; i < 13; i++) {
			if (i < 8) {
				/**
				 * Применение фильтрации знаков в текстовом поле
				 */
				filter.filterField(user_textfields[i], 7);
			} else {
				/**
				 * Запрет на изменение полей вывода результатов
				 */
				user_textfields[i].setEditable(false);
			}
		}
		/**
		 * Обрабатываем нажатие на кнопку расчета; обращаемся к кнопке по индексу и
		 * переопределяем метод обработки нажатия на данную кнопку;
		 */
		buttons[1].addActionListener(new ActionListener() {
			/**
			 * Аннотация, указывающая на то, что мы собираемся переопределить метод
			 * обработки класса-родителя
			 */
			@Override
			/**
			 * Метод обработки нажатия на кнопку
			 */
			public void actionPerformed(ActionEvent e) {
				/**
				 * Вызываем переопределенный в данном классе метод расчета для пользователя с
				 * ограниченным доступом
				 */
				Calc();
			}
		});
		/**
		 * Обрабатываем нажатие на кнопку выхода; обращаемся к кнопке по индексу и
		 * переопределяем метод обработки нажатия на данную кнопку; открываем стартовую
		 * панель авторизации.
		 */
		buttons[0].addActionListener(new ActionListener() {
			/**
			 * Аннотация, указывающая на то, что мы собираемся переопределить метод
			 * класса-родителя
			 */
			@Override
			/**
			 * Метод обработки нажатия на кнопку
			 */
			public void actionPerformed(ActionEvent e) {
				/**
				 * Создаем экземпляр менеджера компоновки для карточного размещения компонентов
				 */
				CardLayout cl = (CardLayout) getContentPane().getLayout();
				/**
				 * Создаем экземпляр класса авторизации
				 */
				Authorization_Panel af = new Authorization_Panel();
				/**
				 * Включение видимости экземпляра
				 */
				af.setVisible(true);
				/**
				 * Задание размеров экземпляра
				 */
				af.setSize(500, 500);
				/**
				 * Отключение видимости основного окна
				 */
				killFrame();
			}
		});
	}

	/**
	 * Переопределяем абстрактный метод класса-родителя, то есть создаем конкретный
	 * метод расчета квартплаты для пользователя с ограниченным доступом
	 */
	public void Calc() {
		/**
		 * Заносим введенные показатели по жилью в соответствующие переменные.
		 */
		area = Double.valueOf(user_textfields[0].getText());
		normativ = Double.valueOf(user_textfields[1].getText());
		v_cold_water = Double.valueOf(user_textfields[2].getText());
		v_hot_water = Double.valueOf(user_textfields[3].getText());
		v_svet = Double.valueOf(user_textfields[4].getText());
		v_gas = Double.valueOf(user_textfields[5].getText());
		dolg = Double.valueOf(user_textfields[6].getText());
		pereplata = Double.valueOf(user_textfields[7].getText());

		/**
		 * Если выбран тип жилья "квартира", то расчет производится по следующей формуле
		 * в соответствии с тарифами
		 */
		if (radiobuttons[0].isSelected()) {
			sumcoldwater = v_cold_water * 25.43;
			sumhotwater = v_hot_water * 27.5;
			sumgas = v_gas * 7.1;
			sumsvet = v_svet * 3.06;
			itog = area * normativ + sumcoldwater + sumhotwater + sumgas + sumsvet + dolg - pereplata;
			/**
			 * Округляем результат расчета до сотых (до копеек)
			 */
			sumcoldwater = Math.rint(100.0 * sumcoldwater) / 100.0;
			sumhotwater = Math.rint(100.0 * sumhotwater) / 100.0;
			sumgas = Math.rint(100.0 * sumgas) / 100.0;
			sumsvet = Math.rint(100.0 * sumsvet) / 100.0;
			itog = Math.rint(100.0 * itog) / 100.0;
		}
		/**
		 * Если выбран тип жилья "частный дом", то расчет производится по следующей
		 * формуле в соответствии с тарифами
		 */
		else if (radiobuttons[1].isSelected()) {
			sumcoldwater = v_cold_water * 25.43;
			sumhotwater = v_hot_water * 27.5;
			sumgas = v_gas * 7.1;
			sumsvet = v_svet * 3.06;
			itog = sumcoldwater + sumhotwater + sumgas + sumsvet + dolg - pereplata;
			/**
			 * Округляем результат расчета до сотых (до копеек)
			 */
			sumcoldwater = Math.rint(100.0 * sumcoldwater) / 100.0;
			sumhotwater = Math.rint(100.0 * sumhotwater) / 100.0;
			sumgas = Math.rint(100.0 * sumgas) / 100.0;
			sumsvet = Math.rint(100.0 * sumsvet) / 100.0;
			itog = Math.rint(100.0 * itog) / 100.0;
		}
		/**
		 * Выводим результат расчета в отдельное текстовое поле
		 */
		user_textfields[8].setText(String.valueOf(sumcoldwater));
		user_textfields[9].setText(String.valueOf(sumhotwater));
		user_textfields[10].setText(String.valueOf(sumgas));
		user_textfields[11].setText(String.valueOf(sumsvet));
		user_textfields[12].setText(String.valueOf(itog));

	}

}
