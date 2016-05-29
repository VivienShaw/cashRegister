package com.vivien;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Util {

	public static String readFile(String Path){
		BufferedReader reader = null;
		String laststr = "";
		try {
			FileInputStream fileInputStream = new FileInputStream(Path);
			InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
			reader = new BufferedReader(inputStreamReader);
			String tempString = null;
			while((tempString = reader.readLine()) != null) {
				laststr += tempString;
			}
			reader.close();
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			if(reader != null){
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return laststr;
	}
	
	//打印购物清单
	public static void printList(List<Goods> goods) {
		String result = "";
		double totalPrice = 0;
		List<Goods> saleList = new ArrayList<Goods>();
		result += "             <没钱赚商店>购物清单                              "+"\n";
		for (Goods obj:goods) {
			switch (obj.getSaletype()) {
			case "BUY_THREE_GET_ONE_FREE":
				result += obj.toString()
				+ numberFormat(obj.getPrice()*(obj.getNum()-obj.getNum()/3)) 
				+ "(元)" + "\n";
				totalPrice += obj.getPrice()*(obj.getNum()-obj.getNum()/3);
				saleList.add(obj);
				break;
			default:
				result += obj.toString()
				+ numberFormat(obj.getPrice()*obj.getNum()) 
				+ "(元)" + "\n";
				totalPrice += obj.getPrice()*obj.getNum();
				break;
			}
		}
		System.out.println(result);
		
		String saleResult = "";
		double cutPrice = 0;
		if (saleList != null && saleList.size() > 0) {
			//购物清单中有参加活动的商品
			saleResult += "买三免一商品："+"\n";
			for (Goods obj:saleList) {
				if (obj.getNum() >= 3) {
					//当前商品满足满三免一的活动
					saleResult += "名称：" + obj.getName()
			          + "，数量：" + obj.getNum()/3 + obj.getUnit()
			          +"\n";
			        cutPrice += obj.getPrice() * (obj.getNum()/3);
				}
			}
			if (cutPrice != 0) {
				System.out.println(saleResult);
			}
		}
			
		System.out.print("总计：" + numberFormat(totalPrice) + "(元)");
		if (cutPrice != 0) {
			System.out.println("  节省：" + numberFormat(cutPrice)+ "(元)");
		}
	}
	
	//设置保留小数点后两位
	static String numberFormat (double num) {
		DecimalFormat df = new DecimalFormat( "0.00"); 
		return df.format(num);
	}


}
