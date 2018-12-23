package com.eduardosergio.TFG_cliente.negocio.seguridad.departamento;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ObfuscatedTransferObjectDepartamento {
	private List<String> maskedList;
	private HashMap<Object, Object> publicData;
	private HashMap<Object, Object> protectedData;
	
	public ObfuscatedTransferObjectDepartamento() {
		maskedList = new ArrayList<>();
		publicData = new HashMap<>();
		protectedData = new HashMap<>();
	}
	
	public void setProtectedData(Object key, Object value) {
		maskedList.add((String) key);
		protectedData.put(key, value);
	}
	
	public void setPublicData(Object key, Object value) {
		publicData.put(key, value);
	}
	
	public String toString() {
		String cadena = "";
		
		for (Entry<Object, Object> entrySet : publicData.entrySet()) {
			cadena += entrySet.getKey().toString() + " : " + entrySet.getValue().toString() + "\n";
		}
		
		return cadena;
	}
}
