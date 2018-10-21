package com.oguzhanun.siirderyasi.dao;

import java.util.List;

import com.oguzhanun.siirderyasi.entity.Siir;
import com.oguzhanun.siirderyasi.entity.Uye;
import com.oguzhanun.siirderyasi.entity.VerificationToken;

public interface SiirDao {

	void siirEkle(Siir siir);

	List<Siir> getSiirList();

	int getTiklama(String siirAdi);

	void setTiklama(String siirAdi, int count);

	String getSiirAdres(String siirAdi);

	Siir getSiir(String siirAdi);

	void setToken(VerificationToken verificationToken);

	VerificationToken findToken(String token);

	boolean kullaniciVarMi(String email);

	void setUye(Uye uye);

}
