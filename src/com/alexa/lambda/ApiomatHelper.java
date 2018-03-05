/*
 * Copyright (c) 2014 All Rights Reserved, http://www.apiomat.com/
 *
 * This source is property of apiomat.com. You are not allowed to use or distribute this code without a contract
 * explicitly giving you these permissions. Usage of this code includes but is not limited to running it on a server or
 * copying parts from it.
 *
 * Apinauten GmbH, Hainstrasse 10a, 04109 Leipzig, Germany
 *
 * 05.01.2018
 * thomas
 */
package com.alexa.lambda;

/**
 * @author Thomas Guett
 */
public class ApiomatHelper
{

	/**
	 * small helper method to extract the id from an objects href
	 * 
	 * @author Thomas Guett
	 * @param href
	 * @return
	 */
	public static String getIdFromHref( String href )
	{
		String id = null;
		if ( null != href )
		{
			id = href.substring( href.lastIndexOf( "/" ) + 1 );
		}
		return id;
	}
}
