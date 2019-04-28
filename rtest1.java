package rentCalc;

/**
 * Импортирование класса, содержащего набор методов сравнения
 */
import static org.junit.Assert.*;
/**
 * Импортирование основного пакета для тестирования
 */
import org.junit.Test;

public class test1 {

	@org.junit.Test
	public void test() {
		/**
		 * Входными данными является метод, производящий расчета квартплаты и
		 * предполагаемый результат данного расчета
		 */
		Calc a = new Calc(17,10,23,24,56,0,34,19,1,27.5,25.43,3.06,7.1);
		a.Calculate();
		/**
		 * Сравнение результата и ожидаемого значения
		 */
		assertEquals(String.valueOf(a.getitog()), "1827.49");
	}
}
