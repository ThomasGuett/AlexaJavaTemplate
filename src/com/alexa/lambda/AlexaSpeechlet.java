/*
 * Copyright (c) 2014 All Rights Reserved, http://www.apiomat.com/
 *
 * This source is property of apiomat.com. You are not allowed to use or distribute this code without a contract
 * explicitly giving you these permissions. Usage of this code includes but is not limited to running it on a server or
 * copying parts from it.
 *
 * Apinauten GmbH, Hainstrasse 10a, 04109 Leipzig, Germany
 *
 * 21.11.2017
 * thomas
 */
package com.alexa.lambda;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazon.speech.json.SpeechletRequestEnvelope;
import com.amazon.speech.slu.Intent;
import com.amazon.speech.speechlet.Context;
import com.amazon.speech.speechlet.IntentRequest;
import com.amazon.speech.speechlet.LaunchRequest;
import com.amazon.speech.speechlet.Session;
import com.amazon.speech.speechlet.SessionEndedRequest;
import com.amazon.speech.speechlet.SessionStartedRequest;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazon.speech.speechlet.SpeechletV2;
import com.amazon.speech.speechlet.interfaces.display.Display;
import com.amazon.speech.speechlet.interfaces.display.request.ElementSelectedRequest;

/**
 * @author Thomas Guett
 */
public class AlexaSpeechlet implements SpeechletV2, Display
{
	private static final Logger log = LoggerFactory.getLogger( AlexaSpeechlet.class );

	private SpeechletResponse handleStopIntent( )
	{
		List<String> goodByeMessages = new ArrayList<String>( );
		goodByeMessages.add( "bis zum nächsten mal" );
		goodByeMessages.add( "auf wiedersehen" );
		goodByeMessages.add( "hat mich sehr gefreut" );
		return SpeechletHelper.say( randomizeResponse( goodByeMessages ) );
	}

	/**
	 * for a more compelling user experience a response variation is needed
	 *
	 * @param availableResponses
	 * @return returns a randomized response
	 */
	protected String randomizeResponse( List<String> availableResponses )
	{
		return availableResponses.get( 0 + ( int ) ( Math.random( ) * availableResponses.size( ) ) );
	}

	/* (non-Javadoc)
	 *
	 * @see com.amazon.speech.speechlet.SpeechletV2#onSessionStarted(com.amazon.speech.json.SpeechletRequestEnvelope) */
	@Override
	public void onSessionStarted( SpeechletRequestEnvelope<SessionStartedRequest> requestEnvelope )
	{
		//		final Session session = requestEnvelope.getSession( );
	}

	/* (non-Javadoc)
	 *
	 * @see com.amazon.speech.speechlet.SpeechletV2#onLaunch(com.amazon.speech.json.SpeechletRequestEnvelope) */
	@Override
	public SpeechletResponse onLaunch( SpeechletRequestEnvelope<LaunchRequest> requestEnvelope )
	{
		/* read AppConfig for Details how to respond and save it to session */
		Session session = requestEnvelope.getSession( );
		SpeechletResponse response = handleWelcomeIntent( session );
		return response;
	}

	/* (non-Javadoc)
	 *
	 * @see com.amazon.speech.speechlet.SpeechletV2#onIntent(com.amazon.speech.json.SpeechletRequestEnvelope) */
	@Override
	public SpeechletResponse onIntent( SpeechletRequestEnvelope<IntentRequest> requestEnvelope )
	{
		Session session = requestEnvelope.getSession( ); // e.g. save conversation infos
		Context context = requestEnvelope.getContext( );
		if ( context.hasState( com.amazon.speech.speechlet.interfaces.display.DisplayInterface.class ) )
		{
			System.out.println( "found display reference in context..." );
		}
		IntentRequest request = requestEnvelope.getRequest( );
		Intent intent = request.getIntent( );
		switch ( intent.getName( ) )
		{
		case "AMAZON.StopIntent":
			return handleStopIntent( );
		case "AMAZON.CancelIntent":
			return handleStopIntent( );
		case "AMAZON.HelpIntent":
			return handleHelpIntent( );
		default:
			System.out.println( "invoking welcome method..." );
			return handleWelcomeIntent( session );
		}
	}

	/* (non-Javadoc)
	 *
	 * @see com.amazon.speech.speechlet.SpeechletV2#onSessionEnded(com.amazon.speech.json.SpeechletRequestEnvelope) */
	@Override
	public void onSessionEnded( SpeechletRequestEnvelope<SessionEndedRequest> requestEnvelope )
	{
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 *
	 * @see com.amazon.speech.speechlet.interfaces.display.Display#onElementSelected(com.amazon.speech.json.
	 * SpeechletRequestEnvelope) */
	@Override
	public SpeechletResponse onElementSelected( SpeechletRequestEnvelope<ElementSelectedRequest> requestEnvelope )
	{
		return null;
	}

	/**
	 * required Method for submitting your skill
	 *
	 * @return
	 */
	private static SpeechletResponse handleHelpIntent( )
	{
		return SpeechletHelper.say( SpeechText.HELP.getSpokenText( ) );
	}

	/**
	 * method gets called when Skill is opened without inline commands
	 *
	 * @param session
	 *
	 * @return
	 */
	private SpeechletResponse handleWelcomeIntent( final Session session )
	{
		return SpeechletHelper.ask( SpeechText.WELCOME.getSpokenText( ) );
	}
}
