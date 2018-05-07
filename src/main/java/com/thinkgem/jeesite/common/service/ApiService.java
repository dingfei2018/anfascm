package com.thinkgem.jeesite.common.service;

import com.thinkgem.jeesite.common.utils.JedisUtils;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.Jedis;

import java.io.*;
import java.security.Key;
import java.util.Date;

/**
 * token登陆验证 Created by Hamming on 2016/12/23.
 */
@Service
public class ApiService {
	private static final String at = "accessToken";

	public static Key key;

	// private Logger logger = LoggerFactory.getLogger(getClass());
	/**
	 * 生成token Key以字节流形式存入redis
	 * 
	 * @param date
	 *            失效时间
	 * @param appId
	 *            AppId
	 * @return
	 */
	public String generateToken(Date date, String appId) {
		Jedis jedis = null;
		try {
			jedis = JedisUtils.getResource();
			byte[] buf = jedis.get("api:key".getBytes());
			if (buf == null) { // 建新的key
				key = MacProvider.generateKey();
				ByteArrayOutputStream bao = new ByteArrayOutputStream();
				ObjectOutputStream oos = new ObjectOutputStream(bao);
				oos.writeObject(key);
				buf = bao.toByteArray();
				jedis.set("api:key".getBytes(), buf);
			} else { // 重用老key
				key = (Key) new ObjectInputStream(new ByteArrayInputStream(buf)).readObject();
			}

		} catch (IOException io) {
			// System.out.println(io);
		} catch (ClassNotFoundException c) {
			// System.out.println(c);
		} catch (Exception e) {
			// logger.error("ApiService", "generateToken", key, e);
		} finally {
			JedisUtils.returnResource(jedis);
		}

		String token = Jwts.builder().setSubject(appId).signWith(SignatureAlgorithm.HS512, key).setExpiration(date)
				.compact();
		// 计算失效秒,7889400秒三个月
		Date temp = new Date();
		long interval = (date.getTime() - temp.getTime()) / 1000;
		JedisUtils.set(at + appId, token, (int) interval);
		return token;
	}

	/**
	 * 验证token
	 * 
	 * @param appId
	 *            AppId
	 * @param token
	 *            token
	 * @return
	 */
	public boolean verifyToken(String appId, String token) {
		if (appId == null || token == null) {
			return false;
		}
		Jedis jedis = null;
		try {
			jedis = JedisUtils.getResource();
			if (key == null) {
				byte[] buf = jedis.get("api:key".getBytes());
				if (buf == null) {
					return false;
				}
				key = (Key) new ObjectInputStream(new ByteArrayInputStream(buf)).readObject();
			}
			Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody().getSubject().equals(appId);
			return true;
		} catch (Exception e) {
			// logger.error("ApiService", "verifyToken", key, e);
			return false;
		} finally {
			JedisUtils.returnResource(jedis);
		}
	}

	/**
	 * 获取token
	 * 
	 * @param appId
	 * @return
	 */
	public String getToken(String appId) {
		Jedis jedis = null;
		try {
			jedis = JedisUtils.getResource();
			return jedis.get(at + appId);
		} catch (Exception e) {
			// logger.error("ApiService", "getToken", e);
			return "";
		} finally {
			JedisUtils.returnResource(jedis);
		}
	}
}