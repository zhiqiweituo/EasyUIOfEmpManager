package com.zhi.util;

public class StringUtil {
	//判断传入的字符串是否为空或""
	public static boolean isEmpty(String str){
		if(str.isEmpty() || str==null){
			return true;
		}else{
			return false;
		}
	}
	//判断传入的字符串是否不为空并且不为""
	public static boolean isNotEmpty(String str){
		if(str!=null && !str.isEmpty()){
			return true;
		}else{
			return false;
		}
	}
}
