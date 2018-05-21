package com.bartolay.inventory.entity.generators;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import com.bartolay.inventory.repositories.GeneratedStockTransferSystemNumber;

public class StockTransferSystemNumberGenerator implements IdentifierGenerator {

	public static final String DELIMETER = "-";
	
	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {


		Connection connection = session.connection();

		try {
			GeneratedStockTransferSystemNumber obj = (GeneratedStockTransferSystemNumber) object;

			String sql = "select max(system_number) as system_number from " + obj.getTableName() + " where from_location_id = ? and to_location_id = ? and year = ?";

			PreparedStatement ps=connection.prepareStatement(sql);
			ps.setInt(1, obj.getFromLocation().getId());
			ps.setInt(2, obj.getToLocation().getId());
			ps.setString(3, obj.getYear());

			ResultSet rs = ps.executeQuery();

			if(rs.next()) {
				String max_system_number = rs.getString("system_number");

				if(max_system_number != null) {

					String[] system_number = max_system_number.split(DELIMETER);

					Integer index = Integer.parseInt(system_number[1]);
					index++;

					return system_number[0] + DELIMETER + system_number[1] + DELIMETER + system_number[2] + DELIMETER + index;
				} else {
					return obj.getYear() + DELIMETER +  obj.getFromLocation().getId() + DELIMETER + obj.getToLocation().getId() + DELIMETER + 1;
				}
			} 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
}
