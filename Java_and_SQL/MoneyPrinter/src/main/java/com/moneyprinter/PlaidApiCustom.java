package com.moneyprinter;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.security.GeneralSecurityException;

import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.GmailScopes;
import com.google.api.services.gmail.model.Message;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.util.store.FileDataStoreFactory;



public class PlaidApiCustom {

	//This is actually a Gmail API until I figure out how to use plaid effectively.
	private static final String APPLICATION_NAME = "MoneyPrinter";
	private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
	private static final String TOKENS_DIRECTORY_PATH = "tokens";
    private static final List<String> SCOPES = Collections.singletonList(GmailScopes.MAIL_GOOGLE_COM);
    private static String JSON_STRING;
	boolean error = false;
	Gmail service;
	
	PlaidApiCustom(String gmail_json) throws IOException, GeneralSecurityException
	{
		JSON_STRING = gmail_json;
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        service = new Gmail.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
                .setApplicationName(APPLICATION_NAME)
                .build();
	}
	
	private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException
	{

        InputStream in = new ByteArrayInputStream(JSON_STRING.getBytes());
        //if (in == null) throw new FileNotFoundException("Resource not found in DB");
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
                .setAccessType("offline")
                .build();
        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
        Credential credential = new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
        return credential;
    }


	int excessCash(int max) throws IOException
	{
		int excess = 0;
        List<String> labelIds = Arrays.asList(new String[] {"Label_445470554250199365"});
        List<Message> msgIds = service.users().messages().list("me").setLabelIds(labelIds).execute().getMessages();
        if(msgIds != null)
        {
        	Message msg = service.users().messages().get("me", msgIds.get(0).getId()).execute();
        	String msgText = new String(msg.getPayload().getParts().get(0).getBody().decodeData());
        	int start = msgText.indexOf("Available Balance");
        	int end = msgText.indexOf(".", start);
        	int balance = Integer.parseInt(msgText.substring(start, end).replaceAll("\\D", ""));
        	excess = balance - max;
        	
            int msgCount = 0;
            for(Message msgId : msgIds) service.users().messages().trash("me", msgId.getId()).execute(); msgCount++;
            System.out.println("Messages deleted: " + msgCount);
        }
		return excess;
	}
}
