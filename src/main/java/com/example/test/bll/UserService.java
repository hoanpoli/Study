package com.example.test.bll;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.test.common.Const;
import com.example.test.common.Utils;
import com.example.test.dal.AuthTokenDao;
import com.example.test.dal.UserDao;
import com.example.test.model.AuthToken;
import com.example.test.model.Users;

@Service(value = "userService")
@Transactional
public class UserService implements UserDetailsService {
	// region -- Fields --

	@Autowired
	private UserDao userDao;

	@Autowired
	private AuthTokenDao authTokenDao;

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	// end

	// region -- Methods --

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		Users u = userDao.getBy(userName);

		if (u == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}

		List<String> roles = userDao.getRoleByUserId(u.getId());
		String hash = u.getPasswordHash();

		return new User(userName, hash, getAuthority(roles));
	}

	public List<SimpleGrantedAuthority> getRole(int id) {
		List<String> roles = userDao.getRoleByUserId(id);
		List<SimpleGrantedAuthority> res = getAuthority(roles);
		return res;
	}

	private List<SimpleGrantedAuthority> getAuthority(List<String> roles) {
		return roles.stream().map(r -> new SimpleGrantedAuthority(r)).collect(Collectors.toList());
	}

	public Users getBy(int id) {
		Users res = userDao.getBy(id);
		return res;
	}

	public Users getBy(String userName, String email) {
		Users res = userDao.getBy(userName, email);
		return res;
	}

	public String save(Users m) {
		String res = "";

		Integer id = m.getId();
		String userName = m.getUserName();
		String email = m.getEmail();

		Users m1;
		if (id == null || id == 0) {
			m1 = userDao.getBy(userName, email);
			if (m1 != null) {
				res = "Duplicate data";
			} else {
				m.setUuid(UUID.randomUUID());

				userDao.save(m);
			}
		} else {
			m1 = userDao.getBy(id);
			if (m1 == null) {
				res = "Id does not exist";
			} else {

				m1.setFirstName(m.getFirstName());
				m1.setLastName(m.getLastName());
				m1.setAccountNo(m.getAccountNo());
				m1.setContactNo(m.getContactNo());
				m1.setEmail(m.getEmail());
				m1.setRemarks(m.getRemarks());

				userDao.save(m1);
			}
		}

		return res;
	}

	/**
	 * Generate token/OTP
	 * 
	 * @param module
	 *            Token/OTP of action (sign-in, transaction, ...)
	 * @param userId
	 * @param type
	 *            TOKEN or OTP or empty
	 * @return
	 * @throws Exception
	 */
	public AuthToken generateToken(String module, int userId, String type) throws Exception {
		AuthToken m = authTokenDao.getBy("", module, userId);

		if (m == null) {
			m = new AuthToken();
		}

		m.setCreateBy(userId);
		m.setCreateOn(new Date());

		String clientKey = bCryptPasswordEncoder.encode(new Date().toString());
		m.setClientKey(clientKey);

		String token = "";
		if (Const.Setting.CODE_TOKEN.equals(type)) {
			token = Const.Setting.CODE_TOKEN;
		} else if (Const.Setting.CODE_OTP.equals(type)) {
			token = Utils.getToken();
		} else {
			int n = Const.Authentication.TOKEN_NUMBER;
			token = Utils.getToken(n);
		}
		m.setToken(token);

		m.setModule(module);
		Date d = Utils.getTime(Calendar.MINUTE, Const.Authentication.TOKEN_MINUTE);
		m.setExpireOn(d);

		authTokenDao.save(m);

		return m;
	}
}