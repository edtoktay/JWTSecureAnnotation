/**
 * 
 */
package org.edtoktay.springtools.security.jwt.annotation.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

/**
 * @author deniz.toktay
 *
 */
public class ScanSingleton {
	private static ScanSingleton instance;
	private ScanObject scanObject;
	private HashMap<String, List<String>> privateMethodMap;

	protected ScanSingleton() {
		this.scanObject = new ScanObject(new ArrayList<String>(), new ArrayList<JwtSecuredClasses>());
		this.privateMethodMap = new HashMap<String, List<String>>();
	}

	public static ScanSingleton getInstance() {
		if (instance == null)
			instance = new ScanSingleton();
		return instance;
	}

	public void addPublicPath(String path) {
		this.scanObject.getPublicMethods().add(path);
	}

	public void addPrivateObj(JwtSecuredClasses jsc) {
		this.scanObject.getPrivateMethods().add(jsc);
		this.privateMethodMap.put(jsc.getPath(), Arrays.asList(jsc.getRoles()));
	}

	public List<String> getPublicPaths() {
		return this.scanObject.getPublicMethods();
	}

	public List<JwtSecuredClasses> getPrivateObj() {
		return this.scanObject.getPrivateMethods();
	}

	public String getAccessString(String path) {
		StringBuilder sb = new StringBuilder();
		int count = this.privateMethodMap.get(path).size();
		String prefix = "hasRole('";
		String suffix = "')";
		String conj = "') and ";
		for (String role : this.privateMethodMap.get(path)) {
			if (StringUtils.isNotBlank(role)) {
				sb.append(prefix).append(role);
				if (count == 1)
					sb.append(suffix);
				else
					sb.append(conj);
				count--;
			}
		}
		return sb.toString();
	}
}
