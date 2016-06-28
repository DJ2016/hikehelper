package test;

import java.io.IOException;
import java.util.List;
import java.util.function.Consumer;

import parser.Product;
import parser.SportmasterParser;

public class ParserTest {

	public static void main(String[] args) throws IOException {
		SportmasterParser parser = new SportmasterParser();
		String item = "���������";
		List<Product> list = parser.query(item);
		System.out.println("����� ���������� �������� �����������: " + parser.lastCount());
		System.out.println("����� ������� ����������� ��������� � \"" + item + "\":");
		list.forEach(new Consumer<Product>(){

			@Override
			public void accept(Product arg0) {
				System.out.println(arg0.toString());
			}
			
		});
		
		System.out.println("\n");
		// off sort
		parser.setSorted(false);
		list = parser.query(item);
		System.out.println("����� ���������� �������� �����������: " + parser.lastCount());
		System.out.println("���������� ����������� ��������� � \"" + item + "\":");
		list.forEach(new Consumer<Product>(){

			@Override
			public void accept(Product arg0) {
				System.out.println(arg0.toString());
			}
			
		});
	}

}
