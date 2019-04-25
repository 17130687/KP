/**Указываем, в каком пакете находится класс 
*/
package rentCalc;

/** Импортируем оконную библиотеку графического интерфейса, необходимые для функционирования класса. 
*/
import java.awt.*;
/** 
* Импортируем интерфейсы и классы для того, чтобы иметь дело с различными типами событий, 
* запущенных компонентами AWT. 
*/
import java.awt.event.*;

/** 
* Импортируем все классы из пакета для работы с графическими 
* элементами интерфейса 
*/
import javax.swing.*;

/** 
* Импортируем классы проекта, необходимые для функционирования класса 
*/
import rentCalc.Main_Panel;

/**
 * Создаем класс-потомок, который будет выполнять расчет для администратора; он
 * наследуется от класса-родителя (шаблона квартплатного калькулятора)
 */
public class Accountant_Panel extends Main_Panel {

	/**
	 * Создаем конструктор калькулятора администратора
	 */
	public Accountant_Panel() {
		/**
		 * Обращаемся к панели содержимого и добавляем компонент (панель)
		 */
		getContentPane().add(panel, "Accountant");
		/**
		 * Добавляем текстовые поля для ввода показателей для расчета итоговой
		 * квартплаты; используем для этого переопределенный в данном классе метод
		 * класса-родителя.
		 */
		textfields_add(accountant_textfields_number, accountant_textfields_name, accountant_textfields_size, panel);
		/**
		 * Инициализация текстового фильтратора
		 */
		textFieldFilter filter = new textFieldFilter();
		for (int i = 0; i < 17; i++) {
			/**
			 * Применение фильтра к текстовым полям
			 */
			if (i<8 || i>10 & i<=14) {
			filter.filterField(accountant_textfields[i], 7);
			}
			else if (i >7 && i<11 || i<17 && i>14){
				/**
				 * Отключение возможности редактирования для полей вывода
				 */
				accountant_textfields[i].setEditable(false);
			}
		}
		/**
		 * Добавляем метки администраторского калькулятора; используем для этого метод
		 * класса-родителя.
		 */
		labels_add(accountant_labels_number, accountant_labels_text, accountant_labels_size, panel);
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
				 * Вызываем переопределенный в данном классе метод расчета для администратора
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
				 * Добавляем экземпляр класса авторизации, как новую карточку, устанавливаем имя
				 * карточки
				 */
				
				af.setVisible(true);
				af.setSize(500,500);
				/**
				 * Указываем менеджеру компоновки, что нужно показать карточку авторизации
				 */
				killFrame();
			}
		});
	}

	/**
	 * Аннотация, указывающая на то, что мы собираемся переопределить метод
	 * класса-родителя
	 */
	@Override
	/**
	 * Переопределяем абстрактный метод класса-родителя, то есть создаем конкретный
	 * метод расчета квартплаты для администратора
	 */
	public void Calc() {
		/**
		 * Заносим введенные показатели по жилью в соответствующие переменные
		 */
		area = Double.valueOf(accountant_textfields[0].getText());
		normativ = Double.valueOf(accountant_textfields[1].getText());
		v_cold_water = Double.valueOf(accountant_textfields[2].getText());
		v_hot_water = Double.valueOf(accountant_textfields[3].getText());
		v_svet = Double.valueOf(accountant_textfields[4].getText());
		v_gas = Double.valueOf(accountant_textfields[5].getText());
		dolg = Double.valueOf(accountant_textfields[6].getText());
		pereplata = Double.valueOf(accountant_textfields[7].getText());
		tarrif_cold_water = Double.valueOf(accountant_textfields[11].getText());
		tarrif_hot_water = Double.valueOf(accountant_textfields[12].getText());
		tarrif_svet = Double.valueOf(accountant_textfields[13].getText());
		tarrif_gas = Double.valueOf(accountant_textfields[14].getText());

		/**
		 * Если выбран тип жилья "квартира", то расчет производится по следующей формуле
		 * в соответствии с тарифами
		 */
		if (radiobuttons[0].isSelected()) {
			sumcoldwater = v_cold_water * tarrif_cold_water;
			sumhotwater = v_hot_water * tarrif_hot_water;
			sumgas = v_gas * tarrif_gas;
			sumsvet = v_svet * tarrif_svet;
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
			sumcoldwater = v_cold_water * tarrif_cold_water;
			sumhotwater = v_hot_water * tarrif_hot_water;
			sumgas = v_gas * tarrif_gas;
			sumsvet = v_svet * tarrif_svet;
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
		accountant_textfields[8].setText(String.valueOf(sumcoldwater));
		accountant_textfields[9].setText(String.valueOf(sumhotwater));
		accountant_textfields[10].setText(String.valueOf(sumgas));
		accountant_textfields[15].setText(String.valueOf(sumsvet));
		accountant_textfields[16].setText(String.valueOf(itog));

	}

	/**
	 * Аннотация, указывающая на то, что мы собираемся переопределить метод
	 * класса-родителя
	 */
	@Override
	/**
	 * Метод для создания текстовых полей их редактирования и добавления на панель
	 */
	public void textfields_add(int accountant_textfields_number, String accountant_textfields_name[],
			int accountant_textfields_size[], JPanel panel) {
		/**
		 * Создаем цикл, в котором обрабатываются элементы массива текстовых полей
		 */
		for (int i = 0; i < accountant_textfields_number; i++) {
			/**
			 * Создаем экземпляр текстового поля
			 */
			accountant_textfields[i] = new JTextField();
			/**
			 * Устанавливаем начальное значение для текстового поля (0)
			 */
			accountant_textfields[i].setText("0");
			/**
			 * Устанавливаем имя для текстового поля
			 */
			accountant_textfields[i].setName(accountant_textfields_name[i]);
			/**
			 * Устанавливаем размеры текстового поля
			 */
			accountant_textfields[i].setBounds(accountant_textfields_size[i * 4], accountant_textfields_size[i * 4 + 1],
					accountant_textfields_size[i * 4 + 2], accountant_textfields_size[i * 4 + 3]);
			/**
			 * Добавляем текстовое поле на панель
			 */
			panel.add(accountant_textfields[i]);
		}
	}
}
