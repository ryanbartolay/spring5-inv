package com.bartolay.inventory.entity.generators;

import java.io.Serializable;
import java.util.stream.Stream;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class SystemNumberGenerator implements IdentifierGenerator {

	private String prefix;
	
	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object obj) throws HibernateException {
		String query = String.format("select %s from %s", 
				session.getEntityPersister(obj.getClass().getName(), obj)
				.getIdentifierPropertyName(),
				obj.getClass().getSimpleName());

		System.err.println("query " + query);
		Stream ids = session.createQuery(query).stream();

//		Long max = ids.map(o -> ((String) o).replace(prefix + "-", ""))
//				.mapToLong(Long::parseLong)
//				.max()
//				.orElse(0L);

//		return prefix + "-" + (max + 1);
		return null;
	}

}
