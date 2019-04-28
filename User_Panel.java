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
		 * Обращаемся к панели содержимого апплета и добавляем компонент (панель)
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
				makeCalc();
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

	public void makeCalc() {
		if (radiobuttons[0].isSelected()) {
			switchKey = 1;
		} else if (radiobuttons[1].isSelected()) {
			switchKey = 2;
		}
		Calc a = new Calc(Double.valueOf(user_textfields[0].getText()), Double.valueOf(user_textfields[1].getText()),
				Double.valueOf(user_textfields[2].getText()), Double.valueOf(user_textfields[3].getText()),
				Double.valueOf(user_textfields[4].getText()), Double.valueOf(user_textfields[5].getText()),
				Double.valueOf(user_textfields[6].getText()), Double.valueOf(user_textfields[7].getText()), switchKey,
				TARIF_HOT_WATER, TARIF_COLD_WATER, TARIF_GAS, TARIF_SVET);
		a.Calculate();
		user_textfields[8].setText(String.valueOf(a.getsumcoldwater()));
		user_textfields[9].setText(String.valueOf(a.getsumhotwater()));
		user_textfields[10].setText(String.valueOf(a.getsumgas()));
		user_textfields[11].setText(String.valueOf(a.getsumsvet()));
		user_textfields[12].setText(String.valueOf(a.getitog()));
	}

}
