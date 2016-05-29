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
	
	//��ӡ�����嵥
	public static void printList(List<Goods> goods) {
		String result = "";
		double totalPrice = 0;
		List<Goods> saleList = new ArrayList<Goods>();
		result += "             <ûǮ׬�̵�>�����嵥                              "+"\n";
		for (Goods obj:goods) {
			switch (obj.getSaletype()) {
			case "BUY_THREE_GET_ONE_FREE":
				result += obj.toString()
				+ numberFormat(obj.getPrice()*(obj.getNum()-obj.getNum()/3)) 
				+ "(Ԫ)" + "\n";
				totalPrice += obj.getPrice()*(obj.getNum()-obj.getNum()/3);
				saleList.add(obj);
				break;
			default:
				result += obj.toString()
				+ numberFormat(obj.getPrice()*obj.getNum()) 
				+ "(Ԫ)" + "\n";
				totalPrice += obj.getPrice()*obj.getNum();
				break;
			}
		}
		System.out.println(result);
		
		String saleResult = "";
		double cutPrice = 0;
		if (saleList != null && saleList.size() > 0) {
			//�����嵥���вμӻ����Ʒ
			saleResult += "������һ��Ʒ��"+"\n";
			for (Goods obj:saleList) {
				if (obj.getNum() >= 3) {
					//��ǰ��Ʒ����������һ�Ļ
					saleResult += "���ƣ�" + obj.getName()
			          + "��������" + obj.getNum()/3 + obj.getUnit()
			          +"\n";
			        cutPrice += obj.getPrice() * (obj.getNum()/3);
				}
			}
			if (cutPrice != 0) {
				System.out.println(saleResult);
			}
		}
			
		System.out.print("�ܼƣ�" + numberFormat(totalPrice) + "(Ԫ)");
		if (cutPrice != 0) {
			System.out.println("  ��ʡ��" + numberFormat(cutPrice)+ "(Ԫ)");
		}
	}
	
	//���ñ���С�������λ
	static String numberFormat (double num) {
		DecimalFormat df = new DecimalFormat( "0.00"); 
		return df.format(num);
	}


}
