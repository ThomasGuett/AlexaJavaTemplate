/*
 * Copyright (c) 2014 All Rights Reserved, http://www.apiomat.com/
 *
 * This source is property of apiomat.com. You are not allowed to use or distribute this code without a contract
 * explicitly giving you these permissions. Usage of this code includes but is not limited to running it on a server or
 * copying parts from it.
 *
 * Apinauten GmbH, Hainstrasse 10a, 04109 Leipzig, Germany
 *
 * 20.12.2017
 * thomas
 */
package com.alexa.lambda;

/**
 * @author Thomas Guett
 */
public enum SpeechText
{
	WELCOME( "Welcome to my Alexa Skill, how can I be of assistance", null ),
	HELP( "you can say Hello", null ),
	ERROR( "I am sorry, but an error occoured", null );

	private final String displayText;
	private final String SpokenText;

	SpeechText( String displayText, String spokenText )
	{
		this.displayText = displayText;
		this.SpokenText = null != spokenText ? spokenText : displayText;
	}

	/**
	 * @return the displayText
	 */
	public String getDisplayText( )
	{
		return this.displayText;
	}

	/**
	 * @return the ssmlSpokenText
	 */
	public String getSpokenText( )
	{
		return this.SpokenText;
	}
}
