package rentCalc;

/**
 * Импортирование класса, содержащего набор методов сравнения
 */
import static org.junit.Assert.*;
/**
 * Импортирование основного пакета для тестирования
 */
import org.junit.Test;

public class test5 {

	@org.junit.Test
	public void test() {
		/**
		 * Входными данными является метод, производящий расчета квартплаты и
		 * предполагаемый результат данного расчета
		 */
		Calc a = new Calc(38,58,86,78,41,71,77,17,2,52,50,50,40);
		a.Calculate();
		/**
		 * Сравнение результата и ожидаемого значения
		 */
		assertEquals(String.valueOf(a.getitog()), "13606.0");
	}
}
