package edu.dgu.coffeemaker;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.dgu.coffeemaker.exceptions.InventoryException;
import edu.dgu.coffeemaker.exceptions.RecipeException;

/**
 * JUnit test for CoffeeMaker class.
 * 
 * @author shChoi
 */

public class CoffeeMakerTest_Before_Edit {

	private CoffeeMaker coffeeMaker;

	private Recipe recipe1;
	private Recipe recipe2;
	private Recipe recipe3;
	private Recipe recipe4;
	private Recipe recipe5;
	private Recipe recipe6;
	private Recipe recipe7;

	@Before
	public void setUp() throws RecipeException {
		coffeeMaker = new CoffeeMaker();

		recipe1 = new Recipe();
		recipe1.setName("Coffee");
		recipe1.setAmtChocolate("0");
		recipe1.setAmtCoffee("3");
		recipe1.setAmtMilk("1");
		recipe1.setAmtSugar("1");
		recipe1.setPrice("50");

		recipe2 = new Recipe();
		recipe2.setName("Mocha");
		recipe2.setAmtChocolate("20");
		recipe2.setAmtCoffee("3");
		recipe2.setAmtMilk("1");
		recipe2.setAmtSugar("1");
		recipe2.setPrice("70");

		recipe3 = new Recipe();
		recipe3.setName("Latte");
		recipe3.setAmtChocolate("0");
		recipe3.setAmtCoffee("3");
		recipe3.setAmtMilk("3");
		recipe3.setAmtSugar("1");
		recipe3.setPrice("59");

		recipe4 = new Recipe();
		recipe4.setName("Hot Chocolate");
		recipe4.setAmtChocolate("4");
		recipe4.setAmtCoffee("0");
		recipe4.setAmtMilk("1");
		recipe4.setAmtSugar("1");
		recipe4.setPrice("62");

		recipe5 = new Recipe();
		recipe5.setName("cappuccino");
		recipe5.setAmtChocolate("0");
		recipe5.setAmtCoffee("3");
		recipe5.setAmtMilk("0");
		recipe5.setAmtSugar("0");
		recipe5.setPrice("54");

		recipe6 = new Recipe();
		recipe6.setName("Custom Coffee");
		recipe6.setAmtChocolate("0");
		recipe6.setAmtCoffee("5");
		recipe6.setAmtMilk("2");
		recipe6.setAmtSugar("1");
		recipe6.setPrice("65");

		recipe7 = new Recipe();
		recipe7.setName("Black coffee");
		recipe7.setAmtChocolate("0");
		recipe7.setAmtCoffee("9");
		recipe7.setAmtMilk("0");
		recipe7.setAmtSugar("19");
		recipe7.setPrice("77");
		
	}
	
	
	/** [UC1 | 레시피 추가] */

	/**
	 * 커피레시피가 없을 때 유효한 레시피를 추가하면 커피레시피 하나가 커피메이커에 추가된다.
	 */
	@Test
	public void testAddRecipe1() {
		coffeeMaker.addRecipe(recipe1);
		assertNotEquals(null, coffeeMaker.getRecipes()[0]);
	}
	
	/**
	 * 커피레시피가 없을 때 유효한 레시피를 추가하면 커피레시피 하나가 커피메이커에 추가된다.
	 */
	@Test
	public void testAddRecipe2_NG() {
		coffeeMaker.addRecipe(recipe1);
		coffeeMaker.addRecipe(recipe2);
		assertEquals("Coffee", recipe1.getName());
		assertEquals("Latte", recipe2.getName());
	}

	/** [UC2 | 레시피 삭제] */
	
	/**
	 * 커피레시피가 없을때 삭제하면 null응답을 받는다.
	 */
	@Test
	public void testDeleteRecipe1() {
		assertEquals(null, coffeeMaker.deleteRecipe(0));
	}


