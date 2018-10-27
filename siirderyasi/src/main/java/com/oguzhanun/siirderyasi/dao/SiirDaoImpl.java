package com.oguzhanun.siirderyasi.dao;


import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.oguzhanun.siirderyasi.entity.Siir;
import com.oguzhanun.siirderyasi.entity.Uye;
import com.oguzhanun.siirderyasi.entity.VerificationToken;


@Repository
public class SiirDaoImpl implements SiirDao {

	
	@Autowired
	private SessionFactory sessionFactory;
	
	
	@Override
	public void siirEkle(Siir siir) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(siir);
	}

	@Override
	public List<Siir> getSiirList() {
	
		Session session = sessionFactory.getCurrentSession();
		Query<Siir> query = session.createQuery("from Siir order by tiklama DESC", Siir.class);
		List<Siir> siirler = query.getResultList();
		return siirler;
	}

	@Override
	public Siir getSiir(String siirLinkAdi) {
		
		Session session = sessionFactory.getCurrentSession();
		Query<Siir> query = session.createQuery("from Siir where siirLinkAdi=:siir_link_adi",Siir.class);
		query.setParameter("siir_link_adi", siirLinkAdi);
		
		// Başlangıç pozisyonu set edilmeden alınan getFirstResult methodu 0'ı başlangıç pozisyonu olarak belirliyor...
		//set ettiğinin sonucunu veriyor. **************ANLAMADIM TAM OLARAK***********
		//query.setFirstResult(1);
		//int id = query.getFirstResult();
		List<Siir> siirler = query.getResultList();
		Siir siir = null;
		
		if(siirler != null) {
			siir = siirler.get(0);
		}

		//System.out.println("id ="+ id);
		//Siir siir = session.get(Siir.class, id);
		
		return siir;
	}
	
	@Override
	public int getTiklama(String siirAdi) {

		Session session = sessionFactory.getCurrentSession();
		
		Query<Siir> query = session.createQuery("from Siir E where E.siirAdi=:siir_adi", Siir.class);
		query.setParameter("siir_adi", siirAdi);
		
		List<Siir> siirler = query.getResultList();
		Siir siir = siirler.get(0);
		
		// Başlangıç pozisyonu set edilmeden alınan getFirstResult methodu 0'ı başlangıç pozisyonu olarak belirliyor...
		//query.setFirstResult(1);
		//int id = query.getFirstResult();
		//Siir siir= session.get(Siir.class, id);
		
		System.out.println(siir.getSiirAdi());
		
		return siir.getTiklama();
	}

	@Override
	public void setTiklama(String siirAdi, int count) {
		
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("update Siir set tiklama=:tiklama where siirLinkAdi=:siir_link_adi");
		query.setParameter("siir_link_adi", siirAdi);
		query.setParameter("tiklama", count);
		query.executeUpdate();
	}

	@Override
	public String getSiirAdres(String siirAdi) {
		
		Session session = sessionFactory.getCurrentSession();
		Query<Siir> query = session.createQuery("from Siir where siirAdi=:siirAdi",Siir.class);
		query.setParameter("siirAdi", siirAdi);
		
		// Başlangıç pozisyonu set edilmeden alınan getFirstResult methodu 0'ı başlangıç pozisyonu olarak belirliyor...
		//query.setFirstResult(1);
		//int id = query.getFirstResult();
		//Siir siir = session.get(Siir.class, id);
		
		List<Siir> siirler = query.getResultList();
		Siir siir = siirler.get(0);
		
		return siir.getAdres();
	}

	@Override
	public void setToken(VerificationToken verificationToken) {
		
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(verificationToken);
	}
	@Override
	public VerificationToken findToken(String token) {
		Session session = sessionFactory.getCurrentSession();
		Query<VerificationToken> tokenQuery = session.createQuery("from VerificationToken where token=:token", VerificationToken.class);
		
		tokenQuery.setParameter("token",token);
		System.out.println(token);
		List<VerificationToken> tokenList = tokenQuery.getResultList();
		if(!tokenList.isEmpty()) {
			VerificationToken vt = tokenList.get(0);
			return vt;
		} else
			return null;
	}

	@Override
	public boolean kullaniciVarMi(String email) {
		Session session = sessionFactory.getCurrentSession();
		Query<Uye> query = session.createQuery("from Uye where email=:email",Uye.class);
		query.setParameter("email", email);
		
		List<Uye> uyeler = new ArrayList<>();
		uyeler = query.getResultList();
		
		if(uyeler.isEmpty()) {
			System.out.println("üye mevcut...");
			return false;
		}
		return true;
	}

	@Override
	public void setUye(Uye uye) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(uye);
	}

	@Override
	public Uye getUye(String kullaniciAdi) {
		
		Session session = sessionFactory.getCurrentSession();
		Query<Uye> query = session.createQuery("from Uye where email=:email",Uye.class);
		query.setParameter("email", kullaniciAdi);
		Uye uye = query.getSingleResult();
		
		return uye;
	}
	
	@Override
	public void uyeSil(String uye) {
		
		Session session = sessionFactory.getCurrentSession();
		System.out.println("kullanıcı adı = "+ uye);
		
		Query queryUye = session.createQuery("delete from Uye where email=:email");
		queryUye.setParameter("email", uye);
		queryUye.executeUpdate();
		
		Query queryVerificationToken = session.createQuery("delete from VerificationToken where email=:email");
		queryVerificationToken.setParameter("email",uye);
		queryVerificationToken.executeUpdate();	
	}

	@Override
	public void setOnay(String kullaniciAdi, int i) {

		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("update Uye set onayli=:i where email=:email ");
		query.setParameter("email", kullaniciAdi);
		query.setParameter("i", i);
		query.executeUpdate();
	}
}
