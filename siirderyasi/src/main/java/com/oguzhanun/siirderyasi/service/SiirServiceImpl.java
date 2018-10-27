package com.oguzhanun.siirderyasi.service;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.oguzhanun.siirderyasi.dao.SiirDao;
import com.oguzhanun.siirderyasi.dao.SiirDaoImpl;
import com.oguzhanun.siirderyasi.entity.Siir;
import com.oguzhanun.siirderyasi.entity.Uye;
import com.oguzhanun.siirderyasi.entity.VerificationToken;


@Service
public class SiirServiceImpl implements SiirService {

	
	@Autowired
	private SiirDao siirDao;
	
	
	@Transactional
	@Override
	public void siirEkle(Siir siir) {
		siirDao.siirEkle(siir);
	}

	@Transactional
	@Override
	public List<Siir> getSiirList() {
		
		List<Siir> siirler = siirDao.getSiirList();
		return siirler;
	}

	@Transactional
	@Override
	public int getTiklama(String siirAdi) {

		//siirDao = new SiirDaoImpl();
		int i = siirDao.getTiklama(siirAdi);
		
		return i;
	}

	@Transactional
	@Override
	public void setTiklama(String siirAdi, int count) {
		
		siirDao.setTiklama(siirAdi, count);
	}

	@Transactional
	@Override
	public String getSiirAdres(String siirAdi) {
		
		return siirDao.getSiirAdres(siirAdi);
	}

	@Override
	@Transactional
	public Siir getSiir(String siirAdi) {
		
		return siirDao.getSiir(siirAdi);
	}

	@Override
	@Transactional
	public void setToken(VerificationToken verificationToken) {
		siirDao.setToken(verificationToken);
	}

	@Override
	@Transactional
	public boolean kullaniciVarMi(String email) {
		
		return siirDao.kullaniciVarMi(email);
	}

	@Override
	@Transactional
	public void setUye(Uye uye) {
		siirDao.setUye(uye);
	}

	@Override
	@Transactional
	public VerificationToken findToken(String token) {
		
		return siirDao.findToken(token);
	}

	@Override
	@Transactional
	public Uye getUye(String kullaniciAdi) {
		
		return siirDao.getUye(kullaniciAdi);
	}

	@Override
	@Transactional
	public void uyeSil(String kullaniciAdi) {

		siirDao.uyeSil(kullaniciAdi);
	}

	@Override
	@Transactional
	public void setOnay(String kullaniciAdi, int i) {
		
		siirDao.setOnay(kullaniciAdi,i);
	}

}
