package com.intirix.openmm.server.mt.technical.sql;

import java.sql.Types;

public class SQLNull
{


	public static class NullObject
	{
		int type;
		
		public NullObject( int type )
		{
			this.type = type;
		}
		
		public int getType()
		{
			return type;
		}
	}
	
	public static NullObject StringNull = new NullObject( Types.VARCHAR );
	public static NullObject TimestampNull = new NullObject( Types.TIMESTAMP );
	public static NullObject IntNull = new NullObject( Types.INTEGER );

}
