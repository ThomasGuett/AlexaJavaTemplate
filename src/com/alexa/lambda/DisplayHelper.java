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

import java.util.regex.Pattern;

import com.amazon.speech.speechlet.interfaces.display.element.PlainText;
import com.amazon.speech.speechlet.interfaces.display.element.RichText;
import com.amazon.speech.speechlet.interfaces.display.template.Template.BackButtonBehavior;

/**
 * @author Thomas Guett
 */
public class DisplayHelper
{
	private static String SEPARATOR = "__";

	/**
	 *
	 * @param items - (required) Items to be listed in this page
	 * @param backgroundImageUrl - (optional) URL to a background image
	 * @return
	 */
	public static com.amazon.speech.speechlet.interfaces.display.template.ListTemplate1 getTemplate1ListPage(
		java.util.List<com.amazon.speech.speechlet.interfaces.display.template.ListTemplate1.ListItem> items,
		String backgroundImageUrl )
	{
		com.amazon.speech.speechlet.interfaces.display.template.ListTemplate1 listPage =
			new com.amazon.speech.speechlet.interfaces.display.template.ListTemplate1( );
		listPage.setListItems( items );
		if ( null != backgroundImageUrl )
		{
			listPage.setBackgroundImage( createImageFromUrl( backgroundImageUrl ) );
		}
		listPage.setBackButtonBehavior( BackButtonBehavior.VISIBLE );
		return listPage;
	}

	/**
	 *
	 * @param items - (required) Items to be listed in this page
	 * @return
	 */
	public static com.amazon.speech.speechlet.interfaces.display.template.ListTemplate1 getTemplate1ListPage(
		java.util.List<com.amazon.speech.speechlet.interfaces.display.template.ListTemplate1.ListItem> items )
	{
		return getTemplate1ListPage( items, null );
	}

	/**
	 *
	 * @param items - (required) Items to be listed in this page
	 * @param backgroundImageUrl - (optional) URL to a background image
	 * @return
	 */
	public static com.amazon.speech.speechlet.interfaces.display.template.ListTemplate2 getTemplate2ListPage(
		java.util.List<com.amazon.speech.speechlet.interfaces.display.template.ListTemplate2.ListItem> items,
		String backgroundImageUrl )
	{
		com.amazon.speech.speechlet.interfaces.display.template.ListTemplate2 listPage =
			new com.amazon.speech.speechlet.interfaces.display.template.ListTemplate2( );
		listPage.setListItems( items );
		if ( null != backgroundImageUrl )
		{
			listPage.setBackgroundImage( createImageFromUrl( backgroundImageUrl ) );
		}
		return listPage;
	}

	/**
	 *
	 * @param items - (required) Items to be listed in this page
	 * @return
	 */
	public static com.amazon.speech.speechlet.interfaces.display.template.ListTemplate2 getTemplate2ListPage(
		java.util.List<com.amazon.speech.speechlet.interfaces.display.template.ListTemplate2.ListItem> items )
	{
		return getTemplate2ListPage( items, null );
	}

	/**
	 * get Directive for Template
	 *
	 * @param template
	 * @return RenderTemplateDirective can be added to the SpeechletResponse
	 */
	public static com.amazon.speech.speechlet.interfaces.display.directive.RenderTemplateDirective getRenderDirective(
		com.amazon.speech.speechlet.interfaces.display.template.Template template )
	{
		com.amazon.speech.speechlet.interfaces.display.directive.RenderTemplateDirective renderDirective =
			new com.amazon.speech.speechlet.interfaces.display.directive.RenderTemplateDirective( );
		renderDirective.setTemplate( template );
		return renderDirective;
	}

	/**
	 * goes through all necessary steps to create an Image for a display
	 *
	 * @param imageUrl
	 * @return Image that can be included in a screen
	 */
	public static com.amazon.speech.speechlet.interfaces.display.element.Image createImageFromUrl( String imageUrl )
	{
		com.amazon.speech.speechlet.interfaces.display.element.ImageInstance imageInstance =
			new com.amazon.speech.speechlet.interfaces.display.element.ImageInstance( );
		imageInstance.setUrl( imageUrl );
		java.util.List<com.amazon.speech.speechlet.interfaces.display.element.ImageInstance> imageInstances =
			new java.util.ArrayList<com.amazon.speech.speechlet.interfaces.display.element.ImageInstance>( );
		imageInstances.add( imageInstance );
		com.amazon.speech.speechlet.interfaces.display.element.Image image =
			new com.amazon.speech.speechlet.interfaces.display.element.Image( );
		image.setSources( imageInstances );
		return image;
	}

