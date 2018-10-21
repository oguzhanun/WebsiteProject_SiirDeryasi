package com.oguzhanun.siirderyasi.config;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.oguzhanun.siirderyasi.entity.Siir;
import com.oguzhanun.siirderyasi.service.SiirService;


@Component
public class Counter extends HandlerInterceptorAdapter {
	
	
	@Autowired
	private SiirService siirService;
	/*
	 * controller da pathvariable ile çalışan bir metod yazılacak
	 * link üzerinden metoda girildiğinde pathvariable daki isim ile veritabanına ulaşılacak
	 * veritabanından istenen şiir çekilecek
	 * 
	 * pathvariable a gelen request öncesinde counter a uğrayacak
	 * counter da servlet path ini alıp baştaki kesmeden arındırıp hql sorgusu için serviceten faydalanılacak
	 * geri dönen rakam baz alınarak session a göre önceki işlemler tekrar edilecek
	 * 
	 * */
	private static Object obj = new Object();
	
	private int count ;
	
	private String siirAdi;
	
	private static Set<String> tiklananlarListesi;
	
	private static List<String> liste;
	
	private Siir siir;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
			
		liste = new ArrayList<>();
		liste.add("/anasayfa/SensizOlmuyor");
		liste.add("/anasayfa/BrehBreh");
		liste.add("/anasayfa/Test-2");
		liste.add("/anasayfa/Test-2-html");
		liste.add("/anasayfa/BirZamanlarBenDe");
		liste.add("/anasayfa/YalinKilicCiktimYola");
		
		//siirService = new SiirServiceImpl();
		
		System.out.println("merhaba televole...");
		
		//request üzerinde üzerinde bulunana siir adını bulmak için...
		String servletPath = request.getServletPath();
		
		System.out.println("servletPath = "+ servletPath);
		
		System.out.println(request.getSession().getAttribute(servletPath));
		
		//anasayfa istendiğinde tiklama sayıları daha oluşturulmamış durumda nullpointer verir
		if(liste.contains(servletPath)) {
			
			siirAdi = servletPath.substring(10);
			System.out.println("Şiir Adı ="+ siirAdi);
			
			// Burada ihtiyaç duyulan tıklama sayısını alırken şiir nesnesinin tüm özelliklerine ulaşabilmek için
			// şiir nesnesinin önce kendisini almak gerekecek. Sonrasında adres, tiklama sayısı vs. bilgileri buradan alınacak
			//count = siirService.getTiklama(siirAdi);
			siir = siirService.getSiir(siirAdi);
			count = siir.getTiklama();
			
			//Session üzerinden tiklananlar isimli attributete siir adının kayıtlı olup olmadığını bulmak için kullanılacak...
			tiklananlarListesi = (HashSet) request.getSession().getAttribute("tiklananlar");
		
			if(tiklananlarListesi == null) {
				tiklananlarListesi = new HashSet<String>();
			}
			
			//başlangıçta tıklananlar listesinin içi boş nullpointer verir.
			if( !tiklananlarListesi.contains(servletPath)) {
				count = count + 1;
				siirService.setTiklama(siirAdi, count);
				tiklananlarListesi.add(servletPath);
				request.getSession().setAttribute("tiklananlar", tiklananlarListesi);
			}
		}
		
		return super.preHandle(request, response, obj);
		
//		switch (servletPath){
//			case "/sensizOlmuyor": request.getSession().setAttribute("/sensizOlmuyor", artır);
//				break;
//			case "/brehBreh": request.getSession().setAttribute("/brehBreh", artır2);
//				break;
//		}
//		switch (servletPath){
//			case "/sensizOlmuyor":  
//				if( ((Integer) request.getSession().getAttribute("/sensizOlmuyor")) < Integer.valueOf(2)) {
//					count = count+1;
//					artır = artır+1;
//				}
//				break;
//			case "/brehBreh" : 
//				break;
//		}
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getSiirAdi() {
		return siirAdi;
	}

	public void setSiirAdi(String siirAdi) {
		this.siirAdi = siirAdi;
	}

	public Siir getSiir() {
		return siir;
	}

	public void setSiir(Siir siir) {
		this.siir = siir;
	}
}
