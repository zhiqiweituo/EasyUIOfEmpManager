package com.oracle.zibo.util;

public class StringUtil {
	//�жϴ�����ַ����Ƿ�Ϊ�ջ�""
	public static boolean isEmpty(String str){
		if(str.isEmpty() || str==null){
			return true;
		}else{
			return false;
		}
	}
	//�жϴ�����ַ����Ƿ�Ϊ�ղ��Ҳ�Ϊ""
	public static boolean isNotEmpty(String str){
		if(str!=null && !str.isEmpty()){
			return true;
		}else{
			return false;
		}
	}
}
