package com.alexa.lambda;

import java.util.List;

import com.amazon.speech.slu.Slot;
import com.amazon.speech.slu.entityresolution.Resolution;
import com.amazon.speech.slu.entityresolution.Resolutions;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazon.speech.ui.Card;
import com.amazon.speech.ui.Reprompt;
import com.amazon.speech.ui.SsmlOutputSpeech;

/**
 *
 * @author Thomas Guett
 */
public class SpeechletHelper
{
	public static SpeechletResponse say( String speechText )
	{
		SsmlOutputSpeech speech = new SsmlOutputSpeech( );
		speech.setSsml( "<speak>" + speechText + "</speak>" );
		SpeechletResponse response = SpeechletResponse.newTellResponse( speech );
		response.setNullableShouldEndSession( null );
		return response;
	}

	public static SpeechletResponse say( String speechText, Card card )
	{
		SsmlOutputSpeech speech = new SsmlOutputSpeech( );
		speech.setSsml( "<speak>" + speechText + "</speak>" );
		SpeechletResponse response = SpeechletResponse.newTellResponse( speech, card );
		response.setNullableShouldEndSession( null );
		return response;
	}

	public static SpeechletResponse ask( String speechText )
	{
		SsmlOutputSpeech speech = new SsmlOutputSpeech( );
		speech.setSsml( "<speak>" + speechText + "</speak>" );
		Reprompt reprompt = new Reprompt( );

		SpeechletResponse response = SpeechletResponse.newAskResponse( speech, reprompt );
		response.setNullableShouldEndSession( null );
		return response;
	}

	public static SpeechletResponse ask( String speechText, Card card )
	{
		SsmlOutputSpeech speech = new SsmlOutputSpeech( );
		speech.setSsml( "<speak>" + speechText + "</speak>" );
		Reprompt reprompt = new Reprompt( );

		SpeechletResponse response = SpeechletResponse.newAskResponse( speech, reprompt, card );
		response.setNullableShouldEndSession( null );
		return response;
	}

	public static SpeechletResponse askAgain( String question )
	{
		String speechText = "Ich konnte Ihre Eingabe leider nicht verstehen. " + question;
		return ask( speechText );
	}

	/**
	 * responds with Speechlet including Renderdirectives
	 * 
	 * @param speechText - Speech-Reply
	 * @param directives - visual reply
	 * @return
	 */
	public static SpeechletResponse ask( String speechText,
		java.util.List<com.amazon.speech.speechlet.Directive> directives )
	{
		SsmlOutputSpeech speech = new SsmlOutputSpeech( );
		speech.setSsml( "<speak>" + speechText + "</speak>" );
		Reprompt reprompt = new Reprompt( );
		SpeechletResponse response = SpeechletResponse.newAskResponse( speech, reprompt );
		response.setDirectives( directives );
		response.setNullableShouldEndSession( null );
		return response;
	}

	/**
	 * helper method to retrieve correct slot value either synonym or legacy
	 * 
	 * @param slot
	 * @return
	 */
	public static String resolveSlotValue( Slot slot )
	{
		String slotValue = slot.getValue( );
		Resolutions resolutions = slot.getResolutions( );
		if ( null != resolutions )
		{
			List<Resolution> foundResolutions = resolutions.getResolutionsPerAuthority( );
			if ( foundResolutions.size( ) > 0 )
			{
				return foundResolutions.get( 0 ).getValueWrappers( ).get( 0 ).getValue( ).getName( );
			}
		}
		return slotValue;
	}
}
