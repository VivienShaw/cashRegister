package com.vivien.sales;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.vivien.Goods;

import junit.framework.TestCase;
import net.sf.json.JSONObject;

public class TestForSale extends TestCase {

	String goodsPath = "";
	String salePath = "";

	@Before
	protected void setUp() throws Exception {
		goodsPath = "test/goods.json";
		salePath = "test/sales.json";
	}

	@After
	protected void tearDown() throws Exception {

	}

	@Test
	public void testGetGoodFromBarcode() {
		ForSale.importJson(goodsPath, salePath);
		Goods good = new Goods("ITEM0000", "可乐", "瓶", "食品", "碳酸饮料", 3.0, 3, "BUY_THREE_GET_ONE_FREE");
		assertEquals(good, ForSale.getGoodFromBarcode("ITEM0000-3"));
	}

	@Test
	public void testImportJson() {
		ForSale.importJson(goodsPath, salePath);
		Map<String, JSONObject> map = new HashMap<String, JSONObject>();
		String jsonStr = "{\"barcode\":\"ITEM0000\",\"name\":\"可乐\",\"unit\":\"瓶\",\"category\":\"食品\",\"subcategory\":\"碳酸饮料\",\"price\":\"3.0\"}";
		JSONObject jsonObject = JSONObject.fromObject(jsonStr);
		map.put("ITEM0000", jsonObject);
		assertEquals(map, ForSale.goodsMap);

		Map<String, String> salesmap = new HashMap<String, String>();
		salesmap.put("ITEM0000", "BUY_THREE_GET_ONE_FREE");
		salesmap.put("ITEM0002", "BUY_THREE_GET_ONE_FREE");
		assertEquals(salesmap, ForSale.salesMap);
	}

}
