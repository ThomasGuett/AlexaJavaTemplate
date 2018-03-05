/* Copyright (c) 2014 All Rights Reserved, http://www.apiomat.com/
 *
 * This source is property of apiomat.com. You are not allowed to use or distribute this code without a contract
 * explicitly giving you these permissions. Usage of this code includes but is not limited to running it on a server or
 * copying parts from it.
 *
 * Apinauten GmbH, Hainstrasse 10a, 04109 Leipzig, Germany
 *
 * 19.07.2017
 * Jens Awisus */
package com.alexa.lambda;

import java.util.HashSet;
import java.util.Set;

import com.amazon.speech.speechlet.lambda.SpeechletRequestStreamHandler;

/**
 * @author Thomas Guett
 */
public class AlexaSpeechletRequestStreamHandler extends SpeechletRequestStreamHandler
{
	private static final Set<String> supportedApplicationIds = new HashSet<String>( );

	static
	{
		/* This Id can be found on https://developer.amazon.com/edw/home.html#/ "Edit" the relevant
		 * Alexa Skill and put the relevant Application Ids in this Set. */
		supportedApplicationIds.add( "amzn..." ); // my Skill Id
	}

	public AlexaSpeechletRequestStreamHandler( )
	{
		super( new AlexaSpeechlet( ), supportedApplicationIds );
	}
}