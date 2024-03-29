
package tool;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import data.exercise;

public class search_for_ALL_WORKOUT extends exlistClass{ //exercise 기반 검색 기능 구현 클래스

	
	search_for_ALL_WORKOUT(){
		super("ALL_WORKOUT");
		
	}
	
//	File file = new File("ALL_WORKOUT");
	
	public String search(String name){ //운동 이름으로 운동 이름 / 카테고리 / 계산 방식 찾아주기
//		ArrayList<work> worklist2 = new ArrayList<work>();
		String res = "";
		search_for_ALL_WORKOUT A = new search_for_ALL_WORKOUT();
		try {	
			if(A.get_exlist().size()>0) {
				for(int i = 0; i < A.get_exlist().size(); i++) {
					if (name.equals(A.get_exlist().get(i).getname())) {
						res = A.get_exlist().get(i).getname() + "/" + A.get_exlist().get(i).getcategory() + "/"
								+ A.get_exlist().get(i).getcalmethod();
						break;
					}
					else {
						continue;
					}
				}
			}

		else {
			res = "filenotfound";
		}

		}catch (Exception e) {
			e.printStackTrace();
		}
		A.clear_exlist();
		return res;
	}
	
	public String searchcate(String name){ //운동 이름으로 카테고리 찾기
		String res = "";
		search_for_ALL_WORKOUT B = new search_for_ALL_WORKOUT();
		try {
			if(B.get_exlist().size() > 0) {
				for(int i = 0; i < B.get_exlist().size(); i++) {
					if (name.equals(B.get_exlist().get(i).getcategory())) {
						res =  res + B.get_exlist().get(i).getname() + "$";
					}
					else {
						continue;
					}
				}
			}
				
			else {
				res = "filenotfound"; //배열리스트에 하나도 저장 안되어 있단 것은 .. 파일을 못 찾은 것(예외)
			}

		}catch (Exception e) {
			e.printStackTrace();
		}
		B.clear_exlist();
		return res;
	}
}
