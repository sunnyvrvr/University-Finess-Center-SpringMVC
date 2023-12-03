package com.health.domain;

import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.Setter;

@Data @Setter @Component
public class MemberVO {
	String loginId;
	String pwd;
	String studentId;
	String mStatus;
	String name;
	String phone;
	String email;
	String accountBank;
	String accountNo;
}
