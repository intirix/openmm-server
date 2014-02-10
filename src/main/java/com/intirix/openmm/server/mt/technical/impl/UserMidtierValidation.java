package com.intirix.openmm.server.mt.technical.impl;

import com.intirix.openmm.server.mt.OpenMMMidtierException;
import com.intirix.openmm.server.mt.technical.UserMidtier;
import com.intirix.openmm.server.mt.technical.beans.UserBean;

public class UserMidtierValidation extends UserMidtierDecorator
{

	public UserMidtierValidation( UserMidtier child )
	{
		super( child );
	}

	@Override
	public void updateUser( UserBean oldBean, UserBean newBean ) throws OpenMMMidtierException
	{
		if ( oldBean.isAdmin() && !newBean.isAdmin() && "admin".equals( oldBean.getUsername() ) )
		{
			throw new OpenMMMidtierException( "Cannot revoke admin access for user 'admin'" );
		}

		super.updateUser( oldBean, newBean );
	}

	
}
