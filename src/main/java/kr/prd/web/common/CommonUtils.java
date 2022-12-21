package kr.prd.web.common;

import java.util.UUID;

public class CommonUtils {

	public final  static String uploadPath = "C:\\download\\files\\";

	 /*파일저장 이름을 랜덤하게 만들기  위해 사용 */
	 public static String getUUID( ) {
		 String uuid =  UUID.randomUUID().toString();
		 uuid = uuid.replaceAll("-", "").substring(0, 16);
		 return uuid;
	 }
	 
	 
}
