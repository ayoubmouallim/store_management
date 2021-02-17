package com.DAO;

import java.sql.Connection;

public class AbstarctDao {
	
	 protected Connection connection = SignleConnection.getConnection();
	
	
	

}
