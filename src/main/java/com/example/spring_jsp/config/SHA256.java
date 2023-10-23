package com.example.spring_jsp.config;

import java.security.MessageDigest;

import org.springframework.stereotype.Component;

@Component
public class SHA256 {
	public String getSHA256(String str) {
		// str에 해쉬를 적용해주는 값을 반환 해서 이용하겠다는 함수
		// 악의적인 공격자에 대한 공격에 대해 방어해주는 역할
		StringBuffer result = new StringBuffer();
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			// 사용자가 입력한 값을 SHA-256으로 알고리즘을 적용할 수 있도록 만들어줌
			byte[] salt = "This is salt to protect from hacker.".getBytes();
			// 단순하게 SHA-256을 접근하게 되면 해커의 공격에 취약하기 때문에 salt값을 적용시켜줌
			// 내가 원하는 salt값 아무거나 입력해주면 됨
			digest.reset();
			digest.update(salt);
			byte[] chars = digest.digest(str.getBytes("UTF-8"));
			// 배열변수를 만들어서 실제로 해쉬로 적용한 값을 chars 변수에다가 담아줌
			for(int i = 0; i < chars.length; i++) {
				String hex = Integer.toHexString(0xff & chars[i]);
				// 헥스값과 현재 이 헤쉬값을 작용한 해당 인덱스를 & 연산을 해주고
				if(hex.length() == 1) result.append("0");
				// 한자리수인 경우에는 0을 붙여서 총 2자리값을 가지는 16진수 형태로 출력할 수 있게 만들어주고
				result.append(hex);
				// 뒤에 헥스값을 하나하나 달아줌
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result.toString();
		// 해당 헥스값을 반환함
	}
}