	/**
	 * returns a fitting ListItem for ListTemplate1
	 *
	 * @param primaryText - (optional) headline Text for an Item
	 * @param secondaryText - (optional) secondary Text
	 * @param tertiaryText - (optional) additional small Text
	 * @param imageUrl - (optional) image for the list item
	 * @param token - (optional) set a token for hit-response
	 *
	 * @return ListTemplate1.ListItem
	 */
	public com.amazon.speech.speechlet.interfaces.display.template.ListTemplate1.ListItem getTemplate1ListItem(
		String primaryText, String secondaryText, String tertiaryText, String imageUrl, String token )
	{
		com.amazon.speech.speechlet.interfaces.display.template.ListTemplate1.ListItem listItem =
			new com.amazon.speech.speechlet.interfaces.display.template.ListTemplate1.ListItem( );
		if ( null != primaryText || null != secondaryText || null != tertiaryText )
		{
			System.out.println( "creating text field..." );
			com.amazon.speech.speechlet.interfaces.display.template.ListTemplate1.ListItem.TextContent textContent =
				new com.amazon.speech.speechlet.interfaces.display.template.ListTemplate1.ListItem.TextContent( );
			if ( null != primaryText )
			{
				textContent.setPrimaryText( getRichTextField( primaryText ) );
			}
			if ( null != secondaryText )
			{
				textContent.setPrimaryText( getRichTextField( secondaryText ) );
			}
			if ( null != tertiaryText )
			{
				textContent.setPrimaryText( getRichTextField( tertiaryText ) );
			}
			listItem.setTextContent( textContent );
		}
		if ( null != imageUrl )
		{
			listItem.setImage( createImageFromUrl( imageUrl ) );
		}
		if ( null != token )
		{
			listItem.setToken( token );
		}
		return listItem;
	}

	/**
	 * returns a fitting ListItem for ListTemplate2
	 *
	 * @param primaryText - (optional) headline Text for an Item
	 * @param secondaryText - (optional) secondary Text
	 * @param tertiaryText - (optional) additional small Text
	 * @param imageUrl - (optional) image for the list item
	 * @param token - (optional) set a token for hit-response
	 *
	 * @return ListTemplate1.ListItem
	 */
	public com.amazon.speech.speechlet.interfaces.display.template.ListTemplate2.ListItem getTemplate2ListItem(
		String primaryText, String secondaryText, String tertiaryText, String imageUrl, String token )
	{
		com.amazon.speech.speechlet.interfaces.display.template.ListTemplate2.ListItem listItem =
			new com.amazon.speech.speechlet.interfaces.display.template.ListTemplate2.ListItem( );
		if ( null != primaryText || null != secondaryText || null != tertiaryText )
		{
			System.out.println( "creating text field..." );
			com.amazon.speech.speechlet.interfaces.display.template.ListTemplate2.ListItem.TextContent textContent =
				new com.amazon.speech.speechlet.interfaces.display.template.ListTemplate2.ListItem.TextContent( );
			if ( null != primaryText )
			{
				textContent.setPrimaryText( getPlainTextField( primaryText ) );
			}
			if ( null != secondaryText )
			{
				textContent.setPrimaryText( getPlainTextField( secondaryText ) );
			}
			if ( null != tertiaryText )
			{
				textContent.setPrimaryText( getPlainTextField( tertiaryText ) );
			}
			listItem.setTextContent( textContent );
		}
		if ( null != imageUrl )
		{
			listItem.setImage( createImageFromUrl( imageUrl ) );
		}
		if ( null != token )
		{
			listItem.setToken( token );
		}
		return listItem;
	}

	/**
	 * get a simple PlainTextField for a String
	 *
	 * @param text
	 * @return
	 */
	public static PlainText getPlainTextField( String text )
	{
		PlainText plainText = new PlainText( );
		plainText.setText( text );
		return plainText;
	}

	/**
	 * get a richTextField for a String
	 *
	 * @param text
	 * @return
	 */
	public static RichText getRichTextField( String text )
	{
		RichText richText = new RichText( );
		richText.setText( text );
		return richText;
	}

	/**
	 * create quasi serialized token
	 *
	 * @param context
	 * @param id
	 * @return
	 */
	public static String createToken( String context, String id )
	{
		String token = null;
		if ( null != context )
		{
			token = context;
		}
		if ( null != id )
		{
			token += SEPARATOR + id;
		}
		return token;
	}

	/**
	 * returns the quasi deserialized info from token
	 *
	 * @param token
	 * @return
	 */
	public static java.util.Map<String, String> readToken( String token )
	{
		java.util.Map<String, String> tokenInfo = new java.util.HashMap<String, String>( );
		if ( null != token )
		{
			Pattern pattern = Pattern.compile( "(.*)" + SEPARATOR + "(.*)" );
			java.util.regex.Matcher m = pattern.matcher( token );
			if ( m.find( ) )
			{
				tokenInfo.put( "context", m.group( 1 ) );
				tokenInfo.put( "id", m.group( 2 ) );
			}
		}
		return tokenInfo;
	}
}
