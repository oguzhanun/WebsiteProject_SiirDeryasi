package com.oguzhanun.siirderyasi.service;

import java.util.List;

import com.oguzhanun.siirderyasi.entity.Siir;
import com.oguzhanun.siirderyasi.entity.Uye;
import com.oguzhanun.siirderyasi.entity.VerificationToken;

public interface SiirService {

	void siirEkle(Siir siir);

	List<Siir> getSiirList();

	int getTiklama(String siirAdi);

	void setTiklama(String siirAdi, int count);

	String getSiirAdres(String siirAdi);

	Siir getSiir(String siirAdi);

	void setToken(VerificationToken verificationToken);
	
	boolean kullaniciVarMi(String email);

	void setUye(Uye uye);

	VerificationToken findToken(String token);

	Uye getUye(String kullaniciAdi);

	void uyeSil(String kullaniciAdi);

	void setOnay(String kullaniciAdi, int i);

}
