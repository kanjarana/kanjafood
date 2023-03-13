package br.com.kanjarana.kanjafood;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Oi {
	
	@GetMapping("/oi")
	@ResponseBody
	public String oi() {
		return "<h1>Oi Éder</h1>";
	}

}
