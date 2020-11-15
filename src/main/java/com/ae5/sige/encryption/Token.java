package com.ae5.sige.encryption;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ae5.sige.model.Usuario;

@Configuration
@EnableScheduling
public class Token {
	
	private static final Log LOG = LogFactory.getLog(Token.class);
	private static List<JSONArray> tokens = new ArrayList<>();
		
	public static JSONArray createToken(Usuario user) {

		String[] dnitipo = {user.getDni(),user.getTipo(),user.getNombre(),user.getApellidos()};
		System.out.println(dnitipo);
		String md5 = getMD5(dnitipo.toString());
		String json = getMD5(dnitipo+md5);
		JSONArray token = new JSONArray();
		token.put(dnitipo);
		token.put(json);
		token.put(LocalTime.now());
		tokens.add(token);
		return token;
				
	}
	
	public JSONArray checkToken(JSONArray token) throws JSONException {
		JSONArray aux = token;
		if(tokens.contains(token)) {
			token.put(2, LocalTime.now());
			tokens.add(tokens.indexOf(aux), token);
			return token;
		}else {
			return null;
		}
	}
	@Scheduled(fixedDelay = 30000)
	public void deletetokenaftertime() throws JSONException {
		LOG.info("Comprobación token antiguos");
		LocalTime aux = LocalTime.now();
		List<JSONArray> tokenaux = new ArrayList<>();
		for(int i = 0; i<tokens.size(); i++) {
			tokenaux.add(tokens.get(i));
		}

		while(!tokenaux.isEmpty()) {
			JSONArray tokenact= tokenaux.remove(0);
			if(Duration.between(aux, (LocalTime) tokenact.get(2)).toMinutes() > 3.0 ) {
				tokens.remove(tokens.indexOf(tokenact));
			}
		}
	}
	
	public static String getMD5(String input) {
		 try {
		 MessageDigest md = MessageDigest.getInstance("MD5");
		 byte[] messageDigest = md.digest(input.getBytes());
		 BigInteger number = new BigInteger(1, messageDigest);
		 String hashtext = number.toString(16);

		 while (hashtext.length() < 32) {
		 hashtext = "0" + hashtext;
		 }
		 return hashtext;
		 }
		 catch (NoSuchAlgorithmException e) {
		 throw new RuntimeException(e);
		 }
		 }
}
