package com.oguzhanun.siirderyasi.controller;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


import com.oguzhanun.siirderyasi.entity.Siir;
import com.oguzhanun.siirderyasi.entity.Uye;
import com.oguzhanun.siirderyasi.entity.VerificationToken;
import com.oguzhanun.siirderyasi.event.OnRegistrationCompleteEvent;
import com.oguzhanun.siirderyasi.service.SiirService;


@Controller
public class MainController {
	
	
	@Autowired
	private SiirService siirService;
	
	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;
	
	@Autowired
	private TaskExecutor taskExecutor;
	
	
	@RequestMapping("/anasayfa")
	public String anasayfa(Model model) {
		
		// veri tabanındaki tüm şiirler tıklama sayısına göre çekilecek
		// tiklama sayısı en fazla olan şiirin adresi iframe e gidecek (${siirAdres})
		// şiir listesi ise bir liste halinde model kutusuna yazılacak
		
		List<Siir> siirler = siirService.getSiirList();
		
		if(siirler != null) {
			
			model.addAttribute("siirler",siirler);
		
			Siir siir = siirler.get(0);
			model.addAttribute("tiklamaSayisi", Integer.toString(siir.getTiklama()));
			System.out.println("Şiir ="+ siir);
			
			model.addAttribute("siirAdi",siir.getSiirAdi());
			System.out.println("şiir adı ="+siir.getSiirAdi());
			
			model.addAttribute("muzikDosyasi", siir.getMuzik());
			
			model.addAttribute("resim",siir.getResim());
			
			//Dosya adresi Counter sınıfı üzerinden gelen siir nesnesinden alınacak...
			String siirAdres = siir.getAdres();
			siirAdres = siirAdres.substring(69);
			System.out.println("Şiir Adresi ="+ siirAdres);
			model.addAttribute("siirAdres",siirAdres);
		}
		return "anasayfa";
	}
	
	@RequestMapping("/anasayfa/{siir}")
	public String anasayfadaSec(@PathVariable("siir") String siirLinkAdi, Model model) {
		
		/*
		 * Controller a gelmeden önce Counter interceptor de şiirin tiklanma sayısı adı ve dosya adresi gibi bilgiler zaten alınmış durumda
		 * tiklama sayısı Counter sınıfında oluşturulan count değişkeni ile alınabilir...
		 * isim ve dosya adresi de veritabanı işlemi yapmadan alınabilir.
		 * bunlar model kutusuna atılacak
		 * 
		 * Dosya adresi ile dosya okunacak ve String bir değer olarak bir değişkene atanacak
		 * String değer değişkeni model kutusuna atılacak
		 * Sonrasında jsp el ile gösterimi sağlanacak*/
		List<Siir> siirler = siirService.getSiirList();
		
		if(siirler != null) {
			
			model.addAttribute("siirler",siirler);
		}
			
		Siir siir = siirService.getSiir(siirLinkAdi);
		model.addAttribute("tiklamaSayisi", Integer.toString(siir.getTiklama()));
		System.out.println("Şiir ="+ siir);
		
		// Şiirin Linkteki adı alınıyor
		model.addAttribute("siirLinkAdi",siirLinkAdi);
		System.out.println("şiir link adı ="+siirLinkAdi);
		
		// Şiirin adı
		model.addAttribute("siirAdi", siir.getSiirAdi());
		System.out.println("Şiir Adı :" + siir.getSiirAdi());
		
		//Müzik dosyasını yüklüyoruz...
		model.addAttribute("muzikDosyasi", "https://muzikmp3indir.com/mp3?t=1539703406&v=40332&h=9eaa4310f42081484681230c260335ba");
		
		model.addAttribute("resim",siir.getResim());
		
		//Dosya adresi Counter sınıfı üzerinden gelen siir nesnesinden alınacak...
		String siirAdres = siir.getAdres();
		siirAdres = siirAdres.substring(69);
		System.out.println("Şiir Adresi ="+ siirAdres);
		model.addAttribute("siirAdres",siirAdres);
				
		return "anasayfa";
	}
	
	@RequestMapping("/siirYukle")
	public String siirYukle(Model model) {
		
		Siir siir = new Siir();
		model.addAttribute("siir",siir);
		
		return "siirYukle";
	}
	
