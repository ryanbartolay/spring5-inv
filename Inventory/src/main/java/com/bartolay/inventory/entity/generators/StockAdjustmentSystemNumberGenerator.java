package com.bartolay.inventory.entity.generators;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import com.bartolay.inventory.repositories.GeneratedSystemNumber;

public class StockAdjustmentSystemNumberGenerator implements IdentifierGenerator {
	
	private String prefix = "SA";
	private String DELIMETER = "-";

	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
		Connection connection = session.connection();
		
		try {
			GeneratedSystemNumber obj = (GeneratedSystemNumber) object;
			
			String sql = "select max(system_number) as system_number from " + obj.getTableName() + " where location_id = ? and year = ?";
			
			PreparedStatement ps=connection.prepareStatement(sql);
			ps.setInt(1, obj.getLocation().getId());
			ps.setString(2, obj.getYear());

			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				String max_system_number = rs.getString("system_number");
				
				if(max_system_number != null) {
					
					String[] system_number = max_system_number.split(DELIMETER);
					
					Integer index = Integer.parseInt(system_number[2]);
					index++;
					
					return prefix + DELIMETER + system_number[1] + DELIMETER + index;
				} else {
					return prefix + DELIMETER + obj.getYear()+ obj.getLocation().getId() + DELIMETER + 1;
				}
			} 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

}
