package org.brijframework.jpa.factories;

import java.util.LinkedHashMap;

import org.brijframework.jpa.EntityNetwork;
import org.brijframework.jpa.builder.DataBuilder;
import org.brijframework.jpa.context.EntityContext;
import org.brijframework.jpa.factories.files.JsonFileDataFactory;

public class EntityNetworkFactory {
	LinkedHashMap<String, EntityNetwork> cache = new LinkedHashMap<>();
	
	private static EntityNetworkFactory factory;
	
	private EntityContext entityContext;
	
	public static EntityNetworkFactory getFactory() {
		if (factory == null) {
			factory = new EntityNetworkFactory();
			factory.loadFactory();
		}
		return factory;
	}
	
	public void loadFactory() {
		JsonFileDataFactory.getFactory().forType(EntityNetwork.class.getName(), network->{
			try {
				EntityNetwork networkModel=DataBuilder.getDataObject(network);
				register(networkModel);
			}catch (Exception e) {
			}
		});
	}

	public void setContext(EntityContext entityContext) {
		this.entityContext=entityContext;
	}
	
	public EntityContext getContext() {
		return entityContext;
	}

	
	public void register(EntityNetwork model) {
		getCache().put(model.getId(), model);
	}
	
	public EntityNetwork find(String key) {
		return getCache().get(key);
	}
	
	public LinkedHashMap<String, EntityNetwork> getCache() {
		return cache;
	}
}
