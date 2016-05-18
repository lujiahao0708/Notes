package com.android.volley.toolbox;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class HttpsTrustManager implements X509TrustManager {

	private static TrustManager[] trustManagers;

	@Override
	public void checkClientTrusted(X509Certificate[] x509Certificates, String s)
			throws java.security.cert.CertificateException {
		// To change body of implemented methods use File | Settings | File
		// Templates.
	}

	@Override
	public void checkServerTrusted(X509Certificate[] x509Certificates, String s)
			throws java.security.cert.CertificateException {
		// To change body of implemented methods use File | Settings | File
		// Templates.
	}

	@Override
	public X509Certificate[] getAcceptedIssuers() {
		return new X509Certificate[0];
	}

	public static SSLSocketFactory allowAllSSL() {
		HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {

            @Override
            public boolean verify(String arg0, SSLSession arg1) {
                return true;
            }

        });

		SSLContext context;
		if (trustManagers == null) {
			trustManagers = new TrustManager[] { new HttpsTrustManager() };
		}

		try {
			context = SSLContext.getInstance("TLS");
			context.init(null, trustManagers, new SecureRandom());
			HttpsURLConnection.setDefaultSSLSocketFactory(context.getSocketFactory());
			return context.getSocketFactory();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (KeyManagementException e) {
			e.printStackTrace();
		}
		return null;
	}

}
