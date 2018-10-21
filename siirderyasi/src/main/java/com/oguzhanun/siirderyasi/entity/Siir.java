package com.oguzhanun.siirderyasi.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="siir")
public class Siir {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="siir_adi")
	private String siirAdi;
	
	@Column(name="adres")
	private String adres;
	
	@Column(name="tiklama")
	private int tiklama;
	
	@Column(name="siir_link_adi")
	private String siirLinkAdi;

	@Column(name="muzik")
	private String muzik;
	
	public Siir() {
		
	}

	public Siir(int id, String siirAdi, String adres, int tiklama) {
		this.id = id;
		this.siirAdi = siirAdi;
		this.adres = adres;
		this.tiklama = tiklama;
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSiirAdi() {
		return siirAdi;
	}

	public void setSiirAdi(String siirAdi) {
		this.siirAdi = siirAdi;
	}

	public int getTiklama() {
		return tiklama;
	}

	public void setTiklama(int tiklama) {
		this.tiklama = tiklama;
	}

	
	public String getAdres() {
		return adres;
	}

	public void setAdres(String adres) {
		this.adres = adres;
	}
	
	public String getSiirLinkAdi() {
		return siirLinkAdi;
	}

	public void setSiirLinkAdi(String siirLinkAdi) {
		this.siirLinkAdi = siirLinkAdi;
	}

	
	public String getMuzik() {
		return muzik;
	}

	public void setMuzik(String muzik) {
		this.muzik = muzik;
	}

	@Override
	public String toString() {
		return "Siir [id=" + id + ", siirAdi=" + siirAdi + ", tiklama=" + tiklama + "]";
	}
	
}
