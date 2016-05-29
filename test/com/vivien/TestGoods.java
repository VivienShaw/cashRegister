package com.vivien;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.vivien.Goods;

import junit.framework.TestCase;

public class TestGoods extends TestCase {

	@Before
	protected void setUp() throws Exception {
		super.setUp();
	}

	@After
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	@Test
	public void testGet() {
		Goods good = new Goods("ITEM0001","可乐","瓶","食品","饮料",3.0,5,"NO");
		assertEquals("ITEM0001", good.getBarcode());
		assertEquals("可乐", good.getName());
		assertEquals("瓶", good.getUnit());
		assertEquals("食品", good.getCategory());
		assertEquals("饮料", good.getSubcategory());
		assertEquals(3.0, good.getPrice(),0.1);
		assertEquals(5, good.getNum());
		assertEquals("NO", good.getSaletype());
	}
	
	@Test
	public void testSet() {
		Goods good = new Goods();
		good.setBarcode("ITEM0006");
		good.setName("乐高数字火车");
		good.setUnit("件");
		good.setCategory("玩具");
		good.setSubcategory("电动玩具");
		good.setPrice(120.0);
		good.setNum(2);
		good.setSaletype("BUY_THREE_GET_ONE_FREE");
		
		assertEquals("ITEM0006", good.getBarcode());
		assertEquals("乐高数字火车", good.getName());
		assertEquals("件", good.getUnit());
		assertEquals("玩具", good.getCategory());
		assertEquals("电动玩具", good.getSubcategory());
		assertEquals(120.0, good.getPrice(),0.1);
		assertEquals(2, good.getNum());
		assertEquals("BUY_THREE_GET_ONE_FREE", good.getSaletype());
	}
		
	@Test
	public void testEquals() {
		Goods obj1 = new Goods("ITEM0001","可乐","瓶","食品","饮料",3.0,2,"NO");
		Goods obj2 = new Goods("ITEM0001","可乐","瓶","食品","饮料",3.0,2,"NO");
		assertEquals(obj1, obj2);
	}

}
