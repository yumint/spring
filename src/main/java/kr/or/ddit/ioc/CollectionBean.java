package kr.or.ddit.ioc;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class CollectionBean {
	
	// list<String> , Set<String> , Map<String,String> , Properties
	private List<String> list;
	private Set<String> set;
	private Map<String,String> map;
	private Properties property;
	
	
	//속성 set만있으면 된다
	public List<String> getList() {
		return list;
	}
	public void setList(List<String> list) {
		this.list = list;
	}
	public Set<String> getSet() {
		return set;
	}
	public void setSet(Set<String> set) {
		this.set = set;
	}
	public Map<String, String> getMap() {
		return map;
	}
	public void setMap(Map<String, String> map) {
		this.map = map;
	}
	public Properties getProperty() {
		return property;
	}
	public void setProperty(Properties property) {
		this.property = property;
	}
	
	
	

}
