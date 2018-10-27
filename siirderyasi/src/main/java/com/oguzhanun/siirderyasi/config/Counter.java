package com.oguzhanun.siirderyasi.config;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.oguzhanun.siirderyasi.entity.Siir;
import com.oguzhanun.siirderyasi.service.SiirService;


@Component
@PropertySource("classpath:siirler.properties")
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
	
	private Siir siir;
	
	@Value("#{'${siirler}'.split(',')}")
	List<String> siirListesi;
	
	@Value("#{${users}}")
	private Map<String,String> users;
	
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
			
		System.out.println("şiir link adı =" +siirListesi.get(3)+ "-------");
		System.out.println("users=" + users.get("test1"));
		
		System.out.println("merhaba televole...");
		
		//request üzerinde üzerinde bulunana siir adını bulmak için...
		String servletPath = request.getServletPath();
		
		System.out.println("servletPath = "+ servletPath);
		
		System.out.println(request.getSession().getAttribute(servletPath));
		
		//anasayfa istendiğinde tiklama sayıları daha oluşturulmamış durumda nullpointer verir
		if(siirListesi.contains(servletPath)) {
			
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
