package kr.or.ddit.user.model;

import java.util.Date;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

public class UserVoJsr303 {
	
	// 공백으로 오면 안되기 때문에 @NotEmpty사용
	// 글자 길이 제한 @Length 사용
	@Length(min=5)
	@NotEmpty
	private String userId;
	
	@NotEmpty
	private String name;
	private String pass;
	private String addr1;
	private String addr2;
	private String zipcd;
	private String email;
	private String tel;
	private String profile;
	
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date birth;
	
	
	@Override
	public String toString() {
		return "UserVo [userId=" + userId + ", name=" + name + ", pass=" + pass + ", addr1=" + addr1 + ", addr2="
				+ addr2 + ", zipcd=" + zipcd + ", birth=" + birth + ", email=" + email + ", tel=" + tel + ", profile="
				+ profile + ", rnum=" + rnum + "]";
	}

		// 기본생성자
		public UserVoJsr303() {

		}
	
	// 페이징 처리
	private int rnum;

	
	public int getRnum() {
		return rnum;
	}

	public void setRnum(int rnum) {
		this.rnum = rnum;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getAddr1() {
		return addr1;
	}

	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}

	public String getAddr2() {
		return addr2;
	}

	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}

	public String getZipcd() {
		return zipcd;
	}

	public void setZipcd(String zipcd) {
		this.zipcd = zipcd;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}



	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}




}
