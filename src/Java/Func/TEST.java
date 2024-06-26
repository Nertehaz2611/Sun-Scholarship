package Func;

import java.util.ArrayList;
import java.util.List;

import Connection.DBconnet;
import Model.kho;

public class TEST {
	public static void main(String[] args) {
		DBconnet DBcnn = new DBconnet();
		List<String> listMasp = new ArrayList<>();
		listMasp = DBcnn.selectAllMasp();
		for (String e : listMasp) {
			kho kho = new kho(e, 5);
			DBcnn.insertKho(new Object[] {kho.getMaSp(), kho.getSoluong()});
		}
	}
}
