package com.bartolay.inventory.entity.generators;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.stream.Stream;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import com.bartolay.inventory.entity.stock.StockOpening;

public class SystemNumberGenerator implements IdentifierGenerator {

	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object)
			throws HibernateException {

		Connection connection = session.connection();

		try {
			StockOpening opening = (StockOpening) object;
			System.err.println(opening.getDocumentNumber());
			
			Statement statement=connection.createStatement();
			
			

			ResultSet rs=statement.executeQuery("select count(systemnumber) as Id from stock_opening");

			if(rs.next())
			{
				int id=rs.getInt(1)+101;
				String generatedId = new Integer(id).toString();
				System.out.println("Generated Id: " + generatedId);
				return generatedId;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return null;
	}
}
