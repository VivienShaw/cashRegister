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
		Goods good = new Goods("ITEM0001","����","ƿ","ʳƷ","����",3.0,5,"NO");
		assertEquals("ITEM0001", good.getBarcode());
		assertEquals("����", good.getName());
		assertEquals("ƿ", good.getUnit());
		assertEquals("ʳƷ", good.getCategory());
		assertEquals("����", good.getSubcategory());
		assertEquals(3.0, good.getPrice(),0.1);
		assertEquals(5, good.getNum());
		assertEquals("NO", good.getSaletype());
	}
	
	@Test
	public void testSet() {
		Goods good = new Goods();
		good.setBarcode("ITEM0006");
		good.setName("�ָ����ֻ�");
		good.setUnit("��");
		good.setCategory("���");
		good.setSubcategory("�綯���");
		good.setPrice(120.0);
		good.setNum(2);
		good.setSaletype("BUY_THREE_GET_ONE_FREE");
		
		assertEquals("ITEM0006", good.getBarcode());
		assertEquals("�ָ����ֻ�", good.getName());
		assertEquals("��", good.getUnit());
		assertEquals("���", good.getCategory());
		assertEquals("�綯���", good.getSubcategory());
		assertEquals(120.0, good.getPrice(),0.1);
		assertEquals(2, good.getNum());
		assertEquals("BUY_THREE_GET_ONE_FREE", good.getSaletype());
	}
		
	@Test
	public void testEquals() {
		Goods obj1 = new Goods("ITEM0001","����","ƿ","ʳƷ","����",3.0,2,"NO");
		Goods obj2 = new Goods("ITEM0001","����","ƿ","ʳƷ","����",3.0,2,"NO");
		assertEquals(obj1, obj2);
	}

}
