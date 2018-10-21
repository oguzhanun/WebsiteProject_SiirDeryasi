package com.oguzhanun.siirderyasi.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="uye")
public class Uye {

	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="email")
	private String email;
	
	@Column(name="isim")
	private String isim;
	
	@Column(name="onayli")
	private int onayli;

	public Uye() {

	}

	public Uye(int id, String email, String isim, int onayli) {
		this.id = id;
		this.email = email;
		this.isim = isim;
		this.onayli = onayli;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIsim() {
		return isim;
	}

	public void setIsim(String isim) {
		this.isim = isim;
	}

	public int isOnayli() {
		return onayli;
	}

	public void setOnayli(int onayli) {
		this.onayli = onayli;
	}

	@Override
	public String toString() {
		return "Uye [id=" + id + ", email=" + email + ", isim=" + isim + ", onayli=" + onayli + "]";
	}
}