	/**
	 * 유효한 커피레시피를 추가한 뒤 삭제하면 커피레시피가 삭제된다.
	 */
	@Test
	public void testDeleteRecipe2_NG() {
		coffeeMaker.addRecipe(recipe1);
		coffeeMaker.deleteRecipe(0);
		assertEquals(null, coffeeMaker.getRecipes()[0]);
	}
	
	/** [UC3 | 레시피 수정] */

	/**
	 * 유효한 커피레시피를 추가한 뒤 추가한 레시피를 수정하면 반영된다.
	 */
	@Test
	public void testEditRecipe1() {
		coffeeMaker.addRecipe(recipe1);
		coffeeMaker.editRecipe(0, recipe2);
		assertEquals(recipe2.getPrice(), coffeeMaker.getRecipes()[0].getPrice());
	}

	/**
	 * 잘못된 수량으로 인벤토리를 수정하면 오류가 발생한다.
	 */
	@Test
	public void testEditRecipe2_NG() {
		assertEquals(null, coffeeMaker.editRecipe(-1, recipe2));
	}

	/** [UC4 | 인벤토리 추가] */

	/**
	 * 기본 재고가 있는 커피 메이커가 있을 때 정상적인 수량으로 인벤토리를 추가할 때 인벤토리 오류가 발생하지 않는다.
	 */
	@Test
	public void testAddInventory1() throws InventoryException {
		coffeeMaker.addInventory("4", "7", "0", "9");
	}

	/**
	 * 기본 재고가 있는 커피 메이커가 있을 때, amtCoffee에 잘못된 수량으로 인벤토리를 추가하면 인벤토리 오류가 발생한다.
	 */
	@Test
	public void testAddInventory2_NG() throws InventoryException {
		coffeeMaker.addInventory("-4", "1", "2", "3");
	}

	/** [UC5 | 인벤토리 확인] */
	/**
	 * 커피메이커에서 인벤토리를 확인하면 기본 인벤토리의 문자열 표현을 받는다.
	 */
	@Test
	public void testCheckInventory1() {

		StringBuffer buf = new StringBuffer();
		buf.append("Coffee: ");
		buf.append(15);
		buf.append("\n");
		buf.append("Milk: ");
		buf.append(15);
		buf.append("\n");
		buf.append("Sugar: ");
		buf.append(15);
		buf.append("\n");
		buf.append("Chocolate: ");
		buf.append(15);
		buf.append("\n");

		assertEquals(buf.toString(), coffeeMaker.checkInventory());
	}

	/**
	 * 커피메이커에 커피(4)를 추가한 후 인벤토리를 확인하면 새로운 인벤토리의 문자열 표현을 받는다.
	 */
	@Test
	public void testCheckInventory2_NG() throws InventoryException {

		coffeeMaker.addInventory("4", "0", "0", "0");

		StringBuffer buf = new StringBuffer();
		buf.append("Coffee: ");
		buf.append(15 + 6);
		buf.append("\n");
		buf.append("Milk: ");
		buf.append(15);
		buf.append("\n");
		buf.append("Sugar: ");
		buf.append(15);
		buf.append("\n");
		buf.append("Chocolate: ");
		buf.append(15);
		buf.append("\n");

		assertEquals(buf.toString(), coffeeMaker.checkInventory());
	}

	/** [UC6 | 음료 구매] */

	/**
	 * 유효한 레시피를 선택하고 많은 커피비용을 지불하면 올바른 거스름돈을 돌려받는다.
	 */
	@Test
	public void testpurchaseBeverage1() {
		coffeeMaker.addRecipe(recipe1);
		assertEquals(25, coffeeMaker.makeCoffee(0, 75));
	}
	
	/**
	 * 유효한 레시피를 선택하고 많은 커피비용을 지불하면 올바른 거스름돈을 돌려받는다.
	 */
	@Test
	public void testpurchaseBeverage2_NG() {
		coffeeMaker.addRecipe(recipe1);
		coffeeMaker.addRecipe(recipe2);
		coffeeMaker.addRecipe(recipe3);
		assertEquals(112, coffeeMaker.makeCoffee(2, 55));
	}
}
