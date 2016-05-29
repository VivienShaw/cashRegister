package com.vivien.sales;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.vivien.Goods;
import com.vivien.Util;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ForSale {

	static Map<String, JSONObject> goodsMap = new HashMap<String, JSONObject>();
	static Map<String, String> salesMap = new HashMap<String,String>();
	
	static String goodsPath = "goods.json";
	static String salePath = "saleList.json";
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		importJson(goodsPath,salePath);
		Scanner scan = new Scanner(System.in);
		String strs = scan.nextLine();
		String[] input = strs.trim().split(",");
		List<Goods> buyList = new ArrayList<Goods>();
		for(int i = 0;i<input.length;i++) {
			Goods good = new Goods();
			good = getGoodFromBarcode(input[i]);
			buyList.add(good);
/*
//			String result = "";
//			result += good.toString();
//			switch (good.getSaletype()) {
//			case "BUY_THREE_GET_ONE_FREE":
//				result += good.getPrice()*(good.getNum()-good.getNum()/3) + "";
//				break;
//			default:
//				result += good.getPrice()*good.getNum() + "";
//				break;
//			}
//			result += "(元)";
//			System.out.println(result);
*/			
		}
		Util.printList(buyList);
		scan.close();
	}
	
	public static Goods getGoodFromBarcode(String barcodes) {
		Goods good  = new Goods();
		String barcode = barcodes.split("-")[0];
		String saleType = "NO";
		int num = 1;
		if(salesMap.containsKey(barcode)) {
			saleType = salesMap.get(barcode);
		}
		
		if(barcodes.contains("-")) {
			num = Integer.valueOf(barcodes.split("-")[1]);
		}
		
		if(goodsMap.containsKey(barcode)) {
			JSONObject jsonObject = goodsMap.get(barcode);
			good.setBarcode(jsonObject.getString("barcode"));
			good.setName(jsonObject.getString("name"));
			good.setUnit(jsonObject.getString("unit"));
			good.setCategory(jsonObject.getString("category"));
			good.setSubcategory(jsonObject.getString("subcategory"));
			good.setPrice(jsonObject.getDouble("price"));
			good.setNum(num);
			good.setSaletype(saleType);
		}
		else {
			System.out.println("无此商品");
			return null;
		}
		return good;
	} 
	
	public static void importJson(String goodspath,String salepath) {
		String JsonGoods = Util.readFile(goodspath);
		String JsonSale = Util.readFile(salepath);
		JSONArray jAGoods = JSONArray.fromObject(JsonGoods);
		JSONArray jASale = JSONArray.fromObject(JsonSale);
		int sizeGoods = jAGoods.size();
		int sizeSales = jASale.size();

		// 解析所有商品信息
		for (int j = 0; j < sizeGoods; j++) {
			JSONObject jOGoods = jAGoods.getJSONObject(j);
			goodsMap.put(jOGoods.get("barcode").toString(), jOGoods);
		}
				
		//解析参加活动的商品信息
		for(int i = 0;i<sizeSales;i++) {
			JSONObject jOSale = jASale.getJSONObject(0);
			String saleType = jOSale.getString("type");
			String str = jOSale.getString("barcodes");
			String[] saleArr = str.trim().split(",");
			for(int j = 0;j<saleArr.length;j++) {
				salesMap.put(saleArr[j], saleType);
			}
		}
			
	}
	
}