	@RequestMapping("/siirIsle")
	public String siirIsle( @RequestParam("dosya") MultipartFile dosya, 
							@RequestParam("siirAdi") String siirAdi, @RequestParam("siirLinkAdi") String siirLinkAdi,
							@RequestParam("muzik") String muzik,@RequestParam("resim") String resim, HttpServletRequest request ) {
		
		System.out.println("şiir adı :" + siirAdi);
		System.out.println("müzik dosyası :"+ muzik);
		System.out.println("siir link adı :"+ siirLinkAdi);
		System.out.println("dosya :" + dosya);
		System.out.println("resim :" + resim);
		
		String siirlerPath = request.getSession().getServletContext().getRealPath("/siirler");
		System.out.println("siirlerPath = " + siirlerPath);
		
		File file = null;
		
		if(dosya != null) {
			try {
				byte[] siirDosyasi = dosya.getBytes();
				
				file = new File("/Users/mustafaoguzhanun/eclipse-workspace/siirderyasi/src/main/webapp/resources/siirler" + "/" 
						+ dosya.getOriginalFilename()); //"classpath:/siirler/"+ dosya.getOriginalFilename() 
				System.out.println("file = " + file);
				
				file.createNewFile();
				
				OutputStream fos = new FileOutputStream(file);
				fos.write(siirDosyasi);
				fos.close();
				
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		
		Siir siir = new Siir();
		siir.setSiirAdi(siirAdi);
		siir.setAdres(file.toString());
		siir.setTiklama(0);
		siir.setMuzik(muzik);
		siir.setSiirLinkAdi(siirLinkAdi);
		siir.setResim(resim);
		siirService.siirEkle(siir);
		
		//dosya adı, şiir adı ve tıklanma sayısı ile veri tabanı kayıt işlemi yapılacak
		/*
		 * anasayfa istendiğinde en çok hangi şiir tıklama aldıysa o şiir gösterilecek
		 * yan panelde de en tüm şiirlerin listesi ve kaç kez tıklandığı gösterilecek
		 * yan panel için veri tabanında isim sorgusu yapılırken tıklama sayısına göre sıralama ile istenecek
		 * aynı şekilde sıralamada yukarıdan aşağıda tıklamalar da liste halinde alınacak
		 * model kutusuna bunlar atıldıktan sonra backend in işi bitecek */
		
		return "siirYukle";
	}
	
	@PostMapping("/uyeKayit")
	public String uyeKayit(@RequestParam("isim") String isim, @RequestParam("email") String email, Model model, HttpServletRequest request) {
		
		List<Siir> siirler = siirService.getSiirList();
		
		if(siirler != null) {
			
			model.addAttribute("siirler",siirler);
		
			Siir siir = siirler.get(0);
			model.addAttribute("tiklamaSayisi", Integer.toString(siir.getTiklama()));
			System.out.println("Şiir ="+ siir);
			
			model.addAttribute("siirAdi",siir.getSiirAdi());
			System.out.println("şiir adı ="+siir.getSiirAdi());
			
			model.addAttribute("muzikDosyasi", "/siirderyasi/resources/muzik/mp3indirdur-Mabel-Matiz-Sarmasik.mp3");
			
			//Dosya adresi Counter sınıfı üzerinden gelen siir nesnesinden alınacak...
			String siirAdres = siir.getAdres();
			siirAdres = siirAdres.substring(69);
			System.out.println("Şiir Adresi ="+ siirAdres);
			model.addAttribute("siirAdres",siirAdres);
		}
		//KULLANICI ADININ VERİTABANINDA ZATEN MEVCUT OLUP OLMADIĞINI KONTROL ET
		if(siirService.kullaniciVarMi(email)) {
				
				model.addAttribute("kullaniciVar", true);
				
				System.out.println("kullanıcı var...");
				
				return "redirect:/anasayfa";
		} 
			
		//HERŞEY YOLUNDA İSE KAYIT İŞLEMİNİ GERÇEKLEŞTİR
		else {
			
			int onayli = 0;
			
			Uye uye = new Uye();
			uye.setEmail(email);
			uye.setIsim(isim);
			uye.setOnayli(onayli);
			
			String appUrl = request.getContextPath();
			System.out.println("context path alındı...");
			
			// BAŞKA BİR THREAD E GEÇMEK TE FAYDA VAR BURADA MAIL GÖNDERİMİ SIRASINDA YAVAŞLAMA OLUYOR...
			taskExecutor.execute(new Runnable() {
				@Override
				public void run() {
					try {
						applicationEventPublisher.publishEvent(new OnRegistrationCompleteEvent(uye, request.getLocale(), appUrl));
						System.out.println("event publish edildi...");
					}catch(Exception exc) {
						exc.printStackTrace();
						//return "uyeOlustur"; //Başka bir sayfa daha sonra buraya yapılması gerekiyor...
					}
				}
			});
			
			//Müşteri veritabanını da burada oluşturuyoruz...
			siirService.setUye(uye);

			return "redirect:/anasayfa";
		}
	}

	@RequestMapping("/uyeOnay/{token}")
	public String uyeOnay(@PathVariable("token") String token, Model model) {
		
		VerificationToken vt;
		
		if((vt = siirService.findToken(token)) != null){
			
			if(System.currentTimeMillis() < vt.getExpiryDate().getTime()) {
				String kullaniciAdi = vt.getEmail();
				
				siirService.setOnay(kullaniciAdi, 1);
			
				return "redirect:/anasayfa";
			
			} else {
				
				String kullaniciAdi = vt.getEmail();
				
				siirService.uyeSil(kullaniciAdi);
				
				return "zamanAsimi";} //ÜYELİK BİLGİLERİNİN SİLİNMESİ DE GEREKİYOR MUTLAKA...
			
		} else

			return "hataliToken";
	}
	
	@RequestMapping("/uyeSil/{token}")
	public String uyeSil(@PathVariable("token") String token, Model model) {
		
		VerificationToken vt;
		
		if((vt = siirService.findToken(token)) != null){
			
			String kullaniciAdi = vt.getEmail();
				
			siirService.uyeSil(kullaniciAdi);
			
			return "uyeSilindi";
		} else
		
		return "silmeHatasi";
			
		
	}
}



///Kullanıcılar/mustafaoguzhanun/.m2
